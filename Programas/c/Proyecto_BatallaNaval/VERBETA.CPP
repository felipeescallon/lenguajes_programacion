#include <process.h>
#include "monitor.h"
#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include <iostream.h>
#include <string.h>
#include <graphics.h>
#include <dos.h>
#include <time.h>
#define PIXEL_COUNT 1000
#define DELAY_TIME  100

#define IMAGEN "a:\\DI.pcx"
#define IMAGEN1 "a:\\PRES.pcx"
#define IMAGEN2 "a:\\CIRKAL.pcx"
#define IMAGEN3 "a:\\VERSI.pcx"


#define ancho 60
#define alto 10

#ifndef __MOUSE_H
#define __MOUSE_H

#define MOUSE 0x33         // Interrupci�n de sistema para el mouse
#define TRUE  1
#define FALSE 0

#endif

//VARIABLES GLOBALES
typedef struct elementos{
			 int coorx,a;
			 int coory;
			 int coorx2;
			 int coory2;
			 float valor;
			 int tipo;
				 };
elementos entrada[50];
struct elementos leer[50];
struct elementos leer1[50];

union REGS reg,iReg,oReg;
int el = 0;
float valor=0;
int cc,x,y,*a,q=1, r = 1,f = 1,fc = 1,salida=0,r1,estado,boton,var;

//FUNCIONES   prototipos
int imagen(void);
int imagen1(void);
int imagen2(void);
int imagen3(void);
float  sel_voltaje();
float  corriente ();
void menu_resistencia();
float resequivalente();
void coordenadas();
void cierre(int,int,int);
void resistencia(int ,int ,int );
void fuente (int,int,int);
void fuente1(int,int,int);
void click1();
void unclick();
void mostrar_raton();
void ocultar_raton();
void inicializar_raton();
void estado_raton(int,int,int);
void menu_grafico(void);
void leerxymouse(int &x, int &y);
void dibujar ();
//****************************************

//FUNCIONES MOUSE

///////PROBAR BOTONES MOUSE
int mtest(void)
	{reg.x.ax = 0x0;
	int86(0x33, &reg, &reg);
	if (reg.x.ax > 0) return reg.x.bx;
	else return 0;}

/////////LEER CORDENADAS MOUSE
void leerxymouse(int &x, int &y){
union REGS iReg,oReg;
iReg.x.ax=0x03;
int86(0x33,&iReg,&oReg);
x=oReg.x.cx;
y=oReg.x.dx;}

//////////LEER ESTADO RATON
void estado_raton(int *ox,int *oy,int *boton)
	{iReg.x.ax=3;
	 int86(0x33,&iReg,&oReg);
	 *ox=oReg.x.cx;
	 *oy=oReg.x.dx;
	 *boton=oReg.x.bx;}

//////////LEE CLICK MOUSE
void click1()
	{do
	{estado_raton(&x,&y,&boton);
	}while(boton!=1);}

//////////LEE SOLTAR BOTON MOUSE
void unclick()
	{do
	{estado_raton(&x,&y,&boton);
	}while(boton!=0);
	}

/////////MUESTRA MOUSE EN PANTALLA
void mostrar_raton()
	{iReg.x.ax=1;
	 int86(0x33,&iReg,&oReg);}

/////////OCULTA MOSUE
void ocultar_raton()
	{iReg.x.ax=2;
	 int86(0x33,&iReg,&oReg);
	}

/////////INICIALIZA MOUSE
void inicializar_raton()
	{iReg.x.ax=0;
	 int86(0x33,&iReg,&oReg);
	}

//***********************************************************************


void main()
	{imagen();
	imagen1();
	imagen2();
	imagen3();

	int gmode=VGA, xmode=VGAHI, i;
	initgraph(&gmode,&xmode,"c:\\TC\\bgi");
	_setcursortype(_NOCURSOR);
	setcolor(0);
	for (i=0;i<480;i++)
	{line (0,i,640,i);}

	menu_grafico();
	}

