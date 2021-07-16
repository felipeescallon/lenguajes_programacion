import java.applet.*;
import java.awt.*;
import java.lang.String;


//ASI FUNCIONA EL PROGRAMA
/*public class CHolamundo extends Applet
{
 public void paint(Graphics contexto)
	 {
	  Holamundo hm;
	  hm = new Holamundo();
	  String frase;
	  frase = hm.Escribirhm();
	  contexto.drawString(frase,50,30);
  	 }
}*/


//ASI TAMBIEN
public class CHolamundo extends Applet
{
 public void paint(Graphics contexto)
	 {
	  contexto.drawString(new Holamundo().Escribirhm(),20,20);
  	 }
}