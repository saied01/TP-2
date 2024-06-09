package aed;

import java.util.ArrayList;

public class Materia {


    ArrayList<String[]> carrerasALasQuePertenece;
    int[] cargosdocentes;
    int cupo;
    int inscriptos;

    ListaEnlazadEstudiante estudiantes;


    public Materia() {
        this.carrerasALasQuePertenece = new ArrayList<>();
        this.cargosdocentes = new int[]{0,0,0,0};//AY2[3],AY1[2],JTP[1],PROF[0]
        this.cupo = 0;
        this.inscriptos = 0;
        this.estudiantes = new ListaEnlazadEstudiante();



    }


    public void agregarCargoDocente(String cargo) {
        switch (cargo) {
            case "AY2" -> cargosdocentes[3] += 1;
            case "AY1" -> cargosdocentes[2] += 1;
            case "JTP" -> cargosdocentes[1] += 1;
            case "PROF" -> cargosdocentes[0] += 1;
            default -> throw new UnsupportedOperationException("NO PUEDE PASAR ESTO");
        }
    }
}
