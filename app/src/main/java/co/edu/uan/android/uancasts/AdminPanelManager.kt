package co.edu.uan.android.uancasts

class AdminPanelManager(
    private val listaMovimientos: List<RegMovimiento>,
    private val capacidadTotalParqueadero: Int
) {
    // Tarifas configurables (por defecto)
    private var tarifaCarro: Double = 5000.0
    private var tarifaMoto: Double = 2000.0

    // Interfaz/Endpoint para configuración de tarifas (modificar tarifa por hora)
    fun actualizarTarifas(nuevaTarifaCarro: Double, nuevaTarifaMoto: Double) {
        this.tarifaCarro = nuevaTarifaCarro
        this.tarifaMoto = nuevaTarifaMoto
        println("Tarifas actualizadas: Carro = $tarifaCarro, Moto = $tarifaMoto")
    }

    // Consultar ingresos del día
    fun calcularIngresosDelDia(fecha: String): Double {
        return listaMovimientos
            .filter { it.horaEntrada.startsWith(fecha) && it.estado == "FINALIZADO" }
            .sumOf { it.total ?: 0.0 }
    }

    // Porcentaje de ocupación
    fun calcularPorcentajeOcupacion(): Double {
        val activos = listaMovimientos.count { it.estado == "ACTIVO" }
        if (capacidadTotalParqueadero == 0) return 0.0
        return (activos.toDouble() / capacidadTotalParqueadero.toDouble()) * 100
    }

    // Total de carros vs motos
    fun obtenerTotalCarrosVsMotos(): Pair<Int, Int> {
        val activos = listaMovimientos.filter { it.estado == "ACTIVO" }
        val carros = activos.count { it.tipo.equals("carro", ignoreCase = true) }
        val motos = activos.count { it.tipo.equals("moto", ignoreCase = true) }
        return Pair(carros, motos)
    }
}