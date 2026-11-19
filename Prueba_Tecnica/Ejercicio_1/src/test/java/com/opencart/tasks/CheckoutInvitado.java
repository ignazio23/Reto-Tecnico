package com.opencart.tasks;

import com.opencart.models.DatosCliente;
import com.opencart.ui.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CheckoutInvitado {

    /** Paso 1: Checkout Options -> Guest Checkout. */
    public static Performable elegirGuestCheckout() {
        return Task.where("{0} elige 'Guest Checkout'",
                Click.on(CheckoutPage.OPCION_INVITADO),
                Click.on(CheckoutPage.BOTON_CONTINUAR_OPCIONES),
                WaitUntil.the(CheckoutPage.NOMBRE, isVisible()).forNoMoreThan(20).seconds());
    }

    /** Paso 2: Account & Billing Details. */
    public static Performable completarDatosDeFacturacion(DatosCliente datos) {
        return Task.where("{0} completa sus datos de facturación",
                Enter.theValue(datos.nombre()).into(CheckoutPage.NOMBRE),
                Enter.theValue(datos.apellido()).into(CheckoutPage.APELLIDO),
                Enter.theValue(datos.email()).into(CheckoutPage.EMAIL),
                Enter.theValue(datos.telefono()).into(CheckoutPage.TELEFONO),
                Enter.theValue(datos.direccion()).into(CheckoutPage.DIRECCION),
                Enter.theValue(datos.ciudad()).into(CheckoutPage.CIUDAD),
                Enter.theValue(datos.codigoPostal()).into(CheckoutPage.CODIGO_POSTAL),
                SelectFromOptions.byVisibleText(datos.pais()).from(CheckoutPage.PAIS),
                WaitUntil.the(CheckoutPage.OPCION_DE_REGION.of(datos.region()), isPresent())
                        .forNoMoreThan(20).seconds(),
                SelectFromOptions.byVisibleText(datos.region()).from(CheckoutPage.REGION),
                Click.on(CheckoutPage.BOTON_CONTINUAR_FACTURACION),
                WaitUntil.the(CheckoutPage.BOTON_CONTINUAR_ENVIO, isClickable())
                        .forNoMoreThan(20).seconds());
    }

    /** Paso 4: Delivery Method (se deja el método por defecto: Flat Shipping Rate). */
    public static Performable continuarConElEnvioPorDefecto() {
        return Task.where("{0} continúa con el método de envío por defecto",
                Click.on(CheckoutPage.BOTON_CONTINUAR_ENVIO),
                WaitUntil.the(CheckoutPage.PAGO_CONTRA_ENTREGA, isVisible()).forNoMoreThan(20).seconds());
    }

    /** Paso 5: Payment Method -> Cash On Delivery + términos y condiciones. */
    public static Performable pagarContraEntrega() {
        return Task.where("{0} elige pago contra entrega y acepta los términos",
                Click.on(CheckoutPage.PAGO_CONTRA_ENTREGA),
                Click.on(CheckoutPage.ACEPTAR_TERMINOS),
                Click.on(CheckoutPage.BOTON_CONTINUAR_PAGO),
                WaitUntil.the(CheckoutPage.BOTON_CONFIRMAR_ORDEN, isClickable())
                        .forNoMoreThan(20).seconds());
    }

    /** Paso 6: Confirm Order. */
    public static Performable confirmarOrden() {
        return Task.where("{0} confirma la orden",
                Click.on(CheckoutPage.BOTON_CONFIRMAR_ORDEN),
                WaitUntil.the(ExpectedConditions.urlContains("checkout/success"))
                        .forNoMoreThan(Duration.ofSeconds(30)));
    }

    private CheckoutInvitado() {
    }
}
