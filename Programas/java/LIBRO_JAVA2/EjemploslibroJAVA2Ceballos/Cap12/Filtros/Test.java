import java.io.*;
public class Test
{
  public static void main(String[] args)
  {
    String nombreFichero = "datos.dat";
    String nombre = null, direcci�n = null;
    long tel�fono = 0;
    
    // Escribir daros
    try
    {
      FileOutputStream fos = new FileOutputStream(nombreFichero);
      DataOutputStream dos = new DataOutputStream(fos);
    
      // Almacenar el nombre la direcci�n y el tel�fono en el fichero
      dos.writeUTF("un nombre");
      dos.writeUTF("una direcci�n");
      dos.writeLong(942334455);
      
      dos.close(); fos.close();
    
      // Leer datos
      FileInputStream fis = new FileInputStream(nombreFichero);
      DataInputStream dis = new DataInputStream(fis);
    
      // Leer el nombre la direcci�n y el tel�fono del fichero
      nombre = dis.readUTF();
      direcci�n = dis.readUTF();
      tel�fono = dis.readLong();

      System.out.println(nombre);
      System.out.println(direcci�n);
      System.out.println(tel�fono);
  
      dis.close(); fis.close();
    }
    catch (IOException e)
    {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
