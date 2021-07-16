import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EstructuraAplicacionSwing extends JFrame
{
  // Referencias a los componentes

  // Otras referencias

  private EstructuraAplicacionSwing(String t�tulo) // constructor
  {
    super(t�tulo); // t�tulo de la ventana principal
    iniciarComponentes();
    // Ajustar el tama�o de la ventana al m�nimo
    pack();
  }

  private void iniciarComponentes()
  {
    /* Este m�todo es llamado desde el constructor para iniciar la
     * ventana principal, tambi�n llamada formulario.
     */

    // Permitir que la ventana de la aplicaci�n reciba los eventos
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
    // Salir de la aplicaci�n
    System.exit(0);
  }

  public static void main(String[] args)
  {
    EstructuraAplicacionSwing miApp = new EstructuraAplicacionSwing("Aplicaci�n Swing");
    miApp.show();
  }
}
