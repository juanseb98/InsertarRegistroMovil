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
import javax.swing.JOptionPane;

/**
 * Calse encargada de añadir nueva marca a la base de datos
 *
 * @author Juan Sebastián González Sánchez
 */
public class ControladorAniadirMarca implements ActionListener {

    private String NOMBRE;
    private int ID;
    private ConeccionBD bd;
    private IngresarMarca panel;

    private static final String CONSULTA_ID = "select MAX(ID_MARCA) from MARCA;";
    private static final String INSERTAR_MARCA = "INSERT INTO MARCA(ID_MARCA,NOMBRE) VALUES( ?,  ?);";

    public ControladorAniadirMarca(IngresarMarca panel) {
        bd = new ConeccionBD();
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (insertar()) {
                JOptionPane.showMessageDialog(panel, "Se ha añadido marca correctamente", "Añadido", JOptionPane.INFORMATION_MESSAGE);
//                actualizarID(); // en caso de no querer cerrar el panel automaticamente descomentar
//                panel.limpiar(); // en caso de no querer cerrar el panel automaticamente descomentar
                panel.cerrarVentana();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "No se ha podido insertar la nueva marca", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Ya creado", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo encargado de actualizar la Id para la nueva marca Se encuentra en
     * desuso ya que cierro automaticamente la ventana
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
     * Metodo encargado de insertar la nueva marca en la base de datos
     *
     * @return Devuelve un boolean true en caso de haber completado
     * correctamente y un false en caso de error
     * @throws SQLException
     * @throws Exception Excepcion encargada de notificar que ya esta creada la
     * marca
     */
    private boolean insertar() throws SQLException, Exception {
        boolean correcto = false;

        if (obtenerDatos()) {
            if (!existe()) {
                PreparedStatement prepareStament = bd.getPrepareStament(INSERTAR_MARCA);

                prepareStament.setInt(1, ID);
                prepareStament.setString(2, NOMBRE);

                prepareStament.executeUpdate();
                prepareStament.close();
                correcto = true;
            } else {//En caso de que la marca exista en la base de datos mandara un mensaje notificandolo
                throw new Exception("La marca " + NOMBRE + " ya existe");
            }

        }
        return correcto;
    }

    /**
     * Metodo encargado de obtener los datos de la ventana añadir marca
     *
     * @return Devuelve un boolean a true si pudo obtener los datos
     * correctamente o false si ha surgido algun problema
     */
    private boolean obtenerDatos() {
        boolean datosCorrecto = false;
        try {
            ID = Integer.parseInt(panel.getTxtId());
            NOMBRE = panel.getTxtMarca().trim();
            if (NOMBRE.equals("")) {
                throw new NullPointerException();
            }
            datosCorrecto = true;
        } catch (NullPointerException e) {//En caso de algun error se mostrara por pantalla un mensaje
            JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return datosCorrecto;
    }

    /**
     * Metodo que se encarga de comprobar si la marca ya existe en la base de
     * datos
     *
     * @return Devuelve un boolean a true si existe y un boolean a false si no
     * existe
     */
    private boolean existe() {
        boolean existe = false;

        try {
            ResultSet resultado = bd.realizarConsulta("select NOMBRE from MARCA;");
            while (resultado.next() && !existe) {
                if (NOMBRE.equalsIgnoreCase(resultado.getString("NOMBRE"))) {
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
