import java.io.*;
//////////////////////////////////////////////////////////////////
// Aplicaci�n para trabajar con un fichero accedido aleatoriamente
//
public class Test
{
  // Definir una referencia al flujo est�ndar de salida: flujoS
  static PrintStream flujoS = System.out;

  static CListaTfnos listatfnos;

  public static void imprimirListaTfnos() throws IOException
  {
    // Crear un flujo hacia la impresora
    FileWriter flujoS = new FileWriter("LPT1");
  
    String crlf = "\r\n"; // cambiar a la siguiente l�nea
    String ff = "\f";     // saltar a la siguiente p�gina
    Integer i;            // referencia a un objeto Integer
    Long l;               // referencia a un objeto Long
    int nregs = listatfnos.longitud(); // n�mero de registros

    for (int n = 0; n < nregs; n++)
    {
      // Saltar p�gina inicialmente y despu�s cada 60 l�neas
      if (n % 60 == 0) flujoS.write(ff);
      // Imprimir el registro n de la lista de tel�fonos
      i = new Integer(n); // n�mero de registro
      flujoS.write("Registro: " + i.toString() + crlf);
      flujoS.write(listatfnos.valorEn(n).obtenerNombre() + crlf);
      flujoS.write(listatfnos.valorEn(n).obtenerDirecci�n() + crlf);
      l = new Long(listatfnos.valorEn(n).obtenerTel�fono());
      flujoS.write(l.toString() + crlf);
      flujoS.write(crlf); // saltar una l�nea
    }
    flujoS.write(ff); // saltar a la siguiente p�gina
    flujoS.close();   // cerrar el flujo hacia la impresora
  }
  
  public static boolean modificar(int nreg) throws IOException
  {
    String nombre, direcci�n;
    long tel�fono;
    int op;
    // Leer el registro
    CPersona obj = listatfnos.valorEn(nreg);
    if (obj == null) return false;
    
    // Modificar el registro
    do
    {
      flujoS.print("\n\n");
      flujoS.println("Modificar el dato:");
      flujoS.println("1. Nombre");
      flujoS.println("2. Direcci�n");
      flujoS.println("3. Tel�fono");
      flujoS.println("4. Salir y salvar los cambios");
      flujoS.println("5. Salir sin salvar los cambios");
      flujoS.println();
      flujoS.print("   Opci�n: ");
      op = Leer.datoInt();
          
      switch( op )
      {
        case 1: // modificar nombre
          flujoS.print("nombre:    ");
          nombre = Leer.dato();
          obj.asignarNombre(nombre);
          break;
        case 2: // modificar direcci�n
          flujoS.print("direcci�n: ");
          direcci�n = Leer.dato();
          obj.asignarDirecci�n(direcci�n);
          break;
        case 3: // modificar tel�fono
          flujoS.print("tel�fono:  ");
          tel�fono = Leer.datoLong();
          obj.asignarTel�fono(tel�fono);
          break;
        case 4: // guardar los cambios
          break;
        case 5: // salir sin guardar los cambios
          break;
      }
    }
    while( op != 4 && op != 5);
    if (op == 4)
    {
      listatfnos.ponerValorEn(nreg, obj);             
      return true;
    }
    else
      return false;
  }
  
  public static void actualizar(File fActual) throws IOException 
  {    // Crear un fichero temporal
    File ficheroTemp = new File("listatfnos.tmp");
    CListaTfnos ftemp = new CListaTfnos(ficheroTemp);    
    int nregs = listatfnos.longitud();
    // Copiar en el fichero temporal todos los registros del
    // fichero actual que en su campo tel�fono no tengan un 0
    CPersona obj;
    for ( int reg_i = 0; reg_i < nregs; reg_i++ )
    {
      obj = listatfnos.valorEn(reg_i);
      if (obj.obtenerTel�fono() != 0)
        ftemp.a�adir(obj);
    }
    listatfnos.cerrar();
    ftemp.cerrar();
    fActual.delete();
    if (!ficheroTemp.renameTo(fActual))
      throw new IOException("no se renombr� el fichero");
  }  

  public static int men�()
  {
    flujoS.print("\n\n");
    flujoS.println("1. Buscar");
    flujoS.println("2. Buscar siguiente");
    flujoS.println("3. Modificar");
    flujoS.println("4. A�adir");
    flujoS.println("5. Eliminar");
    flujoS.println("6. Imprimir");    
    flujoS.println("7. Salir");    
    flujoS.println();
    flujoS.print("   Opci�n: ");
    int op;
    do
      op = Leer.datoInt();
    while (op < 1 || op > 7);
    return op;
  }
  
  public static void main(String[] args)
  {
    int opci�n = 0, pos = -1;
    String cadenabuscar = null;
    String nombre, direcci�n;
    long tel�fono;
    boolean eliminado = false;
    boolean modificado = false;
    
    try
    {
      // Crear un objeto lista de tel�fonos vac�o (con 0 elementos)
      // o con el contenido del fichero listatfnos.dat si existe.
      File fichero = new File("listatfnos.dat");
      listatfnos = new CListaTfnos(fichero);
      
      do
      {
        opci�n = men�();
        switch (opci�n)
        {
          case 1: // buscar
            flujoS.print("conjunto de caracteres a buscar ");
            cadenabuscar = Leer.dato();
            pos = listatfnos.buscar(cadenabuscar, 0);
            if (pos == -1)
              if (listatfnos.longitud() != 0)
                flujoS.println("b�squeda fallida");
              else
                flujoS.println("lista vac�a");
            else
            {
              flujoS.println("N�mero de registro: " + pos);
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
              flujoS.println("N�mero de registro: " + pos);
              flujoS.println(listatfnos.valorEn(pos).obtenerNombre());
              flujoS.println(listatfnos.valorEn(pos).obtenerDirecci�n());
              flujoS.println(listatfnos.valorEn(pos).obtenerTel�fono());
            }
            break;
          case 3: // modificar
            // Solicitar el n�mero de registro a modificar  
            flujoS.print("n�mero de registro entre 0 y " + 
                         (listatfnos.longitud() - 1) + ": ");
            pos = Leer.datoInt();
            modificado = modificar(pos);
            if (modificado)
              flujoS.println("modificaci�n realizada con �xito");
            else
              flujoS.println("error: no se modific� el registro");
            break;
          case 4: // a�adir
            flujoS.print("nombre:    "); nombre = Leer.dato();
            flujoS.print("direcci�n: "); direcci�n = Leer.dato();
            flujoS.print("tel�fono:  "); tel�fono = Leer.datoLong();
            listatfnos.a�adir(new CPersona(nombre, direcci�n, tel�fono));
            break;
          case 5: // eliminar
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
          case 6: // imprimir
            imprimirListaTfnos();
            break;
          case 7: // salir
            // guardar lista
            if (eliminado) actualizar(fichero);
            listatfnos = null;
        }
      }
      while(opci�n != 7);
    }
    catch (IOException e)
    {
      flujoS.println("Error: " + e.getMessage());
    }
  }
}
