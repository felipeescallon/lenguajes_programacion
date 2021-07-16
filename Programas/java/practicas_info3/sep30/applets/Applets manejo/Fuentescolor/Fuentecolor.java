import java.awt.*;
import java.applet.*;

public class Fuentecolor extends Applet
{
private String correo_e;
private long telefono;
private Font fuente;
private Color colorFondo;
private Color colorTexto;

public void init()
{
correo_e="Malparido";
telefono=8231301;

fuente=new Font("Currier new", Font.BOLD,14);

colorFondo=new Color(225,225,0);
colorTexto=new Color(0,0,128);

}

public void paint(Graphics g)
{
	g.setColor(colorFondo);
	g.fillRect(0,0,getSize().width,getSize().height);
	g.setColor(colorTexto);
	g.setFont(fuente);
	g.drawString("Correo electronico "+correo_e, 10, 20);
	g.drawString("telefono "+telefono, 10, 40);
}

}		
	