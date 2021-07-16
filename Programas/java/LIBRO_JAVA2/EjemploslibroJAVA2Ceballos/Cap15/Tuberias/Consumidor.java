import java.io.*;
//////////////////////////////////////////////////////////////////
// Tuberías. Hilo consumidor.
//
public class Consumidor extends Thread
{
  private PipedReader receptor = null;
  private BufferedReader flujoE = null;
  
  public Consumidor(PipedReader re)  // constructor
  {
    receptor = re;
    flujoE = new BufferedReader(receptor);
  }

  public void run()
  {
    while (true)
    {
      obtenerMensaje();
    }
  }
  
  public synchronized void obtenerMensaje()
  {
    String msj = null;

    try
    {
      msj = flujoE.readLine(); // obtener mensaje de la tubería
      System.out.println("Consumidor " + getName() +
                         " obtuvo:   " + msj);
    }
    catch (IOException ignorada) {}
  }
  
  protected void finalize() throws IOException
  {
    if (flujoE != null) { flujoE.close(); flujoE = null; }
    if (receptor != null) { receptor.close(); receptor = null; }
  }
}
//////////////////////////////////////////////////////////////////

