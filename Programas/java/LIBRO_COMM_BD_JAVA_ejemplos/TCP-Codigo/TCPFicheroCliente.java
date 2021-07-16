import java.io.*;

public class TCPFicheroCliente extends TCPCliente {
     private FileInputStream FicheroOrigen;

     TCPFicheroCliente(String Host, int Puerto) {
         super(Host, Puerto);
     }

     public  void Comunicacion (DataOutputStream Flujo) {
       final int TAMANIO_BUFFER = 256;
       byte buffer[] = new byte[TAMANIO_BUFFER];
       int NumBytesLeidos = 0;

         try {
            FicheroOrigen = new FileInputStream("TCPCliente.java");
         } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
            System.exit(0);
         }
       
          try {
             do {
                NumBytesLeidos = FicheroOrigen.read(buffer);
                Flujo.write(buffer,0,NumBytesLeidos);
             } while (NumBytesLeidos == TAMANIO_BUFFER);
             FicheroOrigen.close();
           } catch (IOException e){  
                System.out.println(e.getMessage());   
           }
         } 
  }