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


public void control()throws Desborde
	{
	if(primero==null)
		{
		throw new Desborde("LA LISTA ESTA VACIA");
		}
	}


    public void control1()throws Desborde
	{
	if(lc==null)
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
		throw new Desborde("IMPOSIBLE IMPRIMIR PORQUE NO HAY NODOS EN LA LISTA!!!");
		}
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

 public void ubicar_ref_inicio()
 {
  ref=null;
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
  ClaseNodo buscar=primero;

	while(buscar!=null)
		{
		System.out.print(buscar.retornar_info()+" ");
		buscar=buscar.retornar_siguiente();
		}
   
 
	}



public void visualizar_lisc()//pendiente excepcion de obj no creado
 {
    lc.imprimir_lis();
 }



public void guardar_lis(File fichero)throws IOException
	{
	DataOutputStream dos =null;//manejo de datos primitivos
	char respuesta;		

	try
	{
	dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)));
	int value;
	ClaseNodo guardador=primero;
	while(guardador!=null)//recorrer lista
		{
		value=guardador.retornar_info();
		dos.writeInt(value);
		guardador=guardador.retornar_siguiente();
		}
	
	}
	
	finally//siempre cerrar flujos
	{
	if(dos!=null)
	dos.close();
	}	
	}


public void mostrarFichero(String nombreFichero) throws IOException
	{
	 DataInputStream dis=null;
	 File fichero=null;

	try
	{
 	fichero = new File(nombreFichero);
 	if(fichero.exists())
 	{
	dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)));

	int values;

	do
	{
	values=dis.readInt();
	innodo_lis(values);
	}while(true);//mientras exista  O HASTA QUE SE ACABE 
	}

	else 	System.out.println("EL ARCHIVO " +nombreFichero+ " ES INESXISTENTE");

	}
	catch(EOFException e)
	{ 
	System.out.println("Archivo recuperado");
	}

	finally
	{
	if(dis!=null) dis.close();
	invertir_lis();
	} 	

	}		

public int buscar_lis(int contenido)
	{
	int c=1;
	ref=primero;

	while(contenido!=ref.retornar_info() && ref!=null)
	{
	ref=ref.retornar_siguiente();
	c=c+1;
	}
		
	if(ref==null)
	{
	return 0;//NO ENCONTRADO
	}		
	
	else
	{
	return c;
	}	
	}	

}