//************************  menu plantilla inicial******************
void menu_grafico(){
	int i,j;
	float valor;
     inicializar_raton();
	setcolor(15);
	rectangle (10,1,630,459);//rectangulo principal
	line(10,70,630,70);//linea arriba
	line(81,1,81,70);//1ra linea vertical
	line(152,1,152,70);//2da linea vertical
	line(223,1,223,70);//3ra linea vertical
	line(294,1,294,70);//4ta linea vertical
	line(365,1,365,70);//5ta linea vertical
	line(436,1,436,70);//6ta linea vertical
	line(507,1,507,70);//7ma linea vertical
	line(578,1,578,70);//8va linea vertical

fuente(15,35,0);//dibujo fuente vertical
    f = 1;
fuente(115,7,1);//dibujo fuente horizontal
	f= 1;
resistencia(158,35,0);// dibujo resistencia horizontal
	r = 1;
resistencia(250,7,1);//dibujo resistencia vertical
	r = 1;
cierre(300,35,0);//dibujo linea horizontal de cierre
cierre(400,27,1);//dibujo linea vertical de cierre
fuente1(442,35,0);//dibujo fuente horizontal  de corriente
	fc = 1;
fuente1(540,7,1); //dibujo fuente vertical de corriente
	fc = 1;
////////////////////////////////////////////////////
	setfillstyle(1,0);
	 bar (450,350,630,459);
	setcolor(15);
	outtextxy(590,20,"DEL");
	line(579,35,630,35);
	outtextxy(590,50,"WAIT");
	rectangle(60,395,365,410);
	setfillstyle(1,0);
	bar (13,350,446,456);
	outtextxy(70,400,"ESCOJA LA OPCION DIBUJAR DEL MENU");

////////////////////////////////////////////////*************
	setfillstyle(1,0);
	bar (450,350,630,459);
	setcolor(15);
	line(579,35,630,35);
	line(11,350,450,350);
	rectangle (450,350,630,459);
	line(450,365,630,365); //primera linea horizontal
	line(450,412,630,412);    //segunda linea horizontal
	outtextxy(480,355,"I N I C I O");
	line(510,365,510,412);   //primera linea vertical
	line(570,365,570,412);    //segunda linea vertical
	outtextxy(466,381,"VOLT");
	outtextxy(512,381,"CURRENT");
	outtextxy(576,381,"RESIST");
	outtextxy (470,432,"DIBUJAR");
	outtextxy (545,432,"EXIT PROG");
	line(540,412,540,459);  //LINEA VERTICAL
	setcolor(10);
	rectangle(379,424,420,441);
	line(420,415,445,432);
	line(420,450,445,432);
	line(420,415,420,450);

////////////////////////////////////////////
//CUADRICULA
	for(j = 100; j <= 320; j+=10)
	{for(i =40; i <= 600; i+=10)
	{putpixel(i,j,10);}}

//VALIDACIONES UBICACION DE PUNTERO
mostrar_raton();
    do
    { click1();
    //VALIDACION ANTES DE DIBUJAR///////////////
    if ((x<578)&&(x>10)&&(y<70)&&(y>1)){
    setfillstyle(1,0);
    bar(14,351,447,456);
    setcolor(YELLOW);
    outtextxy(120,375,"ESCOJA LA OPCION DE DIBUJAR");
    outtextxy(170,388,"PARA INICIAR");
    setcolor(15) ;
    rectangle(60,368,365,402);
    setcolor(10);
	rectangle(379,424,420,441);
	line(420,415,445,432);
	line(420,450,445,432);
	line(420,415,420,450);

    }
       ////////////VALIDACION SALIR//////////////
   if ((x>541)&&(x<630)&&(y>413)&&(y<460))
   {  closegraph();
      exit (0);
      }
  ////////////VALIDACION DIBUJO//////////////
  if (x>451&&x<540&&y<460&&y>413)
   {      setcolor(4);
     setfillstyle(1,0);
     bar(14,351,447,456);
     menu_resistencia();
   }
//////////////////////////////////////////
   }while (2);
}


