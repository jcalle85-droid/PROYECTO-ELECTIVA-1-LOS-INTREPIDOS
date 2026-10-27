package co.edu.uan.android.uancasts

data class Administrador(
    val nombre: String,
    val correo: String,
    val contrasena: String
)

object AdminAuth {

    private val administradores = listOf(
        Administrador(
            nombre = "Jhon Stiven Calle Cabiativa",
            correo = "jcalle85@uan.edu.co",
            contrasena = "Jhon123"
        ),
        Administrador(
            nombre = "Deby Camila Aguirre",
            correo = "daguirre47@gmail.com",
            contrasena = "Deby123"
        ),
        Administrador(
            nombre = "Yury Gabriela Alarcón Bustos",
            correo = "yualarcon58@uan.edu.co",
            contrasena = "Yury123"
        )
    )

    fun iniciarSesion(
        correo: String,
        contrasena: String
    ): Administrador? {
        return administradores.firstOrNull {
            it.correo.equals(correo.trim(), ignoreCase = true) &&
                    it.contrasena == contrasena
        }
    }
}