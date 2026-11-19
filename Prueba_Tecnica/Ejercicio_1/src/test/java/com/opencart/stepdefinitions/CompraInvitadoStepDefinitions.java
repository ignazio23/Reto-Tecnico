package com.opencart.stepdefinitions;

import com.opencart.models.DatosCliente;
import com.opencart.tasks.Carrito;
import com.opencart.tasks.CheckoutInvitado;
import com.opencart.tasks.Tienda;
import com.opencart.ui.CarritoPage;
import com.opencart.ui.CheckoutPage;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CompraInvitadoStepDefinitions {

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que el cliente abre la tienda OpenCart")
    public void abreLaTienda() {
        theActorCalled("Cliente").attemptsTo(Tienda.abrir());
    }

    @Cuando("agrega al carrito los productos {string} y {string}")
    public void agregaDosProductos(String primero, String segundo) {
        theActorInTheSpotlight().attemptsTo(
                Carrito.agregarProducto(primero),
                Carrito.agregarProducto(segundo));
    }

    @Cuando("visualiza el carrito de compras")
    public void visualizaElCarrito() {
        theActorInTheSpotlight().attemptsTo(Carrito.visualizar());
    }

    @Entonces("el carrito muestra los productos {string} y {string}")
    public void elCarritoMuestra(String primero, String segundo) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().contains("route=checkout/cart"),
                Ensure.that(CarritoPage.NOMBRES_DE_PRODUCTOS).textValues()
                        .containsExactlyInAnyOrder(primero, segundo));
    }

    @Cuando("completa el checkout como invitado con sus datos de facturación")
    public void completaElCheckoutComoInvitado() {
        theActorInTheSpotlight().attemptsTo(
                Carrito.iniciarCheckout(),
                CheckoutInvitado.elegirGuestCheckout(),
                CheckoutInvitado.completarDatosDeFacturacion(DatosCliente.deEjemplo()),
                CheckoutInvitado.continuarConElEnvioPorDefecto(),
                CheckoutInvitado.pagarContraEntrega());
    }

    @Cuando("finaliza la compra")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(CheckoutInvitado.confirmarOrden());
    }

    @Entonces("ve el mensaje de confirmación {string}")
    public void veElMensajeDeConfirmacion(String mensaje) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CheckoutPage.TITULO_CONFIRMACION).text().isEqualTo(mensaje));
    }
}