/////***********************************************************

//FUNCIONES DIBUJO CIRCUITO

////////////POLOS A TIERRA
void cierre(int x1,int y1, int opc)
	{setcolor(10);
ocultar_raton();
	if(opc == 1){
	line(x1,y1,x1,y1+20);
	line(x1-12,y1+20,x1+12,y1+20);
	line(x1-8,y1+25,x1+8,y1+25);
	line(x1-3,y1+30,x1+3,y1+30);
	}

	else{
	line(x1,y1,x1+60,y1);
	}

mostrar_raton();}


///////////DIBUJA RESISTENCIA
void resistencia(int x1,int y1, int opc)
	{
	char str[5];
	 ocultar_raton();
	 setcolor (15);

	if(opc == 1)
	{outtextxy(x1+18,y1+30,"R");
	outtextxy(x1+28,y1+30,itoa(r,str,10));
	setcolor(10);
	settextjustify(0,2);
	line(x1,y1,x1,y1+ancho/3);
	line(x1,y1+ancho*2/3,x1,y1+ancho);
	line(x1,y1+ancho/3,x1-10,y1+ancho/2.5);
	line(x1-10,y1+ancho/2.5,x1+10,y1+ancho/1.8);
	line(x1+10,y1+ancho/1.8,x1,y1+ancho*2/3);}

	else
	{settextjustify(1,1);
	outtextxy(x1+25,y1-16,"R");
	outtextxy(x1+35,y1-16,itoa(r,str,10));
	setcolor(10);
	line(x1,y1,x1+ancho/3,y1);
	line(x1+ancho*2/3,y1,x1+ancho,y1);
	line(x1+ancho/3,y1,x1+ancho/2.5,y1-10);
	line(x1+ancho/2.5,y1-10,x1+ancho/1.8,y1+10);
	line(x1+ancho/1.8,y1+10,x1+ancho*2/3,y1);}
	mostrar_raton();
		r++;}


//////////DIBUJA FUENTE VOLTAJE

void fuente(int x1,int y1, int opc)
	{	char str[5];
ocultar_raton();
	setcolor(15);
	if(opc == 1)
	{outtextxy(x1+20,y1+30,"V");
	outtextxy(x1+30,y1+30,itoa(f,str,10));
	setcolor(10);
	settextjustify(0,2);
	line(x1,y1,x1,y1+ancho/3);
	line(x1,y1+ancho*2/3,x1,y1+ancho);
	circle (x1,y1+ancho/2,ancho/6);}
	else
	{settextjustify(1,1);
	outtextxy(x1+25,y1-16,"V");
	outtextxy(x1+35,y1-16,itoa(f,str,10));
	setcolor(10);
	line(x1,y1,x1+ancho/3,y1);
	line(x1+ancho*2/3,y1,x1+ancho,y1);
	circle (x1+ancho/2,y1,ancho/6);}
f++;
mostrar_raton();
}

///////////DIBUJA FUENTE CORRIENTE

void fuente1(int x1,int y1, int opc)
	{	char str[5];
	 ocultar_raton();
	setcolor(15);
	if(opc == 1)
	{outtextxy(x1+18,y1+30,"I");
	outtextxy(x1+26,y1+30,itoa(fc,str,10));
	setcolor(10);
	settextjustify(0,2);
	line(x1,y1,x1,y1+ancho);
	circle (x1,y1+ancho/2,ancho/6);}

	else
	{settextjustify(1,1);
	outtextxy(x1+25,y1-16,"I");
	outtextxy(x1+35,y1-16,itoa(fc,str,10));
	setcolor(10);
	line(x1,y1,x1+ancho,y1);
	circle (x1+ancho/2,y1,ancho/6);}
mostrar_raton();
fc++;}

//////////*******************************************************


