import java.awt.*;

public class PruebaAccesoBD {

  public static void main(String[] args) {

    GUIAccesoBD Instancia = new GUIAccesoBD();

    Panel MiPanel = Instancia.DamePanel();
    Frame MiMarco = new Frame();
    MiMarco.add(MiPanel);
    MiMarco.setSize(700,200);
    MiMarco.setVisible(true);     
  }
}