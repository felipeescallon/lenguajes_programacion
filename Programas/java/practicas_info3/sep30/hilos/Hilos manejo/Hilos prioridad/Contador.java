import java.io.*;
import java.lang.*;

class Contador extends Thread
{
public int cuenta;
private double suma=0;

public void run()
	{
	for(cuenta=0; cuenta<100; cuenta++)
	{
	System.out.println(cuenta);
	}
	suma+=Math.random(); 
	
	}
	
}
