import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplicacionSwing extends JFrame
{
  // Referencias a los componentes
  private JLabel etiqueta;
  private JButton botón;
  
  // Otras referencias
  private static String mensaje = "¡¡¡Hola mundo!!!";
  
  private AplicacionSwing(String título) // constructor
  {
    super(título); // título de la ventana principal
    iniciarComponentes();
    // Ajustar el tamaño de la ventana al mínimo
    pack();
  }

  private void iniciarComponentes()
  {
    // Crear una etiqueta con el texto centrado
    etiqueta = new JLabel();
    etiqueta.setHorizontalAlignment(JLabel.CENTER);
    // Crear un botón
    botón = new JButton("Haga clic aquí");
    // Establecer como tecla aceleradora la C. Entonces, pulsar Alt+C
    // será equivalente ha hacer clic, sobre el botón.
    botón.setMnemonic(KeyEvent.VK_C);
    // Asignar al botón una descripción abreviada
    botón.setToolTipText("botón de pulsación");
    // Permitir que el botón responda a los eventos de acción
    ActionListener al =
      new ActionListener()
      {
        // Este método se ejecutará cuando se haga clic en "botón"
        public void actionPerformed(ActionEvent evento)
        {
          Object obj = evento.getSource();
          if (obj == botón)
            mostrarMensaje();
        }
      };
    botón.addActionListener(al);
    
    // Crear un panel para colocar los controles
    JPanel panel = new JPanel();
    // Introducimos un borde sin pintar alrededor de los controles:
    // createEmptyBorder(top, left, bottom, right)
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    // Establecer un administrador de diseño de cuadrícula
    panel.setLayout(new GridLayout(0, 1));
    // Los controles se añaden en columna (filas = 0)
    panel.add(etiqueta);
    panel.add(botón);
    
    // Añadir los componentes al contenedor de la aplicación
    getContentPane().add(panel, BorderLayout.CENTER);

    // Permitir que la ventana de la aplicación responda a los
    // eventos de ventana (p.e. cerrar la ventana)
    addWindowListener( 
      new WindowAdapter()
      {
        public void windowClosing(WindowEvent evento)
        {
          cerrarVentana();
        }
      } );
  }
  
  private void mostrarMensaje()
  {
    // Mostrar el "mensaje" en la "etiqueta"
    etiqueta.setText(mensaje);
  }

  private void cerrarVentana()
  {
    // Salir de la aplicación
    System.exit(0);
  }

  public static void main(String[] args)
  {
    try
    {
      // Aspecto y percepción de la interfaz gráfica
      UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e)
    {
      System.out.println("No se pudo establecer el aspecto deseado: " + e);
    }
    AplicacionSwing vPpal = new AplicacionSwing("Aplicación Swing");
    vPpal.show();
  }
}