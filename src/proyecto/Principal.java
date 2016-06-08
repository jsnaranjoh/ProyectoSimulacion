package proyecto;

/**
 *
 * @author
 * Javier Simón Naranjo Herrera
 * Álvaro Andrés Loaiza
 * 
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTiempoEjecucion = new javax.swing.JLabel();
        tiempoEjecucion = new javax.swing.JTextField();
        labelNumEmsambladoras = new javax.swing.JLabel();
        numEnsambladoras = new javax.swing.JTextField();
        checkBoxDejarEnfriarHorno = new javax.swing.JCheckBox();
        checkBoxLimitarColaHorno = new javax.swing.JCheckBox();
        botonSimular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTiempoEjecucion.setText("Tiempo de ejecución:");

        labelNumEmsambladoras.setText("# de emsambladoras:");

        checkBoxDejarEnfriarHorno.setText("Dejar enfriar el horno");

        checkBoxLimitarColaHorno.setText("Limitar la cola del horno");

        botonSimular.setText("Simular");
        botonSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSimularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTiempoEjecucion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tiempoEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkBoxDejarEnfriarHorno))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNumEmsambladoras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numEnsambladoras, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkBoxLimitarColaHorno)))
                    .addComponent(botonSimular))
                .addContainerGap(355, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTiempoEjecucion)
                    .addComponent(tiempoEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumEmsambladoras)
                    .addComponent(numEnsambladoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxDejarEnfriarHorno)
                    .addComponent(checkBoxLimitarColaHorno))
                .addGap(18, 18, 18)
                .addComponent(botonSimular)
                .addContainerGap(487, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSimularActionPerformed
        simular();
    }//GEN-LAST:event_botonSimularActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    
    public void simular() {
        
        Simulacion simulacion = new Simulacion();
        simulacion.iniciarEnsambladorasEjecucion(Integer.parseInt(tiempoEjecucion.getText()), Integer.parseInt(numEnsambladoras.getText()));
        
        boolean enfriamiento;
        if(checkBoxDejarEnfriarHorno.isSelected()) {
            enfriamiento=true;
        } else
            enfriamiento=false;
       
        if(checkBoxLimitarColaHorno.isSelected()) {
            simulacion.ejecutarSimulacionEscenario1(enfriamiento);
        } else
            simulacion.ejecutarSimulacionEscenario0(enfriamiento);
        
        simulacion.resultados();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSimular;
    private javax.swing.JCheckBox checkBoxDejarEnfriarHorno;
    private javax.swing.JCheckBox checkBoxLimitarColaHorno;
    private javax.swing.JLabel labelNumEmsambladoras;
    private javax.swing.JLabel labelTiempoEjecucion;
    private javax.swing.JTextField numEnsambladoras;
    private javax.swing.JTextField tiempoEjecucion;
    // End of variables declaration//GEN-END:variables
}
