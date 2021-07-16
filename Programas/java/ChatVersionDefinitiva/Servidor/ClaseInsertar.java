import java.lang.*;
import java.io.*;
import java.sql.*;

class ClaseInsertar
{
private String BasedeDatos;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas;
private String Login, LoginBD; 


public ClaseInsertar(ClaseCampo DatosUsuario, String CadenaIP ,int PuertoAsignar, int TipoRegistrarse)
	{
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexion = DriverManager.getConnection(BasedeDatos);
	SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
	
		if(TipoRegistrarse==2)									//SI LOS DATOS INTRODUCIDOS EN LA INTERFAZ DE CHAT SON VALIDOS
		{
		Login = DatosUsuario.RetornarLoginBD();
			while(Personas.next())
			{
			LoginBD = Personas.getString("Login");						//buscamos al correspondiente usuario en la base de datos 
													//y modificamos sus campos de Estado, cadenaIP y Puerto: 
				if((Login.compareTo(LoginBD)==0))					//activamos su estado de conversacion 
				{									//modificamos su actual cadena IP	
				Personas.updateString("Nombre", DatosUsuario.RetornarNombreBD());	//modificamos su puerto de conversacion
				Personas.updateString("Apellido", DatosUsuario.RetornarApellidoBD());
				Personas.updateString("Login", DatosUsuario.RetornarLoginBD());
				Personas.updateString("Password", DatosUsuario.RetornarPasswordBD());	
				Personas.updateInt("Grupo", DatosUsuario.RetornarGrupoBD());
				Personas.updateString("CadenaIP", CadenaIP);
				Personas.updateInt("Estado", 1);
				Personas.updateInt("Puerto", PuertoAsignar);				
				Personas.updateRow();
				}
			
			}
		}
	
	
		else											//SI LOS DATOS INTRODUCIDOS EN LA INTERFAZ DE DATOS SE PUEDEN REGISTRAR SIN NINGUN PROBLEMA
		{	
		Personas.moveToInsertRow();								//Movemos el cursor a la posición de insercion 
		Personas.updateString("Nombre", DatosUsuario.RetornarNombreBD());			//Recuperamos todos los datos que el usuario ingreso en la interfaz 
		Personas.updateString("Apellido", DatosUsuario.RetornarApellidoBD());			//los modificamos 
		Personas.updateString("Login", DatosUsuario.RetornarLoginBD());
		Personas.updateString("Password", DatosUsuario.RetornarPasswordBD());	
		Personas.updateInt("Grupo", DatosUsuario.RetornarGrupoBD());
		Personas.updateString("CadenaIP", CadenaIP);
		Personas.updateInt("Estado", 1);
		Personas.updateInt("Puerto", PuertoAsignar);
		Personas.insertRow();									//Agregamos físicamente el nuevo registro a nuestra base de datos
		}
		
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