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
    int i = 0;

    do
    {
      i = m.c�lculos(getName()); // adquirir datos
    }
    while (i < m.tama�o);
  }
}
//////////////////////////////////////////////////////////////////
