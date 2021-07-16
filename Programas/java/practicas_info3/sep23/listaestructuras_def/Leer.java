import java.io.*;
public class Leer
{
  // Definir un flujo de caracteres de entrada: flujoE
  private static InputStreamReader isr = new InputStreamReader(System.in);  
  private static PushbackReader flujoE = new PushbackReader(isr);
  public static void limpiar()
  {
    int car = 0;
    try
    {
      while (flujoE.ready()) flujoE.read(); // limpiar flujoE
    }
    catch(IOException e)
    {
      System.err.println("Error: " + e.getMessage());
    }
  }
  
  public static char mirar()
  {
    int car = 0;
    try
    {
      car = flujoE.read();
      flujoE.unread(car);
    }
    catch(IOException e)
    {
      System.err.println("Error: " + e.getMessage());
    }
    return (char)car; // retornar el primer carácter disponible
  }
  
  public static String dato()
  {
    StringBuffer sdato = new StringBuffer();
    int car = 0;
    try
    {
      // Leer. La entrada finaliza al pulsar la tecla Entrar
      while ((car = flujoE.read()) != '\r' && car != -1)
        sdato.append((char)car);
      limpiar();
    }
    catch(IOException e)
    {
      System.err.println("Error: " + e.getMessage());
    }
    if (car == -1) return null;
    return sdato.toString(); // devolver el dato tecleado
  }
  
  public static char caracter()
  {
    int car = 0;
    try
    {
      car = flujoE.read();
    }
    catch(IOException e)
    {
      System.err.println("Error: " + e.getMessage());
    }
    return (char)car; // devolver el dato tecleado
  }
  
 public static char[] datoCadena()
  {
    String auxiliar1;
    auxiliar1=dato();
    return(auxiliar1.toCharArray());
                                       	
  }


  public static short datoShort()
  {
    try
    {
      return Short.parseShort(dato());
    }
    catch(NumberFormatException e)
    {
	return Short.MIN_VALUE; // valor más pequeño
    }
  }
  
  public static int datoInt()
  {
    try
    {
      return Integer.parseInt(dato());
    }
    catch(NumberFormatException e)
    {
  	return Integer.MIN_VALUE; // valor más pequeño
    }
  }
  
  public static long datoLong()
  {
    try
    {
      return Long.parseLong(dato());
    }
    catch(NumberFormatException e)
    {
      return Long.MIN_VALUE; // valor más pequeño
    }
  }
  
  public static float datoFloat()
  {
    try
    {
      Float f = new Float(dato());
      return f.floatValue();
    }
    catch(NumberFormatException e)
    {
     return Float.NaN; // No es un Número; valor float.
    }
  }
  
  public static double datoDouble()
  {
    try
    {
      Double d = new Double(dato());
      return d.doubleValue();
    }
    catch(NumberFormatException e)
    {
	return Double.NaN; // No es un Número; valor double.
    }
  }
}
