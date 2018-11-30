package insertarDatos.controlador;

import insertarDatos.vista.IngresarDatos;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Clase encargada de darnos el nombre del procesador seleccionado en el
 * combobox de procesador en la ventana principal.
 *
 * @author Juan Sebastián González Sánchez
 */
public class ControladorCbProcesador implements ItemListener {

    private IngresarDatos ventana;

    /**
     * Constructor en el cual obtenemos nuestra ventana principal
     *
     * @param ventana ventana principal de añadir movil
     */
    public ControladorCbProcesador(IngresarDatos ventana) {
        this.ventana = ventana;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {//Si el combobox cambia
            ventana.setProcesador(e.getItem().toString());
        }
    }

}
