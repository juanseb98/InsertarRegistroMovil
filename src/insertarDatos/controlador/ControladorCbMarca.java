package insertarDatos.controlador;

import insertarDatos.vista.IngresarDatos;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Metodo encargado de ver el nombre de la marca que esta seleccionada en el
 * combobox de la ventana insertar movil
 *
 * @author juan Sebastián González Sánchez
 */
public class ControladorCbMarca implements ItemListener {

    private IngresarDatos ventana;

    /**
     * Constructor en el cual obtenemos nuestra ventana principal
     *
     * @param ventana ventana principal de añadir movil
     */
    public ControladorCbMarca(IngresarDatos ventana) {
        this.ventana = ventana;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {//Si el combobox cambia
            ventana.setMarca(e.getItem().toString());
        }
    }

}
