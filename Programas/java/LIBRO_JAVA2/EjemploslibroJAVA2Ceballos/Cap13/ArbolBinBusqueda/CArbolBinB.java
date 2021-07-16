/////////////////////////////////////////////////////////////////
// Clase abstracta: �rbol binario de b�squeda. Para utilizar los
// m�todos proporcionados por esta clase, tendremos que crear
// una subclase de ella y redefinir los m�todos:
// comparar, procesar y visitarInorden.
//
public abstract class CArbolBinB
{
  // Atributos del �rbol binario
  protected CNodo ra�z = null; // ra�z del �rbol
  
  // Nodo de un �rbol binario
  private class CNodo
  {
    // Atributos
    private Object datos;      // referencia a los datos
    private CNodo izquierdo;   // ra�z del sub�rbol izquierdo
    private CNodo derecho;     // ra�z del sub�rbol derecho
    
    // M�todos
    public CNodo() {}          // constructor
  }
  
  // Posibles errores que se pueden dar relativos a un nodo
  public static final int CORRECTO  = 000;
  public static final int NO_DATOS  = 100;
  public static final int YA_EXISTE = 101;
  public static final int NO_EXISTE = 102;

  // M�todos del �rbol binario
  public CArbolBinB() {}       // constructor
  
  // El m�todo siguiente debe ser redefinido en una subclase para
  // que permita comparar dos nodos del �rbol por el atributo
  // que necesitemos en cada momento.
  public abstract int comparar(Object obj1, Object obj2);
  
  // El m�todo siguiente debe ser redefinido en una subclase para
  // que permita especificar las operaciones que se deseen
  // realizar con el nodo visitado.
  public abstract void procesar(Object obj);
  
  // El m�todo siguiente debe ser redefinido en una subclase para
  // que invoque a insertar con los argumentos deseados.
  public abstract void visitarInorden();

  public Object buscar(Object obj)
  {
    // El m�todo buscar permite acceder a un determinado nodo.
    CNodo actual = ra�z;
    int nComp = 0;

    // Buscar un nodo que tenga asociados los datos dados por obj
    while ( actual != null )
    {
      if (( nComp = comparar( obj, actual.datos )) == 0)
        return( actual.datos );  // CORRECTO (nodo encontrado)
      else if ( nComp < 0 )      // buscar en el sub�rbol izquierdo
        actual = actual.izquierdo;
      else                       // buscar en el sub�rbol derecho
        actual = actual.derecho;
    }
    return null; // NO_EXISTE
  }
  
  public int insertar(Object obj)
  {
    // El m�todo insertar permite a�adir un nodo que a�n no est�
    // en el �rbol.
    CNodo �ltimo = null, actual = ra�z;
    int nComp = 0;

    if ( obj == null ) return NO_DATOS;
  
    // Comienza la b�squeda para verificar si ya hay un nodo con
    // estos datos en el �rbol
    while (actual != null)
    {
      if ((nComp = comparar( obj, actual.datos )) == 0)
        break; // se encontr� el nodo
      else
      {
        �ltimo = actual;
        if ( nComp < 0 )  // buscar en el sub�rbol izquierdo
          actual = actual.izquierdo;
        else              // buscar en el sub�rbol derecho
          actual = actual.derecho;
      }
    }

    if ( actual == null ) // no se encontr� el nodo, a�adirlo
    {
      CNodo nuevoNodo = new CNodo();
      nuevoNodo.datos = obj;
      nuevoNodo.izquierdo = nuevoNodo.derecho = null;

      // El nodo a a�adir pasar� a ser la ra�z del �rbol total si
      // �ste est� vac�o, del sub�rbol izquierdo de "�ltimo" si la
      // comparaci�n fue menor, o del sub�rbol derecho de "�ltimo" si
      // la comparaci�n fue mayor.
      if ( �ltimo == null ) // �rbol vac�o
        ra�z = nuevoNodo;
      else if ( nComp < 0 )
        �ltimo.izquierdo = nuevoNodo;
      else
        �ltimo.derecho = nuevoNodo;

      return CORRECTO;
    } // fin del bloque if ( actual == null )
    else // el nodo ya existe en el �rbol
      return YA_EXISTE;
  }
  
  public Object borrar(Object obj)
  {
    // El m�todo borrar permite eliminar un nodo del �rbol.
    CNodo �ltimo = null, actual = ra�z;
    CNodo marcado = null, sucesor = null;
    int nAnteriorComp = 0, nComp = 0;

    if (obj == null) return null; // NO_DATOS
  
    // Comienza la b�squeda para verificar si hay un nodo con
    // estos datos en el �rbol.
    while ( actual != null )
    {
      nAnteriorComp = nComp; // resultado de la comparaci�n anterior
      if (( nComp = comparar( obj, actual.datos )) == 0)
        break; // se encontr� el nodo
      else
      {
        �ltimo = actual;
        if ( nComp < 0 )   // buscar en el sub�rbol izquierdo
          actual = actual.izquierdo;
        else               // buscar en el sub�rbol derecho
          actual = actual.derecho;
      }
    } // fin del bloque while ( actual != null )

    if ( actual != null ) // se encontr� el nodo
    {
      marcado = actual;
      if (( actual.izquierdo == null && actual.derecho == null ))
        // se trata de un nodo terminal (no tiene descendientes)
        sucesor = null;
      else if ( actual.izquierdo == null ) // nodo sin sub�rbol izquierdo
        sucesor = actual.derecho;
      else if ( actual.derecho == null ) // nodo sin sub�rbol derecho
        sucesor = actual.izquierdo;
      else  // nodo con sub�rbol izquierdo y derecho
      {
        // Referencia al sub�rbol derecho del nodo a borrar
        sucesor = actual = actual.derecho;
        // Descender al nodo m�s a la izquierda en el sub�rbol
        // derecho de este nodo (el de valor m�s peque�o) y hacer
        // que el sub�rbol izquierdo del nodo a borrar sea ahora
        // el sub�rbol izquierdo de este nodo.
        while ( actual.izquierdo != null )
          actual = actual.izquierdo;
        actual.izquierdo = marcado.izquierdo;
      }

      // Eliminar el nodo y rehacer los enlaces
      if ( �ltimo != null )
      {
        if ( nAnteriorComp < 0 )
          �ltimo.izquierdo = sucesor;
        else
          �ltimo.derecho = sucesor;
      }
      else
        ra�z = sucesor;
      
      return marcado.datos;; // CORRECTO
      // "marcado" ser� enviado a la basura
    }
    else // el nodo buscado no est� en el �rbol
      return null; // NO_EXISTE
  }
  
  public void inorden( CNodo r , boolean nodoRa�z)
  {
    // El m�todo recursivo inorden visita los nodos del �rbol
    // utilizando la forma inorden; esto es, primero se visita
    // el sub�rbol izquierdo, despu�s se visita la ra�z, y por
    // �ltimo, el sub�rbol derecho.
    // Si el segundo par�metro es true, la visita comienza
    // en la ra�z independientemente del primer par�metro.

    CNodo actual = null;

    if ( nodoRa�z )
      actual = ra�z; // partir de la ra�z
    else
      actual = r;   // partir de un nodo cualquiera
    if ( actual != null )
    {
      inorden( actual.izquierdo, false ); // visitar sub�rbol izq.
      // Procesar los datos del nodo visitado
      procesar( actual.datos );
      inorden( actual.derecho, false ); // visitar sub�rbol dcho.
    }
  }
}
/////////////////////////////////////////////////////////////////
