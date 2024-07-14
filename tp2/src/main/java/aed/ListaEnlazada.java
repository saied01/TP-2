package aed;

import java.util.NoSuchElementException;

public class ListaEnlazada<T> {

    static class NodoLisEnl<T> {
        NodoLisEnl<T> siguiente;
        T dato;

        public NodoLisEnl(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    NodoLisEnl<T> raiz;
    int tamaño;

    public ListaEnlazada() {
        this.raiz = null;
        this.tamaño = 0;
    }

    public void insertar(T dato) {
        NodoLisEnl<T> nuevoNodoLisEnl = new NodoLisEnl<>(dato);

        if (raiz == null) {
            raiz = nuevoNodoLisEnl;
        } else {
            nuevoNodoLisEnl.siguiente = raiz;
            raiz = nuevoNodoLisEnl;
        }
        tamaño++;
    }

    public T obtenerPrimero() {
        if (raiz == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return raiz.dato;
    }

    public int tamaño() {
        return tamaño;
    }

    // Método para imprimir la lista (solo para depuración)
    public void imprimirLista() {
        NodoLisEnl<T> actual = raiz;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
    
    }
