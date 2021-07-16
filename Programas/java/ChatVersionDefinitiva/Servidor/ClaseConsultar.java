import java.lang.*;
import java.io.*;
import java.sql.*;

class ClaseConsultar
{
private String BasedeDatos;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas; 
private ClaseCampo DatosUsuario;
private int Grupo, GrupoBD, EstadoBD, Registro, posicion, identificador; 
private String Nombre, NombreBD, Apellido, ApellidoBD, Login, LoginBD, Password, PasswordBD, CadenaIP, CadenaIPBD;

public ClaseConsultar(String Consulta, Cliente DatosPersonales)
	{
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexion = DriverManager.getConnection(BasedeDatos);
	SentenciaSQL = Conexion.createStatement();
	Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
	
		if(Consulta.compareTo("Principales")==0)					//DATOS INTRODUCIDOS EN LA INTERFAZ DE CHAT
		{	
		Registro = 3;									//(Registro =3) Indica que los datos introducidos en la InterfazdeChat  
		Login = DatosPersonales.RetornarLogin();					//son inexsistentes se coloca este valor por defecto
		Password = DatosPersonales.RetornarPassword();
		
			while(Personas.next())							//hasta que devuelva el valor false que indica el fin de la tabla
			{
			LoginBD = Personas.getString("Login");
			PasswordBD = Personas.getString("Password");
		
				if((Login.compareTo(LoginBD)==0) && (Password.compareTo(PasswordBD)==0)) 	//Si los datos introducidos por el usuario en la interfaz de Chat
				{										//concuerdan con los datos almacenados en la base de datos
				NombreBD = Personas.getString("Nombre");			
				ApellidoBD = Personas.getString("Apellido");
				GrupoBD = Personas.getInt("Grupo");
				EstadoBD = Personas.getInt("Estado");										
				DatosUsuario = new ClaseCampo(NombreBD, ApellidoBD, LoginBD, PasswordBD, GrupoBD, EstadoBD);		//Creamos un objeto con los datos almacenados en la base de datos		
				
					if(EstadoBD==0)				
					{
					Registro = 2;						//(Registro = 2) Los datos introducidos en la InterfazdeChat son validos 
					}
					
					else				
					{
					Registro = 4;						//(Registro = 4) Los datos son validos pero su sesion ya se encuentra abierta
					}	
				}
			}
								
		}
	
		if(Consulta.compareTo("Personales")==0)						//DATOS INTRODUCIDOS EN LA INTERFAZ DE DATOS O ACTUALIZAR
		{
		Registro = 1;									//(Registro == 1) Los datos introducidos en la Interfaz de Datos van a registrarse o modificarse
		Login = DatosPersonales.RetornarLogin();					
										
			while(Personas.next())							//hasta que devuelva el valor false que indica el fin de la tabla
			{
			LoginBD = Personas.getString("Login");
			
				if(Login.compareTo(LoginBD)==0)					//Si el Login de un nuevo usuario o de uno que modifica los datos coincide con 
				{								//El login de otro usuario registrado
				identificador = DatosPersonales.RetornarIdentificador();
				posicion = Personas.getRow();
					
					if(posicion != identificador)
					{
					Registro=0;						//(Registro == 0 ) El Login introducido en la Interfaz de Datos ya existe
					}
				}
			}
		
		
			if(Registro == 1)								
			{
			Nombre = DatosPersonales.RetornarNombre();
			Apellido = DatosPersonales.RetornarApellido();
			Password = DatosPersonales.RetornarPassword();
			Grupo = DatosPersonales.RetornarGrupo();
			EstadoBD = 1;
			DatosUsuario = new ClaseCampo(Nombre, Apellido, Login, Password, Grupo, EstadoBD);	//Creamos un objeto con los datos introducidos por el usuario
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

public int RetornarRegistro()
	{
	return Registro; 					//Devuelve el resultado del registro y su respectivo significado
	}


public ClaseCampo RetornarDatosUsuario()
	{
	return DatosUsuario;					//Devuelve el objeto que apunta a los datos del usuario
	}

}