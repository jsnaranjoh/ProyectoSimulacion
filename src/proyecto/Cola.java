package proyecto;

import java.util.ArrayList;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Cola {

    private ArrayList<Componente> colaComponentes;

    public Cola() {
        colaComponentes = new ArrayList<>();
    }

    public void agregarEnCola(Componente componente) {
        colaComponentes.add(componente);
    }
    
    public int cantidadEnCola(){
        return colaComponentes.size();
    }

    public boolean esColaVacia() {
        return colaComponentes.isEmpty();
        }
    
    public void aumentarTiempoCola(){
        for(int i=0; i<colaComponentes.size(); i++){
            colaComponentes.get(i).aumenteTiempoCola();
        }
    }
    
    public Componente tomarComponenteCola(){
        Componente componente = colaComponentes.get(0);
        colaComponentes.remove(0);
        return componente;
    }
}
