
package co.edu.uan.android.uancasts

import androidx.compose.runtime.Composable
import co.edu.uan.android.uancasts.ui.theme.UANCastsTheme

@Composable
fun UANCastsApp() {
    UANCastsTheme {
        ParKolAppContent()
    }
}

@Composable
private fun ParKolAppContent() {
    // La navegación principal de ParKol se inicia desde UanCastsActivity.
    // Esta función se conserva para mantener la compatibilidad
    // si otra parte del proyecto todavía utiliza UANCastsApp().
}
