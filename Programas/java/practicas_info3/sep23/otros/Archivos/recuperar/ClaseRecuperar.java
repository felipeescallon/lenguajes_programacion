import java.io.*;

class ClaseRecuperar
{

 public void mostrarFichero(String nombreFichero) throws IOException
{
 DataInputStream dis=null;
 File fichero=null;

try
{
 fichero = new File(nombreFichero);
 if(fichero.exists())
 {
	dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)));

	String nombre,direccion;
	long telefono;
	do
	{
	 nombre=dis.readUTF();
	 direccion=dis.readUTF();
	 telefono=dis.readLong();	

		System.out.println(nombre);
		System.out.println(direccion);
		System.out.println(telefono);
		System.out.println();

	}while(true);//mientras exista
 }

else 	System.out.println("File does not exist!");

}
catch(EOFException e)
{ 
	System.out.println("fin del listado");
}

finally
{
	if(dis!=null) dis.close();

} 	
 

}



}