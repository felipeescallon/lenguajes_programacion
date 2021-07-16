import java.lang.*;
import java.io.*;

class RecuperarCliente
{
private ObjectInputStream ObjetoLeer;
private File NombreArchivo; 
private Cliente DatosCliente;

public RecuperarCliente()			
{
DatosCliente = new Cliente("","","","",0,0);						//Creamos un falsa instancia de la clase Registro para poder  
}											//recuperar el objeto contenido en el archivo

public Cliente RecuperarFDatosPersonales()throws Exception				//Metodo para recuperar el resutado de la verificacion
{
	try
	{
	NombreArchivo = new File("DatosPersonales.txt"); 				//Nombre del fichero
	ObjetoLeer = new ObjectInputStream(new FileInputStream(NombreArchivo));		//Instaciamos el flujo para leer el contenido del archivo
	DatosCliente = (Cliente)ObjetoLeer.readObject();				//Recuperamos el objeto contenido en el archivo
	}
	
	finally										//Cerramos el flujo
	{
	if(ObjetoLeer!=null)
	ObjetoLeer.close();
	return DatosCliente;								//Retornamos la referencia que apunta al objeto que contiene 	
	}										//los datos personales del cliente 

}

}
