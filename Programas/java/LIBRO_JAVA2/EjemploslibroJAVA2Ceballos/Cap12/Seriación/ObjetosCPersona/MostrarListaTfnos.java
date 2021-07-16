import java.io.*;

public class MostrarListaTfnos
{
  public static void mostrarFichero(String nombreFichero)
    throws IOException
  {
    PrintStream flujoS = System.out; // salida estándar
    ObjectInputStream ois = null;// entrada de datos desde el fichero
    File fichero = null;       // objeto que identifica el fichero
    
    try
    {
      // Crear un objeto File que identifique al fichero
      fichero = new File(nombreFichero);

      // Verificar si el fichero existe
      if (fichero.exists())
      {
        // Si existe, abrir un flujo desde el mismo
        ois = new ObjectInputStream(new FileInputStream(fichero));
        
        // Declarar los datos a leer desde el fichero
        CPersona persona;
        String nombre, dirección;
        long teléfono;
        do
        {
          // Leer un objeto CPersona desde el fichero. Cuando se
          // alcance el final del fichero Java lanzará una
          // excepción del tipo EOFException.
          persona = (CPersona)ois.readObject();

          nombre = persona.obtenerNombre();
          dirección = persona.obtenerDirección();
          teléfono = persona.obtenerTeléfono();
          
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
    catch(ClassNotFoundException e)
    {
      flujoS.println("Error: " + e.getMessage());
    }
    finally
    {
      // Cerrar el flujo
      if (ois != null) ois.close();
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
