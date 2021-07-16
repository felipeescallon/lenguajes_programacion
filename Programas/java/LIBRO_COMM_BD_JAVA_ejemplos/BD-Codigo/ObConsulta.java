import java.sql.*;

public class ObConsulta {

  private int Posicion = 0;
  private ObRegistro DatosPersona;

  ObConsulta(String DNIPedido){
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement();
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

     while (Personas.next()) {
       String DNI = Personas.getString("DNI");
       if (DNI.equalsIgnoreCase(DNIPedido)){
         Posicion = Personas.getRow();
         String Nombre = Personas.getString("Nombre");
         String Apellido = Personas.getString("Apellido");
         int Edad = Personas.getInt("Edad");
         DatosPersona = new ObRegistro(DNI,Nombre,Apellido,Edad);
         break;
       }

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

  public int PosicionEncontrada() {
     return Posicion;
  }

  public ObRegistro DameDatos() {
    return DatosPersona;
  }

}