package insertarDatos.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Modelo para realizar consultas en base de datos SQLite
 *
 * @author DAM-2
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

    public ResultSet realizarConsulta(String consulta) throws SQLException {
        return sentencia.executeQuery(consulta);
    }

    public void realizarInsert(String insert) throws SQLException {
        sentencia.executeUpdate(insert);
    }

    public void cerrarConeccion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PreparedStatement getPrepareStament(String prepareStament) throws SQLException {
        return conexion.prepareStatement(prepareStament);
    }

}
