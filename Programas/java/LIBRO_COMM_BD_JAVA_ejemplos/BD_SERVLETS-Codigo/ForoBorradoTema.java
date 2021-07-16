import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ForoBorradoTema extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Tema = request.getParameter("Temas");

        ForoAccesoBD TemasForo= new ForoAccesoBD("Foro");
        TemasForo.Ejecuta("DELETE FROM Temas WHERE Identificador='"+Tema+"'");
        TemasForo.Ejecuta("DROP TABLE "+Tema);      
   
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Tema: "+Tema+" borrado <br>");
        TemasForo.Lista("SELECT * FROM Temas",out);
        out.println("</body>");
        out.println("</html>");

        TemasForo.Cierra();
    }
}