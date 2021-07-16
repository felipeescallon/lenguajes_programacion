/////////////////////////////////////////////////////////////////
// Clase derivada de la clase abstracta CArbolBinB. Redefine los
// métodos: comparar, procesar y visitarInorden.
//
public class CArbolBinarioDeBusqueda extends CArbolBinB
{
  public int totalPalabras = 0;
  public int totalPalabrasDiferentes = 0;
  
  // Permite comparar dos nodos del árbol por el atributo
  // nombre.
  public int comparar(Object obj1, Object obj2)
  {
    String str1 = new String(((CDatos)obj1).obtenerPalabra());
    String str2 = new String(((CDatos)obj2).obtenerPalabra());
    return str1.compareTo(str2);
  }
  
  // Permite mostrar los datos del nodo visitado.
  public void procesar(Object obj)
  {
    String palabra = new String(((CDatos)obj).obtenerPalabra());
    int contador = ((CDatos)obj).obtenerContador();
    System.out.println(palabra + " = " + contador);
    totalPalabras += contador;
    totalPalabrasDiferentes++;
  }
  
  // Visitar los nodos del árbol.
  public void visitarInorden()
  {
    // Si el segundo argumento es true, la visita comienza
    // en la raíz independientemente del primer argumento.
    inorden(null, true);
  }
}
/////////////////////////////////////////////////////////////////
