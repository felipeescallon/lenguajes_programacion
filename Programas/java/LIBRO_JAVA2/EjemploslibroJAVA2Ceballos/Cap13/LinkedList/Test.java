import java.util.*;
//////////////////////////////////////////////////////////////////
// Crear una lista lineal simplemente enlazada
//
public class Test
{
  public static void mostrarLista(LinkedList lse)
  {
    int i = 0, tam = lse.size();
    CDatos obj;
    while (i < tam)
    {
      obj = (CDatos)lse.get(i);
      System.out.println(i + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      i++;
    }
  }
  
  public static void main(String[] args)
  {
    // Crear una lista lineal vacía
    LinkedList lse = new LinkedList();
    
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
      lse.addLast(new CDatos(nombre, nota));
      System.out.print("nombre: ");
    }

    // Añadir un objeto al principio
    lse.addFirst(new CDatos("abcd", 10));
    // Añadir un objeto en la posición 1
    lse.add(1, new CDatos("defg", 9.5));
        
    System.out.println("\n");
    // Mostrar el primero
    CDatos obj = (CDatos)lse.getFirst();
    System.out.println("Primero: " + obj.obtenerNombre() + " " +
                       obj.obtenerNota());

    // Mostrar el último
    obj = (CDatos)lse.getLast();
    System.out.println("Último:  " + obj.obtenerNombre() + " " +
                       obj.obtenerNota());
    // Mostrar todos
    System.out.println("Lista:");
    mostrarLista(lse);
    
    // Borrar el elemento de índice 2
    obj = (CDatos)lse.remove(2);
    
    // Modificar el elemento de índice 1
    obj = (CDatos)lse.get(1);
    obj.asignarNota(9);
    
    // Mostrar todos
    System.out.println("Lista:");
    mostrarLista(lse);
  }
}
