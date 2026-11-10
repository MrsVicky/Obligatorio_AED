package sistema;

import sistema.ImplementacionSistema;
import sistema.Retorno;
import org.junit.Test;
import static org.junit.Assert.*;
import interfaces.ISistema;

public class Test01_InicializarSistema {

    private Retorno retorno;
    private final ISistema s = new ImplementacionSistema();

    @Test
    public void inicializarSistemaOk() {
        retorno = s.inicializarSistema();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
