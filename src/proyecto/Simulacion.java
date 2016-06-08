/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Simulacion {

    //Variables para generar tiempo aleatorios
    private ArrayList<Integer> listaX, listaTiemposEnsamblajes, listaTiemposHorneado;
    private ArrayList<Double> listaRnEnsamblaje, listaRnHorneado;
    private int cantidadDatos = 5000, contadorHorneado = 0, contadorEnsamblaje = 0;
    
    //Variables del sistema
    private int tiempoEjecucion , tiempoGeneral = 0, totalPiezasEnsambladas = 0;
    private ArrayList<Componente> listaComponentesHorneados, listaComponentesEnsambladorasBloqueadas;
    private Componente componenteAuxiliar,componenteAuxiliar1, componenteHorneandose;
    private ArrayList<Ensambladora> listaEnsambladoras, listaEnsambladorasBloqueadas;
    private Ensambladora ensambladoraAuxiliar;
    private int cantidadEnsambladoras ;
    private Horno horno;
    private Cola colaComponentes;
    private Boolean conEnfriamiento;
    
    //Variables para mostrar
    private String salida="-";
    JTextArea salidaT=new JTextArea(10,50);
    
    
    
    
    public Simulacion() {
    
        //Variables generador tiempos aleatorios
        listaX = new ArrayList<>();
        listaRnEnsamblaje = new ArrayList<>();
        listaRnHorneado = new ArrayList<>();
        listaTiemposEnsamblajes = new ArrayList<>();
        listaTiemposHorneado = new ArrayList<>();
        generarAleatorioEnsamblaje();

        //Variables simulacion
        horno = new Horno();
        colaComponentes = new Cola();
        listaComponentesHorneados = new ArrayList<>();
        listaEnsambladoras = new ArrayList<>();
        listaEnsambladorasBloqueadas = new ArrayList<>();
        listaComponentesEnsambladorasBloqueadas = new ArrayList<>();

    }
    
   public void iniciarEnsambladorasEjecucion(int tiempo, int cantidad)
    {
        tiempoEjecucion=tiempo;
        cantidadEnsambladoras=cantidad;
        
        for (int i = 0; i < cantidadEnsambladoras; i++) {
            ensambladoraAuxiliar = new Ensambladora(i);
            ensambladoraAuxiliar.setTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje));
            ensambladoraAuxiliar.agregarTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje));
            listaEnsambladoras.add(ensambladoraAuxiliar);
            contadorEnsamblaje++;
        }
    }

    public void ejecutarSimulacionEscenario0(boolean enfriamiento) {
        conEnfriamiento=enfriamiento;

        do {
            System.out.println("\nTiempoEjecucion: " + tiempoGeneral);
            salida+="\n"+"\nTiempoEjecucion: " + tiempoGeneral;
            //Proceso Ensambladoras
            for (int i = 0; i < listaEnsambladoras.size(); i++) {
                ensambladoraAuxiliar = listaEnsambladoras.get(i);
                System.out.println("ensambladora: " + ensambladoraAuxiliar.getNumeroEnsambladora() + " tiempoEnsamblaje: " + ensambladoraAuxiliar.getTiempoEnsamblaje() + " cantidadComponentesEnsamblados: " + ensambladoraAuxiliar.getCantPiezasEnsambladas());
                salida+="\n"+"ensambladora: " + ensambladoraAuxiliar.getNumeroEnsambladora() + " tiempoEnsamblaje: " + ensambladoraAuxiliar.getTiempoEnsamblaje() + " cantidadComponentesEnsamblados: " + ensambladoraAuxiliar.getCantPiezasEnsambladas();
                
                //Terminó de ensamblar?
                if (ensambladoraAuxiliar.getTiempoEnsamblaje() == 0) {
                    ensambladoraAuxiliar.setTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje));
                    ensambladoraAuxiliar.agregarTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje));
                    contadorEnsamblaje++;
                    ensambladoraAuxiliar.aumenteCantPiezasEnsambladas();
                    eventoComponenteEnsamblado();
                } else {
                    ensambladoraAuxiliar.disminuyaTiempoEnsamblaje();
                }
            }

            //Proceso del horno
            if (horno.getEstadoHorno() == true) {
                System.out.println("horno cocinando: " + horno.getTiempoCoccion());
                salida+="\nhorno cocinando: " + horno.getTiempoCoccion();
                if (horno.getTiempoCoccion() == 0) {
                    if (conEnfriamiento == false) {
                        eventoSaleHorno();
                    } else {
                        if (horno.getTiempoEnfriamiento() == 0) {
                            horno.setTiempoEnfriamiento(5);
                            eventoSaleHorno();
                        } else {
                            horno.disminuirTiempoEnfriamiento();
                        }
                    }
                } else {
                    horno.disminuirTiempoCoccion();
                }
            } else {
                horno.aumentarTiempoInactividadHorno();
                System.out.println("horno inactivo: " + horno.getTiempoInactividadHorno());
                salida+="\nhorno inactivo: " + horno.getTiempoInactividadHorno();
            }

            //Proceso de la cola
            if (colaComponentes.estadoCola() == true) {
                colaComponentes.aumentarTiempoCola();
            }

            tiempoGeneral++;
        } while (tiempoGeneral < tiempoEjecucion);

        //Promedios Finales
        double promEnsamblaje = 0;
        for (int i = 0; i < listaEnsambladoras.size(); i++) {
            promEnsamblaje += listaEnsambladoras.get(i).calcularTiempoPromedioEnsamblaje();
        }
        System.out.println("\npromedioTiempoEnsamblaje: " + redondear(promEnsamblaje / listaEnsambladoras.size()));
        salida+="\npromedioTiempoEnsamblaje: " + redondear(promEnsamblaje / listaEnsambladoras.size());
        System.out.println("promedioTiempoHorneado: " + redondear(horno.calcularTiempoPromedioHorneado()));
        salida+="\n"+"promedioTiempoHorneado: " + redondear(horno.calcularTiempoPromedioHorneado());
        System.out.println("cantPiezasHorneadas: " + horno.getCantidadPiezasHorneadas());
        salida+="\n"+"cantPiezasHorneadas: " + horno.getCantidadPiezasHorneadas();
        System.out.println("cantPiezasHorneadas: " + horno.getCantidadPiezasHorneadas());
        salida+="\n"+"cantPiezasHorneadas: " + horno.getCantidadPiezasHorneadas();
        System.out.println("tamañoCola: " + colaComponentes.cantidadComponentesCola());
        salida+="\n"+"tamañoCola: " + colaComponentes.cantidadComponentesCola();
        System.out.println("tiempoInactivoHorno: " + horno.getTiempoInactividadHorno());
        salida+="\n"+"tiempoInactivoHorno: " + horno.getTiempoInactividadHorno();

    }

    public void ejecutarSimulacionEscenario1(boolean enfriamiento) {
        conEnfriamiento=enfriamiento;

        do {
            System.out.println("\nTiempoEjecucion: " + tiempoGeneral);
            salida+="\nTiempoEjecucion: " + tiempoGeneral;
            //Proceso Ensambladoras
            for (int i = 0; i < listaEnsambladoras.size(); i++) {
                ensambladoraAuxiliar = listaEnsambladoras.get(i);
                System.out.println("ensambladora: " + ensambladoraAuxiliar.getNumeroEnsambladora() + " tiempoEnsamblaje: " + ensambladoraAuxiliar.getTiempoEnsamblaje() + " cantidadComponentesEnsamblados: " + ensambladoraAuxiliar.getCantPiezasEnsambladas()+" estado: "+ensambladoraAuxiliar.getEstadoEnsambladora());
                salida+="\n"+"ensambladora: " + ensambladoraAuxiliar.getNumeroEnsambladora() + " tiempoEnsamblaje: " + ensambladoraAuxiliar.getTiempoEnsamblaje() + " cantidadComponentesEnsamblados: " + ensambladoraAuxiliar.getCantPiezasEnsambladas()+" estado: "+ensambladoraAuxiliar.getEstadoEnsambladora();
                //Ensambladora Bloqueada?
                if (ensambladoraAuxiliar.getEstadoEnsambladora() == false) {
                    ensambladoraAuxiliar.aumenteTiempoInactividadEnsambladora();
                } else {
                    //Terminó de ensamblar?
                    if (ensambladoraAuxiliar.getTiempoEnsamblaje() == 0) {
                        ensambladoraAuxiliar.setTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje)); //Nuevo Tiempo Aleatorio
                        ensambladoraAuxiliar.agregarTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje));
                        contadorEnsamblaje++;
                        ensambladoraAuxiliar.aumenteCantPiezasEnsambladas();
                        eventoComponenteEnsamblado1();
                    } else {
                        ensambladoraAuxiliar.disminuyaTiempoEnsamblaje();
                    }
                }
            }

            //Proceso del horno
            if (horno.getEstadoHorno() == true) {
                System.out.println("horno cocinando: " + horno.getTiempoCoccion());
                salida+="\nhorno cocinando: " + horno.getTiempoCoccion();
                if (horno.getTiempoCoccion() == 0) {
                    if (conEnfriamiento == false) {
                        eventoSaleHorno1();
                    } else {
                        if (horno.getTiempoEnfriamiento() == 0) {
                            horno.setTiempoEnfriamiento(5);
                            eventoSaleHorno1();
                        } else {
                            horno.disminuirTiempoEnfriamiento();
                            System.out.println("horno enfriando: "+horno.getTiempoEnfriamiento());
                            salida+="\n"+"horno enfriando: "+horno.getTiempoEnfriamiento();
                        }
                    }
                } else {
                    horno.disminuirTiempoCoccion();
                }
            } else {
                horno.aumentarTiempoInactividadHorno();
                System.out.println("horno inactivo: " + horno.getTiempoInactividadHorno());
                salida+="\n"+"horno inactivo: " + horno.getTiempoInactividadHorno();
            }

            //Proceso de la cola
            if (colaComponentes.estadoCola() == true) {
                System.out.println("en cola: " + colaComponentes.cantidadComponentesCola());
                salida+="\n"+"en cola: " + colaComponentes.cantidadComponentesCola();
                colaComponentes.aumentarTiempoCola();
            }

            tiempoGeneral++;
        } while (tiempoGeneral < tiempoEjecucion);
        
        //Promedios Finales
        double promEnsamblaje = 0, promInactividadEnsambladoras=0;
        for (int i = 0; i < listaEnsambladoras.size(); i++) {
            promEnsamblaje += listaEnsambladoras.get(i).calcularTiempoPromedioEnsamblaje();
            promInactividadEnsambladoras+= listaEnsambladoras.get(i).getTiempoInactividadEnsambladora();
        }
        System.out.println("\npromedioTiempoEnsamblaje: " + redondear(promEnsamblaje / listaEnsambladoras.size()));
        salida+="\n"+"\npromedioTiempoEnsamblaje: " + redondear(promEnsamblaje / listaEnsambladoras.size());
        System.out.println("promedioTiempoHorneado: " + redondear(horno.calcularTiempoPromedioHorneado()));
        salida+="\n"+"promedioTiempoHorneado: " + redondear(horno.calcularTiempoPromedioHorneado());
        System.out.println("promedioInactividadEnsambladoras: " + redondear(promInactividadEnsambladoras / listaEnsambladoras.size()));
        salida+="\n"+"promedioInactividadEnsambladoras: " + redondear(promInactividadEnsambladoras / listaEnsambladoras.size());
        System.out.println("cantPiezasHorneadas: " + horno.getCantidadPiezasHorneadas());
        salida+="\n"+"cantPiezasHorneadas: " + horno.getCantidadPiezasHorneadas();
        System.out.println("cantPiezasEnsambladas: "+totalPiezasEnsambladas);
        salida+="\n"+"cantPiezasEnsambladas: "+totalPiezasEnsambladas;
        System.out.println("cantEnsambladorasBloqueadas: "+listaEnsambladorasBloqueadas.size());
        salida+="\n"+"cantEnsambladorasBloqueadas: "+listaEnsambladorasBloqueadas.size();
        System.out.println("tamañoCola: " + colaComponentes.cantidadComponentesCola());
        salida+="\n"+"tamañoCola: " + colaComponentes.cantidadComponentesCola();
        System.out.println("tiempoInactivoHorno: " + horno.getTiempoInactividadHorno());
        salida+="\n"+"tiempoInactivoHorno: " + horno.getTiempoInactividadHorno();

    }

    /**
     * **************************************************************************************************************************
     * // Escenario 0: Flujo normal y flujo con enfriamiento
     * ***************************************************************************************************************************
     */
    public void eventoComponenteEnsamblado() {
        componenteAuxiliar = new Componente(totalPiezasEnsambladas);
        totalPiezasEnsambladas++;
        eventoEntraCola(componenteAuxiliar);
    }

    public void eventoEntraCola(Componente componente) {
        //Horno Disponible?
        if (horno.getEstadoHorno() == false) {
            eventoEntraHorno(componente);
        } else {
            colaComponentes.agregarComponenteCola(componente);
        }
    }

    public void eventoEntraHorno(Componente componente) {
        horno.setTiempoCoccion(listaTiemposHorneado.get(contadorHorneado)); 
        horno.agregarTiempoHorneado(listaTiemposHorneado.get(contadorHorneado));
        contadorHorneado++;
        horno.cambieEstadoHorno();
        componenteHorneandose = componente;
    }

    public void eventoSaleHorno() {
        horno.aumentarCantidadPiezasHorneadas();
        listaComponentesHorneados.add(componenteHorneandose);
        //Hay Componentes en cola?
        if (colaComponentes.estadoCola() == true) {
            componenteAuxiliar = colaComponentes.tomarComponenteCola();
            horno.cambieEstadoHorno();
            eventoEntraHorno(componenteAuxiliar);
        } else {
            horno.cambieEstadoHorno();
        }
    }

    /**
     * ******************************************************************************************************************************
     * // Escenario 1: Flujo normal, flujo enfriamiento y tamaño de cola
     * limitado
     * ****************************************************************************************************************************
     */
    public void eventoComponenteEnsamblado1() {
        componenteAuxiliar = new Componente(totalPiezasEnsambladas);
        totalPiezasEnsambladas++;
        eventoEntraCola1(componenteAuxiliar);
    }

    public void eventoEntraCola1(Componente componente) {
        //Espacio en cola
        if (colaComponentes.cantidadComponentesCola() < 4) {
            //Horno Disponible?
            if (horno.getEstadoHorno() == false) {
                eventoEntraHorno1(componente);
            } else {
                colaComponentes.agregarComponenteCola(componente);
            }
        } else {
            ensambladoraAuxiliar.cambieEstadoEnsambladora();
            listaComponentesEnsambladorasBloqueadas.add(componente);
            System.out.println("bloquea ensambladora, estado: "+ensambladoraAuxiliar.getEstadoEnsambladora());
            salida+="\n"+"bloquea ensambladora, estado: "+ensambladoraAuxiliar.getEstadoEnsambladora();
            listaEnsambladorasBloqueadas.add(ensambladoraAuxiliar);
        }
    }

    public void eventoEntraHorno1(Componente componente) {
        horno.setTiempoCoccion(listaTiemposHorneado.get(contadorHorneado)); //Tiempo Aleatorio
        horno.agregarTiempoHorneado(listaTiemposHorneado.get(contadorHorneado));
        contadorHorneado++;
        horno.cambieEstadoHorno();
        componenteHorneandose = componente;
    }

    public void eventoSaleHorno1() {
        horno.aumentarCantidadPiezasHorneadas();
        listaComponentesHorneados.add(componenteHorneandose);
        //Hay Componentes en cola?
        if (colaComponentes.estadoCola() == true) {
            componenteAuxiliar = colaComponentes.tomarComponenteCola();
            horno.cambieEstadoHorno();
            //Hay Ensambladoras Bloqueadas?
            if (!listaEnsambladorasBloqueadas.isEmpty()) {
                ensambladoraAuxiliar = listaEnsambladorasBloqueadas.get(0);
                ensambladoraAuxiliar.cambieEstadoEnsambladora();
                System.out.println("ensambladora Desbloqueada, estado: "+ensambladoraAuxiliar.getEstadoEnsambladora());
                salida+="\n"+"ensambladora Desbloqueada, estado: "+ensambladoraAuxiliar.getEstadoEnsambladora();
                ensambladoraAuxiliar.setTiempoEnsamblaje(listaTiemposEnsamblajes.get(contadorEnsamblaje));
                componenteAuxiliar1=listaComponentesEnsambladorasBloqueadas.get(0);
                colaComponentes.agregarComponenteCola(componenteAuxiliar1);
                listaComponentesEnsambladorasBloqueadas.remove(0);
                contadorEnsamblaje++;
                listaEnsambladorasBloqueadas.remove(0);
                System.out.println("sacó pieza de ensambladora bloqueada, cantEnsambladorasBloqueadas: "+listaEnsambladorasBloqueadas.size());
                salida+="\n"+"sacó pieza de ensambladora bloqueada, cantEnsambladorasBloqueadas: "+listaEnsambladorasBloqueadas.size();
            }
            eventoEntraHorno1(componenteAuxiliar);
        } else {
            horno.cambieEstadoHorno();
        }
    }

    public double redondear(double numero) {
        double resultado = Math.round(numero * Math.pow(10, 5.0)) / (double) Math.pow(10, 5.0);
        return resultado;
    }

    public void generarAleatorioEnsamblaje() {
        int a = 17807;
        int m = 2147483641;
        int X0 = 30;

        int q = m / a;
        int r = m % a;
        int m1 = a * q + r;

        // Valores iniciales
        listaX.add(X0);
        double inicio = X0 / m1;
        listaRnEnsamblaje.add(redondear(inicio));
        listaRnHorneado.add(redondear(inicio));

        for (int i = 1; i < cantidadDatos; i++) {
            double aux = (a * (listaX.get(i - 1) % q)) - (r * Math.floor(listaX.get(i - 1) / q));
            if (aux < 0) {
                aux += m1;
                if (aux > 0) {
                    listaX.add((int) aux);
                } else {
                    JOptionPane.showMessageDialog(null, "No funciona con los valores introducidos");
                    break;
                }
            } else {
                listaX.add((int) aux);
            }
            double Rn = (double) listaX.get(i) / m1;
            listaRnEnsamblaje.add(redondear(Rn));
            listaRnHorneado.add(redondear(Rn));
            listaTiemposEnsamblajes.add(tiempoAleatorioEnsamblaje(Rn));
            listaTiemposHorneado.add(tiempoAleatorioHorneado(Rn));
            if ((listaX.get(i) == listaX.get(0)) || (i == cantidadDatos - 1)) {
                break;
            }
        }

    }

    public int tiempoAleatorioEnsamblaje(double Rn) {
        int aleatorio = (int) (10 * Rn) + 25;
        return aleatorio;
    }

    public int tiempoAleatorioHorneado(double Rn) {
        int aleatorio = (int) (4 * Rn) + 6;
        return aleatorio;
    }
    
    public void resultados()
    {
        salidaT.setText(salida);
        salidaT.setEditable(false);
        JScrollPane scroll=new JScrollPane(salidaT);
        JOptionPane.showMessageDialog(null,scroll);
    }
}
