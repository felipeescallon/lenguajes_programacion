class CEstDatos
{
	public static void main(String args[])
	{
	EstDatos mp[]= new EstDatos[5];
	
	
	String a,b;
	try
	{
	mp[0]=new ClasePila(2);
	mp[0].ingresarcadena("pipe");
	a=mp[0].sacarcadena();
	//mp[0].mostrar();

	mp[1]=new ClaseCola(3);
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
