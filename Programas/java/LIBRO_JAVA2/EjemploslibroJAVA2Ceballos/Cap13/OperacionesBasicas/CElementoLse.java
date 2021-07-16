// Elemento de una lista lineal simplemente enlazada
class CElementoLse
{
  // Atributos
  int dato;
  CElementoLse siguiente; // referencia al siguiente elemento

  // Métodos
  CElementoLse() {}     // constructor sin parámetros
  CElementoLse( int d ) // constructor con parámetros
  {
    dato = d;
  }
  protected void finalize() throws Throwable // destructor
  {
    System.out.println("Objeto destruido");
  }
}
