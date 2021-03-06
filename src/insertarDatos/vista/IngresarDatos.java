package insertarDatos.vista;

import insertarDatos.controlador.ControladorAniadir;
import insertarDatos.controlador.ControladorVentanainsertar;
import insertarDatos.controlador.ControladorCbMarca;
import insertarDatos.controlador.ControladorCbProcesador;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Pantalla encargada de gestionar la base de datos añadiendo nuevo movil y
 * teniendo como funciones adicionales añadir nuevos procesadores y marcas
 *
 * @author sastian
 */
public class IngresarDatos extends javax.swing.JFrame {

    /**
     * Creates new form IngresarDatos
     */
    public IngresarDatos() {
        initComponents();
        this.setResizable(false);
        marca = new String();
        procesador = new String();
        setTitle("Insertar nuevo Movil");

        //configuramos la ventana para que aparezca en el centro de nuestra pantalla y le añadimos un controlador
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ControladorVentanainsertar(this));

        //desactivamos el textfield del id para que no sea modificable
        txtId.setEnabled(false);

        //Creamos el controlador del combobox de marcas y se lo añadimos
        ControladorCbMarca ctr = new ControladorCbMarca(this);
        jComboBoxMarca.addItemListener(ctr);

        //Creamos el controlador del combobox de procesadores y se lo añadimos
        ControladorCbProcesador ctr2 = new ControladorCbProcesador(this);
        cbProcesador.addItemListener(ctr2);

        //Creamos el controlador de los componentes de nuestra ventana y lo añadimos
        ControladorAniadir controladorComponentes = new ControladorAniadir(this);
        controlador(controladorComponentes);

        //insertamos la barra de menu
        BarraMenu mb = new BarraMenu(this);
        mb.controlador(controladorComponentes);
        this.setJMenuBar(mb);
    }

    /**
     * Metodo que añade el controlador a los componentes de la pantalla
     *
     * @param ctr controlador que escuchara las acciones de los botones
     */
    public void controlador(ActionListener ctr) {
        btInsertar.addActionListener(ctr);
        btInsertar.setActionCommand("insertar");
        btNewProcesador.addActionListener(ctr);
        btNewProcesador.setActionCommand("procesador");
        btNewMarca.addActionListener(ctr);
        btNewMarca.setActionCommand("marca");
        btVerImagen.addActionListener(ctr);
        btVerImagen.setActionCommand("imagen");
        btCancelar.addActionListener(ctr);
        btCancelar.setActionCommand("cancelar");
    }

//<---- Setters ---->
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTxtId(int id) {
        txtId.setText(String.valueOf(id));
    }

    public void setProcesador(String toString) {
        procesador = toString;
    }

    public void setVerImagen(String url) {
        verImagen.setUrlImagen(url);
    }

