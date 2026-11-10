package sistema;


import sistema.Retorno.Resultado;
import sistema.ImplementacionSistema;
import sistema.Sistema;
import test.Prueba;
import test.Retorno;



public class Main {

    public static void main(String[] args) {
        Sistema sistema = new ImplementacionSistema();
        Prueba prueba = new Prueba();

        prueba.inicializarResultadosPrueba();
        juegoDePrueba1(sistema, prueba);
        prueba.imprimirResultadosPrueba();
    }

    public static void juegoDePrueba(Sistema s, Prueba p) {

        p.tituloPrueba("INICIO DE PRUEBAS ");

        p.ver(s.inicializarSistema().getResultado(), Resultado.OK, "Se inicializo sistema");
        p.ver(s.registrarDespliegue("1", "AAA-BBB-CCCC", "Despliegue 001", true, Modalidad.AUDIO).getResultado(), Resultado.OK, "Primer desplieue");
        //Error 1
        p.ver(s.registrarDespliegue("", "AAA-BBB-CCCC", "Despliegue 001", true, Modalidad.AUDIO).getResultado(), Resultado.ERROR_1, "Error 1: Id vacio");
        p.ver(s.registrarDespliegue("1", "", "Despliegue 001", true, Modalidad.AUDIO).getResultado(), Resultado.ERROR_1, "Error 1: Codigo vacio");
        p.ver(s.registrarDespliegue("1", "AAA-BBB-CCCC", "", true, Modalidad.AUDIO).getResultado(), Resultado.ERROR_1, "Error 1: Se intenta regist5rar desplieue sin descripcion");
        p.ver(s.registrarDespliegue("1", "AAA-BBB-CCCC", "Despliegue 001", true, null).getResultado(), Resultado.ERROR_1, "Error 1: Modalidad null");
        //Error 2
        p.ver(s.registrarDespliegue("1", "AA-B-CC", "Despliegue 001", true, Modalidad.AUDIO).getResultado(), Resultado.ERROR_2, "Error 2: formato de codigo invalido");

        //Error 3
        p.ver(s.registrarDespliegue("1", "AAA-BBB-CCCC", "Despliegue 001", true, Modalidad.AUDIO).getResultado(), Resultado.ERROR_3, "Error 3: Se intenta registrar un depliegue con id existente");
        //Error 4
        p.ver(s.registrarDespliegue("2", "AAA-BBB-CCCC", "Despliegue 001", true, Modalidad.AUDIO).getResultado(), Resultado.ERROR_4, "Error 4: Se intenta registrar un depliegue con codigo existente");

        //  buscar despliegue
        p.ver(s.buscarDesplieguePorId("1").getResultado(), Resultado.OK, "se encuenta el despliegue buscado");
        p.ver(s.buscarDesplieguePorId("").getResultado(), Resultado.ERROR_1, "Error 1 id vacio o null");
        p.ver(s.buscarDesplieguePorId("2").getResultado(), Resultado.ERROR_2, "Error 2: no existe un despliegue registrado con ese id.");

        //listar despliegue por id asc
        p.ver(s.listarDesplieguesPorIdAscendente().getResultado(), Resultado.OK, "Listado por id ascendente");

        //listar despliegue por id desc
        p.ver(s.listarDesplieguesPorIdDescendente().getResultado(), Resultado.OK, "Listado por id descendente");

        // buscar despliegue por codigo
        p.ver(s.buscarDesplieguePorCodigo("AAA-BBB-CCCC").getResultado(), Resultado.OK, "OK se encuentra despliegue AAA-BBB-CCCC");
        p.ver(s.buscarDesplieguePorCodigo("").getResultado(), Resultado.ERROR_1, "ERROR 1 despliegue CON CODIGO VACIO");
        p.ver(s.buscarDesplieguePorCodigo("AAA-BBB-CC").getResultado(), Resultado.ERROR_2, "ERROR 2 despliegue CON CODIGO FORMATO INCORRECTO");
        p.ver(s.buscarDesplieguePorCodigo("AAA-BBB-XXXX").getResultado(), Resultado.ERROR_3, "se encuentra despliegue AAA-BBB-XXXX");

        //listar despliegues por codigo ascendente
        p.ver(s.listarDesplieguesPorCodigoAscendente().getResultado(), Resultado.OK, "Listado de despliegues por codigo ascendente");

        // listar despliegues por modalidad
        p.ver(s.listarDesplieguesPorModalidad(Modalidad.MULTIMODAL.AUDIO).getResultado(), Resultado.OK, "Despliegues por modalidad Audio");
        p.ver(s.listarDesplieguesPorModalidad(Modalidad.MULTIMODAL.MULTIMODAL).getResultado(), Resultado.OK, "Despliegues por modalidad Multimodal");
        p.ver(s.listarDesplieguesPorModalidad(Modalidad.MULTIMODAL.OTROS).getResultado(), Resultado.OK, "Despliegues por modalidad otros");

        p.ver(s.listarDesplieguesPorModalidad(null).getResultado(), Resultado.ERROR_1, "Error 1 modalidad en null");

        //registrar region
        p.ver(s.registrarRegion("1", "Montevideo", "Uruguay", "Zona 1").getResultado(), Resultado.OK, "Se registra region 1");
        p.ver(s.registrarRegion("1", "Montevideo", "", "Zona 1").getResultado(), Resultado.ERROR_1, "Se registra region 1 con pais en null");
        p.ver(s.registrarRegion("1", "Montevideo", "Uruguay", "Zona 1").getResultado(), Resultado.ERROR_2, "Se intenta registrar region 1 que ya existe");

        //obtener Region
        p.ver(s.obtenerRegion("1").getResultado(), Resultado.OK, "OK Reguion encontrada");
        p.ver(s.obtenerRegion("").getResultado(), Resultado.ERROR_1, "Error 1 Reguion codigo vacio");
        p.ver(s.obtenerRegion("2").getResultado(), Resultado.ERROR_2, "Error 2 Reguion no encontrada");

        // Solicitar despliegue en región
        p.ver(s.solicitarDespliegueEnRegion("1", "1").getResultado(), Resultado.OK, "OK se hace una solicitud para el despliegue 1 que existe");
        p.ver(s.solicitarDespliegueEnRegion("1", "").getResultado(), Resultado.ERROR_1, "Error1 se hace una solicitud para el despliegue con codigo null");
        p.ver(s.solicitarDespliegueEnRegion("", "1").getResultado(), Resultado.ERROR_1, "Error1 se hace una solicitud para el despliegue con id null");
        p.ver(s.solicitarDespliegueEnRegion("100", "1").getResultado(), Resultado.ERROR_2, "Error 2 se hace una solicitud para el despliegue 1 que no existe");

    }

