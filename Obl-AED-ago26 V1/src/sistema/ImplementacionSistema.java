package sistema;
import dominio.Despliegue;
import dominio.DesXCodigo;
import dominio.Region;
import tads.Cola;
import tads.ListaDoble;
import tads.ListaSimple;
import tads.Pila;


//Octavio Imperial 354244
//Victoria Martinez 365013
public class ImplementacionSistema implements Sistema {
    
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
        
        if(checkVacioONull(codigo) || checkVacioONull(nombre) || checkVacioONull(pais) || checkVacioONull(zona)) return Retorno.error1();  
        Region nueva = new Region(codigo,nombre,pais,zona);
        if(RegionPorCod.contiene(nueva)) return Retorno.error2();    
                
        RegionPorCod.agregarOrdenado(nueva);            
        
        
        return Retorno.ok();
    }

    @Override
    public Retorno obtenerRegion(String codigo) {
        if(checkVacioONull(codigo)) return Retorno.error1();
        Region check = new Region(codigo,"","","");
        Region ret = RegionPorCod.obtener(check);       
        if(ret == null) return Retorno.error2();
        
        
        return Retorno.ok(ret.toString());
    }

    @Override
    public Retorno solicitarDespliegueEnRegion(String idDespliegue, String codigoRegion) {
        if(checkVacioONull(idDespliegue) || checkVacioONull(codigoRegion)) return Retorno.error1();
        Despliegue chkDes = new Despliegue(idDespliegue,"","",true,Modalidad.OTROS);
        if(!DesPorId.buscarElemento(chkDes)) return Retorno.error2();
        Region chkReg = new Region(codigoRegion,"","","");
        if(!RegionPorCod.contiene(chkReg)) return Retorno.error3();
         Region regAUsar = RegionPorCod.obtener(chkReg);
         DesXCodigo des = new DesXCodigo(chkDes);
          des = DesPorCod.obtenerElemento(des);
        if(regAUsar.getActivos().contiene(des) || regAUsar.getPendientes().contiene(chkDes))return Retorno.error4();
        
        Despliegue desAUsar = DesPorId.obtenerElemento(chkDes);
        regAUsar.getPendientes().encolar(desAUsar);
        
        
        return Retorno.ok();
    }

    @Override
    public Retorno procesarSiguienteDespliegue(String codigoRegion) {
        if(checkVacioONull(codigoRegion)) return Retorno.error1();
        Region chkReg = new Region(codigoRegion,"","","");
        if(!RegionPorCod.contiene(chkReg)) return Retorno.error2();
        Region regAUsar = RegionPorCod.obtener(chkReg);
        if(regAUsar.getPendientes().esVacia()) return Retorno.error3();
        Despliegue des = regAUsar.getPendientes().desencolar();
        DesXCodigo desAct = new DesXCodigo(des);
        regAUsar.getActivos().agregarOrdenado(desAct);
        regAUsar.getHistorial().apilar(des);

        
        return Retorno.ok(des.toString());
    }

    @Override
    public Retorno listarDesplieguesActivosDeRegion(String codigoRegion) {
        if(checkVacioONull(codigoRegion)) return Retorno.error1();
        Region chkReg = new Region(codigoRegion,"","","");
        if(!RegionPorCod.contiene(chkReg)) return Retorno.error2();
        Region regAUsar = RegionPorCod.obtener(chkReg);      
       
              
        return Retorno.ok(regAUsar.getActivos().mostrar());
    }

    @Override
    public Retorno retirarUltimoDespliegueProcesado(String codigoRegion) {
        if(checkVacioONull(codigoRegion)) return Retorno.error1();
        Region chkReg = new Region(codigoRegion,"","","");
        if(!RegionPorCod.contiene(chkReg)) return Retorno.error2();
        Region regAUsar = RegionPorCod.obtener(chkReg);
        if(regAUsar.getActivos().esVacia()) return Retorno.error3();
        Despliegue desRet = regAUsar.getHistorial().desapilar(); //Se desapila despligue
        DesXCodigo desAct = new DesXCodigo(desRet);
        regAUsar.getActivos().eliminar(desAct);
        
        
        
        return Retorno.ok(desRet.toString());
    }
    
    private boolean checkVacioONull(String texto){        
        return texto == null || texto.isBlank();
    }

}
