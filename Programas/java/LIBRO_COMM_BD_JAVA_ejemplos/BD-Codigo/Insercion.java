import java.sql.*;

public class Insercion {

  public static void main(String[] args){
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

     Personas.moveToInsertRow();
     Personas.updateString("DNI","50839979M");
     Personas.updateString(2,"Pedro");
     Personas.updateString("Apellido","Cela"); 
     Personas.updateInt("Edad",78);
     Personas.insertRow();
     // Personas.moveToCurrentRow();

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