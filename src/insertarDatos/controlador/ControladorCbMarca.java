package insertarDatos.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import insertarDatos.vista.IngresarDatos;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author sastian
 */
public class ControladorCbMarca implements ItemListener {

    private IngresarDatos ventana;

    public ControladorCbMarca(IngresarDatos aThis) {
        ventana = aThis;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            ventana.setMarca(e.getItem().toString());
        }
    }

}
