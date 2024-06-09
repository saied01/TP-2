package aed;

public class NodoEstudiante {
    NodoEstudiante siguiente;
    Estudiante estudiante;

    public NodoEstudiante(Estudiante estudiante) {
        this.siguiente = null;
        this.estudiante = estudiante;
    }
}
