package PAD;

import javax.swing.UIManager;
import java.awt.*;

public class PAD {
  boolean packFrame = false;
  GuiPAD frame;

  /**Construir la aplicación*/
  public PAD() {
    frame = new GuiPAD();
    frame.marco(frame);
    //frame.conectar();
    //Validar marcos que tienen tamaños preestablecidos
    //Empaquetar marcos que cuentan con información de tamaño preferente útil
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Centrar la ventana
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  public GuiPAD retornar_marco(){
   return frame;
  }
  /**Método Main*/
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    PAD pad=new PAD();
    GuiPAD m=pad.retornar_marco();
    m.conectar();
  }
}


