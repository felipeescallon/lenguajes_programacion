//////////////////////////////////////////////////////////////////
// Lista lineal simplemente enlazada
//
public class CListaLinealSE
{
  // p: referencia al primer elemento de la lista
  private CElemento p = null;

  // Elemento de una lista lineal simplemente enlazada
  private class CElemento
  {
    // Atributos
    private Object datos;
    private CElemento siguiente; // siguiente elemento
    // M�todos
    private CElemento() {}       // constructor
  }

  public CListaLinealSE() {}     // constructor
  
  // A�adir un elemento al principio de la lista
  public void a�adirAlPrincipio(Object obj)
  {
    CElemento q = new CElemento();
    q.datos = obj;   // asignaci�n de valores
    q.siguiente = p; // reasignaci�n de referencias
    p = q;
  }
  
  public Object obtener(int i)
  {
    if (p == null)
    {
      System.err.println("lista vac�a");
      return null;
    }
    
    CElemento q = p; // referencia al primer elemento
    if (i >= 0)
    {
      // Posicionarse en el elemento i
      for (int n = 0; q != null && n < i; n++)
        q = q.siguiente;
      // Retornar los datos
      if (q != null) return q.datos;
    }
    // �ndice fuera de l�mites
    return null;
  }
}
//////////////////////////////////////////////////////////////////
