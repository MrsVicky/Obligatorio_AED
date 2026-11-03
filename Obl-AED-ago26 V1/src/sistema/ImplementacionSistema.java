package sistema;

//Octavio Imperial 354244
//Victoria Martinez 365013
public class ImplementacionSistema implements Sistema {

    @Override
    public Retorno inicializarSistema() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarDespliegue(String id, String codigo, String descripcion,
                                      boolean cuantizado, Modalidad modalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarDesplieguePorId(String id) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorIdAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorIdDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarDesplieguePorCodigo(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorCodigoAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorModalidad(Modalidad unaModalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarRegion(String codigo, String nombre, String pais, String zona) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno obtenerRegion(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno solicitarDespliegueEnRegion(String idDespliegue, String codigoRegion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno procesarSiguienteDespliegue(String codigoRegion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesActivosDeRegion(String codigoRegion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno retirarUltimoDespliegueProcesado(String codigoRegion) {
        return Retorno.noImplementada();
    }

}
