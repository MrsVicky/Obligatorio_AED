/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import sistema.Modalidad;

/**
 *
 * @author default
 */
public class Despliegue implements Comparable <Despliegue>{
    
    private String idDes;
    private String codigoDes;
    private String descripcion;
    private boolean cuantizado;
    private Modalidad modalidad;

    public Despliegue(String idDes, String codigoDes, String descripcion, boolean cuantizado, Modalidad modalidad) {
        this.idDes = idDes;
        this.codigoDes = codigoDes;
        this.descripcion = descripcion;
        this.cuantizado = cuantizado;
        this.modalidad = modalidad;
    }

    public String getIdDes() {
        return idDes;
    }

    public void setIdDes(String idDes) {
        this.idDes = idDes;
    }

    public String getCodigoDes() {
        return codigoDes;
    }

    public void setCodigoDes(String codigoDes) {
        this.codigoDes = codigoDes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCuantizado() {
        return cuantizado;
    }

    public void setCuantizado(boolean cuantizado) {
        this.cuantizado = cuantizado;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }
    
    
    @Override
    public String toString(){       
        
        return idDes + ";" + codigoDes + ";" + descripcion + ";" + cuantizado + ";" + modalidad;  
    }
    
    
    @Override
    public boolean equals(Object o){
    return this.getIdDes().equalsIgnoreCase(((Despliegue)o).getIdDes());
    }
    
    @Override
    public int compareTo(Despliegue o){
    
    return this.getIdDes().compareTo(o.getIdDes());
    
    }
           
    
}
