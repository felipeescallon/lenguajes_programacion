import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public abstract class TCPServidor {
  private DataInputStream Flujo;

  TCPServidor(int Puerto){
     byte[] Mensaje=new byte[256]; 
     InputStream FlujoDeEntrada;
     try {
        ServerSocket SocketServidor = new ServerSocket(Puerto);
        Socket ComunicaConCliente = SocketServidor.accept();

        System.out.println("Comunicacion establecida");
        FlujoDeEntrada = ComunicaConCliente.getInputStream();
        Flujo = new DataInputStream(FlujoDeEntrada);

        Comunicacion (Flujo);

        ComunicaConCliente.close();
        SocketServidor.close();
     } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
            System.exit(0);
     } catch (SecurityException e) {
            System.out.println("Comunicacion no permitida por razones de seguridad");
           System.exit(0);
     }
  }


   public abstract void Comunicacion (DataInputStream Flujo);

 }