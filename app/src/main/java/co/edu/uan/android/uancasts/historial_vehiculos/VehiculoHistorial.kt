package co.edu.uan.android.uancasts.historial_vehiculos

import java.util.UUID

data class VehiculoHistorial(
    val id: String = UUID.randomUUID().toString(),
    val placa: String,
    val tipoVehiculo: String,
    val fechaHoraEntrada: Long,
    val fechaHoraSalida: Long? = null,
    val tarifaPorHora: Double,
    val totalCobrado: Double? = null,
    val finalizado: Boolean = false
)
