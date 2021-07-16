import java.io.*;
import java.lang.*;


class Consumidor extends Thread  
{
private CMensaje mensaje;	

public Consumidor(CMensaje c)
{	
	mensaje = c;
}


public void run()
{
	String recado;
	while (true)
	{
	recado = mensaje.obtener();
	System.out.println("Consumidor "+getName()+ " obtuvo " +recado);
	}
}
}
