//Clase Archivo

import java.lang.*;
import java.io.*;

class ClaseArchivo 
{

ClaseArchivo()
{
}


public void guardar_lis(File fichero, ClaseLista apuntador)throws IOException
	{
	ObjectOutputStream oos =null;								//manejo de datos primitivos
	char respuesta;		

	try
	{
	oos = new ObjectOutputStream(new FileOutputStream(fichero));
	oos.writeObject(apuntador);
	
	}
	
	finally//siempre cerrar flujos
	{
	if(oos!=null)
	oos.close();
	}	
	}

public ClaseLista mostrarFichero(String nombreFichero) throws IOException//RECUPERARLO!!!
	{
	ObjectInputStream ois=null;
	File fichero=null;
	ClaseLista recuperador=null;
	
	try
	{
		try
		{
	 	fichero = new File(nombreFichero);
 		if(fichero.exists())
 			{
			ois = new ObjectInputStream(new FileInputStream(fichero));

		do
		{
		recuperador=(ClaseLista)ois.readObject();
		}while(true);													//mientras exista  O HASTA QUE SE ACABE 
		}

		else 	
		{
		System.out.println("EL ARCHIVO " +nombreFichero+ " ES INESXISTENTE");
		}
	
		}
	
		catch(EOFException e)
		{ 
	
		System.out.println("\n");
		if(nombreFichero!="copialista.txt")
			{
			System.out.println("Archivo recuperado");
			}
		System.out.println("\n");
		}

	
		finally
		{
		if(ois!=null) ois.close();
		return recuperador;
		}
	}
	
	catch(Exception e)				
	{
	return null;
	}

	}		


}		