/*CALCULA EL N-ESIMO TERMINO DE LA SERIE DE FIBONACCI*/
//Serie Fibonacci=1,1,2,3,5,8,13,21,34...
//FORMULA:el termino sgte se calcula a traves de la suma de los dos anteriores

#include<stdio.h>
#include<conio.h>

void main()
{
 int n,na=0,naa=0,v=0;
 clrscr();
 printf("Escriba el termino de la serie de Fibonacci que desea averiguar: ");
 scanf("%d",&n);
   if (n==1 || n==2)
   {
   printf("El valor es 1");
   }
    else
    {
    na=1;
    naa=1;
      for(int cont=2;cont<n;cont++)
      {
      v=na+naa;//formula de la serie
      /*asignaciones a los proximos valores*/
      naa=na;
      na=v;
      }
    n=v;
    printf("El valor es: %d",n);
    }

 getch();

 }

