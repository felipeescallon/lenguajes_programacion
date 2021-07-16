import java.io.*;
//////////////////////////////////////////////////////////////////
// Tuber�as. Hilo productor.
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
    int n�meroMsj;       // n�mero de mensaje
    String textoMensaje; // texto mensaje
    
    n�meroMsj = (int)(Math.random() * 100);
    // Suponer operaciones para buscar el mensaje en una tabla
    // de mensajes; resultado:
    textoMensaje = "mensaje #" + n�meroMsj;
    flujoS.println(textoMensaje); // enviar mensaje por la tuber�a
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
