import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public abstract class TCPCliente {
     private  DataOutputStream Flujo;

  TCPCliente(String Host, int Puerto) {
             OutputStream FlujoDeSalida;

     try {
        Socket SocketCliente = new Socket(Host, Puerto);

        FlujoDeSalida = SocketCliente.getOutputStream();
        Flujo = new DataOutputStream(FlujoDeSalida);
        
        Comunicacion(Flujo);

        SocketCliente.close();
     } catch (UnknownHostException e) {
            System.out.println("Referencia a host no resuelta");
     } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
     } catch (SecurityException e) {
            System.out.println("Comunicacion no permitida por razones de seguridad");
     }
  }


  public abstract void Comunicacion (DataOutputStream Flujo);

}