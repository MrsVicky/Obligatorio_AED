package sistema;
import dominio.Despliegue;
import dominio.DesXCodigo;
import dominio.Region;
import tads.Cola;
import tads.ListaDoble;
import tads.ListaSimple;
import tads.Pila;
import interfaces.ISistema;


//Octavio Imperial 354244
//Victoria Martinez 365013
public class ImplementacionSistema implements ISistema {
    
    private ListaDoble<Despliegue> DesPorId;
    private ListaDoble<DesXCodigo> DesPorCod;
    private ListaSimple<Region> RegionPorCod;
    private ListaDoble<DesXCodigo> DesModTexto;
    private ListaDoble<DesXCodigo> DesModVision;
    private ListaDoble<DesXCodigo> DesModAudio;
    private ListaDoble<DesXCodigo> DesModMultiMod;
    private ListaDoble<DesXCodigo> DesModOtros;
    

    @Override
    public Retorno inicializarSistema() {
        
        DesPorId = new ListaDoble<>();
        DesPorCod = new ListaDoble<>();
        DesModTexto = new ListaDoble<>();
        DesModVision = new ListaDoble<>();
        DesModAudio = new ListaDoble<>();
        DesModMultiMod = new ListaDoble<>();
        DesModOtros = new ListaDoble<>();        
        RegionPorCod = new ListaSimple<>();
             
        
        
                return Retorno.ok();

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
