import java.sql.*;
import java.awt.TextArea;

public class ObListado {

  ObListado(TextArea AreaDeListado){
    String DNI, Nombre, Apellido, Linea;
    int Edad, Posicion;
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement();
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
     AreaDeListado.setText("");

     while (Personas.next()) {
       DNI = Personas.getString("DNI");
       Nombre = Personas.getString("Nombre");
       Apellido = Personas.getString("Apellido");
       Edad = Personas.getInt("Edad");
       Posicion = Personas.getRow();
       Linea = DNI+", "+Nombre+" "+Apellido+", "+String.valueOf(Edad);
       AreaDeListado.append(String.valueOf(Posicion)+": "+Linea+"\n");
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