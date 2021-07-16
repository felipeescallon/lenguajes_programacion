//////////////////////////////////////////////////////////////////
// Sincronización de hilos. Trabajar con secciones críticas.
//
public class CAdquirirDatos extends Thread
{
  private CDatos m;    // objeto para almacenar los datos

  public CAdquirirDatos(CDatos mdatos) // constructor
  {
    m = mdatos;
  }

  public void run()
  {
    double x;
    do
    {
      synchronized (m)
      {
        if (m.ind >= m.tamaño) return;
        x = Math.random();
        System.out.println(getName() + " tomó la muestra " + m.ind);
        m.asignar(x, m.ind);
        m.ind++;
      }
    }
    while (m.ind < m.tamaño);
  }
}
//////////////////////////////////////////////////////////////////
