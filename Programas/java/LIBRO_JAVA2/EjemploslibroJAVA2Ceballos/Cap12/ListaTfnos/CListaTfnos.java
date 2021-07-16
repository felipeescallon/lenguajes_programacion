/////////////////////////////////////////////////////////////////
// Definici�n de la clase CListaTfnos.
//
import java.io.*;
public class CListaTfnos implements Serializable
{
  private CPersona[] listaTel�fonos; // matriz de objetos
  private int nElementos; // n�mero de elementos de la matriz
  
  private CPersona[] asignarMemoria(int nElementos)
  {
    try
    {
      return new CPersona[nElementos];
    }
    catch (OutOfMemoryError e)
    {
      System.out.println(e.getMessage());
      return listaTel�fonos;
    }
  }
  
  public CListaTfnos()
  {
    // Crear una lista vac�a
    nElementos = 0;
    listaTel�fonos = asignarMemoria(nElementos);
  }
  
  private void unElementoM�s(CPersona[] listaActual)
  {
    nElementos = listaActual.length;
    listaTel�fonos = asignarMemoria(nElementos + 1);
    // Copiar la lista actual
    for ( int i = 0; i < nElementos; i++ )
      listaTel�fonos[i] = listaActual[i];
    nElementos++;
  }
  
  private void unElementoMenos(CPersona[] listaActual)
  {
    if (listaActual.length == 0) return;
    int k = 0;
    nElementos = listaActual.length;
    listaTel�fonos = asignarMemoria(nElementos - 1);
    // Copiar la lista actual
    for ( int i = 0; i < nElementos; i++ )
      if (listaActual[i] != null)
        listaTel�fonos[k++] = listaActual[i];
    nElementos--;
  }
  
  public void ponerValorEn( int i, CPersona objeto )
  {
    if (i >= 0 && i < nElementos)
      listaTel�fonos[i] = objeto;
    else
      System.out.println("�ndice fuera de l�mites");
  }
  
  public CPersona valorEn( int i )
  {
    if (i >= 0 && i < nElementos)
      return listaTel�fonos[i];
    else
    {
      System.out.println("�ndice fuera de l�mites");
      return null;
    }
  }
  
  public int longitud() { return nElementos; }
  
  public void a�adir(CPersona obj)
  {
    unElementoM�s(listaTel�fonos);
    ponerValorEn( nElementos - 1, obj );
  }
  
  public boolean eliminar(long tel)
  {
    // Buscar el tel�fono y eliminar registro
    for ( int i = 0; i < nElementos; i++ )
      if (listaTel�fonos[i].obtenerTel�fono() == tel)
      {
        listaTel�fonos[i] = null;
        unElementoMenos(listaTel�fonos);
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
      nom = listaTel�fonos[i].obtenerNombre();
      if (nom == null) continue;
      // �str est� contenida en nom?
      if (nom.indexOf(str) > -1)
        return i;
    }
    return -1;
  }
}
