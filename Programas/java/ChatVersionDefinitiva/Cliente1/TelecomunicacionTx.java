import java.lang.*;									
import java.io.*;
import java.net.*;
import java.awt.*;

public class TelecomunicacionTx 
{
private Socket SocketCliente;								
private OutputStream FlujodeSalida;					
private DataOutputStream Flujohaciaservidor;
private Thread HiloTx = null;
private String CampoLogin;

public TelecomunicacionTx(Registro Veredicto, String CampoLogin1)			//Recibimos el login del usuario y la referencia al resutado de la verificaion	
{
	try
	{
	CampoLogin = CampoLogin1;
	SocketCliente = new Socket("localhost", Veredicto.RetornarPuertoA());		//Instaciamos los sockets de transmision la cual se realizara por el puerto asignado por el servidor
	FlujodeSalida = SocketCliente.getOutputStream();				//obtenemos el objeto FlujodeSalida de tipo OutputStream 
	Flujohaciaservidor = new DataOutputStream(FlujodeSalida);			//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo DataOutputStream 
	}
	
	catch(UnknownHostException e)
	{
	System.out.println("Referencia a Host no resuleta: "+e.getMessage());
	System.exit(0);
	}
	
	catch(IOException e)
	{
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}	
}

public void correr(TextField MensajeSaliente, String[] Logines, int i)			 //Funcion que permite el envio de mensajes al servidor  
{											 //Recibe el mensaje a enviar, la matriz de logines destinatarios
String CadenaTotal;									 //y una variable que indica la funcion	o elindice de la matriz de usuarios

	try
	{
		if(i!=-2 && i!=-7)							 //(i!=-2): MENSAJE ESCRITO POR EL USUARIO Y QUE SE LE ENVIARA AL SERVIDOR
		{
		Flujohaciaservidor.writeInt(i);						 //Enviamos el indice el indice de la matriz de usuarios activos

			for(int contador=0; contador<=i; contador++)
			{
			Flujohaciaservidor.writeUTF(Logines[contador]);			 //Enviamos los logines de los usuarios activos	
			}

		CadenaTotal = "  << ".concat(CampoLogin.concat(" >> :  ".concat(MensajeSaliente.getText())));	//Agregamos la etiqueta de remitente al mensaje 
		Flujohaciaservidor.writeUTF(CadenaTotal); 				 //Enviamos el mensaje escrito por usuario
		}	

		if(i==-7)
		{
		Flujohaciaservidor.writeInt(-7);					 //(i=-7): SE LE INDICA AL SERVIDOR QUE EL USUARIO DESEA ACTUALIZAR SUS DATOS
		Flujohaciaservidor.writeUTF(CampoLogin);				 //Mandamos el login a actualizar
		SocketCliente.close();							 //cerramos el socket ya que se le cierra la sesion al usuario temporalmente
		}		

		if(i==-2)								 
		{
		Flujohaciaservidor.writeInt(i);						 //(i=-2): SE LE INDICA AL SERVIDOR QUE EL USUARIO DESEA CERRAR SESION
		Flujohaciaservidor.writeUTF(CampoLogin);				 //Mandamos el login a actualizar	
		SocketCliente.close();							 //cerramos el socket ya que se la sesion del usuario se cerrara	
		}
	}
	
	catch(IOException e)
	{
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}
	
}

public void ExcluirCorrer()								 //Metodo utilizado por el servidor para excluir a un usuario cerrandole su sesion desde su mismo equipo  	
{											 //METODO EJECUTADO AUTONOMAMENTE POR EL SERVIDOR Y ES AJENO AL USUARIO	
	try
	{
	Flujohaciaservidor.writeInt(-2);						 //(i=-2): SE LE INDICA AL SERVIDOR QUE EL USUARIO DESEA CERRAR SESION
	Flujohaciaservidor.writeUTF(CampoLogin);					 //Mandamos el login a actualizar
	SocketCliente.close();								 //cerramos el socket ya que se le cierra la sesion al usuario temporalmente
	}

	catch(IOException e)
	{
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}

}
}

