import java.sql.*;
import java.io.*;
import java.awt.TextArea;

public class ForoAccesoBD {

  private Statement SentenciaSQL;
  private Connection Conexion;
 
  ForoAccesoBD(String NombreLogico){
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:"+NombreLogico;
     Conexion = DriverManager.getConnection(BaseDeDatos);
     SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    } catch (ClassNotFoundException e) {
      System.out.println("Clase no encontrada");
    } catch (SQLException e) {
      System.out.println(e);
    }
   } // Constructor 


   synchronized public void Ejecuta(String Sql) {
    try{
     int RegistrosInvolucrados = SentenciaSQL.executeUpdate(Sql);
    } catch (SQLException e) {
      System.out.println(e);
    }
   }

   synchronized public void Lista(String Sql, PrintWriter out) {
    try{
      ResultSet Personas = SentenciaSQL.executeQuery(Sql);
      ResultSetMetaData DatosInternos = Personas.getMetaData();
      int NumeroDeColumnas = DatosInternos.getColumnCount();
  
      out.println("<table border=1>");
      while (Personas.next()) {
        out.println("<tr>");    
        for (int Columna=1;Columna<=NumeroDeColumnas;Columna++) {
          String Nombre = DatosInternos.getColumnName(Columna);
          String Contenido = Personas.getString(Nombre);
          out.println("<td>"+Contenido+"</td>");
        }
        out.println("</tr>");
      }
      out.println("</table>");
      Personas.close();
    } catch (SQLException e) {
      System.out.println(e);
    }
   } 


   synchronized public String[] CreaLista(String Tabla,String Campo) {
    String[] Lista=null;
    try {
     String Sql = "SELECT "+Campo+" FROM "+Tabla;
     ResultSet Personas = SentenciaSQL.executeQuery(Sql);
     Personas.last();
     int Registros=Personas.getRow();
     Lista = new String[Registros];
     Personas.beforeFirst();

     while (Personas.next()) 
       if (!Personas.isAfterLast())
        Lista[Personas.getRow()-1]=Personas.getString(Campo); 
     
    } catch (SQLException e) {
      System.out.println(e);
    }
     return Lista;   
   }


   synchronized public void Cierra() {
    try{
     SentenciaSQL.close();
     Conexion.close(); 
    } catch (SQLException e) {
      System.out.println(e);
    }
   }

}