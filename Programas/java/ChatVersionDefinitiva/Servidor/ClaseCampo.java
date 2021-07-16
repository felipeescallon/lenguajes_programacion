import java.lang.*;
import java.io.*;

class ClaseCampo
{
private int GrupoBD, EstadoBD; 
private String NombreBD, ApellidoBD, LoginBD, PasswordBD;

public ClaseCampo(String Nombre, String Apellido, String Login, String Password, int Grupo, int Estado)
{
	NombreBD = Nombre;
	ApellidoBD = Apellido;				
	LoginBD = Login;				//Asignamos los datos que el usuario introdujo a los	
	PasswordBD = Password;				//atributos del objeto
	GrupoBD = Grupo;
	EstadoBD = Estado;
}

public String RetornarNombreBD()
	{
	return NombreBD;
	}

public String RetornarApellidoBD()
	{
	return ApellidoBD;
	}

public String RetornarLoginBD()
	{
	return LoginBD;
	}

public String RetornarPasswordBD()
	{
	return PasswordBD;
	}

public int RetornarGrupoBD()
	{
	return GrupoBD;
	}

public int RetornarEstadoBD()
	{
	return EstadoBD;
	}

}