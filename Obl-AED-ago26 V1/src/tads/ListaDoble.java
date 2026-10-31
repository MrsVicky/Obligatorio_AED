/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

import interfaces.IListaDoble;

public class ListaDoble<T extends Comparable<T>>
        implements IListaDoble<T> {

    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo primero;
    private Nodo ultimo;
    private int cantnodos;

    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------

    public ListaDoble() {
        this.primero = null;
        this.ultimo = null;
        this.cantnodos = 0;
    }

    //---------------------------------------------------
    // Operaciones
    //---------------------------------------------------

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo nuevo = new Nodo(dato);

        if (esVacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }

        cantnodos++;
    }

    @Override
    public void agregarFin(T dato) {
        Nodo nuevo = new Nodo(dato);

        if (esVacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }

        cantnodos++;
    }

    @Override
    public void agregarOrd(T dato) {
        if (esVacia() || dato.compareTo(primero.dato) <= 0) {
            agregarInicio(dato);
        } else if (dato.compareTo(ultimo.dato) >= 0) {
            agregarFin(dato);
        } else {
            Nodo aux = primero;

            while (aux.siguiente.dato.compareTo(dato) < 0) {
                aux = aux.siguiente;
            }

            Nodo nuevo = new Nodo(dato);

            nuevo.siguiente = aux.siguiente;
            nuevo.anterior = aux;

            aux.siguiente.anterior = nuevo;
            aux.siguiente = nuevo;

            cantnodos++;
        }
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                primero = primero.siguiente;
                primero.anterior = null;
            }

            cantnodos--;
        }
    }

    @Override
    public void eliminarFin() {
        if (!esVacia()) {
            if (primero == ultimo) {
                primero = null;
                ultimo = null;
            } else {
                ultimo = ultimo.anterior;
                ultimo.siguiente = null;
            }

            cantnodos--;
        }
    }

    private Nodo buscarNodo(T dato) {
        Nodo aux = primero;

        while (aux != null) {
            if (aux.dato.equals(dato)) {
                return aux;
            }

            aux = aux.siguiente;
        }

        return null;
    }

    @Override
    public void eliminarElemento(T dato) {
        Nodo borrar = buscarNodo(dato);

        if (borrar != null) {
            if (borrar == primero) {
                eliminarInicio();
            } else if (borrar == ultimo) {
                eliminarFin();
            } else {
                borrar.anterior.siguiente = borrar.siguiente;
                borrar.siguiente.anterior = borrar.anterior;

                cantnodos--;
            }
        }
    }

    @Override
    public T obtenerElemento(T dato) {
        Nodo encontrado = buscarNodo(dato);

        if (encontrado == null) {
            return null;
        }

        return encontrado.dato;
    }

    @Override
    public boolean buscarElemento(T dato) {
        return buscarNodo(dato) != null;
    }

    @Override
    public String mostrar() {
        StringBuilder resultado = new StringBuilder();
        Nodo aux = primero;

        while (aux != null) {
            resultado.append(aux.dato);

            if (aux.siguiente != null) {
                resultado.append("|");
            }

            aux = aux.siguiente;
        }

        return resultado.toString();
    }

    @Override
    public String mostrarDescendente() {
        StringBuilder resultado = new StringBuilder();
        Nodo aux = ultimo;

        while (aux != null) {
            resultado.append(aux.dato);

            if (aux.anterior != null) {
                resultado.append("|");
            }

            aux = aux.anterior;
        }

        return resultado.toString();
    }

    @Override
    public int cantNodos() {
        return cantnodos;
    }
}