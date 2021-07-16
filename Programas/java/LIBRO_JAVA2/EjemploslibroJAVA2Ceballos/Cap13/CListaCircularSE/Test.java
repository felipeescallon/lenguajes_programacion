//////////////////////////////////////////////////////////////////
// Crear una lista lineal circular simplemente enlazada
//
public class Test
{
  public static void mostrarLista(CListaCircularSE lcse)
  {
    // Mostrar todos los elementos de la lista
    int i = 0, tam = lcse.tama�o();
    CDatos obj;
    while (i < tam)
    {
      obj = (CDatos)lcse.obtener(i);
      System.out.println(i + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      i++;
    }
    if (tam == 0) System.out.println("lista vac�a");
  }
  
  public static void main(String[] args)
  {
    // Crear una lista circular vac�a
    CListaCircularSE lcse = new CListaCircularSE();
    
    // Leer datos y a�adirlos a la lista
    String nombre;
    double nota;
    int i = 0;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z.");
    System.out.print("nombre: ");
    while ((nombre = Leer.dato()) != null)
    {
      System.out.print("nota:   ");
      nota = Leer.datoDouble();
      lcse.a�adirAlFinal(new CDatos(nombre, nota));
      System.out.print("nombre: ");
    }

    // A�adir un objeto al principio
    lcse.a�adirAlPrincipio(new CDatos("abcd", 10));
        
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
