package PAD;

import javax.swing.UIManager;
import java.awt.*;

public class DTEA {
  boolean activar = false;
  GuiDTEA ventana;

  public DTEA() {
    ventana = new GuiDTEA();
    ventana.marco(ventana);
    if (activar) {
      ventana.pack();
    }
    else {
      ventana.validate();
    }

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = ventana.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    ventana.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    ventana.setVisible(true);
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new DTEA();
  }
}