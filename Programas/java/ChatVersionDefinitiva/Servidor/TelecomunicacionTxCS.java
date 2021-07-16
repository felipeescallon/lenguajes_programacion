import java.lang.*;									
import java.io.*;
import java.net.*;
import java.awt.*;

public class TelecomunicacionTxCS 
{
private Socket SocketCliente;							
private OutputStream FlujodeSalida;					
private DataOutputStream Flujohaciaservidor;
private Thread HiloTx = null;

public TelecomunicacionTxCS()									
{
	try
	{
	SocketCliente = new Socket("localhost", 8002);					//Instaciamos los sockets de transmision; la cual se realizara por el puerto 8002 predeterminado
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
	System.out.println("Daño Tx servidor 1");
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}	
}

public void correr(TextField MensajeSaliente, String[] Logines, int i, String CampoLogin)	//Funcion que permite el envio de mensajes al servidor  
{												//Recibe el mensaje a enviar, la matriz de logines destinatarios
String CadenaTotal;										//y una variable que indica el indice de la matriz de usuarios activos	

	try
	{
	
	Flujohaciaservidor.writeInt(i);								//Enviamos el indice el indice de la matriz de usuarios activos

		for(int contador=0; contador<=i; contador++)
		{
		Flujohaciaservidor.writeUTF(Logines[contador]);					//Enviamos los logines de los usuarios activos	
		}

	CadenaTotal = "  << ".concat(CampoLogin.concat(" >> :  ".concat(MensajeSaliente.getText())));	 //Agregamos la etiqueta de remitente al mensaje 
	Flujohaciaservidor.writeUTF(CadenaTotal); 						//Enviamos el mensaje escrito por usuario
	}
	
	catch(IOException e)
	{
	System.out.println("Daño en Tx Servidor 2");
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}
}
	
public void ExcluirUsuario(String[] Logines, int i)						//Funcion que permite el envio de mensajes al para que excluya a detrminado usuario  
{												//Recibimos la matriz de logines y el indice de la matriz de usuarios activos
	try
	{
	Flujohaciaservidor.writeInt(-5);							//(envio = -5) SIGNIFICA QUE SE REALIZARA LA EXCLUSION DE USUARIOS DEL CHAT
	Flujohaciaservidor.writeInt(i);								//Mandamos el indice de la matriz
	
		for(int contador=0; contador<=i; contador++)
		{
		Flujohaciaservidor.writeUTF(Logines[contador]);					//Mandamos los logines de los usuarios a excluir
		}

	Flujohaciaservidor.writeUTF("<<EXCLUIDO POR EL SERVIDOR>>");				//Mandamos un mensaje que se imprimira en pantalla a todos los usuarios excluidos	
	}

	catch(IOException e)
	{
	System.out.println("Daño Tx Servidor 3");
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









