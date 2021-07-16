//Clase que representa la estructura de datos 

import java.lang.*;
import java.io.*;

class ClaseLista
{
private String nombre;
private ClaseNodo primero,ref,ultimo;
private ClaseLista lc;

public ClaseLista(String n)
	{
	nombre=new String (n);
	primero=null;
	ref=null;
	ultimo=null;
  lc=null;
  	}

public void innodo_lis(int i)
	{
	ClaseNodo nuevo=new ClaseNodo();	//Crear el nodo
	nuevo.asignar_info(i);			//Llenar el nodo
	
	if(ref==null)
		{
		nuevo.asignar_siguiente(primero);
		primero=nuevo;
		}
	else	
		{
		nuevo.asignar_siguiente(ref.retornar_siguiente());
		ref.asignar_siguiente(nuevo);
		}

	if(nuevo.retornar_siguiente()==null)
		{
		ultimo=nuevo;
		ref=null;
		}	
	}


public	int elimnodo_lis()
	{
	ClaseNodo elim=null;
	
	if(ref==null || ref==primero) 
		{
		elim=primero;
		primero=primero.retornar_siguiente();
		ref=null;
		}

	else
		{
		elim=ref;
		ref=primero;
		while(ref.retornar_siguiente()!=elim)
			{
			ref=ref.retornar_siguiente();
			}
		ref.asignar_siguiente(elim.retornar_siguiente());
		}


	if(elim.retornar_siguiente()==null)
		{
		ultimo=ref;
		}

	int aux;
	aux=elim.retornar_info();
	elim=null;
	return aux;

	}


public void copiar_lis()
	{
  ClaseNodo copiar=primero;
  int valor;//auxiliar para guardar la info
	ref=null;

	lc=new ClaseLista("Lista copiada");

	while(copiar!=null)//hago recorrido por la lista original
		{
		valor=copiar.retornar_info();
		lc.innodo_lis(valor);
		copiar=copiar.retornar_siguiente();
    }

   lc.invertir_lis();
  }

public void invertir_lis()
	{
	ClaseNodo invertir1=primero;
	ClaseNodo invertir2=primero;
	int valor;
	ref=null;

	while(invertir2!=null)
		{
		valor=invertir2.retornar_info();
		innodo_lis(valor);
		invertir2=invertir2.retornar_siguiente();
		}


	invertir2=primero;
	while(invertir2.retornar_siguiente()!=invertir1)
		{
		invertir2=invertir2.retornar_siguiente();
		}

	ultimo=invertir2;
	invertir2.asignar_siguiente(null);
	invertir1=null;
	imprimir_lis();//comprobar
	}



public void imprimir_lis()
	{
	System.out.println("El nombre de la lista es " +nombre);
	System.out.println("La informacion de la lista es ");
	ref=primero;
	while(ref!=null)
		{
		System.out.print(ref.retornar_info()+" ");
		ref=ref.retornar_siguiente();
		}
	}

 public void visualizar_lisc()//pendiente excepcion de obj no creado
 {
    lc.imprimir_lis();
 }

 }










