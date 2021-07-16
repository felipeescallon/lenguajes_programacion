import java.io.*;
// Se utiliza tambi�n la clase Leer modificada en este cap�tulo

public class CrearListaTfnos
{
  public static void crearFichero(File fichero)
    throws IOException
  {
    PrintStream flujoS = System.out; // salida est�ndar
    DataOutputStream dos = null;// salida de datos hacia el fichero
    char resp;
    try
    {
      // Crear un flujo hacia el fichero que permita escribir
      // datos de tipos primitivos y que utilice un buffer.
      dos = new DataOutputStream(new BufferedOutputStream(
                                 new FileOutputStream(fichero)));
      // Declarar los datos a escribir en el fichero
      String nombre, direcci�n;
      long tel�fono;
      // Leer datos de la entrada est�ndar y escribirlos
      // en el fichero
      do
      {
        flujoS.print("nombre:    "); nombre = Leer.dato();
        flujoS.print("direcci�n: "); direcci�n = Leer.dato();
        flujoS.print("tel�fono:  "); tel�fono = Leer.datoLong();
            
        // Almacenar un nombre, una direcci�n y un tel�fono en
        // el fichero
        dos.writeUTF(nombre);
        dos.writeUTF(direcci�n);
        dos.writeLong(tel�fono);
            
        flujoS.print("�desea escribir otro registro? (s/n) ");
        resp = Leer.car�cter();
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
    PrintStream flujoS = System.out; // salida est�ndar
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
        flujoS.print("El fichero existe �desea sobreescribirlo? (s/n) ");
        resp = Leer.car�cter();
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
