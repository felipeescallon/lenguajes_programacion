import java.io.*;

//////////////////////////////////////////////////////////////////
// Ordenar un fichero utilizando el método de mezcla natural.
// Se trata de un fichero de texto que almacena una lista de
// nombres.
// El nombre del fichero se recibe a través de la línea de órdenes.
// La ordenación se realiza en orden alfabético ascendente.
// La aplicación está soportada por la clase CMezclaNatural.
//    Métodos:
//      mezclaNatural
//      distribuir
//      mezclar
//      main
//
public class CMezclaNatural
{
  // Mezcla natural //////////////////////////////////////////////
  public static void mezclaNatural(File fichFuente)
    throws IOException
  {
    // Definición de variables
    File a = new File("ftempa.tmp"); // fichero temporal
    File b = new File("ftempb.tmp"); // fichero temporal

    int nro_tramos;
    do
    {
      nro_tramos = distribuir(fichFuente, a, b);
      if (nro_tramos <= 1)
      {
        // Proceso finalizado. Borrar los ficheros temporales.
        a.delete(); b.delete();
        return;
      }
      nro_tramos = mezclar(a, b, fichFuente);
    }
    while (nro_tramos != 1);
  } // mezclaNatural

  // Fase de distribución ////////////////////////////////////////
  public static int distribuir(File fuente, File destinoA,
                               File destinoB) throws IOException
  {
    // Abrir un flujo de entrada desde fuente que permita
    // leer la información línea a línea.
    FileInputStream fis = new FileInputStream(fuente);
    InputStreamReader isr = new InputStreamReader(fis);
    BufferedReader fc = new BufferedReader(isr);
  
    // Abrir un flujo de salida hacia destinoA
    FileOutputStream fosA = new FileOutputStream(destinoA);
    OutputStreamWriter osrA = new OutputStreamWriter(fosA);
    BufferedWriter fa = new BufferedWriter(osrA);
      
    // Abrir un flujo de salida hacia destinoB
    FileOutputStream fosB = new FileOutputStream(destinoB);
    OutputStreamWriter osrB = new OutputStreamWriter(fosB);
    BufferedWriter fb = new BufferedWriter(osrB);
    
    BufferedWriter faux = fa; // faux será fa o fb
    String línea;             // última línea leída
    String línea_ant;         // línea anterior a la última leída
    int nro_tramos = 1;       // número total de tramos ordenados

    // Leer la primera línea (línea anterior)
    if ((línea_ant = fc.readLine()) != null)
    {
      // Escribe en fa la línea leída más el separador de línea
      fa.write(línea_ant); fa.newLine();
    }
    else
    {
      faux = null; fc.close(); fa.close(); fb.close();
      return 0;
    }

    // Leer la siguiente línea (línea actual)
    while ((línea = fc.readLine()) != null)
    {
      if (línea.compareTo(línea_ant) < 0)
      {
        // Cambiar al otro fichero
        faux = (faux == fa) ? fb : fa;
        ++nro_tramos;
      }
      línea_ant = línea;
      // Escribe en faux la línea leída más el separador de línea
      faux.write(línea); faux.newLine();
    }
    faux = null; fc.close(); fa.close(); fb.close();
    return nro_tramos;
  } // distribuir

