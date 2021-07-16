#include <iostream>
#include <stdlib.h>
#include "funciones.h"
#include "calculadora.h"

int main(int argc, char *argv[])
{
    int opcion;
    float oper,r,x;
    //calculadora obj;
	funciones obj;
    opcion=obj.menu();
   // cout<<"es x "<<opcion;
  	switch (opcion)
	{
	   case 1:
    	      obj.datos();//cout<<minimo(euno,edos)<<endl;
    	      r=obj.a;
    	      cout<<"es x "<<r<<endl;
    	      x=obj.b;
    	      cout<<"es x "<<x<<endl;
    	      oper=obj.suma(r,x);//<<endl;//*///obj. Suma (r, x);
    	     // cout<<oper<<endl;
              break;
///////oper=obj.suma(r,x);
  	   /*//case 2:
	  // 	resultado = Resta (a, b);
	   //break;


	   case 3:
	   	//resultado = Multiplicacion (a, b);
	   break;


	   case 4:
	   	//resultado = Division (a, b);
       break;

       case 5:
	   	//resultado = Raiz_cuadrada (a);
 	   break;*/


	   }
  
  
  
  
  system("PAUSE");	
  return 0;
}
