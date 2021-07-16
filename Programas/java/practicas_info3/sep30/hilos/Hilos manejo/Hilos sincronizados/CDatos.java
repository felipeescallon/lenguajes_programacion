import java.io.*;
import java.lang.*;


class CDatos
{
private double [] dato;
public int ind=0;
public int tamaño;

public CDatos(int n)
{
	if (n < 1) 
	{
	n = 10; 
	}
	tamaño = n;
	dato = new double[n];
}

public void asignar(double x, int i)
{
	dato[i]=x;
}

public synchronized int calculos(String hilo)
{
	if(ind >= tamaño) return tamaño; 
	double x = Math.random(); 
	System.out.println(hilo + " tomo la muestra" + ind);
	asignar(x, ind);
	ind++;
	return ind;
}

}