import java.net.*;

public class TEnviaUDP extends Object {
 
  public void Envia(String Mensaje,int TamanioMensaje,String HostDestino,int Puerto) {
    try {
        DatagramSocket MiSocket = new DatagramSocket();     
        byte[] buffer = new byte[TamanioMensaje];
        DatagramPacket Paquete; 
        buffer=Mensaje.getBytes();
        Paquete = new DatagramPacket(buffer, Mensaje.length(),InetAddress.getByName(HostDestino),Puerto);
        MiSocket.send(Paquete);
        MiSocket.close();  
    } catch (Exception exc){
          System.out.println ("Error"); 
    } //try    
  } // Envia    

} // TEnviaUDP

