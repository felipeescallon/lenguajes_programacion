import java.io.*;
import java.lang.*;


class Productor extends Thread  
{
private CMensaje mensaje; 

public Productor(CMensaje c) 
{
	mensaje = c;
}

public void run()
{
	int numeros; 
	while (true)
	{
	numeros=(int)(Math.random() * 100);
	mensaje.almacenar(numeros); 
	System.out.println("Productor " +getName()+ " almacena el mensaje # " +numeros);
	
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
}
