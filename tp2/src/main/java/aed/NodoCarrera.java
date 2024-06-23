package aed;

/* Invariante de Representación:
- `letra` puede ser nulo o puede ser un caracter (con valor String).
- El hijo de un nodo es un arreglo de tamaño 256 que contiene nodos de `NodoCarrera` o valores `null`.
- No existen dos hijos de un mismo nodo, con valores distintos de `null`, y con el mismo valor.
- Si `esFinalPalabra` es verdadero, entonces `trieMaterias` no es `null`.
- Contrariamente, si `esFinalPalabra` es falso, entonces `trieMaterias` es `null`.
- Si `esFinalPalabra` es falso, entonces el nodo tiene al menos un nodo hijo con valor distinto de `null`.
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
