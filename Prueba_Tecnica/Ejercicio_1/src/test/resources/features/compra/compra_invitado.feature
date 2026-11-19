# language: es
@ejercicio1
Característica: Compra como invitado en OpenCart

  Como cliente sin cuenta
  Quiero comprar productos como invitado
  Para recibir mi pedido sin registrarme

  Escenario: Flujo E2E de compra con Guest Checkout
    Dado que el cliente abre la tienda OpenCart
    Cuando agrega al carrito los productos "iPhone" y "MacBook"
    Y visualiza el carrito de compras
    Entonces el carrito muestra los productos "iPhone" y "MacBook"
    Cuando completa el checkout como invitado con sus datos de facturación
    Y finaliza la compra
    Entonces ve el mensaje de confirmación "Your order has been placed!"
