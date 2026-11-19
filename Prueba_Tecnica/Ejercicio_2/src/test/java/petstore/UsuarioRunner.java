package petstore;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioRunner {

    @Test
    void ejecutarPruebasDeUsuario() {
        Results resultados = Runner.path("classpath:petstore")
                .reportDir("Reporte_Final")
                .parallel(1);

        assertEquals(0, resultados.getFailCount(), resultados.getErrorMessages());
    }
}