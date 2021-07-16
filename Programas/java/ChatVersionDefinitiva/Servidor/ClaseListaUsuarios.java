import java.lang.*;
import java.io.*;
import java.sql.*;

class ClaseListaUsuarios
{
private String BasedeDatos, LoginBD;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas; 
private int EstadoActivo, contador=0;
private String[] ListaUsuarios;

public ClaseListaUsuarios()
{
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexion = DriverManager.getConnection(BasedeDatos);
	SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");

		while(Personas.next())								//hasta que devuelva el valor false que indica el fin de la tabla
		{
		EstadoActivo = Personas.getInt("Estado");					//Encontramos el numero de usuarios activos en el Chat
			
			if(EstadoActivo==1)
			{
			contador=contador+1;
			}
								
		}

		Personas.beforeFirst();								//Nos colocamos de nuevo en la posicion antes del primer registro 
		ListaUsuarios = new String[contador];						//Instannciamos una matriz de string delnumero de usuarios activos
		contador = 0;	
	 
		while(Personas.next())								//hasta que devuelva el valor false que indica el fin de la tabla
		{
		EstadoActivo = Personas.getInt("Estado");					
			
			if(EstadoActivo==1)
			{
			LoginBD = Personas.getString("Login");					//Guardamos en la matriz de String los logines de todos 
			ListaUsuarios[contador] = LoginBD;					//los usuarios activos en el Chat
			contador=contador+1;
			}
					
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

public String[] RetornarListaUsuarios()
	{
	return ListaUsuarios; 
	}

}