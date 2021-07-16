import java.io.*;
import java.lang.*;


class ContadorAdelante extends Thread  
{

public ContadorAdelante(String nombre)
{
if(nombre!=null)
setName(nombre);
start();
}

public ContadorAdelante()
{
this(null);
}

public void run()
{
	for(int i=1; i<=1000; i++)
	{
	System.out.println(getName()+" "+i+ "\r");
	}
	System.out.println();
}
}