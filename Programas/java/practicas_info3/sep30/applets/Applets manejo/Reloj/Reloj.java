import java.applet.*;
import java.awt.*;
import java.util.*;
import java.text.DateFormat;

public class Reloj extends Applet implements Runnable
{
  private Thread hilo=null;
  private Font fuente;
  private String horaActual="00:00:00";

	public void intit()
	{
	    fuente = new Font("Arial",Font.BOLD,24);
	}

	public void start()
	{
	    if(hilo==null)
		{
		hilo=new Thread(this,"Reloj");
		hilo.start();
		}
	}
	
	public void run()
	{
	 Thread hiloActual = Thread.currentThread();
	 while (hilo==hiloActual)
	{
		// Obtener la hora actual
	Calendar cal = Calendar.getInstance();
	Date hora=cal.getTime();
	DateFormat df = DateFormat.getTimeInstance();
	horaActual=df.format(hora);
	repaint();
		try
		{
	 	Thread.sleep(1000);
		}
		catch(InterruptedException e){}
	}
	}
	

	public void paint(Graphics g)
	{
	  // Dibujar un rectángulo alrededor del contenedor
		g.draw3DRect(1,1,getSize().width-3,getSize().height-3,false);
	  // Establecer la fuente 
		g.setFont(fuente);
	  // Mostrar la hora
		g.drawString(horaActual,14,40);
	}

	public void stop()
	{
		hilo=null;
	}
}
	