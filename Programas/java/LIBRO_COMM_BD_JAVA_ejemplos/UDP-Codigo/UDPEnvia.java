import java.net.*;

public class UDPEnvia {

  public static void main(String args[]) {
    try {
        DatagramSocket MiSocket = new DatagramSocket();
        byte[] buffer = new byte[15];
        String Mensaje = "Hola Mundo";
        buffer=Mensaje.getBytes();
        DatagramPacket Paquete = new DatagramPacket(buffer, Mensaje.length(),InetAddress.getByName("localhost"),14000);
        MiSocket.send(Paquete);
        MiSocket.close();  
    } catch (Exception exc){
        System.out.println ("Error"); 
    } //try    
  }     

} // UDPEnvia

