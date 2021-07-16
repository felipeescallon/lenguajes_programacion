import java.io.*;

public class Grep
{
  public static boolean BuscarCadena(String cadena1, String cadena2)
  {
    // ¿cadena2 está contenida en cadena1?
    if (cadena1.indexOf(cadena2) > -1)
      return true;  // sí
    else
      return false; // no
  }
  
  public static void BuscarEnFich(String nombrefich, String cadena)
  {
    // Definiciones de variables
    File fichFuente = new File(nombrefich);
    BufferedReader flujoE = null;
    
    try
    {
      // Asegurarse de que el fichero, existe y se puede leer
      if (!fichFuente.exists() || !fichFuente.isFile())
      {
        System.err.println("No existe el fichero " + nombrefich);
        return;
      }
      if (!fichFuente.canRead())
      {
        System.err.println("El fichero " + nombrefich +
                           " no se puede leer");
        return;
      }
      
      // Abrir un flujo de entrada desde el fichero fuente
      FileInputStream fis = new FileInputStream(fichFuente);
      InputStreamReader isr = new InputStreamReader(fis);
      flujoE = new BufferedReader(isr);

      // Buscar cadena en el fichero fuente
      String linea;
      int nroLinea = 0;
      while ((linea = flujoE.readLine()) != null)
      {
        // Si se alcanzó el final del fichero,
        // readLine devuelve null
        nroLinea++; // contador de líneas
        if (BuscarCadena(linea, cadena))
          System.out.println(nombrefich + " " + nroLinea + " " +
                             linea);
      }
    }
    catch(IOException e)
    {
      System.out.println("Error: " + e.getMessage());
    }
    finally
    {
      // Cerrar el flujo
      try
      {
        if (flujoE != null) flujoE.close();
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.toString());
      }
    }    
  }
  
  public static void main(String[] args)
  {
    // main debe recibir dos o más parámetros: la cadena a buscar
    // y los ficheros fuente. Por ejemplo:
    // java Grep catch Grep.java Leer.java

    if (args.length < 2)
      System.err.println("Sintaxis: java Grep " + "<cadena> " +
                         "<fichero 1> <fichero 2> ...");
    else
    {
      for (int i = 1; i < args.length; i++)
        // Buscar args[0] en args[i]
        BuscarEnFich(args[i], args[0]);
    }
  }
}
