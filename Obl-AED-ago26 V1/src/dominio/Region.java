/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import tads.Cola;
import tads.ListaSimple;
import tads.Pila;

public class Region implements Comparable <Region>{

    private String codigoReg;
    private String nombre;
    private String pais;
    private String zona;

    private Cola<Despliegue> pendientes;
    private ListaSimple<Despliegue> activos;
    private Pila<Despliegue> historial;
 
    // Constructor
    public Region(String codigoReg, String nombre,
                  String pais, String zona) {

        this.codigoReg = codigoReg;
        this.nombre = nombre;
        this.pais = pais;
        this.zona = zona;

        this.pendientes = new Cola<>();
        this.activos = new ListaSimple<>();
        this.historial = new Pila<>();
    }

    // Getters y setters

    public String getCodigoReg() {
        return codigoReg;
    }

    public void setCodigoReg(String codigoReg) {
        this.codigoReg = codigoReg;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Cola<Despliegue> getPendientes() {
        return pendientes;
    }

    public ListaSimple<Despliegue> getActivos() {
        return activos;
    }

    public Pila<Despliegue> getHistorial() {
        return historial;
    }

    // Representación de la región
    @Override
    public String toString() {
        return codigoReg + ";" + nombre + ";" + pais + ";" + zona;
    }
    
    @Override
    public boolean equals(Object o){
    return this.getCodigoReg().equalsIgnoreCase(((Region)o).getCodigoReg());
    }

    @Override
    public int compareTo(Region o) {
     return this.getCodigoReg().compareTo(o.getCodigoReg());

    }
}
