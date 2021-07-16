import java.io.*;
public class CValorAscii
{
  // Examinar una cadena de caracteres almacenada en una matriz
  public static void main(String[] args)
  {
    char[] cadena = new char[80]; // matriz de caracteres
    int car, i = 0;  // un car�cter y el sub�ndice para la matriz
    
    try
    {
      System.out.println("Escriba una cadena de caracteres:");
      while ((car = System.in.read()) != '\r' && i < cadena.length)
        cadena[i++] = (char)car;
      // Examinar la matriz de caracteres
      i = 0;
      do
      {
        System.out.println("Car�cter = " + cadena[i] + 
                           ", c�digo ASCII = " + (int)cadena[i]);
        i++;
      }
      while (i < cadena.length && cadena[i] != '\0');
    }
    catch(IOException ignorada) {}
  }
}
