import java.io.*;
import java.lang.*;


class CDatos
{
private double [] dato;
public int ind=0;
public int tama�o;

public CDatos(int n)
{
	if (n < 1) 
	{
	n = 10; 
	}
	tama�o = n;
	dato = new double[n];
}

public void asignar(double x, int i)
{
	dato[i]=x;
}

public synchronized int calculos(String hilo)
{
	if(ind >= tama�o) return tama�o; 
	double x = Math.random(); 
	System.out.println(hilo + " tomo la muestra" + ind);
	asignar(x, ind);
	ind++;
	return ind;
}

}