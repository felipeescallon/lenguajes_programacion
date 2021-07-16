import java.io.*;

class ClaseDeUnObj
{
  public static void main(String[] args)
  {
    int n;
    try
    {
      System.out.print("Dato: ");
      n = System.in.read(); // leer un carácter desde el teclado
      System.out.println((char)n); // visualizar el carácter
      
      // Investigamos
      Class ObjetoClass; // objeto Class
      ObjetoClass = System.in.getClass();
      System.out.println("Clase de in:  " + ObjetoClass.getName());
      ObjetoClass = System.out.getClass();
      System.out.println("Clase de out: " + ObjetoClass.getName());
      ObjetoClass = System.err.getClass();
      System.out.println("Clase de err: " + ObjetoClass.getName());
    }
    catch(IOException e)
    {   
      System.err.println("Error: " + e.getMessage());
    }
  }
}
