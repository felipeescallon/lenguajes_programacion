/*  PROGRAMA CREADO POR: FELIPE ESCALLON Y DIEGO MANQUILLO=>PERO DADO POR EL PROFESOR
 *  INFORMATICA 3
 *  JAVA 2!
 *  AGO/19/04
 */              
import java.io.*;

public class Leer
{
  public static String dato()
  {
    String sdato = "";
    try
    {
      // Definir un flujo de caracteres de entrada: flujoE
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader flujoE = new BufferedReader(isr);
      // Leer. La entrada finaliza al pulsar la tecla Entrar
      sdato = flujoE.readLine();
    }
    catch(IOException e)
    {
      System.err.println("Error: " + e.getMessage());
    }
    return sdato; // devolver el dato tecleado
  }
  
   
  public static int datoInt()
  {
    try
    {
      return Integer.parseInt(dato());
    }
    catch(NumberFormatException e)
    {
      return Integer.MIN_VALUE; // valor más pequeño cuando se entre un dato invalido(letra)
    }
  }
  
}
