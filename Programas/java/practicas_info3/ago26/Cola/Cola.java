//Principal de la cola

import java.io.*;

public class Cola
{
	public static void main(String args[])
	{
	int nfilas, opcion;
	String ingresecadena;
	
	System.out.println("\n");
	System.out.println("Digite el numero de filas que desea que tenga la cola");
	
	do
	{
	nfilas=Leer.datoInt();
	System.out.println("\n");
	if(nfilas==Integer.MIN_VALUE || nfilas<1)
	System.out.println("Ingrese un numero valido (entero mayor que cero)");	
	}while(nfilas==Integer.MIN_VALUE || nfilas<1);
	
	ClaseCola renglon=new ClaseCola(nfilas);	

	do
	{
		System.out.println("--------------**********----------------");
		System.out.println("M E N U");
		System.out.println("1. Introducir cadena de caracteres");
		System.out.println("2. Sacar cadena de caracteres");
		System.out.println("3. Mostrar cola");
		System.out.println("4. Salir");
		System.out.println("--------------**********---------------");
		opcion=Leer.datoInt();
		System.out.println("\n");
		try
		{
		switch(opcion)
		{
		case 1:		renglon.indice();
				System.out.println("Introdusca una cadena de caracteres");
				ingresecadena=Leer.dato();
				renglon.ingresarcadena(ingresecadena);
				break;
		case 2:		System.out.println(renglon.sacarcadena());
				System.out.println("Fue la cadena de caracteres eliminada");
				break;		
		
		case 3:		String matriz[];
				int validos=renglon.elementosvalidos();
				matriz=new String[validos]; 
				matriz=renglon.lamatriz();
				System.out.println("La cola tinene "+validos+" cadenas validas y son: \n");
				for(int h=validos-1; h>=0; h--)
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