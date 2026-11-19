# language: es
@extra @registro
Característica: EXTRA_Registro - Registro de un usuario nuevo

  Caso adicional al Ejercicio 1. Se ejecuta solo si se activa con -Pcasos (ver readme.txt).

  Escenario: EXTRA_Registro_de_usuario
    Dado que el cliente abre la tienda OpenCart
    Cuando ingresa al formulario de registro desde el menú My Account
    Y completa el formulario de registro con datos ficticios
    Y acepta la política de privacidad y continúa
    Entonces ve que la cuenta fue creada con el título "Your Account Has Been Created!"
