import java.io.*;

public class MostrarListaTfnos
{
  public static void mostrarFichero(String nombreFichero)
    throws IOException
  {
    PrintStream flujoS = System.out; // salida estándar
    DataInputStream dis = null;// entrada de datos desde el fichero
    File fichero = null;       // objeto que identifica el fichero
    
    try
    {
      // Crear un objeto File que identifique al fichero
      fichero = new File(nombreFichero);

      // Verificar si el fichero existe
      if (fichero.exists())
      {
        // Si existe, abrir un flujo desde el mismo
        dis = new DataInputStream(new BufferedInputStream(
                                  new FileInputStream(fichero)));
        
        // Declarar los datos a escribir en el fichero
        String nombre, dirección;
        long teléfono;
        do
        {
          // Leer un nombre, una dirección y un teléfono desde el
          // fichero. Cuando se alcance el final del fichero Java
          // lanzará una excepción del tipo EOFException.
          nombre = dis.readUTF();
          dirección = dis.readUTF();
          teléfono = dis.readLong();
          
          // Mostrar los datos nombre, dirección y teléfono
          flujoS.println(nombre);
          flujoS.println(dirección);
          flujoS.println(teléfono);
          flujoS.println();
        }
        while (true);
      }
      else
        flujoS.println("El fichero no existe");
    }
    catch(EOFException e)
    {
      flujoS.println("Fin del listado");
    }
    finally
    {
      // Cerrar el flujo
      if (dis != null) dis.close();
    }
  }
  
  public static void main(String[] args)
  {
    if (args.length != 1)
      System.err.println("Sintaxis: java MostrarListaTfnos " +
                         "<fichero fuente>");
    else
    {
      try
      {
        mostrarFichero(args[0]);
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}
