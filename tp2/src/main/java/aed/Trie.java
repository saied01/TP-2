package aed;

import java.util.ArrayList;

public class Trie<T> {

    NodoTrie<T> raiz;
    int tamañoTrie;

    public Trie() {
        raiz = new NodoTrie<T>();
        tamañoTrie = 0;
    }

    public NodoTrie<T> insertarEnTrie(String palabra) {
        int n = palabra.length();
        NodoTrie<T> actual = raiz;

        for (int i = 0; i < n; i++) {
            int index = palabra.charAt(i); // Usamos el valor ASCII del carácter
            if (actual.hijo[index] == null) {
                actual.hijo[index] = new NodoTrie<>();
            }
            actual = actual.hijo[index];
            actual.letra = String.valueOf(palabra.charAt(i));
        }

        actual.esFinalPalabra = true;
        return actual;
    }

    public NodoTrie<T> devolverHoja(String palabra) {
        int n = palabra.length();
        NodoTrie<T> actual = raiz;

        for (int i = 0; i < n; i++) {
            int index = palabra.charAt(i); // Usamos el valor ASCII del carácter
            if (actual.hijo[index] == null) {
                return null;
            }
            actual = actual.hijo[index];
        }

        return actual;
    }

    public void borrarPalabra(NodoTrie<T> nodoTrie) {
        /*
        O(|N_m|)
        recorre del final al principio letra por letra hasta
        - Encontrase con una letra que sea finalDePalabra de otra materia
        - LLegar a la raiz

        Ejemplo: Para la materia "ALGO1", actual.letra es "O" y hijo.letra es "1"
         */
            String palabraEliminada = "";
            NodoTrie<T> actual = nodoTrie.padre;
            NodoTrie<T> hijo = nodoTrie;
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

    public void borrarTodasLasPalabras(ListaEnlazada.NodoLisEnl<NodoTrie<T>> nodoLisEnlMateria){
        while(nodoLisEnlMateria != null){ // O(∑_{n∈N_m} |n|)
            nodoLisEnlMateria.dato.esFinalPalabra = false;
            nodoLisEnlMateria.dato.dato = null;
            borrarPalabra((NodoTrie<T>) nodoLisEnlMateria.dato);
            nodoLisEnlMateria = nodoLisEnlMateria.siguiente;
        }
    }

    public String[] devolverTodasLasPalabras(NodoTrie<T> nodoTrie, String prefijo, ArrayList<String> resultado) {
        if (nodoTrie == null) {
            return resultado.toArray(new String[0]);
        }

        if (nodoTrie.esFinalPalabra) {
            resultado.add(prefijo);
        }

        for (int i = 0; i < nodoTrie.hijo.length; i++) {
            if (nodoTrie.hijo[i] != null) {
                devolverTodasLasPalabras(nodoTrie.hijo[i], prefijo + (char) i, resultado);
            }
        }
        return resultado.toArray(new String[0]);
    }

}
