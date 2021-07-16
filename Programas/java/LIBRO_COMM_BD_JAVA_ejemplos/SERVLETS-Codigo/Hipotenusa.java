import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Hipotenusa extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        String Cateto1 = request.getParameter("CatetoA");
        String Cateto2 = request.getParameter("CatetoB");
        double Cat1 = Double.parseDouble(Cateto1);
        double Cat2 = Double.parseDouble(Cateto2);
        double Hipotenusa = Math.sqrt(Cat1*Cat1+Cat2*Cat2);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Hipotenusa: " + Hipotenusa);
        out.println("</body>");
        out.println("</html>");
    }
}