package co.edu.uan.android.uancasts.historial_vehiculos

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class HistorialStorage(context: Context) {

    private val preferences = context.getSharedPreferences(
        "parkol_historial",
        Context.MODE_PRIVATE
    )

    fun guardar(registros: List<VehiculoHistorial>) {

        val jsonArray = JSONArray()

        registros.forEach { vehiculo ->

            val objeto = JSONObject().apply {
                put("id", vehiculo.id)
                put("placa", vehiculo.placa)
                put("tipoVehiculo", vehiculo.tipoVehiculo)
                put("fechaHoraEntrada", vehiculo.fechaHoraEntrada)

                put(
                    "fechaHoraSalida",
                    vehiculo.fechaHoraSalida ?: JSONObject.NULL
                )

                put("tarifaPorHora", vehiculo.tarifaPorHora)

                put(
                    "totalCobrado",
                    vehiculo.totalCobrado ?: JSONObject.NULL
                )

                put("finalizado", vehiculo.finalizado)
            }

            jsonArray.put(objeto)
        }

        preferences.edit()
            .putString("registros", jsonArray.toString())
            .apply()
    }

    fun obtener(): List<VehiculoHistorial> {

        val contenido = preferences.getString("registros", null)
            ?: return emptyList()

        return try {

            val jsonArray = JSONArray(contenido)
            val registros = mutableListOf<VehiculoHistorial>()

            for (i in 0 until jsonArray.length()) {

                val objeto = jsonArray.getJSONObject(i)

                val salida = if (
                    objeto.isNull("fechaHoraSalida")
                ) {
                    null
                } else {
                    objeto.getLong("fechaHoraSalida")
                }

                val total = if (
                    objeto.isNull("totalCobrado")
                ) {
                    null
                } else {
                    objeto.getDouble("totalCobrado")
                }

                registros.add(
                    VehiculoHistorial(
                        id = objeto.getString("id"),
                        placa = objeto.getString("placa"),
                        tipoVehiculo = objeto.getString("tipoVehiculo"),
                        fechaHoraEntrada = objeto.getLong(
                            "fechaHoraEntrada"
                        ),
                        fechaHoraSalida = salida,
                        tarifaPorHora = objeto.getDouble(
                            "tarifaPorHora"
                        ),
                        totalCobrado = total,
                        finalizado = objeto.getBoolean("finalizado")
                    )
                )
            }

            registros

        } catch (e: Exception) {
            emptyList()
        }
    }
}
