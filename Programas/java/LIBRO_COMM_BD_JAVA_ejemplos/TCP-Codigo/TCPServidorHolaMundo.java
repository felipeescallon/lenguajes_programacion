import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TCPServidorHolaMundo {

  public static void main(String[] args) {
     byte[] Mensaje=new byte[80]; 
     InputStream FlujoDeEntrada;
     DataInputStream Flujo;
     int BytesLeidos;
     try {
        ServerSocket SocketServidor = new ServerSocket(8000);
        Socket ComunicaConCliente = SocketServidor.accept();

        System.out.println("Comunicacion establecida");
        FlujoDeEntrada = ComunicaConCliente.getInputStream();
        Flujo = new DataInputStream(FlujoDeEntrada);

        do {
          BytesLeidos = Flujo.read(Mensaje);
          if (BytesLeidos>0) 
            System.out.print(new String(Mensaje));
        } while(BytesLeidos>0);

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

}