package co.edu.uan.android.uancasts

// Modelo para el Historial de Vehículos
data class RegMovimiento(
    val id: Int,
    val placa: String,
    val tipo: String, // "carro" o "moto"
    val horaEntrada: String,
    val horaSalida: String?,
    val tiempoHoras: Int?,
    val total: Double?,
    val estado: String // "ACTIVO" o "FINALIZADO"
)

// Modelo para la Configuración de Tarifas
data class ConfigTarifa(
    val tipoVehiculo: String,
    var precioHora: Double
)