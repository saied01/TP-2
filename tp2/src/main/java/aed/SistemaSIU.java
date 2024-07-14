package aed;
import java.util.ArrayList;
import java.util.Arrays;


/* Invariante de Representacion:
- `trieCarreras` cumple con el invariante de representacion de `TrieCarreras`.
- `trieMaterias` cumple con el invariante de representacion de `TrieMaterias`.
- `raiz` es el nodo raiz de `TrieCarreras`.
- `infoMaterias` cumple con el invariante de representacion de `InfoMateria`.
- El largo de `estudiantes` es igual a la cantidad de libretas universitarias ingresadas.
- Los valores de `estudiantes` (Estudiante) cumplen con el invariante de representacion de `Estudiante`.
- El largo de `materias` es mayor o igual a 0.
- Los valores de `materias` (Materia) cumplen con el invariante de representacion de `Materia`.
* */


public class SistemaSIU {
    Trie<Trie<Materia>> trieCarreras;
    Trie<Estudiante> trieEstudiantes;
    Nodo<Trie<Materia>> raiz;
    InfoMateria[] infoMaterias;
    ArrayList<Estudiante> estudiantes;
    ArrayList<Materia> materias;

    public enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        /*
        O(∑{c∈C} |c| * |M_c| + ∑{m∈M} ∑_{n∈N_m} |n| + E)
        */

        this.trieCarreras = new Trie<Trie<Materia>>();
        this.trieEstudiantes = new Trie<Estudiante>();
        this.raiz = trieCarreras.raiz;
        this.infoMaterias = materiasEnCarreras;
        this.materias = new ArrayList<>();
        this.estudiantes = new ArrayList<>(libretasUniversitarias.length);

        for (int i = 0; i < libretasUniversitarias.length; i++){ // O(E)
            estudiantes.add(new Estudiante(libretasUniversitarias[i]));
        }


