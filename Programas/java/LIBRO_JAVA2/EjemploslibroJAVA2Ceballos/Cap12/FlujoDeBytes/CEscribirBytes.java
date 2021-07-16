import java.io.*;

public class CEscribirBytes
{
  public static void main (String[] args)
  {
    FileOutputStream fs = null;
    byte[] buffer = new byte[81];
    int nbytes;
    String nombreFichero = null;
    File fichero = null;
    
    try
    {
      System.out.print("Nombre del fichero: ");
      nbytes = System.in.read(buffer);
      nombreFichero = new String(buffer, 0, nbytes-2); // menos CR+LF
      fichero = new File(nombreFichero);

      char resp = 's';
      if (fichero.exists())
      {
        System.out.print("El fichero existe ¿desea sobreescribirlo? (s/n) ");
        resp = (char)System.in.read();
        // Saltar los bytes no leídos del flujo in
        System.in.skip(System.in.available());
      }

      if (resp == 's')
      {
        System.out.println(
          "Escriba el texto que desea almacenar en el fichero:");
        nbytes = System.in.read(buffer);
        fs = new FileOutputStream(fichero);
        fs.write(buffer, 0, nbytes);
      }
    }
    catch(IOException e)
    {
      System.out.println("Error: " + e.toString());
    }
    finally
    {
      try
      {
        // Cerrar el fichero
        if (fs != null) fs.close();
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.toString());
      }
    }
  }
}
