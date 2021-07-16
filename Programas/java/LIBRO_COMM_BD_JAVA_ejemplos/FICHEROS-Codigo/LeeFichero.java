import java.io.*;

public class LeeFichero {

   public static void main(String[]  argumentos) {
       final int TAMANIO_BUFFER = 64;
       byte buffer[] = new byte[TAMANIO_BUFFER];
       int NumBytes; 

       try {
          FileInputStream FicheroOrigen = new FileInputStream("LeeFichero.java");

          try {
             do {
                NumBytes = FicheroOrigen.read(buffer);
                System.out.print(new String(buffer,0,NumBytes));
             } while (NumBytes == TAMANIO_BUFFER);
             FicheroOrigen.close();
           } catch (IOException e){  
                System.out.println("Error leyendo los datos o cerrando el fichero");   
           }


       } catch (FileNotFoundException e) {
          System.out.println("Fichero no encontrado");
       }

   }

}