package aed;

import java.util.ArrayList;

public class TrieMaterias {

    NodoMateria raiz;
    int cantidadDeMaterias;

    public TrieMaterias() {
        raiz = new NodoMateria();
        cantidadDeMaterias = 0;
    }

    public NodoMateria insertarEnTrie(String word) {
        /*
        O(|M_c|)
        -Al igual que el insertEnTrie para carreras vamos a recorrer siempre el largo de |M_c|
        -cambiamos el nodo final a esFinalPalabra = true
        -devolvemos el ultimo nodo porque lo vamos a usar a continuacion

         */
        int n = word.length();

        NodoMateria actual = raiz;

        for (int i = 0; i < n; i++) {

            int index = word.charAt(i);// - 'a'; <- esto se bugea, resolver despues

            if (actual.hijo[index] == null) {
                actual.hijo[index] = new NodoMateria();
            }

            actual.hijo[index].padre = actual;
            actual = actual.hijo[index];
            actual.letra = String.valueOf(word.charAt(i));
        }

        actual.esFinalPalabra = true;
        return actual;
    }

    public NodoMateria devolverHojaMateria(String word) {

        int n = word.length();

        NodoMateria head = raiz;
        for (int i = 0; i < n; i++) {
            int index = word.charAt(i);// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) return null;
            head = head.hijo[index];
        }

        return head;
    }

    public void borrarMateria(NodoMateria tailMateria) {
        /*
        O(|N_m|)
        recorre del final al principio letra por letra hasta
        - Encontrase con una letra que sea finalDePalabra de otra materia
        - LLegar a la raiz

        Ejemplo: Para la materia "ALGO1", actual.letra es "O" y hijo.letra es "1"
         */
        String palabraEliminada = "";
        NodoMateria actual = tailMateria.padre;
        NodoMateria hijo = tailMateria;
        if(hijo.hijo == null) {
            while (actual.padre != null & actual.esFinalPalabra) {  // O(|N_m|)
                palabraEliminada = hijo.letra + palabraEliminada;
                hijo = actual;
                actual = actual.padre;
            }
            actual.hijo[hijo.letra.charAt(0)] = null;

            //Ej llegas a actual = raiz e hijo.letra = "A"
            // y pones que actual.hijo[indice] = null en lugar de hijo.
        }

    }

    public String[] devolverTodasLasMaterias(NodoMateria nodo, String prefijo, ArrayList<String> resultado){
        /*
        O(∑_{m_c∈M_c} |m_c|)

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
                devolverTodasLasMaterias(nodo.hijo[i], prefijo + (char) (i), resultado);
            }
        }
        return resultado.toArray(new String[0]);
    }

}