  // Fase de mezcla //////////////////////////////////////////////
  public static int mezclar(File fuenteA, File fuenteB,
                            File destino) throws IOException
  {
    // Abrir un flujo de entrada desde fuenteA que permita
    // leer la información línea a línea.
    FileInputStream fisA = new FileInputStream(fuenteA);
    InputStreamReader isrA = new InputStreamReader(fisA);
    BufferedReader fa = new BufferedReader(isrA);
  
    // Abrir un flujo de entrada desde fuenteB que permita
    // leer la información línea a línea.
    FileInputStream fisB = new FileInputStream(fuenteB);
    InputStreamReader isrB = new InputStreamReader(fisB);
    BufferedReader fb = new BufferedReader(isrB);
  
    // Abrir un flujo de salida hacia destino
    FileOutputStream fos = new FileOutputStream(destino);
    OutputStreamWriter osr = new OutputStreamWriter(fos);
    BufferedWriter fc = new BufferedWriter(osr);
    
  
    String líneaDeFa, líneaDeFb, líneaDeFa_ant, líneaDeFb_ant;
    int nro_tramos = 1;

    // Leemos las dos primeras líneas, una de fa y otra de fb
    líneaDeFa = fa.readLine();
    líneaDeFa_ant = líneaDeFa;
    líneaDeFb = fb.readLine();
    líneaDeFb_ant = líneaDeFb;
    
    // Vamos leyendo y comparando hasta que se acabe alguno de los
    // ficheros. La fusión se realiza entre pares de tramos
    // ordenados. Un tramo de fa y otro de fb darán lugar a un
    // tramo ordenado sobre fc.
    while (líneaDeFa != null && líneaDeFb != null)
    {
      if (líneaDeFa.compareTo(líneaDeFb) < 0)       // if 1
      {
        if (líneaDeFa.compareTo(líneaDeFa_ant) < 0) // if 2
        // Encontrado el final del tramo de fa
        {
          líneaDeFa_ant = líneaDeFa;
          // Copiamos el tramo ordenado de fb
          do
          {
            fc.write(líneaDeFb); fc.newLine();
            líneaDeFb_ant = líneaDeFb;
          }
          while ((líneaDeFb = fb.readLine()) != null &&
                 líneaDeFb.compareTo(líneaDeFb_ant) > 0);
          ++nro_tramos;
        }
        else // de if 2
        {
          // Copiamos la cadena leída de fa
          líneaDeFa_ant = líneaDeFa;
          fc.write(líneaDeFa); fc.newLine();
          líneaDeFa = fa.readLine();
        }
      }
      else // de if 1
      {
        if (líneaDeFb.compareTo(líneaDeFb_ant) < 0)  // if 3
        // Encontrado el final del tramo de fb
        {
          líneaDeFb_ant = líneaDeFb;
          // Copiamos el tramo ordenado de fa
          do
          {
            fc.write(líneaDeFa); fc.newLine();
            líneaDeFa_ant = líneaDeFa;
          }
          while ((líneaDeFa = fa.readLine()) != null &&
                 líneaDeFa.compareTo(líneaDeFa_ant) > 0);
          ++nro_tramos;
        }
        else // de if 3
        {
          // Copiamos la cadena leída de fb
          líneaDeFb_ant = líneaDeFb;
          fc.write(líneaDeFb); fc.newLine();
          líneaDeFb = fb.readLine();
        }
      }
    } // de while

    // En el caso de acabarse primero los datos de fb
    if (líneaDeFb == null)
    {
      fc.write(líneaDeFa); fc.newLine();
      while ((líneaDeFa = fa.readLine()) != null)
      {
        fc.write(líneaDeFa); fc.newLine();
      }
    }
    // En el caso de acabarse primero los datos de fa
    else if (líneaDeFa == null)
    {
      fc.write(líneaDeFb); fc.newLine();
      while ((líneaDeFb = fb.readLine()) != null)
      {
        fc.write(líneaDeFb); fc.newLine();
      }
    }
    fc.close(); fa.close(); fb.close();
    return nro_tramos;
  } // de mezclar
    
  public static void main(String[] args)
  {
    // main debe recibir un parámetro: el fichero a ordenar.
    if (args.length != 1)
      System.err.println("Sintaxis: java CMezclaNatural " +
                         "<nombre_fichero>");
    else
    {
      File nombreFichero = new File(args[0]);
      try
      {
        // Asegurarse de que "nombreFichero" existe y se puede leer
        if (!nombreFichero.exists() || !nombreFichero.isFile())
          throw new IOException("No existe el fichero " + 
                                nombreFichero);
        if (!nombreFichero.canRead())
          throw new IOException("El fichero " + nombreFichero +
                                " no se puede leer");
       
        mezclaNatural(nombreFichero); // realizar la ordenación
        
        // Mostrar el contenido del fichero
        char resp;
        System.out.print("¿Desea ver el contenido del fichero? s/n: ");
        resp = Leer.carácter();
        Leer.limpiar();
        if (resp == 's')
        {
          // Abrir un flujo de entrada desde nombreFichero
          // que permita leer la información línea a línea.
          FileInputStream fis = new FileInputStream(nombreFichero);
          InputStreamReader isr = new InputStreamReader(fis);
          BufferedReader fc = new BufferedReader(isr);
          
          // Leer el fichero y mostrarlo
          String línea;
          while ((línea = fc.readLine()) != null)
            System.out.println(línea);
          fc.close();
        }
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}
//////////////////////////////////////////////////////////////////
