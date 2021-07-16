import java.awt.*;
import java.applet.*;
import java.net.*;

public class MiApplet extends Applet
{
  private String correo_e;
  private long teléfono;
  private Font fuente;
  private Color colorFondo;
  private Color colorTexto;
  private Image imgTfno, imgCorreo;
  private AudioClip sonido;
  
  public void init()
  {
    // Recoger parámetros
    correo_e = getParameter("CORREO_E");
    if (correo_e == null) correo_e = "@";
    String stel = getParameter("TELEFONO");
    if (stel == null) stel = "0";
    teléfono = Long.parseLong(stel);
    
    // Fuente para el texto
    fuente = new Font("Monospaced", Font.PLAIN, 14);
    
    // Colores
    colorFondo = new Color(255, 255, 255); // blanco
    colorTexto = new Color(0, 0, 128);   // azul
    
    // Imágenes
    imgTfno = getImage(getDocumentBase(), "recursos/telefono.gif");
    imgCorreo = getImage(getDocumentBase(), "recursos/correo.gif");
    
    // Sonido de fondo
    sonido = getAudioClip(getDocumentBase(), "recursos/sonidox.midi");
 }
  
  public void start()
  {
    // Reproducir "sonido" indefinidamente
    sonido.loop();
  }
  
  public void stop()
  {
    // Detener "sonido"
    sonido.stop();
  }
  
  public void paint(Graphics g)
  {
    // Color para el fondo
    g.setColor(colorFondo); // color con el que se pintará el fondo
    // Pintar el fondo
    g.fillRect(0, 0, getSize().width, getSize().height);
    // Color para el texto
    g.setColor(colorTexto); // color con el que se pintará el texto
    // Fuente para pintar el texto
    g.setFont(fuente);
    // Mostrar imágenes y pintar el texto
    g.drawImage(imgCorreo, 10, 40, this);
    g.drawString(correo_e, 60, 70);
    g.drawImage(imgTfno, 10, 80, this);
    g.drawString(teléfono + "", 60, 100);
  }
}
