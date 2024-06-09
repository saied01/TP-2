package aed;

import java.util.ArrayList;

public class Materia {


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
