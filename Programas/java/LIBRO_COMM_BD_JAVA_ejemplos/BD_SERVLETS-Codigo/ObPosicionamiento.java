import java.sql.*;

public class ObPosicionamiento {

  private ObRegistro DatosPersona;
  private int Posicion;

  ObPosicionamiento(String Accion, int Posicion) {
    int IDAccion,Edad;
    String DNI,Nombre,Apellido;
    boolean PosicionCorrecta=false;

    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     String BaseDeDatos = "jdbc:odbc:NombreLogico";
     Connection Conexion = DriverManager.getConnection(BaseDeDatos);
     Statement SentenciaSQL = Conexion.createStatement(
       ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     ResultSet Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

     Personas.absolute(Posicion);

     IDAccion = Integer.parseInt(Accion);
     switch (IDAccion) {
      case 0:  //
         PosicionCorrecta = Personas.last(); 
         break;
      case 1:
         PosicionCorrecta = Personas.first();
         break;
      case 2:
         PosicionCorrecta = Personas.relative(-5);
         break;
      case 3:
         PosicionCorrecta = Personas.previous();
         break;
      case 4:
         PosicionCorrecta = Personas.next(); 
         break;
      case 5:
         PosicionCorrecta = Personas.relative(+5);
         break;
     }
     if (PosicionCorrecta) {
       this.Posicion = Personas.getRow();
       DNI = Personas.getString("DNI");
       Nombre = Personas.getString("Nombre");
       Apellido = Personas.getString("Apellido");
       Edad = Personas.getInt("Edad");
     } else {
       this.Posicion = Posicion;
       DNI = "----";
       Nombre = "----";
       Apellido = "----";
       Edad = 0;
     }
     DatosPersona = new ObRegistro(DNI,Nombre,Apellido,Edad);

     Personas.close();
     Conexion.close();
     SentenciaSQL.close();

     System.out.println(String.valueOf(Posicion)+":"+String.valueOf(this.Posicion));
    }
    catch (ClassNotFoundException e) {
      System.out.println("Clase no encontrada");
    }
    catch (SQLException e) {
      System.out.println(e);
    }    
  }

  public int Posicion() {
     return Posicion;
  }

  public ObRegistro DameDatos() {
    return DatosPersona;
  }

}