/*ESTE ES EL CLIENTE DE LA CLASE PILA*/

import java.io.*;

class ClientePila
{
  public static void main(String args[])
	{
	int t;
    String dat;
    CEstDatPil pila=null;
    Archivo arch=new Archivo();

	System.out.println("MANEJO DE LA ESTRUCTURA DE DATOS PILA");
	System.out.println("MENU SUPER_PRINCIPAL");
	System.out.println("1. ABRIR UNA PILA DESDE UN ARCHIVO");
	System.out.println("2. CREAR UNA PILA NUEVA");
	System.out.print("\n DIGITE LA OPCIÓN: ");
	t=Leer.datoInt();
    
	if(t==1){
         			System.out.println("todos los cambios seran guardados(sobreescribiendo) en Pila.txt");
   pila = arch.recuperar("Pila.txt");
   			System.out.println("archivo recuperado es Pila.txt");
   }

	
	if(t==2) {
      			System.out.println("todos los datos ingresados y modificaciones sobre los mismos seran guardados en Pila.txt");
			System.out.println("DIGITE EL TAMAÑO DE LA PILA");
			t=Leer.datoInt();
			pila = new CEstDatPil(t);
			}
	
	do
	{
	System.out.println("MANEJO DE LA ESTRUCTURA DE DATOS PILA");
	System.out.println("MENU PRINCIPAL");
	System.out.println("1. INGRESAR ELEMENTO EN LA PILA");
	System.out.println("2. SACAR ELEMENTO DE LA PILA");
	System.out.println("3. MOSTRAR ELEMENTOS DE LA PILA");
	System.out.println("4. SALIR");
	System.out.print("\n DIGITE LA OPCIÓN: ");
	t=Leer.datoInt();

	switch(t)
		{
		case 1: 	
		{
		System.out.println("DIGITE EL ELEMENTO "+(pila.retornari()+2)+" DE LA PILA");//+2:por que empieza en -1 pa que entienda el usr
       	dat=Leer.dato();
		try
			{
				pila.ingresar(dat);
			}
			catch(Desborde d)
				{
				System.out.println(d.getMessage());
				}
		break;
		}
		case 2: 				
		try
		{
			System.out.print("EL ELEMENTO EN LA POSICION "+(pila.retornari()+1)+" SACADO DE LA PILA ES: ");
			System.out.println(pila.sacar());				
		}
		catch(Desborde d)
		{
			System.out.println(d.getMessage());
		}
		break;

		case 3: 
		pila.mostrar();
		break;
		}
	}
	while(t!=4);
	
	arch.guardar("Pila.txt",pila);
	
	
	System.out.println("FINALIZACIÓN DEL MANEJO DE LA ESTRUCTURA DE DATOS PILA");
	}
}