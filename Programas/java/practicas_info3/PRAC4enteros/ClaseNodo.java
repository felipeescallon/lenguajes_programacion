//Clase que genera un nod de informaci�n

import java.lang.*;
import java.io.*;

class ClaseNodo
{
private int info;
private ClaseNodo siguiente;

public void asignar_info(int i)
	{
	info=i;
	}
public int retornar_info()
	{
	return info;
	}
public void asignar_siguiente(ClaseNodo aux)
	{
	siguiente=aux;
	}
public ClaseNodo retornar_siguiente()
	{
	return siguiente;
	} 
}