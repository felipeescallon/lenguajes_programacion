import java.io.*;
import java.lang.*;


class ContadorAdelante extends Thread  
{

private boolean continuar = true;

public ContadorAdelante()
{
start();
}


public ContadorAdelante(String nombreHilo)
{
setName(nombreHilo);
start();
}

public void run()
{
	int i=1;
	while(continuar)
	{
		System.out.println(getName()+" "+ i++ + "\r");
	}
	System.out.println();
}

public void terminar()
{
	continuar=false;
}
}
