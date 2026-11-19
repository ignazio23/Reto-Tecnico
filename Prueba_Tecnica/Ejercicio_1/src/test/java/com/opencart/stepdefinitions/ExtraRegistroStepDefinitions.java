package com.opencart.stepdefinitions;

import com.opencart.models.DatosRegistro;
import com.opencart.tasks.Registro;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ExtraRegistroStepDefinitions {

    @Cuando("ingresa al formulario de registro desde el menú My Account")
    public void ingresaAlFormularioDeRegistro() {
        theActorInTheSpotlight().attemptsTo(Registro.abrirDesdeElMenuMyAccount());
    }

    @Cuando("completa el formulario de registro con datos ficticios")
    public void completaElFormularioDeRegistro() {
        theActorInTheSpotlight().attemptsTo(Registro.completarFormulario(DatosRegistro.deEjemplo()));
    }

    @Cuando("acepta la política de privacidad y continúa")
    public void aceptaLaPoliticaYContinua() {
        theActorInTheSpotlight().attemptsTo(Registro.aceptarPoliticaYContinuar());
    }

    @Entonces("ve que la cuenta fue creada con el título {string}")
    public void veQueLaCuentaFueCreada(String titulo) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().contains("account/success"),
                Ensure.thatTheCurrentPage().title().isEqualTo(titulo));
    }
}
