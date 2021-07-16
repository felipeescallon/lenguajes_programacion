import java.lang.*;											
import java.io.*;

class ClaseNodo 
{
private String CampoLoginL;
private TelecomunicacionRx punteroRx;
private TelecomunicacionTx punteroTx;
private ClaseNodo siguiente;

public void AsignarInfo(TelecomunicacionRx p0, TelecomunicacionTx p1, String CampoLoginL1)		
	{
	CampoLoginL=CampoLoginL1;				//Nodo de informacion que contiene el Login de un usuario activo y 			
	punteroRx=p0;						//una referencia a la clase TelecomunicacionRx encargada de la recepcion de la informacion procedente del usuario
	punteroTx=p1;						//y otra referencia a la clase Telecomunicacion Tx encargada de la transmision de informacion al usuario 
	}

public TelecomunicacionRx RetornarPunteroRx()
	{
	return punteroRx;
	} 

public TelecomunicacionTx RetornarPunteroTx()
	{
	return punteroTx;
	} 

public void AsignarSiguiente(ClaseNodo aux)
	{
	siguiente=aux;
	}

public ClaseNodo RetornarSiguiente()
	{
	return siguiente;
	}

public String RetornarCampoLoginL()
	{
	return CampoLoginL;
	}

}