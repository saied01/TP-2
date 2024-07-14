package aed;

public class Nodo<T> {
    boolean esFinalPalabra;
    Nodo<T>[] hijo;
    Nodo<T> padre;
    T dato;
    String letra;

    public Nodo() {
        this.letra = null;
        this.padre = null;
        this.hijo = (Nodo<T>[]) new Nodo[256];
        this.esFinalPalabra = false;
        this.dato = null;
    }

    public Nodo(String letra, Nodo<T> padre, T dato) {
        this.letra = letra;
        this.padre = padre;
        this.hijo = (Nodo<T>[]) new Nodo[256];
        this.esFinalPalabra = false;
        this.dato = dato;
    }

    public boolean isEsFinalPalabra() {
        return esFinalPalabra;
    }

    public void setEsFinalPalabra(boolean esFinalPalabra) {
        this.esFinalPalabra = esFinalPalabra;
    }

    public Nodo<T>[] getHijo() {
        return hijo;
    }

    public void setHijo(Nodo<T>[] hijo) {
        this.hijo = hijo;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
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
