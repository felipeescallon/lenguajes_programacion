import java.lang.*;
import java.io.*;
import java.sql.*;

class ClaseModificacion
{
private String BasedeDatos;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas; 
private int posicionbuscada;

public ClaseModificacion (ClaseCampo Datos, String CadenaIP, int PuertoAsignar, int identificador)
	{
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexion = DriverManager.getConnection(BasedeDatos);
	SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
	posicionbuscada = identificador;
	
	Personas.absolute(posicionbuscada);					//Ubicamos el cursor en la posicion a actualizar
	Personas.updateString("Nombre", Datos.RetornarNombreBD());		//Recuperamos todos los datos que el usuario ingreso en la interfaz
	Personas.updateString("Apellido", Datos.RetornarApellidoBD());		//para que fueran modificados
	Personas.updateString("Login", Datos.RetornarLoginBD());
	Personas.updateString("Password", Datos.RetornarPasswordBD());
	Personas.updateInt("Grupo", Datos.RetornarGrupoBD());
	Personas.updateString("CadenaIP", CadenaIP);
	Personas.updateInt("Estado", 1);
	Personas.updateInt("Puerto", PuertoAsignar);
	Personas.updateRow();							//Realizar la modificacion fisica en la base de datos

	Personas.close();
	Conexion.close();
	SentenciaSQL.close();
	}

	catch(ClassNotFoundException e)
	{
	System.out.println("Clase no encontrada");
	}

	catch(SQLException e)
	{
	System.out.println(e);
	}
	}

}
		
	
	