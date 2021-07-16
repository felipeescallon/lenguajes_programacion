//Principal de la pila

import java.io.*;

public class Pila
{
	public static void main(String args[])
	{
	int nfilas, opcion;
	char ingresecadena[];
	
	System.out.println("\n");
	System.out.println("Digite el numero de filas que desea que tenga la pila");
	
	do
	{
	nfilas=Leer.datoInt();
	System.out.println("\n");
	if(nfilas==Integer.MIN_VALUE || nfilas<1)
	System.out.println("Ingrese un numero valido (entero mayor que cero)");	
	}while(nfilas==Integer.MIN_VALUE || nfilas<1);
	
	ClasePila renglon=new ClasePila(nfilas);	

	do
	{
		System.out.println("*****----------------------------*****");
		System.out.println("M E N U");
		System.out.println("1. Introducir cadena de caracteres");
		System.out.println("2. Sacar cadena de caracteres");
		System.out.println("3. Mostrar pila");
		System.out.println("4. Salir");
		System.out.println("*****-----------------------------*****");
		opcion=Leer.datoInt();
		System.out.println("\n");
		try
		{
		switch(opcion)
		{
		case 1:		renglon.indice();//verifica primero
				System.out.println("Introdusca una cadena de caracteres");
				ingresecadena=Leer.datoCadena();
				renglon.ingresarcadena(ingresecadena);
				break;

		case 2:		System.out.println(renglon.sacarcadena());
				System.out.println("Fue la cadena de caracteres eliminada");
				break;		
		
		//mostrar es una interfas=>debe ir en el main
		case 3:		char matriz[][];
				int validos=renglon.elementosvalidos();
				matriz=new char[validos][]; 
				matriz=renglon.lamatriz();
				System.out.println("La pila tinene "+validos+" cadenas validas y son: \n");
				for(int h=validos-1; h>=0; h--)//va de para abajo
				{
				System.out.println(matriz[h]);			
				}
				break;
		
		case 4:		break;
	
		default:	System.out.println("Opcion no valida (entero entre 1 y 4)");		
				
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

	}while(opcion!=4);

	}
	
}