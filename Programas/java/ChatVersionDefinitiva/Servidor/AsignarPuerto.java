import java.lang.*;
import java.io.*;
import java.sql.*;

class AsignarPuerto 
{
private String BasedeDatos;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas; 
private int EstadoActivo, PuertoAsignar=8004, Puerto, Cambio=0;
private String Login, LoginBD;
private boolean PrimeraBase;

public AsignarPuerto()
{
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexion = DriverManager.getConnection(BasedeDatos);
	SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
	Personas.absolute(1);	

	while(Cambio==0)							//Buscamos un puerto que no este ocupado por otro usuario	
	{
		while(Personas.next())						//hasta que devuelva el valor false que indica el fin de la tabla
		{
		Cambio=1;							//Suponemos un puerto desocupado
		EstadoActivo = Personas.getInt("Estado");			//Obtenemos los puertos de los usuarios activos en la reunion
		Puerto = Personas.getInt("Puerto");
		
			if(EstadoActivo==1 && PuertoAsignar==Puerto)		//Si nuestro supesto puerto conicide con el de otro usuario 
			{
			Cambio=0;
			break;
			}
		}

		if(Cambio==0)
		{
		PuertoAsignar = PuertoAsignar + 2;				//Al puerto supueste le sumamos dos para obtener otro supuesto puerto libre
		}								//para el cual repetimos de nuevo nuestro proseso. El proceso se repetira hasta encontrar un puerto libre 
	
	PrimeraBase = Personas.first();						//NOTA: Sumamos de a dos porque a cada usuario se le asignan dos puertos DIFERENTES
	}									//uno de Tx y otro de Rx
	
 	Personas.close();							//NOTA: Cuando un usuario libera sus puertos el proximo usuario en ingresar los tomara 
  	Conexion.close();							//evitando asi que la suma en busqueda de puertos se haga infinita
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

public int RetornarPuertoAsignar()
	{
	return PuertoAsignar; 
	}

}