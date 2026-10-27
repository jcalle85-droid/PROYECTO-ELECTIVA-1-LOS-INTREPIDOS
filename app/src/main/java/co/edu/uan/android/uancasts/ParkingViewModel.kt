package co.edu.uan.android.uancasts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Locale

class ParkingViewModel : ViewModel() {

    private val manager = ParkingData.manager

    var placa by mutableStateOf("")
        private set

    var tipoVehiculo by mutableStateOf("Carro")
        private set

    var mensaje by mutableStateOf("")
        private set

    var cupos by mutableStateOf(manager.obtenerEstadoCupos())
        private set

    val disponibles: Int
        get() = cupos.count {
            it.estado == EstadoCupo.DISPONIBLE
        }

    val ocupados: Int
        get() = cupos.count {
            it.estado == EstadoCupo.OCUPADO
        }

    val reservados: Int
        get() = cupos.count {
            it.estado == EstadoCupo.RESERVADO
        }

    fun cambiarPlaca(nuevaPlaca: String) {
        val limite = 6

        placa = nuevaPlaca
            .filter { it.isLetterOrDigit() }
            .take(limite)
            .uppercase(Locale.ROOT)
    }

    fun cambiarTipo(tipo: String) {
        tipoVehiculo = tipo
        mensaje = ""
    }

    private fun actualizarVista() {
        cupos = manager.obtenerEstadoCupos()
    }

    fun registrarEntrada() {
        val resultado = manager.registrarEntrada(
            placa = placa,
            tipo = tipoVehiculo
        )

        mensaje = resultado
        actualizarVista()

        if (resultado.startsWith("Entrada registrada.")) {
            placa = ""
        }
    }

    fun reservarCupo() {
        val resultado = manager.reservarCupo(
            placa = placa,
            tipo = tipoVehiculo
        )

        mensaje = resultado
        actualizarVista()

        if (resultado.startsWith("Reserva realizada.")) {
            placa = ""
        }
    }

    fun cancelarReserva() {
        val resultado = manager.cancelarReserva(placa)

        mensaje = resultado
        actualizarVista()

        if (resultado == "Reserva cancelada correctamente.") {
            placa = ""
        }
    }

    fun registrarSalida() {
        val resultado = manager.registrarSalida(placa)

        mensaje = resultado
        actualizarVista()

        // Conservamos la placa para poder registrar el pago
        // inmediatamente después de la salida.
    }

    fun registrarPago() {
        val resultado = manager.registrarPago(placa)

        mensaje = resultado

        if (resultado.startsWith("Pago registrado correctamente:")) {
            placa = ""
        }
    }
}
