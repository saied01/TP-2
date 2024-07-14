package aed;

public class Nodo {
    boolean esFinalPalabra;
    Nodo[] hijo;
    Nodo padre;
    String letra;

    public Nodo(int hijoSize) {
        this.letra = null;
        this.padre = null;
        this.hijo = new Nodo[hijoSize];
        this.esFinalPalabra = false;
    }
}

class NodoMateria extends Nodo {
    Materia materia;

    public NodoMateria() {
        super(256);
        this.materia = null;
    }
}

class NodoCarrera extends Nodo {
    TrieMaterias trieMaterias;

    public NodoCarrera() {
        super(256);
        this.trieMaterias = null;
    }
}

class NodoEstudianteTrie extends Nodo {
    Estudiante estudiante;

    public NodoEstudianteTrie() {
        super(256);
        this.estudiante = null;
    }
}