package co.edu.uan.android.uancasts.historial_vehiculos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.LaunchedEffect

private val VerdeParKol = Color(0xFF42CDA5)
private val FondoCuadro = Color(0xFF1B353C)

@Composable
fun HistorialScreen(
    viewModel: HistorialViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.cargarHistorial()
    }
    val vehiculos = viewModel.vehiculos

    var seleccionado by remember {
        mutableStateOf<String?>(null)
    }

    val formatoFecha = remember {
        SimpleDateFormat("dd/MM/yyyy", Locale("es", "CO"))
    }

    val formatoHora = remember {
        SimpleDateFormat("hh:mm a", Locale("es", "CO"))
    }

    val formatoDinero = remember {
        NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Historial de vehículos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${vehiculos.size} vehículos registrados",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (vehiculos.isEmpty()) {

            Text(
                text = "Todavía no hay vehículos registrados.",
                modifier = Modifier.padding(vertical = 24.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        } else {

            // Organizar los vehículos en filas de tres cuadros
            vehiculos.chunked(3).forEach { fila ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    fila.forEach { vehiculo ->

                        val estaSeleccionado =
                            seleccionado == vehiculo.id

                        Card(
                            onClick = {
                                seleccionado =
                                    if (estaSeleccionado) {
                                        null
                                    } else {
                                        vehiculo.id
                                    }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            colors = CardDefaults.cardColors(
                                containerColor = FondoCuadro
                            ),
                            border = BorderStroke(
                                width = if (estaSeleccionado) 2.dp else 1.dp,
                                color = VerdeParKol
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = if (
                                        vehiculo.tipoVehiculo.equals(
                                            "Moto",
                                            ignoreCase = true
                                        )
                                    ) "🏍️" else "🚗",
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Text(
                                    text = vehiculo.placa,
                                    color = VerdeParKol,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = if (vehiculo.finalizado) {
                                        "Finalizado"
                                    } else {
                                        "Estacionado"
                                    },
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }

                    // Completar los espacios vacíos de la última fila
                    repeat(3 - fila.size) {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        )
                    }
                }
            }
        }

        // Mostrar los detalles del vehículo seleccionado
        val vehiculoSeleccionado = vehiculos.find {
            it.id == seleccionado
        }

        if (vehiculoSeleccionado != null) {

            val v = vehiculoSeleccionado

            HorizontalDivider(color = VerdeParKol)

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = "Detalles: ${v.placa}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text("Tipo de vehículo: ${v.tipoVehiculo}")

                    HorizontalDivider()

                    Text(
                        text = "ENTRADA",
                        color = VerdeParKol,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Fecha: ${
                            formatoFecha.format(
                                Date(v.fechaHoraEntrada)
                            )
                        }"
                    )

                    Text(
                        "Hora: ${
                            formatoHora.format(
                                Date(v.fechaHoraEntrada)
                            )
                        }"
                    )

                    HorizontalDivider()

                    Text(
                        text = "SALIDA",
                        color = VerdeParKol,
                        fontWeight = FontWeight.Bold
                    )

                    if (v.fechaHoraSalida != null) {

                        Text(
                            "Fecha: ${
                                formatoFecha.format(
                                    Date(v.fechaHoraSalida)
                                )
                            }"
                        )

                        Text(
                            "Hora: ${
                                formatoHora.format(
                                    Date(v.fechaHoraSalida)
                                )
                            }"
                        )

                    } else {

                        Text(
                            text = "Pendiente de registrar salida",
                            color = Color(0xFFFFC34D)
                        )
                    }

                    HorizontalDivider()

                    Text(
                        "Tarifa por hora: ${
                            formatoDinero.format(v.tarifaPorHora)
                        }"
                    )

                    if (v.fechaHoraSalida != null) {

                        val minutos =
                            (v.fechaHoraSalida - v.fechaHoraEntrada) / 60_000L

                        val horas = minutos / 60
                        val minutosRestantes = minutos % 60

                        Text(
                            "Tiempo estacionado: " +
                                    "${horas} h ${minutosRestantes} min"
                        )

                        Text(
                            text = "Total cobrado: ${
                                formatoDinero.format(
                                    v.totalCobrado ?: 0.0
                                )
                            }",
                            color = VerdeParKol,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
