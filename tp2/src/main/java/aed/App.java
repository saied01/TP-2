import java.util.Objects;
import aed.InfoMateria;

public static void main() {
    Probando();
}

public static void Probando() {
    String[] carreras = {"Ingeniería", "Ciencias", "Artes"};
    String[] nombres = {"Matemáticas", "Física", "Historia del Arte"};

    InfoMateria infoMateria = new InfoMateria(carreras, nombres);

    // Probar longitud
    System.out.println("Longitud: " + infoMateria.longitud());

    // Probar obtenerCarreras
    for (int i = 0; i < infoMateria.longitud(); i++) {
        System.out.println("Carrera " + i + ": " + infoMateria.obtenerCarrera(i));
    }
}