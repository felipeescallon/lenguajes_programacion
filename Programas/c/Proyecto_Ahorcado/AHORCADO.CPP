#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <iostream.h>
//#include <time.h>
int d,i;
void main()  {
	do
	{
int i,Li,Resp=8,Com,A=0,g=40;
char Lib[15][11]={"CUADERNO", "COLOMBIA", "COCACOLA", "DISKETTE", "CARNAVAL",
		  "AHORCADO", "FABULOSO", "XILOFONO", "URUGUAYO", "PERDEDOR",
		  "VARIABLE", "TENTADOR", "ESPIRITU", "IMAGENES", "PRODUCTO",
		  };
char P;


	clrscr();
	printf("\n\n\n\t\tBIENVENIDO A JUGAR ESTE ENTRETENIDO JUEGO\n\n\n");
	printf("\n\n\n\n\n\n\n\n\n\n\n\tPRESIONE CUALQUIER TECLA PARA EMPEZAR A JUGAR............");
	getche();
	clrscr();
	printf("\n\t\t\t  ESTE ES EL JUEGO DEL HAORCADO\n");
	randomize();
	Li=random(15);
	gotoxy(40,8);
	printf("_ _ _ _ _ _ _ _ ");
	gotoxy(6,4);
	printf("__________");

	for (i=5 ;i<=11 ;i++)
{
	gotoxy(15,i);
	printf("|");
}
	gotoxy(6,5);
	printf("|");
	gotoxy(6,6);
	printf("|");
	gotoxy(5,12);
	printf("-------------------");

	gotoxy(3,15);
	printf("Vaya introduciendo las letras....:");
	do
{
	P=toupper(getch());
	Com=Resp;
	for(i=0 ;i<=9 ;i++)
	  if(P==Lib[Li][i])  {
	 // Lib[Li][i]='@';
	  gotoxy(40+2*i,8);
	  putchar(P);
	  Resp--;
	  }
	  g+=1;
	  gotoxy(g,15);
	  printf("%c",P);

	  if(Com==Resp) A++;

		switch(A)
{
	case 1: gotoxy(6,6);
		printf("0");
		break;
	case 2: gotoxy(6,7);
		printf("|");
		gotoxy(6,8);
		printf("|");
		break;
	case 3: gotoxy(5,7);
		printf("-");
		break;
	case 4: gotoxy(7,7);
		printf("-");
		break;
	case 5: gotoxy(5,9);
		printf("/");
		break;
	case 6: gotoxy(7,9);
		printf("\\");
		gotoxy(6,6);
		printf("|");
		gotoxy(6,7);
		printf("0");
		gotoxy(5,7);
		printf("/");
		gotoxy(7,7);
		printf("\\");
		break;
		}
}
	while(Resp>0 && A<6);
	   if(Resp==0)   {
	    gotoxy(47,23);
	    printf(" FELICIDADES!");
	    gotoxy(47,24);
	    printf("ME GANASTE....!");
	    getche();
	   }
	else
	{
	 gotoxy(47,23);
	 printf("   TU PERDISTE!");
	 gotoxy(47,24);
	 printf("YO SOY EL GANADOR!");
	 gotoxy(47,10);
	 for(i=0;i<=8;i++)
	 {
	 cout<<Lib[Li][i];
	 }
	  getche();
	}
	clrscr();
	gotoxy(23,8);
	printf("É");
	gotoxy(52,8);
	printf("»");
	gotoxy(23,15);
	printf("È");
	gotoxy(52,15);
	printf("¼");
	gotoxy(25,10);
	for(i=24;i<52;i++)
	{
	  gotoxy(i,8);
	  printf("Í");
	}
	for(i=24;i<52;i++)
	{
	  gotoxy(i,15);
	  printf("Í");
	}
	for(i=9;i<15;i++)
	{
	  gotoxy(23,i);
	  printf("º");
	}
	for(i=9;i<15;i++)
	{
	  gotoxy(52,i);
	  printf("º");
	}


	gotoxy(25,10);
	printf("Deseas volver a jugar?");
	gotoxy(34,11);
	printf("1. Si");
	gotoxy(34,12);
	printf("2. No");
	gotoxy(25,13);
	printf("->");
	scanf("%i",&d);

 }
while(d==1);
clrscr();
gotoxy(18,7);
printf("\\³³/");
gotoxy(17,8);
printf("³   @___00");
gotoxy(8,9);
printf("/\\  /\\  /  (__,,,,³");
gotoxy(7,10);
printf(") /^\\)^\\/  _)");
gotoxy(7,11);
printf(")    /^\\/  _)");
gotoxy(7,12);
printf(")   _  / / _)");
gotoxy(3,13);
printf("/\\  )/\\/ ³ ³ ³ )_)");
gotoxy(2,14);
printf("<  >      ³(,,) )__) ");
gotoxy(3,15);
printf("³³       /    \\)___)\\");
gotoxy(3,16);
printf("³ \\_____(      )___) )___");
gotoxy(4,17);
printf("\\_______(_______;;; __;;;");
gotoxy(35,5);
printf("É");
gotoxy(66,5);
printf("»");
gotoxy(35,18);
printf("È");
gotoxy(66,18);
printf("¼");

for(i=36;i<=65;i++)
{
gotoxy(i,5);
printf("Í");
}
for(i=36;i<=65;i++)
{
gotoxy(i,18);
printf("Í");
}
for(i=6;i<18;i++)
{
gotoxy(35,i);
printf("º");
}
for(i=6;i<18;i++)
{
gotoxy(66,i);
printf("º");
}
gotoxy(37,6);
printf(" ___    _          ___");
gotoxy(37,7);
printf("³      / \\  ³\\ /³ ³");
gotoxy(37,8);
printf("³___  ³___³ ³ V ³ ³__");
gotoxy(37,9);
printf("³   ³ ³   ³ ³   ³ ³");
gotoxy(37,10);
printf("³___³ ³   ³ ³   ³ ³___");
gotoxy(43,12);
printf(" ___         ___  ___");
gotoxy(43,13);
printf("³   ³ ³   ³ ³    ³   ³");
gotoxy(43,14);
printf("³   ³ ³   ³ ³__  ³___³");
gotoxy(43,15);
printf("³   ³  \\ /  ³    ³ \\");
gotoxy(43,16);
printf("³___³   V   ³___ ³  \\");
gotoxy(30,20);
printf("CREADO POR:");
gotoxy(29,21);
printf("HEIVAR ARMER©");
gotoxy(18,22);
printf("TODOS LOS DERECHOS RESERVADOS 2002");



}

