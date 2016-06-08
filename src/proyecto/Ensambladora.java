package proyecto;

import java.util.ArrayList;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Ensambladora {

    private int numeroEnsambladora;
    private int tiempoEnsamblaje = 0;
    private int cantPiezasEnsambladas = 0;
    private boolean estadoEnsambladora = true; //True: ensamblando False: inactiva
    private int tiempoInactividadEnsambladora = 0;
    private ArrayList<Integer> listaTiemposEnsamblaje = new ArrayList<>();
    private double promedioTiempoEnsamblaje = 0;

    public Ensambladora(int numeroEnsambladora) {
        this.numeroEnsambladora = numeroEnsambladora;
    }

    public void disminuyaTiempoEnsamblaje() {
        tiempoEnsamblaje--;
    }

    public void aumenteCantPiezasEnsambladas() {
        cantPiezasEnsambladas++;
    }

    public void aumenteTiempoInactividadEnsambladora() {
        tiempoInactividadEnsambladora++;
    }

    public int getTiempoEnsamblaje() {
        return tiempoEnsamblaje;
    }

    public boolean getEstadoEnsambladora() {
        return estadoEnsambladora;
    }

    public void setEstadoEnsambladora(boolean estadoEnsambladora) {
        this.estadoEnsambladora = estadoEnsambladora;
    }

    public void cambieEstadoEnsambladora() {
        if (estadoEnsambladora == true) {
            estadoEnsambladora = false;
        } else {
            estadoEnsambladora = true;
        }
    }

    public void setTiempoEnsamblaje(int tiempoEnsamblaje) {
        this.tiempoEnsamblaje = tiempoEnsamblaje;
    }

    public int getNumeroEnsambladora() {
        return numeroEnsambladora;
    }

    public int getCantPiezasEnsambladas() {
        return cantPiezasEnsambladas;
    }

    public void agregarTiempoEnsamblaje(int tiempoEns) {
        listaTiemposEnsamblaje.add(tiempoEns);
    }

    public int getTiempoInactividadEnsambladora() {
        return tiempoInactividadEnsambladora;
    }

    public Double calcularTiempoPromedioEnsamblaje() {
        int sumaTiempos = 0;
        for (int i = 0; i < listaTiemposEnsamblaje.size(); i++) {
            sumaTiempos += listaTiemposEnsamblaje.get(i);
        }
        return ((double) sumaTiempos / listaTiemposEnsamblaje.size());
    }
}
