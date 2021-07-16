import java.io.*;

public class Test
{
  public static void main(String args[])
  {
    char car = '\0';
    try
    {
      System.out.print("\nDesea continuar s/n (sí o no) ");
      car = (char)System.in.read();
      // Saltar los caracteres disponibles en el flujo de entrada
      System.in.skip(System.in.available());
      while (car != 's' && car != 'n')
      {
        System.out.print("\nDesea continuar s/n (sí o no) ");
        car = (char)System.in.read();
        System.in.skip(System.in.available());
      }
    }
    catch(IOException ignorada) {}
  }
}
