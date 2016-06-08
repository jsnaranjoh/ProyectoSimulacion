/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private ArrayList<Componente> colaPiezas;

    public Cola() {
        colaPiezas = new ArrayList<>();
    }

    public void agregarComponenteCola(Componente componente) {
        colaPiezas.add(componente);
    }
    
    public int cantidadComponentesCola(){
        return colaPiezas.size();
    }

    public Boolean estadoCola() { //True: hay componentes False: esta vacia
        if (colaPiezas.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
    
    public void aumentarTiempoCola(){
        for(int i=0;i<colaPiezas.size();i++){
            colaPiezas.get(i).aumenteTiempoCola();
        }
    }
    
    public Componente tomarComponenteCola(){
        Componente componente= colaPiezas.get(0);
        colaPiezas.remove(0);
        return componente;
    }
}
