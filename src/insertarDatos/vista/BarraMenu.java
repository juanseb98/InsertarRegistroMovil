package insertarDatos.vista;

import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 * Barra de menu de opciones de nuestra pantalla
 *
 * @author Juan Sebsatián González Sánchez
 */
public class BarraMenu extends JMenuBar {

    private JMenu mArchivo, mAyuda;
    private JMenuItem iSalir, iInsertar, iAyuda, iManual;
    private IngresarDatos principal;
    private JSeparator separador;

    public BarraMenu(IngresarDatos principal) {
        this.principal = principal;

        initVariables();
        add(mArchivo);
        iSalir.setMnemonic('S');
        iInsertar.setMnemonic('I');
        mArchivo.add(iInsertar);
        mArchivo.add(separador);
        mArchivo.add(iSalir);

        add(mAyuda);
        iAyuda.setMnemonic('A');
        mAyuda.add(iAyuda);
        iManual.setMnemonic('M');
        mAyuda.add(iManual);

        cargarAyuda();
    }

    public void controlador(ActionListener crt) {
        iSalir.addActionListener(crt);
        iSalir.setActionCommand("cancelar");
        iInsertar.addActionListener(crt);
        iInsertar.setActionCommand("insertar");
        iAyuda.addActionListener(crt);
        iAyuda.setActionCommand("ayuda");
        iManual.addActionListener(crt);
        iManual.setActionCommand("manual");

    }

    private void initVariables() {
        mArchivo = new JMenu("Archivo");
        mAyuda = new JMenu("Ayuda");
        iInsertar = new JMenuItem("Insertar");
        iSalir = new JMenuItem("Salir");
        iAyuda = new JMenuItem("Ayuda");
        iManual = new JMenuItem("Manual");
        separador = new JSeparator();
    }

    private void cargarAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("src/insertarDatos/help/help.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsar F1. mntmIndice es el JMenuitem
            hb.enableHelpOnButton(iAyuda, "manual", helpset);
            hb.enableHelpKey(principal.getContentPane(), "manual", helpset);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(principal, "Error al cargar la ayuda: " + e, "Error al abrir la ayuda", JOptionPane.ERROR_MESSAGE);

        }
    }

}
