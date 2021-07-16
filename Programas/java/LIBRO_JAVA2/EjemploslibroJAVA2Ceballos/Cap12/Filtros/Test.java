import java.io.*;
public class Test
{
  public static void main(String[] args)
  {
    String nombreFichero = "datos.dat";
    String nombre = null, dirección = null;
    long teléfono = 0;
    
    // Escribir daros
    try
    {
      FileOutputStream fos = new FileOutputStream(nombreFichero);
      DataOutputStream dos = new DataOutputStream(fos);
    
      // Almacenar el nombre la dirección y el teléfono en el fichero
      dos.writeUTF("un nombre");
      dos.writeUTF("una dirección");
      dos.writeLong(942334455);
      
      dos.close(); fos.close();
    
      // Leer datos
      FileInputStream fis = new FileInputStream(nombreFichero);
      DataInputStream dis = new DataInputStream(fis);
    
      // Leer el nombre la dirección y el teléfono del fichero
      nombre = dis.readUTF();
      dirección = dis.readUTF();
      teléfono = dis.readLong();

      System.out.println(nombre);
      System.out.println(dirección);
      System.out.println(teléfono);
  
      dis.close(); fis.close();
    }
    catch (IOException e)
    {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
