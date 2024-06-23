package aed;

/* Invariante de Representación:
- `raiz` es la referencia al primer nodo de la lista enlazada. Es `null` si y solo si la lista está vacía.
- Cada `NodoEstudiante` en la lista enlazada contiene una referencia a un `Estudiante`.
- No existe ningun nodo el cual su siguiente sea la raiz.
- El ultimo elemento de la lista siempre va a tener a elemento.siguiente = null.
 */


public class ListaEnlazadaEstudiante {
    NodoEstudiante raiz;

    public ListaEnlazadaEstudiante() {
        this.raiz = null;
    }

    public void insertarEstudiante(Estudiante estudiante) {
        /*
        Inserta al estudiante dentro de la lista enlazada de una materia
        Ademas le suma a su "materiasCursando" +1
         */
        NodoEstudiante nuevoEstudiante = new NodoEstudiante(estudiante);

        if (raiz == null) {
            raiz = nuevoEstudiante;
        } else {
            nuevoEstudiante.siguiente = raiz;
            raiz = nuevoEstudiante;
        }
        estudiante.materiasCursando += 1;
    }
}