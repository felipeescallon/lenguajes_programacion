/**
 *  Practica 4. Conexion HTTP mediante el Movil [VideoTienda]
 *	Electiva:  Desarrollo de Aplicaciones para Dispositivos Móviles [Telemática]
 *  Universidad del Cauca
 *  Ing. Oscar Mauricio Caicedo				 		Monitor: Carlos Felipe Lopez
 * 
 *  Created on may-2005
 */


package control;

import javax.microedition.lcdui.*;
import java.util.Stack;

/**
 * Permite administrar las ventanas de la aplicacion. Se usa on objeto de tipo "stack" para desplegar los objetos de tipo displayable.
 * @author GIT - UNICAUCA
 * @version 1.0
 */
public class DisplayManager extends Stack {
    private Display display; // Referencia a un Objeto Display
    private Displayable mainDisplayable; // Displayable principal del MIDlet
    private Alert alStackError; // Alerta para errores

    /**
     * Constructor.
     * @param display MIDlet display object
     * @param mainDisplayable Main screen to be displayed. It becomes the home screen
     */
    public DisplayManager(Display display, Displayable mainDisplayable) {
        //Solo un objeto display por Midlet,  
    	this.display = display;
        this.mainDisplayable = mainDisplayable;
        // Crea una alerta cuando hay algun problema al cargar el objeto displayable.
        alStackError = new Alert("Displayable Stack Error");
        alStackError.setTimeout(Alert.FOREVER); // Modal
    }

    /**
     * Agrega el objeto displayable actual al tope del Stack y lo despliega.
     ** @param newDisplayable Nueva ventana a ser desplegada
     */
    public void pushDisplayable(Displayable newDisplayable) {
        push(display.getCurrent());
        display.setCurrent(newDisplayable);
    }

    /**
     * Agrega el objeto displayable actual al tope del Stack y lo despliega.
     * Pero Primero despliega una Alarma.
     ** @param newDisplayable Nueva ventana a ser desplegada
     */
    public void pushDisplayable(Alert alert, Displayable newDisplayable) {
        push(display.getCurrent());
        display.setCurrent(alert,newDisplayable);
    }

    
    /**
     * Despliega la ventana definida anteriormente en la constructora.
     */
    public void home() {
        while (elementCount > 1)
            pop();
        display.setCurrent(mainDisplayable);
    }

    /**
     * Despliega el objeto que esta en el tope y lo elimina del Stack. 
     */
    public void popDisplayable() {

        // Si el Stack no esta vacio, saca el objeto. 
        if (empty() == false){
            display.setCurrent((Displayable)pop());
        }else{
            // Cuando hay un error despliega la alerta
            // Cuando sale la alerta, coloca el mainDisplayable
            display.setCurrent(alStackError, mainDisplayable);
        }

    }
}
