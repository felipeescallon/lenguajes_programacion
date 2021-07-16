//////////////////////////////////////////////////////////////////
// Crear una lista lineal simplemente enlazada
//
public class Test
{
  public static void main(String[] args)
  {
    // Crear una lista lineal vacía
    CListaLinealSE lse = new CListaLinealSE();
    
    // Leer datos reales y añadirlos a la lista
    double n;
    boolean eof = true;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z.");
    System.out.print("dato: ");
    while (Double.isNaN(n = Leer.datoDouble()) != eof)
    {
      lse.añadirAlPrincipio(n);
      System.out.print("dato: ");
    }

    // Mostrar la lista de datos
    System.out.println();
    lse.mostrarTodos();
  }
}
