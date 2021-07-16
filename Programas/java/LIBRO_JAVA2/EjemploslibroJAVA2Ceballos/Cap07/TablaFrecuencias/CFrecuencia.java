import java.io.*;
// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CFrecuencia
{
  // Tabla de frecuencias de letras adyacentes en un texto.
  public static void main(String[] args)
  {
    // Crear la matriz tabla con 'z'-'a'+1 por 'z'-'a'+1 elementos.
    // Java inicia los elementos de la matriz a cero.
    int[][] tabla = new int['z'-'a'+1]['z'-'a'+1];
    char f, c;          // subíndices
    char car;           // carácter actual
    char carant = ' ';  // carácter anterior
    final char eof = (char)-1;
        
    // Entrada de datos y cálculo de la tabla de frecuencias
    System.out.println("Introducir un texto.");
    System.out.println("Para finalizar pulsar [Ctrl][z]\n");
    try
    {
      // Leer el siguiente carácter del texto
      while ((car = (char)System.in.read()) != eof)
      {
        // Convertir el carácter a minúsculas si procede
        if (car >= 'A' && car <= 'Z') car += ('a' - 'A');
        // Si el carácter leído está entre la 'a' y la 'z'
        // incrementar el contador correspondiente
        if ((carant>='a' && carant<='z') && (car>='a' && car<='z'))
          tabla[carant - 'a'][car - 'a']++;
        carant = car;
      }
    }
    catch (IOException ignorada) {}
    
    // Mostrar la tabla de frecuencias
    System.out.println("\n");
    // Visualizar una cabecera "a b c ... "
    System.out.print(" ");
    for (c = 'a'; c <= 'z'; c++)
      System.out.print(" " + c);
    System.out.println();
    // Visualizar la tabla de frecuencias
    for (f = 'a'; f <= 'z'; f++)
    {
      System.out.print(f);
      for (c = 'a'; c <= 'z'; c++)
        System.out.print(" " + tabla[f - 'a'][c - 'a']);
      System.out.println();
    }
  }
}
