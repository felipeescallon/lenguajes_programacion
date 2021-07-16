import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class GeneraRespuestas extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {
    String Leccion = request.getParameter("Leccion");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Lección: "+Leccion+"</h1>");
 
    GeneraRespuestas(request,out,Leccion);

    out.println("</body>");
    out.println("</html>");
  }


   private void GeneraRespuestas(HttpServletRequest request,PrintWriter out,String Leccion){
    boolean SolucionA,SolucionB,SolucionC,SolucionD,RaUsuario,RbUsuario,RcUsuario,RdUsuario,Acertado;
    int NumPregunta;
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:Tests";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement();
     ResultSet Preguntas = SentenciaSQL.executeQuery("SELECT * FROM Test WHERE Leccion="+Leccion);

     while (Preguntas.next()) {
       NumPregunta = Preguntas.getInt("NumPregunta");
       SolucionA = Preguntas.getBoolean("Solucion1");
       SolucionB = Preguntas.getBoolean("Solucion2");
       SolucionC = Preguntas.getBoolean("Solucion3");
       SolucionD = Preguntas.getBoolean("Solucion4");

       RaUsuario = new Boolean(request.getParameter("R"+NumPregunta+"a")).booleanValue();
       RbUsuario = new Boolean(request.getParameter("R"+NumPregunta+"b")).booleanValue();
       RcUsuario = new Boolean(request.getParameter("R"+NumPregunta+"c")).booleanValue();
       RdUsuario = new Boolean(request.getParameter("R"+NumPregunta+"d")).booleanValue();

       Acertado = (SolucionA==RaUsuario && SolucionB==RbUsuario &&
                   SolucionC==RcUsuario && SolucionD==RdUsuario);

       out.println("<h2>"+NumPregunta+". "+Acertado+"</h2>");
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