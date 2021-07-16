import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ChatInserta extends HttpServlet {

  public void doGet(HttpServletRequest rq, HttpServletResponse rp)
  throws IOException, ServletException   {
    String Apodo = rq.getParameter("Apodo");
    String Mensaje = rq.getParameter("Mensaje");
    
    ChatConversacion.InsertaMensaje(Apodo+": "+Mensaje);

    rp.setContentType("text/html");
    PrintWriter out = rp.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<form method='GET' action='http://e-ducacion.eui.upm.es:8080/examples/servlet/ChatInserta'>");
    out.println("<input type='text' size=10 name='Apodo'>");
    out.println("<input type='text' size=70 name='Mensaje'>");
    out.println("<input type='submit' value='Enviar'>"); 
    out.println("</form>");
    out.println("</body>");

    out.println("</html>");

    }
}