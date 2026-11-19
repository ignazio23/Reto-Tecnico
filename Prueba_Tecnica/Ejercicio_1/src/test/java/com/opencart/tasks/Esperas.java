package com.opencart.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Esperas {

    /** Espera a que la URL actual contenga el fragmento indicado (por ejemplo "account/success"). */
    public static Performable hastaQueLaUrlContenga(String fragmento) {
        return WaitUntil.the(ExpectedConditions.urlContains(fragmento))
                .forNoMoreThan(Duration.ofSeconds(30));
    }

    private Esperas() {
    }
}
