
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;

public class ServidorTCP
{
	public static void main(String [] args)
	{
		byte [] Mensaje = new byte [100];
		InputStream is;
		DataInputStream dis;
		
		try
		{
			ServerSocket SocketServidor = new ServerSocket(8000);
			Socket ComunicaConCliente = SocketServidor.accept();
			System.out.println("Comunicacion establecida");
			
			is=ComunicaConCliente.getInputStream();
			dis=new DataInputStream(is);
			int BytesLeidos = dis.read(Mensaje);
			System.out.println(new String (Mensaje));
			
			ComunicaConCliente.close();
			SocketServidor.close();
		}
		catch(IOException e) {
		System.out.println("Error en las comunicaciones");		
		}
		catch(SecurityException e) {
		System.out.println("Comunicaciones no permitidas por razones de seguridad");		
		}
	}
}