//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos. Hilo consumidor.
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
    int n�mero;

    while (continuar)
    {
      n�mero = matriz.obtener();
      //System.out.println("Consumidor " + getName() +
      //                   " obtuvo:   n�mero " + n�mero);
    }
  }

  public void terminar()
  {
    continuar = false;
  }
}
//////////////////////////////////////////////////////////////////

