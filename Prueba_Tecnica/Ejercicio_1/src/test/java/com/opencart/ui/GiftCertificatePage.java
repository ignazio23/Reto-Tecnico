package com.opencart.ui;

import net.serenitybdd.screenplay.targets.Target;

public class GiftCertificatePage {

    public static final Target NOMBRE_DESTINATARIO = Target
            .the("campo Recipient's Name").locatedBy("#input-to-name");
    public static final Target EMAIL_DESTINATARIO = Target
            .the("campo Recipient's e-mail").locatedBy("#input-to-email");
    public static final Target NOMBRE_REMITENTE = Target
            .the("campo Your Name").locatedBy("#input-from-name");
    public static final Target EMAIL_REMITENTE = Target
            .the("campo Your e-mail").locatedBy("#input-from-email");

    public static final Target TEMA = Target
            .the("tema {0} del Gift Certificate")
            .locatedBy("//label[normalize-space(.)='{0}']/input[@name='voucher_theme_id']");

    public static final Target MENSAJE = Target
            .the("campo Message").locatedBy("#input-message");
    public static final Target MONTO = Target
            .the("campo Amount").locatedBy("#input-amount");

    public static final Target ACEPTAR_NO_REEMBOLSABLE = Target
            .the("casilla 'gift certificates are non-refundable'")
            .locatedBy("//div[@id='content']//input[@name='agree']");
    public static final Target BOTON_CONTINUAR = Target
            .the("botón 'Continue' del formulario de Gift Certificate")
            .locatedBy("//div[@id='content']//input[@type='submit'][@value='Continue']");

    public static final Target MENSAJE_DE_EXITO = Target
            .the("mensaje de confirmación del Gift Certificate")
            .locatedBy("//div[@id='content']/p[contains(normalize-space(.),"
                    + "'Thank you for purchasing a gift certificate!')]");

    private GiftCertificatePage() {
    }
}
