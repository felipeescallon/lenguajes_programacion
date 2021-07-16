//Clase que representa la estructura de datos 

import java.lang.*;
import java.io.*;

class ClaseLista implements Serializable
{
private String nombre;
private ClaseNodo primero,ref,ultimo,salvar;
private ClaseLista lc;

public ClaseLista(String n)
	{
	nombre=new String (n);
	primero=null;
	ref=null;
	salvar=null;	
	ultimo=null;
 	}


public void control()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("IMPOSIBLE ELIMINAR NODO, LA LISTA ORIGINAL ESTA VACIA");
		}
	}

public void control1()throws Desborde		
	{
	if(primero==null)
		{
		throw new Desborde("NO EXISTE LISTA COPIADA");
		}
	}

public void control2()throws Desborde
	{
	if(primero!=null)
		{
		throw new Desborde("IMPOSIBLE RECUPERAR PORQUE HAY UNA APLICACION EN CURSO");
		}
	}


public void control3()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("IMPOSIBLE BUSCAR Y REFERENCIAR NODO, LA LISTA ORIGINAL ESTA VACIA");
		}
	}


public void control4()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("IMPOSIBLE IMPRIMIR, LA LISTA COPIADA ESTA VACIA");
		}
	}


public void control5()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("IMPOSIBLE INVERTIR, LA LISTA ORIGINAL ESTA VACIA");
		}
	}

public void control7()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("IMPOSIBLE IMPRIMIR, LA LISTA ORIGINAL ESTA VACIA");
		}
	}

public void control8()throws Desborde		
	{
	if(primero==null)
		{
		throw new Desborde("");
		}
	}


public void control9()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("IMPOSIBLE MODIFICAR NODO, LA LISTA ORIGINAL ESTA VACIA");
		}
	}


public void innodo_lis(EstDatos general, String nombre, String tipo)
	{
	ClaseNodo nuevo=new ClaseNodo();	//Crear el nodo
	nuevo.asignar_info(general, nombre, tipo);			//Llenar el nodo

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
		//ref=null;
		}
	}

public void ubicar_ref_inicio()
	{
  	ref=null;
 	}

public void ubicar_pri_inicio()
	{
  	ref=null;
 	primero=null;
	ultimo=null;
	}



public	ClaseNodo elimnodo_lis(int p)
	{
	ClaseNodo elim=null;
	EstDatos  imprimidor;	
	String matriz[];
	int validos;
	
	if(p==1)					//p=1 es equivalente a elim!=null
	{
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
	
	ref=null;
	return elim;
	}
	
	else
	{
	elim=null;
	return elim;
	}
	
	}


public void invertir_lis()
	{
	EstDatos nest;
	String nnodo, ntipo;
	ClaseNodo invertir1=primero;
	ClaseNodo invertir2=primero;
	
	
	ref=null;

	while(invertir2!=null)
		{
		nest=invertir2.retornar_puntero();
		nnodo=invertir2.retornar_nombre();
		ntipo=invertir2.retornar_tipo();
		innodo_lis(nest,nnodo,ntipo);
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
	invertir2=null;
	imprimir_lis();//comprobar
	}



public void imprimir_lis()
	{
	ClaseNodo buscar=primero;
	EstDatos printer;


	System.out.println("El nombre de la lista es " +nombre);
	System.out.println("La informacion de la lista es \n");	
	String matriz[];
	int validos;

	
	while(buscar!=null)
		{
		System.out.println("Nodo: "+buscar.retornar_nombre());
		System.out.println("Tipo: "+buscar.retornar_tipo());
		printer=buscar.retornar_puntero();
		try
			{
			validos=printer.elementosvalidos(buscar.retornar_tipo());
			matriz=new String[validos]; 
			matriz=printer.lamatriz();
		
			System.out.println("La " +buscar.retornar_tipo()+ " tinene "+validos+" cadenas validas y son: \n");
			for(int h=validos-1; h>=0; h--)
				{
				System.out.println(matriz[h]);			
				}
			}
		
		catch(Desborde e)
		{
		System.out.println(e.getMessage());
		}
		
		catch(Exception e)
		{
		}
		
		System.out.println("\n\n");
		buscar=buscar.retornar_siguiente();
		}
	}


public ClaseNodo buscar_lis(String contenido)
	{
	ref=primero;
	
	try
	{
	while(!(contenido.compareTo(ref.retornar_nombre())==0) && ref!=null)
		{
		ref=ref.retornar_siguiente();
		}
				
	}
	
	catch(Exception e)				
	{
	return ref;
	}

	return ref;				//NO ENCONTRADO
	}


public ClaseNodo buscar_lis1(String contenido)//modifico sin perder ref:pa eso utilizo salvar
	{
	salvar=primero;
	
	try
	{
	while(!(contenido.compareTo(salvar.retornar_nombre())==0) && salvar!=null)
		{
		salvar=salvar.retornar_siguiente();
		}
				
	}
	
	catch(Exception e)				
	{
	return salvar;
	}

	return salvar;				//NO ENCONTRADO
	}

public void modificar_nodo(EstDatos modificado, String nombre1, String tipo1)
	{	
	salvar.asignar_info(modificado, nombre1, tipo1);
	salvar=null;
	}
}