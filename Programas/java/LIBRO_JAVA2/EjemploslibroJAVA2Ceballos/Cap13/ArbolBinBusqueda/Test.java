//////////////////////////////////////////////////////////////////
// Crear un árbol binario de búsqueda
//
public class Test
{
  public static void main(String[] args)
  {
    CArbolBinarioDeBusqueda arbolbb = new CArbolBinarioDeBusqueda();
    
    // Leer datos y añadirlos al árbol
    String nombre;
    double nota;
    int i = 0, cod;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z.");
    System.out.print("nombre: ");
    while ((nombre = Leer.dato()) != null)
    {
      System.out.print("nota:   ");
      nota = Leer.datoDouble();
      cod = arbolbb.insertar(new CDatos(nombre, nota));
      if (cod == CArbolBinarioDeBusqueda.YA_EXISTE)
      {
        // Si ya existe, distinguimos dos casos:
        // 1. nota nueva >= 0; cambiamos la nota
        // 2. nota nueva < 0; borramos el nodo
        if (nota >= 0)
        {
          CDatos datos = (CDatos)arbolbb.buscar(new CDatos(nombre, nota));
          datos.asignarNota(nota);
        }
        else
        {
          arbolbb.borrar(new CDatos(nombre, nota));
          System.out.println("nodo borrado");
        }
      }
      System.out.print("nombre: ");
    }
    System.out.println("\n");
    
    // Mostrar los nodos del árbol
    System.out.println("\nArbol:");
    arbolbb.visitarInorden();
  }
}
