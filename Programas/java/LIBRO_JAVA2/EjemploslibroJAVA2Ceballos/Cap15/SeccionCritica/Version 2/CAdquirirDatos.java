//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos. Trabajar con secciones cr�ticas.
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
        if (m.ind >= m.tama�o) return;
        x = Math.random();
        System.out.println(getName() + " tom� la muestra " + m.ind);
        m.asignar(x, m.ind);
        m.ind++;
      }
    }
    while (m.ind < m.tama�o);
  }
}
//////////////////////////////////////////////////////////////////
