import java.io.*;

public class LeerUnaCadena
{
  public static void  main(String args[])
  {
    // Definir un flujo de caracteres de entrada: flujoE
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader flujoE = new BufferedReader(isr);
    // Definir una referencia al flujo est�ndar de salida: flujoS
    PrintStream flujoS = System.out;
    String sdato; // variable para almacenar una l�nea de texto
    
    try
    {
      flujoS.print("Introduzca un texto: ");
      sdato = flujoE.readLine(); // leer una l�nea de texto
      flujoS.println(sdato);     // escribir la l�nea le�da
    }
    catch (IOException ignorada) { }
  }
}
