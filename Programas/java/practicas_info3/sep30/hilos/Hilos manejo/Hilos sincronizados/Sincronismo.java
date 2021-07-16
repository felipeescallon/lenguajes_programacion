import java.io.*;
import java.lang.*;

class Sincronismo
{
	public static void main(String args[])
	{
	CDatos datos = new CDatos(10);
	CAdquirirDatos adquirirDatos_0 = new CAdquirirDatos(datos);
	CAdquirirDatos adquirirDatos_1 = new CAdquirirDatos(datos);
	adquirirDatos_0.start();
	adquirirDatos_1.start();
	}
}
