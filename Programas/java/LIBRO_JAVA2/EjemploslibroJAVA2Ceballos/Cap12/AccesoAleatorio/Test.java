import java.io.*;
//////////////////////////////////////////////////////////////////
// Aplicación para trabajar con un fichero accedido aleatoriamente
//
public class Test
{
  // Definir una referencia al flujo estándar de salida: flujoS
  static PrintStream flujoS = System.out;

  static CListaTfnos listatfnos;

  public static void imprimirListaTfnos() throws IOException
  {
    // Crear un flujo hacia la impresora
    FileWriter flujoS = new FileWriter("LPT1");
  
    String crlf = "\r\n"; // cambiar a la siguiente línea
    String ff = "\f";     // saltar a la siguiente página
    Integer i;            // referencia a un objeto Integer
    Long l;               // referencia a un objeto Long
    int nregs = listatfnos.longitud(); // número de registros

    for (int n = 0; n < nregs; n++)
    {
      // Saltar página inicialmente y después cada 60 líneas
      if (n % 60 == 0) flujoS.write(ff);
      // Imprimir el registro n de la lista de teléfonos
      i = new Integer(n); // número de registro
      flujoS.write("Registro: " + i.toString() + crlf);
      flujoS.write(listatfnos.valorEn(n).obtenerNombre() + crlf);
      flujoS.write(listatfnos.valorEn(n).obtenerDirección() + crlf);
      l = new Long(listatfnos.valorEn(n).obtenerTeléfono());
      flujoS.write(l.toString() + crlf);
      flujoS.write(crlf); // saltar una línea
    }
    flujoS.write(ff); // saltar a la siguiente página
    flujoS.close();   // cerrar el flujo hacia la impresora
  }
  
  public static boolean modificar(int nreg) throws IOException
  {
    String nombre, dirección;
    long teléfono;
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
      flujoS.println("2. Dirección");
      flujoS.println("3. Teléfono");
      flujoS.println("4. Salir y salvar los cambios");
      flujoS.println("5. Salir sin salvar los cambios");
      flujoS.println();
      flujoS.print("   Opción: ");
      op = Leer.datoInt();
          
      switch( op )
      {
        case 1: // modificar nombre
          flujoS.print("nombre:    ");
          nombre = Leer.dato();
          obj.asignarNombre(nombre);
          break;
        case 2: // modificar dirección
          flujoS.print("dirección: ");
          dirección = Leer.dato();
          obj.asignarDirección(dirección);
          break;
        case 3: // modificar teléfono
          flujoS.print("teléfono:  ");
          teléfono = Leer.datoLong();
          obj.asignarTeléfono(teléfono);
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
    // fichero actual que en su campo teléfono no tengan un 0
    CPersona obj;
    for ( int reg_i = 0; reg_i < nregs; reg_i++ )
    {
      obj = listatfnos.valorEn(reg_i);
      if (obj.obtenerTeléfono() != 0)
        ftemp.añadir(obj);
    }
    listatfnos.cerrar();
    ftemp.cerrar();
    fActual.delete();
    if (!ficheroTemp.renameTo(fActual))
      throw new IOException("no se renombró el fichero");
  }  

  public static int menú()
  {
    flujoS.print("\n\n");
    flujoS.println("1. Buscar");
    flujoS.println("2. Buscar siguiente");
    flujoS.println("3. Modificar");
    flujoS.println("4. Añadir");
    flujoS.println("5. Eliminar");
    flujoS.println("6. Imprimir");    
    flujoS.println("7. Salir");    
    flujoS.println();
    flujoS.print("   Opción: ");
    int op;
    do
      op = Leer.datoInt();
    while (op < 1 || op > 7);
    return op;
  }
  
  public static void main(String[] args)
  {
    int opción = 0, pos = -1;
    String cadenabuscar = null;
    String nombre, dirección;
    long teléfono;
    boolean eliminado = false;
    boolean modificado = false;
    
    try
    {
      // Crear un objeto lista de teléfonos vacío (con 0 elementos)
      // o con el contenido del fichero listatfnos.dat si existe.
      File fichero = new File("listatfnos.dat");
      listatfnos = new CListaTfnos(fichero);
      
      do
      {
        opción = menú();
        switch (opción)
        {
          case 1: // buscar
            flujoS.print("conjunto de caracteres a buscar ");
            cadenabuscar = Leer.dato();
            pos = listatfnos.buscar(cadenabuscar, 0);
            if (pos == -1)
              if (listatfnos.longitud() != 0)
                flujoS.println("búsqueda fallida");
              else
                flujoS.println("lista vacía");
            else
            {
              flujoS.println("Número de registro: " + pos);
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
              flujoS.println("Número de registro: " + pos);
              flujoS.println(listatfnos.valorEn(pos).obtenerNombre());
              flujoS.println(listatfnos.valorEn(pos).obtenerDirección());
              flujoS.println(listatfnos.valorEn(pos).obtenerTeléfono());
            }
            break;
          case 3: // modificar
            // Solicitar el número de registro a modificar  
            flujoS.print("número de registro entre 0 y " + 
                         (listatfnos.longitud() - 1) + ": ");
            pos = Leer.datoInt();
            modificado = modificar(pos);
            if (modificado)
              flujoS.println("modificación realizada con éxito");
            else
              flujoS.println("error: no se modificó el registro");
            break;
          case 4: // añadir
            flujoS.print("nombre:    "); nombre = Leer.dato();
            flujoS.print("dirección: "); dirección = Leer.dato();
            flujoS.print("teléfono:  "); teléfono = Leer.datoLong();
            listatfnos.añadir(new CPersona(nombre, dirección, teléfono));
            break;
          case 5: // eliminar
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
          case 6: // imprimir
            imprimirListaTfnos();
            break;
          case 7: // salir
            // guardar lista
            if (eliminado) actualizar(fichero);
            listatfnos = null;
        }
      }
      while(opción != 7);
    }
    catch (IOException e)
    {
      flujoS.println("Error: " + e.getMessage());
    }
  }
}
