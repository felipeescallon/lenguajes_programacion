import java.io.*;
import java.lang.*;


class ContadorAtras implements Runnable  
{

private Thread CuentaAtras;

public ContadorAtras(String nombre)
{
	CuentaAtras=new Thread(this);
	if(nombre!=null)
	CuentaAtras.setName(nombre);
	CuentaAtras.start();
}

public ContadorAtras()
{
this(null);
}

public void run()
{
	for(int i=1000; i>0; i--)
	{
	System.out.println("\t\t"+ CuentaAtras.getName()+" "+i+ "\r");
	}
	System.out.println();
}
}