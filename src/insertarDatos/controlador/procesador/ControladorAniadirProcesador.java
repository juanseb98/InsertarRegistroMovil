/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertarDatos.controlador.procesador;

import insertarDatos.controlador.marca.ControladorAniadirMarca;
import insertarDatos.modelo.ConeccionBD;
import insertarDatos.vista.IngresarProcesador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sastian
 */
public class ControladorAniadirProcesador implements ActionListener {

    private String MODELO, TIPO;
    private int ID;
    private double FRECUANCIA;

    private ConeccionBD bd;
    private IngresarProcesador panel;

    private static final String CONSULTA_ID = "select MAX(ID_PROCESADOR) from PROCESADOR;";
    private static final String INSERTAR_PROCESADOR = "INSERT INTO PROCESADOR(ID_PROCESADOR,MODELO,TIPO,FRECUENCIA) VALUES( ?,  ?,  ?,  ?);";

    public ControladorAniadirProcesador(IngresarProcesador panel) {
        bd = new ConeccionBD();
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (insertar()) {
                JOptionPane.showMessageDialog(panel, "Se ha añadido Procesador correctamente", "Añadido", JOptionPane.INFORMATION_MESSAGE);
                //actualizarID();
                //panel.limpiar();
                panel.cerrarVentana();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "No se ha podido insertar Procesador", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Ya creado", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void actualizarID() throws SQLException {

        ResultSet resultado = bd.realizarConsulta(CONSULTA_ID);
        if (resultado.next()) {
            panel.setTxtId(resultado.getInt(1) + 1);
        }
        resultado.close();

    }

    private boolean insertar() throws SQLException, Exception {
        boolean correcto = false;
        if (obtenerDatos()) {
            if (!comprobarExistente()) {
                PreparedStatement prepareStament = bd.getPrepareStament(INSERTAR_PROCESADOR);

                prepareStament.setInt(1, ID);
                prepareStament.setString(2, MODELO);
                prepareStament.setString(3, TIPO);
                prepareStament.setDouble(4, FRECUANCIA);

                prepareStament.executeUpdate();
                prepareStament.close();
                correcto = true;
            } else {
                throw new Exception("El procesador " + MODELO + " ya existe");
            }
        }
        return correcto;
    }

    private boolean obtenerDatos() {
        boolean datosCorrecto = false;
        try {
            ID = Integer.parseInt(panel.getTxtId());
            MODELO = panel.getTxtModelo().trim();
            TIPO = panel.getTxtTipo().trim();
            if (MODELO.equals("") || TIPO.equals("")) {
                throw new NullPointerException();
            }
            FRECUANCIA = Double.parseDouble(panel.getTxtFrecuencia());
            datosCorrecto = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "La frecuencia es un numero", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return datosCorrecto;
    }

    private boolean comprobarExistente() {
        boolean existe = false;

        try {
            ResultSet resultado = bd.realizarConsulta("select MODELO from PROCESADOR;");
            while (resultado.next() && !existe) {
                if (MODELO.equalsIgnoreCase(resultado.getString("MODELO"))) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAniadirMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

}
