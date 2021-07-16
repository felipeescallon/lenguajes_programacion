abstract class EstDatos  
{

protected int []arr;
protected int i;


public EstDatos()
	{
	}

public void ingresar(int e) throws Desborde 
	{
	try
	{
		i++;	
		arr[i]=e;
	}	

	catch(ArrayIndexOutOfBoundsException err)
	{
		i--;
		throw new Desborde("LA PILA ESTA LLENA");
	}	
	
	}	


abstract public int sacar() throws Desborde;				//implementado en pila y cola en forma !=


abstract public void mostrar();
	
}