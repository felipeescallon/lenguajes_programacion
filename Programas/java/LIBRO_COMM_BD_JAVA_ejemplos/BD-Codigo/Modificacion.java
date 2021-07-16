import java.sql.*;

public class Modificacion {

  public static void main(String[] args){
    String Nombre,Apellido,DNI;
    int Edad;
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

     while (Personas.next()) {
       Nombre = Personas.getString("Nombre");
       Apellido = Personas.getString("Apellido");
       if (Apellido.equalsIgnoreCase("Moreno")&&Nombre.equalsIgnoreCase("Ana")){
         // IMPORTANTE: por cada getXxxxx("Campo") hay que realizar
         //             un updateXxxxx("Campo")
         Personas.updateString("Nombre","Luis");
         Personas.updateString("Apellido","Reverte");
         Personas.updateInt("Edad",45);
         Personas.updateString("DNI","47645876F");
         Personas.updateRow();
         System.out.println("Registro modificado");
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

}