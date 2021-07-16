//CREATED BY FELIPE ESCALLON PORTILLA: 9th/AGO/2004

//INFORMATICA 3:JAVA LABORATORY



class Principal
{
	public static void main(String args[])  /*paso de paramertros al main*/
	
	//ojo:String-->>1era letra con Mayus!
	{
		Holamundo2 obj;
		obj= new Holamundo2();
		obj.Escribir();
	}
}




class Holamundo2
{

	public void Escribir()

	{
  		System.out.println("hOLA MuNdo!");
	}

}

/*OJO::GRABAR COMO:exactamente como se llame la clase que contenga al main*/


