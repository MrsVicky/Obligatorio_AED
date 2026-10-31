/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author default
 */
public interface IPila<T> {
    void apilar(T dato);
    T desapilar();
    T cima();
    boolean esVacia();
    int tamaño();
    
}

