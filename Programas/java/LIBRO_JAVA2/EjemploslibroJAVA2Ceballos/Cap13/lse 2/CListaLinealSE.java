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
    private double dato;
    private CElemento siguiente; // siguiente elemento
    // M?todos
    private CElemento() {} // constructor
  }

  public CListaLinealSE() {} // constructor
  
  // A?adir un elemento al principio de la lista
  public void a?adirAlPrincipio(double n)
  {
    CElemento q = new CElemento();
    q.dato = n;      // asignaci?n de valores
    q.siguiente = p; // reasignaci?n de referencias
    p = q;
  }
  
  public double obtener(int i)
  {
    if (p == null)
    {
      System.err.println("lista vac?a");
      return Double.NaN;
    }
    
    CElemento q = p; // referencia al primer elemento
    if (i >= 0)
    {
      // Posicionarse en el elemento i
      for (int n = 0; q != null && n < i; n++)
        q = q.siguiente;
      // Retornar el dato
      if (q != null) return q.dato;
    }
    // ?ndice fuera de l?mites
    return Double.NaN;
  }
}
//////////////////////////////////////////////////////////////////
