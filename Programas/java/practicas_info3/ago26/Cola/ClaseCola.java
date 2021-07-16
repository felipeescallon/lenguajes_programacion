//Clase que representa la estructura de la cola

import java.io.*;

class ClaseCola
{
private String matrizcola[],control;
private int filas;

public ClaseCola(int nfilas)
{
matrizcola=new String[nfilas];
filas=-1;
}

public void indice()throws Desborde
{
	try
	{
	filas++;
	control=matrizcola[filas];
	}

	catch(ArrayIndexOutOfBoundsException err)
	{
	filas--;
	throw new Desborde("LA COLA ESTA LLENA");
	}

}


public void ingresarcadena(String dato)
{
	matrizcola[filas]=dato;
}

public String sacarcadena()throws Desborde
{
	try
	{
	String auxiliar;
	auxiliar=matrizcola[0];
	for(int j=0; j<filas; j++)
		{	
		matrizcola[j]=matrizcola[j+1];//corrimiento
		}

	filas--;
	control=matrizcola[filas+1];
	return auxiliar;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas++;
	throw new Desborde("LA COLA ESTA VACIA");
	}

}
	
public int elementosvalidos()throws Desborde
{
	try
	{
	int validos;
	control=matrizcola[filas];
	validos=filas+1;
	return validos;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA COLA ESTA VACIA");
	}


}

public String[] lamatriz()
{
	return(matrizcola);
}	

}