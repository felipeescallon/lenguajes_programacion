import java.io.*;

public class CopiarFichero
{
  public static void copiar(String fuente, String destino)
    throws IOException
  {
    // Si el fichero fuente y el destino son el mismo fichero ...
    if (fuente.compareTo(destino) == 0)
        throw new ECopiarFichero("No puede sobreescribirse un " +
                                 "fichero sobre sí mismo");

    // Definiciones de variables, referencias y objetos
    File fichFuente = new File(fuente);
    File fichDestino = new File(destino);
    FileInputStream fFuente = null;
    FileOutputStream fDestino = null;
    byte[] buffer;
    int nbytes;
    
    try
    {
      // Asegurarse de que "fuente" es un fichero, existe
      // y se puede leer.
      if (!fichFuente.exists() || !fichFuente.isFile())
        throw new ECopiarFichero("No existe el fichero " + fuente);
      if (!fichFuente.canRead())
        throw new ECopiarFichero("El fichero " + fuente +
                                 " no se puede leer");
      // Si "destino" existe, asegurarse de que es un fichero que
      // se puede escribir y preguntar si se quiere sobreescribir.
      if (fichDestino.exists())   // ¿existe el destino?
      {
        if (fichDestino.isFile()) // ¿es un fichero?
        {
          if (!fichDestino.canWrite())
            throw new ECopiarFichero("No se puede escribir en " +
                                     "el fichero " + destino);
          // Indicar que el fichero existe y preguntar si se desea
          // sobreescribir.
          System.out.print("El fichero " + destino + " existe. " +
                           "¿Desea sobreescribirlo? (s/n): ");
          // Leer la respuesta
          char resp = (char)System.in.read();
          System.in.skip(System.in.available());
          if (resp == 'n' || resp == 'N')
            throw new ECopiarFichero("Copia cancelada");
        }
        else
          throw new ECopiarFichero(destino + " no es un fichero");
      }
      else // si "destino" no existe verificar que el directorio
           // padre existe y no está protegido contra escritura
      {
        File dirPadre = directorioPadre(fichDestino);
        if (!dirPadre.exists())
          throw new ECopiarFichero("El directorio " + destino +
                                   " no existe");
        if (!dirPadre.canWrite())
          throw new ECopiarFichero("No se puede escribir en el " +
                                    "directorio " + destino);
      }

      // Para realizar la copia, abrir un flujo de entrada desde
      // el fichero fuente y otro de salida hacia el destino.
      fFuente = new FileInputStream(fichFuente);
      fDestino = new FileOutputStream(fichDestino);
      buffer = new byte[1024];

      // Copiar el fichero fuente en el destino
      while (true)
      {
        nbytes = fFuente.read(buffer);
        if (nbytes == -1) break; // se llegó al final del fichero
        fDestino.write(buffer, 0, nbytes);
      }
    }
    // Cerrar cualquier flujo que esté abierto
    finally
    {
      try
      {
        if (fFuente != null) fFuente.close();
        if (fDestino != null) fDestino.close();
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.toString());
      }
    }    
  }
  
  // File.getParent devuelve null si el fichero se especifica sin
  // un directorio. El método siguiente trata este caso.
  private static File directorioPadre(File f)
  {
    String nombreDir = f.getParent();
    if (nombreDir == null)
      // El método getProperty con el parámetro "user.dir" devuelve
      // el directorio actual de trabajo.
      return new File(System.getProperty("user.dir"));
    else
      // Devolver el directorio padre del fichero
      return new File(nombreDir);
  }
  
  public static void main(String[] args)
  {
    // main debe recibir dos parámetros: el fichero fuente y
    // el destino.
    if (args.length != 2)
      System.err.println("Sintaxis: java CopiarFichero " +
                         "<fichero fuente> <fichero destino>");
    else
    {
      try
      {
        copiar(args[0], args[1]); // realizar la copia
      }
      catch(IOException e)
      {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}

// Si se produce un error durante la copia, se lanzará
// el siguiente tipo de excepción:
class ECopiarFichero extends IOException
{
  public ECopiarFichero(String mensaje)
  {
    super(mensaje);
  }
}
