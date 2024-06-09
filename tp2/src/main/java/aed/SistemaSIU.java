package aed;
import java.util.ArrayList;


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

    //O( sumaDe{c∈C}(|c| ∗ |Mc|)+ sumaDe{m∈M}(sumaDe{n∈Nm}(|n|)) + E )
    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        /*
        materiasEnCarreras\ (listaDeCarreras...) (nombreDeMismaMateria)
         */

        this.trieCarreras = new TrieCarreras();
        this.raiz = trieCarreras.raiz;
        this.infoMaterias = materiasEnCarreras;
        this.materias = new ArrayList<>(); // este Array se la tengo solo para debuggin
        this.estudiantes = new ArrayList<>(libretasUniversitarias.length); 

        for (int i = 0; i < libretasUniversitarias.length; i++){ // O(E)
            estudiantes.add(new Estudiante(libretasUniversitarias[i]));
        }


        // es donde se crean los tries de carreras y materias
        for (int i = 0; i < materiasEnCarreras.length; i++) { // O(sumaDe{c∈C}(|c| ∗ |Mc|)+ sumaDe{m∈M}(sumaDe{n∈Nm}(|n|))
            Materia materia = insertararCarrera(infoMaterias[i]);
            materias.add(materia);
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

    //O(|c| + |m|)
    public void inscribir(String estudiante, String carrera, String materia){
        Estudiante estudianteObtenido = ObtenerEstudianteOCrearlo(estudiante); // O(E) !!!
        NodoCarrera tailCarrera = trieCarreras.devolverHojaCarrera(carrera); // O(|c|)
        NodoMateria tailMateria = tailCarrera.trieMaterias.devolverHojaMateria(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.materia; // O(1)

        instanciaDeMateria.inscriptos += 1;
        instanciaDeMateria.estudiantes.insertarEstudiante(estudianteObtenido);
        /*TENGO LA DUDA SI INSERTAR ESTUDIANTE EXCEDE LA COMPLEJIDAD
        Que otra forma de encontrar al estudiante hay que no implica mas de O(1)?*/
    }

    public Estudiante ObtenerEstudianteOCrearlo(String estudiante){
        for (int i = 0; i < estudiantes.size(); i++){
            if (estudiantes.get(i).LU == estudiante){
                return estudiantes.get(i);
            }
        }
        return new Estudiante(estudiante);
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

    //O(|c| + |m|)
    public int inscriptos(String materia, String carrera){
        NodoCarrera tailCarrera = trieCarreras.devolverHojaCarrera(carrera); // O(|c|)
        NodoMateria tailMateria = tailCarrera.trieMaterias.devolverHojaMateria(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.materia; // O(1)
        return instanciaDeMateria.inscriptos;
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
