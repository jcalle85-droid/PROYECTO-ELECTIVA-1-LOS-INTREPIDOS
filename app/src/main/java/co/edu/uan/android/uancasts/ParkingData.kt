package co.edu.uan.android.uancasts

object ParkingData {

    // Lista compartida por los componentes del backend.
    val listaMovimientos = mutableListOf<RegMovimiento>()

    // Lógica principal del parqueadero.
    val manager = ParkingManager(
        movimientos = listaMovimientos,
        capacidadTotal = 50
    )

    // Panel administrativo: utiliza los mismos movimientos.
    val admin = AdminPanelManager(
        listaMovimientos = listaMovimientos,
        capacidadTotalParqueadero = 50
    )

    // Historial: consulta los mismos movimientos.
    val historial = HistorialRepository(
        listaMovimientos = listaMovimientos
    )
}
