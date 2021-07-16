import java.io.*;
import java.lang.*;


class Consumidor extends Thread  
{
private PipedReader receptor=null;
private BufferedReader flujoE = null;

public Consumidor(PipedReader re) 
	{	
	receptor = re;
	flujoE = new BufferedReader(receptor);
	}

public void run()
	{
	while (true)
		{
		obtenerMensaje();
		}
	}

public synchronized void obtenerMensaje()
	{
	String recado = null;
	try
		{
		recado=flujoE.readLine();
		System.out.println("Consumidor "+getName()+ "obtuvo: " +recado);
		}
	catch (IOException ignorada) 
		{
		}
	}

protected void finalize() throws IOException
{	
	if (flujoE!=null) 
	{ 
	flujoE.close(); 
	flujoE = null; 
	}

	if (receptor!= null) 
	{ 
	receptor.close(); 
	receptor=null;
	}
}
}

