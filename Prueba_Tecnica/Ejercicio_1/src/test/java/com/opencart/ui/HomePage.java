package com.opencart.ui;

import net.serenitybdd.screenplay.targets.Target;

public class HomePage {

    public static final Target BOTON_AGREGAR_AL_CARRITO = Target
            .the("botón 'Add to Cart' de {0}")
            .locatedBy("//div[@class='product-thumb transition'][.//h4/a[normalize-space()='{0}']]"
                    + "//button[contains(@onclick,'cart.add')]");

    public static final Target ALERTA_PRODUCTO_AGREGADO = Target
            .the("alerta de éxito al agregar {0}")
            .locatedBy("//div[contains(@class,'alert-success')][contains(normalize-space(.),'{0}')]");

    public static final Target ENLACE_CARRITO = Target
            .the("enlace 'Shopping Cart' del encabezado")
            .locatedBy("//a[@title='Shopping Cart']");

    public static final Target MENU_MY_ACCOUNT = Target
            .the("menú desplegable 'My Account'")
            .locatedBy("//a[@title='My Account'][contains(@class,'dropdown-toggle')]");

    public static final Target OPCION_REGISTER = Target
            .the("opción 'Register' del menú 'My Account'")
            .locatedBy("//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='Register']");

    public static final Target ENLACE_GIFT_CERTIFICATES = Target
            .the("enlace 'Gift Certificates' del pie de página")
            .locatedBy("//footer//a[normalize-space()='Gift Certificates']");

    private HomePage() {
    }
}
