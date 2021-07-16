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
    // Métodos
    private CElemento() {} // constructor
  }

  public CListaLinealSE() {} // constructor
  
  // Añadir un elemento al principio de la lista
  public void añadirAlPrincipio(double n)
  {
    CElemento q = new CElemento();
    q.dato = n;      // asignación de valores
    q.siguiente = p; // reasignación de referencias
    p = q;
  }
  
  public double obtener(int i)
  {
    if (p == null)
    {
      System.err.println("lista vacía");
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
    // Índice fuera de límites
    return Double.NaN;
  }
}
//////////////////////////////////////////////////////////////////
