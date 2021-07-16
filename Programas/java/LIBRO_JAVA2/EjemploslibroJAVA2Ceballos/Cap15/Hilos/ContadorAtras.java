//////////////////////////////////////////////////////////////////
// Clase asociada con un hilo.
//
public class ContadorAtras implements Runnable
{
  private Thread cuentaAtras;
  public ContadorAtras(String nombre) // constructor
  {
    cuentaAtras = new Thread(this);   // objeto de la clase Thread
    if (nombre != null) cuentaAtras.setName(nombre);
    cuentaAtras.start(); // el hilo ejecuta el método run de
  }                      // ContadorAtrás
 // public ContadorAtras() { this(null); } // constructor

  public void run()
  {
    for (int i = 1000; i > 0; i--)
    {
      System.out.print("\t\t" + cuentaAtras.getName() + " " + i + " \r");
    }
    System.out.println();
  }
}
//////////////////////////////////////////////////////////////////
