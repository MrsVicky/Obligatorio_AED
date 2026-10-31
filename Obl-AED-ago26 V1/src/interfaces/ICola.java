/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author default
 */
public interface ICola<T> {
    void encolar(T dato);
    T desencolar();
    T frente();
    boolean esVacia();
    int tamaño();
}
