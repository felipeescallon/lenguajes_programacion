//Clase que genera un nod de información

import java.lang.*;
import java.io.*;

class ClaseNodo implements Serializable
{
private EstDatos puntero;
private ClaseNodo siguiente;
private String nombre1,tipo;


//PUEDO ASIGNAR MUCHOS PERO SOLO RETORNAR UNO
public void asignar_info(EstDatos r, String n , String t)
	{
	puntero=r;
	nombre1=n;
	tipo=t;
	}

public String retornar_nombre()
	{
	return nombre1;
	}

public String retornar_tipo()
	{
	return tipo;
	}

public EstDatos retornar_puntero()
	{
	return puntero;
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