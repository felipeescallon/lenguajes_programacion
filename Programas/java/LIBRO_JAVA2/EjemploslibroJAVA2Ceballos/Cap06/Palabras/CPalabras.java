import java.io.*;
// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CPalabras
{
  // Contar el número de palabras en un texto
  // con 4 o más vocales diferentes
  public static void main(String[] args)
  {
    int np = 0; // número de palabras con 4 vocales distintas
    int a = 0, e = 0, i = 0, o = 0, u = 0;
    char car;
    final char eof = (char)-1;
    
    try
    {
      System.out.println("Introducir texto. " + 
                         "Para finalizar pulsar Ctrl+z.\n");
      while ((car = (char)System.in.read()) != eof)
      {
        switch (car)
        {
          case 'A': case 'a': case 'á':
            a = 1;
            break;
          case 'E': case 'e': case 'é':
            e = 1;
            break;
          case 'I': case 'i': case 'í':
            i = 1;
            break;
          case 'O': case 'o': case 'ó':
            o = 1;
            break;
          case 'U': case 'u': case 'ú':
            u = 1;
            break;
          default:
            if (car == ' ')
            {
              if ((a + e + i + o + u) >= 4) np++;
              a = e = i = o = u = 0;
            }
            if (car == '\n')
            {
              if ((a + e + i + o + u) >= 4) np++;
              a = e = i = o = u = 0;
            }
        } // fin del switch
      } // fin del while
      if ((a + e + i + o + u) >= 4) np++;
      System.out.println("\n\nNúmero de palabras con " + 
                         "4 vocales distintas: " + np);
    }
    catch(IOException ignorada) {}
  }
}
