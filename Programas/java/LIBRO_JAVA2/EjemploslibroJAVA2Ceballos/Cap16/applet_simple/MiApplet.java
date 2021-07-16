import java.awt.*;    // necesaria para Graphics
import java.applet.*; // necesaria para Applet
import java.util.*;   // necesaria para StringTokenizer

public class MiApplet extends Applet
{
  private StringBuffer texto_a_mostrar;

  private void anadir(String str)
  {
    texto_a_mostrar.append(str);
    repaint();
  }

  // Punto de entrada:like the main()
  public void init()
  {
    texto_a_mostrar = new StringBuffer();
    anadir("Iniciando el applet ...,");
  }

  public void start()
  {
    anadir("Arrancando el applet ...,");
  }

  public void stop()
  {
    anadir("Deteniendo el applet ...,");
  }

  public void paint(Graphics g)
  {
    // Dibujar un rectángulo alrededor del contenedor
    g.draw3DRect(0, 0, getSize().width-1, getSize().height-1,false);
    // Dibujar el texto que hay actualmente en texto_a_mostrar
    // poniendo cada frase debajo de la anterior<=>uso a StringTokenizer
    StringTokenizer cadena;
    cadena = new StringTokenizer(texto_a_mostrar.toString(), ",");//delimitador:","
    int i = 1;
    while (cadena.hasMoreTokens())
    {
      g.drawString(cadena.nextToken(), 10, 20*i);
      i++;
    }
  }
}
