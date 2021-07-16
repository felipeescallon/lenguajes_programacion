import java.io.*;
/////////////////////////////////////////////////////////////////
// Aplicaci�n para trabajar con matrices de objetos
//
public class Test
{
  public static int men�()
  {
    System.out.print("\n\n");
    System.out.println("1. Buscar");
    System.out.println("2. Buscar siguiente");
    System.out.println("3. A�adir");
    System.out.println("4. Eliminar");
    System.out.println("5. Salir");    
    System.out.println();
    System.out.print("   Opci�n: ");
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
    // Definir una referencia al flujo est�ndar de salida: flujoS
    PrintStream flujoS = System.out;

    // Crear un objeto lista de tel�fonos vac�o (con cero elementos)
    CListaTfnos listatfnos = new CListaTfnos();
    int opci�n = 0, pos = -1;
    String cadenabuscar = null;
    String nombre, direcci�n;
    long tel�fono;
    boolean eliminado = false;
    do
    {
      try
      {
        opci�n = men�();
        switch (opci�n)
        {
          case 1: // buscar
            flujoS.print("conjunto de caracteres a buscar ");
            cadenabuscar = flujoE.readLine();
            pos = listatfnos.buscar(cadenabuscar, 0);
            if (pos == -1)
              if (listatfnos.longitud() != 0)
                flujoS.println("b�squeda fallida");
              else
                flujoS.println("lista vac�a");
            else
            {
              flujoS.println(listatfnos.valorEn(pos).obtenerNombre());
              flujoS.println(listatfnos.valorEn(pos).obtenerDirecci�n());
              flujoS.println(listatfnos.valorEn(pos).obtenerTel�fono());
            }
            break;
          case 2: // buscar siguiente
            pos = listatfnos.buscar(cadenabuscar, pos + 1);
            if (pos == -1)
              if (listatfnos.longitud() != 0)
                flujoS.println("b�squeda fallida");
              else
                flujoS.println("lista vac�a");
            else
            {
              flujoS.println(listatfnos.valorEn(pos).obtenerNombre());
              flujoS.println(listatfnos.valorEn(pos).obtenerDirecci�n());
              flujoS.println(listatfnos.valorEn(pos).obtenerTel�fono());
            }
            break;
         case 3: // a�adir
            flujoS.print("nombre:    "); nombre = flujoE.readLine();
            flujoS.print("direcci�n: "); direcci�n = flujoE.readLine();
            flujoS.print("tel�fono:  "); tel�fono = Leer.datoLong();
            listatfnos.a�adir(new CPersona(nombre, direcci�n, tel�fono));
            break;
          case 4: // eliminar
            flujoS.print("tel�fono: "); tel�fono = Leer.datoLong();
            eliminado = listatfnos.eliminar(tel�fono);
            if (eliminado)
              flujoS.println("registro eliminado");
            else
              if (listatfnos.longitud() != 0)
                flujoS.println("tel�fono no encontrado");
              else
                flujoS.println("lista vac�a");
            break;
          case 5: // salir
            listatfnos = null;
        }
      }
      catch (IOException ignorada) {}
    }
    while(opci�n != 5);
  }
}
