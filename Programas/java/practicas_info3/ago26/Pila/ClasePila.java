//Clase que representa la estructura de la pila

import java.io.*;
class ClasePila
{
private char matrizpila[][];
private int filas; //==i
private char control[];//pa verificar si llena o vacia=>throws

public ClasePila(int nfilas)
{
matrizpila=new char[nfilas][];
filas=-1;
}

public void indice()throws Desborde//verifica si es posible introducir
{
	try
	{
	filas++;
	control=matrizpila[filas];
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas--;
	throw new Desborde("LA PILA ESTA LLENA");
	}	

}

public void ingresarcadena(char dato[])
{
	matrizpila[filas]=dato;
}

public char[] sacarcadena()throws Desborde
{
	try
	{
	char auxiliar[];
	auxiliar=matrizpila[filas];
	filas--;
	return auxiliar;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA PILA ESTA VACIA");
	}

}
	
public int elementosvalidos()throws Desborde
{
	try
	{
	int validos;
	control=matrizpila[filas];//pa evitar que diga que tiene cero elementos
	validos=filas+1;
	return validos;
	}
		
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA PILA ESTA VACIA");
	}
	

}


public char[][] lamatriz()
{
	return(matrizpila);
}	

}