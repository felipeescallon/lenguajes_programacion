import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Tabla extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<table border=1 bgcolor='Salmon'>");
        for (int Fila=1;Fila<=7;Fila++) {
           out.println("<tr><td>");
           out.println("<font size="+Fila+">Tamaño de letra: "+Fila+"</font>");
           out.println("</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}