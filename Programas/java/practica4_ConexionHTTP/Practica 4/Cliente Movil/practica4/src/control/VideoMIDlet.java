/**
 *  Practica 4. Conexion HTTP mediante el Movil [VideoTienda]
 *	Electiva:  Desarrollo de Aplicaciones para Dispositivos Móviles [Telemática]
 *  Universidad del Cauca
 *  Ing. Oscar Mauricio Caicedo				Monitor: Carlos Felipe López
 * 
 *  Created on may-2005
 */


package control;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import vista.*;



public class VideoMIDlet extends MIDlet {

	private Display display;
	public static VideoMIDlet midlet;
	public DisplayManager displayMgr;
	private Image OK;
	private String respuesta;
	
	
	public void VideoMIDLet() {
	
	}

     /**
     * Invocada cuando es creado el midlet.
     */
    public void startApp() { 

        display = Display.getDisplay(this);
        midlet = this;
    	displayMgr = new DisplayManager(display,new ValidarForm());
        displayMgr.home();
    }

    /**
     * Invocada cuando es cerrada la aplicacion.
     */    
	public void destroyApp( boolean unconditional ) {
	    midlet.notifyDestroyed();
	    midlet = null;
	} 

    
    public void pauseApp() {}

    public void setResp(String resp){
     respuesta=resp;
    }
}