///////********FUNCIONES CALCULOS MATEMATICOS********************  //


//////RESISTENCIA EQUIVALENTE

float resequivalente()
	{int cont=0,i,j,aux2=0,aux3,aux4,aux5;
	float aux1=0,aux=0;

	for (i=0; i<=el; i++){
		if(entrada[i].tipo ==3)
		  {leer[cont].valor=entrada[i].valor;
		  leer[cont].coorx=entrada[i].coorx;
		  leer[cont].coory=entrada[i].coory;
		  leer[cont].tipo=entrada[i].tipo;
		  leer[cont].coorx2=entrada[i].coorx2;
		  leer[cont].coory2=entrada[i].coory2;}

		if(entrada[i].tipo==4)
		  {leer[cont].valor=entrada[i].valor;
		  leer[cont].coorx=entrada[i].coorx;
		  leer[cont].coory=entrada[i].coory;
		  leer[cont].tipo=entrada[i].tipo;
		  leer[cont].coorx2=entrada[i].coorx2;
		  leer[cont].coory2=entrada[i].coory2;}

		if (x>451&&x<519&&y<457&&y>413)
		  { closegraph();
		     exit (0);}

cont=cont+1;}

		for (i=0;i<=cont;i++)
		 {for (j=i+1;j<=cont;j++)
			{if (leer[i].coorx<leer[j].coorx)
			 {aux2=leer[j].coorx;
			 aux3=leer[j].coory;
			 aux4=leer[j].coorx2;
			 aux5=leer[j].coory2;

			 leer[j].coorx=leer[i].coorx;
			 leer[j].coory=leer[i].coory;
			 leer[j].coorx2=leer[i].coorx2;
			 leer[j].coory2=leer[i].coory2;

			 leer[i].coorx=aux2;
			 leer[i].coory=aux3;
			 leer[i].coorx=aux4;
			 leer[i].coory=aux5; }
			 }
		 }

	aux1=leer[0].valor;
	for(i=0;i<cont;i++)
	{if (((leer[i+1].coorx == leer[i].coorx)&&
	    (leer[i+1].coory == leer[i].coory)&&
	    (leer[i+1].coorx<leer[i].coorx2)&&
	    (leer[i+1].coory2>leer[i].coory2)) ||
	    ((leer[i+1].coory==(leer[i].coory2-60))&&
	    (leer[i+1].coory2==leer[i].coory+60))){

	    aux=((aux1*(leer[i+1].valor))/(aux1+(leer[i+1].valor)));
	    aux1=aux;

	    leer[i+1].coorx=leer[i].coorx;
	    leer[i+1].coorx2=leer[i].coorx2;
	    leer[i+1].coory=leer[i].coory;
	    leer[i+1].coory2=leer[i].coory2;
	}

	if (((leer[i+1].coorx<leer[i].coorx)&&
	   (leer[i+1].coory==leer[i].coory)&&
	   (leer[i+1].coorx2<=leer[i].coorx2)&&
	   (leer[i+1].coory2==leer[i].coory2))  ||
	   ((leer[i+1].coorx<leer[i].coorx)&&
	   (leer[i+1].coory==leer[i].coory)&&
	   (leer[i+1].coorx2<=leer[i].coorx2)&&
	   (leer[i+1].coory2==(leer[i].coory2)-60))) {

	     aux=aux1+leer[i+1].valor;
	     aux1=aux;

	    leer[i+1].coorx=leer[i].coorx;
	    leer[i+1].coorx2=leer[i].coorx2;
	    leer[i+1].coory=leer[i].coory;
	    leer[i+1].coory2=leer[i].coory2;}
	   }
return (aux1);
       }


/////////////////MENU OPCIONES PRINCIPALES
 /////////********************VALIDACION MENU RESISTENCIA******/////////
