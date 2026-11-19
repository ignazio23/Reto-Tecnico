Prueba Técnica - Ejercicio 2
======================================================================================

Pruebas de API REST: ciclo de vida de un usuario en Swagger Petstore.
Servicio bajo prueba: https://petstore.swagger.io/v2   
Documentación: https://petstore.swagger.io/
Herramientas: Karate 1.5.2 + JUnit 5 + Gradle (wrapper)

----------------------------------------------------------------------------------------

1. Qué hace la prueba -

Un único escenario encadenado ejecuta, en este orden, los 5 casos del ejercicio sobre el
recurso /user. Cada paso usa lo que definió o devolvió el anterior.

  1) Crear un usuario:
       Petición : POST /user
       Entrada  : body JSON (id, username, firstName, lastName, email, password, phone, userStatus)
       Tests    : status 200 | Content-Type JSON | response = {code:200, type:"unknown", message:<id>}

  2) Buscar el usuario creado:
       Petición : GET /user/{username}
       Entrada  : username generado en el paso 1
       Tests    : status 200 | response identica al body enviado

  3) Actualizar el nombre y el correo del usuario:
       Petición : PUT /user/{username}
       Entrada  : el mismo usuario con firstName y email nuevos
       Tests    : status 200 | response = {code:200, type:"unknown", message:<id>}

  4) Buscar el usuario actualizado:
       Petición : GET /user/{username}
       Tests    : status 200 | firstName y email = valores nuevos | distintos de los iniciales |
                  response identica al body enviado en el paso 3

  5) Eliminar el usuario:
       Petición : DELETE /user/{username}
       Tests    : status 200 | response = {code:200, type:"unknown", message:<username>}
       Verificación posterior: GET /user/{username} -> status 404 y
                  {code:1, type:"error", message:"User not found"}

Variables:
  baseUrl                         | https://petstore.swagger.io/v2 (src\test\java\karate-config.js)
  sufijo                          | Hora actual en milisegundos. Hace único cada dato en cada ejecución.
  idUsuario                       | Valor númerico de sufijo (id del usuario).
  username                        | qa_user_<sufijo>
  nombreInicial / correoInicial   | Datos con los que se crea el usuario.
  nombreNuevo / correoNuevo       | Datos con los que se actualiza el usuario.
  usuario / usuarioActualizado    | Body JSON enviado en la creación y en la actualización.

----------------------------------------------------------------------------------------

2. Requisitos -

  - Java JDK 17. Verificar con: java -version (debe indicar 17.x)
  - Conexión a Internet: la primera ejecución descarga Gradle 9.2.1 (~130 MB) y las dependencias del proyecto, y la
    prueba consume el servicio público de Petstore.
  - NO hace falta instalar Gradle, Maven ni Karate: se usa el wrapper incluido (gradlew.bat).
  - Un navegador (Chrome, Edge, Firefox) para abrir el reporte.
  - Probado en: Windows 11, JDK 17. 
  - Linux y macOS no fueron verificados.

----------------------------------------------------------------------------------------

3. Ejecución paso a paso -

  Paso 1. Obtener el proyecto:
            git clone https://github.com/ignazio23/Reto-Tecnico.git
          (o descargar el repositorio como ZIP y descomprimirlo, o usar ZIP que se dejo en mail).

  Paso 2. Abrir PowerShell y ubicarse en la carpeta Prueba_Tecnica/Ejercicio_2, la que contiene
          build.gradle y gradlew.bat:
            cd Reto-Tecnico/Prueba_Tecnica/Ejercicio_2

  Paso 3. Ejecutar:
            .\gradlew.bat

          Esto, de forma automática va a ejecutar lo siguiente en el mismo orden detallado:
            a) Borra el reporte y los datos de ejecuciones anteriores.
            b) Compila el proyecto.
            c) Ejecuta el escenario de los 5 casos contra Petstore.
            d) Genera el reporte de Karate en la carpeta Reporte_Final.

  Paso 4. Resultado esperado en la consola:
            scenarios:    1 | passed:     1 | failed: 0
            UsuarioRunner > ejecutarPruebasDeUsuario() PASSED
            BUILD SUCCESSFUL

  Paso 5. Abrir el reporte con un navegador (doble clic o arrastrar al navegador):
            Reporte_Final\karate-summary.html     | Resumen de la ejecución
            Reporte_Final\petstore.usuario.html   | Detalle paso a paso: request, response,
                                                  | headers, status y tiempos de cada peticion

  *Nota: la primera ejecución tarda más (descarga de Gradle y dependencias).

----------------------------------------------------------------------------------------

4. Estructura -

  build.gradle                      | Configuración de Gradle, Karate y limpieza
  gradlew.bat / gradlew / gradle\   | Gradle Wrapper
  Reporte_Final\                    | Reporte generado por Karate (se recrea en cada ejecución)
  README.txt / CONCLUSIONES.txt     | Esta guía y los hallazgos del ejercicio
  src\test\java\
      karate-config.js              | URL base y timeouts
      logback-test.xml              | Nivel de log de la consola
      petstore\usuario.feature      | Escenario con los 5 casos (entradas, variables y tests)
      petstore\UsuarioRunner.java   | Punto de entrada: ejecuta los .feature con JUnit 5

----------------------------------------------------------------------------------------

5. Solución de problemas -

  - Errores de Java o de compilación: confirmar que "java -version" indica 17.x y que JAVA_HOME
    apunta al JDK 17.

  - Fallos de conexión, timeouts o respuestas 5xx: Petstore es un servicio publico y compartido y puede estar lento 
    o no disponible. Verificar la conexión (y el proxy, si lo hay) y volver a ejecutar.

  - Reporte sin estilos: abrirlo con un navegador real; la vista previa integrada de algunos editores bloquea scripts.

  - Para que la limpieza ocurra siempre, ejecutar .\gradlew.bat sin indicar tareas (si se indican tareas manualmente,
    por ejemplo "test", Reporte_Final no se borra antes).

  - Caracteres raros en la consola: es solo la codificación de la consola de Windows. Se corrige con "chcp 65001" y no 
    afecta al resultado.

----------------------------------------------------------------------------------------

6. Avisos -

  - Cada ejecución crea un usuario ficticio (qa_user_<hora en ms>) y lo elimina al final.

  - Si la ejecución falla entre la creacion y la eliminacion, puede quedar ese usuario en el servicio público. 
    Es inofensivo y no afecta ejecuciones posteriores (cada una usa un username distinto).

  - Ya queda incluido un Reporte en /Reporte_Final de una ejecución del caso requerido.