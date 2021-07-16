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
  
  public void mostrarTodos()
  {
    // Recorrer la lista
    CElemento q = p; // referencia al primer elemento
    while (q != null)
    {
      System.out.print(q.dato + " ");
      q = q.siguiente;
    }
  }
}
//////////////////////////////////////////////////////////////////
