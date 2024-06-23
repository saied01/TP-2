package aed;

public class ListaEnlazadaPointersDeMaterias {

    public class Nodo{
        Nodo siguiente;
        NodoMateria pointerActual;

        public Nodo(NodoMateria pointerActual){
            this.siguiente =null;
            this.pointerActual = pointerActual;

        }
    }
    Nodo raiz;
    int largo;
    public ListaEnlazadaPointersDeMaterias() {
        this.raiz = null;
        this.largo = 0;
    }

    public void insertar(NodoMateria pointer) {
        /*
        -Inserta al pointer del nombre de esa materia para una de sus carreras
        dentro de la lista enlazada.
        -Es O(1) porque lo inserta al principio de la lista siempre
         */

        Nodo nuevoNodo = new Nodo(pointer);

        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            nuevoNodo.siguiente = raiz;
            raiz = nuevoNodo;
        }
        largo += 1;
    }
}