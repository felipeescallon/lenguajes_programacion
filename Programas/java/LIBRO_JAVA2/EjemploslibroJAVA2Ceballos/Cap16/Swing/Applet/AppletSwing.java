import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppletSwing extends JApplet
{
  // Referencias a los componentes
  private JLabel etiqueta;
  private JButton bot�n;
  
  // Otras referencias
  private static String mensaje = "���Hola mundo!!!";

  public void init()
  {
    try
    {
      // Aspecto y percepci�n de la interfaz gr�fica
      UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e)
    {
      System.out.println("No se pudo establecer el aspecto deseado: " + e);
    }
    iniciarComponentes();
  }

  private void iniciarComponentes()
  {
    // Crear una etiqueta con el texto centrado
    etiqueta = new JLabel();
    etiqueta.setHorizontalAlignment(JLabel.CENTER);
    // Crear un bot�n
    bot�n = new JButton("Haga clic aqu�");
    // Establecer como tecla aceleradora la C. Entonces, pulsar Alt+C
    // ser� equivalente ha hacer clic, sobre el bot�n.
    bot�n.setMnemonic(KeyEvent.VK_C);
    // Asignar al bot�n una descripci�n abreviada
    bot�n.setToolTipText("bot�n de pulsaci�n");
    // Permitir que el bot�n responda a los eventos de acci�n
    ActionListener al =
      new ActionListener()
      {
        // Este m�todo se ejecutar� cuando se haga clic en "bot�n"
        public void actionPerformed(ActionEvent evento)
        {
          Object obj = evento.getSource();
          if (obj == bot�n)
            mostrarMensaje();
        }
      };
    bot�n.addActionListener(al);
    
    // Crear un panel para colocar los controles
    JPanel panel = new JPanel();
    // Introducimos un borde sin pintar alrededor de los controles:
    // createEmptyBorder(top, left, bottom, right)
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    // Establecer un administrador de dise�o de cuadr�cula
    panel.setLayout(new GridLayout(0, 1));
    // Los controles se a�aden en columna (filas = 0)
    panel.add(etiqueta);
    panel.add(bot�n);
    
    // A�adir los componentes al contenedor de la aplicaci�n
    getContentPane().add(panel, BorderLayout.CENTER);
  }
  
  private void mostrarMensaje()
  {
    // Mostrar el "mensaje" en la "etiqueta"
    etiqueta.setText(mensaje);
  }
}