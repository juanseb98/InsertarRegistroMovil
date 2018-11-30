package insertarDatos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase encargada de relizar acciones sobre la base de datos SQLite
 *
 * @author Juan Sebsatián González Sánchez
 */
public class ConeccionBD {
//ACORTADOR DE URL https://bitly.com/

    private String URL_BD = "jdbc:sqlite:bd/comparadorMovil.db";
    private static final String DRIVER_SQLITE = "org.sqlite.JDBC";
    private Connection conexion = null;
    Statement sentencia;

    public ConeccionBD() {
        try {

            Class.forName(DRIVER_SQLITE);
            conexion = DriverManager.getConnection(URL_BD);
            sentencia = conexion.createStatement();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos", "Error en la conección", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo encargado de realizar consultas que se le pasa por parametro y
     * devolver el resultado de la consulta.
     *
     * @param consulta sentencia de consulta
     * @return Devuelve el resultSet con el resultado de la consulta.
     * @throws SQLException
     */
    public ResultSet realizarConsulta(String consulta) throws SQLException {
        return sentencia.executeQuery(consulta);
    }

    /**
     * Metodo encargado de Insertar en la base de datos.
     *
     * @param insert Sentencia insert.
     * @throws SQLException
     */
    public void realizarInsert(String insert) throws SQLException {
        sentencia.executeUpdate(insert);
    }

    /**
     * Metodo encargado de cerrar la coneccion con la base de datos
     */
    public void cerrarConeccion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ConeccionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que se encarga de crear un preparedStament con la sentencia pasada
     * por parametro y devolverlo para poder operar con el
     *
     * @param prepareStament Sentencia de prepareStament
     * @return
     * @throws SQLException
     */
    public PreparedStatement getPrepareStament(String prepareStament) throws SQLException {
        return conexion.prepareStatement(prepareStament);
    }

}
