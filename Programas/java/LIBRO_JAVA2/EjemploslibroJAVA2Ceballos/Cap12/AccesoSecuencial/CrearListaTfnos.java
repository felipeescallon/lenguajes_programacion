import java.io.*;
// Se utiliza también la clase Leer modificada en este capítulo

public class CrearListaTfnos
{
  public static void crearFichero(File fichero)
    throws IOException
  {
    PrintStream flujoS = System.out; // salida estándar
    DataOutputStream dos = null;// salida de datos hacia el fichero
    char resp;
    try
    {
      // Crear un flujo hacia el fichero que permita escribir
      // datos de tipos primitivos y que utilice un buffer.
      dos = new DataOutputStream(new BufferedOutputStream(
                                 new FileOutputStream(fichero)));
      // Declarar los datos a escribir en el fichero
      String nombre, dirección;
      long teléfono;
      // Leer datos de la entrada estándar y escribirlos
      // en el fichero
      do
      {
        flujoS.print("nombre:    "); nombre = Leer.dato();
        flujoS.print("dirección: "); dirección = Leer.dato();
        flujoS.print("teléfono:  "); teléfono = Leer.datoLong();
            
        // Almacenar un nombre, una dirección y un teléfono en
        // el fichero
        dos.writeUTF(nombre);
        dos.writeUTF(dirección);
        dos.writeLong(teléfono);
            
        flujoS.print("¿desea escribir otro registro? (s/n) ");
        resp = Leer.carácter();
        Leer.limpiar();
      }
      while (resp == 's');
    }
    finally
    {
      // Cerrar el flujo
      if (dos != null) dos.close();
    }
  }

  public static void main(String[] args)
  {
    PrintStream flujoS = System.out; // salida estándar
    String nombreFichero = null;     // nombre del fichero
    File fichero = null; // objeto que identifica el fichero
    
    try
    {
      // Crear un objeto File que identifique al fichero
      flujoS.print("Nombre del fichero: ");
      nombreFichero = Leer.dato();
      fichero = new File(nombreFichero);
      
      // Verificar si el fichero existe
      char resp = 's';
      if (fichero.exists())
      {
        flujoS.print("El fichero existe ¿desea sobreescribirlo? (s/n) ");
        resp = Leer.carácter();
        Leer.limpiar();
      }
      if (resp == 's')
      {
        crearFichero(fichero);
      }
    }
    catch(IOException e)
    {
      flujoS.println("Error: " + e.getMessage());
    }
  }
}
