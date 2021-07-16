//////////////////////////////////////////////////////////////////
// Sincronización de hilos. Hilo productor.
//
public class Productor extends Thread
{
  private CMensaje mensaje;    // último mensaje producido

  public Productor(CMensaje c) // constructor
  {
    mensaje = c;
  }

  public void run()
  {
    int númeroMsj; // número de mensaje
    
    while (true)
    {
      númeroMsj = (int)(Math.random() * 100);
      mensaje.almacenar(númeroMsj); // almacena el mensaje
      System.out.println("Productor  " + getName() +
                         " almacena: mensaje #" + númeroMsj);
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
