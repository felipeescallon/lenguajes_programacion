import java.io.*;

public class CAscii
{
  // C�digo ASCII de cada uno de los caracteres de un texto
  public static void main(String[] args)
  {
    final char eof = (char)-1;
    char car = 0; // car = car�cter nulo (\0)
    try
    {
      System.out.println("Introduzca una cadena de texto.");
      System.out.println("Para terminar pulse Ctrl+z\n");
      while ((car = (char)System.in.read()) != eof)
      {
        if (car != '\r' && car != '\n')
          System.out.println("El c�digo ASCII de " + car + 
                             " es " + (int)car);
      }
    }
    catch(IOException ignorada) {}
  }
}
