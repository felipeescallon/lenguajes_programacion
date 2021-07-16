import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Agenda extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws IOException, ServletException    {

     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     out.println("<html>");
     out.println("<body>");

     out.println("<form action='http://localhost:8080/examples/servlet/AgendaSelecciona' method=get>");
     out.println("<input type=radio name=Operacion value=Crear> Crear agenda <br>");
     out.println("<input type=radio name=Operacion value=Borrar> Borrar agenda <br>");

     out.println("<input type=radio name=Operacion value=Consulta> Consultar contacto <br>");
     out.println("<input type=radio name=Operacion value=Insercion> Insertar contacto <br>");
     out.println("<input type=radio name=Operacion value=Borrado> Borrar contacto<br>");
     out.println("<input type=radio name=Operacion value=Listar> Listar toda la agenda<br><br>");

     out.println("<input type=submit value='Realizar la operación'> <br>");
     out.println("</form>");

     out.println("</body>");
     out.println("</html>");
  }
}