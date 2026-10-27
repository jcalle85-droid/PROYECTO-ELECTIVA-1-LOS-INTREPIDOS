# 🅿️ ParKol — Sistema de Gestión de Parqueadero

**Proyecto académico de desarrollo móvil | Equipo Los Intrépidos**

## 📌 Descripción del proyecto

**ParKol** es una aplicación móvil para Android diseñada para facilitar la administración de un parqueadero público. Su propósito es mejorar el control de los vehículos, la disponibilidad de los espacios y el registro de entradas, salidas y pagos, mediante una interfaz sencilla y organizada.

La aplicación está orientada a gestionar un parqueadero con una capacidad máxima de **50 cupos**, distribuidos por zonas para facilitar su identificación y administración.

El proyecto se desarrolla como parte de una actividad académica de la Universidad Antonio Nariño, utilizando herramientas y tecnologías propias del desarrollo de aplicaciones móviles.

## 🎯 Objetivo general

Desarrollar una aplicación móvil que permita administrar de manera organizada los cupos de un parqueadero público, controlar el ingreso y la salida de vehículos y calcular el valor del servicio según el tiempo de permanencia y el tipo de vehículo.

## ⚙️ Tecnologías utilizadas

- **Kotlin:** lenguaje de programación utilizado para desarrollar la aplicación.
- **Android Studio:** entorno de desarrollo del proyecto.
- **Jetpack Compose:** tecnología utilizada para construir las interfaces gráficas.
- **Gradle:** herramienta de configuración y compilación del proyecto.
- **Git y GitHub:** herramientas para el control de versiones y la gestión del código fuente.

## 🚘 Capacidad y organización del parqueadero

ParKol está diseñado para administrar un total de **50 cupos**, organizados en cinco zonas identificadas con las letras A, B, C, D y E.

Cada zona permite organizar visualmente los espacios para facilitar el seguimiento de su estado.

La aplicación contempla los siguientes estados:

- **Disponible:** el cupo puede asignarse a un vehículo.
- **Ocupado:** el cupo tiene un vehículo registrado.
- **Reservado:** el espacio está destinado a una reserva.

## 💰 Tarifas del servicio

La lógica actual de la aplicación contempla las siguientes tarifas de referencia:

| Tipo de vehículo | Tarifa por hora |
|---|---:|
| 🚗 Carro | $5.000 COP |
| 🏍️ Moto | $2.000 COP |

El sistema utiliza el tipo de vehículo y el tiempo de permanencia para calcular el valor del servicio. Las tarifas corresponden a los valores configurados actualmente en el código y pueden ajustarse de acuerdo con las necesidades del proyecto.

**Ejemplo de funcionamiento:** si un carro permanece tres horas, el valor de referencia es de $15.000 COP, antes de cualquier regla adicional de cobro que se implemente.

## ✅ Funcionalidades de la aplicación

ParKol contempla las siguientes funcionalidades para la administración del parqueadero:

- **Inicio de sesión:** acceso mediante credenciales de los administradores autorizados.
- **Registro de entrada:** ingreso de la placa y selección del tipo de vehículo.
- **Asignación de cupos:** identificación y asignación de un espacio disponible.
- **Consulta de disponibilidad:** visualización de los espacios libres, ocupados y reservados.
- **Reservas:** gestión de la reserva y cancelación de cupos.
- **Registro de salida:** finalización de la permanencia del vehículo.
- **Cálculo del servicio:** determinación del tiempo estacionado y del valor correspondiente.
- **Registro de pagos:** control del pago asociado a la salida del vehículo.
- **Liberación de cupos:** actualización del espacio una vez finalizado el servicio.
- **Historial de movimientos:** consulta de los registros generados durante la operación.
- **Administración:** acceso restringido a los usuarios administradores definidos para el proyecto.

## 🔐 Acceso de administradores

El acceso a la aplicación está pensado para los administradores autorizados del parqueadero. No se contempla un registro público de cuentas.

Las credenciales de prueba están definidas localmente en el código durante esta etapa de desarrollo. Por seguridad, no se publican contraseñas en este repositorio.

