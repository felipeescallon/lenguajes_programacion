/////////////////////////////////////////////////////////////////
// Clase abstracta: árbol binario perfectamente equilibrado.
// Para utilizar los métodos proporcionados por esta clase,
// tendremos que crear una subclase de ella y redefinir los
// métodos: leerDatos, comparar, procesar y visitarInorden.
//
public abstract class CArbolBinE
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
  
  // Métodos del árbol binario
  public CArbolBinE() {}       // constructor
  
  // El método siguiente debe ser redefinido en la subclase para
  // que permita leer los datos que serán referenciados por un
  // nodo del árbol. Devuelve el objeto de datos.
  public abstract Object leerDatos();
  
  // El método siguiente debe ser redefinido en una subclase para
  // que permita comparar dos nodos del árbol por el atributo
  // que necesitemos en cada momento.
  public abstract int comparar(Object obj1, Object obj2);
  
  // El método siguiente debe ser redefinido en la subclase para
  // que permita especificar las operaciones que se deseen
  // realizar con el nodo visitado.
  public abstract void procesar(Object obj);
  
  // El método siguiente debe ser redefinido en la subclase para
  // que invoque a "inorden" con los argumentos deseados.
  public abstract void visitarInorden();
  
  private CNodo construirArbol(int n)
  {
    // Construye un árbol de n nodos perfectamente equilibrado
    CNodo nodo = null;
    int ni = 0, nd = 0;

    if ( n == 0 )
      return null;
    else
    {
      ni = n / 2;      // nodos del subárbol izquierdo
      nd = n - ni - 1; // nodos del subárbol derecho
      nodo = new CNodo();
      nodo.datos = leerDatos();
      nodo.izquierdo = construirArbol(ni);
      nodo.derecho = construirArbol(nd);
      return nodo;
    }
  }

  public void construirArbolEquilibrado(int n)
  {
    raíz = construirArbol(n);
  }
  
  private void buscar(Object obj, CNodo r, Object[] datos, int[] pos)
  {
    // El método buscar permite acceder a un determinado nodo.
    // Si los datos especificados por "obj" se localizan en el
    // árbol referenciado por "r" a partir de la posición "pos[0]",
    // "buscar" devuelve en datos[0] la referencia a esos datos;
    // en otro caso, devuelve null. 
    // Los nodos se consideran numerados (0, 1, 2, ...) según
    // el orden en el que son accedidos por el método "inorden".
    CNodo actual = r;

    if ( actual != null && datos[0] == null )
    {
      buscar(obj, actual.izquierdo, datos, pos);
      if (pos[0]-- <= 0)
        if ( comparar( obj, actual.datos ) == 0 )
          datos[0] = actual.datos;  // nodo encontrado
      buscar(obj, actual.derecho, datos, pos);
    }
  }
  
  public Object buscar(Object obj)
  {
    return buscar(obj, 0);
  }
  
  public Object buscar(Object obj, int posición)
  {
    Object[] datos = {null};
    int[] pos = {posición};
    buscar(obj, raíz, datos, pos);
    return datos[0];
  }
  
  public void inorden(CNodo r, boolean nodoRaíz)
  {
    // El método recursivo inorden visita los nodos del árbol
    // utilizando la forma inorden; esto es, primero se visita
    // el subárbol izquierdo, después se visita la raíz, y por
    // último, el subárbol derecho.
    // Si el segundo argumento es true, la visita comienza
    // en la raíz independientemente del primer argumento.
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
