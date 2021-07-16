import java.io.*;
import java.lang.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
 

public class Primero extends Applet
{
private StringBuffer texto_a_mostrar;

private void añadir(String str)
{
	texto_a_mostrar.append(str);
	repaint();
}

public void init()
{
	texto_a_mostrar=new StringBuffer();
	añadir("Iniciando el Applet ");  
}

public void start()
{
	añadir("Arrancando el aplet");
}

public void stop()
{
	añadir("Deteniendo el aplet");
}

public void paint(Graphics g)
{
	g.draw3DRect(0,0,getSize().width-1,getSize().height-1,false);
	StringTokenizer cadena;
	cadena= new StringTokenizer(texto_a_mostrar.toString(),",");
	int i=1;
	while(cadena.hasMoreTokens())
		{
		g.drawString(cadena.nextToken(),10,20*i);
		i++;
		}	
}
}
