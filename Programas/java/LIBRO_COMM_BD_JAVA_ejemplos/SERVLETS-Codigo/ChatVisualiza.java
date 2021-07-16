import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ChatVisualiza extends HttpServlet {

    public void doGet(HttpServletRequest rq, HttpServletResponse rp)
    throws IOException, ServletException
    {
        String[] Frases = ChatConversacion.DameConversacion();

        rp.setContentType("text/html");
        PrintWriter out = rp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='refresh' content='5;url=http://e-ducacion.eui.upm.es:8080/examples/servlet/ChatVisualiza'>");
        out.println("</head>");  
        out.println("<body>");
        out.println("<table border=0>");
        for (int i=0;i<Frases.length;i++) {
          out.println("<tr><td>" + Frases[i] + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}