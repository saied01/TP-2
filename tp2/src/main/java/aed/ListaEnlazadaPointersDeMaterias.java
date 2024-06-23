package aed;

/* Invariante de Representación:
- `raiz` es una referencia al primer nodo de la lista enlazada.
- El nodo raiz es `null` si y solo si la lista esta vacia.
- Si la lista esta vacia el valor de `largo` es 0.
- El valor de `largo` es mayor o igual a 0.
- Cada nodo en la lista contiene una referencia `pointerActual` y una referencia `siguiente` al siguiente nodo en la lista, o es nulo si es el último nodo.
- Si el `siguiente` de un nodo es null, ese nodo es el ultimo de la lista.
- Ningun nodo tiene como `siguiente` a la raiz.
*/

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