//PROGRAMA QU RECIBE DOS DATOS ENTEROS POR TECLADO Y LOS SUMA!
//no tiene manejo de excepciones:OJO

import java.io.*;//importo todo el pack

class CLeer
{
 public static void main(String[] args)
	{
    int a,b,c;
    System.out.println("entre primer # entero:");
	  a=Leer.datoInt();
    System.out.println("entre segundo # entero:");
  	b=Leer.datoInt();
  	c=a+b;
    System.out.println("la suma de los dos enteros es:"+c);//+ es concatenador en este contexto

	}
}