/////////////////////////////////////////////////////////////////
// La clase lista circular doblemente enlazada permite manipular
// los elementos que componen una lista de este tipo.
//
public class CListaCircularDE
{
  private CElemento �ltimo;
    // referencia al �ltimo elemento de la lista
  private CElemento actual;
    // referencia al �ltimo elemento accedido
  private long n�meroDeElementos;
    // n�mero de elementos que tiene la lista
  private long posici�n;
    // posici�n del elemento actual

  // Elemento de una lista lineal circular simplemente enlazada
  private class CElemento
  {
    // Atributos
    private Object datos;        // referencia a los datos
    private CElemento anterior;  // anterior elemento
    private CElemento siguiente; // siguiente elemento
    
    // M�todos
    private CElemento() {} // constructor
  }

  public CListaCircularDE() // constructor
  {
    actual = �ltimo = null;
    n�meroDeElementos = 0L;
    posici�n = -1L; // la posici�n del primer elemento ser� la 0
  }
  
  public long tama�o()
  {
    // Permite saber el tama�o de la lista
    return n�meroDeElementos;
  }
  
  public void insertar(Object obj)
  {
    // A�ade un nuevo elemento a la lista a continuaci�n
    // del elemento actual; el nuevo elemento pasa a ser el
    // actual.
    CElemento q;
                 
    if (�ltimo == null) // lista vac�a
    {
      �ltimo = new CElemento();
        
      // Las dos l�neas siguientes inician una lista circular.
      �ltimo.anterior = �ltimo;
      �ltimo.siguiente = �ltimo;
      �ltimo.datos = obj;     // asignar datos.
      actual = �ltimo;
      posici�n = 0L;          // ya hay un elemento en la lista.
    }
    else // existe una lista
    {
      q = new CElemento();

      // Insertar el nuevo elemento despu�s del actual.
      actual.siguiente.anterior = q;
      q.siguiente = actual.siguiente;
      actual.siguiente = q;
      q.anterior = actual;
      q.datos = obj;

      // Actualizar par�metros.
      posici�n++;
      
      // Si el elemento actual es el �ltimo, el nuevo elemento
      // pasa a ser el actual y el �ltimo.
      if( actual == �ltimo )
        �ltimo = q;
        
      actual = q; // el nuevo elemento pasa a ser el actual.
    } // fin else

    n�meroDeElementos++; // incrementar en uno el n�mero de elementos.
  }

  public Object borrar()
  {
    // El m�todo borrar devuelve los datos del elemento
    // referenciado por actual y lo elimina de la lista
    // (al quedar desreferenciado es enviado a la basura)
    CElemento q;
    Object obj;
      
    if( �ltimo == null ) return ( null );  // lista vac�a.

    if( actual == �ltimo ) // se trata del �ltimo elemento.
    {
      if( n�meroDeElementos == 1L ) // hay un solo elemento
      {
        obj = �ltimo.datos;
        �ltimo = actual = null;
        n�meroDeElementos = 0L;
        posici�n = -1L;
      }
      else // hay m�s de un elemento
      {    
        actual = �ltimo.anterior;
        �ltimo.siguiente.anterior = actual;
        actual.siguiente = �ltimo.siguiente;
        obj = �ltimo.datos;
        �ltimo = actual;
        posici�n--;
        n�meroDeElementos--;
      }  // fin del bloque else
    }    // fin del bloque if( actual == �ltimo )
    else // el elemento a borrar no es el �ltimo
    {
      q = actual.siguiente;
      actual.anterior.siguiente = q;
      q.anterior = actual.anterior;
      obj = actual.datos;
      actual = q;
      n�meroDeElementos--;
    }
    return obj;
  }

  public void irAlSiguiente()
  {
    // Avanza la posici�n actual al siguiente elemento.
    if (posici�n < n�meroDeElementos - 1)
    {
      actual = actual.siguiente;
      posici�n++;
    } 
  }

  public void irAlAnterior()
  {
    // Retrasa la posici�n actual al elemento anterior.
    if ( posici�n > 0L )
    {
      actual = actual.anterior;
      posici�n--;
    }
  }

  public void irAlPrincipio()
  {
    // Hace que la posici�n actual sea el principio de la lista.
    actual = �ltimo.siguiente;
    posici�n = 0L;
  }

  public void irAlFinal()
  {
    // El final de la lista es ahora la posici�n actual.
    actual = �ltimo;
    posici�n = n�meroDeElementos - 1;
  }
  
  public boolean irAl(long i)
  {
    // Posicionarse en el elemento i
    long n�meroDeElementos = tama�o();
    if (i >= n�meroDeElementos || i < 0) return false;
    
    irAlPrincipio();
    // Posicionarse en el elemento i
    for (long n = 0; n < i; n++)
      irAlSiguiente();
    return true;
  }

  public Object obtener()
  {
    // El m�todo obtener devuelve la referencia a los datos
    // asociados con el elemento actual.
    if ( �ltimo == null ) return null; // lista vac�a

    return actual.datos;
  }

  public Object obtener(long i)
  {
    // El m�todo obtener devuelve la referencia a los datos
    // asociados con el elemento de �ndice i.
    if (!irAl(i)) return null;
    return obtener();
  }

  public void modificar(Object pNuevosDatos)
  {
    // El m�todo modificar establece nuevos datos para el
    // elemento actual.
    if(�ltimo == null) return; // lista vac�a
      
    actual.datos = pNuevosDatos;
  }
}
//////////////////////////////////////////////////////////////////
