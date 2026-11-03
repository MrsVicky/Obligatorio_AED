/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author default
 */
import interfaces.ICola;

public class Cola<T> implements ICola<T> {   

    private class Nodo {
        T dato;
        Nodo sig;
        Nodo(T d) { dato = d; }
    }
    private Nodo frente;
    private Nodo fondo;
    private int tamaño = 0;

    public Cola() {
        this.frente = null;
        this.fondo = null;
    }

    
    
    //--------------------------------------------
    // set y get
    //--------------------------------------------
    public Nodo getFrente() {
        return frente;
    }

    public void setFrente(Nodo frente) {
        this.frente = frente;
    }

    public Nodo getFondo() {
        return fondo;
    }

    public void setFondo(Nodo fondo) {
        this.fondo = fondo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
//-------------------------------------------------------------------------
//aabstrctos
//-------------------------------------------------------------------------    
    @Override
    public void encolar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia())
        {
           fondo=nuevo;
           frente=nuevo;           
        }else{
           fondo.sig=nuevo;
           fondo=nuevo;
        }
        tamaño++;
    }

    @Override
    public T desencolar() {
        if (esVacia()) return null;
        T dato = frente.dato;
        frente=frente.sig;
        tamaño--;
        if (frente==null) 
        {
            fondo=null;
        }
        return dato;
    }

    @Override
    public T frente() {
        if (esVacia())
            return null;
        else 
            return frente.dato;         
    }

    @Override
    public boolean esVacia() {
        return frente==null;
    }

    @Override
    public int tamaño() {
        return tamaño;
    }
    
}