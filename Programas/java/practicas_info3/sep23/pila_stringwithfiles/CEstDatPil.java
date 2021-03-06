/*ESTA CLASE IMPLEMENTA LA ESTRUCTURA DE DATOS PILA A TRAVES DE UN ARREGLO*/

class Desborde extends Exception
{
public Desborde(){};
public Desborde(String mensaje)
	{
	super(mensaje);
	}
}

class CEstDatPil
{
 private int i, tam;
 private String [] p;//matriz bidimensional

 public CEstDatPil(int tamf)
	{
	tam=tamf;
	p=new String[tamf];
	//for(int j=0;j<tam;j++) p[j]=0;::unnecessary
	i=-1;
	}

 public void ingresar (String e) throws Desborde
	{	
	try
		{
		i++;
		p[i]=e;
		}
	catch(ArrayIndexOutOfBoundsException err)
		{
		i--;
		throw new Desborde("Error: No puede ingresar mas datos, pila llena");
		}
	}

 public String sacar () throws Desborde
	{
	try
		{
		String aux;
		aux=p[i];
		i--;
		return aux;
		}
	catch(ArrayIndexOutOfBoundsException err)
		{
		throw new Desborde("Error: No puede sacar mas datos, pila vacia");
		}	
	}

 public void mostrar ()
	{
	if(i==-1) System.out.println("NO HAY ELEMENTOS PARA MOSTRAR. LA PILA ESTA VACIA.");
	else
		{
		System.out.println("HAY "+(i+1)+" ELEMENTOS ALMACENADOS EN LA PILA");
 		for(int j=0;j<=i;j++) 
				{
				System.out.print("ELEMENTO "+(j+1)+" : ");
				System.out.println(p[j]);
				}
		}
	}

public String retornarcad(int pos)//yo le digo cual con el parametro
{
	return p[pos];
}

public int retornartam()
{
	return tam;
}

 public int retornari()
	{
	return i;
	}
}
 