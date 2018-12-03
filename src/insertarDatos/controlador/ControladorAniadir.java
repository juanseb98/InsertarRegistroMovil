package insertarDatos.controlador;

import insertarDatos.controlador.marca.ControladorAniadirMarca;
import insertarDatos.controlador.marca.ControladorVentanainsertarMarca;
import insertarDatos.controlador.procesador.ControladorAniadirProcesador;
import insertarDatos.controlador.procesador.ControladorVentanainsertarProcesador;
import insertarDatos.vista.IngresarDatos;
import insertarDatos.modelo.ConeccionBD;
import insertarDatos.vista.IngresarMarca;
import insertarDatos.vista.IngresarProcesador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Clase encargada de controlar los componentes de la pantalla tales como los
 * botones
 *
 * @author Juan Sebastián González Sánchez
 */
public class ControladorAniadir implements ActionListener {

//instanciamos las clases que utilizaremos
    private ConeccionBD bd;
    private IngresarDatos ventana;

//Declaracion de constantes.
    private static final String CONSULTA_PRO = "select MODELO from PROCESADOR;";
    private static final String CONSULTA_ID = "select MAX(ID) from MOVILES;";
    private static final String CONSULTA_MARCA = "select NOMBRE from MARCA;";
    private static final String INSERT_MOVIL = "INSERT INTO MOVILES(ID,MARCA,NOMBRE,FOTO,TAMANNO,PESO,PULGADAS,RESOLUCION,ALMACENAMIENTO,RAM,PROCESADOR,HUELLA,ACELEROMETRO,GIROSCOPIO,BATERIA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //declaracion de variables.
    private String NOMBRE, FOTO, TAMANNO, RESOLUCION;
    private int ID, MARCA, PESO, ALMACENAMIENTO, RAM, PROCESADOR, HUELLA, ACELEROMETRO, GIROSCOPIO, BATERIA;
    private double PULGADAS;

    /**
     * Constructor del controlador en el que se inicializa el acceso a la base
     * de datos.
     *
     * @param ventana Ventana principal
     */
    public ControladorAniadir(IngresarDatos ventana) {
        bd = new ConeccionBD();
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String boton = e.getActionCommand();//Recogemos el nombre de la accion que hemos recibido

        switch (boton) {
            case "insertar":

                if (obtenerDatos()) {//obteniene los datos de la ventana y si estan todos continua
                    insertar(); // TODO falta comprobar que el movil ya no exista
                    actualizarID();
                    ventana.limpiar();
                }

                break;
            case "procesador":
                abrirInsertarProcesador();
                break;
            case "marca":
                abrirInsertarMarca();
                break;
            case "imagen":
                ventana.setVerImagen(ventana.getTxtFoto()); // al pulsar ver imagen se actualizara nuestro bean con la nueva foto
                break;
            case "cancelar":
                System.exit(0);
                break;
        }

    }

    /**
     * Metodo encargado de crear un nuevo JDialog en el que aparecerá la
     * pantalla para añadir una nueva marca
     */
    private void abrirInsertarMarca() {
        JDialog ventanaMarca = new JDialog(ventana, "Añadir Marca");
        IngresarMarca p = new IngresarMarca(ventanaMarca);
        ControladorAniadirMarca ctrMarca = new ControladorAniadirMarca(p);
        ventanaMarca.addWindowListener(new ControladorVentanainsertarMarca(p, this));

        p.controlador(ctrMarca);

        ventanaMarca.add(p);

        ventanaMarca.pack();
        ventanaMarca.setModal(true);
        ventanaMarca.setResizable(false);
        ventanaMarca.setLocationRelativeTo(null);
        ventanaMarca.setVisible(true);
    }

    /**
     * Metodo encargado de crear un nuevo JDialog en el que aparecerá la
     * pantalla para añadir un nuevo procesador
     */
    private void abrirInsertarProcesador() {
        JDialog ventanaProcesador = new JDialog(ventana, "Añadir Procesador");
        IngresarProcesador panel = new IngresarProcesador(ventanaProcesador);
        ControladorAniadirProcesador ctr = new ControladorAniadirProcesador(panel);
        ventanaProcesador.addWindowListener(new ControladorVentanainsertarProcesador(panel, this));

        panel.controlador(ctr);

        ventanaProcesador.add(panel);

        ventanaProcesador.pack();
        ventanaProcesador.setModal(true);
        ventanaProcesador.setResizable(false);
        ventanaProcesador.setLocationRelativeTo(null);
        ventanaProcesador.setVisible(true);
    }

    /**
     * Metodo encargado de inicializar las marcas
     *
     * @param bd se le pasa una coneccion a una base de datos
     * @throws SQLException
     */
    public void iniciarMarca(ConeccionBD bd) throws SQLException {
        ResultSet resultado;
        //realizamos la consulta del procesador a la base de datos y guardamos los datos
        resultado = bd.realizarConsulta(CONSULTA_MARCA);
        //Limpiamos los combobox del procesador
        ventana.limpiarItems(ventana.getCbMarca());
        while (resultado.next()) {
            //aniadimos el resultado de la consulta a los combobox de procesador
            ventana.addMarca(resultado.getString("NOMBRE"));
        }
        resultado.close();
    }

    /**
     * Metodo encargado de inicializar los procesadores
     *
     * @param bd se le pasa una coneccion a una base de datos
     * @throws SQLException
     */
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

