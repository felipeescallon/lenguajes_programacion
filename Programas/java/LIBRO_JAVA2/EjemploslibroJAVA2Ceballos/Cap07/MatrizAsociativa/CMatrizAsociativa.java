import java.io.*;
// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CMatrizAsociativa
{
  // Frecuencia con la que aparecen las letras en un texto.
  public static void main(String[] args)
  {
    // Crear la matriz c con 'z'-'a'+1 elementos.
    // Java inicia los elementos de la matriz a cero.
    int[] c = new int['z'-'a'+1];
    char car; // subíndice
    final char eof = (char)-1;
        
    // Entrada de datos y cálculo de la tabla de frecuencias
    System.out.println("Introducir un texto.");
    System.out.println("Para finalizar pulsar [Ctrl][z]\n");
    try
    {
      // Leer el siguiente carácter del texto y contabilizarlo
      while ((car = (char)System.in.read()) != eof)
      {
        // Si el carácter leído está entre la 'a' y la 'z'
        // incrementar el contador correspondiente
        if (car >= 'a' && car <= 'z')
          c[car - 'a']++;
      }
    }
    catch (IOException ignorada) {}
    
    // Mostrar la tabla de frecuencias
    System.out.println("\n");
    // Visualizar una cabecera "a b c ... "
    for (car = 'a'; car <= 'z'; car++)
      System.out.print(" " + car);
    System.out.println("\n ------------------------------------" +
    "----------------------------------------");
    // Visualizar la frecuencia con la que han aparecido los caracteres
    for (car = 'a'; car <= 'z'; car++)
      System.out.print(" " + c[car - 'a']);
    System.out.println();
  }
}
