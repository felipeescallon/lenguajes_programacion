//////////////////////////////////////////////////////////////////
// Clase abstracta CListaLinealSEO: 
//   Lista lineal simplemente enlazada ordenada ascendentemente.
//
public abstract class CListaLinealSEO
{
  // p: referencia al primer elemento de la lista.
  
  private CElemento p = null;            // elemento de cabecera
  private CElemento elemAnterior = null; // elemento anterior
  private CElemento elemActual = null;   // elemento actual

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

  public CListaLinealSEO() {} // constructor
  
  // El método siguiente debe ser redefinido en una subclase para
  // que permita comparar dos elementos de la lista por el atributo
  // que necesitemos en cada momento.
  public abstract int comparar(Object obj1, Object obj2);
  
  public boolean listaVacía()
  {
    return p == null;
  }
  
  public int buscar(Object obj)
  {
    // Buscar el punto de inserción de un elemento en una lista
    // ordenada. El método almacena en elemActual la referencia
    // del elemento buscado si existe, o siguiente si no existe
    // y en elemAnterior la referencia del elemento anterior.
    int r = 0;
    elemAnterior = elemActual = null;
    
    // Si la lista referenciada por p está vacía, retornar.
    if ( listaVacía() ) return 0;

    // Si la lista no está vacía, encontrar el elemento.
    elemAnterior = p;
    elemActual = p;
    // Posicionarse en el elemento buscado.
    while (elemActual != null && (r = comparar(obj, elemActual.datos)) > 0)
    {
      elemAnterior = elemActual;
      elemActual = elemActual.siguiente;
    }
    return r == 0 ? 1 : 0; // devuelve: 0 no encontrado y 1 encontrado
  }
  
  public void añadir(Object obj)
  {
    // Añadir un elemento en orden ascendente según una clave
    // proporcionada por obj.
    CElemento q = new CElemento(obj, null); // crear el elemento
    
    // Si la lista referenciada por p está vacía, añadirlo sin más 
    if ( listaVacía() )
    {
      // Añadir el primer elemento
      p = q;
      elemAnterior = elemActual = p; // actualizar referencias
      return;
    }
    
    // Si la lista no está vacía, encontrar el punto de inserción
    buscar(obj); // establece los valores de elemAnterior y elemActual

    // Dos casos:
    // 1) Insertar al principio de la lista
    // 2) Insertar después del anterior (incluye insertar al final)
    if ( elemAnterior == elemActual ) // insertar al principio
    {
      q.siguiente = p;
      p = q; // cabecera
      elemAnterior = elemActual = p; // actualizar referencias
    }
    else // insertar después del anterior
    {
      q.siguiente = elemActual;
      elemAnterior.siguiente = q;
      elemActual = q; // actualizar referencia
    }
  }
  
  public Object borrar(Object obj)
  {
    // Borrar un determinado elemento.
    // Si la lista está vacía, retornar.
    if ( listaVacía() ) return null;
    
    // Si la lista no está vacía, buscar el elemento. El método
    // buscar establece los valores de elemAnterior y elemActual
    if (buscar(obj) == 0) return null; // no está
    // Dos casos:
    // 1) Borrar el primer elemento de la lista
    // 2) Borrar el siguiente a elemAnterior (elemActual)
    if ( elemActual == p ) // 1)
      p = p.siguiente; // cabecera
    else // 2)
      elemAnterior.siguiente = elemActual.siguiente;
 
    Object borrado = elemActual.datos;
    elemActual = elemActual.siguiente; // actualizar referencia
    return borrado; // retornar el elemento borrado.
    // El elemento referenciado por borrado será enviado a la
    // basura al quedar desreferenciado, por tratarse de una
    // variable local.
  }
  
  public Object obtenerPrimero()
  {
    // Devolver una referencia a los datos del primer elemento.
    // Si la lista está vacía, devolver null.
    if ( listaVacía() ) return null;
    elemActual = elemAnterior = p;
    return p.datos;
  }
  
  public Object obtenerSiguiente()
  {
    // Devolver una referencia a los datos del elemento siguiente
    // al actual y hacer que éste sea el actual. Si la lista
    // está vacía o se intenta ir más allá del último,
    // devolver null.
    if ( listaVacía() || elemActual.siguiente == null ) return null;
    // Avanzar un elemento
    elemAnterior = elemActual;
    elemActual = elemActual.siguiente;
    if ( elemActual != null )
      return elemActual.datos;
    else
      return null;
  }
}
//////////////////////////////////////////////////////////////////
