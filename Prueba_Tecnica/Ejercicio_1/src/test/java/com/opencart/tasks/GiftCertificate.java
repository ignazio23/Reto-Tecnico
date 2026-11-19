package com.opencart.tasks;

import com.opencart.models.DatosGiftCertificate;
import com.opencart.ui.GiftCertificatePage;
import com.opencart.ui.HomePage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GiftCertificate {

    public static Performable ingresarDesdeElPiePagina() {
        return Task.where("{0} ingresa a 'Gift Certificates' desde el pie de página",
                Click.on(HomePage.ENLACE_GIFT_CERTIFICATES),
                WaitUntil.the(GiftCertificatePage.NOMBRE_DESTINATARIO, isVisible())
                        .forNoMoreThan(20).seconds());
    }

    public static Performable completarFormulario(DatosGiftCertificate datos) {
        return Task.where("{0} completa el formulario del Gift Certificate",
                Enter.theValue(datos.nombreDestinatario()).into(GiftCertificatePage.NOMBRE_DESTINATARIO),
                Enter.theValue(datos.emailDestinatario()).into(GiftCertificatePage.EMAIL_DESTINATARIO),
                Enter.theValue(datos.nombreRemitente()).into(GiftCertificatePage.NOMBRE_REMITENTE),
                Enter.theValue(datos.emailRemitente()).into(GiftCertificatePage.EMAIL_REMITENTE),
                Click.on(GiftCertificatePage.TEMA.of(datos.tema())),
                Enter.theValue(datos.mensaje()).into(GiftCertificatePage.MENSAJE),
                Enter.theValue(datos.monto()).into(GiftCertificatePage.MONTO));
    }

    public static Performable aceptarYEnviar() {
        return Task.where("{0} acepta que no es reembolsable y envía el formulario",
                Click.on(GiftCertificatePage.ACEPTAR_NO_REEMBOLSABLE),
                Click.on(GiftCertificatePage.BOTON_CONTINUAR),
                Esperas.hastaQueLaUrlContenga("account/voucher/success"));
    }

    private GiftCertificate() {
    }
}
