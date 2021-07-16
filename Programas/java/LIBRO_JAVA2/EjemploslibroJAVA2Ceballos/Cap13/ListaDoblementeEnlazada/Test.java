//////////////////////////////////////////////////////////////////
// Lista circular doblemente enlazada
//
public class Test
{
  public static void mostrarLista(CListaCircularDE lista)
  {
    // Mostrar todos los elementos de la lista
    long i = 0, tam = lista.tamaño();
    CDatos obj;
    while (i < tam)
    {
      obj = (CDatos)lista.obtener(i);
      System.out.println(i + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      i++;
    }
    if (tam == 0) System.out.println("lista vacía");
  }
  
  public static void main(String[] args)
  {
    // Crear una lista vacía
    CListaCircularDE lcde = new CListaCircularDE();
    
    // Insertar elementos
    lcde.insertar(new CDatos("alumno1", 7.8));
    lcde.insertar(new CDatos("alumno2", 6.5));
    lcde.insertar(new CDatos("alumno3", 10));
    lcde.insertar(new CDatos("alumno4", 8.6));
    
    // Ir al elemento de la posición 2
    lcde.irAl(2);
    
    // Borrar el elemento actual (posición 2)
    lcde.borrar();
    
    // Ir al anterior
    lcde.irAl(1);
    lcde.insertar(new CDatos("nuevo alumno3", 9.5));
    
    // Ir al final
    lcde.irAlFinal();
    lcde.insertar(new CDatos("alumno5", 8.5));
    
    // Ir al anterior
    lcde.irAlAnterior();
    lcde.modificar(new CDatos("alumno4", 5.5));
    
    // Mostrar la lista
    System.out.println("\nLista:");
    mostrarLista(lcde);
  }
}
