//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos. Hilo productor.
//
public class Productor extends Thread
{
  private CMensaje mensaje;    // �ltimo mensaje producido

  public Productor(CMensaje c) // constructor
  {
    mensaje = c;
  }

  public void run()
  {
    int n�meroMsj; // n�mero de mensaje
    
    while (true)
    {
      n�meroMsj = (int)(Math.random() * 100);
      mensaje.almacenar(n�meroMsj); // almacena el mensaje
      System.out.println("Productor  " + getName() +
                         " almacena: mensaje #" + n�meroMsj);
      try
      {
        int msegs = (int)(Math.random() * 100);
        // Poner a dormir el hilo hasta que se produzca el
        // siguiente mensaje.
        sleep(msegs);
      }
      catch (InterruptedException e) { }
    }
  }
}
//////////////////////////////////////////////////////////////////
