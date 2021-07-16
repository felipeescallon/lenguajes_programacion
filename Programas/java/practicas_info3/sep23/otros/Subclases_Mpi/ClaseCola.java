import java.io.*;
import java.lang.String;
//Clase que representa la estructura de la cola



class ClaseCola extends EstDatos
{

public ClaseCola(int nfilas)
{
matriz=new String[nfilas];
filas=-1;
}

public String sacarcadena() throws Desborde
{
	try
	{
	String auxiliar;
	auxiliar=matriz[0];
	for(int j=0; j<filas; j++)
		{	
		matriz[j]=matriz[j+1];//corrimiento
		}

	filas--;
	//control=matriz[filas+1];
	return auxiliar;
	}
	
	catch(ArrayIndexOutOfBoundsException err)
	{
	filas++;
	throw new Desborde("LA COLA ESTA VACIA");
	}

}
	
}