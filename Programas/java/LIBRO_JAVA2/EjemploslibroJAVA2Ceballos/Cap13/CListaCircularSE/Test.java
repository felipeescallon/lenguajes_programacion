//////////////////////////////////////////////////////////////////
// Crear una lista lineal circular simplemente enlazada
//
public class Test
{
  public static void mostrarLista(CListaCircularSE lcse)
  {
    // Mostrar todos los elementos de la lista
    int i = 0, tam = lcse.tamaño();
    CDatos obj;
    while (i < tam)
    {
      obj = (CDatos)lcse.obtener(i);
      System.out.println(i + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      i++;
    }
    if (tam == 0) System.out.println("lista vacía");
  }
  
  public static void main(String[] args)
  {
    // Crear una lista circular vacía
    CListaCircularSE lcse = new CListaCircularSE();
    
    // Leer datos y añadirlos a la lista
    String nombre;
    double nota;
    int i = 0;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z.");
    System.out.print("nombre: ");
    while ((nombre = Leer.dato()) != null)
    {
      System.out.print("nota:   ");
      nota = Leer.datoDouble();
      lcse.añadirAlFinal(new CDatos(nombre, nota));
      System.out.print("nombre: ");
    }

    // Añadir un objeto al principio
    lcse.añadirAlPrincipio(new CDatos("abcd", 10));
        
    System.out.println("\n");
    // Mostrar la lista
    System.out.println("Lista:");
    mostrarLista(lcse);
    
    // Borrar el elemento primero
    CDatos obj = (CDatos)lcse.borrar();
    
    // Mostrar la lista
    System.out.println("Lista:");
    mostrarLista(lcse);
  }
}
