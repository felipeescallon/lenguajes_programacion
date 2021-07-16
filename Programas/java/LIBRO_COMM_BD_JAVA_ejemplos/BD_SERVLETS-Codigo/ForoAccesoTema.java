import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ForoAccesoTema extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Tema = request.getParameter("Temas");

        ForoAccesoBD TemasForo= new ForoAccesoBD("Foro");    
   
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Tema: "+Tema+"<br>");
        TemasForo.Lista("SELECT * FROM "+Tema,out);
        out.println("</body>");
        out.println("</html>");

        TemasForo.Cierra();
    }
}