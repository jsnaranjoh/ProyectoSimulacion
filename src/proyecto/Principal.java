/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javax.swing.JOptionPane;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Simulacion simulacion= new Simulacion();
        
        int tiempoEjecucion=Integer.parseInt( JOptionPane.showInputDialog("Tiempo de Ejecucion (en minutos): "));
        int cantidadEnsambladoras= Integer.parseInt(JOptionPane.showInputDialog("Cantidad de ensambladoras: "));
        
        simulacion.iniciarEnsambladorasEjecucion(tiempoEjecucion,cantidadEnsambladoras);
        
        int conEnfriamiento=JOptionPane.showConfirmDialog(null,"Enfriamiento de 5 minutos?", "Seleccionar Enfriamiento", JOptionPane.YES_NO_OPTION);
        int conCola=JOptionPane.showConfirmDialog(null,"Usar una cola de 4 para antes de hornear?", "Seleccionar Cola", JOptionPane.YES_NO_OPTION);
        
        boolean enfriamiento;
        if (conEnfriamiento==JOptionPane.YES_OPTION)
        {
            enfriamiento=true;
        }
        else
            enfriamiento=false;
       
        if(conCola==JOptionPane.YES_OPTION)
        {
            simulacion.ejecutarSimulacionEscenario1(enfriamiento);
        }
        else
            simulacion.ejecutarSimulacionEscenario0(enfriamiento);
        
        simulacion.resultados();
    }
}
