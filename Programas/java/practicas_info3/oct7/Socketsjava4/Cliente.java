import java.io.*;
import java.net.*;

//traspasando archivos (Cliente-Enviador)

class Cliente 
{

public static void main(String[] args) throws IOException 
{
	Socket echoSocket = null;		//Socket del cliente
	OutputStream out = null;		//Salida
	InputStreamReader in = null;		//Entrada
		
	try 
	{
	//abrir el socket donde se transmitira
	echoSocket = new Socket(args[0], 4444);
	
	//ponerlo para escribir
	out = echoSocket.getOutputStream();
	
	//abrir el archivo que se va a leer
	in = new InputStreamReader(new FileInputStream(args[1]));
	} 

	catch (IOException e) 
	{
	System.err.println("Couldn’t get I/O ");
	System.exit(1);
	}
	
	int b;
	while ((b = in.read()) != -1) {
	out.write(b);
	}

out.close();
in.close();
echoSocket.close();

}
}