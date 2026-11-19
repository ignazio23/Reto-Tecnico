Prueba Técnica - Ejercicio 1
======================================================================================

Prueba funcional automatizada (E2E): flujo de compra como invitado en OpenCart
Sitio bajo prueba: http://opencart.abstracta.us/
Herramientas: Serenity BDD 5.3.2 + Cucumber (Gherkin en español) + Screenplay + Gradle (wrapper)

--------------------------------------------------------------------------------------

1. Qué hace la prueba -

Caso solicitado por el ejercicio (se ejecuta por defecto):

  "Flujo E2E de compra con Guest Checkout"
    1. Agrega dos productos al carrito (iPhone y MacBook).
    2. Visualiza el carrito y verifica que contiene los dos productos.
    3. Completa el Checkout como invitado (Guest Checkout): datos de facturación, método de
       envío por defecto y pago contra entrega (Cash On Delivery).
    4. Confirma la orden y verifica el mensaje final: "Your order has been placed!".

Casos adicionales (opcionales, NO se ejecutan por defecto; ver sección 4):

  "EXTRA_Creacion_de_Gift_Certificate"  -> crea un Gift Certificate y lo verifica en el carrito.
  "EXTRA_Registro_de_usuario"           -> completa el formulario Register con datos ficticios.

--------------------------------------------------------------------------------------

2. Requisitos -

  - Java JDK 17. Se puede verificar con: java -version (debe indicar 17.x)
  - Google Chrome instalado (versión más reciente). El driver se descarga solo con Selenium Manager.
  - Conexión a Internet: la primera ejecución descarga Gradle 9.2.1 (~130 MB), las
    dependencias del proyecto y el driver del navegador. La prueba también accede al sitio.
  - NO hace falta instalar Gradle ni Maven: se usa el wrapper incluido (gradlew.bat / gradlew).
  - Memoria: se recomiendan al menos 2 GB libres (Java + Chrome).
  - Probado en: Windows 11, JDK 17, Google Chrome. 
  - Linux y macOS no fueron verificados.

--------------------------------------------------------------------------------------

3. Ejecución paso a paso -

  Paso 1. Obtener el proyecto:

            git clone https://github.com/ignazio23/Reto-Tecnico.git
          (o descargar el repositorio como ZIP y descomprimirlo, o usar el ZIP que se envio en respuesta por mail).

  Paso 2. Abrir PowerShell y ubicarse en la carpeta Prueba_Tecnica/Ejercicio_1, la que contiene
  
          build.gradle y gradlew.bat:
            cd Reto-Tecnico/Prueba_Tecnica/Ejercicio_1

  Paso 3. Ejecutar:

            .\gradlew.bat

          Hará esto de forma automática y en el orden siguiente:
            a) Borra los reportes y datos de ejecuciones anteriores (ver sección 6).
            b) Compila el proyecto.
            c) Ejecuta SOLO el caso del Ejercicio 1. Se abre Chrome y se ve el flujo completo
               (por defecto no es headless; ver sección 5). Dura alrededor de 30 segundos.
            d) Genera el reporte de Serenity en la carpeta Reporte_Final.

  Paso 4. Resultado esperado en la consola:

            Casos a ejecutar: ejercicio (filtro de tags: @ejercicio1)
            ...
            BUILD SUCCESSFUL

  Paso 5. Abrir el reporte con el navegador (doble clic o arrastrar al navegador):

            Reporte_Final\index.html              |    Reporte completo con pasos y capturas
            Reporte_Final\serenity-summary.html   |    Resumen en una sola página

          Abrirlo con un navegador (Chrome, Edge, Firefox). La vista previa integrada de
          algunos editores bloquea scripts y el reporte se ve sin estilos ni gráficos.

  *Nota: la primera ejecución tarda más porque descarga de Gradle, las dependencias y el driver.

--------------------------------------------------------------------------------------

4. Casos adicionales (EXTRA) -

Se activan con el parámetro -Pcasos. Sin ese parámetro solo corre el caso solicitado por el ejercicio.

  Comando                           | Qué ejecuta
  --------------------------------- | ------------------------------------------------------
  .\gradlew.bat                     | Solo el caso del Ejercicio 1 (comportamiento por defecto)
  .\gradlew.bat -Pcasos=extras      | Los dos casos EXTRA
  .\gradlew.bat -Pcasos=gift        | Solo EXTRA_Creacion_de_Gift_Certificate
  .\gradlew.bat -Pcasos=registro    | Solo EXTRA_Registro_de_usuario
  .\gradlew.bat -Pcasos=todos       | El caso del ejercicio + los dos EXTRA

La consola indica siempre lo que se va a ejecutar ("Casos a ejecutar: ...").
Un valor no válido en -Pcasos detiene la ejecución con un mensaje que lista los valores permitidos.

