/////////////////////////////////////////////////////////////////
// La clase lista circular doblemente enlazada permite manipular
// los elementos que componen una lista de este tipo.
//
public class CListaCircularDE
{
  private CElemento último;
    // referencia al último elemento de la lista
  private CElemento actual;
    // referencia al último elemento accedido
  private long númeroDeElementos;
    // número de elementos que tiene la lista
  private long posición;
    // posición del elemento actual

  // Elemento de una lista lineal circular simplemente enlazada
  private class CElemento
  {
    // Atributos
    private Object datos;        // referencia a los datos
    private CElemento anterior;  // anterior elemento
    private CElemento siguiente; // siguiente elemento
    
    // Métodos
    private CElemento() {} // constructor
  }

  public CListaCircularDE() // constructor
  {
    actual = último = null;
    númeroDeElementos = 0L;
    posición = -1L; // la posición del primer elemento será la 0
  }
  
  public long tamaño()
  {
    // Permite saber el tamaño de la lista
    return númeroDeElementos;
  }
  
  public void insertar(Object obj)
  {
    // Añade un nuevo elemento a la lista a continuación
    // del elemento actual; el nuevo elemento pasa a ser el
    // actual.
    CElemento q;
                 
    if (último == null) // lista vacía
    {
      último = new CElemento();
        
      // Las dos líneas siguientes inician una lista circular.
      último.anterior = último;
      último.siguiente = último;
      último.datos = obj;     // asignar datos.
      actual = último;
      posición = 0L;          // ya hay un elemento en la lista.
    }
    else // existe una lista
    {
      q = new CElemento();

      // Insertar el nuevo elemento después del actual.
      actual.siguiente.anterior = q;
      q.siguiente = actual.siguiente;
      actual.siguiente = q;
      q.anterior = actual;
      q.datos = obj;

      // Actualizar parámetros.
      posición++;
      
      // Si el elemento actual es el último, el nuevo elemento
      // pasa a ser el actual y el último.
      if( actual == último )
        último = q;
        
      actual = q; // el nuevo elemento pasa a ser el actual.
    } // fin else

    númeroDeElementos++; // incrementar en uno el número de elementos.
  }

  public Object borrar()
  {
    // El método borrar devuelve los datos del elemento
    // referenciado por actual y lo elimina de la lista
    // (al quedar desreferenciado es enviado a la basura)
    CElemento q;
    Object obj;
      
    if( último == null ) return ( null );  // lista vacía.

    if( actual == último ) // se trata del último elemento.
    {
      if( númeroDeElementos == 1L ) // hay un solo elemento
      {
        obj = último.datos;
        último = actual = null;
        númeroDeElementos = 0L;
        posición = -1L;
      }
      else // hay más de un elemento
      {    
        actual = último.anterior;
        último.siguiente.anterior = actual;
        actual.siguiente = último.siguiente;
        obj = último.datos;
        último = actual;
        posición--;
        númeroDeElementos--;
      }  // fin del bloque else
    }    // fin del bloque if( actual == último )
    else // el elemento a borrar no es el último
    {
      q = actual.siguiente;
      actual.anterior.siguiente = q;
      q.anterior = actual.anterior;
      obj = actual.datos;
      actual = q;
      númeroDeElementos--;
    }
    return obj;
  }

  public void irAlSiguiente()
  {
    // Avanza la posición actual al siguiente elemento.
    if (posición < númeroDeElementos - 1)
    {
      actual = actual.siguiente;
      posición++;
    } 
  }

  public void irAlAnterior()
  {
    // Retrasa la posición actual al elemento anterior.
    if ( posición > 0L )
    {
      actual = actual.anterior;
      posición--;
    }
  }

  public void irAlPrincipio()
  {
    // Hace que la posición actual sea el principio de la lista.
    actual = último.siguiente;
    posición = 0L;
  }

  public void irAlFinal()
  {
    // El final de la lista es ahora la posición actual.
    actual = último;
    posición = númeroDeElementos - 1;
  }
  
  public boolean irAl(long i)
  {
    // Posicionarse en el elemento i
    long númeroDeElementos = tamaño();
    if (i >= númeroDeElementos || i < 0) return false;
    
    irAlPrincipio();
    // Posicionarse en el elemento i
    for (long n = 0; n < i; n++)
      irAlSiguiente();
    return true;
  }

  public Object obtener()
  {
    // El método obtener devuelve la referencia a los datos
    // asociados con el elemento actual.
    if ( último == null ) return null; // lista vacía

    return actual.datos;
  }

  public Object obtener(long i)
  {
    // El método obtener devuelve la referencia a los datos
    // asociados con el elemento de índice i.
    if (!irAl(i)) return null;
    return obtener();
  }

  public void modificar(Object pNuevosDatos)
  {
    // El método modificar establece nuevos datos para el
    // elemento actual.
    if(último == null) return; // lista vacía
      
    actual.datos = pNuevosDatos;
  }
}
//////////////////////////////////////////////////////////////////
