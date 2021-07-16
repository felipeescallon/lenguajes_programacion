
//Clase que representa la estructura de la pila


class ClasePila extends EstDatos
{

public ClasePila(int nfilas)
{
matriz=new String[nfilas];
filas=-1;
}


public void ingresarcadena(String dato)
{
	matriz[filas]=dato;
}

public String sacarcadena()throws Desborde
{
	try
	{
	String auxiliar;
	auxiliar=matriz[filas];
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
	control=matriz[filas];//pa evitar que diga que tiene cero elementos
	validos=filas+1;
	return validos;
	}
		
	catch(ArrayIndexOutOfBoundsException err)
	{
	throw new Desborde("LA ESTRUCTURA ESTA VACIA");
	}
	

}


	

}