package aed;

import java.util.ArrayList;

public class Materia {


    ArrayList<String[]> carrerasALasQuePertenece;
    ArrayList<String> docentes;
    ArrayList<Integer> numeroDocentes;
    int cupo;
    int inscriptos;

    ListaEnlazadEstudiante estudiantes;


    Materia() {
        this.carrerasALasQuePertenece = new ArrayList<>();
        this.docentes = new ArrayList<>(4);
        this.numeroDocentes = new ArrayList<>(4);
        this.cupo = 0;
        this.inscriptos = 0;
        this.estudiantes = new ListaEnlazadEstudiante();
    }


}
