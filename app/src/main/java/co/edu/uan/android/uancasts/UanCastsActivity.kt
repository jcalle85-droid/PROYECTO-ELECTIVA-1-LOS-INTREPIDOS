
package co.edu.uan.android.uancasts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import co.edu.uan.android.uancasts.ui.theme.UANCastsTheme

class UanCastsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            UANCastsTheme {
                var sesionIniciada by remember {
                    mutableStateOf(false)
                }

                if (sesionIniciada) {
                    ParkingScreen()
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
