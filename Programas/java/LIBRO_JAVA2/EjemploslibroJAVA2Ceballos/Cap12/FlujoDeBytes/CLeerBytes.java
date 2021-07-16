import java.io.*;

public class CLeerBytes
{
  public static void main (String[] args)
  {
    FileInputStream fe = null;
    byte[] buffer = new byte[81];
    int nbytes;
    String nombreFichero = null;
    File fichero = null;
    
    try
    {
      do
      {
        System.out.print("Nombre del fichero: ");
        nbytes = System.in.read(buffer);
        nombreFichero = new String(buffer, 0, nbytes-2); // menos CR+LF
        fichero = new File(nombreFichero);
      }
      while (!fichero.exists());
      
      fe = new FileInputStream(fichero);
      nbytes = fe.read(buffer, 0, 81);
      String str = new String(buffer, 0, nbytes);
      System.out.println(str);
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
        if (fe != null) fe.close();
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.toString());
      }
    }
  }
}
