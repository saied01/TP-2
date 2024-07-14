package aed;

import java.util.ArrayList;

public class Trie<T> {

    Nodo<T> raiz;
    int tamañoTrie;

    public Trie() {
        raiz = new Nodo<T>();
        tamañoTrie = 0;
    }

    public Nodo<T> insertarEnTrie(String palabra) {
        int n = palabra.length();
        Nodo<T> actual = raiz;

        for (int i = 0; i < n; i++) {
            int index = palabra.charAt(i); // Usamos el valor ASCII del carácter
            if (actual.hijo[index] == null) {
                actual.hijo[index] = new Nodo<>();
            }
            actual = actual.hijo[index];
            actual.letra = String.valueOf(palabra.charAt(i));
        }

        actual.esFinalPalabra = true;
        return actual;
    }

    public Nodo<T> devolverHoja(String palabra) {
        int n = palabra.length();
        Nodo<T> actual = raiz;

        for (int i = 0; i < n; i++) {
            int index = palabra.charAt(i); // Usamos el valor ASCII del carácter
            if (actual.hijo[index] == null) {
                return null;
            }
            actual = actual.hijo[index];
        }

        return actual;
    }

    public void borrarPalabra(Nodo<T> nodo) {
        /*
        O(|N_m|)
        recorre del final al principio letra por letra hasta
        - Encontrase con una letra que sea finalDePalabra de otra materia
        - LLegar a la raiz

        Ejemplo: Para la materia "ALGO1", actual.letra es "O" y hijo.letra es "1"
         */
            String palabraEliminada = "";
            Nodo<T> actual = nodo.padre;
            Nodo<T> hijo = nodo;
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

    //todo reveritir Trie<T>
    public void borrarTodasLasPalabras(ListaEnlazadaPointersDeMaterias.Nodo materia){
        while(materia != null){ // O(∑_{n∈N_m} |n|)
            materia.pointerActual.esFinalPalabra = false;
            materia.pointerActual.dato = null;
            borrarPalabra((Nodo<T>) materia.pointerActual);
            materia = materia.siguiente;
        }
    }

    public String[] devolverTodasLasPalabras(Nodo<T> nodo, String prefijo, ArrayList<String> resultado) {
        if (nodo == null) {
            return resultado.toArray(new String[0]);
        }

        if (nodo.esFinalPalabra) {
            resultado.add(prefijo);
        }

        for (int i = 0; i < nodo.hijo.length; i++) {
            if (nodo.hijo[i] != null) {
                devolverTodasLasPalabras(nodo.hijo[i], prefijo + (char) i, resultado);
            }
        }
        return resultado.toArray(new String[0]);
    }

}
