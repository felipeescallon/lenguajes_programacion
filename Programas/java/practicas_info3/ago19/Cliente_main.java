/*  PROGRAMA CREADO POR: FELIPE ESCALLON Y DIEGO MANQUILLO
 *  INFORMATICA 3
 *  JAVA 2!
 *  AGO/19/04
 */
//CONTIENE AL MAIN Y VA EN OTRO ARCHIVO

import java.lang.*;
import java.io.*;

//MANEJA AL RELOJ: FUNCIONANDO!!!
class Cliente_main
{

 public static void main(String args[])
 {
	int h=0,m=0,s=0,i=0;//COMPILADOR EXIGE INICIALIZACION


//entrar por teclado las horas
	do
	{
	if(i!=0)
	System.out.println("valor de las horas menor o igual que 23!!!");
	i=i+1;
	System.out.println("ingrese el valor de las horas:");
	h=Leer.datoInt();
   //valido las horas
    while(h<0)
    {
     System.out.println("ingrese el valor valido de las horas(0 a 23):");
     h=Leer.datoInt();
    }
   while(h==Integer.MIN_VALUE)//SI INGRESO CUALQUIER LETRA O ENTER:ver clase Leer
    {
    System.out.println("ingrese el valor valido de las horas(0 a 23):");
    h=Leer.datoInt();
    }
	}while(h>23);

	i=0;//PARA USARLO DESPUES!

  //entrar por teclado los min
	do
	{
	if(i!=0)
	System.out.println("valor de los minutos menor o igual que 59!!!");
	i=i+1;
	System.out.println("ingrese el valor de los minutos:");
	m=Leer.datoInt();
//VALIDO LOS minutos
  while(m<0)//NEGATIVOS
    {
     System.out.println("ingrese el valor valido de los minutos(0 a 59):");
     m=Leer.datoInt();
    }
   while(m==Integer.MIN_VALUE)//SI INGRESO CUALQUIER LETRA O ENTER:ver clase Leer
    {
    System.out.println("ingrese el valor valido de los minutos(0 a 59):");
    m=Leer.datoInt();
    }

	}while(m>59);

	i=0; //PARA USARLO DESPUES!

  //entrar por teclado los segs
	do
	{
	if(i!=0)
	System.out.println("valor de los segundos menor o igual que 59!!!");
	i=i+1;
	System.out.println("ingrese el valor de los segundos:");
	s=Leer.datoInt();
//VALIDO LOS SEGUNDOS
  while(s<0)//NEGATIVOS
    {
     System.out.println("ingrese el valor valido de los segundos(0 a 59):");
     s=Leer.datoInt();
    }
   while(s==Integer.MIN_VALUE)//SI INGRESO CUALQUIER LETRA O ENTER:ver clase Leer
    {
    System.out.println("ingrese el valor valido de los segundos(0 a 59):");
    s=Leer.datoInt();
    }

	}while(s>59);


	Reloj r;				//cramos el objeto de la clase reloj
	r = new Reloj();
	r.fijar_valor(h,m,s);			//fijamos los valores iniciales

	try
	{
	for(;;)					//ciclo "infinito"
	{
		r.mostrar();			//muestra la hora
		Thread.sleep(1000);		//retardo: SLEEP EN ms
		r.logica();			//movimiento del reloj

	}
	}
	catch(InterruptedException e)
	{
	}

 }
}