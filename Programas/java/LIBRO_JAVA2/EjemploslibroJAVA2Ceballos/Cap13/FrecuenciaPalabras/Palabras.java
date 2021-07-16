import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////
// Utilizar un árbol de búsqueda para obtener la frecuencia con la
// que aparecen las palabras en un fichero de texto.
// Esta aplicación, además de las clases necesarias de la
// biblioteca de Java, utiliza las clases: CArbolBinarioDeBusqueda
// derivada de CArbolBinB y CDatos.
//
public class Palabras
{
  private static CArbolBinarioDeBusqueda arbolbb = new CArbolBinarioDeBusqueda();
  
  public static void palabras(String línea)
  {
    // Descomponer línea en palabras
    StringTokenizer cadena;
    cadena = new StringTokenizer(línea, " ,;.:\n\r\t\f");
    String palabra;
    CDatos obj;
    while (cadena.hasMoreTokens())
    {
      palabra = cadena.nextToken();
      if ((obj = (CDatos)arbolbb.buscar(new CDatos(palabra))) == null)
        arbolbb.insertar(new CDatos(palabra, 1));
      else
        obj.asignarContador(obj.obtenerContador()+1);
    }
  }
  
  public static void leerFichero(String nombrefich)
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
      String línea;

      while ((línea = flujoE.readLine()) != null)
      {
        // Si se alcanzó el final del fichero,
        // readLine devuelve null
        palabras(línea);
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
    // main debe recibir un parámetro: el nombre del fichero
    // java Palabras palabras.txt

    if (args.length < 1)
      System.err.println("Sintaxis: java Palabras <fichero_de_texto>");
    else
      leerFichero(args[0]);
    
    arbolbb.visitarInorden();
    System.err.println();
    System.err.println("Total palabras: " + arbolbb.totalPalabras);
    System.err.println("Total palabras diferentes: " +
                       arbolbb.totalPalabrasDiferentes);
  }
}
