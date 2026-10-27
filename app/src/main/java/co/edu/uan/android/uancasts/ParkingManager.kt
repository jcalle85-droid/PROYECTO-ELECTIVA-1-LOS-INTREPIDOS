
package co.edu.uan.android.uancasts

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.ceil

class ParkingManager(
    private val movimientos: MutableList<RegMovimiento> = mutableListOf(),
    val capacidadTotal: Int = 50
) {

    private val tarifas = mutableMapOf(
        "carro" to 5000.0,
        "moto" to 2000.0
    )

    // Placa -> número de cupo asignado
    private val cuposOcupados = mutableMapOf<String, Int>()
    private val cuposReservados = mutableMapOf<String, Int>()

    // Placa -> tipo de vehículo reservado
    private val tiposReservados = mutableMapOf<String, String>()

    // Identificadores de movimientos cuyo pago ya se registró
    private val pagosRegistrados = mutableSetOf<Int>()

    private var siguienteId = 1

    private val formatoFecha =
        DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        )

    init {
        require(capacidadTotal > 0) {
            "La capacidad del parqueadero debe ser mayor que cero."
        }
    }

    private fun normalizarPlaca(placa: String): String =
        placa.trim().uppercase(Locale.ROOT)

    private fun validarPlaca(placa: String, tipo: String): Boolean {
        val placaNormalizada = normalizarPlaca(placa)

        return when (tipo.trim().lowercase(Locale.ROOT)) {
            "carro" ->
                Regex("^[A-Z]{3}[0-9]{3}$")
                    .matches(placaNormalizada)

            "moto" ->
                Regex("^[A-Z]{3}[0-9]{2}[A-Z]?$")
                    .matches(placaNormalizada)

            else -> false
        }
    }

    private fun buscarCupoDisponible(): Int? {
        val ocupados = cuposOcupados.values.toSet()
        val reservados = cuposReservados.values.toSet()

        return (1..capacidadTotal).firstOrNull {
            it !in ocupados && it !in reservados
        }
    }

    private fun fechaActual(): String =
        LocalDateTime.now().format(formatoFecha)

    fun registrarEntrada(placa: String, tipo: String): String {
        val placaNormalizada = normalizarPlaca(placa)
        val tipoNormalizado = tipo.trim().lowercase(Locale.ROOT)

        if (placaNormalizada.isBlank()) {
            return "Ingresa la placa del vehículo."
        }

        if (tipoNormalizado !in tarifas.keys) {
            return "El tipo de vehículo debe ser carro o moto."
        }

        if (!validarPlaca(placaNormalizada, tipoNormalizado)) {
            return when (tipoNormalizado) {
                "carro" ->
                    "Placa inválida para carro. Usa 3 letras y 3 números."

                "moto" ->
                    "Placa inválida para moto. Usa 3 letras, 2 números y, opcionalmente, una letra final."

                else -> "Tipo de vehículo no válido."
            }
        }

        if (placaNormalizada in cuposOcupados) {
            return "Este vehículo ya se encuentra en el parqueadero."
        }

        val movimientoActivo = movimientos.any {
            it.placa == placaNormalizada && it.estado == "ACTIVO"
        }

        if (movimientoActivo) {
            return "Este vehículo ya tiene una entrada activa."
        }

        val tipoReserva = tiposReservados[placaNormalizada]
        if (tipoReserva != null && tipoReserva != tipoNormalizado) {
            return "El tipo de vehículo no coincide con el de la reserva."
        }

        val reserva = cuposReservados.remove(placaNormalizada)
        tiposReservados.remove(placaNormalizada)

        val cupo = reserva ?: buscarCupoDisponible()
        ?: return "No hay cupos disponibles."

        cuposOcupados[placaNormalizada] = cupo

        movimientos.add(
            RegMovimiento(
                id = siguienteId++,
                placa = placaNormalizada,
                tipo = tipoNormalizado,
                horaEntrada = fechaActual(),
                horaSalida = null,
                tiempoHoras = null,
                total = null,
                estado = "ACTIVO"
            )
        )

        return "Entrada registrada. Cupo asignado: $cupo."
    }

    // Versión compatible con llamadas antiguas.
    // Si la placa tiene una reserva, utiliza su tipo.
    // Si no hay reserva, intenta identificar el formato.
    fun reservarCupo(placa: String): String {
        val placaNormalizada = normalizarPlaca(placa)

        val tipoExistente = tiposReservados[placaNormalizada]
        if (tipoExistente != null) {
            return "Este vehículo ya tiene una reserva."
        }

        val tipoDetectado = when {
            Regex("^[A-Z]{3}[0-9]{3}$")
                .matches(placaNormalizada) -> "carro"

            Regex("^[A-Z]{3}[0-9]{2}[A-Z]?$")
                .matches(placaNormalizada) -> "moto"

            else -> return "Placa inválida. Revisa el formato del vehículo."
        }

        return reservarCupo(placaNormalizada, tipoDetectado)
    }

    fun reservarCupo(placa: String, tipo: String): String {
        val placaNormalizada = normalizarPlaca(placa)
        val tipoNormalizado = tipo.trim().lowercase(Locale.ROOT)

        if (placaNormalizada.isBlank()) {
            return "Ingresa la placa para realizar la reserva."
        }

        if (tipoNormalizado !in tarifas.keys) {
            return "El tipo de vehículo debe ser carro o moto."
        }

        if (!validarPlaca(placaNormalizada, tipoNormalizado)) {
            return when (tipoNormalizado) {
                "carro" ->
                    "Placa inválida para carro. Usa 3 letras y 3 números."

                "moto" ->
                    "Placa inválida para moto. Usa 3 letras, 2 números y, opcionalmente, una letra final."

                else -> "Tipo de vehículo no válido."
            }
        }

        if (placaNormalizada in cuposOcupados) {
            return "Este vehículo ya está dentro del parqueadero."
        }

        if (placaNormalizada in cuposReservados) {
            return "Este vehículo ya tiene una reserva."
        }

        val movimientoActivo = movimientos.any {
            it.placa == placaNormalizada && it.estado == "ACTIVO"
        }

        if (movimientoActivo) {
            return "Este vehículo ya tiene una entrada activa."
        }

        val cupo = buscarCupoDisponible()
            ?: return "No hay cupos disponibles para reservar."

        cuposReservados[placaNormalizada] = cupo
        tiposReservados[placaNormalizada] = tipoNormalizado

        return "Reserva realizada. Cupo reservado: $cupo."
    }

    fun cancelarReserva(placa: String): String {
        val placaNormalizada = normalizarPlaca(placa)

        if (cuposReservados.remove(placaNormalizada) == null) {
            return "No existe una reserva para esta placa."
        }

        tiposReservados.remove(placaNormalizada)

        return "Reserva cancelada correctamente."
    }

    fun registrarSalida(placa: String): String {
        val placaNormalizada = normalizarPlaca(placa)

        val movimiento = movimientos.lastOrNull {
            it.placa == placaNormalizada && it.estado == "ACTIVO"
        } ?: return "No se encontró un vehículo activo con esa placa."

        val entrada = LocalDateTime.parse(
            movimiento.horaEntrada,
            formatoFecha
        )

        val salida = LocalDateTime.now()
        val minutos = Duration.between(entrada, salida)
            .toMinutes()
            .coerceAtLeast(1)

        // Se cobra cada hora iniciada, con mínimo de una hora.
        val horas = ceil(minutos / 60.0).toInt().coerceAtLeast(1)

        val tarifa = tarifas[movimiento.tipo]
            ?: return "No se encontró una tarifa para este vehículo."

        val total = horas * tarifa
        val indice = movimientos.indexOf(movimiento)

        movimientos[indice] = movimiento.copy(
            horaSalida = salida.format(formatoFecha),
            tiempoHoras = horas,
            total = total,
            estado = "FINALIZADO"
        )

        // El cupo queda disponible cuando sale el vehículo.
        cuposOcupados.remove(placaNormalizada)

        return "Salida registrada. Tiempo cobrado: $horas hora(s). Total: ${total.toLong()}."
    }

    fun registrarPago(placa: String): String {
        val placaNormalizada = normalizarPlaca(placa)

        val movimiento = movimientos.lastOrNull {
            it.placa == placaNormalizada &&
                    it.estado == "FINALIZADO" &&
                    it.id !in pagosRegistrados
        } ?: return "No hay un pago pendiente para esta placa."

        pagosRegistrados.add(movimiento.id)

        return "Pago registrado correctamente: ${movimiento.total?.toLong() ?: 0}."
    }

    fun obtenerCupo(placa: String): Int? =
        cuposOcupados[normalizarPlaca(placa)]
            ?: cuposReservados[normalizarPlaca(placa)]

    fun obtenerCuposDisponibles(): Int =
        capacidadTotal - cuposOcupados.size - cuposReservados.size

    fun obtenerCuposOcupados(): Int = cuposOcupados.size

    fun obtenerCuposReservados(): Int = cuposReservados.size

    fun obtenerMovimientos(): List<RegMovimiento> =
        movimientos.toList()

    fun obtenerTarifa(tipo: String): Double? =
        tarifas[tipo.trim().lowercase(Locale.ROOT)]

    fun actualizarTarifas(carro: Double, moto: Double): String {
        if (carro <= 0 || moto <= 0) {
            return "Las tarifas deben ser mayores que cero."
        }

        tarifas["carro"] = carro
        tarifas["moto"] = moto

        return "Tarifas actualizadas correctamente."
    }

    fun pagoRegistrado(idMovimiento: Int): Boolean =
        idMovimiento in pagosRegistrados

    fun obtenerEstadoCupos(): List<CupoUi> {
        val ocupados = cuposOcupados.values.toSet()
        val reservados = cuposReservados.values.toSet()

        return (1..capacidadTotal).map { numero ->
            val estado = when (numero) {
                in ocupados -> EstadoCupo.OCUPADO
                in reservados -> EstadoCupo.RESERVADO
                else -> EstadoCupo.DISPONIBLE
            }

            CupoUi(
                numero = numero,
                estado = estado
            )
        }
    }
}
