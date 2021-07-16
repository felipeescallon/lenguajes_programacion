import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DirectorioRaiz extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter ACliente = response.getWriter();
        File Fichero = new File("c:/");
        String[] Contenido = Fichero.list();
        ACliente.println("<html>");
        ACliente.println("<body>");
        ACliente.println("<table border=1 bgcolor='Salmon'>");
        for (int Fich=0;Fich<Contenido.length;Fich++) {
           ACliente.println("<tr><td>");
           ACliente.println("<font size=1>");
           ACliente.println(Contenido[Fich]);
           ACliente.println("</font>");
           ACliente.println("</td></tr>");
        }
        ACliente.println("</table>");
        ACliente.println("</body>");
        ACliente.println("</html>");
    }

}