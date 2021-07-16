import java.io.*;

public class EscribeFichero {

   public static void main(String[]  argumentos) {
       final int TAMANIO_BUFFER = 64;
       byte buffer[] = new byte[TAMANIO_BUFFER];
       int NumBytes; 

       try {
          FileOutputStream FicheroDestino = new FileOutputStream("Salida.txt");

          try {
             do {
                NumBytes = System.in.read(buffer);
                FicheroDestino.write(buffer,0,NumBytes);
             } while (buffer[0] != Character.LINE_SEPARATOR);
             FicheroDestino.close();
           } catch (IOException e){  
                System.out.println("Error escribiendo los datos o cerrando el fichero");   
           }


       } catch (FileNotFoundException e) {
          System.out.println("Fichero no encontrado");
       }

   }

}