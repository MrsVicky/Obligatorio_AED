package sistema;

import sistema.ImplementacionSistema;
import sistema.Modalidad;
import sistema.Retorno;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import interfaces.ISistema;

public class Test02_RegistrarDespliegue {

    private Retorno retorno;
    private final ISistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
    }

    @Test
    public void registrarDespliegueOk() {
        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "Modelo de texto 70B en producción",
                true,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0002",
                "VIS-004-C3D4",
                "Modelo de visión 4B",
                false,
                Modalidad.VISION);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0003",
                "AUD-002-E5F6",
                "Modelo de audio 2B",
                false,
                Modalidad.AUDIO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarDespliegueError01() {
        retorno = s.registrarDespliegue(
                "",
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "   ",
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                null,
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "   ",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                null,
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "   ",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                null,
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                false,
                null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarDespliegueError02() {
        retorno = s.registrarDespliegue(
                "DEP-0001",
                "llm-070-A1B2",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-020-A1_2",
                "Modelo de texto 20B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-0B0-A122",
                "Modelo de texto 20B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LL-070-A1B2",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-70-A1B2",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B",
                "Modelo de texto 70B",
                false,
                Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void registrarDespliegueError03() {
        s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                true,
                Modalidad.TEXTO);

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "VIS-004-C3D4",
                "Otro modelo",
                false,
                Modalidad.VISION);

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void registrarDespliegueError04() {
        s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                true,
                Modalidad.TEXTO);

        retorno = s.registrarDespliegue(
                "DEP-0002",
                "LLM-070-A1B2",
                "Otro modelo",
                false,
                Modalidad.OTROS);

        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    public void registrarDespliegueRespetaOrdenValidaciones() {
        s.registrarDespliegue(
                "DEP-0001",
                "LLM-070-A1B2",
                "Modelo de texto 70B",
                true,
                Modalidad.TEXTO);

        retorno = s.registrarDespliegue(
                "DEP-0001",
                "codigo-invalido",
                "Otro modelo",
                false,
                Modalidad.OTROS);

        // Se cumplen ERROR_2 y ERROR_3, debe retornar el error de menor número.
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
