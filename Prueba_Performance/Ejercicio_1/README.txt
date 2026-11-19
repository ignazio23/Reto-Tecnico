Ejercicio 1 – Script de Pruebas de Carga
====================================================

1. Descripción:
Prueba de carga del endpoint de login (POST /auth/login) con K6, usando
credenciales parametrizadas desde un CSV. 
Escenario de 25 TPS durante 5 minutos.

----------------------------------------------------

2. Tecnologías y versiones:
- K6: v2.4.0
- Sistema operativo: Windows 11 Pro
- Servicio bajo prueba: https://fakestoreapi.com/auth/login
- Se requiere acceso a internet (el script importa librerías desde: jslib.k6.io, raw.githubusercontent.com)

----------------------------------------------------

3. Estructura:
- data/users.csv          |  usuarios y contraseñas (user,passwd)
- scripts/login_test.js   |  script de la prueba
- reports/                |  reportes generados (summary.txt, summary.json, resumen.html, dashboard.html)
- CONCLUSIONES.txt        |  hallazgos y conclusiones

----------------------------------------------------

4. Pasos de ejecución:
  1) Instalar K6:
       - Bajar k6-v2.4.0-windows-amd64.zip de https://github.com/grafana/k6/releases/tag/v2.4.0
       - Extraer y agregar al PATH

  2) Verificar la instalación:
       k6 version

  3) Clonar el repositorio y ubicarse en la carpeta del ejercicio:
       git clone https://github.com/ignazio23/Reto-Tecnico.git
          cd Reto-Tecnico/Prueba_Performance/Ejercicio_1

  4) Ejecutar la prueba (comandos):

     Prueba corta (30 s, 25 TPS):
       k6 run -e TPS=25 -e DURATION=30s --out "web-dashboard=export=reports/dashboard.html" scripts/login_test.js 

     Prueba demostrativa a 3 TPS (se espera fallo a modo de prueba de criterios. exit code = 99):
       k6 run -e TPS=3 -e DURATION=30s --out "web-dashboard=export=reports/dashboard.html" scripts/login_test.js   

     Prueba completa (25 TPS, 5 minutos):
       k6 run --out "web-dashboard=export=reports/dashboard.html" scripts/login_test.js                            

  5) Ver el código de salida (0 si pasó, 99 si falló un threshold):
       PowerShell: $LASTEXITCODE
       cmd: echo %ERRORLEVEL%
       Bash: echo $?   

  6) Abrir y revisar los resultados en consola y reportes.
       PowerShell o cmd: start reports\dashboard.html y start reports\resumen.html
       Bash en Windows: start reports/dashboard.html
  
  7) A considerar:
       Cada corrida pisa los 4 archivos de reports/ por eso la completa va última.
       Dashboard en vivo: mientras corre la prueba podés abrir http://localhost:5665
       Comillas: las del --out son obligatorias en PowerShell, por el &


  *Parámetros opcionales (variables de entorno):
       k6 run -e TPS=25 -e DURATION=30s -e BASE_URL=https://fakestoreapi.com scripts/login_test.js

----------------------------------------------------

5. Escenario:
- Ejecutor: constant-arrival-rate, 25 iteraciones/seg (1 petición por iteración)
- Duración: 5 minutos (~7500 peticiones)
- VUs preasignados: 50, máximo 200
- Datos: 5 usuarios del CSV, rotados de forma cíclica

----------------------------------------------------

6. Criterios de aceptación (o thresholds):
- Tiempo de respuesta: p(95) < 1500 ms
- Tasa de error: < 3 %
- Checks exitosos: > 97 %
- Throughput: >= 20 TPS

Si algún criterio falla, k6 finaliza con código de salida 99.