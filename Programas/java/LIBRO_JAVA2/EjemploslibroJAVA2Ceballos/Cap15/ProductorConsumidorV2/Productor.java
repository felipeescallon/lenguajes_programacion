//////////////////////////////////////////////////////////////////
// Sincronización de hilos. Hilo productor.
//
public class Productor extends Thread
{
  private CMatriz matriz;
  private boolean continuar = true;
  
  public Productor(CMatriz m) // constructor
  {
    matriz = m;
  }

  public void run()
  {
    int número; // número producido

    while (continuar)
    {
      número = (int)(Math.random() * 100);
      matriz.almacenar(número); // almacena el número
      //System.out.println("Productor  " + getName() +
      //                   " almacena: número " + número);
    }
  }

  public void terminar()
  {
    continuar = false;
  }
}
//////////////////////////////////////////////////////////////////
