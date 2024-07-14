package aed;

/* Invariante de Representación:
- `letra` puede ser nulo o puede ser un caracter (con valor String).
- El hijo de un nodo es un arreglo de tamaño 256 que contiene nodos de `NodoCarrera` o valores `null`.
- No existen dos hijos de un mismo nodo, con valores distintos de `null`, y con el mismo valor.
- Contrariamente, si `esFinalPalabra` = false, entonces `trieMaterias` es `null`.
- Si `esFinalPalabra` = false, entonces el nodo tiene al menos un nodo hijo con `esFinalPalabra` = true.
- Si `esFinalPalabra` = true, entonces `trieMaterias` referencia a un instancia de TrieMaterias.
*/

public class NodoCarrera {
    /*
    nodo con modificacion del 'tail' con instancia de trie de Materias de carrera
     */
    boolean esFinalPalabra;
    NodoCarrera[] hijo;
    TrieMaterias trieMaterias;
    String letra;

    public NodoCarrera() {
        this.letra = null;
        this.hijo = new NodoCarrera[256];
        this.esFinalPalabra = false;
        this.trieMaterias = null;
    }
}
