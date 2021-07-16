import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TCPServidorDuplexHolaMundo {

  public static void main(String[] args)  {
     DataInputStream FlujoEntrada;
     DataOutputStream FlujoSalida;
     byte[] Mensaje=new byte[80];
     int BytesLeidos=0;

     try {
        ServerSocket SocketServidor = new ServerSocket(8000);
        Socket ComunicaConCliente = SocketServidor.accept();
        System.out.println("Comunicacion establecida");

        OutputStream FlujoDeSalida = ComunicaConCliente .getOutputStream();
        InputStream  FlujoDeEntrada = ComunicaConCliente .getInputStream();

        FlujoEntrada = new DataInputStream(FlujoDeEntrada);
        FlujoSalida = new DataOutputStream(FlujoDeSalida);

      do {
       try {
        BytesLeidos = FlujoEntrada.read(Mensaje);
        } catch (IOException e) {
            System.out.println("Error en la lectura de datos");
            System.exit(0);
       }
        System.out.print(new String(Mensaje,0,BytesLeidos));
        FlujoSalida.writeBytes("Hola humano\n");
      } while (true);
    

        //ComunicaConCliente.close();
        //SocketServidor.close();

     } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
            System.exit(0);
     } 

  }

 
}