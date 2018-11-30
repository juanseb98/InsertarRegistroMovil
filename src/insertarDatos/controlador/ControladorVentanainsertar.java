package insertarDatos.controlador;

import insertarDatos.vista.IngresarDatos;
import insertarDatos.modelo.ConeccionBD;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase encargada de cargar los datos de marca y de procesador en los combobox
 * al iniciar el programa.
 *
 * @author JuanSebastián González Sánchez
 */
public class ControladorVentanainsertar extends WindowAdapter {

    private final IngresarDatos ventana;
    private ConeccionBD bd;
    private static final String CONSULTA_MARCAS = "select NOMBRE from MARCA;";
    private static final String CONSULTA_PRO = "select MODELO from PROCESADOR;";
    private static final String CONSULTA_ID = "select MAX(ID) from MOVILES;";

    public ControladorVentanainsertar(IngresarDatos vent) {
        ventana = vent;
        bd = new ConeccionBD();//Creamos la coneccion a la base de datos
    }

    @Override
    public void windowOpened(WindowEvent e) {

        try {
            iniciarMarcas();//iniciamos primero el Combobox de marca
            iniciarProcesadores();//iniciamos el combobox de procesador
            consultarID();//Consultaos el id del ultimo movil y ponermos el id del nuevo en la pantalla principal
        } catch (SQLException ex) {
            //En caso de no poder realizar la consulta saltara un mensaje informandonos de ello
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta correctamente", "Error Al realizar consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo encargado de consultar los procesadores de la base de datos e
     * ingresarlo en el combobox de procesadores en la pagina inicial.
     *
     * @throws SQLException
     */
    public void iniciarProcesadores() throws SQLException {
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

    /**
     * Metodo encargado de consultar las marcas de la base de datos e
     * insertarlas en el combobox de marcas en la pagina inicial.
     *
     * @throws SQLException
     */
    private void iniciarMarcas() throws SQLException {
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

    /**
     * Metodo encargado de consultar el ID de ultimo movil en la base de datos e
     * inicializar el campo de Id en la ventana principal con uno mas al ID
     * anterior.
     *
     * @throws SQLException
     */
    private void consultarID() throws SQLException {
        ResultSet resultado;
        //realizamos una cosulta a la base de datos para obtener el ultimo ID usado
        resultado = bd.realizarConsulta(CONSULTA_ID);
        while (resultado.next()) {
            //ponemos el id ya configurado en el cuadro de texto ID
            ventana.setTxtId(resultado.getInt(1) + 1);
        }
        resultado.close();
    }
}
