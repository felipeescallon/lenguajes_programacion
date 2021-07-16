import java.io.*;

abstract public class CopiaFichero {

   CopiaFichero(String NombreOrigen, String NombreDestino) {
       final int TAMANIO_BUFFER = 64;
       byte buffer[] = new byte[TAMANIO_BUFFER];
       int NumBytes; 

       try {
          FileOutputStream FicheroDestino = new FileOutputStream(NombreDestino);
          FileInputStream FicheroOrigen = new FileInputStream(NombreOrigen);

          try {
             do {
                NumBytes = FicheroOrigen.read(buffer);
                buffer = Procesa(buffer,NumBytes);
                FicheroDestino.write(buffer,0,NumBytes);
             } while (NumBytes == TAMANIO_BUFFER);
             FicheroOrigen.close();
             FicheroDestino.close();
           } catch (IOException e){  
                System.out.println(e.toString());   
           }

       } catch (FileNotFoundException e) {
          System.out.println("Fichero no encontrado");
       }

   }

   abstract public byte[] Procesa(byte[] buffer, int NumBytes);

}