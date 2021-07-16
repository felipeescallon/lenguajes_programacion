//////////////////////////////////////////////////////////////////
// Lista lineal circular simplemente enlazada
//
public class CListaCircularSE
{
  // último: referencia el último elemento.
  // último.siguiente referencia al primer elemento de la lista.
  private CElemento último = null;

  // Elemento de una lista lineal circular simplemente enlazada
  private class CElemento
  {
    // Atributos
    private Object datos;        // referencia a los datos
    private CElemento siguiente; // siguiente elemento
    
    // Métodos
    private CElemento() {} // constructor
    
    private CElemento(Object d, CElemento s) // constructor
    {
      datos = d;
      siguiente = s;
    }
  }

  public CListaCircularSE() {} // constructor
  
  public int tamaño()
  {
    // Devuelve el número de elementos de la lista
    if (último == null) return 0;
    CElemento q = último.siguiente; // primer elemento
    int n = 1;                      // número de elementos
    while (q != último)
    {
      n++;
      q = q.siguiente;
    }
    return n;
  }
  
  public void añadirAlPrincipio(Object obj)
  {
    // Añade un elemento al principio de la lista.
    // Crear el nuevo elemento.
    CElemento q = new CElemento(obj, null);

    if( último != null ) // existe una lista
    {         
      q.siguiente = último.siguiente;
      último.siguiente =  q;
    }    
    else  // inserción del primer elemento
    {
      último = q;
      último.siguiente = q;
    }
  }
  
  public void añadirAlFinal(Object obj)
  {
    // Añade un elemento al final de la lista.
    // Por lo tanto, último referenciará este nuevo elemento.
    // Crear el nuevo elemento.
    CElemento q = new CElemento(obj, null);
      
    if( último != null ) // existe una lista
    {       
      q.siguiente = último.siguiente;
      último = último.siguiente =  q;
    }      
    else  // inserción del primer elemento
    {
      último = q;
      último.siguiente = q;
    }
  }
  
  public Object borrar()
  {
    // Devuelve una referencia a los datos del primer elemento de
    // la lista y borra este elemento.
    if( último == null )
    {
      System.err.println( "Lista vacía\n" );
      return null;
    }

    CElemento q = último.siguiente;
    Object obj = q.datos;

    if( q == último )
      último = null;
    else
      último.siguiente = q.siguiente;
    
    return obj;
    // El elemento referenciado por q es enviado a la basura, al
    // quedar desreferenciado cuando finaliza este método por ser
    // q una variable local.
  }
  
  public Object obtener(int i)
  {
    // Obtener el elemento de la posición i
    int númeroDeElementos = tamaño();
    if (i >= númeroDeElementos || i < 0)
      return null;
    
    CElemento q = último.siguiente; // primer elemento
    // Posicionarse en el elemento i
    for (int n = 0; n < i; n++)
      q = q.siguiente;
    
    // Retornar los datos
    return q.datos;
  }
}
//////////////////////////////////////////////////////////////////
