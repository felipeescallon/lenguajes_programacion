import java.io.*;

public class TCPTeletipoCliente extends TCPCliente {

     TCPTeletipoCliente(String Host, int Puerto) {
         super(Host, Puerto);
     }

     public  void Comunicacion (DataOutputStream Flujo) {
       byte[] Valor = new byte[256];
       int NumBytesLeidos = 0;
       InputStream Teclado = System.in;
       String Mensaje;
       do {
         try {
           NumBytesLeidos = Teclado.read(Valor);
         }
         catch (IOException e){
           System.out.println("Error en la entrada de datos por consola");
         }
         try {
           Flujo.write(Valor,0,NumBytesLeidos);
         }catch (IOException e)  {
            System.out.println("Error en la escritura de datos a la linea");
         }
         Mensaje = new String(Valor);
         Mensaje = Mensaje.substring(0,NumBytesLeidos-2); 
    
       } while (!Mensaje.equals("Fin"));
     } 


  }