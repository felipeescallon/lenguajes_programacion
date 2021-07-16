import java.util.Random;
import java.net.*;

public class TemperaturaYPresion extends Thread{

  public static void main(String[] args) {
    TemperaturaYPresion Sensor = new TemperaturaYPresion(args[0],args[1],args[2]);  
  }

  TemperaturaYPresion(String Sensor, String HostDestino, String PuertoDestino) {
    Float Temperatura, Presion;
    Random SecuenciaAleatoria = new Random();
    DatagramSocket SocketSensor;
    DatagramPacket MedidasSensor;
    byte[] Buffer = new byte[3];

    do {
      Temperatura = new Float(SecuenciaAleatoria.nextFloat()*60);
      Presion     = new Float(SecuenciaAleatoria.nextFloat()*20);
      Float SegundosEspera = new Float(SecuenciaAleatoria.nextFloat()*5000);
      System.out.println(Temperatura);
      System.out.println(Presion); 

      Buffer[0] = new Integer(Sensor).byteValue();
      Buffer[1] = Temperatura.byteValue();
      Buffer[2] = Presion.byteValue();
      try {
        MedidasSensor = new DatagramPacket(Buffer,Buffer.length,InetAddress.getByName(HostDestino),Integer.parseInt(PuertoDestino));
        SocketSensor = new DatagramSocket(); 
        SocketSensor.send(MedidasSensor);
        SocketSensor.close();
      } catch (Exception e) {}

      try { 
        this.sleep(SegundosEspera.longValue());
      } catch (InterruptedException e) {}

    } while (true); 
  }

}