import java.io.*;

public class CLeerCars
{
  public static void main (String[] args)
  {
    byte[] nomFich = new byte[81];
    String nombreFichero = null;
    File fichero = null;
    int nbytes, ncars;
    FileReader fe = null;
    char[] buffer = new char[81];
    
    try
    {
      do
      {
        System.out.print("Nombre del fichero: ");
        nbytes = System.in.read(nomFich);
        nombreFichero = new String(nomFich, 0, nbytes-2); // menos CR+LF
        fichero = new File(nombreFichero);
      }
      while (!fichero.exists());
      
      fe = new FileReader(fichero);
      ncars = fe.read(buffer, 0, 81);
      System.out.println(buffer);
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
