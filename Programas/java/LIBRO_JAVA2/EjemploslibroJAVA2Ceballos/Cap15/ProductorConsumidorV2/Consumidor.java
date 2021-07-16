//////////////////////////////////////////////////////////////////
// Sincronización de hilos. Hilo consumidor.
//
public class Consumidor extends Thread
{
  private CMatriz matriz;
  private boolean continuar = true;
  
  public Consumidor(CMatriz m)  // constructor
  {
    matriz = m;
  }

  public void run()
  {
    int número;

    while (continuar)
    {
      número = matriz.obtener();
      //System.out.println("Consumidor " + getName() +
      //                   " obtuvo:   número " + número);
    }
  }

  public void terminar()
  {
    continuar = false;
  }
}
//////////////////////////////////////////////////////////////////

