//Principal de la LISTA

import java.io.*;

public class Lista
{
	public static void main(String args[])
	{
	int nfilas, opcion;
	int ingreseentero;//pude ser negativo
	
	System.out.println("\n");
	System.out.println("Digite el numero de elementos que desea que tenga la lista:");

	do
	{
	nfilas=Leer.datoInt();
	System.out.println("\n");
	if(nfilas==Integer.MIN_VALUE || nfilas<1)
	System.out.println("Ingrese un numero valido (entero mayor que cero):");
	}while(nfilas==Integer.MIN_VALUE || nfilas<1);

	ClaseLista renglon = new ClaseLista(nfilas);

	do
	{
		System.out.println("****---------****------------****");
		System.out.println("M E N U");
		System.out.println("1. Introducir entero por arriba");
		System.out.println("2. Introducir entero por abajo");
		System.out.println("3. Sacar entero por arriba");
		System.out.println("4. Sacar entero por abajo");
		System.out.println("5. Mostrar lista");
		System.out.println("6. Salir");
		System.out.println("****---------****------------****");
		opcion=Leer.datoInt();
		System.out.println("\n");
		try
		{
		switch(opcion)
		{
		case 1:		renglon.indice();
				System.out.println("Introduzca un entero:");
				do
				{
				ingreseentero=Leer.datoInt();
				if(ingreseentero==Integer.MIN_VALUE)
				System.out.println("Ingrese numero valido (Numero entero)");
				}while(ingreseentero==Integer.MIN_VALUE);
				renglon.ingresararriba(ingreseentero);
				break;
		
		case 2:		renglon.indice();
				System.out.println("Introduzca un entero");
				do
				{
				ingreseentero=Leer.datoInt();
				if(ingreseentero==Integer.MIN_VALUE)
				System.out.println("Ingrese numero valido (Numero entero)");
				}while(ingreseentero==Integer.MIN_VALUE);
				renglon.ingresarabajo(ingreseentero);
				break;
						
		case 3:		System.out.println(renglon.sacararriba());
				System.out.println("Fue el numero entero eliminado por arriba");
				break;		
		
		case 4:		System.out.println(renglon.sacarabajo());
				System.out.println("Fue el numero entero eliminado por abajo");
				break;		

		case 5:		int lista[];
				int validos=renglon.elementosvalidos();
				lista=new int[validos]; 
				lista=renglon.lalista();
				System.out.println("La lista tiene "+validos+" enteros validos y son: \n");
				for(int h=0; h<=validos-1; h++)
				{
				System.out.println(lista[h]);			
				}
				break;
		
		case 6:		break;
	
		default:	System.out.println("Opcion no valida (entero entre 1 y 6)");		
				
		}
		}
		
		catch(Desborde e)
		{
		System.out.println(e.getMessage());
		}
		
		catch(Exception e)
		{
		}
		
		
		System.out.println("\n");

	}while(opcion!=6);

	}
	

}