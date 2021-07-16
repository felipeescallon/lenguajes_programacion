//////////////////////////////////////////////////////////////////
// Pilas y colas
//
public class Test
{
  public static void mostrarLista(CListaCircularSE lista)
  {
    // Mostrar todos los elementos de la lista
    int i = 0, tam = lista.tamaño();
    CDatos obj;
    while (i < tam)
    {
      if (lista instanceof CPila) 
        obj = (CDatos)((CPila)lista).sacarDePila();
      else if (lista instanceof CCola)
        obj = (CDatos)((CCola)lista).sacarDeCola();
      else
      {
        i++;
        continue;
      }
      System.out.println(i + ".-  " + obj.obtenerNombre() + " " +
                         obj.obtenerNota());
      i++;
    }
    if (tam == 0) System.out.println("lista vacía");
  }
  
  public static void main(String[] args)
  {
    // Crear una pila y una cola vacías
    CPila pila = new CPila();
    CCola cola = new CCola();
    
    // Leer datos y añadirlos a ambas
    String nombre;
    double nota;
    int i = 0;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z.");
    System.out.print("nombre: ");
    while ((nombre = Leer.dato()) != null)
    {
      System.out.print("nota:   ");
      nota = Leer.datoDouble();
      pila.meterEnPila(new CDatos(nombre, nota));
      cola.meterEnCola(new CDatos(nombre, nota));
      System.out.print("nombre: ");
    }
    System.out.println("\n");

    // Mostrar la pila
    System.out.println("\nPila:");
    mostrarLista(pila);
    // Mostrar la pila por segunda vez
    System.out.println("\nPila:");
    mostrarLista(pila);
    
    // Mostrar la cola
    System.out.println("\nCola:");
    mostrarLista(cola);
    // Mostrar la cola por segunda vez
    System.out.println("\nCola:");
    mostrarLista(cola);
    
    // Crear una lista circular
    CListaCircularSE lcse = new CListaCircularSE();
    lcse.añadirAlFinal(new CDatos("lcse", 10));
    // Mostrar la lista circular
    System.out.println("\nlcse:");
    mostrarLista(lcse);
  }
}
