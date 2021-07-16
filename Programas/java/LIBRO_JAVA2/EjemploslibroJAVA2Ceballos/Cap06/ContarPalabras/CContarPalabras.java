import java.io.*;
// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CContarPalabras
{
  // Contar caracteres, palabras y líneas en un texto 
  public static void main(String[] args)
  {
    final char eof = (char)-1;
    char car;
    boolean palabra = false;
    int ncaracteres = 0, npalabras = 0, nlineas = 0;
    
    try
    {
      System.out.println("Introducir texto. " +
                         "Pulse [Entrar] después de cada línea.");
      System.out.println("Para finalizar pulsar Ctrl+z.\n");
      while ((car = (char)System.in.read()) != eof)
      {
        // [Entrar] = CRLF = \r\n
        if (car == '\r') continue; // le sigue un \n
        ncaracteres++;   // contador de caracteres
        // Eliminar blancos, tabuladores y finales de línea
        // entre palabras
        if (car == ' ' || car == '\n' || car == '\t')
          palabra = false;
        else if (!palabra) // comienza una palabra
        {
          npalabras++;   // contador de palabras
          palabra = true;
        }
        if (car == '\n') // finaliza una línea
          nlineas++;     // contador de líneas
      }
      System.out.println();
      System.out.println(ncaracteres + " " + npalabras + " " +
                         nlineas);
    }
    catch(IOException ignorada) {}
  }
}
