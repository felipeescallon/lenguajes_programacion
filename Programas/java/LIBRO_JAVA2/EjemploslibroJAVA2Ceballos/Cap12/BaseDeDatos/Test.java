import java.io.*;
//////////////////////////////////////////////////////////////////
// Aplicación para trabajar con un fichero accedido aleatoriamente
// Utiliza la clase Leer para leer de la entrada estándar cadenas
// y datos de tipos primitivos.
public class Test
{
  // Definir una referencia al flujo estándar de salida: flujoS
  static PrintStream flujoS = System.out;
  static CBaseDeDatos artículos;
  static boolean ficheroAbierto = false;

  public static void nuevoFich() throws IOException
  {
    if (ficheroAbierto)
    {
      flujoS.println("Ya hay un fichero abierto.");
      return;
    }
    flujoS.print("Nombre del fichero: ");
    File objFichero = new File(Leer.dato()); // nombre fichero
    while (objFichero.exists())
    {
      flujoS.println("Este fichero existe. Escriba otro.");
      objFichero = new File(Leer.dato());
    }
    artículos = new CBaseDeDatos(objFichero);
    ficheroAbierto = true;
  }

  public static void abrirFich() throws IOException
  {
    if (ficheroAbierto)
    {
      flujoS.println("Ya hay un fichero abierto.");
      return;
    }
    
    flujoS.print("Nombre del fichero: ");
    File objFichero = new File(Leer.dato()); // nombre fichero
    
    File obj = null;
    char resp;
    while (!objFichero.exists())
    {
      flujoS.println("Este fichero no existe.");
      flujoS.print("¿Desea ver la lista de ficheros? s/n: ");
      resp = (char)System.in.read();
      System.in.skip(System.in.available());
      if (resp == 'n') return;
      // Obtener un listado del directorio actual de trabajo
      obj = new File(System.getProperty("user.dir"));
      String[] nombresDir = obj.list();
      for (int i = 0; i < nombresDir.length; i++)
        flujoS.print(nombresDir[i] + ",  ");
      flujoS.print("\n\nNombre del fichero: ");
      objFichero = new File(Leer.dato());
    }
    artículos = new CBaseDeDatos(objFichero);
    ficheroAbierto = true;
  }

  public static void añadirReg() throws IOException
  {
    String referencia;
    double precio;
    
    flujoS.print("Referencia:    ");
    referencia = Leer.dato();
    flujoS.print("Precio:        ");
    precio = Leer.datoDouble();
    artículos.añadir(new CRegistro(referencia, precio));  
  }

  public static void modificarReg() throws IOException
  {
    String referencia;
    double precio;
    int op, nreg;
    
    // Solicitar el número de registro a modificar  
    flujoS.print("Número de registro entre 0 y " + 
                 (artículos.longitud() - 1) + ": ");
    nreg = Leer.datoInt();

    // Leer el registro
    CRegistro obj = artículos.valorEn(nreg);
    if (obj == null) return;
    // Visualizarlo
    flujoS.println(obj.obtenerReferencia());
    flujoS.println(obj.obtenerPrecio());
    
    // Modificar el registro
    do
    {
      flujoS.print("\n\n");
      flujoS.println("Modificar el dato:");
      flujoS.println("1. Referencia");
      flujoS.println("2. Precio");
      flujoS.println("3. Salir y salvar los cambios");
      flujoS.println("4. Salir sin salvar los cambios");
      flujoS.println();
      flujoS.print("   Opción: ");
      op = Leer.datoInt();
          
      switch( op )
      {
        case 1: // modificar referencia
          flujoS.print("Referencia:    ");
          referencia = Leer.dato();
          obj.asignarReferencia(referencia);
          break;
        case 2: // modificar precio
          flujoS.print("Precio:  ");
          precio = Leer.datoDouble();
          obj.asignarPrecio(precio);
          break;
        case 3: // guardar los cambios
          break;
        case 4: // salir sin guardar los cambios
          break;
      }
    }
    while( op != 3 && op != 4);
    if (op == 3) artículos.ponerValorEn(nreg, obj);             
  }
  
  public static boolean eliminarReg() throws IOException
  {
    String referencia;
    flujoS.print("Referencia: "); referencia = Leer.dato();
    boolean eliminado = artículos.eliminar(referencia);
    if (eliminado)
      flujoS.println("registro eliminado");
    else
      if (artículos.longitud() != 0)
        flujoS.println("referencia no encontrada");
      else
        flujoS.println("lista vacía");
    return eliminado;
  }
  
  public static void visualizarRegs() throws IOException
  {
    int nreg = -1, nregs = artículos.longitud();
    if (nregs == 0)
      flujoS.println("lista vacía");
    flujoS.print("conjunto de caracteres a buscar ");
    String str = Leer.dato();
    CRegistro obj = null;
    do
    {
      nreg = artículos.buscar(str, nreg+1);
      if (nreg > -1)
      {
        obj = artículos.valorEn(nreg);
        flujoS.println("Registro: " + nreg);
        flujoS.println(obj.obtenerReferencia());
        flujoS.println(obj.obtenerPrecio());
        flujoS.println();
      }
    }
    while (nreg != -1);
    if (obj == null)
      flujoS.println("no se encontró ningún registro");
  }
  
  public static int menú()
  {
    flujoS.print("\n\n");
    flujoS.println("1. Nuevo fichero");
    flujoS.println("2. Abrir fichero");
    flujoS.println("3. Añadir registro");
    flujoS.println("4. Modificar registro");
    flujoS.println("5. Eliminar registro");
    flujoS.println("6. Visualizar registros");
    flujoS.println("7. Salir");    
    flujoS.println();
    flujoS.print("   Opción: ");
    int op;
    do
    {
      op = Leer.datoInt();
      if (op < 1 || op > 7)
        flujoS.print("Opción no válida. Elija otra: ");
    }
    while (op < 1 || op > 7);
    if (op > 2 && op < 7 && !ficheroAbierto)
    {
      flujoS.println("No hay un fichero abierto.");
      return 0;
    }
    return op;
  }
  
  public static void main(String[] args)
  {
    int opción = 0;
    
    try
    {
      do
      {
        opción = menú();
        switch (opción)
        {
          case 1: // nuevo fichero
            nuevoFich();
            break;
          case 2: // abrir fichero
            abrirFich();
            break;
          case 3: // añadir registro al final del fichero
            añadirReg();
            break;
          case 4: // modificar registro
            modificarReg();
            break;
          case 5: // eliminar registro
            eliminarReg();
            break;
          case 6: // visualizar registros
            visualizarRegs();
            break;
          case 7: // salir
            if (artículos.tieneRegsEliminados())
              artículos.actualizar();
            artículos = null;
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
