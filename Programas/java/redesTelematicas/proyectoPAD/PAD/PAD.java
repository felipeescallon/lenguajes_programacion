package PAD;

import javax.swing.UIManager;
import java.awt.*;

public class PAD {
  boolean packFrame = false;
  GuiPAD frame;

  /**Construir la aplicaci�n*/
  public PAD() {
    frame = new GuiPAD();
    frame.marco(frame);
    //frame.conectar();
    //Validar marcos que tienen tama�os preestablecidos
    //Empaquetar marcos que cuentan con informaci�n de tama�o preferente �til
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
  /**M�todo Main*/
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


