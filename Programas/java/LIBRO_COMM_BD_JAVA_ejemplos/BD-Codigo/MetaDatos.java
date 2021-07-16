import java.sql.*;

public class MetaDatos {

  public static void main(String[] args){
    try{
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      String BaseDeDatos = "jdbc:odbc:NombreLogico";
      Connection Conexion = DriverManager.getConnection(BaseDeDatos);
      Statement SentenciaSQL = Conexion.createStatement();
      ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
      ResultSetMetaData DatosInternos = Personas.getMetaData();
      int NumeroDeColumnas = DatosInternos.getColumnCount();
      
      for (int Columna=1;Columna<=NumeroDeColumnas;Columna++) {
        String Nombre = DatosInternos.getColumnName(Columna);
        String Tipo = DatosInternos.getColumnTypeName(Columna);
        int Tamanio = DatosInternos.getColumnDisplaySize(Columna);    
        System.out.println(Nombre+", "+Tipo+", "+Tamanio);
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