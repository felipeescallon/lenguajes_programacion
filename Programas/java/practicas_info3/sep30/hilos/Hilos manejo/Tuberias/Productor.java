import java.io.*;
import java.lang.*;


class Productor extends Thread  
{
private PipedWriter emisor = null;
private PrintWriter flujoS = null;

public Productor(PipedWriter em) 
{
	emisor=em;
	flujoS=new PrintWriter(emisor);
}


public void run()
{
	while (true)
	{
	almacenarMensaje();
	try
		{
		int msegs = (int)(Math.random() * 100);
		sleep(msegs);
		}
	catch (InterruptedException e) 
		{
		}
	}
}


public synchronized void almacenarMensaje()
{
	int numero;
	String textoMensaje; 
	numero = (int)(Math.random() * 100);
	textoMensaje= "mensaje # "+numero;
	flujoS.println(textoMensaje);
	System.out.println("Productor "+getName()+ " almacena: " +textoMensaje);
}

protected void finalize() throws IOException
{	
	if (flujoS!=null) 
	{ 
	flujoS.close(); 
	flujoS = null; 
	}

	if (emisor!= null) 
	{ 
	emisor.close(); 
	emisor=null;
	}
 
}
}