//<---- Getters ---->
    /**
     * Metodo que comprueba el estado de los checkbox y si esta seleccionado
     * devuelve 1 si no devuelve 0
     *
     * @return devuelve 1 si esta seleccionado y 0 si no lo esta
     */
    public int getCbAcelerometro() {
        int ret = 0;
        if (cbAcelerometro.isSelected()) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo que comprueba el estado de los checkbox y si esta seleccionado
     * devuelve 1 si no devuelve 0
     *
     * @return devuelve 1 si esta seleccionado y 0 si no lo esta
     */
    public int getCbGiroscopio() {
        int ret = 0;
        if (cbGiroscopio.isSelected()) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo que comprueba el estado de los checkbox ysi esta seleccionado
     * devuelve 1 si no devuelve 0
     *
     * @return devuelve 1 si esta seleccionado y 0 si no lo esta
     */
    public int getCbHuella() {
        int ret = 0;
        if (cbHuella.isSelected()) {
            ret = 1;
        }
        return ret;
    }

    public String getTxtAlmacenamiento() {
        return txtAlmacenamiento.getText();
    }

    public String getTxtBateria() {
        return txtBateria.getText();
    }

    public String getTxtFoto() {
        return txtFoto.getText();
    }

    public String getTxtId() {
        return txtId.getText();
    }

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtPeso() {
        return txtPeso.getText();
    }

    public String getTxtPulgadas() {
        return txtPulgadas.getText();
    }

    public String getTxtResolucion() {
        return txtResolucion.getText();
    }

    public String getTxtRam() {
        return txtRam.getText();
    }

    public String getTxtTamanio() {
        return txtTamanio.getText();
    }

    public JComboBox getCbMarca() {
        return jComboBoxMarca;
    }

    public JComboBox getCbProcesador() {
        return cbProcesador;
    }

    public JTextField getPeso() {
        return txtPeso;
    }

    public JTextField getPulgadas() {
        return txtPulgadas;
    }

    public JTextField getAlmacenamiento() {
        return txtAlmacenamiento;
    }

    public JTextField getRam() {
        return txtRam;
    }

    public JTextField getBateria() {
        return txtBateria;
    }

    /**
     * Devuelve el nombre del procesador seleccionado
     *
     * @return String con el nombre del procesador
     */
    public String getcbProcesador() {
        return procesador;
    }

    /**
     * Devuelve el nombre de la marca
     *
     * @return String con la marca
     */
    public String getMarca() {
        return marca;
    }

//<---- Metodos ---->
    /**
     * Metodo encargado de eliminar todos los items de un combobox que se le
     * pasa por parametro
     *
     * @param cb combobox que se quiere limpiar
     */
    public void limpiarItems(JComboBox cb) {
        cb.removeAllItems();
    }

    /**
     * Metodo encargado de añadir nuevos al combobox marca
     *
     * @param mar nombre de la marca
     */
    public void addMarca(String mar) {
        jComboBoxMarca.addItem(mar);
    }

    /**
     * Metodo encargado de añadir nuevo item al combobox del procesador
     *
     * @param pro nombre del procesador
     */
    public void addProcesador(String pro) {
        cbProcesador.addItem(pro);
    }

    /**
     * Metodo encargado de limpiar los valores de la pantalla una vez insertado
     * el movil
     */
    public void limpiar() {
        txtNombre.setText("");
        txtFoto.setText("");
        txtTamanio.setText("");
        txtPeso.setText("");
        txtPulgadas.setText("");
        txtResolucion.setText("");
        txtAlmacenamiento.setText("");
        txtRam.setText("");
        cbHuella.setSelected(false);
        cbAcelerometro.setSelected(false);
        cbGiroscopio.setSelected(false);
        txtBateria.setText("");

        txtNombre.requestFocus();//Se le da focus al campo nombre
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabeld = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        txtFoto = new javax.swing.JTextField();
        txtTamanio = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        txtPulgadas = new javax.swing.JTextField();
        txtResolucion = new javax.swing.JTextField();
        txtAlmacenamiento = new javax.swing.JTextField();
        txtRam = new javax.swing.JTextField();
        cbProcesador = new javax.swing.JComboBox<>();
        cbHuella = new javax.swing.JCheckBox();
        cbAcelerometro = new javax.swing.JCheckBox();
        cbGiroscopio = new javax.swing.JCheckBox();
        txtBateria = new javax.swing.JTextField();
        btInsertar = new javax.swing.JButton();
        btNewProcesador = new javax.swing.JButton();
        btNewMarca = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btVerImagen = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        verImagen = new imageView.ImageView();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabeld.setText("ID");
        getContentPane().add(jLabeld, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel2.setText("Marca");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel1.setText("Nombre");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel3.setText("Foto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel4.setText("Tamaño");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel5.setText("Peso");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel6.setText("Pulgadas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel7.setText("Resolucion");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel8.setText("Almacenamiento");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel9.setText("RAM");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel10.setText("Procesador");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jLabel11.setText("Huella");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        jLabel12.setText("Acelerometro");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        jLabel13.setText("Giroscopio");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        jLabel14.setText("Bateria");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 46, -1));

        jComboBoxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 158, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 275, -1));
        getContentPane().add(txtFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 275, -1));
        getContentPane().add(txtTamanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, -1));
        getContentPane().add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 60, -1));
        getContentPane().add(txtPulgadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 60, -1));
        getContentPane().add(txtResolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 150, -1));
        getContentPane().add(txtAlmacenamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 60, -1));
        getContentPane().add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 60, -1));

        cbProcesador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbProcesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 236, -1));
        getContentPane().add(cbHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, -1, -1));
        getContentPane().add(cbAcelerometro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, -1, -1));
        getContentPane().add(cbGiroscopio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, -1, -1));
        getContentPane().add(txtBateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, 63, -1));

        btInsertar.setText("Añadir movil");
        getContentPane().add(btInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, 191, -1));

        btNewProcesador.setText("Nuevo procesador");
        getContentPane().add(btNewProcesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, -1, 30));

        btNewMarca.setText("Nueva marca");
        getContentPane().add(btNewMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 145, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 530, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 530, 10));

        btVerImagen.setText("Ver imagen");
        getContentPane().add(btVerImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        btCancelar.setText("Cancelar");
        getContentPane().add(btCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 190, -1));
        getContentPane().add(verImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 530, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btInsertar;
    private javax.swing.JButton btNewMarca;
    private javax.swing.JButton btNewProcesador;
    private javax.swing.JButton btVerImagen;
    private javax.swing.JCheckBox cbAcelerometro;
    private javax.swing.JCheckBox cbGiroscopio;
    private javax.swing.JCheckBox cbHuella;
    private javax.swing.JComboBox<String> cbProcesador;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabeld;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtAlmacenamiento;
    private javax.swing.JTextField txtBateria;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPulgadas;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtResolucion;
    private javax.swing.JTextField txtTamanio;
    private imageView.ImageView verImagen;
    // End of variables declaration//GEN-END:variables
    private String marca, procesador;
}
