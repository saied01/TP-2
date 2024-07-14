package aed;

public class Nodo {
    boolean esFinalPalabra;
    Nodo[] hijo;
    Nodo padre;
    String letra;

    public Nodo() {
        this.letra = null;
        this.padre = null;
        this.hijo = new Nodo[256];
        this.esFinalPalabra = false;
    }
}

class NodoMateria extends Nodo {
    Materia materia;

    NodoMateria() {
        this.materia = null;
    }
}

class NodoCarrera extends Nodo {
    TrieMaterias trieMaterias;

    NodoCarrera() {
        this.trieMaterias = null;
    }
}

class NodoEstudianteTrie extends Nodo {
    Estudiante estudiante;

    NodoEstudianteTrie() {
        this.estudiante = null;
    }
}