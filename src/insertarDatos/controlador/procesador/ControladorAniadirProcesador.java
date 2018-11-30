/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertarDatos.controlador.procesador;

import insertarDatos.modelo.ConeccionBD;
import insertarDatos.vista.IngresarProcesador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                //actualizarID(); // en caso de no querer cerrar el panel automaticamente descomentar
                //panel.limpiar(); // en caso de no querer cerrar el panel automaticamente descomentar
                panel.cerrarVentana();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "No se ha podido insertar Procesador", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Ya creado", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo encargado de actualizar la Id para el nuevo procesador. Se
     * encuentra en desuso ya que cierro automaticamente la ventana
     *
     * @throws SQLException
     */
    private void actualizarID() throws SQLException {

        ResultSet resultado = bd.realizarConsulta(CONSULTA_ID);
        if (resultado.next()) {
            panel.setTxtId(resultado.getInt(1) + 1);
        }
        resultado.close();

    }

    /**
     * Metodo encargado de insertar el nuevo procesador en la base de datos
     *
     * @return Devuelve un boolean true en caso de haber completado
     * correctamente y un false en caso de error
     * @throws SQLException
     * @throws Exception Excepcion encargada de notificar que ya esta creado el
     * procesador
     */
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
            } else {//En caso de que el procesador exista en la base de datos mandara un mensaje notificandolo
                throw new Exception("El procesador " + MODELO + " ya existe");
            }
        }
        return correcto;
    }

    /**
     * Metodo encargado de obtener los datos de la ventana añadir procesador
     *
     * @return Devuelve un boolean a true si pudo obtener los datos
     * correctamente o false si ha surgido algun problema
     */
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

    /**
     * Metodo que se encarga de comprobar si el procesador ya existe en la base
     * de datos
     *
     * @return Devuelve un boolean a true si existe y un boolean a false si no
     * existe
     */
    private boolean comprobarExistente() {
        boolean existe = false;

        try {
            ResultSet resultado = bd.realizarConsulta("select MODELO from PROCESADOR;");
            while (resultado.next() && !existe) {
                if (MODELO.equalsIgnoreCase(resultado.getString("MODELO"))) {
                    existe = true;
                }
            }
            resultado.close();
        } catch (SQLException ex) {
            //excepción no utilizada
        }
        return existe;
    }

}
