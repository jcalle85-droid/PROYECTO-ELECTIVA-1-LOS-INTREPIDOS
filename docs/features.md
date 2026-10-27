# Funcionalidades de la aplicación

**ParKol** es una aplicación móvil para Android desarrollada en Kotlin, orientada a la gestión de un parqueadero público con una capacidad de 50 cupos, organizados por zonas. Su objetivo es facilitar el control de los vehículos, la disponibilidad de espacios, las reservas y el registro de pagos desde una sola aplicación.

El acceso a las funciones de gestión se realiza mediante un inicio de sesión para el administrador. La aplicación permite llevar el control de las operaciones del parqueadero de forma organizada y consultar la información registrada durante su funcionamiento.

## Funcionalidades principales

1. **Inicio de sesión del administrador:** Permite ingresar al módulo de gestión mediante credenciales de acceso. La versión actual utiliza credenciales de demostración y no cuenta todavía con un sistema de autenticación de producción.

2. **Consultar la disponibilidad de cupos:** Permite visualizar el estado de los 50 espacios del parqueadero, identificando cuáles están disponibles, ocupados o reservados.

3. **Registrar la entrada de vehículos:** Permite ingresar la información necesaria para identificar un vehículo que ingresa al parqueadero y comenzar el registro de su permanencia.

4. **Registrar la placa:** Permite asociar cada vehículo con su placa para facilitar su identificación y seguimiento dentro del sistema.

5. **Seleccionar el tipo de vehículo:** Permite distinguir entre carros 🚗 y motocicletas 🏍️, ya que cada tipo tiene una tarifa diferente.

6. **Asignar un cupo:** Permite seleccionar un espacio disponible para ubicar el vehículo registrado, teniendo en cuenta el estado actual de los cupos.

7. **Gestionar reservas:** Permite realizar y consultar reservas de espacios, así como cancelar una reserva cuando corresponda, de acuerdo con las opciones disponibles en la aplicación.

8. **Registrar la salida de vehículos:** Permite finalizar el registro de permanencia de un vehículo y utilizar la información de entrada para calcular el tiempo transcurrido.

9. **Calcular el tiempo de permanencia:** Calcula el tiempo que el vehículo estuvo registrado en el parqueadero a partir de sus datos de entrada y salida.

10. **Calcular el valor del servicio:** Calcula el valor correspondiente según el tipo de vehículo y el tiempo de permanencia registrado. Las tarifas de referencia actuales son de $5.000 COP por hora para carros y $2.000 COP por hora para motocicletas.

11. **Registrar pagos:** Permite registrar el pago correspondiente al servicio del parqueadero y asociarlo con la operación del vehículo.

12. **Liberar cupos:** Al finalizar la permanencia del vehículo, el cupo utilizado debe quedar disponible nuevamente para permitir su asignación a otro vehículo.

13. **Consultar el historial de vehículos:** Permite revisar los registros disponibles de las operaciones anteriores, de acuerdo con la información conservada durante la ejecución de la aplicación.

## Funcionalidades del administrador

El administrador es el responsable de utilizar las funciones de gestión del parqueadero. Desde la aplicación podrá:

1. **Visualizar el estado general del parqueadero:** Consultar los cupos disponibles, ocupados y reservados, organizados por las zonas establecidas.

2. **Controlar los vehículos registrados:** Consultar la información de los vehículos que se encuentran registrados en el sistema.

3. **Gestionar entradas y salidas:** Registrar los movimientos de ingreso y salida de los vehículos.

4. **Controlar las reservas:** Consultar las reservas registradas y utilizar las opciones disponibles para gestionarlas o cancelarlas.

5. **Consultar los cobros:** Revisar el valor calculado para el servicio de parqueadero y registrar los pagos correspondientes.

6. **Revisar el historial:** Consultar los registros de vehículos y operaciones anteriores que se encuentren disponibles en la aplicación.

7. **Consultar el resumen del parqueadero:** Visualizar información general sobre la ocupación y disponibilidad de los espacios.

## Consideraciones de la versión actual

- La aplicación está desarrollada para dispositivos Android; no es una aplicación web.
- El acceso a las funciones de gestión está orientado al administrador.
- El parqueadero cuenta con una capacidad definida de 50 cupos, distribuidos por zonas.
- Las tarifas de referencia son de $5.000 COP por hora para carros y $2.000 COP por hora para motocicletas.
- Los datos de las operaciones se manejan actualmente en memoria, por lo que no se garantiza su conservación al cerrar o reiniciar la aplicación.
- La autenticación utiliza credenciales de demostración; todavía no equivale a un sistema de seguridad listo para producción.
- Las funcionalidades descritas deben distinguirse entre las que ya están disponibles en el código y las que continúan en desarrollo. La persistencia en una base de datos y la conservación permanente del historial siguen siendo aspectos pendientes de consolidación.
