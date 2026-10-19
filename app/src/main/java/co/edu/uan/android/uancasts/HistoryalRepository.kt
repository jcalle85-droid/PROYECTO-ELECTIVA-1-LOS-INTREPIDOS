package co.edu.uan.android.uancasts

class HistorialRepository(private val listaMovimientos: MutableList<RegMovimiento>) {

    // Consultas a la base de datos para filtrar el historial por placa
    fun filtrarPorPlaca(placa: String): List<RegMovimiento> {
        return listaMovimientos.filter {
            it.placa.equals(placa, ignoreCase = true)
        }
    }

    // Consultas para filtrar el historial por fecha (formato YYYY-MM-DD)
    fun filtrarPorFecha(fecha: String): List<RegMovimiento> {
        return listaMovimientos.filter {
            it.horaEntrada.startsWith(fecha)
        }
    }

    // Obtener todo el historial
    fun obtenerTodoElHistorial(): List<RegMovimiento> {
        return listaMovimientos
    }
}