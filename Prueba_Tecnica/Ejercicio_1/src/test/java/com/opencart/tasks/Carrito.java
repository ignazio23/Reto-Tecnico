package com.opencart.tasks;

import com.opencart.ui.CarritoPage;
import com.opencart.ui.CheckoutPage;
import com.opencart.ui.HomePage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Carrito {

    public static Performable agregarProducto(String producto) {
        return Task.where("{0} agrega '" + producto + "' al carrito",
                Click.on(HomePage.BOTON_AGREGAR_AL_CARRITO.of(producto)),
                WaitUntil.the(HomePage.ALERTA_PRODUCTO_AGREGADO.of(producto), isVisible())
                        .forNoMoreThan(15).seconds());
    }

    public static Performable visualizar() {
        return Task.where("{0} visualiza el carrito de compras",
                Click.on(HomePage.ENLACE_CARRITO),
                WaitUntil.the(CarritoPage.BOTON_CHECKOUT, isVisible())
                        .forNoMoreThan(15).seconds());
    }

    public static Performable iniciarCheckout() {
        return Task.where("{0} presiona 'Checkout' en el carrito",
                Click.on(CarritoPage.BOTON_CHECKOUT),
                WaitUntil.the(CheckoutPage.OPCION_INVITADO, isVisible())
                        .forNoMoreThan(20).seconds());
    }

    private Carrito() {
    }
}
