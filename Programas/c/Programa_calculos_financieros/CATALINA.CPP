/*PROGRAMA QUE CALCULA EL SALARIO Y OTRAS COSAS DE 40 TRABAJADORES*/
/*CREADO POR FELIPE ESCALLON Y CATALINA PORTILLA: mayo /2002 */
//para funciones de entrada y salida
#include<stdio.h>
//para getch();
#include<conio.h>
//funci¢n principal
void main(void)
{
   int h_trabajod,h_trabajon,h_extrad,h_extran,c;
   int c_trab1,c_trab2,s_bruto;
   int pagoh_extrad,pagoh_extran,edad,pagoh_extrat,totalextras;
   float bonificacion10,bonificacion5,descuento,s_brutot,s_neto;
   float total_bonif,bonificaciont,total_des,total_sneto;
   //limpia pantalla
   clrscr();
   //i=i+1 es igual a i++

   c=1;
   c_trab1=0;    //inicializacion "global"
   c_trab2=0;

 while(c<=40)
 {
   printf("\nTRABAJADOR %d\n",c);
   printf("\nEscribir el n£mero de horas diurnas trabajadas:");
   scanf("%d",&h_trabajod);//&:para dar la direcci¢n de la variable
   fflush(stdin);//limpia buffer de entrada
   printf("\nEscribir el n£mero de horas nocturnas trabajadas:");
   scanf("%d",&h_trabajon);
   fflush(stdin);

   s_bruto=(h_trabajod*1000)+(h_trabajon*1250);

   h_extrad=h_trabajod-50;
   h_extran=h_trabajon-30;


     if(h_extrad<20)
     {
	 pagoh_extrad=h_extrad*1400;
     }

     if(h_extran<20)
     {
	 pagoh_extran=h_extran*1600;
     }

   pagoh_extrat=pagoh_extrad+pagoh_extran;

   printf("\nEscribir su edad en a¤os cumplidos(recuerde que esta debe ser inferior a 56 a¤os):");
   scanf("%d",&edad);
   fflush(stdin);


      //and=&&
      if(edad>40)
      {
	 c_trab1=c_trab1+1;
	 bonificacion10=s_bruto*0.1;

      }



      if((edad>35)&&(edad<40))
      {
	 c_trab2=c_trab2+1;
	 bonificacion5=s_bruto*0.05;

      }


   descuento=s_bruto*0.08;
   s_neto=s_bruto-(s_bruto*0.08);
   total_des=0;
   total_des=total_des+descuento;
   s_brutot=0;//inicializaci¢n para evitar errores
   s_brutot=s_brutot+s_bruto;//variable acumuladora
   totalextras=0;
   totalextras=totalextras+pagoh_extrat;
   bonificaciont=bonificacion10+bonificacion5;
   total_bonif=0;
   total_bonif=total_bonif+bonificaciont;
   total_sneto=0;
   total_sneto=total_sneto+s_neto;

   printf("\nEl salario neto del trabajador %d es: %f pesos",c,s_neto);
   c=c+1;

 }


   printf("\nEl total de los salarios brutos de todos los trabajadores es: %f pesos",s_brutot);
   printf("\nEl total por concepto de horas extras canceladas a todos los trabajadores es: %d pesos",totalextras);
   printf("\nEl n£mero de trabajadores mayor de 40 a¤os es: %d",c_trab1);
   printf("\nEl n£mero de trabajadores entre 35 y 40 a¤os es: %d",c_trab2);
   printf("\nEl total de bonificaciones canceladas es: %f pesos",total_bonif);
   printf("\nEl total de los descuentos es: %f pesos",total_des);
   printf("\nEl total de los salarios netos cancelados a todos los trabajadores es: %f pesos",total_sneto);


getch();

}


