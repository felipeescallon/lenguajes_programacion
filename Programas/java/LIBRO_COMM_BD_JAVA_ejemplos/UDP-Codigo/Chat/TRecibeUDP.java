import java.net.*;

public class TRecibeUDP extends Object {

  DatagramSocket MiSocket;
  DatagramPacket Paquete;
  byte[] buffer;
  int PuertoRemoto=0;
  String IPRemota="";

  public synchronized String Recibe(int Puerto, int TamanioMaximoMensaje,int TimeOut) { 
     try {       
       MiSocket = new DatagramSocket(Puerto);  
       MiSocket.setSoTimeout(TimeOut); 
       buffer = new byte[TamanioMaximoMensaje];
       Paquete = new DatagramPacket(buffer, buffer.length);
       MiSocket.receive(Paquete);
       PuertoRemoto = MiSocket.getLocalPort();
       IPRemota = Paquete.getAddress().getHostAddress();
       MiSocket.close();
     } catch (Exception e){
         System.out.println ("Error");
     } //try
     return new String(Paquete.getData()).substring(0,Paquete.getLength());
  } // Recibe

  public synchronized int DamePuertoRemoto(){
    return  PuertoRemoto;
  }

  public synchronized String DameIPRemota(){
    return IPRemota;
  }

} // TRecibeUDP