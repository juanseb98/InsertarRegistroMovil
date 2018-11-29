package insertarDatos.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import insertarDatos.vista.IngresarDatos;
import insertarDatos.modelo.ConeccionBD;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sastian
 */
public class ControladorVentanainsertar extends WindowAdapter {

    private final IngresarDatos ventana;
    private static final String CONSULTA_MARCAS = "select NOMBRE from MARCA;";
    private static final String CONSULTA_PRO = "select MODELO from PROCESADOR;";
    private static final String CONSULTA_ID = "select MAX(ID) from MOVILES;";

    public ControladorVentanainsertar(IngresarDatos vent) {
        ventana = vent;
    }

    @Override
    public void windowOpened(WindowEvent e) {

        try {
            ConeccionBD bd = new ConeccionBD();
            consultarMarcas(bd);

            iniciarProcesadores(bd);

            consultarID(bd);
        } catch (SQLException ex) {
            //En caso de no poder realizar la consulta saltara un mensaje informandonos de ello
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta correctamente", "Error Al realizar consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarID(ConeccionBD bd) throws SQLException {
        ResultSet resultado;
        //realizamos una cosulta a la base de datos para obtener el ultimo ID usado
        resultado = bd.realizarConsulta(CONSULTA_ID);
        while (resultado.next()) {
            //ponemos el id ya configurado en el cuadro de texto ID
            ventana.setTxtId(resultado.getInt(1) + 1);
        }
        resultado.close();
    }

    public void iniciarProcesadores(ConeccionBD bd) throws SQLException {
        ResultSet resultado;
        //realizamos la consulta del procesador a la base de datos y guardamos los datos
        resultado = bd.realizarConsulta(CONSULTA_PRO);
        //Limpiamos los combobox del procesador
        ventana.limpiarItems(ventana.getCbProcesador());
        while (resultado.next()) {
            //aniadimos el resultado de la consulta a los combobox de procesador
            ventana.addProcesador(resultado.getString("MODELO"));
        }
        resultado.close();
    }

    private void consultarMarcas(ConeccionBD bd) throws SQLException {
        ResultSet resultado;
        //realizamos la consulta de las marcas a la base de datos y guardamos los datos
        resultado = bd.realizarConsulta(CONSULTA_MARCAS);
        //Limpiamos los combobox de las marcas
        ventana.limpiarItems(ventana.getCbMarca());
        //aniadimos un item de identificativo
        while (resultado.next()) {
            //aniadimos el resultado de la consulta a los combobox de modelo
            ventana.addMarca(resultado.getString("NOMBRE"));
        }
        resultado.close();
    }
}
