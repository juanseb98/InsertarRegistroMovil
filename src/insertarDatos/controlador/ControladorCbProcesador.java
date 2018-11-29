package insertarDatos.controlador;

import insertarDatos.vista.IngresarDatos;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sastian
 */
public class ControladorCbProcesador implements ItemListener {

    private IngresarDatos ventana;

    public ControladorCbProcesador(IngresarDatos aThis) {
        ventana = aThis;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            ventana.setProcesador(e.getItem().toString());
        }
    }

}
