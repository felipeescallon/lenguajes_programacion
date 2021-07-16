//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos. Hilo consumidor.
//
public class Consumidor extends Thread
{
  private CMensaje mensaje;      // mensaje a mostrar

  public Consumidor(CMensaje c)  // constructor
  {
    mensaje = c;
  }

  public void run()
  {
    String msj;
    
    while (true)
    {
      msj = mensaje.obtener(); // obtiene el �ltimo mensaje
      System.out.println("Consumidor " + getName() +
                         " obtuvo:   " + msj);
    }
  }
}
//////////////////////////////////////////////////////////////////

