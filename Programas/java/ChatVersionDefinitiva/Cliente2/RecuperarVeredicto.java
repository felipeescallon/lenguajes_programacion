import java.lang.*;
import java.io.*;

class RecuperarVeredicto
{
private ObjectInputStream ObjetoLeer;
private File NombreArchivo; 
private Registro Veredicto;

public RecuperarVeredicto()			
{
String[] ListaBasura = new String[0];
Veredicto= new Registro("",ListaBasura,0);						//Creamos un falsa instancia de la clase Registro para poder  
}											//recuperar el objeto contenido en el archivo

public Registro RecuperarFveredicto()throws Exception					//Metodo para recuperar el resutado de la verificacion
{
	try
	{
	NombreArchivo = new File("Veredicto.txt"); 					//Nombre del fichero
	ObjetoLeer = new ObjectInputStream(new FileInputStream(NombreArchivo));		//Instaciamos el flujo para leer el contenido del archivo
	Veredicto = (Registro)ObjetoLeer.readObject();					//Recuperamos el objeto contenido en el archivo
	}
	
	finally										//Cerramos el flujo
	{
	if(ObjetoLeer!=null)
	ObjetoLeer.close();
	return Veredicto;								//Retornamos la referencia que apunta al objeto que contiene 	
	}										//el resultado de la verificacion

}

}
