import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PanelDeMedidas extends HttpServlet {

    public void doGet(HttpServletRequest rq, HttpServletResponse rp)
    throws IOException, ServletException    {
        
        int[][] Medidas = MedidasSensores.DameMedidas();       

        rp.setContentType("text/html");
        PrintWriter out = rp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='refresh' content='2;url=http://localhost:8080/examples/servlet/PanelDeMedidas'>");
        out.println("</head>");  
        out.println("<body>");
        out.println("<table border=1 bgcolor=salmon>");
        for (int i=0;i<5;i++) {
          out.println("<tr><td> Sensor " + i + "</td>"); 
          out.println("<td>" + Medidas[i][0] + "</td>");
          out.println("<td>" + Medidas[i][1] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}