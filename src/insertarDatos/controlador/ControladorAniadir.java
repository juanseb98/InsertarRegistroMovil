package insertarDatos.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author sastian
 */
public class ControladorAniadir implements ActionListener {

    private ConeccionBD bd;
    private IngresarDatos ventana;

    private static final String CONSULTA_PRO = "select MODELO from PROCESADOR;";
    private static final String CONSULTA_ID = "select MAX(ID) from MOVILES;";
    private static final String CONSULTA_MARCA = "select NOMBRE from MARCA;";
    private static final String INSERT_MOVIL = "INSERT INTO MOVILES(ID,MARCA,NOMBRE,FOTO,TAMANNO,PESO,PULGADAS,RESOLUCION,ALMACENAMIENTO,RAM,PROCESADOR,HUELLA,ACELEROMETRO,GIROSCOPIO,BATERIA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private String NOMBRE, FOTO, TAMANNO, RESOLUCION;
    private int ID, MARCA, PESO, ALMACENAMIENTO, RAM, PROCESADOR, HUELLA, ACELEROMETRO, GIROSCOPIO, BATERIA;
    private double PULGADAS;

    public ControladorAniadir(IngresarDatos ventana) {
        bd = new ConeccionBD();
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String boton = e.getActionCommand();

        switch (boton) {
            case "insertar":
                if (obtenerDatos()) {
                    insertar();
                    actualizarID();
                }

                break;
            case "procesador":
                JDialog ventanaProcesador = new JDialog(ventana, "Añadir Procesador");
                IngresarProcesador panel = new IngresarProcesador(ventanaProcesador);
                ControladorAniadirProcesador ctr = new ControladorAniadirProcesador(panel);
                ventanaProcesador.addWindowListener(new ControladorVentanainsertarProcesador(panel, this));

                panel.controlador(ctr);

                ventanaProcesador.add(panel);

                ventanaProcesador.pack();
                ventanaProcesador.setModal(true);
                ventanaProcesador.setVisible(true);
                break;
            case "marca":
                JDialog ventanaMarca = new JDialog(ventana, "Añadir Marca");
                IngresarMarca p = new IngresarMarca(ventanaMarca);
                ControladorAniadirMarca ctrMarca = new ControladorAniadirMarca(p);
                ventanaMarca.addWindowListener(new ControladorVentanainsertarMarca(p, this));

                p.controlador(ctrMarca);

                ventanaMarca.add(p);

                ventanaMarca.pack();
                ventanaMarca.setModal(true);
                ventanaMarca.setVisible(true);
                break;
            case "imagen":
                ventana.setVerImagen(ventana.getTxtFoto());

                break;
            case "cancelar":
                System.exit(0);
                break;
        }

    }

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

    private int obtenerIdMarca(String marca) {
        int id = 0;
        try {
            ResultSet resultado = bd.realizarConsulta("select ID_MARCA from MARCA where NOMBRE='" + marca + "';");
            id = resultado.getInt("ID_MARCA");
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorAniadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private int obtenerIdProcesador(String procesador) {
        try {
            int id = 0;
            ResultSet resultado = bd.realizarConsulta("select ID_PROCESADOR from PROCESADOR where MODELO = '" + procesador + "';");
            while (resultado.next()) {
                id = resultado.getInt("ID_PROCESADOR");
            }
            resultado.close();
            return id;
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorAniadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

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
            JOptionPane.showMessageDialog(ventana, "Se ha añadido movil correctamente", "Añadido", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(ventana, "No se ha podido insertar movil", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private boolean obtenerDatos() {
        boolean datosCorreecto = false;
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
            datosCorreecto = true;

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Los campos peso, pulgadas,almacenamiento, ram y bateria son numericos", "Error", JOptionPane.ERROR_MESSAGE);
            marcarCasillas();
        }
        return datosCorreecto;
    }

    private void actualizarID() {
        try {
            ResultSet resultado = bd.realizarConsulta(CONSULTA_ID);
            if (resultado.next()) {
                ventana.setTxtId(resultado.getInt(1) + 1);
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorAniadir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void marcarCasillas() {
        ventana.getBateria().setBackground(Color.red);
        ventana.getAlmacenamiento().setBackground(Color.red);
        ventana.getPeso().setBackground(Color.red);
        ventana.getPulgadas().setBackground(Color.red);
        ventana.getRam().setBackground(Color.red);

    }

}
