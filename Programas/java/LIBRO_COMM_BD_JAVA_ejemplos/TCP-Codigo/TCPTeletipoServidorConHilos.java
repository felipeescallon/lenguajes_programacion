import java.io.*;
import java.awt.TextField;

public class TCPTeletipoServidorConHilos extends TCPServidorConHilos {
     TextField AreaSalida;

     TCPTeletipoServidorConHilos(int Puerto, TextField AreaSalida) {
       super(Puerto);
       this.AreaSalida = AreaSalida;
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
                
              } while(!FinMensaje(Mensaje)); 
          
           } catch (IOException e)  {
              System.out.println("Error en la lectura de datos por linea");
           }
           AreaSalida.setText(Mensaje);
      
         } while 	(!Mensaje.equals("Fin*"));

     } 

     private boolean FinMensaje(String Mensaje) {
         Character Asterisco = new Character('*');
         for (int i=0;i!=Mensaje.length()-1;i++)
         if (new Character(Mensaje.charAt(i)).compareTo(Asterisco)==0)
           return true;
         return false;
     }

}