//////////////////////////////////////////////////////////////////
// Crear una lista lineal simplemente enlazada
//
public class Test
{
  public static void mostrarLista(CListaLinealSEOrdenada lse)
  {
    // Mostrar todos los elementos de la lista
    CDatos obj = (CDatos)lse.obtenerPrimero();
    int i = 1;
    while (obj != null)
    {
      System.out.println(i++ + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      obj = (CDatos)lse.obtenerSiguiente();
    }
  }
  
  public static void main(String[] args)
  {
    // Crear una lista lineal vacía
    CListaLinealSEOrdenada lse = new CListaLinealSEOrdenada();
    
    // Leer datos y añadirlos a la lista
    CDatos obj;
    String nombre;
    double nota;
    int i = 0;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z.");
    System.out.print("nombre: ");
    while ((nombre = Leer.dato()) != null)
    {
      System.out.print("nota:   ");
      nota = Leer.datoDouble();
      lse.añadir(new CDatos(nombre, nota));
      System.out.print("nombre: ");
    }
    System.out.println("\n");
    
    // Borrar un elemento determinado
    System.out.print("nombre del alumno a borrar: ");
    nombre = Leer.dato();
    obj = (CDatos)lse.borrar(new CDatos(nombre, 0));
    if (obj == null)
      System.out.println("Error: elemento no borrado");
    
    // Obtener el siguiente
    obj = (CDatos)lse.obtenerSiguiente();
    if (obj != null)
      System.out.println("Nombre: " + obj.obtenerNombre() +
                         ", nota: " + obj.obtenerNota());

    // Mostrar todos
    System.out.println("Lista:");
    mostrarLista(lse);
  }
}
