import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AgendaSelecciona extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {

    String Operacion = request.getParameter("Operacion");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");

    out.println("<form action='http://localhost:8080/examples/servlet/AgendaOpciones' method=get>");

    if (Operacion.equals("Crear")) {
     out.println("Nombre de la agenda: <input type=text size=15 name=Agenda> <br>");    
    } else {
     ForoAccesoBD UtilidadesBD = new ForoAccesoBD("Agenda");
     String[] Identificadores = UtilidadesBD.CreaLista("Agendas","Identificador"); 
     UtilidadesBD.Cierra();    
        
     out.println("<select name=Agenda>");
     for (int i=0;i<Identificadores.length;i++)
       out.println("<option>"+Identificadores[i]+"</option>"); 
     out.println("</select><br><br>");
    } 

    out.println("<input type=hidden name=Operacion value="+Operacion+"><br>");
    out.println("<input type=submit value='Enviar'> <br>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}