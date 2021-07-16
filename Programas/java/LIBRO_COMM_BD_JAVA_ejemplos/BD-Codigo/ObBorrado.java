import java.sql.*;

public class ObBorrado {

  ObBorrado(int PosicionBuscada){
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

     Personas.absolute(PosicionBuscada);
     Personas.deleteRow();

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