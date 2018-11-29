/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertarDatos.vista;

import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author sastian
 */
public class IngresarMarca extends javax.swing.JPanel {

    private final JDialog contenedor;

    public IngresarMarca(JDialog contenedor) {
        this.contenedor = contenedor;
        initComponents();
        txtId.setEnabled(false);
    }

// <editor-fold defaultstate="collapsed" desc="Getters">
    public String getTxtId() {
        return txtId.getText();
    }

    public String getTxtMarca() {
        return txtMarca.getText();
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Setters">
    public void setTxtId(int id) {
        txtId.setText(String.valueOf(id));
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Metodos propios">
    public void cerrarVentana() {
        contenedor.dispose();
    }

    /**
     * Metodo que limpia los campos
     */
    public void limpiar() {
        txtMarca.setText("");
    }
    // </editor-fold>

//<---- controlador ---->
    public void controlador(ActionListener ctr) {
        btInsertar.addActionListener(ctr);
        btInsertar.setActionCommand("insertar");

    }

    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        btInsertar = new javax.swing.JButton();

        jLabel1.setText("ID Marca");

        jLabel2.setText("Marca");

        btInsertar.setText("Añadir nueva marca");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btInsertar)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInsertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMarca;
    // End of variables declaration//GEN-END:variables
}
