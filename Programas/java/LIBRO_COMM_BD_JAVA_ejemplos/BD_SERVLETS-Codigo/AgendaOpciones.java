import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AgendaOpciones extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Agenda = request.getParameter("Agenda");
        String Operacion = request.getParameter("Operacion");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");

        if (Operacion.equals("Crear")) {
          ForoAccesoBD UtilidadesBD= new ForoAccesoBD("Agenda");
          UtilidadesBD.Ejecuta("INSERT INTO Agendas VALUES ('"+Agenda+"')");
          UtilidadesBD.Ejecuta("CREATE TABLE "+Agenda+" (Nombre CHAR(30),email CHAR(30),Telefono CHAR(10))");
        }
 
        if (Operacion.equals("Borrar")) {
          ForoAccesoBD UtilidadesBD= new ForoAccesoBD("Agenda");
          UtilidadesBD.Ejecuta("DELETE FROM Agendas WHERE Identificador='"+Agenda+"'");
          UtilidadesBD.Ejecuta("DROP TABLE "+Agenda); 
        }

        if (Operacion.equals("Consulta")||Operacion.equals("Borrado")||Operacion.equals("Insercion")) {
         out.println("<form action='http://localhost:8080/examples/servlet/AgendaInCoBo' method=get>");
         out.println("Nombre: <input type=text name=Nombre size=40><br>");
         if (Operacion.equals("Insercion")) {
          out.println("email: <input type=text name=email size=20><br>");
          out.println("Teléfono: <input type=text name=Telefono size=10><br><br>");
         }
         out.println("<input type=hidden name=Agenda value="+Agenda+">");
         out.println("<input type=hidden name=Operacion value="+Operacion+">");
         out.println("<input type=submit> <br>");
         out.println("</form>");
        }

        if (Operacion.equals("Listar")) {
         ForoAccesoBD UtilidadesBD = new ForoAccesoBD("Agenda");
         UtilidadesBD.Lista("SELECT * FROM "+Agenda,out);
         UtilidadesBD.Cierra(); 
        }

        out.println("</body>");
        out.println("</html>");
    }
}