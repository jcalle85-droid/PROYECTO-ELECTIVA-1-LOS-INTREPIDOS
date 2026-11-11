package co.edu.uan.android.uancasts.historial_vehiculos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.edu.uan.android.uancasts.ParkingData
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class HistorialViewModel {

    var vehiculos by mutableStateOf<List<VehiculoHistorial>>(
        emptyList()
    )
        private set

    private val formatoFecha = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    )

    init {
        cargarHistorial()
    }

    // Consultar los movimientos registrados por el parqueadero.
    fun cargarHistorial() {

        vehiculos = ParkingData.historial
            .obtenerTodoElHistorial()
            .mapNotNull { movimiento ->

                val entrada = convertirFecha(
                    movimiento.horaEntrada
                ) ?: return@mapNotNull null

                val salida = movimiento.horaSalida?.let {
                    convertirFecha(it)
                }

                val tarifaBase = when (
                    movimiento.tipo.lowercase(Locale.ROOT)
                ) {
                    "carro" -> 5000.0
                    "moto" -> 2000.0
                    else -> 0.0
                }

                val tarifaReal = if (
                    movimiento.total != null &&
                    movimiento.tiempoHoras != null &&
                    movimiento.tiempoHoras > 0
                ) {
                    movimiento.total / movimiento.tiempoHoras
                } else {
                    tarifaBase
                }

                VehiculoHistorial(
                    id = movimiento.id.toString(),
                    placa = movimiento.placa,
                    tipoVehiculo = movimiento.tipo.replaceFirstChar {
                        it.uppercase(Locale.ROOT)
                    },
                    fechaHoraEntrada = entrada,
                    fechaHoraSalida = salida,
                    tarifaPorHora = tarifaReal,
                    totalCobrado = movimiento.total,
                    finalizado = movimiento.estado == "FINALIZADO"
                )
            }
            .sortedByDescending { it.fechaHoraEntrada }
    }

    // Convertir las fechas del administrador a milisegundos.
    private fun convertirFecha(fecha: String): Long? {
        return try {
            LocalDateTime.parse(
                fecha,
                formatoFecha
            ).atZone(
                ZoneId.systemDefault()
            ).toInstant().toEpochMilli()

        } catch (e: Exception) {
            null
        }
    }

    // Consultar un vehículo por su identificador.
    fun obtenerVehiculo(id: String): VehiculoHistorial? {
        return vehiculos.find {
            it.id == id
        }
    }
}
