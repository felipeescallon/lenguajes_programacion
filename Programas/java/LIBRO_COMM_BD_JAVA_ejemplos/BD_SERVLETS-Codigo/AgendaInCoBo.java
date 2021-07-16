import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AgendaInCoBo extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Agenda = request.getParameter("Agenda");
        String Operacion = request.getParameter("Operacion");
        String Nombre = request.getParameter("Nombre");

        ForoAccesoBD UtilidadesBD= new ForoAccesoBD("Agenda");
        if (Operacion.equals("Insercion")) {
         String email = request.getParameter("email");
         String Telefono = request.getParameter("Telefono");
         UtilidadesBD.Ejecuta("INSERT INTO "+Agenda+" VALUES ('"+Nombre+"','"+email+"','"+Telefono+"')");
        }

        if (Operacion.equals("Borrado")) {
         UtilidadesBD.Ejecuta("DELETE FROM "+Agenda+" WHERE Nombre='"+Nombre+"'");
        }

        if (Operacion.equals("Consulta")) {
         PrintWriter out = response.getWriter();
         out.println("<html><body>");
         UtilidadesBD.Lista("SELECT * FROM "+Agenda+" WHERE Nombre='"+Nombre+"'",out);
         out.println("</body></html>");
        }

        UtilidadesBD.Cierra();       
   }
}