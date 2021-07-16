//////////////////////////////////////////////////////////////////
// Sincronización de hilos. Sección crítica.
//
public class CDatos
{
  // Atributos
  private double[] dato;
  private int ind = 0;
  public int tamaño;
  
  // Métodos
  public CDatos(int n)
  {
    if (n < 1) n = 10;
    tamaño = n;
    dato = new double[n];
  }

  public double obtener(int i)
  {
    return dato[i];
  }

  public void asignar(double x, int i)
  {
    dato[i] = x;
  }

  public synchronized int cálculos(String hilo)
  {
    if (ind >= tamaño) return tamaño;
    double x = Math.random();
    System.out.println(hilo + " tomó la muestra " + ind);
    asignar(x, ind);
    ind++;
    return ind;
  }
}
//////////////////////////////////////////////////////////////////
