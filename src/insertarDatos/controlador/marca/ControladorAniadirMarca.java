/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertarDatos.controlador.marca;

import insertarDatos.modelo.ConeccionBD;
import insertarDatos.vista.IngresarMarca;
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
public class ControladorAniadirMarca implements ActionListener {

    private String NOMBRE;
    private int ID;

    private ConeccionBD bd;
    private IngresarMarca panel;
    private static final String CONSULTA_ID = "select MAX(ID_MARCA) from MARCA;";

    public ControladorAniadirMarca(IngresarMarca panel) {
        bd = new ConeccionBD();
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (insertar()) {
                JOptionPane.showMessageDialog(panel, "Se ha añadido marca correctamente", "Añadido", JOptionPane.INFORMATION_MESSAGE);
                //actualizarID();
                //panel.limpiar();
                panel.cerrarVentana();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "No se ha podido insertar la nueva marca", "Error", JOptionPane.ERROR_MESSAGE);
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
        String prepareInst = "INSERT INTO MARCA(ID_MARCA,NOMBRE) VALUES( ?,  ?);";
        if (obtenerDatos()) {
            if (!comprobarExistente()) {
                PreparedStatement prepareStament = bd.getPrepareStament(prepareInst);

                prepareStament.setInt(1, ID);
                prepareStament.setString(2, NOMBRE);

                prepareStament.executeUpdate();
                prepareStament.close();
                correcto = true;
            } else {
                throw new Exception("La marca " + NOMBRE + " ya existe");
            }

        }
        return correcto;
    }

    private boolean obtenerDatos() {
        boolean datosCorrecto = false;
        try {
            ID = Integer.parseInt(panel.getTxtId());
            NOMBRE = panel.getTxtMarca().trim();
            if (NOMBRE.equals("")) {
                throw new NullPointerException();
            }
            datosCorrecto = true;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return datosCorrecto;
    }

    private boolean comprobarExistente() {
        boolean existe = false;

        try {
            ResultSet resultado = bd.realizarConsulta("select NOMBRE from MARCA;");
            while (resultado.next() && !existe) {
                if (NOMBRE.equalsIgnoreCase(resultado.getString("NOMBRE"))) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAniadirMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

}
