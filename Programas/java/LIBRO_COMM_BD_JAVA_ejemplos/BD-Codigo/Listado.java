import java.sql.*;

public class Listado {

  public static void main(String[] args){
    String Nombre,Apellido,DNI;
    int Edad;
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement();
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
     while (Personas.next()) {
       DNI = Personas.getString("DNI");
       Nombre = Personas.getString("Nombre");
       Apellido = Personas.getString("Apellido");
       Edad = Personas.getInt("Edad");
       System.out.println(Nombre+" "+Apellido+", "+Edad+", "+DNI);
     }
     Personas.close();
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