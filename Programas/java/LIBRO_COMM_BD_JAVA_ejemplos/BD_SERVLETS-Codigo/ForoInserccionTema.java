import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ForoInserccionTema extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Identificador = request.getParameter("Identificador");
        String Descripcion = request.getParameter("Descripcion");

        ForoAccesoBD TemasForo= new ForoAccesoBD("Foro");
        TemasForo.Ejecuta("INSERT INTO Temas VALUES ('"+Identificador+"', '"+
                                                     Descripcion+"')");

        TemasForo.Ejecuta("CREATE TABLE "+Identificador+" (Opinion CHAR(80))");
   
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Tema: "+Identificador+" insertado <br>");
        TemasForo.Lista("SELECT * FROM Temas",out);
        out.println("</body>");
        out.println("</html>");

        TemasForo.Cierra();
    }
}