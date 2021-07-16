import java.io.*;
import java.lang.*;

class GuardarRegistro
{
private ObjectOutputStream EscribirObjeto;
private File NombreArchivo; 


public GuardarRegistro()
{
}

public void GuardarDatosRegistro(Registro DatosVeredicto)throws Exception		//Metodo para guardar los datos del usuario en un archivo
{
	try
	{
	NombreArchivo = new File("Veredicto.txt"); 	                             	//Nombre del fichero
	EscribirObjeto = new ObjectOutputStream(new FileOutputStream(NombreArchivo));	//Instaciamos los flujos para escribir en el archivo
	EscribirObjeto.writeObject(DatosVeredicto);					//Escribimos el objeto con el resultado de la verificacion 
	}
	
	finally										//Cerramos el flujo
	{
	if(EscribirObjeto!=null)
	EscribirObjeto.close();
	}	
	
}

}