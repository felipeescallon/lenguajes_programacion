import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Parametros extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        String NombreRecibido = request.getParameter("Nombre");
        String EdadRecibida = request.getParameter("Edad");
        out.println("<h1> Hola " + NombreRecibido + "</h1>");
        out.println("<h1> Tienes " + EdadRecibida +" años </h1>");
        out.println("</body>");
        out.println("</html>");
    }
}