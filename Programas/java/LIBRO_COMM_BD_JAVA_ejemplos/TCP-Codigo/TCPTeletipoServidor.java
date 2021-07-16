import java.io.*;

public class TCPTeletipoServidor extends TCPServidor {

     TCPTeletipoServidor(int Puerto) {
         super(Puerto);
     }

     public  void Comunicacion (DataInputStream Flujo) {
         byte[] buffer = new byte[256];
         int BytesLeidos=0;
         String Mensaje="";
         do {
           try {
              Mensaje="";
              do {
                BytesLeidos = Flujo.read(buffer);
                if (BytesLeidos>0) {
                  Mensaje = Mensaje + new String(buffer,0,BytesLeidos);
                  System.out.print(new String(buffer,0,BytesLeidos));
                }
              } while(BytesLeidos>0);           

           } catch (IOException e)  {
              System.out.println("Error en la lectura de datos por linea");
           }
      
         } while (!Mensaje.equals(""));
     } 

}