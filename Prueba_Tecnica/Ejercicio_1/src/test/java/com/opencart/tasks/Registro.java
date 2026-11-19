package com.opencart.tasks;

import com.opencart.models.DatosRegistro;
import com.opencart.ui.HomePage;
import com.opencart.ui.RegistroPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Registro {

    public static Performable abrirDesdeElMenuMyAccount() {
        return Task.where("{0} abre el formulario de registro desde el menú 'My Account'",
                Click.on(HomePage.MENU_MY_ACCOUNT),
                WaitUntil.the(HomePage.OPCION_REGISTER, isVisible()).forNoMoreThan(15).seconds(),
                Click.on(HomePage.OPCION_REGISTER),
                WaitUntil.the(RegistroPage.NOMBRE, isVisible()).forNoMoreThan(20).seconds());
    }

    public static Performable completarFormulario(DatosRegistro datos) {
        return Task.where("{0} completa el formulario de registro",
                Enter.theValue(datos.nombre()).into(RegistroPage.NOMBRE),
                Enter.theValue(datos.apellido()).into(RegistroPage.APELLIDO),
                Enter.theValue(datos.email()).into(RegistroPage.EMAIL),
                Enter.theValue(datos.telefono()).into(RegistroPage.TELEFONO),
                Enter.theValue(datos.contrasena()).into(RegistroPage.CONTRASENA),
                Enter.theValue(datos.contrasena()).into(RegistroPage.CONFIRMAR_CONTRASENA),
                Click.on(RegistroPage.NEWSLETTER_NO));
    }

    public static Performable aceptarPoliticaYContinuar() {
        return Task.where("{0} acepta la política de privacidad y continúa",
                Click.on(RegistroPage.ACEPTAR_POLITICA_DE_PRIVACIDAD),
                Click.on(RegistroPage.BOTON_CONTINUAR),
                Esperas.hastaQueLaUrlContenga("account/success"));
    }

    private Registro() {
    }
}
