/////////////////////////////////////////////////////////////////
// Clase abstracta: árbol binario de búsqueda. Para utilizar los
// métodos proporcionados por esta clase, tendremos que crear
// una subclase de ella y redefinir los métodos:
// comparar, procesar y visitarInorden.
//
public abstract class CArbolBinB
{
  // Atributos del árbol binario
  protected CNodo raíz = null; // raíz del árbol
  
  // Nodo de un árbol binario
  private class CNodo
  {
    // Atributos
    private Object datos;      // referencia a los datos
    private CNodo izquierdo;   // raíz del subárbol izquierdo
    private CNodo derecho;     // raíz del subárbol derecho
    
    // Métodos
    public CNodo() {}          // constructor
  }
  
  // Posibles errores que se pueden dar relativos a un nodo
  public static final int CORRECTO  = 000;
  public static final int NO_DATOS  = 100;
  public static final int YA_EXISTE = 101;
  public static final int NO_EXISTE = 102;

  // Métodos del árbol binario
  public CArbolBinB() {}       // constructor
  
  // El método siguiente debe ser redefinido en una subclase para
  // que permita comparar dos nodos del árbol por el atributo
  // que necesitemos en cada momento.
  public abstract int comparar(Object obj1, Object obj2);
  
  // El método siguiente debe ser redefinido en una subclase para
  // que permita especificar las operaciones que se deseen
  // realizar con el nodo visitado.
  public abstract void procesar(Object obj);
  
  // El método siguiente debe ser redefinido en una subclase para
  // que invoque a insertar con los argumentos deseados.
  public abstract void visitarInorden();

  public Object buscar(Object obj)
  {
    // El método buscar permite acceder a un determinado nodo.
    CNodo actual = raíz;
    int nComp = 0;

    // Buscar un nodo que tenga asociados los datos dados por obj
    while ( actual != null )
    {
      if (( nComp = comparar( obj, actual.datos )) == 0)
        return( actual.datos );  // CORRECTO (nodo encontrado)
      else if ( nComp < 0 )      // buscar en el subárbol izquierdo
        actual = actual.izquierdo;
      else                       // buscar en el subárbol derecho
        actual = actual.derecho;
    }
    return null; // NO_EXISTE
  }
  
  public int insertar(Object obj)
  {
    // El método insertar permite añadir un nodo que aún no está
    // en el árbol.
    CNodo último = null, actual = raíz;
    int nComp = 0;

    if ( obj == null ) return NO_DATOS;
  
    // Comienza la búsqueda para verificar si ya hay un nodo con
    // estos datos en el árbol
    while (actual != null)
    {
      if ((nComp = comparar( obj, actual.datos )) == 0)
        break; // se encontró el nodo
      else
      {
        último = actual;
        if ( nComp < 0 )  // buscar en el subárbol izquierdo
          actual = actual.izquierdo;
        else              // buscar en el subárbol derecho
          actual = actual.derecho;
      }
    }

    if ( actual == null ) // no se encontró el nodo, añadirlo
    {
      CNodo nuevoNodo = new CNodo();
      nuevoNodo.datos = obj;
      nuevoNodo.izquierdo = nuevoNodo.derecho = null;

      // El nodo a añadir pasará a ser la raíz del árbol total si
      // éste está vacío, del subárbol izquierdo de "último" si la
      // comparación fue menor, o del subárbol derecho de "último" si
      // la comparación fue mayor.
      if ( último == null ) // árbol vacío
        raíz = nuevoNodo;
      else if ( nComp < 0 )
        último.izquierdo = nuevoNodo;
      else
        último.derecho = nuevoNodo;

      return CORRECTO;
    } // fin del bloque if ( actual == null )
    else // el nodo ya existe en el árbol
      return YA_EXISTE;
  }
  
  public Object borrar(Object obj)
  {
    // El método borrar permite eliminar un nodo del árbol.
    CNodo último = null, actual = raíz;
    CNodo marcado = null, sucesor = null;
    int nAnteriorComp = 0, nComp = 0;

    if (obj == null) return null; // NO_DATOS
  
    // Comienza la búsqueda para verificar si hay un nodo con
    // estos datos en el árbol.
    while ( actual != null )
    {
      nAnteriorComp = nComp; // resultado de la comparación anterior
      if (( nComp = comparar( obj, actual.datos )) == 0)
        break; // se encontró el nodo
      else
      {
        último = actual;
        if ( nComp < 0 )   // buscar en el subárbol izquierdo
          actual = actual.izquierdo;
        else               // buscar en el subárbol derecho
          actual = actual.derecho;
      }
    } // fin del bloque while ( actual != null )

    if ( actual != null ) // se encontró el nodo
    {
      marcado = actual;
      if (( actual.izquierdo == null && actual.derecho == null ))
        // se trata de un nodo terminal (no tiene descendientes)
        sucesor = null;
      else if ( actual.izquierdo == null ) // nodo sin subárbol izquierdo
        sucesor = actual.derecho;
      else if ( actual.derecho == null ) // nodo sin subárbol derecho
        sucesor = actual.izquierdo;
      else  // nodo con subárbol izquierdo y derecho
      {
        // Referencia al subárbol derecho del nodo a borrar
        sucesor = actual = actual.derecho;
        // Descender al nodo más a la izquierda en el subárbol
        // derecho de este nodo (el de valor más pequeño) y hacer
        // que el subárbol izquierdo del nodo a borrar sea ahora
        // el subárbol izquierdo de este nodo.
        while ( actual.izquierdo != null )
          actual = actual.izquierdo;
        actual.izquierdo = marcado.izquierdo;
      }

      // Eliminar el nodo y rehacer los enlaces
      if ( último != null )
      {
        if ( nAnteriorComp < 0 )
          último.izquierdo = sucesor;
        else
          último.derecho = sucesor;
      }
      else
        raíz = sucesor;
      
      return marcado.datos;; // CORRECTO
      // "marcado" será enviado a la basura
    }
    else // el nodo buscado no está en el árbol
      return null; // NO_EXISTE
  }
  
  public void inorden( CNodo r , boolean nodoRaíz)
  {
    // El método recursivo inorden visita los nodos del árbol
    // utilizando la forma inorden; esto es, primero se visita
    // el subárbol izquierdo, después se visita la raíz, y por
    // último, el subárbol derecho.
    // Si el segundo parámetro es true, la visita comienza
    // en la raíz independientemente del primer parámetro.

    CNodo actual = null;

    if ( nodoRaíz )
      actual = raíz; // partir de la raíz
    else
      actual = r;   // partir de un nodo cualquiera
    if ( actual != null )
    {
      inorden( actual.izquierdo, false ); // visitar subárbol izq.
      // Procesar los datos del nodo visitado
      procesar( actual.datos );
      inorden( actual.derecho, false ); // visitar subárbol dcho.
    }
  }
}
/////////////////////////////////////////////////////////////////
