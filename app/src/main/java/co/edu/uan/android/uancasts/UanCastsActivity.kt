package co.edu.uan.android.uancasts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import co.edu.uan.android.uancasts.historial_vehiculos.HistorialScreen
import co.edu.uan.android.uancasts.historial_vehiculos.HistorialViewModel
import co.edu.uan.android.uancasts.ui.theme.UANCastsTheme
import androidx.compose.runtime.saveable.rememberSaveable

class UanCastsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            UANCastsTheme {

                var sesionIniciada by rememberSaveable {
                    mutableStateOf(false)
                }

                var mostrarHistorial by rememberSaveable {
                    mutableStateOf(false)
                }

                val historialViewModel = remember {
                    HistorialViewModel()
                }

                if (sesionIniciada) {

                    if (mostrarHistorial) {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Button(
                                onClick = {
                                    mostrarHistorial = false
                                }
                            ) {
                                Text("Volver al parqueadero")
                            }

                            HistorialScreen(
                                viewModel = historialViewModel,
                                modifier = Modifier.weight(1f)
                            )
                        }

                    } else {

                        ParkingScreen(
                            onAbrirHistorial = {
                                mostrarHistorial = true
                            },
                            onCerrarSesion = {
                                mostrarHistorial = false
                                sesionIniciada = false
                            }
                        )
                    }

                } else {

                    LoginScreen(
                        onLoginSuccess = {
                            sesionIniciada = true
                        }
                    )
                }
            }
        }
    }
}