import java.io.*;

public class TCPFicheroServidor extends TCPServidor {

     TCPFicheroServidor(int Puerto) {
         super(Puerto);
     }

     public  void Comunicacion (DataInputStream Flujo) {
       final int TAMANIO_BUFFER = 256;
       byte buffer[] = new byte[TAMANIO_BUFFER];
       int NumBytes=0; 

       try {
          FileOutputStream FicheroDestino = new FileOutputStream("Salida.txt");

          try {
             do {
                NumBytes = Flujo.read(buffer);
                FicheroDestino.write(buffer,0,NumBytes);
             } while (NumBytes==TAMANIO_BUFFER);
             FicheroDestino.close();
           } catch (IOException e){  
                System.out.println("Error de entrada/salida");   
           }

       } catch (FileNotFoundException e) {
          System.out.println("Fichero no encontrado");
       }

     }

}







