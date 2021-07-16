import java.io.*;
import java.lang.*;


class CMensaje
{
private String textoMensaje;
private int numeroMensaje;
private boolean disponible=false;

public synchronized void almacenar(int variable)
{
while (disponible == true)
	{
	try
		{
		wait(); 
		}

	catch (InterruptedException e)
		{
		}

	}
	numeroMensaje = variable;
	textoMensaje = "gonorreo";
	disponible = true;
	System.out.println("disponible==verdadero");
	notifyAll();
}

public synchronized String obtener()
{
while (disponible == false)
	{
	try
		{
		wait();
		}
		catch (InterruptedException e)
		{
		}
	}
	
	disponible = false;
	System.out.println("disponible== false");
	notifyAll();
	String mensaje;
	mensaje = textoMensaje+ " # " +numeroMensaje;
	return mensaje;
	
}
}