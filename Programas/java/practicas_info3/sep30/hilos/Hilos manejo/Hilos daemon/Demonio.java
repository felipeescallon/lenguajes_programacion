import java.io.*;
import java.lang.*;

class Demonio
{
	public static void main(String args[])
	{
	ClaseDemonio dbip = new ClaseDemonio();
	ContadorAdelante cuentaAdelante = new ContadorAdelante("Contador+");
	
	System.out.println("presione enter par finalizar");
	
	InputStreamReader is = new InputStreamReader(System.in);
     	BufferedReader br = new BufferedReader(is);	
	try
		{
		br.readLine();
		}
		
	catch(IOException e)
		{
		}
	
	cuentaAdelante.terminar();
	}
}
