//////////////////////////////////////////////////////////////////
// Crear un �rbol binario perfectamente equilibrado de n nodos
//
public class Test
{
  public static void main(String[] args)
  {
    CArbolBinarioEquilibrado arbolbe = new CArbolBinarioEquilibrado();
    
    int n�meroDeNodos;
    System.out.print("N�mero de nodos: ");
    n�meroDeNodos = Leer.datoInt();
    arbolbe.construirArbolEquilibrado(n�meroDeNodos);
    System.out.println();
    
    // Buscar datos
    String nombre;
    System.out.print("nombre a buscar: "); nombre = Leer.dato();
    CDatos obj = (CDatos)arbolbe.buscar(new CDatos(nombre, 0));
    if ( obj != null )
      System.out.println(obj.obtenerNombre() + "  " +
                         obj.obtenerNota());
    else
      System.out.println("La b�squeda fall�");
    
    // Mostrar los nodos del �rbol
    System.out.println("\nArbol:");
    arbolbe.visitarInorden();
  }
}