void menu_resistencia()

	{
	ocultar_raton();
	setfillstyle(1,0);
	bar (450,350,630,459);
	setcolor(15);
	line(579,35,630,35);
	line(11,350,450,350);
	rectangle (450,350,630,459);
	line(450,365,630,365); //primera linea horizontal
	line(450,412,630,412);    //segunda linea horizontal
	outtextxy(480,355,"C A L C U L O S");
	line(510,365,510,412);   //primera linea vertical
	line(570,365,570,412);    //segunda linea vertical
	outtextxy(456,381,"VOLT.");
	outtextxy(512,381,"CORRIE.");
	outtextxy(576,381,"RESIST.");
	rectangle(80,375,330,390);
	outtextxy (470,432,"RETORNO");
	line(540,412,540,459);  //LINEA VERTICAL
	setcolor(10);
	rectangle(379,374,420,391);
	line(420,365,445,382);
	line(420,400,445,382);
	line(420,365,420,400);
mostrar_raton();
click1();

////////////VALIDACION DIBUJO//////////////
       if (x>451&&x<540&&y<460&&y>413)
	   {
	     setfillstyle(1,0);
	      bar(14,351,447,456);
	    dibujar();
	       }       }

 ////////////////////MENU GRAFICO 2

 void dibujar()
	{int i,j;
	 float valor,total,value=0,intensidad=0;
   inicializar_raton();
   mostrar_raton();
	setfillstyle(1,0);
	bar(12,351,447,457);
	 outtextxy(95,390,"D I B U J E   S U   C I R C U I T O");
	 setcolor(15);

   do
	{click1();

	     if ((x<630)&&(x>578)&&(y<34)&&(y>1))
		 {
		   r=1;f=1;fc=1;
		 for(i=0;i<=el;i++){

		    entrada[i].valor=0;
		    entrada[i].coorx=0;
		    entrada[i].coory=0;
		    entrada[i].tipo=0;
		    entrada[i].coorx2=0;
		    entrada[i].coory2=0;

		    leer[i].valor=0;
		    leer[i].coorx=0;
		    leer[i].coory=0;
		    leer[i].tipo=0;
		    leer[i].coorx2=0;
		    leer[i].coory2=0;

		    leer1[i].valor=0;
		    leer1[i].coorx=0;
		    leer1[i].coory=0;
		    leer1[i].tipo=0;
		    leer1[i].coorx2=0;
		    leer1[i].coory2=0;
		  }
		  setfillstyle(1,0);
		  bar(11,71,629,347);
		   setcolor(10);
		   for(j = 100; j <= 320; j+=10)
		   {
		   for(i =40; i <= 600; i+=10)
		    {
		    putpixel(i,j,10);}}
		    el=0;
		  setfillstyle(1,0);
		  bar(12,351,447,457);
		  outtextxy(95,390,"D I B U J E   S U   C I R C U I T O");
		  }


	   if ((x<81)&&(x>10)&&(y<70)&&(y>1))
		{unclick();
		  if(x>20 && y > 80 && x <= 520 && y < 320)
		  {setcolor(4);
		  fuente(x+(0.9-(x%10)),y+(0.9-(y%10)),0); //fuente voltaje serie
		  setfillstyle(1,0);bar(14,351,447,456);
		  gotoxy(10,25);
		  printf("Ingrese el valor de la fuente:  ");
		  scanf("%f",&valor);
		  setfillstyle(1,0);bar(14,351,447,456);
		  entrada[el].coorx = (x+(0.9-(x%10)));
		  entrada[el].coory = (y+(0.9-(y%10)));
		  entrada[el].coorx2 = (x+(0.9-(x%10))+60);
		  entrada[el].coory2 = (y+(0.9-(y%10)));
		  entrada[el].valor=valor;
		  entrada[el].tipo = 1;
		el++;}
		}

	 if ((x<152)&&(x>82)&&(y<70)&&(y> 1))
		{unclick();
		  if(x>20 && y > 80 && x <= 580 && y <= 260)//fuente voltaje paralelo
		  {setcolor(1);
		  fuente(x+(0.9-(x%10)),y+(0.9-(x%10)),1);
		  setfillstyle(1,0);bar(14,351,447,456);
		  gotoxy(10,25);
		  printf("Ingrese el valor de la fuente:  ");
		  scanf("%f",&valor);
		  setfillstyle(1,0);bar(14,351,447,456);
		  entrada[el].valor=valor;
		  entrada[el].tipo = 2;
		  entrada[el].coorx = (x+(0.9-(x%10)));
		  entrada[el].coory = (y+(0.9-(x%10)));
		  entrada[el].coorx2 = (x+(0.9-(x%10)));
		  entrada[el].coory2 = (y+(0.9-(x%10))+60);
		el++;}
		 }

	 if ((x<223)&&(x>153)&&(y<70)&&(y> 1))
		{unclick();
		  if(x>20 && y > 80 && x <= 520 && y < 320)    //resistencia serie
		  {setcolor(15);
			resistencia(x+(0.9-(x%10)),y+(0.9-(y%10)),0);
			setfillstyle(1,0);bar(14,351,447,456);
			gotoxy(10,25);
			printf("Ingrese el valor de la resistencia:  ");
			scanf("%f",&valor);
			fflush(stdin);
			setfillstyle(1,0);bar(14,351,447,456);
			entrada[el].valor=valor;
			entrada[el].tipo = 3;
			entrada[el].coorx = (x+(0.9-(x%10)));
			entrada[el].coory = (y+(0.9-(y%10)));
			entrada[el].coorx2 = (x+(0.9-(x%10))+60);
			entrada[el].coory2 = (y+(0.9-(y%10)));
			el++;}
			}


	 if ((x<294)&&(x>224)&&(y<70)&&(y> 1))
		{unclick();
		  if(x>20 && y > 80 && x <= 580 && y <= 260)  //resistencia paralelo
		  {setcolor(10);
			resistencia(x+(0.9-(x%10)),y+(0.9-(y%10)),1);
			setfillstyle(1,0);
			bar(14,351,447,456);
			gotoxy(10,25);
			printf("Ingrese el valor de la resistencia:  ");
			scanf("%f",&valor);
			setfillstyle(1,0);bar(14,351,447,456);
			entrada[el].valor = valor;
			entrada[el].tipo= 4;
			entrada[el].coorx = x+(0.9-(x%10));
			entrada[el].coory = (y+(0.9-(y%10)));
			entrada[el].coorx2 = x+(0.9-(x%10));
			entrada[el].coory2 = (y+(0.9-(y%10)))+60;
			el++;}
			 }
	  ///DIBUJA LA LINEA ***********************************
	 if ((x<365)&&(x>295)&&(y<70)&&(y>1 ))
		{unclick();
		  if(x>20 && y>80 && x<= 560 && y < 320)
			{cierre(x+(0.9-(x%10)),y+(0.9-(y%10)),0);
			entrada[el].coorx = (x+(0.9-(x%10)));
			entrada[el].coory = (y+(0.9-(y%10)));
			el++;}
		      }
   //DIBUJA EL POLO A TIERRA******************************
	 if ((x<436)&&(x>366)&&(y<70)&&(y> 1))
		{unclick();
		  if(x > 20 && y > 80 && x <= 580 && y < 300)
			{cierre(x+(0.9-(x%10)),y+(0.9-(y%10)),1);
			entrada[el].coorx = (x+(0.9-(x%10)));
			entrada[el].coory = (y+(0.9-(y%10)));
			el++;}
			}
	  ///DIBUJA FUENTE DE CORRIENTE EN SERIE
	 if ((x<507)&&(x>437)&&(y<70)&&(y> 1))
		{unclick();
		  if(x>20 && y > 80 && x <= 520 && y < 320)
		  {setcolor(15);
			fuente1(x+(0.9-(x%10)),y+(0.9-(y%10)),0);
			setfillstyle(1,0);bar(14,351,447,456);
			gotoxy(10,25);
			printf("Ingrese el valor de la corriente:  ");
			scanf("%f",&valor);
			setfillstyle(1,0);bar(14,351,447,456);
			entrada[el].valor = valor;
			entrada[el].tipo = 5;
			entrada[el].coorx = x+(0.9-(x%10));
			entrada[el].coory = y+(0.9-(y%10));
			entrada[el].coorx2 = ((0.9-(x%10))+x)+60;
			entrada[el].coory2 = y+(0.9-(y%10));
			el++;}
			}

	 ////////DIBUJA FUENTE DE CORRIENTE EN PARALELO
	 if ((x<578)&&(x>508)&&(y<70)&&(y> 1))
		{unclick();
		  if(x>20 && y > 80 && x <= 580 && y <= 260)
		  {setcolor(10);
			fuente1(x+(0.9-(x%10)),y+(0.9-(y%10)),1);
			setfillstyle(1,0);bar(14,351,447,456);
			gotoxy(10,25);
			printf("Ingrese el valor de la corriente:  ");
			scanf("%f",&valor);
			setfillstyle(1,0);bar(14,351,447,456);
			entrada[el].valor = valor;
			entrada[el].tipo = 6;
			entrada[el].coorx = (x+(0.9-(x%10)));
			entrada[el].coory = (y+(0.9-(y%10)));
			entrada[el].coorx2 = (x+(0.9-(x%10)));
			entrada[el].coory2 = (y+(0.9-(y%10)))+60;
			el++;}
			}

		if ((x>571)&&(x<630)&&(y>366)&&(y<410)){
		     total=resequivalente();
		    setfillstyle(1,0);
		     bar(12,351,447,457);
		outtextxy(20,370,"La Resisistencia Equivalente es:");
		 gotoxy(45,24);
		printf("%0.3f",total);
		  }
///////////calcular intensidad/////////////////////

		 if ((x<569)&&(x>511)&&(y>366)&&(y<410)){

		       if (corriente()!=0){
			   setfillstyle(1,0);
			    bar(12,351,447,457);
			    outtextxy(20,370,"La corriente del circuito es:");
			    gotoxy(45,24);
			    printf("%0.3f",corriente());
			  }
		       else
		       {
			  value=sel_voltaje();
			  intensidad=value/resequivalente();
			  setfillstyle(1,0);
			  bar(12,351,447,457);
		      outtextxy(20,370,"La Corriente del Circuito es:");
		      gotoxy(45,24);
		      printf("%0.3f",intensidad);      }
			  }

 ///////////////////CALCULAR VOLTAJE/////////

		     if ((x>450)&&(x<509)&&(y>366)&&(y<410)){
		       if (sel_voltaje()!=0){
			   setfillstyle(1,0);
			    bar(12,351,447,457);
			    outtextxy(20,370,"El Voltaje del circuito es:");
			    gotoxy(45,24);
			    printf("%0.3f",sel_voltaje());
			  }
		       else
		       {
			 value=corriente()*resequivalente();
			 setfillstyle(1,0);
			 bar(12,351,447,457);
			 outtextxy(20,370,"El Voltaje del circuito es:");
			 gotoxy(45,24);
			 printf("%0.3f",value);  }
		     }

	   if (x>451&&x<519&&y<457&&y>413)
		  {menu_grafico();}

		     }
      while (2);
     }


