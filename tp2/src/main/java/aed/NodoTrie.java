package aed;

public class NodoTrie<T> {
    boolean esFinalPalabra;
    NodoTrie<T>[] hijo;
    NodoTrie<T> padre;
    T dato;
    String letra;

    public NodoTrie() {
        this.letra = null;
        this.padre = null;
        this.hijo = (NodoTrie<T>[]) new NodoTrie[256];
        this.esFinalPalabra = false;
        this.dato = null;
    }

    public NodoTrie(String letra, NodoTrie<T> padre, T dato) {
        this.letra = letra;
        this.padre = padre;
        this.hijo = (NodoTrie<T>[]) new NodoTrie[256];
        this.esFinalPalabra = false;
        this.dato = dato;
    }

    public boolean isEsFinalPalabra() {
        return esFinalPalabra;
    }

    public void setEsFinalPalabra(boolean esFinalPalabra) {
        this.esFinalPalabra = esFinalPalabra;
    }

    public NodoTrie<T>[] getHijo() {
        return hijo;
    }

    public void setHijo(NodoTrie<T>[] hijo) {
        this.hijo = hijo;
    }

    public NodoTrie<T> getPadre() {
        return padre;
    }

    public void setPadre(NodoTrie<T> padre) {
        this.padre = padre;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}

/*
class NodoEstudianteTrie extends Nodo<Estudiante> {
    public NodoEstudianteTrie() {
        super();
    }
}

class NodoMateria extends Nodo<Materia> {
    public NodoMateria() {
        super();
    }

 */
