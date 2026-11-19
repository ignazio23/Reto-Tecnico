package com.opencart.stepdefinitions;

import com.opencart.models.DatosGiftCertificate;
import com.opencart.tasks.GiftCertificate;
import com.opencart.ui.CarritoPage;
import com.opencart.ui.GiftCertificatePage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ExtraGiftCertificateStepDefinitions {

    @Cuando("ingresa a la sección Gift Certificates desde el pie de página")
    public void ingresaALaSeccionGiftCertificates() {
        theActorInTheSpotlight().attemptsTo(GiftCertificate.ingresarDesdeElPiePagina());
    }

    @Cuando("completa el formulario del Gift Certificate con datos ficticios")
    public void completaElFormularioDelGiftCertificate() {
        theActorInTheSpotlight().attemptsTo(
                GiftCertificate.completarFormulario(DatosGiftCertificate.deEjemplo()));
    }

    @Cuando("acepta que no es reembolsable y envía el formulario")
    public void aceptaYEnviaElFormulario() {
        theActorInTheSpotlight().attemptsTo(GiftCertificate.aceptarYEnviar());
    }

    @Entonces("ve el mensaje de Gift Certificate {string}")
    public void veElMensajeDeGiftCertificate(String mensaje) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().contains("account/voucher/success"),
                Ensure.that(GiftCertificatePage.MENSAJE_DE_EXITO).text().contains(mensaje));
    }

    @Entonces("el carrito contiene el Gift Certificate creado")
    public void elCarritoContieneElGiftCertificate() {
        DatosGiftCertificate datos = DatosGiftCertificate.deEjemplo();
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CarritoPage.FILA_GIFT_CERTIFICATE.of(datos.nombreDestinatario())).isDisplayed(),
                Ensure.that(CarritoPage.FILA_GIFT_CERTIFICATE.of(datos.nombreDestinatario()))
                        .text().contains(datos.montoEnCarrito()));
    }
}
