//////////////////////////////////////////////////////////////////
// Sincronización de hilos. Sección crítica.
//
public class CDatos
{
  // Atributos
  private double[] dato;
  public int ind = 0;
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
}
//////////////////////////////////////////////////////////////////
