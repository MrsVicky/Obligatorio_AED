/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author default
 */
import interfaces.IPila;

public class Pila<T> implements IPila<T> {

        private class Nodo {
        T dato;
        Nodo sig;
        Nodo(T d) { dato = d; }
        }
    private Nodo cima;
    private int tamaño=0;
//------------------------------------------------------
//  constructor
//------------------------------------------------------

    public Pila() {
        this.cima = null;
    }
//-----------------------------------------------------
   // set y get 
//-----------------------------------------------------    

    public Nodo getCima() {
        return cima;
    }

    public void setCima(Nodo cima) {
        this.cima = cima;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    


//------------------------------------------------------
//abstractos
//------------------------------------------------------    
    @Override
    public void apilar(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.sig=cima;
        cima=nuevo;
        tamaño++;
    }

    @Override
    public T desapilar() {
        if (esVacia()){
            return null;
        }
        T dato = cima.dato;
        cima=cima.sig;
        tamaño--;
        return dato;
    }

    @Override
    public T cima() {
        if (!esVacia()){
            return cima.dato;
        }
        return null;
    }

    @Override
    public boolean esVacia() {
        return cima==null;
    }

    @Override
    public int tamaño() {
        return tamaño;
    }
    
}
