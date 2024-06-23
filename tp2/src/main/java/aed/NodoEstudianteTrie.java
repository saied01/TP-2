package aed;

/* Invariante de Representación:
- `letra` representa el carácter correspondiente a este nodo, y es `null` si y solo si el nodo es la raiz.
- El valor del `padre` del nodo es `null` si y solo si el nodo es la raiz.
- `hijo` es un arreglo de `NodoEstudianteTrie` de longitud 256, donde cada elemento puede ser `null` o una referencia a un nodo hijo.
- Si los nodos entre la raiz y el nodo son 6, `esFinalPalabra` de ese nodo es true.
- Si el valor `esFinalPalabra` del nodo es true entonces su valor estudiante es distinto de `null`.
- Contrariamente, si el valor `esFinalPalabra` del nodo es false entonces su valor estudiante es `null`.
*/

public class NodoEstudianteTrie {
    boolean esFinalPalabra;
    NodoEstudianteTrie[] hijo;
    NodoEstudianteTrie padre;
    Estudiante estudiante;
    String letra;

    public NodoEstudianteTrie() {
        this.letra = null;
        this.padre = null;
        this.hijo = new NodoEstudianteTrie[256];
        this.esFinalPalabra = false;
        this.estudiante = null;

    }
}
