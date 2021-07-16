import java.io.*;
import java.lang.*;

class GuardarCliente
{
private ObjectOutputStream EscribirObjeto;
private File NombreArchivo; 


public GuardarCliente()
{
}

public void GuardarDatos(Cliente DatosCliente)throws Exception				//Metodo para guardar los datos del usuario en un archivo
{
	try
	{
	NombreArchivo = new File("DatosPersonales.txt"); 				//Nombre del fichero
	EscribirObjeto = new ObjectOutputStream(new FileOutputStream(NombreArchivo));	//Instaciamos los flujos para escribir en el archivo
	EscribirObjeto.writeObject(DatosCliente);					//Escribimos el objeto con los datos del usuario en el archivo
	}
	
	finally										//Cerramos el flujo
	{
	if(EscribirObjeto!=null)
	EscribirObjeto.close();
	}	
	
}

}