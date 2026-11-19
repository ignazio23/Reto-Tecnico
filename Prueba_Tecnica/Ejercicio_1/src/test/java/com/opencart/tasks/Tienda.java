package com.opencart.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class Tienda {

    private static final String URL_TIENDA = "http://opencart.abstracta.us/";

    public static Performable abrir() {
        return Task.where("{0} abre la tienda OpenCart", Open.url(URL_TIENDA));
    }

    private Tienda() {
    }
}
