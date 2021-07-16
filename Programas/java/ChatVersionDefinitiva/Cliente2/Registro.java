import java.io.*;
import java.lang.*;

class Registro implements Serializable  
{
private String Veredicto;
private String[] ListaUsuarios;
private int PuertoAsignar;

public Registro(String Cadena, String[] Lista, int PuertoAsignar1)	//Objeto que contiene los resultados de la verificacion	
{									//Puerto individual asignado al usuario; por el cual se realizara la
PuertoAsignar = PuertoAsignar1;						//comunicacion si el resultado es satisfactorio
Veredicto = Cadena;						  	//Cadena que describe el resultado de la verificacion 
ListaUsuarios = Lista;							//Matriz de String que contendra los logines de los demas usarios
}									//activos en la seccion si el resutado es positivo

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