//////////////FUNCION CALCULO CORRIENTE

 float  corriente()
  {float intens=0;
   int i=0,cont=0;

   for (i=0; i<=el; i++){
	if(entrada[i].tipo ==5)
	  {leer1[cont].valor=entrada[i].valor;
	  intens= leer1[cont].valor;}

	if(entrada[i].tipo==6)
	  {leer1[cont].valor=entrada[i].valor;
	  intens= leer1[cont].valor;}

  cont=cont+1;}
 return(intens);
}



////////////FUNCION CALCULO VOLTAJE
 float  sel_voltaje()
     {float volta=0,suma=0;
     int i=0,cont=0;

   for (i=0; i<=el; i++){
	if((entrada[i].tipo ==1)|| (entrada[i].tipo==2))
	  {leer1[cont].valor=entrada[i].valor;
	  leer1[cont].tipo=entrada[i].tipo;
	  volta= leer1[cont].valor;}
	  cont=cont+1;
	  }
   suma=suma+volta;
  return(suma);
}

int imagen(void)
{
	int r;
	t_paleta pal;
	BYTE *image;

	r=CargaPcx(IMAGEN, pal, &image);
	if(r!=1)
	{
		switch(r)
		{
			case  0: printf("\nNo se encontro el archivo %s\n", IMAGEN); break;
			case -1: printf("\nEl archivo %s no es ZSOFT\n", IMAGEN); break;
			case -2: printf("\nEl tama�o de la imagen es muy grande\n"); break;
			case -3: printf("\nMemoria insuficiente al cargar la imagen\n"); break;
		}
		exit(1);
	}

	clrscr();
	SetMode(GRAFICO);

	DelPal(0);
	PutImageCenter(image, DIR_VGA);
	FadeIn(pal);
	delay(1000);
       //	getch();
	FadeOut();
	SetMode(TEXTO);
	free(image);

	return 0;
}

