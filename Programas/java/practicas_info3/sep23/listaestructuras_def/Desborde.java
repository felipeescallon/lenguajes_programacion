//Clase de excepciones que puede mandar una pila

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