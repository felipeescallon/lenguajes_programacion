//Clase de excepciones que puede mandar una lista

class Desborde extends Exception
{ 
	Desborde()
	{
		super();
	}
	Desborde(String mensaje)
	{
		super(mensaje);
	}
}