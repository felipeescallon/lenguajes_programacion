import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class GeneraTests extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {
    String Leccion = request.getParameter("Leccion");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Lección: "+Leccion+"</h1>");
    out.println("<form action=GeneraRespuestas method=get>"); 
    out.println("<br><input type=hidden name=Leccion value="+Leccion+">"); 
    GeneraListado(out,Leccion);
    out.println("<br><input type=submit>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }


   private void GeneraListado(PrintWriter out,String Leccion){
    String Pregunta,Respuesta1,Respuesta2,Respuesta3,Respuesta4;
    int NumPregunta;
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:Tests";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement();
     ResultSet Preguntas = SentenciaSQL.executeQuery("SELECT * FROM Test WHERE Leccion="+Leccion);

     while (Preguntas.next()) {
       NumPregunta = Preguntas.getInt("NumPregunta");
       Pregunta = Preguntas.getString("Pregunta");
       Respuesta1 = Preguntas.getString("Respuesta1");
       Respuesta2 = Preguntas.getString("Respuesta2");
       Respuesta3 = Preguntas.getString("Respuesta3");
       Respuesta4 = Preguntas.getString("Respuesta4");

       out.println("<h2>"+NumPregunta+". "+Pregunta+"</h2>");
       out.println("<input type=checkbox value=true name=R"+NumPregunta+"a>"+Respuesta1+"<br>");  
       out.println("<input type=checkbox value=true name=R"+NumPregunta+"b>"+Respuesta2+"<br>");
       out.println("<input type=checkbox value=true name=R"+NumPregunta+"c>"+Respuesta3+"<br>");
       out.println("<input type=checkbox value=true name=R"+NumPregunta+"d>"+Respuesta4+"<br>");
     }

     Preguntas.close();
     Conexion.close();
     SentenciaSQL.close();
    }
    catch (ClassNotFoundException e) {
      System.out.println("Clase no encontrada");
    }
    catch (SQLException e) {
      System.out.println(e);
    }    
  }


}