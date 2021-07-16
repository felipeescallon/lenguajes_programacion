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
    int i = 0;

    do
    {
      i = m.cálculos(getName()); // adquirir datos
    }
    while (i < m.tamaño);
  }
}
//////////////////////////////////////////////////////////////////
