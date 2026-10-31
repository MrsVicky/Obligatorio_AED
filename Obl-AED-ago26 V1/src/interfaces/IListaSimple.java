/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author default
 */
public interface IListaSimple<T> {
        void agregarInicio(T dato);
    void agregarFinal(T dato);
    boolean eliminar(T dato);
    boolean contiene(T dato);
    int largo();
    T obtener(int posicion);
    boolean esVacia();
}

