package sistema;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sistema.Modalidad;
import sistema.Retorno.Resultado;

/**
 * Conversion del juegoDePrueba1 de Main a JUnit 4.
 * Se conserva el orden original porque varias operaciones dependen
 * del estado generado por los casos anteriores.
 */
public class TestJuegoDePrueba1 {

    private Sistema s;

    @Before
    public void setUp() {
        s = new ImplementacionSistema();
    }

    @Test
    public void ejecutarJuegoDePrueba1Completo() {

        //public static void juegoDePrueba(ISistema s, Prueba p) {

        // =========================================================
        // 01 - INICIALIZAR SISTEMA
        // =========================================================

        assertEquals("01 - Se inicializo el sistema", Resultado.OK, s.inicializarSistema().getResultado());

        // =========================================================
        // 02 - REGISTRAR DESPLIEGUE
        // =========================================================

        assertEquals("Registro correcto de despliegue TEXTO", Resultado.OK, s.registrarDespliegue("DEP-0001", "LLM-070-A1B2",
                "Modelo de texto 70B", true, Modalidad.TEXTO).getResultado());

        assertEquals("Registro correcto de despliegue VISION", Resultado.OK, s.registrarDespliegue("DEP-0002", "VIS-004-C3D4",
                "Modelo de vision 4B", false, Modalidad.VISION).getResultado());

        assertEquals("Registro correcto de despliegue AUDIO", Resultado.OK, s.registrarDespliegue("DEP-0003", "AUD-002-E5F6",
                "Modelo de audio 2B", false, Modalidad.AUDIO).getResultado());

        assertEquals("Registro correcto de despliegue MULTIMODAL", Resultado.OK, s.registrarDespliegue("DEP-0004", "MUL-010-G7H8",
                "Modelo multimodal 10B", true, Modalidad.MULTIMODAL).getResultado());

        assertEquals("Registro correcto de despliegue OTROS", Resultado.OK, s.registrarDespliegue("DEP-0005", "OTR-001-Z9Y8",
                "Modelo de otros tipos", false, Modalidad.OTROS).getResultado());

        // ERROR_1: parámetros obligatorios nulos o vacíos.
        assertEquals("ERROR_1 - Id vacio", Resultado.ERROR_1, s.registrarDespliegue("", "AAA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_1 - Id null", Resultado.ERROR_1, s.registrarDespliegue(null, "AAA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_1 - Codigo vacio", Resultado.ERROR_1, s.registrarDespliegue("DEP-0010", "", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_1 - Codigo null", Resultado.ERROR_1, s.registrarDespliegue("DEP-0010", null, "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_1 - Descripcion vacia", Resultado.ERROR_1, s.registrarDespliegue("DEP-0010", "AAA-001-A1B2", "",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_1 - Descripcion null", Resultado.ERROR_1, s.registrarDespliegue("DEP-0010", "AAA-001-A1B2", null,
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_1 - Modalidad null", Resultado.ERROR_1, s.registrarDespliegue("DEP-0010", "AAA-001-A1B2", "Descripcion",
                true, null).getResultado());

        // ERROR_2: formato de código AAA-BBB-CCCC.
        assertEquals("ERROR_2 - Familia con menos de tres letras", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Familia con mas de tres letras", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAAA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Familia en minusculas", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "aaa-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Bloque numerico con menos de tres digitos", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAA-01-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Bloque numerico con mas de tres digitos", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAA-0001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Bloque numerico con letras", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAA-0A1-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Build con menos de cuatro caracteres", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAA-001-A1B", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Build con mas de cuatro caracteres", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAA-001-A1B2X", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        assertEquals("ERROR_2 - Build con caracter no alfanumerico", Resultado.ERROR_2, s.registrarDespliegue("DEP-0010", "AAA-001-A1_2", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

        // ERROR_3: id duplicado.
        assertEquals("ERROR_3 - Id duplicado", Resultado.ERROR_3, s.registrarDespliegue("DEP-0001", "LLM-071-B1B2", "Otro modelo",
                false, Modalidad.TEXTO).getResultado());

        // ERROR_4: código duplicado.
        assertEquals("ERROR_4 - Codigo duplicado", Resultado.ERROR_4, s.registrarDespliegue("DEP-0010", "LLM-070-A1B2", "Otro modelo",
                false, Modalidad.TEXTO).getResultado());

        // Prioridad de errores: se retorna el error de menor número.
        assertEquals("Prioridad - ERROR_1 antes que los demas errores", Resultado.ERROR_1, s.registrarDespliegue("DEP-0001", "codigo-invalido", "",
                true, null).getResultado());

        assertEquals("Prioridad - ERROR_2 antes que duplicidad de id", Resultado.ERROR_2, s.registrarDespliegue("DEP-0001", "codigo-invalido", "Descripcion",
                true, Modalidad.TEXTO).getResultado());

      //  s.imprimirEstadoEstructuras();

        // =========================================================
        // 03 - BUSCAR DESPLIEGUE POR ID
        // =========================================================

        assertEquals("Busqueda correcta por id", Resultado.OK, s.buscarDesplieguePorId("DEP-0001").getResultado());

        assertEquals("ERROR_1 - Id vacio", Resultado.ERROR_1, s.buscarDesplieguePorId("").getResultado());

        assertEquals("ERROR_1 - Id null", Resultado.ERROR_1, s.buscarDesplieguePorId(null).getResultado());

        assertEquals("ERROR_1 - Id con espacios", Resultado.ERROR_1, s.buscarDesplieguePorId("   ").getResultado());

        assertEquals("ERROR_2 - Id inexistente", Resultado.ERROR_2, s.buscarDesplieguePorId("DEP-9999").getResultado());

        // =========================================================
        // 04 - LISTAR DESPLIEGUES POR ID ASCENDENTE
        // =========================================================

        assertEquals("Listado de despliegues por id ascendente", Resultado.OK, s.listarDesplieguesPorIdAscendente().getResultado());

        // =========================================================
        // 05 - LISTAR DESPLIEGUES POR ID DESCENDENTE
        // =========================================================

        assertEquals("Listado de despliegues por id descendente", Resultado.OK, s.listarDesplieguesPorIdDescendente().getResultado());

        // =========================================================
        // 06 - BUSCAR DESPLIEGUE POR CODIGO
        // =========================================================

        assertEquals("Busqueda correcta por codigo", Resultado.OK, s.buscarDesplieguePorCodigo("LLM-070-A1B2").getResultado());

        assertEquals("ERROR_1 - Codigo vacio", Resultado.ERROR_1, s.buscarDesplieguePorCodigo("").getResultado());

        assertEquals("ERROR_1 - Codigo null", Resultado.ERROR_1, s.buscarDesplieguePorCodigo(null).getResultado());

        assertEquals("ERROR_1 - Codigo con espacios", Resultado.ERROR_1, s.buscarDesplieguePorCodigo("   ").getResultado());

        assertEquals("ERROR_2 - Formato de codigo incorrecto", Resultado.ERROR_2, s.buscarDesplieguePorCodigo("LLM-07-A1B2").getResultado());

        assertEquals("ERROR_3 - Codigo valido pero inexistente", Resultado.ERROR_3, s.buscarDesplieguePorCodigo("ZZZ-999-X1Y2").getResultado());

        // =========================================================
        // 07 - LISTAR DESPLIEGUES POR CODIGO ASCENDENTE
        // =========================================================

        assertEquals("Listado de despliegues por codigo ascendente", Resultado.OK, s.listarDesplieguesPorCodigoAscendente().getResultado());

        // =========================================================
        // 08 - LISTAR DESPLIEGUES POR MODALIDAD
        // =========================================================

        assertEquals("Listado de despliegues modalidad TEXTO", Resultado.OK, s.listarDesplieguesPorModalidad(Modalidad.TEXTO).getResultado());

        assertEquals("Listado de despliegues modalidad VISION", Resultado.OK, s.listarDesplieguesPorModalidad(Modalidad.VISION).getResultado());

        assertEquals("Listado de despliegues modalidad AUDIO", Resultado.OK, s.listarDesplieguesPorModalidad(Modalidad.AUDIO).getResultado());

        assertEquals("Listado de despliegues modalidad MULTIMODAL", Resultado.OK, s.listarDesplieguesPorModalidad(Modalidad.MULTIMODAL).getResultado());

        assertEquals("Listado de despliegues modalidad OTROS", Resultado.OK, s.listarDesplieguesPorModalidad(Modalidad.OTROS).getResultado());

        assertEquals("ERROR_1 - Modalidad null", Resultado.ERROR_1, s.listarDesplieguesPorModalidad(null).getResultado());

        for (Modalidad modalidad : Modalidad.values()) {

        }

        // =========================================================
        // 09 - REGISTRAR REGION
        // =========================================================

        assertEquals("Registro correcto de region us-east-1", Resultado.OK, s.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "A").getResultado());

        assertEquals("Registro correcto de region sa-east-1", Resultado.OK, s.registrarRegion("sa-east-1", "Sao Paulo", "Brasil", "B").getResultado());

        assertEquals("Registro correcto de region eu-west-1", Resultado.OK, s.registrarRegion("eu-west-1", "Irlanda", "Irlanda", "C").getResultado());

        assertEquals("Registro correcto de region ap-south-1", Resultado.OK, s.registrarRegion("ap-south-1", "Mumbai", "India", "D").getResultado());

        assertEquals("ERROR_1 - Codigo de region null", Resultado.ERROR_1, s.registrarRegion(null, "Nombre", "Pais", "Zona").getResultado());

        assertEquals("ERROR_1 - Codigo de region vacio", Resultado.ERROR_1, s.registrarRegion("", "Nombre", "Pais", "Zona").getResultado());

        assertEquals("ERROR_1 - Codigo de region con espacios", Resultado.ERROR_1, s.registrarRegion("   ", "Nombre", "Pais", "Zona").getResultado());

        assertEquals("ERROR_1 - Nombre null", Resultado.ERROR_1, s.registrarRegion("xx-1", null, "Pais", "Zona").getResultado());

        assertEquals("ERROR_1 - Nombre vacio", Resultado.ERROR_1, s.registrarRegion("xx-1", "", "Pais", "Zona").getResultado());

        assertEquals("ERROR_1 - Pais null", Resultado.ERROR_1, s.registrarRegion("xx-1", "Nombre", null, "Zona").getResultado());

        assertEquals("ERROR_1 - Pais vacio", Resultado.ERROR_1, s.registrarRegion("xx-1", "Nombre", "", "Zona").getResultado());

        assertEquals("ERROR_1 - Zona null", Resultado.ERROR_1, s.registrarRegion("xx-1", "Nombre", "Pais", null).getResultado());

        assertEquals("ERROR_1 - Zona vacia", Resultado.ERROR_1, s.registrarRegion("xx-1", "Nombre", "Pais", "").getResultado());

        assertEquals("ERROR_2 - Codigo de region duplicado", Resultado.ERROR_2, s.registrarRegion("us-east-1", "Otra region", "Otro pais", "Z").getResultado());

       // s.imprimirEstadoEstructuras();

        // =========================================================
        // 10 - OBTENER REGION
        // =========================================================

        assertEquals("Busqueda correcta de region", Resultado.OK, s.obtenerRegion("us-east-1").getResultado());

        assertEquals("ERROR_1 - Codigo vacio", Resultado.ERROR_1, s.obtenerRegion("").getResultado());

        assertEquals("ERROR_1 - Codigo null", Resultado.ERROR_1, s.obtenerRegion(null).getResultado());

        assertEquals("ERROR_1 - Codigo con espacios", Resultado.ERROR_1, s.obtenerRegion("   ").getResultado());

        assertEquals("ERROR_2 - Region inexistente", Resultado.ERROR_2, s.obtenerRegion("xx-unknown").getResultado());

        // =========================================================
        // 11 - SOLICITAR DESPLIEGUE EN REGION
        // =========================================================

        assertEquals("Solicitud de DEP-0001 en us-east-1", Resultado.OK, s.solicitarDespliegueEnRegion("DEP-0001", "us-east-1").getResultado());

        assertEquals("Solicitud de DEP-0002 en us-east-1", Resultado.OK, s.solicitarDespliegueEnRegion("DEP-0002", "us-east-1").getResultado());

        assertEquals("Solicitud de DEP-0003 en us-east-1", Resultado.OK, s.solicitarDespliegueEnRegion("DEP-0003", "us-east-1").getResultado());

        // El mismo despliegue puede estar pendiente/activo en otra región.
        assertEquals("Solicitud del mismo despliegue en otra region", Resultado.OK, s.solicitarDespliegueEnRegion("DEP-0001", "sa-east-1").getResultado());

        assertEquals("ERROR_1 - Id de despliegue null", Resultado.ERROR_1, s.solicitarDespliegueEnRegion(null, "us-east-1").getResultado());

        assertEquals("ERROR_1 - Id de despliegue vacio", Resultado.ERROR_1, s.solicitarDespliegueEnRegion("", "us-east-1").getResultado());

        assertEquals("ERROR_1 - Codigo de region null", Resultado.ERROR_1, s.solicitarDespliegueEnRegion("DEP-0001", null).getResultado());

        assertEquals("ERROR_1 - Codigo de region vacio", Resultado.ERROR_1, s.solicitarDespliegueEnRegion("DEP-0001", "").getResultado());

        assertEquals("ERROR_2 - Despliegue inexistente", Resultado.ERROR_2, s.solicitarDespliegueEnRegion("DEP-9999", "us-east-1").getResultado());

        assertEquals("ERROR_3 - Region inexistente", Resultado.ERROR_3, s.solicitarDespliegueEnRegion("DEP-0001", "xx-unknown").getResultado());

        assertEquals("ERROR_4 - Despliegue ya pendiente en esa region", Resultado.ERROR_4, s.solicitarDespliegueEnRegion("DEP-0001", "us-east-1").getResultado());

        //s.imprimirEstadoEstructuras();

        // =========================================================
        // 12 - PROCESAR SIGUIENTE DESPLIEGUE
        // =========================================================

        assertEquals("Procesar primera solicitud de us-east-1 (FIFO)", Resultado.OK, s.procesarSiguienteDespliegue("us-east-1").getResultado());

        assertEquals("ERROR_4 - Despliegue ya activo en esa region", Resultado.ERROR_4, s.solicitarDespliegueEnRegion("DEP-0001", "us-east-1").getResultado());

        assertEquals("Procesar segunda solicitud de us-east-1 (FIFO)", Resultado.OK, s.procesarSiguienteDespliegue("us-east-1").getResultado());

        assertEquals("Procesar tercera solicitud de us-east-1 (FIFO)", Resultado.OK, s.procesarSiguienteDespliegue("us-east-1").getResultado());

        assertEquals("ERROR_1 - Codigo de region vacio", Resultado.ERROR_1, s.procesarSiguienteDespliegue("").getResultado());

        assertEquals("ERROR_1 - Codigo de region null", Resultado.ERROR_1, s.procesarSiguienteDespliegue(null).getResultado());

        assertEquals("ERROR_1 - Codigo de region con espacios", Resultado.ERROR_1, s.procesarSiguienteDespliegue("   ").getResultado());

        assertEquals("ERROR_2 - Region inexistente", Resultado.ERROR_2, s.procesarSiguienteDespliegue("xx-unknown").getResultado());

        assertEquals("ERROR_3 - No hay solicitudes pendientes", Resultado.ERROR_3, s.procesarSiguienteDespliegue("us-east-1").getResultado());

        // Procesar el mismo despliegue en la segunda región.
        assertEquals("Procesar despliegue en otra region", Resultado.OK, s.procesarSiguienteDespliegue("sa-east-1").getResultado());

        //
       // s.imprimirEstadoEstructuras();

        // =========================================================
        // 13 - LISTAR DESPLIEGUES ACTIVOS DE UNA REGION
        // =========================================================

        assertEquals("Listado de despliegues activos de us-east-1", Resultado.OK, s.listarDesplieguesActivosDeRegion("us-east-1").getResultado());

        assertEquals("Listado de region sin despliegues activos", Resultado.OK, s.listarDesplieguesActivosDeRegion("eu-west-1").getResultado());

        assertEquals("ERROR_1 - Codigo de region vacio", Resultado.ERROR_1, s.listarDesplieguesActivosDeRegion("").getResultado());

        assertEquals("ERROR_1 - Codigo de region null", Resultado.ERROR_1, s.listarDesplieguesActivosDeRegion(null).getResultado());

        assertEquals("ERROR_1 - Codigo de region con espacios", Resultado.ERROR_1, s.listarDesplieguesActivosDeRegion("   ").getResultado());

        assertEquals("ERROR_2 - Region inexistente", Resultado.ERROR_2, s.listarDesplieguesActivosDeRegion("xx-unknown").getResultado());

        // =========================================================
        // 14 - RETIRAR ULTIMO DESPLIEGUE PROCESADO
        // =========================================================

        assertEquals("Retirar ultimo despliegue procesado de us-east-1 (LIFO)", Resultado.OK, s.retirarUltimoDespliegueProcesado("us-east-1").getResultado());

        // El retiro de us-east-1 no debe afectar a sa-east-1.
        assertEquals("Verificar que el despliegue sigue activo en sa-east-1", Resultado.OK, s.listarDesplieguesActivosDeRegion("sa-east-1").getResultado());

        // El despliegue retirado puede solicitarse nuevamente en esa región.
        assertEquals("Volver a solicitar el despliegue retirado", Resultado.OK, s.solicitarDespliegueEnRegion("DEP-0003", "us-east-1").getResultado());

        assertEquals("Procesar nuevamente el despliegue retirado", Resultado.OK, s.procesarSiguienteDespliegue("us-east-1").getResultado());

        assertEquals("Retirar nuevamente el despliegue procesado", Resultado.OK, s.retirarUltimoDespliegueProcesado("us-east-1").getResultado());

        assertEquals("Retirar despliegue de la segunda region", Resultado.OK, s.retirarUltimoDespliegueProcesado("sa-east-1").getResultado());

        assertEquals("ERROR_1 - Codigo de region vacio", Resultado.ERROR_1, s.retirarUltimoDespliegueProcesado("").getResultado());

        assertEquals("ERROR_1 - Codigo de region null", Resultado.ERROR_1, s.retirarUltimoDespliegueProcesado(null).getResultado());

        assertEquals("ERROR_1 - Codigo de region con espacios", Resultado.ERROR_1, s.retirarUltimoDespliegueProcesado("   ").getResultado());

        assertEquals("ERROR_2 - Region inexistente", Resultado.ERROR_2, s.retirarUltimoDespliegueProcesado("xx-unknown").getResultado());

        assertEquals("ERROR_3 - Region sin despliegues procesados activos", Resultado.ERROR_3, s.retirarUltimoDespliegueProcesado("eu-west-1").getResultado());

        // =========================================================
        // VERIFICAR REINICIALIZACION
        // =========================================================

        assertEquals("Reinicializar el sistema", Resultado.OK, s.inicializarSistema().getResultado());

        assertEquals("Verificar que se eliminaron los despliegues", Resultado.ERROR_2, s.buscarDesplieguePorId("DEP-0001").getResultado());

        assertEquals("Verificar que se eliminaron las regiones", Resultado.ERROR_2, s.obtenerRegion("us-east-1").getResultado());

        assertEquals("Listado ascendente vacio luego de reinicializar", Resultado.OK, s.listarDesplieguesPorIdAscendente().getResultado());

        assertEquals("Listado descendente vacio luego de reinicializar", Resultado.OK, s.listarDesplieguesPorIdDescendente().getResultado());

        assertEquals("Listado por codigo vacio luego de reinicializar", Resultado.OK, s.listarDesplieguesPorCodigoAscendente().getResultado());

        assertEquals("Listado por modalidad vacio luego de reinicializar", Resultado.OK, s.listarDesplieguesPorModalidad(Modalidad.TEXTO).getResultado());

    }
}