> **Nota de seguridad:** el mecanismo de autenticación actual es una implementación académica de prueba. Antes de utilizar la aplicación en un entorno real, deberá reemplazarse por un sistema de autenticación seguro.

## 🧩 Organización del proyecto

El código fuente se encuentra dentro del módulo `app`, organizado en archivos Kotlin para separar las responsabilidades principales de la aplicación.

Entre los archivos relevantes se encuentran:

- `UanCastsActivity.kt`: actividad principal que inicia la interfaz de la aplicación.
- `LoginScreen.kt`: interfaz de inicio de sesión.
- `AdminAuth.kt`: validación local de las credenciales de los administradores.
- `ParkingScreen.kt`: interfaz principal para la gestión del parqueadero.
- `ParkingComponents.kt`: componentes reutilizables de la interfaz.
- `ParkingViewModel.kt`: coordinación de los datos utilizados por la interfaz.
- `ParkingManager.kt`: lógica de gestión de las operaciones del parqueadero.
- `ParkingData.kt`: datos y objetos compartidos por los módulos de la aplicación.
- `Modelos.kt`: modelos utilizados para representar la información del sistema.
- `AdminPanelManager.kt`: lógica relacionada con la administración.
- `HistoryalRepository.kt`: repositorio encargado de gestionar el historial de movimientos.

Los nombres de algunos archivos conservan la denominación de la plantilla original utilizada como punto de partida para el desarrollo.

## 💾 Gestión de datos

En la versión actual, los registros de la operación se manejan en memoria durante la ejecución de la aplicación.

Esto permite desarrollar y probar la lógica principal del parqueadero, pero significa que los datos no están garantizados después de cerrar la aplicación o reiniciar el dispositivo.

La incorporación de una base de datos persistente es una mejora prevista para fortalecer el almacenamiento y la recuperación de la información.

## 📍 Contexto y alcance

ParKol se plantea como una solución académica para la gestión de un parqueadero público. Su alcance inicial se centra en el control de cupos, los movimientos de vehículos, las tarifas y los pagos.

La capacidad definida para el proyecto es de 50 vehículos. La distribución por zonas facilita la organización de los espacios sin necesidad de mostrar cada cupo como un botón independiente.

La implementación se encuentra en desarrollo y sus funcionalidades deben verificarse mediante pruebas en Android Studio y en un dispositivo o emulador Android.

## 🚀 Ejecución del proyecto

Para abrir el proyecto en un entorno de desarrollo:

1. Instalar Android Studio.
2. Clonar o descargar el repositorio de ParKol desde GitHub.
3. Abrir el proyecto desde Android Studio, seleccionando la carpeta raíz que contiene los archivos de configuración de Gradle.
4. Esperar a que finalice la sincronización de Gradle.
5. Seleccionar un dispositivo Android físico o un emulador compatible.
6. Ejecutar el módulo `app`.

Es posible que se requiera conexión a internet durante la primera sincronización para descargar las dependencias del proyecto.

## 🔮 Mejoras previstas

Entre las mejoras que pueden incorporarse en futuras versiones se encuentran:

- Implementación de una base de datos persistente.
- Fortalecimiento de la autenticación y protección de las credenciales.
- Configuración de tarifas desde un módulo administrativo.
- Ampliación de las consultas y reportes del historial.
- Pruebas funcionales de los procesos de entrada, salida, reservas y pagos.
- Refinamiento de la interfaz y de la experiencia de uso.

## 👥 Equipo de desarrollo

**Los Intrépidos**

- Jhon Stiven Calle Cabiativa
- Deby Camila Aguirre
- Yury Gabriela Alarcón Bustos

## 📚 Propósito académico

Este proyecto permite aplicar conocimientos de programación en Kotlin, desarrollo de interfaces móviles, organización del código, gestión de datos y control de versiones, mediante la construcción progresiva de una solución para una necesidad cotidiana.

---

**ParKol — Gestión de parqueadero más organizada, desde tu dispositivo Android.**