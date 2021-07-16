//Clase que representa la estructura de la LISTA

import java.io.*;
class ClaseLista
{
private int vectorlista[];
private int filas, control;

public ClaseLista(int nfilas)
{
vectorlista=new int[nfilas];
filas=-1;
control=0;//hay que inicilizarlo
}

public void indice()throws Desborde
{
	try
	{
	filas++;
	control=vectorlista[filas];
	}
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas--;
	throw new Desborde("LA LISTA ESTA LLENA");
	}

}


public void ingresararriba(int dato)
{
	for(int j=filas-1; j>=0; j--)
		{
		vectorlista[j+1]=vectorlista[j];//corrimiento
		}
	vectorlista[0]=dato;	
}
	

public void ingresarabajo(int dato)
{
	vectorlista[filas]=dato;
}


public int sacararriba()throws Desborde
{
	try
	{
	int auxiliar;
	auxiliar=vectorlista[0];
	for(int k=0; k<filas; k++)
		{
		vectorlista[k]=vectorlista[k+1];
		}
	filas--;
	control=vectorlista[filas+1];
	return auxiliar;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas++;
	throw new Desborde("LA LISTA ESTA VACIA");
	}

}
	
public int sacarabajo()throws Desborde
{
	try
	{
	int auxiliar1;
	auxiliar1=vectorlista[filas];
	filas--;
	return auxiliar1;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA LISTA ESTA VACIA");
	}

}
	


public int elementosvalidos()throws Desborde
{
	try
	{
	int validos;
	control=vectorlista[filas];
	validos=filas+1;
	return validos;
	}

	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA LISTA ESTA VACIA");
	}	

}

public int[] lalista()
{
	return(vectorlista);
}	

}