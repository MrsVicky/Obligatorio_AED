/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

public interface IListaDoble<T> {

    boolean esVacia();

    void agregarInicio(T dato);
    void agregarFin(T dato);
    void agregarOrd(T dato);

    void eliminarInicio();
    void eliminarFin();
    void eliminarElemento(T dato);

    T obtenerElemento(T dato);
    boolean buscarElemento(T dato);

    String mostrar();
    String mostrarDescendente();

    int cantNodos();
}
