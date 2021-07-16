import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EstructuraAplicacionSwing extends JFrame
{
  // Referencias a los componentes

  // Otras referencias

  private EstructuraAplicacionSwing(String título) // constructor
  {
    super(título); // título de la ventana principal
    iniciarComponentes();
    // Ajustar el tamaño de la ventana al mínimo
    pack();
  }

  private void iniciarComponentes()
  {
    /* Este método es llamado desde el constructor para iniciar la
     * ventana principal, también llamada formulario.
     */

    // Permitir que la ventana de la aplicación reciba los eventos
    // de ventana (p.e. cerrar la ventana)
    addWindowListener( 
      new WindowAdapter()
      {
        public void windowClosing(WindowEvent evento)
        {
          cerrarVentana(evento);
        }
      } );
  }
  
  private void cerrarVentana(WindowEvent evento)
  {
    // Salir de la aplicación
    System.exit(0);
  }

  public static void main(String[] args)
  {
    EstructuraAplicacionSwing miApp = new EstructuraAplicacionSwing("Aplicación Swing");
    miApp.show();
  }
}
