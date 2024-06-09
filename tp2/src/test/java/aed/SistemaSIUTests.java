package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class SistemaSIUTests {
    String[] estudiantes;
    InfoMateria[] infoMaterias;


    @BeforeEach
    void init() {
        // Este método reinicia los valores de las variables antes de cada test
        // Pedimos disculpas a las carreras no representadas
        infoMaterias = new InfoMateria[] {
            new InfoMateria(new String[] {"Computación", "Datos"}, new String[] {"Intro a Programación", "Algoritmos1"}),
            new InfoMateria(new String[] {"Computación", "Datos"}, new String[] {"Algoritmos", "Algoritmos2"}),
            new InfoMateria(new String[] {"Computación", "Datos"}, new String[] {"Técnicas de Diseño de Algoritmos", "Algoritmos3"}),
            new InfoMateria(new String[] {"Computación", "Datos", "Físicas", "Químicas", "Matemáticas"}, new String[] {"Análisis I", "Análisis I", "Matemática 1", "Análisis Matemático I", "Análisis I"}),
            new InfoMateria(new String[] {"Biológicas", "Químicas"}, new String[] {"Química General e Inorgánica 1", "Química General"}),
            new InfoMateria(new String[] {"Matemáticas", "Datos", "Físicas", "Químicas"}, new String[] {"Análisis II", "Análisis II", "Matemática 3", "Análisis Matemático II"}),
        };
        estudiantes = new String[] {"123/23", "321/24", "122/99", "314/81", "391/18", "478/19", "942/20", "291/18", "382/19", "892/22", "658/13", "217/12", "371/11", "294/20"};
    }

    @Test
    void nuevo_sistema() {
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        TrieCarreras x = sistema.trieCarreras;//
        ArrayList<Materia> y = sistema.materias;//
    }

    void realizar_inscripciones(SistemaSIU sistema){
        sistema.inscribir(estudiantes[0], "Datos", "Algoritmos1");
        sistema.inscribir(estudiantes[0], "Datos", "Análisis I");
        sistema.inscribir(estudiantes[1], "Computación", "Intro a Programación");
        sistema.inscribir(estudiantes[1], "Computación", "Análisis I");
        sistema.inscribir(estudiantes[2], "Biológicas", "Química General e Inorgánica 1");
        sistema.inscribir(estudiantes[2], "Computación", "Técnicas de Diseño de Algoritmos");
        sistema.inscribir(estudiantes[3], "Físicas", "Matemática 1");
        sistema.inscribir(estudiantes[4], "Químicas", "Análisis Matemático I");
        sistema.inscribir(estudiantes[4], "Químicas", "Química General");
        sistema.inscribir(estudiantes[5], "Matemáticas", "Análisis I");
        sistema.inscribir(estudiantes[6], "Datos", "Análisis II");
        sistema.inscribir(estudiantes[6], "Químicas", "Química General");
        sistema.inscribir(estudiantes[7], "Computación", "Técnicas de Diseño de Algoritmos");
        sistema.inscribir(estudiantes[8], "Físicas", "Matemática 3");
        sistema.inscribir(estudiantes[8], "Biológicas", "Química General e Inorgánica 1");
        sistema.inscribir(estudiantes[8], "Computación", "Intro a Programación");
        sistema.inscribir(estudiantes[9], "Químicas", "Análisis Matemático II");
        sistema.inscribir(estudiantes[9], "Biológicas", "Química General e Inorgánica 1");
        sistema.inscribir(estudiantes[9], "Computación", "Algoritmos");
        sistema.inscribir(estudiantes[10], "Datos", "Algoritmos2");
        sistema.inscribir(estudiantes[11], "Datos", "Algoritmos3");
        sistema.inscribir(estudiantes[11], "Matemáticas", "Análisis II");
    }

    @Test
    void inscribir_estudiantes(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        ArrayList<Estudiante> x = sistema.estudiantes;//
    }

    @Test
    void verificar_inscriptos(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        assertEquals(3, sistema.inscriptos("Algoritmos1", "Datos"));
        assertEquals(3, sistema.inscriptos("Intro a Programación", "Computación"));
        assertEquals(5, sistema.inscriptos("Análisis I", "Computación"));
        assertEquals(5, sistema.inscriptos("Análisis I", "Datos"));
        assertEquals(5, sistema.inscriptos("Matemática 1", "Físicas"));
        assertEquals(5, sistema.inscriptos("Análisis Matemático I", "Químicas"));
        assertEquals(5, sistema.inscriptos("Análisis I", "Matemáticas"));
        assertEquals(5, sistema.inscriptos("Química General e Inorgánica 1", "Biológicas"));
        assertEquals(5, sistema.inscriptos("Química General", "Químicas"));
        assertEquals(2, sistema.inscriptos("Algoritmos2", "Datos"));
        assertEquals(2, sistema.inscriptos("Algoritmos", "Computación"));
        assertEquals(3, sistema.inscriptos("Técnicas de Diseño de Algoritmos", "Computación"));
        assertEquals(3, sistema.inscriptos("Algoritmos3", "Datos"));
        assertEquals(4, sistema.inscriptos("Análisis II", "Matemáticas"));
        assertEquals(4, sistema.inscriptos("Análisis Matemático II", "Químicas"));
        assertEquals(4, sistema.inscriptos("Análisis II", "Datos"));
        assertEquals(4, sistema.inscriptos("Matemática 3", "Físicas"));
    }

    void cargar_docentes(SistemaSIU sistema, int ay2, int ay1, int jtps, int profes){
        for (int i = 0; i < ay2; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Datos", "Algoritmos1");
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Químicas", "Análisis Matemático I");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Computación", "Algoritmos");
                sistema.agregarDocente(SistemaSIU.CargoDocente.AY2, "Datos", "Algoritmos3");
            }
        }

        for (int i = 0; i < ay1; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Datos", "Algoritmos2");
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Físicas", "Matemática 1");
            sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Computación", "Técnicas de Diseño de Algoritmos");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Computación", "Intro a Programación");
            }
        }

        for (int i = 0; i < jtps; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Datos", "Algoritmos3");
            sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Matemáticas", "Análisis I");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Computación", "Técnicas de Diseño de Algoritmos");
                sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Computación", "Algoritmos");
                sistema.agregarDocente(SistemaSIU.CargoDocente.JTP, "Computación", "Intro a Programación");
            }
        }

        for (int i = 0; i < profes; i++){
            sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Computación", "Análisis I");
            sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Computación", "Intro a Programación");
            if (i % 2 == 0){
                sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Químicas", "Química General");
                sistema.agregarDocente(SistemaSIU.CargoDocente.PROF, "Datos", "Algoritmos2");
            }
        }
    }

    @Test
    void agregar_docentes(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        int ay2 = 20, ay1 = 10, jtps = 5, profes = 2;
        cargar_docentes(sistema, ay2, ay1, jtps, profes);

        int[] plantelAlgo = sistema.plantelDocente("Algoritmos1", "Datos");
        assertArrayEquals(plantelAlgo, new int[] {2, 3, 5, 20});
        int[] plantelIP = sistema.plantelDocente("Intro a Programación", "Computación");
        assertArrayEquals(plantelIP, plantelAlgo);
        int[] plantelAlgo2 = sistema.plantelDocente("Algoritmos2", "Datos");
        assertArrayEquals(plantelAlgo2, new int[] {1, 3, 10, 10});
        plantelAlgo = sistema.plantelDocente("Algoritmos", "Computación");
        assertArrayEquals(plantelAlgo, plantelAlgo2);
        int[] plantelTDA = sistema.plantelDocente("Técnicas de Diseño de Algoritmos", "Computación");
        assertArrayEquals(plantelTDA, new int[] {0, 8, 10, 10});
        int[] plantelAlgo3 = sistema.plantelDocente("Algoritmos3", "Datos");
        assertArrayEquals(plantelAlgo3, plantelTDA);
        int[] plantelAnI = sistema.plantelDocente("Análisis I", "Datos");
        assertArrayEquals(plantelAnI, new int[] {2, 5, 10, 20});
        int[] plantelMat1 = sistema.plantelDocente("Matemática 1", "Físicas");
        assertArrayEquals(plantelMat1, plantelAnI);
        int[] plantelAnMatI = sistema.plantelDocente("Análisis Matemático I", "Químicas");
        assertArrayEquals(plantelAnMatI, plantelAnI);
        plantelAnI = sistema.plantelDocente("Análisis I", "Computación");
        assertArrayEquals(plantelAnI, plantelAnMatI);
        int[] plantelQuimG = sistema.plantelDocente("Química General", "Químicas");
        assertArrayEquals(plantelQuimG, new int[] {1, 0, 0, 0});
        int[] plantelQuimGIno = sistema.plantelDocente("Química General e Inorgánica 1", "Biológicas");
        assertArrayEquals(plantelQuimGIno, plantelQuimG);
        int[] plantelAnMatII = sistema.plantelDocente("Análisis Matemático II", "Químicas");
        assertArrayEquals(plantelAnMatII, new int[] {0, 0, 0, 0});
    }

    @Test
    void nadie_excede_cupo(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        assertFalse(sistema.excedeCupo("Algoritmos1", "Datos"));
        assertFalse(sistema.excedeCupo("Intro a Programación", "Computación"));
        assertFalse(sistema.excedeCupo("Análisis I", "Computación"));
        assertFalse(sistema.excedeCupo("Análisis I", "Datos"));
        assertFalse(sistema.excedeCupo("Matemática 1", "Físicas"));
        assertFalse(sistema.excedeCupo("Análisis Matemático I", "Químicas"));
        assertFalse(sistema.excedeCupo("Análisis I", "Matemáticas"));
        assertFalse(sistema.excedeCupo("Química General e Inorgánica 1", "Biológicas"));
        assertFalse(sistema.excedeCupo("Química General", "Químicas"));
        assertFalse(sistema.excedeCupo("Algoritmos2", "Datos"));
        assertFalse(sistema.excedeCupo("Análisis II", "Datos"));
    }

    @Test
    void colapsar_facultad(){
        int nuevos_estudiantes = 201;
        String[] nuevos_inscriptos = new String[nuevos_estudiantes];
        for (int i = 0; i < nuevos_estudiantes; i++){
            nuevos_inscriptos[i] = String.format("%d/%d", i, i % 20 + 10);
        }
        SistemaSIU sistema = new SistemaSIU(infoMaterias, nuevos_inscriptos);
        cargar_docentes(sistema, 20, 10, 5, 2);
        for (int i = 0; i < nuevos_estudiantes - 1; i++){
            if (i < 10){
                sistema.inscribir(nuevos_inscriptos[i], "Químicas", "Química General");
            }
            if (i < 102){
                sistema.inscribir(nuevos_inscriptos[i], "Datos", "Algoritmos1");
                sistema.inscribir(nuevos_inscriptos[i], "Datos", "Algoritmos3");
            }
            sistema.inscribir(nuevos_inscriptos[i], "Computación", "Algoritmos");
        }
        assertEquals(10, sistema.inscriptos("Química General e Inorgánica 1", "Biológicas"));
        assertEquals(102, sistema.inscriptos("Algoritmos1", "Datos"));
        assertEquals(102, sistema.inscriptos("Técnicas de Diseño de Algoritmos", "Computación"));
        assertEquals(200, sistema.inscriptos("Algoritmos", "Computación"));
        assertEquals(0, sistema.inscriptos("Análisis I", "Datos"));
        assertTrue(sistema.excedeCupo("Intro a Programación", "Computación"));
        sistema.agregarDocente(SistemaSIU.CargoDocente.AY1, "Computación", "Intro a Programación");
        assertFalse(sistema.excedeCupo("Algoritmos1", "Datos"));
        assertFalse(sistema.excedeCupo("Algoritmos2", "Datos"));
        sistema.inscribir(nuevos_inscriptos[200], "Computación", "Algoritmos");
        assertTrue(sistema.excedeCupo("Algoritmos2", "Datos"));
        assertTrue(sistema.excedeCupo("Técnicas de Diseño de Algoritmos", "Computación"));
        assertTrue(sistema.excedeCupo("Química General e Inorgánica 1", "Biológicas"));
        assertFalse(sistema.excedeCupo("Análisis II", "Matemáticas"));
    }

    @Test
    void listado_carreras(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        String[] carreras = new String[] {"Biológicas", "Físicas", "Matemáticas", "Químicas", "Datos", "Computación"};
        assertArrayEquals(carreras, sistema.carreras());
    }

    @Test
    void listado_materias(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        String[] materiasBio = new String[] {"Química General e Inorgánica 1"};
        assertArrayEquals(materiasBio, sistema.materias("Biológicas"));
        String[] materiasCompu = new String[] {"Algoritmos", "Análisis I", "Intro a Programación", "Técnicas de Diseño de Algoritmos"};
        assertArrayEquals(materiasCompu, sistema.materias("Computación"));
        String[] materiasDatos = new String[] {"Algoritmos1", "Algoritmos2", "Algoritmos3", "Análisis I", "Análisis II"};
        assertArrayEquals(materiasDatos, sistema.materias("Datos"));
        String[] materiasFisica = new String[] {"Matemática 1", "Matemática 3"};
        assertArrayEquals(materiasFisica, sistema.materias("Físicas"));
        String[] materiasMate = new String[] {"Análisis I", "Análisis II"};
        assertArrayEquals(materiasMate, sistema.materias("Matemáticas"));
        String[] materiasQuimica = new String[] {"Análisis Matemático I", "Análisis Matemático II", "Química General"};
        assertArrayEquals(materiasQuimica, sistema.materias("Químicas"));
    }

    @Test
    void materias_inscriptas(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        assertEquals(2, sistema.materiasInscriptas(estudiantes[0]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[1]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[2]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[3]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[4]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[5]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[6]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[7]));
        assertEquals(3, sistema.materiasInscriptas(estudiantes[8]));
        assertEquals(3, sistema.materiasInscriptas(estudiantes[9]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[10]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[11]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[12]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[13]));
    }

    @Test
    void cerrar_materia(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);

        sistema.cerrarMateria("Algoritmos1", "Datos");
        assertEquals(1, sistema.materiasInscriptas(estudiantes[0]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[1]));
        String[] materiasCompu = new String[] {"Algoritmos", "Análisis I", "Técnicas de Diseño de Algoritmos"};
        assertArrayEquals(materiasCompu, sistema.materias("Computación"));
        String[] materiasDatos = new String[] {"Algoritmos2", "Algoritmos3", "Análisis I", "Análisis II"};
        assertArrayEquals(materiasDatos, sistema.materias("Datos"));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[10]));
        assertEquals(2, sistema.materiasInscriptas(estudiantes[11]));
    }

    @Test
    void cerrar_facultad(){
        SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);
        realizar_inscripciones(sistema);
        cargar_docentes(sistema, 20, 10, 5, 2);

        sistema.cerrarMateria("Intro a Programación", "Computación");
        sistema.cerrarMateria("Análisis Matemático I", "Químicas");
        String[] materiasCompu = new String[] {"Algoritmos", "Técnicas de Diseño de Algoritmos"};
        assertArrayEquals(materiasCompu, sistema.materias("Computación"));
        String[] materiasMate = new String[] {"Análisis II"};
        assertArrayEquals(materiasMate, sistema.materias("Matemáticas"));
        String[] materiasQuimica = new String[] {"Análisis Matemático II", "Química General"};
        assertArrayEquals(materiasQuimica, sistema.materias("Químicas"));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[0]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[1]));

        sistema.cerrarMateria("Matemática 3", "Físicas");
        String[] sinMaterias = new String[] {};
        assertArrayEquals(sinMaterias, sistema.materias("Físicas"));
        assertArrayEquals(sinMaterias, sistema.materias("Matemáticas"));
        String[] materiasBio = new String[] {"Química General e Inorgánica 1"};
        assertArrayEquals(materiasBio, sistema.materias("Biológicas"));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[8]));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[11]));
        assertEquals(0, sistema.materiasInscriptas(estudiantes[5]));

        sistema.cerrarMateria("Química General e Inorgánica 1", "Biológicas");
        assertArrayEquals(sinMaterias, sistema.materias("Biológicas"));
        assertArrayEquals(sinMaterias, sistema.materias("Químicas"));
        assertEquals(1, sistema.materiasInscriptas(estudiantes[9]));

        sistema.cerrarMateria("Algoritmos", "Computación");
        assertEquals(1, sistema.materiasInscriptas(estudiantes[7]));
        sistema.cerrarMateria("Algoritmos3", "Datos");
        assertArrayEquals(sinMaterias, sistema.materias("Computación"));
        assertArrayEquals(sinMaterias, sistema.materias("Datos"));

        for(int i = 0; i < estudiantes.length; i++){
            assertEquals(0, sistema.materiasInscriptas(estudiantes[i]));
        }
        String[] carreras = new String[] {"Biológicas", "Físicas", "Matemáticas", "Químicas", "Datos", "Computación"};
        assertArrayEquals(carreras, sistema.carreras());
    }
}
