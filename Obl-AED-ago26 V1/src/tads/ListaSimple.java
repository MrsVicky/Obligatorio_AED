/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

import interfaces.IListaSimple;

/**
 *
 * @author default
 */

public class ListaSimple<T extends Comparable<T>>
        implements IListaSimple<T> {

    
    

    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo(T dato) { this.dato = dato; }
    }
    
    private Nodo inicio;
    private int largo;
    //---------------------------------------------------
    // constructor
    //---------------------------------------------------    
    
        public ListaSimple() {
        this.inicio = null;
        this.largo = 0;
    }
     //---------------------------------------------------
    // set y get
    //---------------------------------------------------

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }
 
    //---------------------------------------------------
    // abstractos
    //---------------------------------------------------
    
    @Override
    public void agregarInicio(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = inicio;
        inicio = nuevo;
        largo++;
    }

    @Override
    public void agregarFinal(T dato) {
        Nodo nuevo = new Nodo(dato);   
        if (inicio==null){
            inicio=nuevo;
        }else{
            Nodo aux=inicio;
            while (aux.siguiente!=null){
                aux=aux.siguiente;
            }
            aux.siguiente=nuevo;       
        }
        largo++;
    }
    
    @Override
    public void agregarOrdenado(T dato) {
        
        Nodo nuevo = new Nodo(dato);
        if(this.esVacia() || dato.compareTo(this.inicio.dato)<0 ){
        agregarInicio(dato);
        }else{
            Nodo aux = this.getInicio();
            while (aux.siguiente!=null && aux.siguiente.dato.compareTo(dato)<0){
            aux=aux.siguiente;
            }
            nuevo.siguiente=aux.siguiente;
            aux.siguiente=nuevo;            
            largo++;  
        } 
    }
    

    @Override
    public boolean eliminar(T dato) {
        if (inicio==null){
            return false;
        }
        if (inicio.dato.equals(dato)){
            inicio=inicio.siguiente;
            largo--;
            return true;
        }
        Nodo aux=inicio;
        while(aux.siguiente!=null && !aux.siguiente.dato.equals(dato)){
            aux=aux.siguiente;
        }
        if (aux.siguiente==null) { 
            return false;
        }
        aux.siguiente=aux.siguiente.siguiente;
        largo--;
        return true;
    }

    @Override
    public boolean contiene(T dato) {
        Nodo aux=inicio;
        while (aux!=null){
            if (aux.dato.equals(dato)){
                return true;
            }
            aux=aux.siguiente;
        }
        return false;
    }

    @Override
    public int largo() {
        return largo;
    }

    @Override
    public T obtener(T dato) {
        Nodo aux = this.inicio;
        
        while (aux!=null){
            if (aux.dato.equals(dato)){
                return aux.dato;
            }
            aux=aux.siguiente;
        }        
        
        return null;

    }

    @Override
    public boolean esVacia() {
        return largo==0;
    }
    
    @Override
    public String mostrar() {
         StringBuilder resultado = new StringBuilder();
        Nodo aux = this.inicio;

        while (aux != null) {
            resultado.append(aux.dato);

            if (aux.siguiente != null) {
                resultado.append("|");
            }

            aux = aux.siguiente;
        }

        return resultado.toString();
    }
    
}
