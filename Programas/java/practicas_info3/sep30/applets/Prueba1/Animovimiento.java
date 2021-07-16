import java.lang.*;
import java.io.*;

class Animovimiento implements Runnable
{
private ClaseReloj corre;
private Thread hilo1=null;

public Animovimiento(ClaseReloj corriendo)
{
	hilo1=new Thread(this, "hilo1");
	corre=corriendo;
	hilo1.start();
}

public void run()
{
	Thread hiloActual1=Thread.currentThread();
	while(hilo1==hiloActual1)
	{
		for(;;)
			{
			corre.repaint();
			corre.logica();
			}
		
	}
}

public void stop()
{
hilo1=null;
}

}
