//////////////////////////////////////////////////////////////////
// Clase CBanco: clase que mantiene una matriz de referencias a
// objetos de cualquier tipo de cuenta bancaria.
//
public class CBanco
{
  private CCuenta[] clientes; // matriz de objetos
  private int nElementos; // número de elementos de la matriz
  
  public CBanco()
  {
    // Crear una matriz vacía
    nElementos = 0;
    clientes = new CCuenta[nElementos];
  }
  
  private void unElementoMás(CCuenta[] clientesActuales)
  {
    nElementos = clientesActuales.length;    // Crear una matriz con un elemento más
    clientes = new CCuenta[nElementos + 1];
    // Copiar los clientes que hay actualmente
    for ( int i = 0; i < nElementos; i++ )
      clientes[i] = clientesActuales[i];
    nElementos++;
  }
  
  private void unElementoMenos(CCuenta[] clientesActuales)
  {
    if (clientesActuales.length == 0) return;
    int k = 0;
    nElementos = clientesActuales.length;
    // Crear una matriz con un elementos menos
    clientes = new CCuenta[nElementos - 1];
    // Copiar los clientes que hay actualmente
    for ( int i = 0; i < nElementos; i++ )
      if (clientesActuales[i] != null)
        clientes[k++] = clientesActuales[i];
    nElementos--;
  }
  
  public void insertarCliente( int i, CCuenta objeto )
  {    // Asignar al elemento i de la matriz, un nuevo objeto
    if (i >= 0 && i < nElementos)
      clientes[i] = objeto;
    else
      System.out.println("Índice fuera de límites");
  }
  
  public CCuenta clienteEn( int i )
  {    // Devolver la referencia al objeto i de la matriz
    if (i >= 0 && i < nElementos)
      return clientes[i];
    else
    {
      System.out.println("Índice fuera de límites");
      return null;
    }
  }
  
  public int longitud() { return nElementos; }
  
  public void añadir(CCuenta obj)
  {    // Añadir un objeto a la matriz
    unElementoMás(clientes);
    insertarCliente( nElementos - 1, obj );
  }
  
  public boolean eliminar(String cuenta)
  {
    // Buscar la cuenta y eliminar el objeto
    for ( int i = 0; i < nElementos; i++ )
      if (cuenta.compareTo(clientes[i].obtenerCuenta()) == 0)
      {
        clientes[i] = null; // enviar el objeto a la basura
        unElementoMenos(clientes);
        return true;
      }
    return false;
  }
  
  public int buscar(String str, int pos)
  {    // Buscar un objeto y devolver su posición
    String nom, cuen;
    if (str == null) return -1;
    if (pos < 0) pos = 0;
    for ( int i = pos; i < nElementos; i++ )
    {      // Buscar por el nombre
      nom = clientes[i].obtenerNombre();
      if (nom == null) continue;
      // ¿str está contenida en nom?
      if (nom.indexOf(str) > -1)
        return i;
      // Buscar por la cuenta
      cuen = clientes[i].obtenerCuenta();
      if (cuen == null) continue;
      // ¿str está contenida en cuen?
      if (cuen.indexOf(str) > -1)
        return i;
    }
    return -1;
  }
}
//////////////////////////////////////////////////////////////////
