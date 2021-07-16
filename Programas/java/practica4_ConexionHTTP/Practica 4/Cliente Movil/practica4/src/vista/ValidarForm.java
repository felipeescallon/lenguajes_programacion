/**
 *  Practica 4. Conexion HTTP mediante el Movil [VideoTienda]
 *	Electiva:  Desarrollo de Aplicaciones para Dispositivos Móviles [Telemática]
 *  Universidad del Cauca
 *  Ing. Oscar Mauricio Caicedo 			Monitor: Carlos Felipe Lopez
 * 
 *  Created on may-2005
 */

package vista;

import javax.microedition.lcdui.*;

import conexion.Conexion;
import control.*;

public class ValidarForm extends Form implements Runnable{

    private Command salir, conectar;
    private TextField Login, Contraseña;
    private String URL;
    private Conexion cx; //Establece la conexion con el servidor HTTP.
    private StringItem result;
    private String parameters[];
    private String action;
    private String response;
    private Alert Error;

    public ValidarForm() {
        super("TIENDA DE VIDEOS");

        try {
            init();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa la aplicacion. Aqui se instancian los elementos que conforman el Form.
     * */
    private void init() throws Exception {
        
    	//Mensajes
        StringItem mensaje = new StringItem("Peliculas <El Rapidito>","");
        mensaje.setLayout(StringItem.LAYOUT_CENTER);
        StringItem mensaje1 = new StringItem("","Por favor ingrese su Cedula y su Password.");
        result = new StringItem("", "");
        //TextField
        Login      = new TextField("Login:         ", "", 15, TextField.ANY);
        Contraseña = new TextField("Password: ","", 15,TextField.PASSWORD);
        //Botones
        salir    = new Command("Cerrar", Command.EXIT, 1);
        conectar = new Command("Conectar", Command.OK, 1);
        //Alertas
        Error = new Alert("Mensaje:", "", null, AlertType.ERROR);
        Error.setTimeout(Alert.FOREVER);        
        
        //Agregamos el listener
        this.setCommandListener(new ValidarForm_this_commandAdapter(this));
        
        //Agregamos los elementos a el Form
        append(mensaje);
        append(mensaje1);
        addCommand(salir);
        addCommand(conectar);
        append(Login);
        append(Contraseña);
        append(result);
    }

    /**
     * Generamos un commandAction para este Formulario.
     * @param command
     * @param displayable
     */
    
    public void this_commandAction(Command command, Displayable displayable) {
    	//Si presiona "Cerrar"
    	if (command.getCommandType() == Command.EXIT) {
		VideoMIDlet.midlet.destroyApp(true);
		VideoMIDlet.midlet.notifyDestroyed();
		VideoMIDlet.midlet = null;
      }
    	//Si presiona "Conectar"
    	else if (command.getCommandType() == Command.OK) {
        System.out.println("******Validando Usuario******");
        //Los parametros que vamos a pasar para que el usaurio sea validado.
        String param[] ={"login="+Login.getString(),"pwd="+Contraseña.getString()};
        setParameters(param);
        
        Login.setString("");
        Contraseña.setString("");
        result.setText("");
        Thread t = new Thread(this);
        t.start();
        //Inicializa el Hilo para conectarse con el Modulo Conexion y establecer la conexión HTTP
      }
    }

    /**
     *  Inicia el Hilo el cua nos permite realizar las consultas al servidor.
     */
    public void run() {
        //Le indicamos la ubicación de el sitio web. 
    	Conexion conn = new Conexion("http://172.16.130.164/WebVideoTienda/login.php");
        try{
            response = conn.sendWithGET(parameters);
            System.out.println(response);
        }catch(Exception e){
            Error.setString("Servidor no encontrado!");
            VideoMIDlet.midlet.displayMgr.pushDisplayable(Error,new ValidarForm());
            e.printStackTrace();
        }
        //Si el servidor no nos da una respuesta
        if (response == null) {
            Error.setString(conn.getErrorMessage());
            VideoMIDlet.midlet.displayMgr.pushDisplayable(Error,new ValidarForm());
        }
          else 
          {
          	//Tenemos una respuesta del servidor
          	//Analizamos la respuesta, dado que nos llega con basura, corremos 3 caracteres, para sacar la respuesta en limpio.
          	String resp= response.substring(3); 

          	if (resp.equals("Valido")){
          		result.setText("Usuario Valido");
          		System.out.println("Usuario Valido");
          		//Como el usuario es valido puede ingresar al sistema.
          		VideoMIDlet.midlet.displayMgr.pushDisplayable(new PeliculasList());
          	}
          	else if (resp.equals("Invalido")){
          		result.setLabel("Usuario Invalido");	
          		result.setText("Inscribite a nuestro servicio por tan solo $50000 mensuales. ");
          		System.out.println("Usuario Invalido");          		
          	}
          	else if (resp.equals("Bad Password")){
          		result.setLabel("Password Invalido");
          		result.setText("Recuerde que el sistema es sensible a las mayusculas.");	
          		System.out.println("Has ingresado mal el password");
          	}
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
 * 
 * Esta clase nos permite implementar el commandListener para recibir las opciones del usuario.
 * 
 */

	class ValidarForm_this_commandAdapter implements javax.microedition.lcdui.CommandListener {
		ValidarForm adaptee;
		ValidarForm_this_commandAdapter(ValidarForm adaptee) {
			this.adaptee = adaptee;
		}

	/**
	 * CommandAction
	 */
		public void commandAction(Command command, Displayable displayable) {
		adaptee.this_commandAction(command, displayable);
	}
}
