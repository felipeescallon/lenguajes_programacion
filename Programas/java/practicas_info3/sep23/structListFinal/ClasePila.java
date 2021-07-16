import java.io.*;
import java.lang.String;
//Clase que representa la estructura de la pila


class ClasePila extends EstDatos
{

public ClasePila(int nfilas)
{
super(nfilas);//debe ser la 1a linea del constructor:accedo a la clase estdatos para ejecutar el //constructor de estdatos
}




public String sacarcadena() throws Desborde
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
	



	

}