import java.io.*;

public class MostrarListaTfnos
{
  public static void mostrarFichero(String nombreFichero)
    throws IOException
  {
    PrintStream flujoS = System.out; // salida est�ndar
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
        String nombre, direcci�n;
        long tel�fono;
        do
        {
          // Leer un nombre, una direcci�n y un tel�fono desde el
          // fichero. Cuando se alcance el final del fichero Java
          // lanzar� una excepci�n del tipo EOFException.
          nombre = dis.readUTF();
          direcci�n = dis.readUTF();
          tel�fono = dis.readLong();
          
          // Mostrar los datos nombre, direcci�n y tel�fono
          flujoS.println(nombre);
          flujoS.println(direcci�n);
          flujoS.println(tel�fono);
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
