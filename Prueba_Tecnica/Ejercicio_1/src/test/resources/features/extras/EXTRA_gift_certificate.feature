# language: es
@extra @gift_certificate
Característica: EXTRA_Gift_Certificate - Creación de un Gift Certificate

  Caso adicional al Ejercicio 1. Se ejecuta solo si se activa con -Pcasos (ver readme.txt).

  Escenario: EXTRA_Creacion_de_Gift_Certificate
    Dado que el cliente abre la tienda OpenCart
    Cuando ingresa a la sección Gift Certificates desde el pie de página
    Y completa el formulario del Gift Certificate con datos ficticios
    Y acepta que no es reembolsable y envía el formulario
    Entonces ve el mensaje de Gift Certificate "Thank you for purchasing a gift certificate!"
    Cuando visualiza el carrito de compras
    Entonces el carrito contiene el Gift Certificate creado
