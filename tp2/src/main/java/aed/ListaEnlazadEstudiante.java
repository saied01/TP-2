package aed;

public class ListaEnlazadEstudiante {
    NodoEstudiante raiz;

    public ListaEnlazadEstudiante() {
        this.raiz = null;
    }

    public void insertarEstudiante(Estudiante estudiante) {
        /*
        Inserta al estudiante dentro de la lista enlazada de una materia
        Ademas le suma a su "materiasCursando" +1
         */
        if (raiz == null) {
            raiz = new NodoEstudiante(estudiante);
        } else {
            NodoEstudiante actual = raiz;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = new NodoEstudiante(estudiante);
        }
    estudiante.materiasCursando += 1;
    }
}
