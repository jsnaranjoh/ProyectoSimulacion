package proyecto;

import java.util.ArrayList;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Horno {

    private int tiempoInactividadHorno = 0;
    private int tiempoCoccion = 0;
    private int tiempoEnfriamiento = 5;
    private boolean estadoHorno = false; //True: Cocinando False: Esperando
    private int cantidadPiezasHorneadas = 0;
    private ArrayList<Integer> listaTiemposHorneado = new ArrayList<>();

    public Horno() {
    }

    public void aumentarTiempoInactividadHorno() {
        tiempoInactividadHorno++;
    }

    public void aumentarCantidadPiezasHorneadas() {
        cantidadPiezasHorneadas++;
    }

    public void disminuirTiempoCoccion() {
        tiempoCoccion--;
    }

    public void disminuirTiempoEnfriamiento() {
        tiempoEnfriamiento--;
    }

    public void agregarTiempoHorneado(int tiempoHorneado) {
        listaTiemposHorneado.add(tiempoHorneado);
    }

    public void cambieEstadoHorno() {
        if (estadoHorno == true) {
            estadoHorno = false;
        } else {
            estadoHorno = true;
        }
    }

    public Boolean getEstadoHorno() {
        return estadoHorno;
    }

    public int getTiempoEnfriamiento() {
        return tiempoEnfriamiento;
    }

    public void setTiempoEnfriamiento(int tiempoEnfriamiento) {
        this.tiempoEnfriamiento = tiempoEnfriamiento;
    }

    public void setTiempoInactividadHorno(int tiempoInactividadHorno) {
        this.tiempoInactividadHorno = tiempoInactividadHorno;
    }

    public void setTiempoCoccion(int tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public int getTiempoCoccion() {
        return tiempoCoccion;
    }

    public int getCantidadPiezasHorneadas() {
        return cantidadPiezasHorneadas;
    }

    public int getTiempoInactividadHorno() {
        return tiempoInactividadHorno;
    }

    public double calcularTiempoPromedioHorneado() {
        int sumaTiempos = 0;
        for (int i = 0; i < listaTiemposHorneado.size(); i++) {
            sumaTiempos += listaTiemposHorneado.get(i);
        }
        return ((double) sumaTiempos / listaTiemposHorneado.size());
    }
}
