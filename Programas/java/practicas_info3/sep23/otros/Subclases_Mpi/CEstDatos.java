import java.io.*;
import java.lang.String;

class CEstDatos
{
	public static void main(String args[])
	{
	EstDatos mp[]= new EstDatos[5];
	
	
	String a,b;
	try
	{
	mp[0]=new ClasePila(10);
	mp[0].indice();
	mp[0].ingresarcadena("pipe");
	a=mp[0].sacarcadena();
	//mp[0].mostrar();

	mp[1]=new ClaseCola(10);
	mp[1].indice();
	mp[1].ingresarcadena("diego");
	b=mp[1].sacarcadena();
	//mp[1].mostrar();
	
	
	System.out.println(a);
	System.out.println(b);	
	
	}
	catch(Desborde e)
		{
		System.out.println(e.getMessage());
		}

	}
}
