package co.edu.uan.android.uancasts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarContrasena by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoParKol)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(76.dp)
                .background(
                    color = Color(0xFF173E35),
                    shape = RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🚘", fontSize = 36.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "PARKOL",
            color = VerdeParKol,
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "Tu parqueadero, bajo control",
            color = TextoSecundarioParKol,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = TarjetaParKol,
            shape = RoundedCornerShape(22.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Bienvenido",
                    color = TextoParKol,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Acceso exclusivo para administradores",
                    color = TextoSecundarioParKol,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = {
                        correo = it
                        mensaje = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo electrónico") },
                    placeholder = { Text("Ingresa tu correo") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
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

                OutlinedTextField(
                    value = contrasena,
                    onValueChange = {
                        contrasena = it
                        mensaje = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Contraseña") },
                    placeholder = { Text("Ingresa tu contraseña") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = if (mostrarContrasena) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        TextButton(
                            onClick = {
                                mostrarContrasena = !mostrarContrasena
                            }
                        ) {
                            Text(
                                text = if (mostrarContrasena) "Ocultar" else "Ver",
                                color = VerdeParKol
                            )
                        }
                    },
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

                if (mensaje.isNotBlank()) {
                    Text(
                        text = mensaje,
                        color = Color(0xFFFF8585),
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = {
                        if (correo.isBlank() || contrasena.isBlank()) {
                            mensaje = "Completa el correo y la contraseña."
                        } else {
                            val administrador =
                                AdminAuth.iniciarSesion(correo, contrasena)

                            if (administrador != null) {
                                mensaje = ""
                                onLoginSuccess()
                            } else {
                                mensaje = "Correo o contraseña incorrectos."
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeParKol,
                        contentColor = FondoParKol
                    )
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🔒  Acceso privado para el equipo administrador",
                        color = TextoSecundarioParKol,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "ParKol · Gestión inteligente de parqueadero",
            color = TextoSecundarioParKol,
            fontSize = 11.sp
        )
    }
}