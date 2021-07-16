/////////////////////////////////////////////////////////////////
// Definición de la clase CListaTfnos.
//
import java.io.*;
public class CListaTfnos implements Serializable
{
  private CPersona[] listaTeléfonos; // matriz de objetos
  private int nElementos; // número de elementos de la matriz
  
  private CPersona[] asignarMemoria(int nElementos)
  {
    try
    {
      return new CPersona[nElementos];
    }
    catch (OutOfMemoryError e)
    {
      System.out.println(e.getMessage());
      return listaTeléfonos;
    }
  }
  
  public CListaTfnos()
  {
    // Crear una lista vacía
    nElementos = 0;
    listaTeléfonos = asignarMemoria(nElementos);
  }
  
  private void unElementoMás(CPersona[] listaActual)
  {
    nElementos = listaActual.length;
    listaTeléfonos = asignarMemoria(nElementos + 1);
    // Copiar la lista actual
    for ( int i = 0; i < nElementos; i++ )
      listaTeléfonos[i] = listaActual[i];
    nElementos++;
  }
  
  private void unElementoMenos(CPersona[] listaActual)
  {
    if (listaActual.length == 0) return;
    int k = 0;
    nElementos = listaActual.length;
    listaTeléfonos = asignarMemoria(nElementos - 1);
    // Copiar la lista actual
    for ( int i = 0; i < nElementos; i++ )
      if (listaActual[i] != null)
        listaTeléfonos[k++] = listaActual[i];
    nElementos--;
  }
  
  public void ponerValorEn( int i, CPersona objeto )
  {
    if (i >= 0 && i < nElementos)
      listaTeléfonos[i] = objeto;
    else
      System.out.println("Índice fuera de límites");
  }
  
  public CPersona valorEn( int i )
  {
    if (i >= 0 && i < nElementos)
      return listaTeléfonos[i];
    else
    {
      System.out.println("Índice fuera de límites");
      return null;
    }
  }
  
  public int longitud() { return nElementos; }
  
  public void añadir(CPersona obj)
  {
    unElementoMás(listaTeléfonos);
    ponerValorEn( nElementos - 1, obj );
  }
  
  public boolean eliminar(long tel)
  {
    // Buscar el teléfono y eliminar registro
    for ( int i = 0; i < nElementos; i++ )
      if (listaTeléfonos[i].obtenerTeléfono() == tel)
      {
        listaTeléfonos[i] = null;
        unElementoMenos(listaTeléfonos);
        return true;
      }
    return false;
  }
  
  public int buscar(String str, int pos)
  {
    String nom;
    if (str == null) return -1;
    if (pos < 0) pos = 0;
    for ( int i = pos; i < nElementos; i++ )
    {
      nom = listaTeléfonos[i].obtenerNombre();
      if (nom == null) continue;
      // ¿str está contenida en nom?
      if (nom.indexOf(str) > -1)
        return i;
    }
    return -1;
  }
}
