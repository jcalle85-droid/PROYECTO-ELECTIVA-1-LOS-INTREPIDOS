
package co.edu.uan.android.uancasts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ParkingScreen(
    parkingViewModel: ParkingViewModel = viewModel()
) {
    val cupos = parkingViewModel.cupos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoParKol)
            .verticalScroll(rememberScrollState())
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Encabezado
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "PARKOL",
                color = VerdeParKol,
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Sistema de gestión de parqueadero",
                color = TextoSecundarioParKol,
                fontSize = 14.sp
            )

            Text(
                text = "Control de espacios en tiempo real",
                color = TextoParKol,
                fontSize = 12.sp
            )
        }

        // Resumen de cupos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ResumenCupo(
                titulo = "Disponibles",
                cantidad = parkingViewModel.disponibles,
                color = VerdeParKol,
                modifier = Modifier.weight(1f)
            )

            ResumenCupo(
                titulo = "Ocupados",
                cantidad = parkingViewModel.ocupados,
                color = RojoParKol,
                modifier = Modifier.weight(1f)
            )

            ResumenCupo(
                titulo = "Reservados",
                cantidad = parkingViewModel.reservados,
                color = AmarilloParKol,
                modifier = Modifier.weight(1f)
            )
        }

        // Formulario de gestión
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = TarjetaParKol
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Gestión de vehículos",
                    color = TextoParKol,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = parkingViewModel.placa,
                    onValueChange = parkingViewModel::cambiarPlaca,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Placa del vehículo") },
                    placeholder = {
                        Text(
                            if (parkingViewModel.tipoVehiculo == "Moto") {
                                "Ej. ABC12D"
                            } else {
                                "Ej. ABC123"
                            }
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextoParKol,
                        unfocusedTextColor = TextoParKol,
                        focusedBorderColor = VerdeParKol,
                        unfocusedBorderColor = TextoSecundarioParKol,
                        focusedLabelColor = VerdeParKol,
                        unfocusedLabelColor = TextoSecundarioParKol,
                        cursorColor = VerdeParKol
                    )
                )

                Text(
                    text = "Tipo de vehículo",
                    color = TextoSecundarioParKol,
                    fontSize = 13.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            parkingViewModel.cambiarTipo("Carro")
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (
                                parkingViewModel.tipoVehiculo == "Carro"
                            ) VerdeParKol else Color(0xFF29364A),
                            contentColor = if (
                                parkingViewModel.tipoVehiculo == "Carro"
                            ) FondoParKol else TextoParKol
                        )
                    ) {
                        Text("🚗 Carro")
                    }

                    Button(
                        onClick = {
                            parkingViewModel.cambiarTipo("Moto")
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (
                                parkingViewModel.tipoVehiculo == "Moto"
                            ) VerdeParKol else Color(0xFF29364A),
                            contentColor = if (
                                parkingViewModel.tipoVehiculo == "Moto"
                            ) FondoParKol else TextoParKol
                        )
                    ) {
                        Text("🏍️ Moto")
                    }
                }

                // Registrar entrada
                Button(
                    onClick = parkingViewModel::registrarEntrada,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeParKol,
                        contentColor = FondoParKol
                    )
                ) {
                    Text(
                        "Registrar entrada",
                        fontWeight = FontWeight.Bold
                    )
                }

                // Reservar y registrar salida
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = parkingViewModel::reservarCupo,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AmarilloParKol,
                            contentColor = FondoParKol
                        )
                    ) {
                        Text("Reservar")
                    }

                    Button(
                        onClick = parkingViewModel::registrarSalida,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RojoParKol,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Registrar salida")
                    }
                }

                // Cancelar reserva y registrar pago
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = parkingViewModel::cancelarReserva,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF64748B),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Cancelar reserva")
                    }

                    Button(
                        onClick = parkingViewModel::registrarPago,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VerdeParKol,
                            contentColor = FondoParKol
                        )
                    ) {
                        Text("Registrar pago")
                    }
                }

                // Mensaje de respuesta
                if (parkingViewModel.mensaje.isNotBlank()) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF29364A)
                    ) {
                        Text(
                            text = parkingViewModel.mensaje,
                            color = TextoParKol,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        // Mapa de los 50 cupos
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Distribución del parqueadero",
                color = TextoParKol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "50 espacios organizados en cinco zonas",
                color = TextoSecundarioParKol,
                fontSize = 13.sp
            )

            LeyendaEstado()

            Spacer(modifier = Modifier.height(2.dp))

            val zonas = listOf(
                Triple("Zona A", "Cupos 01 al 10", 1..10),
                Triple("Zona B", "Cupos 11 al 20", 11..20),
                Triple("Zona C", "Cupos 21 al 30", 21..30),
                Triple("Zona D", "Cupos 31 al 40", 31..40),
                Triple("Zona E", "Cupos 41 al 50", 41..50)
            )

            zonas.forEach { zona ->
                ZonaParqueadero(
                    nombre = zona.first,
                    descripcion = zona.second,
                    cupos = cupos.filter {
                        it.numero in zona.third
                    }
                )
            }
        }

        // Pie de página
        Text(
            text = "ParKol · Gestión de parqueadero",
            color = TextoSecundarioParKol,
            fontSize = 11.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}
