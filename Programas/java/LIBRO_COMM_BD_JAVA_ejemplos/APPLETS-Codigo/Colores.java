import java.awt.*;

public class Colores {

  private int NumColores;
  private Button[][] BotonesColores;
  private Panel PanelColores = new Panel(new GridLayout(NumColores,4));

  Colores(int NumColores) {
    this.NumColores=NumColores;
    BotonesColores = new Button[NumColores][4];

    for (int c=0;c<NumColores;c++) {
      for (int col=0;col<=3;col++) {
        BotonesColores[c][col] = new Button();
        PanelColores.add(BotonesColores[c][col]);
      }
      int Factor = c*(255/NumColores);
      BotonesColores[c][0].setBackground(new Color(255,Factor,Factor));
      BotonesColores[c][1].setBackground(new Color(Factor,255,Factor));
      BotonesColores[c][2].setBackground(new Color(Factor,Factor,255));
      BotonesColores[c][3].setBackground(new Color(Factor,Factor,Factor));
    }
  }

  public Panel DamePanel() {
    return PanelColores;
  }

  public Button[][] DameBotones() {
    return BotonesColores;
  }

}