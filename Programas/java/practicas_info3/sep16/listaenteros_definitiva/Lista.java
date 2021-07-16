//Lista simplemente enlazada lineal

import java.lang.*;
import java.io.*;

public class Lista//OJO CON MANEJO DE EXCEPCIONES
{
	public static void main(String[] args)
	{
	int opcion=0, value=0, eliminado=0, encontrado=0, eleccion=0, opcion1=0;	
	String nombrearchivo=null, nombrearchivo1="copialista.txt" ;
	char responde='s';
	
	ClaseLista l=new ClaseLista("Lista original"); 		//crea la cabecera de la lista
	ClaseLista lc=new ClaseLista("Lista copiada");

	System.out.println("\n\n\n");
	  System.out.println("POR DEFECTO AL INICIAR LA APLICACION SE CREA UNA LISTA VACIA!!!");
   	

	//RECUPERAR LISTA GUARDADA CON UN ARCHIVO AUX QUE GUARDE EL NOM_ARCH QUE CONT LA LISTA

	do
	{
		
		System.out.print("\n\n");
		System.out.println("----------------------------------------------");
		System.out.println("M E N U \n");
		System.out.println("1. 	Crear de nuevo una lista vacia \n");
		System.out.println("2. 	Insertar nodo en la lista \n");
		System.out.println("3. 	Eliminar nodo de la lista \n");//eliminar any node::ubicar ref
		System.out.println("4. 	Buscar o refenciar nodo \n");//SI HAY VARIOS REPETIDOS EL 1O QUE ENCUENTRE
    		System.out.println("5. 	Refenciar principio de la lista \n");//SI HAY VARIOS REPETIDOS EL 1O QUE ENCUENTRE
		System.out.println("6. 	Copiar lista \n");
		System.out.println("7. 	Invertir lista \n");
		System.out.println("8. 	Guardar lista \n");
		System.out.println("9. 	Guardar lista como \n");
		System.out.println("10 	Imprimir lista \n");
 		System.out.println("11. 	Visualizar lista copiada \n");
		System.out.println("12. 	Recuperar lista anterior \n");//RECUPERAR SLOAMENTE SI NO HAY NODOS
		System.out.println("13. DESTRUIR LA LISTA \n");
		System.out.println("14. 	Salir");
		System.out.println("----------------------------------------------");

		opcion=Leer.datoInt();
		System.out.println("\n");

		try
		{
		switch(opcion)
		{
 case 1: if(l==null)
            {
            ClaseLista lnueva=new ClaseLista("Lista original"); 		//crea la cabecera de la lista
            System.out.println("lista vacia nuevamente creada");
            l=lnueva;
            }
            else System.out.println("IMPOSIBLE:Ya existe una aplicacion en curso(no ha sido destruida)!!!");
            break;

		case 2:		if(l!=null)//existe lista vacia?
				{
				System.out.println("Insertar nodo");
				System.out.println("Digite el contenido del nodo: ");
				do
				{
				value=Leer.datoInt();
				System.out.println("\n");
				if(value==Integer.MIN_VALUE)
				System.out.println("Ingrese un numero valido (numero entero)");	
				}while(value==Integer.MIN_VALUE);
				l.innodo_lis(value);
}
        else System.out.println("NO SE PUEDE INSERTAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
		
				break;

		case 3:		if(l!=null)//existe lista vacia?
				{
l.control();
				System.out.println("Eliminar nodo");
				eliminado=l.elimnodo_lis();
				System.out.println("el valor del nodo eliminado era de: " +eliminado);
}
        else System.out.println("NO SE PUEDE ELIMINAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

		case 4:		if(l!=null)//existe lista vacia?
				{
l.control3();
				System.out.println("Buscar y ubicar refencia");
				System.out.println("Digite el contenido del nodo a buscar y refenciar: ");
				do
				{
				value=Leer.datoInt();
				System.out.println("\n");
				if(value==Integer.MIN_VALUE)
				System.out.println("Ingrese un numero valido (numero entero)");	
				}while(value==Integer.MIN_VALUE);
				
				encontrado=l.buscar_lis(value);
				if(encontrado==0)
					{
					System.out.println("No existe ningun nodo con ese conetnido");
					}
				else	{
					System.out.println("El nodo "+encontrado+" tiene un valor de: "+value+ "y ha sido referenciado");
					}
}
        else System.out.println("NO SE PUEDE REFERENCIAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

  		case 5:		if(l!=null)//existe lista vacia?
				
					l.ubicar_ref_inicio();
				 else 	System.out.println("NO SE PUEDE REALIZAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
             			break;


		case 6:		if(l!=null)
				{
				l.control4();//SI NO HAY LISTA vacia NO PUEDE COPIAR
				System.out.println("Copiar lista");
				nombrearchivo1=guardando(l,1,nombrearchivo1);
				lc.mostrarFichero(nombrearchivo1);
				System.out.println("\n\n\n\n");
				System.out.println("La lista original ha sido copiada!!!");
}
        else System.out.println("NO SE PUEDE COPIAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");

				break;


		case 7:		if(l!=null)//existe lista vacia?
				{
l.control5();
				System.out.println("Invertir lista");
				System.out.println("La lista original ha sido invertida!!!");
				l.invertir_lis();
}
        else System.out.println("NO SE PUEDE INVERTIR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;


		case 8:		if(l!=null)//existe lista vacia?
				{
l.control6();
				System.out.println("Guardar lista");
				eleccion=1;
				nombrearchivo=guardando(l,eleccion,nombrearchivo);
}
        else System.out.println("NO SE PUEDE GUARDAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");

				break;

		case 9:		
				if(l!=null)//existe lista vacia?
				{
				l.control6();
				System.out.println("Guardar lista como");
				eleccion=0;
				nombrearchivo=guardando(l,eleccion,nombrearchivo);
}
        else System.out.println("NO SE PUEDE REALIZAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

		case 10:		
				if(l!=null)//existe lista vacia?
				{
				l.control7();
				System.out.println("Imprimir lista");
				l.imprimir_lis();
}
        else System.out.println("NO SE PUEDE IMPRIMIR POR QUE NO HAY LISTA(nisiquiera vacia)! ");

				break;

		case 11: 	//NO NECESITA VERIFICACION
				lc.control1();
				System.out.println("Visualizar lista copiada");
       				lc.imprimir_lis();
    				break;


		case 12: 	
				if(l!=null)//existe lista vacia?
				{
				l.control2();
				//int val=0;
				System.out.println("Recuperar lista anterior");

				try
				{
				        System.out.println("Digite el nombre del fichero a recuperar: ");
					nombrearchivo=Leer.dato();
					l.mostrarFichero(nombrearchivo);
					/*if(val==0)
					{
					nombrearchivo=null;
					}*/	
				}

				catch(IOException e)
 					  {
		                  	 System.out.println("Error:"+e.getMessage());
					  }


				}
        			else
        {
        System.out.println("NO SE PUEDE RECUPERAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
        System.out.println("PARA RECUPERAR : CREEE UNA LISTA VACIA PARA QUE SE PUEDA VISUALIZAR SEGUN LA LISTA QUE DESEE(digite 1) Y LUEGO RECUPERELA!");
        }
				break;

case 13:  	System.out.println("Destruir Lista");

		if(l!=null)
                {
                l=null;//desreferencio la lista para que el garbage collector la destruya!
                System.out.println("la lista ha sido destruida!");
                }
                else
                {
                System.out.println("la lista ya fue destruida!!!");
                //System.out.println("CUALQUIER OTRA PETICION (EXCEPTO 1.) NO REALIZARA OTRA COSA MAS QUE MOSTRAR NUEVAMENTE EL MENU!!");

                //l=null;
                }
              
              //si en el menu despues de este le doy cualquier otro, simplemente no hace nada y
		break;


		case 14:	
		if(l!=null)
                	{
				try
				{
				l.control8();
				if(opcion1!=7 && opcion1!=8)		//cambio!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				{
					do
					{
					System.out.println("Desea guardar los cambios hechos a la lista (s/n)");
					responde=Leer.caracter();
					Leer.limpiar();

					if(responde!='s' && responde!='n')
					{
					System.out.println("Opcion no valida (Caracter s o caracter n)");
					}
					}while(responde!='s' && responde!='n');
					
					if(responde=='s')
						{
						if(nombrearchivo==null)
						{
						eleccion=0;
						nombrearchivo=guardando(l,eleccion,nombrearchivo);
						}	
	
						else 
						{
						eleccion=1;	
						nombrearchivo=guardando(l,eleccion,nombrearchivo);
						}
				
					}
				}
				}
				
				catch(Desborde e)
				{
				System.out.println(e.getMessage());
				}
			}

				break;
				

		default:	System.out.println("Opcion novalida (entero entre 1 y 14)");
				break;

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
		opcion1=opcion;

	}while(opcion!=14);

	}


public static String guardando(ClaseLista j,int voto, String nombre)//VOTO=SAVE OR SAVE AS

	{

	String nombreFichero=null;//IMPÒRTANTE INITIATE
	File fichero;
	char respuest ='s';

	try
	{
	if(voto==0 || nombre==null)
	{
	do
	{
	respuest ='s';
	System.out.println("Escriba el nombre del fichero: ");
	nombreFichero=Leer.dato();
	fichero= new File(nombreFichero);

	if(fichero.exists())
	{
		do
		{
		System.out.println("El fichero ya existe ¿Desea reemplazarlo?: (s/n) ");
		respuest=Leer.caracter();
		Leer.limpiar();

		if(respuest!='s' && respuest!='n')
			{
			System.out.println("Opcion no valida (Caracter s o caracter n)");
			}
		}while(respuest!='s' && respuest!='n');
	}
	}while(respuest=='n');

	j.guardar_lis(fichero);
	System.out.println("\n\n");
	System.out.println("LA LISTA HA SIDO GUARDADA CON EL NOMBRE:");
	System.out.println(nombreFichero);
	return nombreFichero;

	}

	else
	{
	fichero= new File(nombre);
	j.guardar_lis(fichero);
	System.out.println("\n\n");
	System.out.println("LA LISTA HA SIDO GUARDADA EN: ");
	System.out.println(nombre);				//cambios!!!!!!!!!!!!!!!!!!!!!!!
	return nombre;
	}

	}


	catch(IOException e)
	{
	System.out.println("Error: " + e.getMessage());
	}

	return nombre;
	}

}


