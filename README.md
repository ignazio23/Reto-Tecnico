Reto Técnico - SOFKA
======================================================================================

Pruebas de automatización y performance realizadas desde una sola computadora, bajo la misma red y con las herramientas
mencionadas y sugeridas en la documentación brindada.
Se realiza no solo la completitud de las pruebas sino que tambien la configuración de las herramientas necesarias, el
hallazgo de lógicas y funciones en cada etapa de prueba. 
Y la resolución de lo concluido y recopilado.

--------------------------------------------------------------------------------------

1. Que existe en cada carpeta

- Prueba_Performance: 
  Aquí se encuentran los casos de prueba para el Test de performance divididos en:
     - Caso solicitado por el ejercicio 1, con su data, el reporte resultante y el script utilizado. 
       Ademas de las conclusiones un README a modo de guía y resumen.
    
     - Caso del ejercicio 2, donde existe el mismo documento en dos formatos distintos (docx y pdf) el cual incluye la
       resolución del ejercicio como fue solicitado.

- Prueba_Tecnica: 
  Aquí se encuentran los casos de prueba para el Test Tecnico de Automatización, divididos en:
     - Caso requerido por el ejercicio 1, uso de SerenityBDD, en esta carpeta se encontraran no solo las configuraciones y componentes para el caso, sino tambien los necesarios al usar Gradle e interfaces para los reportes. De esa manera además de los casos y la solución, existe ya el ambiente completo para extraer y ejecutar el caso sin la necesidad de reconfigurar todo nuevamente. A su vez se lleva el reporte por defecto a una carpeta llamada "Reporte_Final" para un acceso mas rápido para su revisión.
    
     - Caso requerido por el ejercicio 1, uso de Karate, en esta carpeta se encontraran no solo las configuraciones y componentes para el caso, sino tambien los necesarios al usar Gradle e interfaces para los reportes. De esa manera además de los casos y la solución, existe ya el ambiente completo para extraer y ejecutar el caso sin la necesidad de reconfigurar todo nuevamente. A su vez se lleva el reporte por defecto a una carpeta llamada "Reporte_Final" para un acceso mas rápido para su revisión.

--------------------------------------------------------------------------------------

2. Requisitos Generales -

  - Java JDK 17. Se puede verificar con: java -version (debe indicar 17.x)
  - Google Chrome instalado (versión más reciente).
  - Conexión a Internet.
  - NO hace falta instalar Gradle ni Maven: se usa el wrapper incluido (gradlew.bat / gradlew).
  - K6 en version: v2.4.0
  - Memoria: se recomiendan al menos 2 GB libres (Java + Chrome).
  - Probado en: Windows 11, JDK 17, Google Chrome. 
  - Linux y macOS no fueron verificados.

--------------------------------------------------------------------------------------

3. Servicios bajo prueba

   - Servicio para prueba de Performance - Ejercicio 1: https://fakestoreapi.com/auth/login
   - Sitio para prueba Técnica Automatizada - Ejercicio 1: http://opencart.abstracta.us/
   - Servicio para prueba Técnica Automatizada - Ejercicio 2: https://petstore.swagger.io/v2 

--------------------------------------------------------------------------------------

4. Estructura del proyecto -

  TEST                                           | Raíz
    Prueba_Performance
       Ejercicio_1                               | Casos del Ejercicio - Script de Pruebas de Carga         
       Ejercicio_2                               | Casos del Ejercicio - Ejercicio de Análisis de Resultados   
    Prueba_Tecnica
       Ejercicio_1                               | Casos del Ejercicio - SerenityBDD
       Ejercicio_2                               | Casos del Ejercicio - Karate    

  Como obtenerlo:
    git clone https://github.com/ignazio23/Reto-Tecnico.git
      (o descargar el repositorio como ZIP y descomprimirlo, o usar ZIP que se dejo en mail)             

--------------------------------------------------------------------------------------

5. Avisos -

  - Existen ejecuciones que crean un pedidos reales en el sitio demo, con datos ficticios.

  - Existen criterios de aceptación que bloquearan algunas ejecuciones.

  - Existen casos EXTRA en algunos ejercicios.

  - En las pruebas técnicas existe ya una ruta a reportes para accesos rápido: Reporte_Final