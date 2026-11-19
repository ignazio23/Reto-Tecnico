package com.opencart.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    // Paso 1: Checkout Options
    public static final Target OPCION_INVITADO = Target
            .the("opción 'Guest Checkout'")
            .locatedBy("//input[@name='account' and @value='guest']");
    public static final Target BOTON_CONTINUAR_OPCIONES = Target
            .the("botón 'Continue' del paso 1")
            .locatedBy("#button-account");

    // Paso 2: Account & Billing Details
    public static final Target NOMBRE = Target.the("campo First Name").locatedBy("#input-payment-firstname");
    public static final Target APELLIDO = Target.the("campo Last Name").locatedBy("#input-payment-lastname");
    public static final Target EMAIL = Target.the("campo E-Mail").locatedBy("#input-payment-email");
    public static final Target TELEFONO = Target.the("campo Telephone").locatedBy("#input-payment-telephone");
    public static final Target DIRECCION = Target.the("campo Address 1").locatedBy("#input-payment-address-1");
    public static final Target CIUDAD = Target.the("campo City").locatedBy("#input-payment-city");
    public static final Target CODIGO_POSTAL = Target.the("campo Post Code").locatedBy("#input-payment-postcode");
    public static final Target PAIS = Target.the("lista Country").locatedBy("#input-payment-country");
    public static final Target REGION = Target.the("lista Region / State").locatedBy("#input-payment-zone");
    public static final Target OPCION_DE_REGION = Target
            .the("región {0}")
            .locatedBy("//select[@id='input-payment-zone']/option[normalize-space()='{0}']");
    public static final Target BOTON_CONTINUAR_FACTURACION = Target
            .the("botón 'Continue' del paso 2")
            .locatedBy("#button-guest");

    // Paso 4: Delivery Method
    public static final Target BOTON_CONTINUAR_ENVIO = Target
            .the("botón 'Continue' del paso 4")
            .locatedBy("#button-shipping-method");

    // Paso 5: Payment Method
    public static final Target PAGO_CONTRA_ENTREGA = Target
            .the("opción 'Cash On Delivery'")
            .locatedBy("//input[@name='payment_method' and @value='cod']");
    public static final Target ACEPTAR_TERMINOS = Target
            .the("casilla de términos y condiciones")
            .locatedBy("//input[@name='agree']");
    public static final Target BOTON_CONTINUAR_PAGO = Target
            .the("botón 'Continue' del paso 5")
            .locatedBy("#button-payment-method");

    // Paso 6: Confirm Order
    public static final Target BOTON_CONFIRMAR_ORDEN = Target
            .the("botón 'Confirm Order'")
            .locatedBy("#button-confirm");

    // Pantalla final
    public static final Target TITULO_CONFIRMACION = Target
            .the("título de confirmación de la compra")
            .locatedBy("//div[@id='content']/h1");

    private CheckoutPage() {
    }
}
