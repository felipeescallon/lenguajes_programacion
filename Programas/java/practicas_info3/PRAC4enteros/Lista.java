//Lista simplemente enlazada lineal

import java.lang.*;
import java.io.*;

public class Lista//OJO CON MANEJO DE EXCEPCIONES
{
	public static void main(String[] args)
	{
	int opcion=0, value=0, eliminado=0, encontrado=0;	

	ClaseLista l=new ClaseLista("Lista original"); 		//crea la cabecera de la lista
		
	do
	{
		
		System.out.print("\n\n");
		System.out.println("----------------------------------------------");
		System.out.println("M E N U");
		System.out.println("1. Insertar nodo en la lista");
		System.out.println("2. Eliminar nodo de la lista");//eliminar any node::ubicar ref
		System.out.println("3. Copiar lista");
		System.out.println("4. Invertir lista");
		System.out.println("5. Guardar lista");
		System.out.println("6. IMPRIMIR lista");
 		System.out.println("7. visualizar lista copiada");
		System.out.println("8. Salir");
		System.out.println("----------------------------------------------");

		opcion=Leer.datoInt();
		System.out.println("\n");

		switch(opcion)
		{
		case 1:		System.out.println("Insertar nodo");
				System.out.println("Digite el contenido del nodo: ");
				value=Leer.datoInt();
				l.innodo_lis(value);
				break;

		case 2:		System.out.println("Eliminar nodo");
				eliminado=l.elimnodo_lis();
				System.out.println("el valor del nodo eliminado era de: " +eliminado);
				break;

		case 3:		System.out.println("La lista copiada es:");
				l.copiar_lis();
				break;


		case 4:		System.out.println("Invertir lista");
				System.out.println("La lista original ha sido invertida");
				l.invertir_lis();
				break;


		case 5:		System.out.println("Guardar lista");
				break;

		case 6:		System.out.println("imprimir");
				l.imprimir_lis();
				break;

    case 7: System.out.println("Visualizar lista copiada");
            l.visualizar_lisc();
            break;

		case 8:		break;

		default:	System.out.println("Opcion novalida (entero entre 1 y 8)");
				break;

		}

		System.out.println("\n");

	}while(opcion!=8);

	}

}


