package aed;
import java.util.ArrayList;

/* Invariante de Representacion:
- `TrieEstudiantes` tiene un unico nodo raiz.
- Para toddo nodo n, sus hijos estan conformados en un arreglo de nodos.
- Si un nodo n no es el final de una palabra, tiene por lo menos un hijo.
- No existen dos nodos en el trie que representen el mismo prefijo de una palabra. Osea, cada camino desde la raíz a un nodo es único.
- Si un nodo es el final de una palabra, la palabra que componen los caracteres de los nodos, es una LU de algun estudiante.
* */

public class TrieEstudiantes {

    NodoEstudianteTrie raiz;

    public TrieEstudiantes() {
        raiz = new NodoEstudianteTrie();
    }

    public NodoEstudianteTrie insertarEstudiante(String LU) {
        /*
        O(|LU|) == O (1), esto debido a que el largo de LU de estudiantes esta actotado
         */
        int n = LU.length();

        NodoEstudianteTrie actual = raiz;

        for (int i = 0; i < n; i++) {
            int indice = LU.charAt(i) - '/';

            if (actual.hijo[indice] == null) {
                actual.hijo[indice] = new NodoEstudianteTrie();
            }
            actual.hijo[indice].padre = actual;
            actual = (NodoEstudianteTrie) actual.hijo[indice];
            actual.letra = String.valueOf(LU.charAt(i));
        }
        actual.estudiante = new Estudiante(LU);
        actual.esFinalPalabra = true;
        actual.estudiante.materiasCursando = 0;
        return actual;
    }

    public NodoEstudianteTrie buscarEstudiante(String LU) {
        /*
        O(|LU|) == O (1), esto debido a que el largo de LU de estudiantes esta actotado
         */
        NodoEstudianteTrie actual = raiz;
        for (int i = 0; i < LU.length(); i++) {
            int indice = LU.charAt(i) - '/';
            if (actual.hijo[indice] == null) {
                return null;
            }
            actual = (NodoEstudianteTrie) actual.hijo[indice];
        }
        return actual;
    }
}
