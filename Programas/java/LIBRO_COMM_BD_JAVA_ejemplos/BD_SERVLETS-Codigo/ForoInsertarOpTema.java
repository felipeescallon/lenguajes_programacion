import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ForoInsertarOpTema extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Tema = request.getParameter("Temas");
        String Opinion = request.getParameter("Opinion");
        
        ForoAccesoBD TemaForo= new ForoAccesoBD("Foro");
        TemaForo.Ejecuta("INSERT INTO "+Tema+" VALUES ('"+Opinion+"')");
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Opinión: "+Opinion+" añadida <br>");
        TemaForo.Lista("SELECT * FROM "+Tema,out);
        out.println("</body>");
        out.println("</html>");

        TemaForo.Cierra();
    }
}