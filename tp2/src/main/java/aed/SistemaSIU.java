package aed;

class Nodo {
    Nodo[] hijo;
    boolean esFinalPalabra;

    Nodo()
    {
        hijo = new Nodo[256];
        esFinalPalabra = false;
    }
}

public class SistemaSIU {
    private Nodo raiz;
    private InfoMateria[] infoMaterias;
    private String[] estudiantes;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    void trie() {
        raiz = new Nodo();
    }

    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        this.infoMaterias = materiasEnCarreras;
        this.estudiantes = libretasUniversitarias;
        for (int i = 0; i < materiasEnCarreras.length; i++) {
            insertarCarrera(infoMaterias[i]);
        }
    }

    private void insertarCarrera(InfoMateria infoM) {
        Nodo actual = raiz;
        for (int i = 0; i < infoM.longitud(); i++) {
            String carrera = infoM.obtenerCarrera(i);
            String materia = infoM.obtenerNombresMateria(i);
            for (int j = 0; j < carrera.length(); j++) {
                int indice = (int) carrera.charAt(j) - (int) 'a';
                if (actual.hijo[indice] == null) {
                    actual.hijo[indice] = new Nodo();
                }
                actual = actual.hijo[indice];
            }
            actual.esFinalPalabra = true;
            insertarMateria(actual, materia);
        }
    }

    private void insertarMateria(Nodo actual, String materia) {
        for (int i = 0; i < materia.length(); i++) {
            int indice = (int) materia.charAt(i) - (int) 'a';
            if (actual.hijo[indice] == null) {
                actual.hijo[indice] = new Nodo();
            }
            actual = actual.hijo[indice];
        }
        actual.esFinalPalabra = true;
    }

    public void inscribir(String estudiante, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");
    }
}
