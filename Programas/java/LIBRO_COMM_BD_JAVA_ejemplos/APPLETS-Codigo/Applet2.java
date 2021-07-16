import java.applet.Applet;
import java.awt.*;

public class Applet2 extends Applet {

  private String Mensaje="";
  private Panel PanelSuperior = new Panel();
  private Panel PanelInferior = new Panel();
  private Label EtiquetaSuperior=new Label();
  private Label EtiquetaInferior=new Label();

  public void init() {
    this.setLayout(new GridLayout(2,1));
    this.add(PanelSuperior);
    this.add(PanelInferior);
    PanelSuperior.add(EtiquetaSuperior);
    PanelInferior.add(EtiquetaInferior);
    EtiquetaSuperior.setText("Applet Cargado");
  }

  public void start() {
    EtiquetaInferior.setText("Applet Inicializado");
  }

}