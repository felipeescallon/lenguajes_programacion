//main de los archivos
import java.io.*;

public class Archivos
{
	public static void main (String arg[])
	{
	String nombreFichero=null;
	File fichero;
	char respuest ='s';
	ClaseArchivos guardar=new ClaseArchivos();
	
	try
	{
	
	do
	{
	respuest ='s';	
	System.out.println("Escriba el nombre del fichero: ");
	nombreFichero=Leer.dato();
	fichero= new File(nombreFichero);
	
	if(fichero.exists())
		{
		System.out.println("El fichero ya existe ¿Desea reemplazarlo?: (s/n) ");
		respuest=Leer.caracter();
		Leer.limpiar();
		}
	}while(respuest=='n');

	
	guardar.crearFichero(fichero);
	

	}
	
	catch(IOException e)
	{
	System.out.println("Error: " + e.getMessage());
	}

	}
}