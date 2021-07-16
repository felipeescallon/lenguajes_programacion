import java.io.*;
import java.lang.*;

class Registro implements Serializable  
{
private String Veredicto;
private String[] ListaUsuarios;
private int PuertoAsignar;

public Registro(String Cadena, String[] Lista, int PuertoAsignar1)	
{
PuertoAsignar = PuertoAsignar1;			//Asignamos el puerto individual para cada usuario, la cadena que contiene el resultado
Veredicto = Cadena;				//de la verificacion y la lista de los logines de los usuarios activos en la base de datos
ListaUsuarios = Lista;
}

public String RetornarVeredicto()
	{
	return Veredicto;
	}

public String[] RetornarLista()
	{
	return ListaUsuarios;
	}	


public int RetornarPuertoA()
	{
	return PuertoAsignar;
	}


}

