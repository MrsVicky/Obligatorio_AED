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
 public Retorno registrarDespliegue(String id, String codigo,
         String descripcion, boolean cuantizado, Modalidad modalidad) {

     // ERROR_1: datos null, vacios o solamente espacios.
     if (checkVacioONull(id) || checkVacioONull(codigo) || checkVacioONull(descripcion)|| modalidad== null) {

         return Retorno.error1();
     }

     // ERROR_2: formato de codigo incorrecto.
     if (!codigo.matches("[A-Z]{3}-[0-9]{3}-[A-Za-z0-9]{4}")) {
         return Retorno.error2();
     }

     Despliegue nuevo = new Despliegue(
             id, codigo.trim(), descripcion, cuantizado, modalidad
     );

     // ERROR_3: ya existe ese ID.
     if (DesPorId.buscarElemento(nuevo)) {
         return Retorno.error3();
     }

     DesXCodigo referencia = new DesXCodigo(nuevo);

     // ERROR_4: ya existe ese codigo.
     if (DesPorCod.buscarElemento(referencia)) {
         return Retorno.error4();
     }

     // Agregar a los indices globales.
     DesPorId.agregarOrd(nuevo);
     DesPorCod.agregarOrd(referencia);

     // Agregar solamente a la lista de su modalidad.
     switch (modalidad) {
         case TEXTO:
             DesModTexto.agregarOrd(referencia);
             break;

         case VISION:
             DesModVision.agregarOrd(referencia);
             break;

         case AUDIO:
             DesModAudio.agregarOrd(referencia);
             break;

         case MULTIMODAL:
             DesModMultiMod.agregarOrd(referencia);
             break;

         case OTROS:
             DesModOtros.agregarOrd(referencia);
             break;
     }

     return Retorno.ok();
 }

        @Override
    public Retorno buscarDesplieguePorId(String id) {

        // ERROR_1: ID null, vacio o solamente espacios.
        if (checkVacioONull(id)) {
            return Retorno.error1();
        }

        // Objeto auxiliar para buscar por ID.
        Despliegue buscado = new Despliegue(
                id, null, null, false, null
        );

        Despliegue encontrado = DesPorId.obtenerElemento(buscado);

        // ERROR_2: no existe ese ID.
        if (encontrado == null) {
            return Retorno.error2();
        }

        return Retorno.ok(encontrado.toString());
}

    @Override
    public Retorno listarDesplieguesPorIdAscendente() {
         return Retorno.ok(DesPorId.mostrar());
    }

    @Override
    public Retorno listarDesplieguesPorIdDescendente() {
       return Retorno.ok(DesPorId.mostrarDescendente());
    }

    @Override
    public Retorno buscarDesplieguePorCodigo(String codigo) {
        
        // ERROR_1: código null, vacío o solamente espacios.
        if (checkVacioONull(codigo)) {
            return Retorno.error1();
        }

        // ERROR_2: formato incorrecto.
        if (!codigo.matches("[A-Z]{3}-[0-9]{3}-[A-Za-z0-9]{4}")) {
            return Retorno.error2();
        }

        Despliegue buscado = new Despliegue(
                null, codigo, null, false, null
        );

        DesXCodigo referencia = new DesXCodigo(buscado);

        DesXCodigo encontrado = DesPorCod.obtenerElemento(referencia);

        // ERROR_3: no existe ese código.
        if (encontrado == null) {
            return Retorno.error3();
        }

        return Retorno.ok(encontrado.toString());
    }

    @Override
    public Retorno listarDesplieguesPorCodigoAscendente() {
        return Retorno.ok(DesPorCod.mostrar());
    }

    @Override
    public Retorno listarDesplieguesPorModalidad(Modalidad unaModalidad) {
            // ERROR_1: modalidad null.
        if (unaModalidad == null) {
            return Retorno.error1();
        }

        switch (unaModalidad) {
            case TEXTO:
                return Retorno.ok(DesModTexto.mostrar());

            case VISION:
                return Retorno.ok(DesModVision.mostrar());

            case AUDIO:
                return Retorno.ok(DesModAudio.mostrar());

            case MULTIMODAL:
                return Retorno.ok(DesModMultiMod.mostrar());

            case OTROS:
                return Retorno.ok(DesModOtros.mostrar());

            default:
                return Retorno.noImplementada();
        }
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
         Despliegue desAUsar = DesPorId.obtenerElemento(chkDes);
         DesXCodigo des = new DesXCodigo(desAUsar);
          des = DesPorCod.obtenerElemento(des);
        if(regAUsar.getActivos().contiene(des) || regAUsar.getPendientes().contiene(chkDes))return Retorno.error4();
        
        
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
         return texto == null || texto.trim().isEmpty();
    }

}
