package aed;

/* Invariante de Representación:
- `letra` representa el caracter correspondiente a este nodo, y es `null` si y solo si el nodo es la raiz.
- El valor del `padre` del nodo es `null` si y solo si el nodo es la raiz.
- `hijo` es un arreglo de `NodoMateria` de longitud 256,
   donde cada elemento puede ser `null` o una referencia a un nodo hijo.
- `materia` es una referencia a un objeto `Materia` si el nodo representa el final de una palabra completa,
   de lo contrario, es nulo.
- Si `esFinalPalabra` = true:
    --`materia` no debe ser nulo y
    -- este mismo NodoMateria se encuentra dentro de la lista materia.tailsDeSusCarreras .

*/

public class NodoMateria {
    /*
    nodo con modificacion del 'tail' con instancia de clase Materia
     */
    boolean esFinalPalabra;
    NodoMateria[] hijo;
    NodoMateria padre;
    Materia materia;

    String letra;

    public NodoMateria() {
        this.letra = null;
        this.padre = null;
        this.hijo = new NodoMateria[256];
        this.esFinalPalabra = false;
        this.materia = null;

    }
}
