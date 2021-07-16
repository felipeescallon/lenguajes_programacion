import java.io.*;
import java.lang.String;


abstract class EstDatos implements Serializable
{

protected String[] matriz;
protected String control;
protected int filas;


public EstDatos()
{
control=null;
filas=-1;
}

public void indice(String cadena1) throws Desborde//verifica si es posible introducir
{
	try
	{
	filas++;
	control=matriz[filas];
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas--;
	throw new Desborde("LA " +cadena1+ " ESTA LLENA");
	}	

}






public void ingresarcadena(String dato)
{
	matriz[filas]=dato;
}


	

abstract public String sacarcadena() throws Desborde;				

public int elementosvalidos(String cadena2) throws Desborde
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
	throw new Desborde("LA " +cadena2+ " ESTA VACIA");
	}
	

}



public String[] lamatriz()
{
	return(matriz);
}

	
}