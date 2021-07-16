//////////////////////////////////////////////////////////////////
// Lista lineal circular simplemente enlazada
//
public class CListaCircularSE
{
  // �ltimo: referencia el �ltimo elemento.
  // �ltimo.siguiente referencia al primer elemento de la lista.
  private CElemento �ltimo = null;

  // Elemento de una lista lineal circular simplemente enlazada
  private class CElemento
  {
    // Atributos
    private Object datos;        // referencia a los datos
    private CElemento siguiente; // siguiente elemento
    
    // M�todos
    private CElemento() {} // constructor
    
    private CElemento(Object d, CElemento s) // constructor
    {
      datos = d;
      siguiente = s;
    }
  }

  public CListaCircularSE() {} // constructor
  
  public int tama�o()
  {
    // Devuelve el n�mero de elementos de la lista
    if (�ltimo == null) return 0;
    CElemento q = �ltimo.siguiente; // primer elemento
    int n = 1;                      // n�mero de elementos
    while (q != �ltimo)
    {
      n++;
      q = q.siguiente;
    }
    return n;
  }
  
  public void a�adirAlPrincipio(Object obj)
  {
    // A�ade un elemento al principio de la lista.
    // Crear el nuevo elemento.
    CElemento q = new CElemento(obj, null);

    if( �ltimo != null ) // existe una lista
    {         
      q.siguiente = �ltimo.siguiente;
      �ltimo.siguiente =  q;
    }    
    else  // inserci�n del primer elemento
    {
      �ltimo = q;
      �ltimo.siguiente = q;
    }
  }
  
  public void a�adirAlFinal(Object obj)
  {
    // A�ade un elemento al final de la lista.
    // Por lo tanto, �ltimo referenciar� este nuevo elemento.
    // Crear el nuevo elemento.
    CElemento q = new CElemento(obj, null);
      
    if( �ltimo != null ) // existe una lista
    {       
      q.siguiente = �ltimo.siguiente;
      �ltimo = �ltimo.siguiente =  q;
    }      
    else  // inserci�n del primer elemento
    {
      �ltimo = q;
      �ltimo.siguiente = q;
    }
  }
  
  public Object borrar()
  {
    // Devuelve una referencia a los datos del primer elemento de
    // la lista y borra este elemento.
    if( �ltimo == null )
    {
      System.err.println( "Lista vac�a\n" );
      return null;
    }

    CElemento q = �ltimo.siguiente;
    Object obj = q.datos;

    if( q == �ltimo )
      �ltimo = null;
    else
      �ltimo.siguiente = q.siguiente;
    
    return obj;
    // El elemento referenciado por q es enviado a la basura, al
    // quedar desreferenciado cuando finaliza este m�todo por ser
    // q una variable local.
  }
  
  public Object obtener(int i)
  {
    // Obtener el elemento de la posici�n i
    int n�meroDeElementos = tama�o();
    if (i >= n�meroDeElementos || i < 0)
      return null;
    
    CElemento q = �ltimo.siguiente; // primer elemento
    // Posicionarse en el elemento i
    for (int n = 0; n < i; n++)
      q = q.siguiente;
    
    // Retornar los datos
    return q.datos;
  }
}
//////////////////////////////////////////////////////////////////
