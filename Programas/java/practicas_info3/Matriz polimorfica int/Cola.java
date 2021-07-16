class Cola extends EstDatos
{

public Cola(int tam)
	{
	i=-1;
	arr=new int[tam] ;
	}

public int sacar() throws Desborde
	{
	try
	{
	int auxiliar;
	auxiliar=arr[0];
	for(int j=0; j<i; j++)
		{	
		arr[j]=arr[j+1];		//corrimiento
		}

	i--;
						//control=matrizcola[filas+1];
	return auxiliar;
	}
		
	catch(ArrayIndexOutOfBoundsException err)
	{
	i++;
	throw new Desborde("LA COLA ESTA VACIA");
	}

	}


public void mostrar()
	{
	for(int c=0;c<i;c++)
	System.out.println(arr[c]);
	}
}