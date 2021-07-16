import java.awt.*;
import java.applet.*;

public class MiApplet extends Applet
{
  private String correo_e;
  private long tel�fono;
  
  public void init()
  {
    correo_e = getParameter("CORREO_E");
    if (correo_e == null) correo_e = "@";
    String stel = getParameter("TELEFONO");
    if (stel == null) stel = "0";
    tel�fono = Long.parseLong(stel);
  }
  
  public void paint(Graphics g)
  {
    g.drawString("Correo electr�nico: " + correo_e, 10, 20);
    g.drawString("Tel�fono:           " + tel�fono, 10, 40);
  }
}
