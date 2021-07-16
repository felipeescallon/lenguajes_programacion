import java.lang.*;
import java.io.*;
import java.sql.*;

class ModificacionEstadoPuerto
{
private String BasedeDatos;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas; 
private String Login, LoginBD;

public ModificacionEstadoPuerto()
{
}

public synchronized void ModificarEstadoPuerto(String CampoLogin)
{
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexion = DriverManager.getConnection(BasedeDatos);
	SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
	
	Login = CampoLogin;						//Obtenemos el login del usuario que se le va ha cerrar su sesion									
		
		while(Personas.next())					//hasta que devuelva el valor false que indica el fin de la tabla
		{
		LoginBD = Personas.getString("Login");			//Camparamos el login del usuario al que se le cerrara la sesion  
									//con los logines almacenados en la base de datos
			if(Login.compareTo(LoginBD)==0)			//si los logines son iguales
			{
			Personas.updateString("Login", LoginBD);	//Inactivamos su estado y 
			Personas.updateInt("Estado", 0);		//liberamos el puerto que estaba utilizando
			Personas.updateInt("Puerto", 0);
			Personas.updateRow();	
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

}