int imagen1(void)
{
	int r;
	t_paleta pal;
	BYTE *image;

	r=CargaPcx(IMAGEN1, pal, &image);
	if(r!=1)
	{
		switch(r)
		{
			case  0: printf("\nNo se encontro el archivo %s\n", IMAGEN); break;
			case -1: printf("\nEl archivo %s no es ZSOFT\n", IMAGEN); break;
			case -2: printf("\nEl tama�o de la imagen es muy grande\n"); break;
			case -3: printf("\nMemoria insuficiente al cargar la imagen\n"); break;
		}
		exit(1);
	}

	clrscr();


	SetMode(GRAFICO);

	DelPal(0);
	PutImageCenter(image, DIR_VGA);
	FadeIn(pal);
       delay(1000);
       //	getch();
	FadeOut();
	SetMode(TEXTO);
	free(image);

	return 0;
}

int imagen2(void)
{
	int r;
	t_paleta pal;
	BYTE *image;

	r=CargaPcx(IMAGEN2, pal, &image);
	if(r!=1)
	{
		switch(r)
		{
			case  0: printf("\nNo se encontro el archivo %s\n", IMAGEN); break;
			case -1: printf("\nEl archivo %s no es ZSOFT\n", IMAGEN); break;
			case -2: printf("\nEl tama�o de la imagen es muy grande\n"); break;
			case -3: printf("\nMemoria insuficiente al cargar la imagen\n"); break;
		}
		exit(1);
	}

	clrscr();
	SetMode(GRAFICO);

	DelPal(0);
	PutImageCenter(image, DIR_VGA);
	FadeIn(pal);
	 delay(1000);
       //	getch();
	FadeOut();
	SetMode(TEXTO);
	free(image);

	return 0;
}



int imagen3(void)
{
	int r;
	t_paleta pal;
	BYTE *image;

	r=CargaPcx(IMAGEN3, pal, &image);
	if(r!=1)
	{
		switch(r)
		{
			case  0: printf("\nNo se encontro el archivo %s\n", IMAGEN); break;
			case -1: printf("\nEl archivo %s no es ZSOFT\n", IMAGEN); break;
			case -2: printf("\nEl tama�o de la imagen es muy grande\n"); break;
			case -3: printf("\nMemoria insuficiente al cargar la imagen\n"); break;
		}
		exit(1);
	}

	clrscr();
	SetMode(GRAFICO);

	DelPal(0);
	PutImageCenter(image, DIR_VGA);
	FadeIn(pal);

	delay(1000);
       //	getch();
	FadeOut();
	SetMode(TEXTO);
	free(image);

	return 0;
}
