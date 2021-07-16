//Lista simplemente enlazada lineal

import java.lang.String;
import java.io.*;

public class Lista											//OJO CON MANEJO DE EXCEPCIONES
{
	public static void main(String[] args)
	{
	int opcion=0, opcion2=0, eleccion=0, opcion1=0, detector=0, nfilas=0, recortador=0; 
	String nombrearchivo=null, nombrearchivo1="copialista.txt",nombrenodo=null,identificador=null,ingresecadena=null,tipoestructura=null,encontrado=null, modificado=null;
	char responde='s', answer='s';
	EstDatos general=null;
	ClaseNodo teclado=null;
	
	ClaseLista l=new ClaseLista("Lista original"); 							//crea la cabecera de la lista
	ClaseLista lc=new ClaseLista("Lista copiada");
		
	ClaseArchivo lg=new ClaseArchivo();
	ClaseArchivo lcf=new ClaseArchivo();
	ClaseArchivo lf=new ClaseArchivo();
	
	
	System.out.println("\n\n\n");
	System.out.println("POR DEFECTO AL INICIAR LA APLICACION SE CREA UNA LISTA VACIA!!!");
   	
	do
	{
		
		System.out.print("\n\n");
		System.out.println("----------------------------------------------");
		System.out.println("M E N U  G E N E R A L \n");
		System.out.println("1. 	Crear de nuevo una lista vacia \n");
		System.out.println("2. 	Insertar y reemplazar nodo en la lista \n");
		System.out.println("3.	Modificar contenido del nodo \n");
		System.out.println("4. 	Eliminar nodo de la lista \n");//eliminar any node::ubicar ref
		System.out.println("5. 	Buscar o refenciar nodo \n");//SI HAY VARIOS REPETIDOS EL 1O QUE ENCUENTRE
    		System.out.println("6. 	Refenciar principio de la lista \n");//SI HAY VARIOS REPETIDOS EL 1O QUE ENCUENTRE
		System.out.println("7. 	Copiar lista \n");
		System.out.println("8. 	Invertir lista \n");
		System.out.println("9. 	Guardar lista \n");
		System.out.println("10. 	Guardar lista como \n");
		System.out.println("11. 	Imprimir lista \n");
 		System.out.println("12. 	Visualizar lista copiada \n");
		System.out.println("13. 	Recuperar lista anterior \n");//RECUPERAR SLOAMENTE SI NO HAY NODOS
		System.out.println("14.	Destruir lista \n");
		System.out.println("15. 	Salir");
		System.out.println("----------------------------------------------");

		opcion=Leer.datoInt();
		System.out.println("\n");

		try
		{
		switch(opcion)
		{
		case 1: 	if(l==null)
		        	{
		            	ClaseLista lnueva=new ClaseLista("Lista original"); 		//crea la cabecera de la lista
            			System.out.println("lista vacia nuevamente creada");
		            	l=lnueva;
	        		}
		        	else System.out.println("IMPOSIBLE:Ya existe una aplicacion en curso(no ha sido destruida)!!!");
            			break;

		case 2:		if(l!=null)							//existe lista vacia?
				{
				System.out.println("Insertar y reemplazar nodo \n");
				
				
				do
				{
				answer='s';
				System.out.println("\n");
				System.out.println("Digite el nombre del nodo: ");
				nombrenodo=Leer.dato();						//pa String
				teclado=l.buscar_lis1(nombrenodo);
				
				if(teclado!=null)
				{
					do
					{	
					System.out.println("\n");
					System.out.println("El nodo " +nombrenodo+ " ya existe, Desea reemplazarlo?: (s/n)");
					answer=Leer.caracter();	
					Leer.limpiar();
					if(answer!='s' && answer!='n')
						{
						System.out.println("\n");
						System.out.println("Opcion no valida (Caracter s o caracter n)");
						}
					}while(answer!='s' && answer!='n');
				}
				}while(answer=='n');

				
				do
				{
					System.out.print("\n\n");
					System.out.println("----------------------------------------------");
					System.out.println("S U B M E N U   \n");
					System.out.println("1. 	Crear una estructura Pila \n");
					System.out.println("2. 	Crear una estructura Cola \n");
					System.out.println("3.	Salir");
					System.out.println("----------------------------------------------");
									
					opcion2=Leer.datoInt();
					System.out.println("\n");
					
					switch(opcion2)
						{	
						
						case 1:		System.out.println("Ha decidido crear una estructura PILA");
								identificador="PILA";
								nfilas=tamanoestructura("PILA");
								general=new ClasePila(nfilas);
								menuestructura(general,"PILA");	
								break;	
						
						case 2:		System.out.println("Ha decidido crear una estructura COLA");
								identificador="COLA";
								nfilas=tamanoestructura("COLA");
								general=new ClaseCola(nfilas);
								menuestructura(general,"COLA");	
								break;							
												

						case 3:		break;	
						
						default:	System.out.println("Opcion no valida (entero entre 1 y 2)");
								break;	
						}
				}while(opcion2!=3 && opcion2!=2 && opcion2!=1);
				
				
				if(teclado==null && opcion2!=3)
					{
					l.innodo_lis(general,nombrenodo,identificador);
					}
				
				else
					{
					l.modificar_nodo(general,nombrenodo,identificador);
					}
				
				teclado=null;
				}
				
				else System.out.println("NO SE PUEDE INSERTAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

		
		case 3:		if(l!=null)
				{
				l.control9();
				System.out.println("Modificar contenido del nodo \n");
				System.out.println("Digite el nombre del nodo a modificar: ");
				modificado=Leer.dato();												
				System.out.println("\n");
				teclado=l.buscar_lis1(modificado);
				if(teclado==null)
					{
					System.out.println("No existe ningun nodo con el nombre " +modificado);
					}
				else
					{
					System.out.println("Nodo a modificar: "+teclado.retornar_nombre());
					System.out.println("Tipo " +teclado.retornar_tipo());
					general=teclado.retornar_puntero();
					menuestructura(general,teclado.retornar_tipo());
					l.modificar_nodo(general,teclado.retornar_nombre(),teclado.retornar_tipo());
					}						
				teclado=null;
				}			
				
				else System.out.println("NO SE PUEDE INSERTAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;


		case 4:		if(l!=null)								//existe lista vacia?
				{
				l.control();
				System.out.println("Eliminar nodo\n");
				teclado=l.elimnodo_lis(1);
				imprimirnodo(teclado,"Ha sido eliminado", "tenia", "eran: ");
				teclado=l.elimnodo_lis(0);//desreferenciar
				}

			        else System.out.println("NO SE PUEDE ELIMINAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

		
		case 5:		if(l!=null)								//existe lista vacia?
				{
				l.control3();
				System.out.println("Buscar y ubicar refencia \n");
				System.out.println("Digite el nombre del nodo a buscar y refenciar: ");
				encontrado=Leer.dato();
				System.out.println("\n");
				teclado=l.buscar_lis(encontrado);
				
				if(teclado==null)
					{
					System.out.println("No existe ningun nodo con el nombre " +encontrado);
					}
				
				else	{
					imprimirnodo(teclado, "Ha sido refenciado: ", " tiene ", " son: ");					
					}
				teclado=null;
				}
			        else System.out.println("NO SE PUEDE REFERENCIAR NODO YA QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;
	

  		case 6:		if(l!=null)//existe lista vacia?
				{
				l.ubicar_ref_inicio();
				System.out.println("La refencia ha sido ubicada en el principio de la lista");
				}
				else 	System.out.println("NO SE PUEDE REALIZAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
             			break;


		case 7:		if(l!=null)
				{
				ClaseLista copiada=null;
				System.out.println("Copiar lista");
				lc.ubicar_pri_inicio();
				nombrearchivo1=guardando(lg,l,1,nombrearchivo1);
				System.out.println("La lista original ha sido copiada!!!");
				copiada=lcf.mostrarFichero(nombrearchivo1);
				lc=copiada;
				detector=1;
				}
			        else System.out.println("NO SE PUEDE COPIAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;



		case 8:		if(l!=null)//existe lista vacia?
				{
				l.control5();
				System.out.println("Invertir lista");
				System.out.println("La lista original ha sido invertida!!!");
				l.invertir_lis();
				}
			        else System.out.println("NO SE PUEDE INVERTIR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;


		case 9:		if(l!=null)//existe lista vacia?
				{
				System.out.println("Guardar lista");
				eleccion=1;
				nombrearchivo=guardando(lg,l,eleccion,nombrearchivo);
				}
			        else System.out.println("NO SE PUEDE GUARDAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

		case 10:	if(l!=null)//existe lista vacia?
				{
				System.out.println("Guardar lista como");
				eleccion=0;
				nombrearchivo=guardando(lg,l,eleccion,nombrearchivo);
				}
			        else System.out.println("NO SE PUEDE REALIZAR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;


		case 11:	if(l!=null)//existe lista vacia?
				{
				l.control7();
				System.out.println("Imprimir lista \n");
				l.imprimir_lis();
				}
	
			        else System.out.println("NO SE PUEDE IMPRIMIR POR QUE NO HAY LISTA(nisiquiera vacia)! ");
				break;

		case 12: 	//NO NECESITA VERIFICACION
				if(detector==0)
				{
				lc.control1();
				}
				
				else
				{
				lc.control4();
				System.out.println("Visualizar lista copiada");
       				lc.imprimir_lis();
    				}
				break;


		case 13:	if(l!=null)//existe lista vacia?
				{
				l.control2();
				ClaseLista verificador=null;
				System.out.println("Recuperar lista anterior");

				try
				{
				        System.out.println("Digite el nombre del fichero a recuperar: ");
					nombrearchivo=Leer.dato();
					verificador=lf.mostrarFichero(nombrearchivo);
					
					
					if(verificador==null)
					{
					nombrearchivo=null;
					}	
				l=verificador;	
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

		case 14:  	System.out.println("Destruir Lista");
				if(l!=null)
		                {
                		l=null;//desreferencio la lista para que el garbage collector la destruya!
		                System.out.println("la lista ha sido destruida!");
                		}
                		else
		                {
                		System.out.println("la lista ya fue destruida!!!");
		                }
              
		              	//si en el menu despues de este le doy cualquier otro, simplemente no hace nada y
				break;


		case 15:	if(l!=null)
                		{
				try
				{
				l.control8();
				if(opcion1!=9 && opcion1!=10)		
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
						nombrearchivo=guardando(lg,l,eleccion,nombrearchivo);
						}	
	
						else 
						{
						eleccion=1;	
						nombrearchivo=guardando(lg,l,eleccion,nombrearchivo);
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

				

		default:	System.out.println("Opcion novalida (entero entre 1 y 15)");
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
		general=null;

	}while(opcion!=15);

	}


public static String guardando(ClaseArchivo i, ClaseLista j,int voto, String nombre)				//VOTO=SAVE OR SAVE AS

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

	i.guardar_lis(fichero,j);
	System.out.println("\n\n");
	System.out.println("LA LISTA HA SIDO GUARDADA CON EL NOMBRE:");
	System.out.println(nombreFichero);
	return nombreFichero;

	}

	else
	{
	fichero= new File(nombre);
	i.guardar_lis(fichero,j);
	System.out.println("\n\n");
	
	if(nombre!="copialista.txt")
	{
	System.out.println("LA LISTA HA SIDO GUARDADA EN: ");
	System.out.println(nombre);				
	}
	
	return nombre;
	}

	}


	catch(IOException e)
	{
	System.out.println("Error: " + e.getMessage());
	}

	return nombre;
	}



public static int tamanoestructura(String estructuracion)//GENERICO
	{
	int nfilas1=0; 

	System.out.println("\n");
	System.out.println("Digite el numero de filas que desea que tenga la "+estructuracion);
	
	do
	{
	nfilas1=Leer.datoInt();
	System.out.println("\n");
	if(nfilas1==Integer.MIN_VALUE || nfilas1<1)
	System.out.println("Ingrese un numero valido (entero mayor que cero)");	
	}while(nfilas1==Integer.MIN_VALUE || nfilas1<1);	
	
	return nfilas1;
	}


public static void menuestructura(EstDatos renglon, String tipo)
	{
	int opcion3=0;
	String ingresecadena;
	do
	{
		System.out.println("-----------------"+tipo+ "------------------");
		System.out.println("M E N U   E S T R U C T U R A");
		System.out.println("1. Introducir cadena de caracteres");
		System.out.println("2. Sacar cadena de caracteres");
		System.out.println("3. Mostrar " +tipo);
		System.out.println("4. Salir");
		System.out.println("--------------**********---------------");
		
		opcion3=Leer.datoInt();
		System.out.println("\n");
		
		try
		{
		switch(opcion3)
		{
		case 1:		renglon.indice(tipo);//VERIFRICAR ESTRUCTURA LLENA O VACIA
				System.out.println("Introduzca una cadena de caracteres");
				ingresecadena=Leer.dato();
				renglon.ingresarcadena(ingresecadena);
				break;
		
		case 2:		System.out.println(renglon.sacarcadena());
				System.out.println("Fue la cadena de caracteres eliminada");
				break;		
		
		case 3:		String matriz[];// AUX MATRIX
				int validos=renglon.elementosvalidos(tipo);
				matriz=new String[validos]; 
				matriz=renglon.lamatriz();
				System.out.println("La " +tipo+ " tinene "+validos+" cadenas validas y son: \n");
				for(int h=validos-1; h>=0; h--)//MOSTRAR FORMA VERTICAL
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

	}while(opcion3!=4);
	}

public static void imprimirnodo(ClaseNodo teclear, String cadena1, String cadena2, String cadena3)//GENERICO
	{
	EstDatos sarmiento;
	String matriz[];
	int validos;

	System.out.println(cadena1);
	System.out.println("Nodo: "+teclear.retornar_nombre());
	System.out.println("Tipo: "+teclear.retornar_tipo());
	sarmiento=teclear.retornar_puntero();
	
	try
		{
		validos=sarmiento.elementosvalidos(teclear.retornar_tipo());
		matriz=new String[validos]; 
		matriz=sarmiento.lamatriz();
		
		System.out.println("La " +teclear.retornar_tipo()+ " " +cadena2+ " " +validos+ " cadenas validas y " +cadena3+ "\n");
		
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

	}	
}


