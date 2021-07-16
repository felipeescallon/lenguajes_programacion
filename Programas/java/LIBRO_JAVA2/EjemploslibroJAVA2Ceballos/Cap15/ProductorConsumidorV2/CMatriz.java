//////////////////////////////////////////////////////////////////
// Sincronización de hilos: wait y notify.
//
public class CMatriz
{
  private int[] m;
  private int indProd = 0; // índice productor
  private int indCons = 0; // índice consumidor
  private int elementosVacíos, elementosLlenos;
  
  public CMatriz(int n)
  {
    if (n < 1) n = 10;
    m = new int[n];
    elementosVacíos = m.length;
    elementosLlenos = 0;
  }

  public synchronized void almacenar(int num)
  {
    // Esperar a que haya elementos vacíos
    while (elementosVacíos == 0)
    {
      try
      {
        wait(); // el hilo se pone a dormir y cede el monitor
      }
      catch (InterruptedException e) { }
    }
    elementosVacíos--;
    elementosLlenos++;
    System.out.print("vacíos: " + elementosVacíos + ", llenos: " +
                     elementosLlenos + "  \r");
    m[indProd] = num;
    indProd = (indProd + 1) % m.length;
    // Despertar hilos;
    notifyAll();
  }

  public synchronized int obtener()
  {
    // Esperar a que haya elementos llenos
    while (elementosLlenos == 0)
    {
      try
      {
        wait(); // el hilo se pone a dormir y cede el monitor
      }
      catch (InterruptedException e) { }
    }
    elementosVacíos++;
    elementosLlenos--;
    System.out.print("vacíos: " + elementosVacíos + ", llenos: " +
                     elementosLlenos + "  \r");
    int num =  m[indCons];
    indCons = (indCons + 1) % m.length;
    notifyAll();
    return num;
  }
}
//////////////////////////////////////////////////////////////////
