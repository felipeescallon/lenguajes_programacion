import java.sql.*;

public class ObModificacion {

  ObModificacion(int PosicionBuscada, ObRegistro Datos) {
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

     Personas.absolute(PosicionBuscada);
     Personas.updateString("DNI",Datos.DameDNI());
     Personas.updateString("Nombre",Datos.DameNombre());
     Personas.updateString("Apellido",Datos.DameApellido());
     Personas.updateInt("Edad",Datos.DameEdad());
     Personas.updateRow();

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