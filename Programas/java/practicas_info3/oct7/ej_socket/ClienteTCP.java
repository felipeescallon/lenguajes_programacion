
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public class ClienteTCP
{
	public static void main(String [] args)
	{
		OutputStream os;
		DataOutputStream dos;
		
		try
		{
			Socket SocketCliente = new Socket("localhost",8000);
			os=SocketCliente.getOutputStream();
			dos=new DataOutputStream(os);
			dos.writeBytes("MI PRIMER HOLA MUNDO CON SOCKETS");
			SocketCliente.close();
		}
		catch(UnknownHostException e) {
		System.out.println("Referencia a host no resuelta");		
		}
		catch(IOException e) {
		System.out.println("Error en las comunicaciones");		
		}
		catch(SecurityException e) {
		System.out.println("Comunicaciones no permitidas por razones de seguridad");		
		}
	}
}