        // es donde se crean los tries de carreras y materias
        for (int i = 0; i < materiasEnCarreras.length; i++) {  //O(∑{c∈C} |c| * |M_c| + ∑{m∈M} ∑_{n∈N_m} |n|)
            Materia materia = insertararCarrera(infoMaterias[i]);
            materias.add(materia);
        }
    }

    private Materia insertararCarrera(InfoMateria infoM) { //esta mal escrito
        /*
        O(∑{(c, n)∈C} |c| + |n|)
        para cada par Carrera-Materia ∈ infoM cuesta el largo de sus letras sumadas

        - Modularizamos operaciones dentro de SistemaSIU para tener mas claridad a la hora de leer el codigo
        - Esta funcion recibe una tupla de la lista de los pares carrera-materias y crea los arboles
            para las carreras y materias de cada carrera respectivamente.

        Ejemplo de entrada
        infoM = (["Computación", "Datos"], ["Algoritmos", "Algoritmos2"])
         */
        Materia instanceDeMateria = null; //instancia de clase Materia que se comparte entre tries distintos (aliasing)

        for (int i = 0; i < infoM.longitud(); i++) {
            String carrera = infoM.obtenerCarrera(i); //carrera = "Computacion"
            String materia = infoM.obtenerNombresMateria(i); // materia = "Algoritmos"

            Nodo<Trie<Materia>> TrieMateriaDeCarrera = trieCarreras.insertarEnTrie(carrera); // O(|c|)
            if (TrieMateriaDeCarrera.dato == null) {
                TrieMateriaDeCarrera.dato = new Trie<Materia>();
            }
            Nodo<Materia> ultimoNodoMateria = TrieMateriaDeCarrera.dato.insertarEnTrie(materia); // O(|M_c|)

            if(instanceDeMateria == null){
                //si no existe la instancia de Materia la creo, aprovechando el aliasing
                instanceDeMateria = new Materia();

            }
            ultimoNodoMateria.dato = instanceDeMateria; // O(1)

            ultimoNodoMateria.dato.tailsDeSusCarreras.insertar(ultimoNodoMateria); // O(1)
        }

        return instanceDeMateria;
    }

    public void inscribir(String estudiante, String carrera, String materia){
        /*
         O(|c| + |m|)
         */
        Estudiante estudianteObtenido = ObtenerEstudianteOCrearlo(estudiante); // O(1)
        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Nodo<Materia> tailMateria = tailCarrera.dato.devolverHoja(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.dato; // O(1)

        instanciaDeMateria.inscriptos += 1;
        instanciaDeMateria.estudiantes.insertarEstudiante(estudianteObtenido); // O(1)
    }

    public Estudiante ObtenerEstudianteOCrearlo(String estudiante){
        /*
        O(|LU|) == O(1)
         */
        Nodo<Estudiante> est = trieEstudiantes.devolverHoja(estudiante); // O(1)
        if (est == null) {
            est = trieEstudiantes.insertarEnTrie(estudiante);
            est.dato = new Estudiante(estudiante);
            est.esFinalPalabra = true;
            est.dato.materiasCursando = 0;
            return est.dato;
        }
        return est.dato;
    }


    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        /*
         O(|c| + |m|)
         */

        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Nodo<Materia> tailMateria = tailCarrera.dato.devolverHoja(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.dato; // O(1)
        instanciaDeMateria.agregarCargoDocente(cargo.toString()); // O(1)


    }


    public int[] plantelDocente(String materia, String carrera){
        /*
         O(|c| + |m|)
         */
        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Nodo<Materia> tailMateria = tailCarrera.dato.devolverHoja(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.dato; // O(1)

        return  instanciaDeMateria.cargosdocentes; // O(1)
    }


    public void cerrarMateria(String materia, String carrera){
        /*
          O(|c| + ∑_{n∈N_m} |n| + E_m)
         */
        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Nodo<Materia> tailMateria = tailCarrera.dato.devolverHoja(materia);// O(|m|)
        Trie<Materia> trie = tailCarrera.dato;
        Trie<Materia> x = tailCarrera.dato;

        ListaEnlazadaPointersDeMaterias.Nodo instanciaDeMateria = tailMateria.dato.tailsDeSusCarreras.raiz; // O(1)

        instanciaDeMateria.pointerActual.dato.desincribirEstudiantes(); // O(E_m)


        trie.borrarTodasLasPalabras(instanciaDeMateria);

    }


    public int inscriptos(String materia, String carrera){
        /*
         O(|c| + |m|)
         */
        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Nodo<Materia> tailMateria = tailCarrera.dato.devolverHoja(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.dato; // O(1)
        return instanciaDeMateria.inscriptos;
    }

    //O(|c| + |m|)
    public boolean excedeCupo(String materia, String carrera){
        /*
         O(|c| + |m|)
         */

        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Nodo<Materia> tailMateria = tailCarrera.dato.devolverHoja(materia); // O(|m|)
        Materia instanciaDeMateria = tailMateria.dato; // O(1)
        return instanciaDeMateria.cupo < instanciaDeMateria.inscriptos; // O(1)
    }

    public String[] carreras(){
        /*
         O(∑_{c∈C} |c|)
         */
        ArrayList<String> todasLasMaterias;
        todasLasMaterias = new ArrayList<String>();
        return trieCarreras.devolverTodasLasPalabras(raiz, "", todasLasMaterias); //O(∑_{c∈C} |c|)
    }

    public String[] materias(String carrera){
        /*
        O(|c| + ∑_{m_c∈M_c} |m_c|)
         */
        ArrayList<String> todasLasMaterias;
        todasLasMaterias = new ArrayList<String>();
        Nodo<Trie<Materia>> tailCarrera = trieCarreras.devolverHoja(carrera); // O(|c|)
        Trie<Materia> materiasDeCarrera = tailCarrera.dato;

        //O(∑_{m_c∈M_c} |m_c|)
        return materiasDeCarrera.devolverTodasLasPalabras(materiasDeCarrera.raiz, "", todasLasMaterias);

    }


    //O(1)
    public int materiasInscriptas(String estudiante){
        /*
        O(1)
         */
        Estudiante est = ObtenerEstudianteOCrearlo(estudiante); // O(1)
        return est.materiasCursando; // O(1)
    }
}
