import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RecibeMedidas extends HttpServlet {

  public void doGet(HttpServletRequest rq, HttpServletResponse rp)
  throws IOException, ServletException {
    final int Puerto=6000;
    DatagramSocket SocketSensor;
    DatagramPacket MedidasSensor;
    byte[] Buffer = new byte[3];
    int Sensor,Temperatura,Presion;

    rp.setContentType("text/html");
    PrintWriter out = rp.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("El servicio ha comenzado");
    out.println("</body>");
    out.println("</html>");

    do {
      try {
        MedidasSensor = new DatagramPacket(Buffer,Buffer.length);
        SocketSensor = new DatagramSocket(Puerto);
        SocketSensor.receive(MedidasSensor);
        SocketSensor.close(); 
      } catch (Exception e) {}

      Sensor = new Byte(Buffer[0]).intValue();
      Temperatura = new Byte(Buffer[1]).intValue();
      Presion = new Byte(Buffer[2]).intValue();

      // System.out.println("Sensor "+Sensor+": "+Temperatura+", "+Presion);



        MedidasSensores.Inserta(Sensor,Temperatura,Presion);

    } while (true); 
  }

}