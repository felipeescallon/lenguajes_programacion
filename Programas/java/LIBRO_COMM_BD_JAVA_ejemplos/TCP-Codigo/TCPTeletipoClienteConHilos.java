import java.io.*;
import java.awt.TextField;
import java.awt.event.*;
import java.net.*;

public class TCPTeletipoClienteConHilos extends Thread{

     TextField AreaEntrada;
     OutputStream FlujoDeSalida;
     DataOutputStream Flujo;
     Socket SocketCliente;
     String Host;
     int Puerto;  


     TCPTeletipoClienteConHilos(String Host, int Puerto, TextField AreaEntrada) {
       this.AreaEntrada = AreaEntrada;
       this.Host = Host;
       this.Puerto = Puerto;
     }


  public void run() { 
       try {
         SocketCliente = new Socket(Host, Puerto);
         FlujoDeSalida = SocketCliente.getOutputStream();
         Flujo = new DataOutputStream(FlujoDeSalida);

       } catch (UnknownHostException e) {
            System.out.println("Referencia a host no resuelta");
       } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
       }
       AreaEntrada.addActionListener(new TextoModificado());      
  }


     private class TextoModificado implements ActionListener {
       public void actionPerformed(ActionEvent e) {
         try {
           Flujo.write(AreaEntrada.getText().getBytes());
         } catch (IOException IOe) {
           System.out.println("Error al enviar los datos");
         }
         if (AreaEntrada.getText().equals("Fin"))
           System.exit(0);
         AreaEntrada.setText("");
       }
     }

  }