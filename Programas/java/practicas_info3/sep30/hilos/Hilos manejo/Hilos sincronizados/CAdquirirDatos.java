import java.io.*;
import java.lang.*;


class CAdquirirDatos extends Thread  
{
private CDatos m; 

public CAdquirirDatos(CDatos mdatos) 
	{
	m = mdatos;
	}

public void run()
	{
		int i=0;
		do
		{
		i= m.calculos(getName()); 
		}while (i<m.tamaño);
	}
}