    /**
     * Metodo encargado de optener el Id de la marca que esta seleccionado en el
     * comboBox
     *
     * @param marca Nombre de la marca seleccionado
     * @return Devuelve un entero con el id de la marca
     */
    private int obtenerIdMarca(String marca) throws SQLException {
        int id = 0;
        ResultSet resultado = bd.realizarConsulta("select ID_MARCA from MARCA where NOMBRE='" + marca + "';");
        id = resultado.getInt("ID_MARCA");
        resultado.close();

        return id;
    }

    /**
     * Metodo encargado de optener el Id del procesador que esta seleccionado en
     * el comboBox
     *
     * @param procesador Nombre del procesador seleccionado
     * @return Devuelve un entero con el id del procesador
     */
    private int obtenerIdProcesador(String procesador) throws SQLException {

        int id = 0;
        ResultSet resultado = bd.realizarConsulta("select ID_PROCESADOR from PROCESADOR where MODELO = '" + procesador + "';");
        while (resultado.next()) {
            id = resultado.getInt("ID_PROCESADOR");
        }
        resultado.close();
        return id;

    }

    /**
     * Metodo encargado de realizar la insercion del nuevo movil
     */
    private void insertar() {
        try {
            PreparedStatement prepareStament = bd.getPrepareStament(INSERT_MOVIL);

            prepareStament.setInt(1, ID);
            prepareStament.setInt(2, MARCA);
            prepareStament.setString(3, NOMBRE);
            prepareStament.setString(4, FOTO);
            prepareStament.setString(5, TAMANNO);
            prepareStament.setInt(6, PESO);
            prepareStament.setDouble(7, PULGADAS);
            prepareStament.setString(8, RESOLUCION);
            prepareStament.setInt(9, ALMACENAMIENTO);
            prepareStament.setInt(10, RAM);
            prepareStament.setInt(11, PROCESADOR);
            prepareStament.setInt(12, HUELLA);
            prepareStament.setInt(13, ACELEROMETRO);
            prepareStament.setInt(14, GIROSCOPIO);
            prepareStament.setInt(15, BATERIA);

            prepareStament.executeUpdate();
            prepareStament.close();
            //En claso de haberse realizado correctamente saldra un aviso de movil añadido correctamente
            JOptionPane.showMessageDialog(ventana, "Se ha añadido movil correctamente", "Añadido", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            //En caso que falle se notificara al usuario que no se pudo realizar el insert del nuevo movil
            JOptionPane.showMessageDialog(ventana, "No se ha podido insertar movil", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Metodo encargado de obtener el valor de los campos de texto de la ventana
     * inicial y decir si los ha podido obtener correctamente.
     *
     * @return devuelve un boolean a true si se han obtenido todos los datos
     * correctamente o a false si algun dato no ha podido ser obtenido
     * correctamente.
     */
    private boolean obtenerDatos() {
        boolean datosCorrectos = false;
        try {

            ID = Integer.parseInt(ventana.getTxtId());
            MARCA = obtenerIdMarca(ventana.getMarca());
            NOMBRE = ventana.getTxtNombre();
            FOTO = ventana.getTxtFoto();
            PROCESADOR = obtenerIdProcesador(ventana.getcbProcesador());
            HUELLA = ventana.getCbHuella();
            ACELEROMETRO = ventana.getCbAcelerometro();
            GIROSCOPIO = ventana.getCbGiroscopio();
            BATERIA = Integer.parseInt(ventana.getTxtBateria());
            TAMANNO = ventana.getTxtTamanio();
            PESO = Integer.parseInt(ventana.getTxtPeso());
            PULGADAS = Double.parseDouble(ventana.getTxtPulgadas());
            RESOLUCION = ventana.getTxtResolucion();
            ALMACENAMIENTO = Integer.parseInt(ventana.getTxtAlmacenamiento());
            RAM = Integer.parseInt(ventana.getTxtRam());

            if (NOMBRE.equals("") || FOTO.equals("") || TAMANNO.equals("") || RESOLUCION.equals("")) {
                throw new NullPointerException();
            }
            datosCorrectos = true;//en caso de llgar hasta aqui sin fallo aseguramos que se han exportado bien

        } catch (NullPointerException e) {
            //En caso de que algun dato este vacio se indicara al usuario que todos los campos son obligatorios
            JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            //En caso de fallo por conversion a numero indicaremos que los campos numericos son incorrectos
            JOptionPane.showMessageDialog(ventana, "Los campos peso, pulgadas,almacenamiento, ram y bateria son numericos", "Error", JOptionPane.ERROR_MESSAGE);
            marcarCasillas();//marcamos las casillas que son numericas
        } catch (SQLException ex) {
            //No nos saltara esta excepcion
        }
        return datosCorrectos;
    }

    /**
     * Al insertar en la base de datos necesitamos que el id del movil aumente 1
     * ya que el usuario no ha de conocer que id lleva por lo que consultamos y
     * establecemos el nuevo id.
     */
    private void actualizarID() {
        try {
            //consulta el id mas alto
            ResultSet resultado = bd.realizarConsulta(CONSULTA_ID);
            if (resultado.next()) {
                ventana.setTxtId(resultado.getInt(1) + 1);//establece en el textField id el nuevo valor
            }
            resultado.close();
        } catch (SQLException ex) {
            //No nos saltara esta excepcion
        }
    }

    /**
     * Metodo que pintara de rojo los text field que sean numericos
     */
    private void marcarCasillas() {
        ventana.getBateria().setBackground(Color.red);
        ventana.getAlmacenamiento().setBackground(Color.red);
        ventana.getPeso().setBackground(Color.red);
        ventana.getPulgadas().setBackground(Color.red);
        ventana.getRam().setBackground(Color.red);

    }

}
