import java.io.*;
import java.lang.String;


abstract class EstDatos  
{

protected String[] matriz;
protected String control;
protected int filas;


public EstDatos(){}

public void indice() throws Desborde//verifica si es posible introducir
{
	try
	{
	filas++;
	control=matriz[filas];
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas--;
	throw new Desborde("LA PILA ESTA LLENA");
	}	

}






public void ingresarcadena(String dato)
{
	matriz[filas]=dato;
}


	

abstract public String sacarcadena() throws Desborde;				

public int elementosvalidos() throws Desborde
{
	try
	{
	int validos;
	control=matriz[filas];//pa evitar que diga que tiene cero elementos
	validos=filas+1;
	return validos;
	}
		
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA ESTRUCTURA ESTA VACIA");
	}
	

}



public String[] lamatriz()
{
	return(matriz);
}

	
}