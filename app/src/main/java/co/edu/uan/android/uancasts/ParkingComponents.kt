package co.edu.uan.android.uancasts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal val FondoParKol = Color(0xFF0B1120)
internal val TarjetaParKol = Color(0xFF172235)
internal val VerdeParKol = Color(0xFF34D399)
internal val RojoParKol = Color(0xFFFB7185)
internal val AmarilloParKol = Color(0xFFFBBF24)
internal val TextoParKol = Color(0xFFF1F5F9)
internal val TextoSecundarioParKol = Color(0xFF94A3B8)

enum class EstadoCupo {
    DISPONIBLE,
    OCUPADO,
    RESERVADO
}

data class CupoUi(
    val numero: Int,
    val estado: EstadoCupo = EstadoCupo.DISPONIBLE
)

@Composable
fun ResumenCupo(
    titulo: String,
    cantidad: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = TarjetaParKol
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = titulo,
                color = TextoSecundarioParKol,
                fontSize = 12.sp
            )
            Text(
                text = cantidad.toString(),
                color = color,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LeyendaEstado() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LeyendaItem("Disponible", VerdeParKol)
        LeyendaItem("Ocupado", RojoParKol)
        LeyendaItem("Reservado", AmarilloParKol)
    }
}

@Composable
private fun LeyendaItem(
    texto: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        androidx.compose.foundation.Canvas(
            modifier = Modifier.size(9.dp)
        ) {
            drawCircle(color = color)
        }

        Text(
            text = texto,
            color = TextoSecundarioParKol,
            fontSize = 11.sp
        )
    }
}

@Composable
fun ZonaParqueadero(
    nombre: String,
    descripcion: String,
    cupos: List<CupoUi>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = TarjetaParKol
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = nombre,
                color = TextoParKol,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = descripcion,
                color = TextoSecundarioParKol,
                fontSize = 12.sp
            )

            androidx.compose.foundation.layout.FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 5
            ) {
                cupos.forEach { cupo ->
                    CuadroCupo(cupo = cupo)
                }
            }
        }
    }
}

@Composable
fun CuadroCupo(cupo: CupoUi) {
    val color = when (cupo.estado) {
        EstadoCupo.DISPONIBLE -> VerdeParKol
        EstadoCupo.OCUPADO -> RojoParKol
        EstadoCupo.RESERVADO -> AmarilloParKol
    }

    Column(
        modifier = Modifier
            .size(width = 48.dp, height = 58.dp)
            .background(
                color = color.copy(alpha = 0.12f),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = color.copy(alpha = 0.65f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = cupo.numero.toString().padStart(2, '0'),
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )

        Text(
            text = when (cupo.estado) {
                EstadoCupo.DISPONIBLE -> "Libre"
                EstadoCupo.OCUPADO -> "Ocupado"
                EstadoCupo.RESERVADO -> "Reserva"
            },
            color = color,
            fontSize = 8.sp
        )
    }
}
