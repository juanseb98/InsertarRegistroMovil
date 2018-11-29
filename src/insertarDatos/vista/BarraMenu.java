/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertarDatos.vista;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author sastian
 */
public class BarraMenu extends JMenuBar {

    private JMenu mArchivo, mAyuda;
    private JMenuItem iSalir, iInsertar, iAyuda;
    private IngresarDatos principal;

    public BarraMenu(IngresarDatos principal) {
        this.principal = principal;

        initVariables();
        add(mArchivo);
        iSalir.setMnemonic('S');
        iInsertar.setMnemonic('I');
        mArchivo.add(iSalir);
        mArchivo.add(iInsertar);

        add(mAyuda);
        iAyuda.setMnemonic('A');
        mAyuda.add(iAyuda);

    }

    public void controlador(ActionListener crt) {
        iSalir.addActionListener(crt);
        iSalir.setActionCommand("cancelar");
        iInsertar.addActionListener(crt);
        iInsertar.setActionCommand("insertar");
        iAyuda.addActionListener(crt);
        iAyuda.setActionCommand("ayuda");

    }

    private void initVariables() {
        mArchivo = new JMenu("Archivo");
        mAyuda = new JMenu("Ayuda");
        iInsertar = new JMenuItem("Insertar");
        iSalir = new JMenuItem("Salir");
        iAyuda = new JMenuItem("Ayuda");
    }
    //Método llamado al cargar la ayuda.

    public JMenuItem getJMenuItem() {
        return iAyuda;
    }

}
