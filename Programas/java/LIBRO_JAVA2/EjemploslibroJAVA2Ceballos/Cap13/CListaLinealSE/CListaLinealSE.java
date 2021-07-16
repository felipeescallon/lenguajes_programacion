//////////////////////////////////////////////////////////////////
// Lista lineal simplemente enlazada
//
public class CListaLinealSE
{
  // p: referencia al primer elemento de la lista.
  // Es el elemento de cabecera.
  private CElemento p = null;

  // Elemento de una lista lineal simplemente enlazada
  private class CElemento
  {
    // Atributos
    private Object datos;
    private CElemento siguiente; // siguiente elemento
    // Métodos
    private CElemento() {} // constructor
    private CElemento(Object d, CElemento s) // constructor
    {
      datos = d;
      siguiente = s;
    }
  }

  public CListaLinealSE() {} // constructor
  
  public int tamaño()
  {
    // Devuelve el número de elementos de la lista
    CElemento q = p; // referencia al primer elemento
    int n = 0;       // número de elementos
    while (q != null)
    {
      n++;
      q = q.siguiente;
    }
    return n;
  }
  
  public boolean añadir(int i, Object obj)
  {
    // Añadir un elemento en la posición i
    int númeroDeElementos = tamaño();
    if (i > númeroDeElementos || i < 0)
    {
      System.err.println("índice fuera de límites");
      return false;
    }
    
    // Crear el elemento a añadir
    CElemento q = new CElemento(obj, null);
    
    // Si la lista referenciada por p está vacía, añadirlo sin más 
    if (númeroDeElementos == 0)
    {
      // Añadir el primer elemento
      p = q;
      return true;
    }
    
    // Si la lista no está vacía, encontrar el punto de inserción
    CElemento elemAnterior = p;
    CElemento elemActual = p;
    // Posicionarse en el elemento i
    for (int n = 0; n < i; n++)
    {
      elemAnterior = elemActual;
      elemActual = elemActual.siguiente;
    }
    // Dos casos:
    // 1) Insertar al principio de la lista
    // 2) Insertar después del anterior (incluye insertar al final)
    if ( elemAnterior == elemActual ) // insertar al principio
    {
      q.siguiente = p;
      p = q; // cabecera
    }
    else // insertar después del anterior
    {
      q.siguiente = elemActual;
      elemAnterior.siguiente = q;
    }
    return true;
  }
  
  public boolean añadirAlPrincipio(Object obj)
  {
    // Añadir un elemento al principio
    return añadir(0, obj);
  }
  
  public boolean añadirAlFinal(Object obj)
  {
    // Añadir un elemento al final
    return añadir(tamaño(), obj);
  }
  
  public Object borrar(int i)
  {
    // Borrar el elemento de la posición i
    int númeroDeElementos = tamaño();
    if (i >= númeroDeElementos || i < 0)
      return null;
    
    // Entrar en la lista y encontrar el índice del elemento
    CElemento elemAnterior = p;
    CElemento elemActual = p;
    // Posicionarse en el elemento i
    for (int n = 0; n < i; n++)
    {
      elemAnterior = elemActual;
      elemActual = elemActual.siguiente;
    }
    // Dos casos:
    // 1) Borrar el primer elemento de la lista
    // 2) Borrar el siguiente a elemAnterior (elemActual)
    if ( elemActual == p ) // 1)
      p = p.siguiente; // cabecera
    else // 2)
      elemAnterior.siguiente = elemActual.siguiente;
    
    return elemActual.datos; // retornar el elemento borrado.
    
    // El elemento referenciado por elemActual será enviado a la
    // basura (borrado) al quedar desreferenciado, por ser
    // elemActual una variable local.
  }
  
  public Object borrarPrimero()
  {
    // Borrar el primer elemento
    return borrar(0);
  }
  
  public Object borrarÚltimo()
  {
    // Borrar el último elemento
    return borrar(tamaño() - 1);
  }
  
  public Object obtener(int i)
  {
    // Obtener el elemento de la posición i
    int númeroDeElementos = tamaño();
    if (i >= númeroDeElementos || i < 0)
      return null;
    
    CElemento q = p; // referencia al primer elemento
    // Posicionarse en el elemento i
    for (int n = 0; n < i; n++)
      q = q.siguiente;
    
    // Retornar los datos
    return q.datos;
  }

  public Object obtenerPrimero()
  {
    // Retornar el primer elemento
    return obtener(0);
  }
  
  public Object obtenerÚltimo()
  {
    // Retornar el último elemento
    return obtener(tamaño() - 1);
  }
}
//////////////////////////////////////////////////////////////////
