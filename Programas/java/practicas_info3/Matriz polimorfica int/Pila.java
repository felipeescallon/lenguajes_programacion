
class Pila extends EstDatos
{

public Pila(int tam)
	{
	i=-1;
	arr=new int[tam] ;
	}
public int sacar() throws Desborde
	{
	try
	{
	int auxiliar;
	auxiliar=arr[i];
	i--;
	return auxiliar;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA PILA ESTA VACIA");
	}

	}

public void mostrar()
	{
	for(int c=0;c<i;c++)
	System.out.println(arr[c]);
	}

}

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