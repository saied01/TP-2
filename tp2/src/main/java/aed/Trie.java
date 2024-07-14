package aed;

import java.util.ArrayList;

public class Trie<T extends Nodo> {

    T raiz;

    public Trie(T raiz) {
        this.raiz = raiz;
    }

    public T insertarEnTrie(String word, Class<T> nodoClass) {
        int n = word.length();
        T actual = raiz;

        try {
            for (int i = 0; i < n; i++) {
                int index = word.charAt(i);

                if (actual.hijo[index] == null) {
                    actual.hijo[index] = nodoClass.getDeclaredConstructor().newInstance();
                }

                actual.hijo[index].padre = actual;
                actual = (T) actual.hijo[index];
                actual.letra = String.valueOf(word.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        actual.esFinalPalabra = true;
        return actual;
    }

    public T devolverHoja(String word) {
        int n = word.length();
        T actual = raiz;

        for (int i = 0; i < n; i++) {
            int index = word.charAt(i);

            if (actual.hijo[index] == null) return null;
            actual = (T) actual.hijo[index];
        }

        return actual;
    }

    public void borrar(T nodoFinal) {
        String palabraEliminada = "";
        T actual = (T) nodoFinal.padre;
        T hijo = nodoFinal;

        if (hijo.hijo == null) {
            while (actual.padre != null && actual.esFinalPalabra) {
                palabraEliminada = hijo.letra + palabraEliminada;
                hijo = actual;
                actual = (T) actual.padre;
            }
            actual.hijo[hijo.letra.charAt(0)] = null;
        }
    }

    public String[] devolverTodasLasPalabras(T nodo, String prefijo, ArrayList<String> resultado) {
        if (nodo == null) {
            return resultado.toArray(new String[0]);
        }

        if (nodo.esFinalPalabra) {
            resultado.add(prefijo);
        }

        for (int i = 0; i < nodo.hijo.length; i++) {
            if (nodo.hijo[i] != null) {
                devolverTodasLasPalabras((T) nodo.hijo[i], prefijo + (char) i, resultado);
            }
        }
        return resultado.toArray(new String[0]);
    }
}

class TrieMaterias extends Trie<NodoMateria> {

    int cantidadDeMaterias;

    public TrieMaterias() {
        super(new NodoMateria());
        cantidadDeMaterias = 0;
    }

    public NodoMateria insertarEnTrie(String word) {
        return super.insertarEnTrie(word, NodoMateria.class);
    }

    public NodoMateria devolverHojaMateria(String word) {
        return super.devolverHoja(word);
    }

    public void borrarMateria(NodoMateria tailMateria) {
        super.borrar(tailMateria);
    }

    public String[] devolverTodasLasMaterias(NodoMateria nodo, String prefijo, ArrayList<String> resultado) {
        return super.devolverTodasLasPalabras(nodo, prefijo, resultado);
    }
}

class TrieEstudiantes extends Trie<NodoEstudianteTrie> {

    public TrieEstudiantes() {
        super(new NodoEstudianteTrie());
    }

    public NodoEstudianteTrie insertarEstudiante(String LU) {
        NodoEstudianteTrie actual = super.insertarEnTrie(LU, NodoEstudianteTrie.class);
        actual.estudiante = new Estudiante(LU);
        actual.esFinalPalabra = true;
        actual.estudiante.materiasCursando = 0;
        return actual;
    }

    public NodoEstudianteTrie buscarEstudiante(String LU) {
        return super.devolverHoja(LU);
    }
}

class TrieCarreras extends Trie<NodoCarrera> {

    public TrieCarreras() {
        super(new NodoCarrera());
    }

    public TrieMaterias insertarEnTrie(String nombreCarrera) {
        NodoCarrera head = super.insertarEnTrie(nombreCarrera, NodoCarrera.class);
        if (head.trieMaterias == null) {
            head.trieMaterias = new TrieMaterias();
        }
        return head.trieMaterias;
    }

    public NodoCarrera devolverHojaCarrera(String word) {
        return super.devolverHoja(word);
    }

    public String[] devolverTodasLasCarreras(NodoCarrera nodo, String prefijo, ArrayList<String> resultado) {
        return super.devolverTodasLasPalabras(nodo, prefijo, resultado);
    }
}