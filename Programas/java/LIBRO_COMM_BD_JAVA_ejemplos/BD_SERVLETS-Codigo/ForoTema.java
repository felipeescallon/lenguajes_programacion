import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ForoTema extends HttpServlet {

    synchronized public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String Operacion = request.getParameter("Operacion");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");

        if (Operacion.equals("Insercion")) {
         out.println("<h2> Insercción de un tema </h2>"); 
         out.println("<form action='http://localhost:8080/examples/servlet/ForoInserccionTema' method=get>");
         out.println("Identificador: <input type=text name=Identificador> <br>");
         out.println("Descripción: <input type=text name=Descripcion> <br><br>");
         out.println("<input type=submit value='Enviar tema'> <br>");
         out.println("</form>");
        } 

        if (Operacion.equals("Borrado")||Operacion.equals("Acceso")||
            Operacion.equals("InsertarOp")){ 
         out.println("<form action='http://localhost:8080/examples/servlet/Foro"+Operacion+"Tema' method=get>");
         ForoAccesoBD UtilidadesBD = new ForoAccesoBD("Foro");
         String[] Identificadores = UtilidadesBD.CreaLista("Temas","Identificador"); 
         UtilidadesBD.Cierra();    
        
         out.println("<select name=Temas>");
         for (int i=0;i<Identificadores.length;i++)
           out.println("<option>"+Identificadores[i]+"</option>"); 
         out.println("</select><br><br>");
         if (Operacion.equals("InsertarOp")) {
           out.println("<textarea rows=3 col=20 name=Opinion>Escribe tu opinión</textarea><br>");
         }
         out.println("<input type=submit value='Enviar'> <br>");
         out.println("</form>"); 
        }

        if (Operacion.equals("Consulta")) {
         ForoAccesoBD UtilidadesBD = new ForoAccesoBD("Foro");
         UtilidadesBD.Lista("SELECT * FROM Temas",out);
         UtilidadesBD.Cierra(); 
        }

        out.println("</body>");
        out.println("</html>");
    }
}