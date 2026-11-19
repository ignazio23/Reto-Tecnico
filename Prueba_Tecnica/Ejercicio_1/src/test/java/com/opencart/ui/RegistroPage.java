package com.opencart.ui;

import net.serenitybdd.screenplay.targets.Target;

public class RegistroPage {

    public static final Target NOMBRE = Target
            .the("campo First Name").locatedBy("#input-firstname");
    public static final Target APELLIDO = Target
            .the("campo Last Name").locatedBy("#input-lastname");
    public static final Target EMAIL = Target
            .the("campo E-Mail").locatedBy("#input-email");
    public static final Target TELEFONO = Target
            .the("campo Telephone").locatedBy("#input-telephone");
    public static final Target CONTRASENA = Target
            .the("campo Password").locatedBy("#input-password");
    public static final Target CONFIRMAR_CONTRASENA = Target
            .the("campo Password Confirm").locatedBy("#input-confirm");

    public static final Target NEWSLETTER_NO = Target
            .the("opción 'No' de la suscripción al newsletter")
            .locatedBy("//input[@name='newsletter'][@value='0']");

    public static final Target ACEPTAR_POLITICA_DE_PRIVACIDAD = Target
            .the("casilla 'I have read and agree to the Privacy Policy'")
            .locatedBy("//div[@id='content']//input[@name='agree']");
    public static final Target BOTON_CONTINUAR = Target
            .the("botón 'Continue' del formulario de registro")
            .locatedBy("//div[@id='content']//input[@type='submit'][@value='Continue']");

    private RegistroPage() {
    }
}
