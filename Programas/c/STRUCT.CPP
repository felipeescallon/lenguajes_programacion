/*estructuras*/
#include<stdio.h>
#include<conio.h>

struct tipo_s
{
int i;
float d;
char str[80];
}s;

void main(void)
{
clrscr();
printf("digite un # entero:");
scanf("%d",&s.i);
printf("\ndigite un # real:");
scanf("%f",&s.d);
printf("\nintroduzca una cadena:");
scanf("%s",&s.str);
printf("\n%d\n%f\n%s",s.i,s.d,s.str);

getch();
}

