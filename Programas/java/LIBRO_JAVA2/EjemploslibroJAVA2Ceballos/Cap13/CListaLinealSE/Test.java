//////////////////////////////////////////////////////////////////
// Crear una lista lineal simplemente enlazada
//
public class Test
{
  public static void mostrarLista(CListaLinealSE lse)
  {
    // Mostrar todos los elementos de la lista
    int i = 0, tam = lse.tama�o();
    CDatos obj;
    while (i < tam)
    {
      obj = (CDatos)lse.obtener(i);
      System.out.println(i + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      i++;
    }
  }
  
  public static void main(String[] args)
  {
    // Crear una lista lineal vac�a
    CListaLinealSE lse = new CListaLinealSE();
    
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
      lse.a�adirAlFinal(new CDatos(nombre, nota));
      System.out.print("nombre: ");
    }

    // A�adir un objeto al principio
    lse.a�adirAlPrincipio(new CDatos("abcd", 10));
    // A�adir un objeto en la posici�n 1
    lse.a�adir(1, new CDatos("defg", 9.5));
        
    System.out.println("\n");
    // Mostrar el primero
    CDatos obj = (CDatos)lse.obtenerPrimero();
    System.out.println("Primero: " + obj.obtenerNombre() + " " +
                       obj.obtenerNota());
    
    // Mostrar el �ltimo
    obj = (CDatos)lse.obtener�ltimo();
    System.out.println("�ltimo:  " + obj.obtenerNombre() + " " +
                       obj.obtenerNota());
    
    // Mostrar todos
    System.out.println("Lista:");
    mostrarLista(lse);
    
    // Borrar el elemento de �ndice 2
    obj = (CDatos)lse.borrar(2);
    if (obj == null)
      System.out.println("Error: elemento no borrado");
    
    // Modificar el elemento de �ndice 1
    obj = (CDatos)lse.obtener(1);
    obj.asignarNota(9);
    
    // Mostrar todos
    System.out.println("Lista:");
    mostrarLista(lse);
  }
}
