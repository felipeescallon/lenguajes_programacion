//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos. Secci�n cr�tica.
//
public class CDatos
{
  // Atributos
  private double[] dato;
  public int ind = 0;
  public int tama�o;
  
  // M�todos
  public CDatos(int n)
  {
    if (n < 1) n = 10;
    tama�o = n;
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
