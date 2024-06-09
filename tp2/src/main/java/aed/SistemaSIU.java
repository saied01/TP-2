package aed;
import java.util.ArrayList;

class Estudiante{
    String LU;
    int materiasCursando;
    String carreraALaQuePertence;

    public Estudiante(String LU){
        this.LU = LU;
        this.materiasCursando = 0;
        this.carreraALaQuePertence = null;
    }
}

class Materia {


    ArrayList<String[]> carrerasALasQuePertenece; // Single list of strings for carreras
    ArrayList<String> docentes; // List of Docente objects
    int[] numeroDocentes; // Array of integers for number of docentes per carrera
    int cupo; // Maximum capacity of the materia

    ListaEnlazadEstudiante estudiantes;


    Materia() {
        this.carrerasALasQuePertenece = new ArrayList<>();
        this.docentes = new ArrayList<>();
        this.numeroDocentes = new int[0]; // Initialize empty int array
        this.cupo = 0;
        this.estudiantes = new ListaEnlazadEstudiante();
    }




}

class NodoEstudiante{
    NodoEstudiante siguiente;
    Estudiante estudiante;

    public NodoEstudiante(Estudiante estudiante){
        this.siguiente = null;
        this.estudiante = estudiante;
    }
}

class NodoMateria {
    /*
    nodo con modificacion del 'tail' con instancia de clase Materia
     */
    boolean esFinalPalabra;
    NodoMateria[] hijo;
    Materia materia;

    String letra;

    public NodoMateria() {
        this.letra = null;
        this.hijo = new NodoMateria[1000];
        this.esFinalPalabra = false;
        this.materia = null;

    }
}

class NodoCarrera {
    /*
    nodo con modificacion del 'tail' con instancia de trie de Materias de carrera
     */
    boolean esFinalPalabra;
    NodoCarrera[] hijo;
    TrieMaterias trieMaterias;
    String letra;
    public NodoCarrera() {
        this.letra = null;
        this.hijo = new NodoCarrera[1000];
        this.esFinalPalabra = false;
        this.trieMaterias = null;
    }
}

class ListaEnlazadEstudiante{
    NodoEstudiante raiz;

    public ListaEnlazadEstudiante(){
        this.raiz = null;
    }

    public void insertarEstudiante(Estudiante estudiante){
        if (raiz == null){
            raiz = new NodoEstudiante(estudiante);
        }
        else {
            NodoEstudiante actual = raiz;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = new NodoEstudiante(estudiante);
        }
    }



}

class TrieMaterias {

    NodoMateria raiz;

    public TrieMaterias() {
        raiz = new NodoMateria();
    }

    public NodoMateria insertarEnTrie(String word) {
        int n = word.length();

        NodoMateria head = raiz;

        for (int i = 0 ; i < n ; i++) {

            int index = word.charAt(i) ;// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) {
                head.hijo[index] = new NodoMateria();
            }

            head = head.hijo[index];
            head.letra = String.valueOf(word.charAt(i));
        }

        head.esFinalPalabra = true;
        return head;
    }

    public boolean buscar(String word) {

        int n = word.length();

        NodoMateria head = raiz;
        for (int i = 0 ; i < n ; i++) {
            int index = word.charAt(i) ;// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) return false;
            head = head.hijo[index];
        }

        return head.esFinalPalabra;
    }

}

class TrieCarreras {

    NodoCarrera raiz;

    public TrieCarreras() {
        raiz = new NodoCarrera();
    }

    public TrieMaterias insertarEnTrie(String word) {
        int n = word.length();

        NodoCarrera head = raiz;
        

        for (int i = 0 ; i < n ; i++) {

            int index = word.charAt(i) ;//  - 'a'; <- esto se bugee, resolver despues

            if (head.hijo[index] == null) {
                head.hijo[index] = new NodoCarrera();
            }

            head = head.hijo[index];
            head.letra = String.valueOf(word.charAt(i));
        }

        head.esFinalPalabra = true;
        if (head.trieMaterias == null){
            head.trieMaterias = new TrieMaterias();
        }
        return head.trieMaterias;
    }

    public TrieMaterias buscar(String word) {

        int n = word.length();

        NodoCarrera head = raiz;
        for (int i = 0 ; i < n ; i++) {
            int index = word.charAt(i) ;// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) return null;
            head = head.hijo[index];
        }

        return head.trieMaterias;
    }

}
public class SistemaSIU {
    TrieCarreras trieCarreras;
    NodoCarrera raiz;
    InfoMateria[] infoMaterias;
    ArrayList<Estudiante> estudiantes;

    ArrayList<Materia> materias;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }


    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        /*
        materiasEnCarreras\ (listaDeCarreras...) (nombreDeMismaMateria)
         */

        this.trieCarreras = new TrieCarreras();
        this.raiz = trieCarreras.raiz;
        this.infoMaterias = materiasEnCarreras;
        this.materias = new ArrayList<>(); // este Array se la tengo solo para debuggin

        // Hay que ver si esta bien IMPLEMENTADO ESTO
        // creo una lista de instancia de estudiantes para despues
        // agregarlos a cada materia que se usa por unica vez
        estudiantes = new ArrayList<>(libretasUniversitarias.length);
        for (int i = 0; i < libretasUniversitarias.length; i++){ // creo una lista con objetos de estudiantes
            estudiantes.add(new Estudiante(libretasUniversitarias[i]));
        }


        // es donde se crean los tries de carreras y materias
        for (int i = 0; i < materiasEnCarreras.length; i++) {
            Materia materia = insertararCarrera(infoMaterias[i]);
            materia.cupo = estudiantes.size();
            materias.add(materia);

            //agrego a la lista de estudiante de cada materia
            for (int e = 0; e < estudiantes.size(); e++) {
                materia.estudiantes.insertarEstudiante(estudiantes.get(e));
                estudiantes.get(e).materiasCursando += 1;
            }
        }
    }

    private Materia insertararCarrera(InfoMateria infoM) {
        /*
        Ejemplo de imput
        infoM = (["Computación", "Datos"], ["Algoritmos", "Algoritmos2"])
        carrera = "Computacion"
        materia = "Algoritmos"
         */
        Materia instanceDeMateria = null; //instancia de la clase Materia que se comparte entre arboles (aliasing)
        
        for (int i = 0; i < infoM.longitud(); i++) {
            String carrera = infoM.obtenerCarrera(i);
            String materia = infoM.obtenerNombresMateria(i);

            TrieMaterias TrieMateriaDeCarrera = trieCarreras.insertarEnTrie(carrera); //arma y devuelve el nodo final
                                                                                    // del arbol de Carrera
            NodoMateria ultimoNodoMateria = TrieMateriaDeCarrera.insertarEnTrie(materia); //arma y devuelve el nodo final
                                                                            // del arbol de Materia

            if(instanceDeMateria == null){ // si no esta agrego el pointer a la nueva isntancia de Materia
                instanceDeMateria = new Materia();
                ultimoNodoMateria.materia = instanceDeMateria;
            }else { //si ya esta lo hago apuntar asi ahi
                ultimoNodoMateria.materia = instanceDeMateria;
            }

            String[] tuplaCarreraMateria = new String[] {carrera, materia};

            instanceDeMateria.carrerasALasQuePertenece.add(tuplaCarreraMateria);

        }

        return instanceDeMateria;
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
