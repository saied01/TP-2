package aed;

import java.util.ArrayList;

/* Invariante de Representacion:
- `TrieCarreras` tiene un unico nodo raiz.
- Para toddo nodo n, sus hijos estan conformados en un arreglo de nodos.
- Si un nodo n no es el final de una palabra, tiene por lo menos un hijo.
- No existen dos nodos en el trie que representen el mismo prefijo de una palabra.
  Osea, cada camino desde la raíz a un nodo es único.
- Si un nodo es el final de una palabra, la palabra que componen los caracteres de los nodos, es una carrera como minimo.

* */

public class TrieCarreras {

    NodoCarrera raiz;

    public TrieCarreras() {
        raiz = new NodoCarrera();
    }

    public TrieMaterias insertarEnTrie(String nombreCarrera) {
        /*
        Siempre es O(|c|)
        -vamos a recorrer el largo de las letras siempre
            - creamos TrieMaterias() en el nodo final o no O(1)
            - head.esFinalPalabra = true para la ultima letra
         - la operacion ademas devuelve el Trie de sus Materias, lo cual nos es util y no implica aumentar
            la complejidad de la funcion
         */
        int n = nombreCarrera.length();

        NodoCarrera head = raiz;


        for (int i = 0; i < n; i++) {

            int index = nombreCarrera.charAt(i);//  - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) {
                head.hijo[index] = new NodoCarrera();
            }

            head = (NodoCarrera) head.hijo[index];
            head.letra = String.valueOf(nombreCarrera.charAt(i));
        }

        head.esFinalPalabra = true;
        if (head.trieMaterias == null) {
            head.trieMaterias = new TrieMaterias();
        }
        return head.trieMaterias;
    }

    public NodoCarrera devolverHojaCarrera(String word) {

        int n = word.length();

        NodoCarrera head = raiz;
        for (int i = 0; i < n; i++) {
            int index = word.charAt(i);// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) return null;
            head = (NodoCarrera) head.hijo[index];
        }

        return head;
    }

    public String[] devolverTodasLasCarreras(NodoCarrera nodo, String prefijo, ArrayList<String> resultado){
      /*
        O(∑_{c∈C} |c|)

        — Esta función itera sobre todas los hijos y los sucesivos hijos de los mismos
        — Crea un nuevo String para cada hijo no nulo de nodo “actual”
        — Para cada String que crea parte del prefijo que ya venía teniendo y suma la nueva letra
        — Por último concatena todos los Strings en un solo Array
         */



        if (nodo == null) {
            return resultado.toArray(new String[0]);
        }

        if (nodo.esFinalPalabra) {
            resultado.add(prefijo.toString());
        }

        for (int i = 0; i < nodo.hijo.length; i++) {
            if (nodo.hijo[i] != null) {
                devolverTodasLasCarreras((NodoCarrera) nodo.hijo[i], prefijo + (char) (i), resultado);
            }
        }
        return resultado.toArray(new String[0]);
    }

}

