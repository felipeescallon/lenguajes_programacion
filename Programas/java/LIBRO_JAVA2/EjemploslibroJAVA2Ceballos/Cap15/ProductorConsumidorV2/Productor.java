//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos. Hilo productor.
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
    int n�mero; // n�mero producido

    while (continuar)
    {
      n�mero = (int)(Math.random() * 100);
      matriz.almacenar(n�mero); // almacena el n�mero
      //System.out.println("Productor  " + getName() +
      //                   " almacena: n�mero " + n�mero);
    }
  }

  public void terminar()
  {
    continuar = false;
  }
}
//////////////////////////////////////////////////////////////////