    public static void juegoDePrueba1(Sistema s, Prueba p) {
        //public static void juegoDePrueba(ISistema s, Prueba p) {

        p.tituloPrueba("INICIO DE PRUEBAS - OBLIGATORIO AGOSTO 2026");

        // =========================================================
        // 01 - INICIALIZAR SISTEMA
        // =========================================================
        p.ver(s.inicializarSistema().getResultado(), Resultado.OK,
                "01 - Se inicializo el sistema");

        // =========================================================
        // 02 - REGISTRAR DESPLIEGUE
        // =========================================================
        p.tituloPrueba("02 - REGISTRAR DESPLIEGUE");

        p.ver(s.registrarDespliegue("DEP-0001", "LLM-070-A1B2",
                "Modelo de texto 70B", true, Modalidad.TEXTO).getResultado(),
                Resultado.OK, "Registro correcto de despliegue TEXTO");

        p.ver(s.registrarDespliegue("DEP-0002", "VIS-004-C3D4",
                "Modelo de vision 4B", false, Modalidad.VISION).getResultado(),
                Resultado.OK, "Registro correcto de despliegue VISION");

        p.ver(s.registrarDespliegue("DEP-0003", "AUD-002-E5F6",
                "Modelo de audio 2B", false, Modalidad.AUDIO).getResultado(),
                Resultado.OK, "Registro correcto de despliegue AUDIO");

        p.ver(s.registrarDespliegue("DEP-0004", "MUL-010-G7H8",
                "Modelo multimodal 10B", true, Modalidad.MULTIMODAL).getResultado(),
                Resultado.OK, "Registro correcto de despliegue MULTIMODAL");

        p.ver(s.registrarDespliegue("DEP-0005", "OTR-001-Z9Y8",
                "Modelo de otros tipos", false, Modalidad.OTROS).getResultado(),
                Resultado.OK, "Registro correcto de despliegue OTROS");

        // ERROR_1: parámetros obligatorios nulos o vacíos.
        p.ver(s.registrarDespliegue("", "AAA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id vacio");

        p.ver(s.registrarDespliegue(null, "AAA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id null");

        p.ver(s.registrarDespliegue("DEP-0010", "", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo vacio");

        p.ver(s.registrarDespliegue("DEP-0010", null, "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo null");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-001-A1B2", "",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Descripcion vacia");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-001-A1B2", null,
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Descripcion null");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-001-A1B2", "Descripcion",
                true, null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Modalidad null");

        // ERROR_2: formato de código AAA-BBB-CCCC.
        p.ver(s.registrarDespliegue("DEP-0010", "AA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Familia con menos de tres letras");

        p.ver(s.registrarDespliegue("DEP-0010", "AAAA-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Familia con mas de tres letras");

        p.ver(s.registrarDespliegue("DEP-0010", "aaa-001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Familia en minusculas");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-01-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Bloque numerico con menos de tres digitos");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-0001-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Bloque numerico con mas de tres digitos");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-0A1-A1B2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Bloque numerico con letras");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-001-A1B", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Build con menos de cuatro caracteres");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-001-A1B2X", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Build con mas de cuatro caracteres");

        p.ver(s.registrarDespliegue("DEP-0010", "AAA-001-A1_2", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Build con caracter no alfanumerico");

        // ERROR_3: id duplicado.
        p.ver(s.registrarDespliegue("DEP-0001", "LLM-071-B1B2", "Otro modelo",
                false, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_3, "ERROR_3 - Id duplicado");

        // ERROR_4: código duplicado.
        p.ver(s.registrarDespliegue("DEP-0010", "LLM-070-A1B2", "Otro modelo",
                false, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_4, "ERROR_4 - Codigo duplicado");

        // Prioridad de errores: se retorna el error de menor número.
        p.ver(s.registrarDespliegue("DEP-0001", "codigo-invalido", "",
                true, null).getResultado(),
                Resultado.ERROR_1, "Prioridad - ERROR_1 antes que los demas errores");

        p.ver(s.registrarDespliegue("DEP-0001", "codigo-invalido", "Descripcion",
                true, Modalidad.TEXTO).getResultado(),
                Resultado.ERROR_2, "Prioridad - ERROR_2 antes que duplicidad de id");

        // =========================================================
        // 03 - BUSCAR DESPLIEGUE POR ID
        // =========================================================
        p.tituloPrueba("03 - BUSCAR DESPLIEGUE POR ID");

        p.ver(s.buscarDesplieguePorId("DEP-0001").getResultado(),
                Resultado.OK, "Busqueda correcta por id");

        p.ver(s.buscarDesplieguePorId("").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id vacio");

        p.ver(s.buscarDesplieguePorId(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id null");

        p.ver(s.buscarDesplieguePorId("   ").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id con espacios");

        p.ver(s.buscarDesplieguePorId("DEP-9999").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Id inexistente");

        // =========================================================
        // 04 - LISTAR DESPLIEGUES POR ID ASCENDENTE
        // =========================================================
        p.tituloPrueba("04 - LISTAR DESPLIEGUES POR ID ASCENDENTE");

        p.ver(s.listarDesplieguesPorIdAscendente().getResultado(),
                Resultado.OK, "Listado de despliegues por id ascendente");

        // =========================================================
        // 05 - LISTAR DESPLIEGUES POR ID DESCENDENTE
        // =========================================================
        p.tituloPrueba("05 - LISTAR DESPLIEGUES POR ID DESCENDENTE");

        p.ver(s.listarDesplieguesPorIdDescendente().getResultado(),
                Resultado.OK, "Listado de despliegues por id descendente");

        // =========================================================
        // 06 - BUSCAR DESPLIEGUE POR CODIGO
        // =========================================================
        p.tituloPrueba("06 - BUSCAR DESPLIEGUE POR CODIGO");

        p.ver(s.buscarDesplieguePorCodigo("LLM-070-A1B2").getResultado(),
                Resultado.OK, "Busqueda correcta por codigo");

        p.ver(s.buscarDesplieguePorCodigo("").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo vacio");

        p.ver(s.buscarDesplieguePorCodigo(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo null");

        p.ver(s.buscarDesplieguePorCodigo("   ").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo con espacios");

        p.ver(s.buscarDesplieguePorCodigo("LLM-07-A1B2").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Formato de codigo incorrecto");

        p.ver(s.buscarDesplieguePorCodigo("ZZZ-999-X1Y2").getResultado(),
                Resultado.ERROR_3, "ERROR_3 - Codigo valido pero inexistente");

        // =========================================================
        // 07 - LISTAR DESPLIEGUES POR CODIGO ASCENDENTE
        // =========================================================
        p.tituloPrueba("07 - LISTAR DESPLIEGUES POR CODIGO ASCENDENTE");

        p.ver(s.listarDesplieguesPorCodigoAscendente().getResultado(),
                Resultado.OK, "Listado de despliegues por codigo ascendente");

        // =========================================================
        // 08 - LISTAR DESPLIEGUES POR MODALIDAD
        // =========================================================
        p.tituloPrueba("08 - LISTAR DESPLIEGUES POR MODALIDAD");

        p.ver(s.listarDesplieguesPorModalidad(Modalidad.TEXTO).getResultado(),
                Resultado.OK, "Listado de despliegues modalidad TEXTO");

        p.ver(s.listarDesplieguesPorModalidad(Modalidad.VISION).getResultado(),
                Resultado.OK, "Listado de despliegues modalidad VISION");

        p.ver(s.listarDesplieguesPorModalidad(Modalidad.AUDIO).getResultado(),
                Resultado.OK, "Listado de despliegues modalidad AUDIO");

        p.ver(s.listarDesplieguesPorModalidad(Modalidad.MULTIMODAL).getResultado(),
                Resultado.OK, "Listado de despliegues modalidad MULTIMODAL");

        p.ver(s.listarDesplieguesPorModalidad(Modalidad.OTROS).getResultado(),
                Resultado.OK, "Listado de despliegues modalidad OTROS");

        p.ver(s.listarDesplieguesPorModalidad(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Modalidad null");

        // =========================================================
        // 09 - REGISTRAR REGION
        // =========================================================
        p.tituloPrueba("09 - REGISTRAR REGION");

        p.ver(s.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "A").getResultado(),
                Resultado.OK, "Registro correcto de region us-east-1");

        p.ver(s.registrarRegion("sa-east-1", "Sao Paulo", "Brasil", "B").getResultado(),
                Resultado.OK, "Registro correcto de region sa-east-1");

        p.ver(s.registrarRegion("eu-west-1", "Irlanda", "Irlanda", "C").getResultado(),
                Resultado.OK, "Registro correcto de region eu-west-1");

        p.ver(s.registrarRegion("ap-south-1", "Mumbai", "India", "D").getResultado(),
                Resultado.OK, "Registro correcto de region ap-south-1");

        p.ver(s.registrarRegion(null, "Nombre", "Pais", "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region null");

        p.ver(s.registrarRegion("", "Nombre", "Pais", "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region vacio");

        p.ver(s.registrarRegion("   ", "Nombre", "Pais", "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region con espacios");

        p.ver(s.registrarRegion("xx-1", null, "Pais", "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Nombre null");

        p.ver(s.registrarRegion("xx-1", "", "Pais", "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Nombre vacio");

        p.ver(s.registrarRegion("xx-1", "Nombre", null, "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Pais null");

        p.ver(s.registrarRegion("xx-1", "Nombre", "", "Zona").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Pais vacio");

        p.ver(s.registrarRegion("xx-1", "Nombre", "Pais", null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Zona null");

        p.ver(s.registrarRegion("xx-1", "Nombre", "Pais", "").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Zona vacia");

        p.ver(s.registrarRegion("us-east-1", "Otra region", "Otro pais", "Z").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Codigo de region duplicado");

        // =========================================================
        // 10 - OBTENER REGION
        // =========================================================
        p.tituloPrueba("10 - OBTENER REGION");

        p.ver(s.obtenerRegion("us-east-1").getResultado(),
                Resultado.OK, "Busqueda correcta de region");

        p.ver(s.obtenerRegion("").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo vacio");

        p.ver(s.obtenerRegion(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo null");

        p.ver(s.obtenerRegion("   ").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo con espacios");

        p.ver(s.obtenerRegion("xx-unknown").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Region inexistente");

        // =========================================================
        // 11 - SOLICITAR DESPLIEGUE EN REGION
        // =========================================================
        p.tituloPrueba("11 - SOLICITAR DESPLIEGUE EN REGION");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", "us-east-1").getResultado(),
                Resultado.OK, "Solicitud de DEP-0001 en us-east-1");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0002", "us-east-1").getResultado(),
                Resultado.OK, "Solicitud de DEP-0002 en us-east-1");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0003", "us-east-1").getResultado(),
                Resultado.OK, "Solicitud de DEP-0003 en us-east-1");

        // El mismo despliegue puede estar pendiente/activo en otra región.
        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", "sa-east-1").getResultado(),
                Resultado.OK, "Solicitud del mismo despliegue en otra region");

        p.ver(s.solicitarDespliegueEnRegion(null, "us-east-1").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id de despliegue null");

        p.ver(s.solicitarDespliegueEnRegion("", "us-east-1").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Id de despliegue vacio");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region null");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", "").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region vacio");

        p.ver(s.solicitarDespliegueEnRegion("DEP-9999", "us-east-1").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Despliegue inexistente");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", "xx-unknown").getResultado(),
                Resultado.ERROR_3, "ERROR_3 - Region inexistente");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", "us-east-1").getResultado(),
                Resultado.ERROR_4, "ERROR_4 - Despliegue ya pendiente en esa region");

        // =========================================================
        // 12 - PROCESAR SIGUIENTE DESPLIEGUE
        // =========================================================
        p.tituloPrueba("12 - PROCESAR SIGUIENTE DESPLIEGUE");

        p.ver(s.procesarSiguienteDespliegue("us-east-1").getResultado(),
                Resultado.OK, "Procesar primera solicitud de us-east-1 (FIFO)");

        p.ver(s.solicitarDespliegueEnRegion("DEP-0001", "us-east-1").getResultado(),
                Resultado.ERROR_4, "ERROR_4 - Despliegue ya activo en esa region");

        p.ver(s.procesarSiguienteDespliegue("us-east-1").getResultado(),
                Resultado.OK, "Procesar segunda solicitud de us-east-1 (FIFO)");

        p.ver(s.procesarSiguienteDespliegue("us-east-1").getResultado(),
                Resultado.OK, "Procesar tercera solicitud de us-east-1 (FIFO)");

        p.ver(s.procesarSiguienteDespliegue("").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region vacio");

        p.ver(s.procesarSiguienteDespliegue(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region null");

        p.ver(s.procesarSiguienteDespliegue("   ").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region con espacios");

        p.ver(s.procesarSiguienteDespliegue("xx-unknown").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Region inexistente");

        p.ver(s.procesarSiguienteDespliegue("us-east-1").getResultado(),
                Resultado.ERROR_3, "ERROR_3 - No hay solicitudes pendientes");

        // Procesar el mismo despliegue en la segunda región.
        p.ver(s.procesarSiguienteDespliegue("sa-east-1").getResultado(),
                Resultado.OK, "Procesar despliegue en otra region");

        // =========================================================
        // 13 - LISTAR DESPLIEGUES ACTIVOS DE UNA REGION
        // =========================================================
        p.tituloPrueba("13 - LISTAR DESPLIEGUES ACTIVOS DE UNA REGION");

        p.ver(s.listarDesplieguesActivosDeRegion("us-east-1").getResultado(),
                Resultado.OK, "Listado de despliegues activos de us-east-1");

        p.ver(s.listarDesplieguesActivosDeRegion("eu-west-1").getResultado(),
                Resultado.OK, "Listado de region sin despliegues activos");

        p.ver(s.listarDesplieguesActivosDeRegion("").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region vacio");

        p.ver(s.listarDesplieguesActivosDeRegion(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region null");

        p.ver(s.listarDesplieguesActivosDeRegion("   ").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region con espacios");

        p.ver(s.listarDesplieguesActivosDeRegion("xx-unknown").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Region inexistente");

        // =========================================================
        // 14 - RETIRAR ULTIMO DESPLIEGUE PROCESADO
        // =========================================================
        p.tituloPrueba("14 - RETIRAR ULTIMO DESPLIEGUE PROCESADO");

        p.ver(s.retirarUltimoDespliegueProcesado("us-east-1").getResultado(),
                Resultado.OK, "Retirar ultimo despliegue procesado de us-east-1 (LIFO)");

        // El retiro de us-east-1 no debe afectar a sa-east-1.
        p.ver(s.listarDesplieguesActivosDeRegion("sa-east-1").getResultado(),
                Resultado.OK, "Verificar que el despliegue sigue activo en sa-east-1");

        // El despliegue retirado puede solicitarse nuevamente en esa región.
        p.ver(s.solicitarDespliegueEnRegion("DEP-0003", "us-east-1").getResultado(),
                Resultado.OK, "Volver a solicitar el despliegue retirado");

        p.ver(s.procesarSiguienteDespliegue("us-east-1").getResultado(),
                Resultado.OK, "Procesar nuevamente el despliegue retirado");

        p.ver(s.retirarUltimoDespliegueProcesado("us-east-1").getResultado(),
                Resultado.OK, "Retirar nuevamente el despliegue procesado");

        p.ver(s.retirarUltimoDespliegueProcesado("sa-east-1").getResultado(),
                Resultado.OK, "Retirar despliegue de la segunda region");

        p.ver(s.retirarUltimoDespliegueProcesado("").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region vacio");

        p.ver(s.retirarUltimoDespliegueProcesado(null).getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region null");

        p.ver(s.retirarUltimoDespliegueProcesado("   ").getResultado(),
                Resultado.ERROR_1, "ERROR_1 - Codigo de region con espacios");

        p.ver(s.retirarUltimoDespliegueProcesado("xx-unknown").getResultado(),
                Resultado.ERROR_2, "ERROR_2 - Region inexistente");

        p.ver(s.retirarUltimoDespliegueProcesado("eu-west-1").getResultado(),
                Resultado.ERROR_3, "ERROR_3 - Region sin despliegues procesados activos");

        // =========================================================
        // VERIFICAR REINICIALIZACION
        // =========================================================
        p.tituloPrueba("VERIFICAR REINICIALIZACION DEL SISTEMA");

        p.ver(s.inicializarSistema().getResultado(),
                Resultado.OK, "Reinicializar el sistema");

        p.ver(s.buscarDesplieguePorId("DEP-0001").getResultado(),
                Resultado.ERROR_2, "Verificar que se eliminaron los despliegues");

        p.ver(s.obtenerRegion("us-east-1").getResultado(),
                Resultado.ERROR_2, "Verificar que se eliminaron las regiones");

        p.ver(s.listarDesplieguesPorIdAscendente().getResultado(),
                Resultado.OK, "Listado ascendente vacio luego de reinicializar");

        p.ver(s.listarDesplieguesPorIdDescendente().getResultado(),
                Resultado.OK, "Listado descendente vacio luego de reinicializar");

        p.ver(s.listarDesplieguesPorCodigoAscendente().getResultado(),
                Resultado.OK, "Listado por codigo vacio luego de reinicializar");

        p.ver(s.listarDesplieguesPorModalidad(Modalidad.TEXTO).getResultado(),
                Resultado.OK, "Listado por modalidad vacio luego de reinicializar");

        p.tituloPrueba("FIN DE LAS PRUEBAS");
    }

}
