import java.io.*;
/////////////////////////////////////////////////////////////////
// Aplicación para trabajar con matrices de objetos
//
public class Test
{
  public static int menú()
  {
    System.out.print("\n\n");
    System.out.println("1. Buscar");
    System.out.println("2. Buscar siguiente");
    System.out.println("3. Añadir");
    System.out.println("4. Eliminar");
    System.out.println("5. Salir");    
    System.out.println();
    System.out.print("   Opción: ");
    int op;
    do
      op = Leer.datoInt();
    while (op < 1 || op > 5);
    return op;
  }
  
  public static void main(String[] args)
  {
    // Definir un flujo de caracteres de entrada: flujoE
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader flujoE = new BufferedReader(isr);
    // Definir una referencia al flujo estándar de salida: flujoS
    PrintStream flujoS = System.out;

    // Crear un objeto lista de teléfonos vacío (con cero elementos)
    CListaTfnos listatfnos = new CListaTfnos();
    int opción = 0, pos = -1;
    String cadenabuscar = null;
    String nombre, dirección;
    long teléfono;
    boolean eliminado = false;
    do
    {
      try
      {
        opción = menú();
        switch (opción)
        {
          case 1: // buscar
            flujoS.print("conjunto de caracteres a buscar ");
            cadenabuscar = flujoE.readLine();
            pos = listatfnos.buscar(cadenabuscar, 0);
            if (pos == -1)
              if (listatfnos.longitud() != 0)
                flujoS.println("búsqueda fallida");
              else
                flujoS.println("lista vacía");
            else
            {
              flujoS.println(listatfnos.valorEn(pos).obtenerNombre());
              flujoS.println(listatfnos.valorEn(pos).obtenerDirección());
              flujoS.println(listatfnos.valorEn(pos).obtenerTeléfono());
            }
            break;
          case 2: // buscar siguiente
            pos = listatfnos.buscar(cadenabuscar, pos + 1);
            if (pos == -1)
              if (listatfnos.longitud() != 0)
                flujoS.println("búsqueda fallida");
              else
                flujoS.println("lista vacía");
            else
            {
              flujoS.println(listatfnos.valorEn(pos).obtenerNombre());
              flujoS.println(listatfnos.valorEn(pos).obtenerDirección());
              flujoS.println(listatfnos.valorEn(pos).obtenerTeléfono());
            }
            break;
         case 3: // añadir
            flujoS.print("nombre:    "); nombre = flujoE.readLine();
            flujoS.print("dirección: "); dirección = flujoE.readLine();
            flujoS.print("teléfono:  "); teléfono = Leer.datoLong();
            listatfnos.añadir(new CPersona(nombre, dirección, teléfono));
            break;
          case 4: // eliminar
            flujoS.print("teléfono: "); teléfono = Leer.datoLong();
            eliminado = listatfnos.eliminar(teléfono);
            if (eliminado)
              flujoS.println("registro eliminado");
            else
              if (listatfnos.longitud() != 0)
                flujoS.println("teléfono no encontrado");
              else
                flujoS.println("lista vacía");
            break;
          case 5: // salir
            listatfnos = null;
        }
      }
      catch (IOException ignorada) {}
    }
    while(opción != 5);
  }
}
