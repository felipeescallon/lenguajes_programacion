import java.io.*;

public class Test
{
  public static void main(String args[])
  {
    // Definir un flujo de caracteres de entrada: flujoE
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader flujoE = new BufferedReader(isr);
    // Definir una referencia al flujo estándar de salida: flujoS
    PrintStream flujoS = System.out;
    
    String sdato;
    float precio = 0.0F;
    
    try
    {
      flujoS.print("Precio: ");
      sdato = flujoE.readLine();
      precio = (sdato != null)
               ? (new Float(sdato)).floatValue()
               : Float.NaN;
    }
    catch (IOException ignorada){ }
    flujoS.println(precio);
    flujoS.println("Continua la aplicación");
  }
}
