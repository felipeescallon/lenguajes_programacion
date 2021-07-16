import java.net.*;
import java.io.*;

public class TCPClienteDuplexHolaMundo {

  public static void main(String[] args)  {
     DataInputStream FlujoEntrada;
     DataOutputStream FlujoSalida;
     byte[] Mensaje=new byte[80];
     int BytesLeidos=0;

     try {
        Socket SocketCliente = new Socket("localhost", 8000);

        OutputStream FlujoDeSalida = SocketCliente.getOutputStream();
        InputStream  FlujoDeEntrada = SocketCliente.getInputStream();

        FlujoEntrada = new DataInputStream(FlujoDeEntrada);
        FlujoSalida = new DataOutputStream(FlujoDeSalida);

      do {
       try {
        FlujoSalida.writeBytes("Hola terricola\n");

        BytesLeidos = FlujoEntrada.read(Mensaje);
        } catch (IOException e) {
            System.out.println("Error en la lectura de datos");
            System.exit(0);
       }
        System.out.print(new String(Mensaje,0,BytesLeidos));
      } while (true);  

        // SocketCliente.close();

     } catch (UnknownHostException e) {
            System.out.println("Referencia a host no resuelta");
     } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
   }
  }


 }