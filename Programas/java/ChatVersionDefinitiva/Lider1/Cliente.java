import java.io.*;
import java.lang.*;

class Cliente implements Serializable				  
{
private String NombreG, ApellidoG, LoginG, PasswordG;
private int GrupoG, IdentificadorG;

public Cliente(String Nombre, String Apellido, String Login, String Password, int Grupo, int Identificador)	
{
NombreG = Nombre;				 							
ApellidoG = Apellido;				 
LoginG = Login;					 //Asignamos los nombres escritos en los cuadros
PasswordG = Password;				 //de texto a los atributos del objeto
GrupoG = Grupo;
IdentificadorG = Identificador; 
}

public String RetornarNombre()			   			
	{
	return NombreG;
	}

public String RetornarApellido()
	{
	return ApellidoG;
	}

public String RetornarLogin()
	{
	return LoginG;
	}

public String RetornarPassword()
	{
	return PasswordG;
	}

public int RetornarGrupo()
	{
	return GrupoG;
	}

public int RetornarIdentificador()
	{
	return IdentificadorG;
	}	
}

