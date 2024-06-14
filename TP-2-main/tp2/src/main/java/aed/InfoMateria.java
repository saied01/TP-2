package aed;

public class InfoMateria{
    String[] carreras;
    String[] nombresEnCarreras;

    public InfoMateria(String[] carreras, String[] nombresEnCarreras){
        this.carreras = carreras;
        this.nombresEnCarreras = nombresEnCarreras;
    }

    public int longitud() {
        return carreras.length;
    }

    public String obtenerCarrera(int i) {
        if (i < 0 || i >= longitud()) {
            throw new IndexOutOfBoundsException("indice fuera de rango.");
        }
        return carreras[i];
    }

    public String obtenerNombresMateria(int i) {
        if (i < 0 || i >= longitud()) {
            throw new IndexOutOfBoundsException("indice fuera de rango.");
        }
        return nombresEnCarreras[i];
    }

}