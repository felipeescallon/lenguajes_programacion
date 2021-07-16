import java.io.*;
import java.lang.*;

class Tuberias
{
	public static void main(String args[])
	{
	try
		{
		PipedWriter emisor = new PipedWriter();
		PipedReader receptor = new PipedReader(emisor);
		
		Productor productor1 = new Productor(emisor);
		Consumidor consumidor1= new Consumidor(receptor);
		
		productor1.start();
		consumidor1.start();
		}
	
	catch(IOException ignorada)
		{
		}
	
	}
}
