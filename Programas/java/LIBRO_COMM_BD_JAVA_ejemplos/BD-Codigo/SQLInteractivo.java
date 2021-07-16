import java.sql.*;

public class SQLInteractivo {

  public static void main(String[] args){
    String DNI,Nombre,Apellido; int Edad;
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
 
     if (args[0].startsWith("UPDATE")||args[0].startsWith("DELETE")||args[0].startsWith("INSERT")) {
       int ElementosVariados = SentenciaSQL.executeUpdate(args[0]);
       System.out.println(ElementosVariados+" elementos han sido actualizados, insertados o borrados");
     } else {
       ResultSet Personas = SentenciaSQL.executeQuery(args[0]);
       while (Personas.next()) {
         DNI = Personas.getString("DNI");
         Nombre = Personas.getString("Nombre");
         Apellido = Personas.getString("Apellido");
         Edad = Personas.getInt("Edad");
         System.out.println(Nombre+" "+Apellido+", "+Edad+", "+DNI);
       }
       Personas.close();
     }
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