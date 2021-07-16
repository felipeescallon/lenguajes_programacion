//main de los archivos
import java.io.*;

public class Recuperar//pa probar
{
	public static void main (String args[])

	{
             ClaseRecuperar obj=new ClaseRecuperar();

		if(args.length!=1)		 System.err.println("Sintaxis: java MLT"+"<fichero fuente>");
		else
		{
		 try
		 {
			obj.mostrarFichero(args[0]);

		 }
		  catch(IOException e)
 		  {
                   System.out.println("Error:"+e.getMessage());		
		  }


		}
			
        }

}