import java.io.*;
public class CCadenas
{
  // Convertir una cadena a Mayúsculas
  static void MinusculasMayusculas(char[] str)
  {
    int i = 0, desp = 'a' - 'A';
    for (i = 0; i < str.length && str[i] != '\0'; i++)
      if (str[i] >= 'a' && str[i] <= 'z')
        str[i] = (char)(str[i] - desp);
  }

  public static void main(String[] args)
  {
    char[] cadena = new char[80]; // matriz de caracteres
    int car, i = 0;  // un carácter y el subíndice para la matriz
    
    try
    {
      System.out.println("Escriba una cadena de caracteres:");
      while ((car = System.in.read()) != '\r' && i < cadena.length)
        cadena[i++] = (char)car;
      // Convertir minúsculas a mayúsculas
      MinusculasMayusculas(cadena); // llamar al método
      System.out.println(cadena);
    }
    catch(IOException ignorada) {}
  }
}
