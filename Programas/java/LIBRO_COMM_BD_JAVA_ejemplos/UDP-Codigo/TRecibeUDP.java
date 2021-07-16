import java.net.*;

public class TRecibeUDP extends Object {

  DatagramSocket MiSocket;
  DatagramPacket Paquete;
  byte[] buffer;

  public String Recibe(int Puerto, int TamanioMaximoMensaje) { 
     try {       
       MiSocket = new DatagramSocket(Puerto);   
       buffer = new byte[TamanioMaximoMensaje];
       Paquete = new DatagramPacket(buffer, buffer.length);
       MiSocket.receive(Paquete);
       MiSocket.close();
     } catch (Exception e){
         System.out.println ("Error");
     } //try
     return new String(Paquete.getData()).substring(0,Paquete.getLength());
  } // Recibe
} // TRecibeUDP