Detalle de los casos EXTRA:
  - EXTRA_Creacion_de_Gift_Certificate: 
      desde el pie de página entra a "Gift Certificates",
      completa el formulario (destinatario, remitente, tema, mensaje, monto), 
      acepta que no es reembolsable y lo envía. 
      Verifica el mensaje "Thank you for purchasing a gift certificate!"
      y que el Gift Certificate aparece en el carrito con su importe.
      No se completa ninguna compra: solo queda agregado al carrito de la sesión del navegador.

  - EXTRA_Registro_de_usuario: 
      desde el menú "My Account" entra a "Register", 
      completa el formulario con datos ficticios,
      acepta la política de privacidad y continúa.
      Verifica que se abre la página de cuenta creada (URL account/success y título "Your Account Has Been Created!").
      Usa un e-mail distinto en cada ejecución (incluye la hora actual),
      porque OpenCart no permite registrar dos veces el mismo e-mail.

Como funciona la selección: cada caso lleva una etiqueta de Cucumber 
(@ejercicio1, @extra, @gift_certificate, @registro) y build.gradle traduce -Pcasos a un filtro de etiquetas.

--------------------------------------------------------------------------------------

5. Configuración -

  - Modo sin ventana (headless): en src\test\resources\serenity.conf cambiar
        headless.mode = false   por   headless.mode = true

  - Datos de prueba:
        src\test\java\com\opencart\models\DatosCliente.java          |  (checkout como invitado)
        src\test\java\com\opencart\models\DatosGiftCertificate.java  |  (Gift Certificate)
        src\test\java\com\opencart\models\DatosRegistro.java         |  (Register)

  - Capturas de pantalla: se toman en cada acción                    |  (serenity.conf, take.screenshots).

--------------------------------------------------------------------------------------

6. Limpieza automática -

Cada vez que se ejecuta .\gradlew.bat se borran antes de empezar:
    Reporte_Final\   build\   target\   history\   y los archivos hs_err_pid*.log

Para conservar un reporte, copiar la carpeta Reporte_Final a otro lugar antes de volver a
ejecutar. Para que la limpieza ocurra siempre, ejecutar .\gradlew.bat sin indicar tareas
(si se indican tareas manualmente, por ejemplo "test", la limpieza no se realiza).

--------------------------------------------------------------------------------------

7. Estructura del proyecto -

  build.gradle                                   | Configuración general de gradle y serenity
  serenity.properties                            | Nombre del proyecto y carpeta del reporte
  gradlew.bat / gradlew / gradle\                | Gradle Wrapper
  Reporte_Final\                                 | Reporte generado (se recrea en cada ejecución)
  README.txt / CONCLUSIONES.txt                  | Este documento y conclusiones recopiladas
  src\test\resources\features\
      compra\compra_invitado.feature             | Caso del ejercicio 1 (@ejercicio1)
      extras\EXTRA_gift_certificate.feature      | Caso EXTRA 1 (@extra @gift_certificate)
      extras\EXTRA_registro_usuario.feature      | Caso EXTRA 2 (@extra @registro)
  src\test\resources\serenity.conf               | Navegador, headless, capturas
  src\test\java\com\opencart\
      runners\                                   | Punto de entrada de las pruebas (suite)
      stepdefinitions\                           | Pasos del feature, se traducen del Gherkin a tareas
      tasks\                                     | Acciones de negocio reutilizables
      ui\                                        | Localizadores de cada página
      models\                                    | Datos de prueba

--------------------------------------------------------------------------------------

8. Solución de problemas -

  - Errores de Java o de compilación: 
    confirmar que "java -version" indica 17.x y que JAVA_HOME apunta al JDK 17.

  - Error de memoria ("The paging file is too small", error 1455): 
    Windows se quedo sin memoria virtual. Cerrar programas pesados (navegadores) o aumentar el archivo de paginación y
    volver a ejecutar.

  - Chrome no abre o falla el driver: 
    comprobar la conexión a Internet y que Chrome este instalado y actualizado.

  - Reporte sin estilos ni gráficos: 
    confirmar que existan las carpetas css\ y scripts\ dentro de Reporte_Final y abrir index.html con un navegador real.
    build.gradle incluye la tarea copiarRecursosDelReporte que las genera.

  - Caracteres raros en la consola (por ejemplo "configuraci?n"): 
    es solo la codificación de la consola de Windows. Se corrige con "chcp 65001" y no afecta al resultado.

  - El sitio de prueba no responde o cambió sus textos: 
    la prueba depende de la disponibilidad de http://opencart.abstracta.us/ y de sus textos en inglés.

--------------------------------------------------------------------------------------

9. Avisos -

  - Cada ejecución del caso del Ejercicio 1 crea un pedido real en el sitio demo, con datos ficticios.

  - Cada ejecución de EXTRA_Registro_de_usuario crea una cuenta nueva en el sitio demo, con datos ficticios y 
    un e-mail único.

  - EXTRA_Creacion_de_Gift_Certificate no crea datos en el servidor (solo el carrito de la sesión del navegador).

  - Ya queda includo un Reporte en /Reporte_Final de una ejecución del caso requerido.