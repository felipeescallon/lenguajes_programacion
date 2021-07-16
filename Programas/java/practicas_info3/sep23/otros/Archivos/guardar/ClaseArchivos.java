//funcion guardar un archivo

import java.io.*;
class ClaseArchivos
{

public void crearFichero(File fichero) throws IOException 
	{
	DataOutputStream dos =null;
	char respuesta;
	try
	{
	dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)));
	String nombre,direccion;
	long telefono;
	
	do
	{
	System.out.println("nombre : " );
	nombre=Leer.dato();
	System.out.println("direccion : " );
	direccion=Leer.dato();
	System.out.println("telefono : " );
	telefono=Leer.datoLong();
	
	dos.writeUTF(nombre);
	dos.writeUTF(direccion);
	dos.writeLong(telefono);
	
	System.out.println("¿desea escribir otro registro? (s/n) ");
	respuesta=Leer.caracter();
	Leer.limpiar();
	}while(respuesta=='s');
	}	

	finally
	{
	if(dos!=null)
	dos.close();
	}
}	 


}