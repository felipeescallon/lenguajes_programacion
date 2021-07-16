// Elemento de una lista lineal simplemente enlazada
class CElementoLse
{
  // Atributos
  int dato;
  CElementoLse siguiente; // referencia al siguiente elemento

  // M�todos
  CElementoLse() {}     // constructor sin par�metros
  CElementoLse( int d ) // constructor con par�metros
  {
    dato = d;
  }
  protected void finalize() throws Throwable // destructor
  {
    System.out.println("Objeto destruido");
  }
}
