import java.io.*;
//////////////////////////////////////////////////////////////////
// Tuberías. Hilo productor.
//
public class Productor extends Thread
{
  private PipedWriter emisor = null;
  private PrintWriter flujoS = null;

  public Productor(PipedWriter em) // constructor
  {
    emisor = em;
    flujoS = new PrintWriter(emisor);
  }

  public void run()
  {
    while (true)
    {
      almacenarMensaje();
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
  
  public synchronized void almacenarMensaje()
  {
    int númeroMsj;       // número de mensaje
    String textoMensaje; // texto mensaje
    
    númeroMsj = (int)(Math.random() * 100);
    // Suponer operaciones para buscar el mensaje en una tabla
    // de mensajes; resultado:
    textoMensaje = "mensaje #" + númeroMsj;
    flujoS.println(textoMensaje); // enviar mensaje por la tubería
    System.out.println("Productor  " + getName() +
                       " almacena: " + textoMensaje);
  }
  
  protected void finalize() throws IOException
  {
    if (flujoS != null) { flujoS.close(); flujoS = null; }
    if (emisor != null) { emisor.close(); emisor = null; }
  }
}
//////////////////////////////////////////////////////////////////
