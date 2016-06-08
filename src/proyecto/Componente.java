package proyecto;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Componente {
    
    private int numeroComponente;
    private int tiempoCola=0;

    public Componente(int numeroComponente) {
        this.numeroComponente = numeroComponente;
    }
    
    public void aumenteTiempoCola(){
        tiempoCola++;
    }

    public int getNumeroComponente() {
        return numeroComponente;
    }
    
}
