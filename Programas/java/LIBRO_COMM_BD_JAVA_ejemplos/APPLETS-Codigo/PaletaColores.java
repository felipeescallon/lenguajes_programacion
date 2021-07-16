import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PaletaColores extends Applet {

  private Colores Paleta;
  private Button[][] Botones;
  private TextArea AreaDeTexto;

  public void init() {
    int NumColores = Integer.parseInt(getParameter("NumeroDeColores"));
    Paleta = new Colores(NumColores);
    Botones = Paleta.DameBotones();
    AreaDeTexto = new TextArea();
    this.setLayout(new GridLayout(1,2));
    add(Paleta.DamePanel());
    add(AreaDeTexto);

     for (int i=0;i<NumColores;i++)
      for (int j=0;j<=3;j++)
        Botones[i][j].addMouseListener(new CambioColor());
  }


  private class CambioColor extends MouseAdapter {

    public void mouseClicked(MouseEvent e) {
      Button Boton = (Button) e.getSource();
      AreaDeTexto.setBackground(Boton.getBackground());
    } 

  }

}