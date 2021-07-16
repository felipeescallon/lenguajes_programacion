/**
 *  Practica 4. Conexion HTTP mediante el Movil [VideoTienda]
 *	Electiva:  Desarrollo de Aplicaciones para Dispositivos Móviles [Telemática]
 *  Universidad del Cauca
 *  Ing. Oscar Mauricio Caicedo	 			Monitor: Carlos Felipe Lopez
 *  
 *  Created on may-2005
 */ 

package vista;

import javax.microedition.lcdui.*;

import control.*;
import conexion.*;

public class PeliculasList extends List implements Runnable {

	
	private String peliculas;
	private int i = 0;
	private Command cmdSalir;
	private Conexion cx;
    private String parameters[];
    private String action;
    private String response;
    private Alert Error;

	  /* Crea los arreglos de string para cargar el titulo, el año, los actores
	   * el genero, el formato y la fecha todo en nulo y solo 10 espacios
	   */

	  public String[] titulo = {
	      null, null, null, null, null, null, null, null, null, null};
	  public String[] año = {
	      null, null, null, null, null, null, null, null, null, null};
	  public String[] genero = {
	      null, null, null, null, null, null, null, null, null, null};
	
	  /**
	   * Constructora. Aqui establecemos la comunicación con el servidor 
	   * para solicitar las peliculas disponibles.
	   */
	  
	  public PeliculasList( ){

	  	super("Peliculas Disponibles",List.IMPLICIT);
	  	System.out.println("******Cargando las peliculas******");
	    String param[] ={""};
	    setParameters(param);
	    Thread t = new Thread(this);
	    t.start();
	    //Establecemos la conexion con el servidor HTTP
	  }
	  
	  /**
	   * Inicializamos la lista, ya con las peliculas  descargadas.
	   * @param resp
	   */
	  public void init(String resp){
	  	
	  	//Capturamos las peliculas
	  	this.peliculas = resp;
	  	//Las organizamos
	  	this.organizar();

	    cmdSalir = new Command("Salir", Command.BACK, 1);

	    //Las agregamos a la Lista.
	    insert(0, " TITULO[AÑO]- GENERO ", null);
	    for (int n = 0; n < this.i; n++) {
	      insert(n + 1, titulo[n] + " [" + año[n] + "] - " +genero[n], null);
	    }
	    addCommand(cmdSalir);
        this.setCommandListener(new PeliculasList_this_commandAdapter(this));
	  }

	  /**
	   * Ya con la cadena con las peliculas, simplemente organizamos en los arreglos que teniamos inicialmente.
	   */

    public void organizar() {

	    while (!peliculas.startsWith("&")) { //Se rompe el ciclo cuando encuentra "&"
	    									 //La cadena es asi pelicula;Titanic;19997;Drama:Saw;2005;Accion:Garfield;2004;Comedia:&

	       System.out.println("i: " + i);

	       //almacena en cada uno de los arreglos los datos que hay en cada uno. 
	       this.titulo[i] = this.peliculas.substring(0, this.peliculas.indexOf(";"));
	       this.peliculas = this.peliculas.substring(this.peliculas.indexOf(";") + 1,
	                                             this.peliculas.length());
	       this.año[i] = this.peliculas.substring(0, this.peliculas.indexOf(";"));
	       this.peliculas = this.peliculas.substring(this.peliculas.indexOf(";") + 1,
	                                             this.peliculas.length());
	       this.genero[i] = this.peliculas.substring(0, this.peliculas.indexOf(":"));
	       this.peliculas = this.peliculas.substring(this.peliculas.indexOf(":") + 1,
	                                             this.peliculas.length());
	       i++;

	    }
	  }

    /**
     *  Inicia el Hilo el cua nos permite realizar las consultas al servidor.
     */

    public void run() {
    	Conexion conn = new Conexion("http://172.16.130.164/WebVideoTienda/peliculas.php");
        try{
            response = conn.sendWithGET(parameters);
            System.out.println(response);
        }catch(Exception e){
            Error.setString("Server not found !");
            VideoMIDlet.midlet.displayMgr.pushDisplayable(Error,new PeliculasList());
            e.printStackTrace();
        }
        if (response == null) {
            Error.setString(conn.getErrorMessage());
            VideoMIDlet.midlet.displayMgr.pushDisplayable(Error,new PeliculasList());
        }
          else 
          {
          	//Tenemos una respuesta del servidor
          	//la respuesta en este caso al iniciar la cadena le indica que contiene las peliculas.
          	String resp= response.substring(3);
          	if (resp.startsWith("pelicula")){

          	String var1 = resp.substring(0, resp.indexOf(";"));
            resp = resp.substring(resp.indexOf(";") + 1,resp.length());

            System.out.println("Respuesta de: " + var1);
            System.out.println("Respuesta: " + resp);

            try{
            	init(resp);
            }catch (Exception e){}
          	}
          }
    	}

    /**
     * Generamos un commandAction para este Formulario.
     * @param command
     * @param displayable
     */    
    public void this_commandAction(Command command, Displayable displayable) {
        if (command.getCommandType() == Command.BACK) {
  		VideoMIDlet.midlet.displayMgr.popDisplayable();
        }
      }

    /**
     * Agregamos los parametros para que sean enviados junto a la dirección HTTP. 
     * @param parameters
     */
    
    public void setParameters(String [] parameters){
        for(int i=0; i<parameters.length;i++){
          System.out.println(parameters[i]);
        }
        this.parameters=parameters;
      }
}
	/**
	 * Esta clase nos permite implementar el commandListener para recibir las opciones del usuario.
	 */
	class PeliculasList_this_commandAdapter implements javax.microedition.lcdui.CommandListener {
		PeliculasList adaptee;
		PeliculasList_this_commandAdapter(PeliculasList adaptee) {
			this.adaptee = adaptee;
		}
	/**
	 * CommandAction
	 */	
		
	public void commandAction(Command command, Displayable displayable) {
		adaptee.this_commandAction(command, displayable);
	}
}
    
    

