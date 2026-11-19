package com.opencart.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CarritoPage {

    public static final Target NOMBRES_DE_PRODUCTOS = Target
            .the("nombres de los productos del carrito")
            .locatedBy("//div[@id='content']//table[contains(@class,'table-bordered')]"
                    + "//td[@class='text-left']/a");

    public static final Target BOTON_CHECKOUT = Target
            .the("botón 'Checkout' del carrito")
            .locatedBy("//div[@id='content']//a[contains(@class,'btn-primary')][normalize-space()='Checkout']");

    public static final Target FILA_GIFT_CERTIFICATE = Target
            .the("fila del Gift Certificate para {0} en el carrito")
            .locatedBy("//div[@id='content']//table[contains(@class,'table-bordered')]"
                    + "//td[@class='text-left'][contains(normalize-space(.),'Gift Certificate for {0}')]");

    private CarritoPage() {
    }
}
