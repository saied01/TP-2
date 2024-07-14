package aed;

/* Invariante de Representación:
- `valor` nunca es nulo después de que se haya creado el nodo.
- El `siguiente` de un nodo es otro nodo estudiante, o `null` si es el final de la lista.
*/

public class NodoEstudiante {
    Estudiante valor;
    NodoEstudiante siguiente;
    Estudiante estudiante;

    public NodoEstudiante(Estudiante estudiante) {
        valor = estudiante;
        this.siguiente = null;
        this.estudiante = estudiante;
    }
}
