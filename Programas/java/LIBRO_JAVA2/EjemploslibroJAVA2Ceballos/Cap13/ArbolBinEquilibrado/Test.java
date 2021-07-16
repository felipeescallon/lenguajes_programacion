//////////////////////////////////////////////////////////////////
// Crear un árbol binario perfectamente equilibrado de n nodos
//
public class Test
{
  public static void main(String[] args)
  {
    CArbolBinarioEquilibrado arbolbe = new CArbolBinarioEquilibrado();
    
    int númeroDeNodos;
    System.out.print("Número de nodos: ");
    númeroDeNodos = Leer.datoInt();
    arbolbe.construirArbolEquilibrado(númeroDeNodos);
    System.out.println();
    
    // Buscar datos
    String nombre;
    System.out.print("nombre a buscar: "); nombre = Leer.dato();
    CDatos obj = (CDatos)arbolbe.buscar(new CDatos(nombre, 0));
    if ( obj != null )
      System.out.println(obj.obtenerNombre() + "  " +
                         obj.obtenerNota());
    else
      System.out.println("La búsqueda falló");
    
    // Mostrar los nodos del árbol
    System.out.println("\nArbol:");
    arbolbe.visitarInorden();
  }
}
