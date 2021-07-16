/*
-----------------------------------------------------------------------
|		                 PROYECTO FINAL DE INFORMATICA I                  |
|                                                                     |
|                  Presentado a:   Ing. Martin Bravo                  |
|			                   UNIVERSIDAD DEL CAUCA                        |
|		                Adriana Mar¡a Gustin Rebolledo                    |
|                  Andr‚s Felipe Escall¢n Portilla                    |
|                   Dar¡o Fernando Gust¡n Insuasty                    |
|                    Javier Fernando Imb£s Guzm n                     |
|                     TUTOR BASICO DE CIRCUITOS                       |
-----------------------------------------------------------------------
*/
//----------------------------------------------------------------------

//Declaracion de librerias
#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
#include<graphics.h>

//----------------------------------------------------------------------

//----------------------------------------------------------------------
FILE *fp;     //declaro archivo en forma global
int incy=0;                  //globalmente
int gdriver = DETECT, gmode;

//Declaracion de Funciones

void portada(void);

//Funciones principales para dibujar

void resistencia_vertical(int x,int y);
void resistencia_horizontal(int x,int y);
void fuente_corriente(int x,int y,int tipo);
void fuente_voltaje(int x, int y, int tipo);

//Funciones compuestas de las anteriores para dibujar circuitos

void pinta_dibujo(int x, int y);
void pinta_dibujo1(int x, int y);
void pinta_dibujo2(int x, int y);
void pinta_dibujo3(int x, int y);
void pinta_dibujo4(int x, int y);
void pinta_dibujo5(int x, int y);
void pinta_dibujo6(int x, int y);
void pinta_dibujo7(int x, int y);
void pinta_dibujo8(int x, int y);
void pinta_dibujo9(int x, int y);
void pinta_dibujo10(int x, int y);
void pinta_dibujo15(int x, int y);
void pinta_dibujo16(int x, int y);
void pinta_dibujo17(int x, int y);
void pinta_dibujo18(int x, int y);
void pinta_dibujo19(int x, int y);
void pinta_dibujo20(int x, int y);
void pinta_dibujo21(int x, int y);
void pinta_dibujo22(int x, int y);
void pita_dibu(int x, int y);
void pinta_dib2(int x, int y);
void pinta_dib4(int x, int y);
void pinta_dib5(int x, int y);
void pinta_dib6(int x, int y);
void pinta_dib55(int x, int y);
void pinta_dib77(int x, int y);
void r_en_serie(int x,int y);
void voltaje_kirchhoff(int x, int y);
void pinta_dibujo(int x, int y);
void r_en_serie(int x,int y);
void r_en_paralelo(int x, int y);

//Funciones para llamar archivos

void cargar_cap_uno1();
void cargar_cap_uno2();
void cargar_cap_uno3();
void cargar_cap_uno4();
void cargar_cap_uno5();
void cargar_cap_uno6();
void cargar_cap_uno7();
void cargar_teo1();
void cargar_teo2();
void cargar_teo3();
void cargar_teo4();
void cargar_teo5();
void cargar_teo6();
void cargar_teo7();
void cargar_teo8();
void cargar_teo9();
void cargar_teo10();
void cargar_teo11();
void cargar_teo12();
void cargar_teo13();
void cargar_teo14();
void cargar_teo15();
void cargar_teo16();
void cargar_teo77();


// Funciones que muestran el texto de los menus
	void inicio (void);
	void menu_principal();
	void cap_1 (void);
	void cap_2 (void);
	void cap_3 (void);
	void cap_4 (void);

// Funciones que le permiten elegir al usuario lo que desea ver
	void elegir_a ();
	void elegir_cap1 ();
	void elegir_cap2 ();
	void elegir_cap3 ();
	void elegir_cap4 ();

//Funcion para usar modo gr fico
	void modo_grafico();

//Funcion que muestra un cuadro para seguir en el programa
//regresando al menu principal o para salir

	void continuar();
//----------------------------------------------------------------------

//----------------------------------------------------------------------
//Principal
	void main(void)
	{


	portada();           //Llamado a funciones
	menu_principal();
	closegraph();//cierra modo grafico para evitar errores
	continuar();
	}


  void modo_grafico()
  {
	initgraph(&gdriver, &gmode, "c:\\TC\\BGI"); //Inicializaci¢n de modo gr fico
	setviewport(40,40,600,420,1);//delimito pantalla
  }


  void resistencia_vertical(int x,int y)//dibuja resistencia vertical
  {
	setcolor(RED);
	line(x,y,x,y-5);
	line(x,y-5,x-10,y-10);
	line(x-10,y-10,x,y-15);
	line(x,y-15,x-10,y-20);
	line(x-10,y-20,x,y-25);
	line(x,y-25,x-10,y-30);
	line(x-10,y-30,x,y-35);
	line(x,y-35,x,y-40);
	setcolor(YELLOW);
  }


  void resistencia_horizontal(int x,int y)  //crea resistencia horizontal
  {
	setcolor(RED);
	y-=10;
	line(x-5,y+10,x,y+10);
	line(x-5,y+10,x-10,y);
	line(x-10,y,x-15,y+10);
	line(x-15,y+10,x-20,y);
	line(x-20,y,x-25,y+10);
	line(x-25,y+10,x-30,y);
	line(x-30,y,x-35,y+10);
	line(x-35,y+10,x-40,y+10);
	setcolor(YELLOW);
  }


  void fuente_voltaje(int x, int y, int tipo) //crea fuente de voltaje
										 // (coordenadas) (tipo)
	{
	setcolor(LIGHTBLUE);
	circle(x,y-25,10);
	switch (tipo)
	{
		case 0:        //vertical hacia abajo
			line(x,y-15,x,y);
			line(x,y-35,x,y-50);
			outtextxy(x-8,y-12,"+");
			outtextxy(x-8,y-43,"-");
		break;
    case 1:        //vertical hacia arriba
      line(x,y-15,x,y);
      line(x,y-35,x,y-50);
      outtextxy(x-8,y-12,"-");
      outtextxy(x-8,y-43,"+");
    break;
    case 2:        //horizontal a la derecha
      line(x-10,y-25,x-25,y-25);
      line(x+10,y-25,x+25,y-25);
      outtextxy(x-17,y-35,"-");
      outtextxy(x+12,y-35,"+");
    break;
    case 3:        //horizontal a la izquierda
      line(x-10,y-25,x-25,y-25);
      line(x+10,y-25,x+25,y-25);
      outtextxy(x-17,y-35,"+");
      outtextxy(x+12,y-35,"-");
    break;
   }
 setcolor(YELLOW);
}


  void fuente_corriente(int x,int y, int tipo)  //crea una fuente de corriente
  {
  setcolor(LIGHTGREEN);
  circle(x,y-25,10);

  switch(tipo)
   {
    case 0:      //vertical hacia arriba
       line(x,y,x,y-50);
       line(x-5,y-25,x,y-30);
       line(x+5,y-25,x,y-30);
    break;
    case 1:      //vertical hacia abajo
       line(x,y,x,y-50);
       line(x-5,y-25,x,y-20);
       line(x+5,y-25,x,y-20);
    break;
    case 2:      //horizontal hacia la derecha
       line(x-25,y-25,x+25,y-25);
       line(x+5,y-25,x,y-30);
       line(x+5,y-25,x,y-20);
    break;
    case 3:       //horizontal hacia la izquierda
       line(x-25,y-25,x+25,y-25);
       line(x-5,y-25,x,y-30);
       line(x-5,y-25,x,y-20);
    break;
   }
 setcolor(YELLOW);
}

  void pinta_dibujo(int x, int y)     //Pinta un circuito
  {
	fuente_voltaje(x,y+80,1);
	outtextxy(x-40,y+50,"10V");
	line(x,y+80,x+110,y+80);
	line(x+70,y+30,x+70,y+38);
	line(x,y+30,x+20,y+30);
	line(x+60,y+30,x+110,y+30);
	resistencia_horizontal(x+60,y+30);
	resistencia_vertical(x+70,y+79);
	fuente_corriente(x+110,y+80,0);
	outtextxy(x+125,y+50,"10A");
	outtextxy(x+35,y+7,"1");
	outtextxy(x+77,y+52,"1");
	setcolor(WHITE);
  }


  void cargar_teo1()         //Lee el archivo
  {
  incy=0;  //Inicializaci¢n del incremento en y
  fp=fopen("TEO1.txt","r");     //abro archivo
  char car[2]={'0','\0'};       //inicializo y finalizo cadena
 if(fp==NULL)     //Si un puntero es nulo imprime error
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp)) //Mientras que no llegue al final de archivo
 {
 if(incy!=410)    //incremento en y llega al final de la pantalla?
 {
   car[0]=fgetc(fp);  //va leyendo el archivo caracter por caracter
   if(car[0]=='\n')
   {
	incy+=10;       //Si encuentra una nueva linea lo incrementa
	moveto(0,incy); //y lo posiciona
   }
   if(car[0]=='@')     //si car [0] tiene @
   {
	pinta_dibujo(200,incy+30);  //se muestra el gr fico en el lugar donde aparece un determinado caracter
	incy+=150;
	moveto(0,incy);  //para que imprima texto desde la posici¢n indicada
   }
  if(car[0]!='\n' && car[0]!='@')  outtext(car);    //no imprimo el caracter
 }
 else
  {
   incy=0;
   moveto(0,incy);   //Si llega al final de la pantalla espera una tecla
   getch();          //para seguir leyendo el archivo, limpia la pantalla
		     //e inicializa las coordenadas
   cleardevice();   //equivalente a clrscr en modo gr fico
  }
 }
 getch();           //Se necesita esperar otra tecla para salir
 fclose(fp);        //Se cierra el archivo
}


 void cargar_teo2()
 {
	incy=0;
	fp=fopen("TEO2.txt","r");
	char car[2]={'0','\0'};

	if(fp==NULL)
	{
	 printf("ERROR");
	 exit(1);
	}

 while(!feof(fp))
  {
   if(incy!=410)
   {
     car[0]=fgetc(fp);
     if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
  }
     if(car[0]=='ž')
     {
	pinta_dibujo1(200,incy+30);
	incy+=150;
	moveto(0,incy);
     }
  if(car[0]=='‹')
   {
	pinta_dibujo2(200,incy+30);
	incy+=180;
	moveto(0,incy);
      }
   if(car[0]=='å')
   {
	pinta_dibujo3(250,incy+30);
	incy+=160;
	moveto(0,incy);
   }

  if(car[0]!='\n' && car[0]!='ž'&& car[0]!='‹' && car[0]!='å')  outtext(car);
  }

 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 fclose(fp);
}


 void pinta_dibujo1(int x, int y)
 {
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"Eth");
 line(x,y+80,x+110,y+80);
 line(x,y+30,x+20,y+30);
 line(x+60,y+30,x+110,y+30);
 resistencia_horizontal(x+60,y+30);
 outtextxy(x+30,y+7,"Rth");
 outtextxy(x+115,y+25,"a");
 outtextxy(x+115,y+75,"b");
 setcolor(WHITE);
 }


 void pinta_dibujo2(int x, int y)
 {
 fuente_corriente(x,y+80,0);
 outtextxy(x-40,y+50,"6A");
 line(x,y+30,x+141,y+30);  //linea horizontal arriba1
 line(x,y+80,x,y+123); //linea verrtical1
 line(x,y+123,x+182,y+123);  //linea horizontal abajo1
 resistencia_vertical (x+61,y+72);//resistencia vertical1
 outtextxy(x+65,y+51,"12");
 fuente_voltaje(x+61,y+122,1);
 outtextxy(x+73,y+95,"36V");
 resistencia_vertical (x+122,y+72);//resistencia vertical2
 outtextxy(x+127,y+51,"6");
 line(x+122,y+72,x+122,y+123);//linea vertical2
 resistencia_horizontal(x+182,y+30);
 outtextxy(x+158,y+10,"3");
 resistencia_vertical (x+182,y+72);//resistencia vertical1
 outtextxy(x+190,y+51,"3");
 fuente_voltaje(x+182,y+122,1);
 outtextxy(x+197,y+95,"9V");
 outtextxy(x+187,y+30,"a");
 outtextxy(x+187,y+122,"b");
 setcolor(WHITE);
 }


 void pinta_dibujo3(int x, int y)
 {
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"36V");
 resistencia_horizontal(x+42,y+30);
 outtextxy(x+21,y+10,"7");
 line(x+42,y+30,x+55,y+30);  //linea horizontal arriba1
 line(x,y+122,x+55,y+122); //linea horizontal
 line(x,y+80,x,y+122);  //linea vertical
 resistencia_vertical (x+55,y+72);//resistencia vertical1
 outtextxy(x+62,y+51,"3");
 fuente_voltaje(x+55,y+122,1);
 outtextxy(x+67,y+96,"9V");
 outtextxy(x+62,y+28,"a");
 outtextxy(x+62,y+120,"b");
 setcolor(WHITE);
 }


 void cargar_teo3()
 {
 incy=0;
 fp=fopen("TEO3.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
   {
    printf("ERROR");
    exit(1);
   }
 while(!feof(fp))
 {
  if(incy!=380)
   {
     car[0]=fgetc(fp);
     if(car[0]=='\n')
     {
	incy+=10;
	moveto(0,incy);
     }
     if(car[0]=='÷')
     {
     pinta_dibujo4(200,incy+30);
     incy+=150;
     moveto(0,incy);
     }
  if(car[0]=='Ž')
   {
	pinta_dibujo5(200,incy+30);
	incy+=150;
	moveto(0,incy);
      }
   if(car[0]=='é')
   {
	pinta_dibujo6(220,incy+30);
	incy+=160;
	moveto(0,incy);
   }

  if(car[0]!='\n' && car[0]!='÷'&& car[0]!='Ž' && car[0]!='é')  outtext(car);
  }

 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 fclose(fp);
}


 void pinta_dibujo4(int x, int y)
 {
 fuente_corriente(x,y+80,0);
 outtextxy(x-40,y+50,"In");
 line(x,y+30,x+120,y+30);  //linea horizontal arriba1
 line(x+61,y+72,x+61,y+80); //linea verrtical1
 line(x,y+80,x+120,y+80);  //linea horizontal abajo1
 resistencia_vertical (x+61,y+72);//resistencia vertical1
 outtextxy(x+65,y+51,"Rn");
 outtextxy(x+127,y+25,"a");
 outtextxy(x+127,y+75,"b");
 setcolor(WHITE);
 }


 void pinta_dibujo5(int x, int y)
 {
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"12V");
 line(x,y+80,x+84,y+80);  //linea horizontal abajo1
 resistencia_vertical (x+42,y+72);//resistencia vertical1
 outtextxy(x+45,y+51,"10");
 line (x+42,y+72,x+42,y+80);
 resistencia_horizontal(x+42,y+30);
 outtextxy(x+21,y+10,"4");
 resistencia_horizontal(x+84,y+30);
 outtextxy(x+63,y+10,"5");
 fuente_voltaje(x+84,y+80,1);
 outtextxy(x+98,y+50,"30V");
 outtextxy(x+40,y+20,"a");
 outtextxy(x+42,y+85,"b");
 setcolor(WHITE);
 }


 void pinta_dibujo6(int x, int y)
 {
 fuente_corriente(x,y+80,0);
 outtextxy(x-30,y+50,"9A");
 resistencia_vertical(x+42,y+72);
 outtextxy(x+45,y+51,"20/9");
 line(x,y+30,x+84,y+30);  //linea horizontal arriba
 line(x,y+80,x+84,y+80); //linea horizontal abajo
 line(x+42,y+72,x+42,y+80);  //linea vertical
 resistencia_vertical (x+84,y+72);//resistencia vertical1
 line(x+84,y+72,x+84,y+80);  //linea vertical
 outtextxy(x+88,y+51,"10");
 outtextxy(x+88,y+29,"a");
 outtextxy(x+88,y+81,"b");
 setcolor(WHITE);
 }


 void cargar_teo4()
 {

 incy=0;
 fp=fopen("TEO4.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=390)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='â')
   {
	pinta_dibujo7(180,incy+30);
	incy+=150;
	moveto(0,incy);
   }

  if(car[0]!='\n' && car[0]!='â')  outtext(car);
  }

 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


 void pinta_dibujo7(int x, int y)
 {
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"10V");
 resistencia_horizontal(x+42,y+30);
 outtextxy(x+21,y+10,"2");
 line(x+42,y+30,x+55,y+30);  //linea horizontal arriba1
 line(x,y+80,x+55,y+80); //linea horizontal
 resistencia_vertical (x+55,y+72);//resistencia vertical1
 line (x+55,y+72,x+55,y+80);
 outtextxy(x+62,y+51,"3");
 outtextxy(x+62,y+28,"a");
 outtextxy(x+62,y+81,"b");
 fuente_voltaje(x+120,y+80,1);
 outtextxy(x+133,y+51,"6V");
 outtextxy(x+120,y+20,"a");
 outtextxy(x+120,y+90,"b");
 fuente_corriente(x+180,y+80,1);
 outtextxy(x+193,y+51,"2A");
 outtextxy(x+180,y+20,"a");
 outtextxy(x+180,y+90,"b");
 setcolor(WHITE);
 }


 void cargar_teo5()
 {
 incy=0;
 fp=fopen("TEO5.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='¾')
   {
	pinta_dibujo8(240,incy+30);
	incy+=150;
	moveto(0,incy);
   }

  if(car[0]!='\n' && car[0]!='¾')  outtext(car);
  }

 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


 void pinta_dibujo8(int x, int y)
 {
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"5V");
 line(x,y+80,x+84,y+80);  //linea horizontal abajo1
 resistencia_vertical (x+42,y+72);//resistencia vertical1
 outtextxy(x+45,y+51,"2");
 line (x+42,y+72,x+42,y+80);
 resistencia_horizontal(x+42,y+30);
 outtextxy(x+21,y+10,"1");
 resistencia_horizontal(x+84,y+30);
 outtextxy(x+63,y+10,"5");
 fuente_voltaje(x+84,y+80,1);
 outtextxy(x+98,y+50,"10V");
 outtextxy(x+40,y+20,"o");
 outtextxy(x+42,y+85,"o'");
 setcolor(WHITE);
 }


 void cargar_teo6()
 {
 incy=0;
 fp=fopen("TEO6.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='ƒ')
   {
	pinta_dibujo9(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]=='’')
   {
	pinta_dibujo10(200,incy+30);
	incy+=150;
	moveto(0,incy);
      }

  if(car[0]!='\n' && car[0]!='ƒ'&& car[0]!='’')  outtext(car);
  }

 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


void pinta_dibujo9(int x, int y)
{
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"Eg");
 resistencia_horizontal(x+42,y+30);
 outtextxy(x+21,y+10,"Rg");
 line(x+42,y+30,x+55,y+30);  //linea horizontal arriba1
 line(x,y+80,x+55,y+80); //linea horizontal
 resistencia_vertical (x+55,y+72);//resistencia vertical1
 line (x+55,y+72,x+55,y+80);
 outtextxy(x+62,y+51,"Rl");
 outtextxy(x+62,y+28,"a");
 outtextxy(x+62,y+81,"b");
 setcolor(WHITE);
}



void pinta_dibujo10(int x, int y)
{
 fuente_voltaje(x,y+80,1);
 outtextxy(x-40,y+50,"4V");
 line(x,y+80,x+84,y+80);  //linea horizontal abajo1
 resistencia_vertical (x+42,y+72);//resistencia vertical1
 outtextxy(x+45,y+51,"2");
 line (x+42,y+72,x+42,y+80);
 resistencia_horizontal(x+42,y+30);
 outtextxy(x+21,y+10,"2");
 resistencia_horizontal(x+84,y+30);
 outtextxy(x+63,y+10,"2");
 resistencia_vertical(x+84,y+72);
 line (x+84,y+72,x+84,y+80);
 outtextxy(x+90,y+51,"Rl");
 outtextxy(x+84,y+20,"a");
 outtextxy(x+84,y+85,"b");
 setcolor(WHITE);
}


void cargar_teo7()
{
 fp=fopen("DETERMIN.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

  if(car[0]!='\n')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }

 fclose(fp);
}


void cargar_teo8()
{
 fp=fopen("mallas.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
   if(car[0]=='')//alt+02=
   {
	pinta_dibujo15(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }

  if(car[0]!='\n' && car[0]!='')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }

 fclose(fp);
}


void pinta_dibujo15(int x, int y)//640*480
{
 y+=100;
 setcolor(YELLOW);
 line(x,y,x+100,y);
 line(x+100,y,x+100,y-30);
 resistencia_vertical(x+100,y-30);
 line(x,y-100,x+100,y-100);
 line(x+100,y-70,x+100,y-100);
 line(x+100,y-100,x+130,y-100);
 line(x,y-100,x,y-70);
 line(x,y,x,y-30);
 fuente_corriente(x,y-20,0);
 line(x+100,y,x+200,y);
 line(x+200,y,x+200,y-30);
 line(x+200,y-70,x+200,y-100);
 line(x+200,y-100,x+170,y-100);
 resistencia_vertical(x+200,y-30);
 resistencia_horizontal(x+170,y-100);
 line(x+300,y-100,x+300,y-70);
 line(x+200,y-100,x+230,y-100);
 line(x+300,y-100,x+270,y-100);
 resistencia_horizontal(x+270,y-100);
 fuente_voltaje(x+300,y-30,1);
 line(x+200,y,x+300,y);
 line(x+300,y-30,x+300,y);
 setcolor(WHITE);
 outtextxy (x-30,y-50,"2A");
 outtextxy (x+150,y-120,"2");
 outtextxy (x+120,y-50,"5");
 outtextxy (x+220,y-50,"2");
 outtextxy (x+240,y-120,"6");
 outtextxy (x+320,y-50,"10V");
}


void cargar_teo9()
{
 incy=0;
 fp=fopen("NODOS.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

   if(car[0]=='~')//alt+126=~
   {
	pinta_dibujo16(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='~')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


void pinta_dibujo16(int x, int y)//640*480
{
 y+=100;
 setcolor(YELLOW);
 line(x,y,x+100,y);
 line(x+100,y,x+100,y-30);
 resistencia_vertical(x+100,y-30);
 line(x,y-100,x+100,y-100);
 line(x+100,y-70,x+100,y-100);
 line(x+100,y-100,x+130,y-100);
 line(x,y-100,x,y-70);
 line(x,y,x,y-30);
 fuente_corriente(x,y-20,1);
 line(x+100,y,x+200,y);
 line(x+200,y,x+200,y-30);
 line(x+200,y-70,x+200,y-100);
 line(x+200,y-100,x+170,y-100);
 resistencia_vertical(x+300,y-30);
 resistencia_horizontal(x+170,y-100);
 line(x+300,y-100,x+300,y-70);
 line(x+200,y-100,x+300,y-100);
 fuente_corriente(x+200,y-30,0);
 line(x+200,y,x+300,y);
 line(x+300,y-30,x+300,y);
 setcolor(WHITE);
 outtextxy(x-30,y-50,"2A");
 outtextxy(x+100,y-110,"Ea");
 outtextxy(x+120,y-50,"6");
 outtextxy(x+150,y-120,"3");
 outtextxy(x+220,y-50,"3A");
 outtextxy(x+320,y-50,"9");
 outtextxy(x+200,y-110,"Eb");
 outtextxy(x+130,y+5,"N.ref");
 }


void cargar_teo10()
{
incy=0;
fp=fopen("TOPOLOGY.txt","r");
char car[2]={'0','\0'};
if(fp==NULL)
 {
 printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
   if(car[0]=='Ä')//alt+196=Ä
   {
	pinta_dibujo17(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }
   if(car[0]=='ê')//alt+234=ê
   {
	pinta_dibujo18(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]=='á')//alt+225=á
   {
	pinta_dibujo19(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='Ä' && car[0]!='ê' && car[0]!='á')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


 void pinta_dibujo17(int x, int y)//640*480
{
 y+=100;
 setcolor(YELLOW);
 line(x+100,y,x+100,y-30);
 resistencia_vertical(x+100,y-30);
 line(x+100,y-70,x+100,y-100);
 line(x+100,y-100,x+70,y-100);
 resistencia_horizontal(x+70,y-100);
 fuente_voltaje(x,y,1);
 line(x+200,y-100,x+100,y-100);
 line(x,y,x+200,y);
 resistencia_vertical(x,y-50);
 line(x+200,y,x+200,y-100);
 fuente_corriente(x+200,y,0);
 resistencia_horizontal(x+70,y-150);
 line(x,y-100,x+30,y-100);
 line(x,y-90,x,y-150);
 line(x+100,y-100,x+100,y-150);
 line(x,y-150,x+30,y-150);
 line(x+100,y-150,x+70,y-150);
 setcolor(WHITE);
 outtextxy(x+40,y-145, "R2");
 outtextxy(x+40,y-100," R1");
 outtextxy(x,y-70," R4");
 outtextxy(x+100,y-50, "R3");
 outtextxy(x-20,y-30, "E");
 outtextxy(x+220,y-30, "I");
 setcolor(WHITE);
}


void pinta_dibujo18(int x, int y)
 {
 y+=100;
 setcolor(YELLOW);
 line(x+100,y,x+100,y-30);
 resistencia_vertical(x+100,y-30);
 line(x,y,x,y-50);
 line(x+100,y-70,x+100,y-100);
 line(x+100,y-100,x+70,y-100);
 resistencia_horizontal(x+70,y-100);
 resistencia_vertical(x,y-50);
 line(x,y,x+100,y);
 resistencia_horizontal(x+70,y-150);
 line(x,y-100,x+30,y-100);
 line(x,y-90,x,y-150);
 line(x+100,y-100,x+100,y-150);
 line(x,y-150,x+30,y-150);
 line(x+100,y-150,x+70,y-150);
 setcolor(WHITE);
 outtextxy(x+40,y-145, "R2");
 outtextxy(x+40,y-100," R1");
 outtextxy(x,y-70," R4");
 outtextxy(x+100,y-50, "R3");
 setcolor(WHITE);
 }


void pinta_dibujo19(int x, int y)
{
 setcolor(YELLOW);
 rectangle(x,y-20,x+100,y);
 rectangle(x,y+40,x+100,y);
 setcolor(BLUE);
 setcolor(WHITE);
 outtextxy(x-20,y, "N1");
 outtextxy(x+110,y, "N2");
 outtextxy(x+40,y+45,"N.ref");
}


void cargar_teo11()
{
 incy=0;
 fp=fopen("DUALIDAD.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
   if(car[0]=='')//alt+02=
   {
	pinta_dibujo20(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }
   if(car[0]=='÷')//alt+247=÷
   {
	pinta_dibujo21(100,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='' && car[0]!='÷')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


void pinta_dibujo20(int x, int y)//640*480
{
 y+=100;
 setcolor(YELLOW);
 line(x,y,x+100,y);
 line(x+100,y,x+100,y-30);
 resistencia_vertical(x+100,y-30);
 line(x,y-100,x+30,y-100);
 line(x+100,y-70,x+100,y-100);
 resistencia_horizontal(x+70,y-100);
 line(x+100,y-100,x+70,y-100);
 line(x,y-100,x,y-70);
 line(x,y,x,y-30);
 fuente_voltaje(x,y-20,1);
 line(x+100,y,x+200,y);
 line(x+200,y,x+200,y-30);
 line(x+200,y-70,x+200,y-100);
 line(x+200,y-100,x+170,y-100);
 resistencia_vertical(x+200,y-30);
 fuente_voltaje(x+150,y-75,3);
 line(x+130,y-100,x+100,y-100);
 setcolor(WHITE);
 outtextxy(x-30,y-50,"4V");
 outtextxy(x+110,y-50,"1");
 outtextxy(x+50,y-130,"1");
 outtextxy(x+150,y-130,"2V");
 outtextxy(x+210,y-50,"1");
}


 void pinta_dibujo21(int x, int y)//640*480
{
 y+=100;
 setcolor(YELLOW);
 line(x,y,x+100,y);
 line(x+100,y,x+100,y-30);
 resistencia_vertical(x+100,y-30);
 line(x,y-100,x+100,y-100);
 line(x+100,y-70,x+100,y-100);
 line(x+100,y-100,x+130,y-100);
 line(x,y-100,x,y-70);
 line(x,y,x,y-30);
 fuente_corriente(x,y-20,0);
 line(x+100,y,x+200,y);
 line(x+200,y,x+200,y-30);
 line(x+200,y-70,x+200,y-100);
 line(x+200,y-100,x+170,y-100);
 resistencia_vertical(x+200,y-30);
 resistencia_horizontal(x+170,y-100);
 line(x+300,y-100,x+300,y-70);
 line(x+200,y-100,x+300,y-100);
 fuente_corriente(x+300,y-30,1);
 line(x+200,y,x+300,y);
 line(x+300,y-30,x+300,y);
 setcolor(WHITE);
 outtextxy(x-30,y-50,"4A");
 outtextxy(x+330,y-50,"2A");
 outtextxy(x+110,y-50,"1");
 outtextxy(x+150,y-130,"1");
 outtextxy(x+220,y-50,"1");
 outtextxy(x+100,y-110,"E1");
 outtextxy(x+200,y-110,"E2");
 outtextxy(x+190,y+10,"N.ref");
}


void cargar_teo12()
{
 incy=0;
 fp=fopen("UNO.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=430)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='')
   {
	pita_dibu(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }


  if(car[0]!='\n' && car[0]!='')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


void pita_dibu(int x, int y)
{
 line(x+45,y,x+90,y);
 line(x+125,y,x+170,y);
 resistencia_horizontal(x+130,y);
 outtextxy(x+96,y+7,"R=2");
 outtextxy(x+83,y-20,"+ 16v -");
 setcolor(WHITE);
}


void cargar_teo13()
{
 incy=0;
 fp=fopen("DOS.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=410)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='þ')
   {
	pinta_dib2(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
   if(car[0]=='Å')
   {
	pinta_dib4(200,incy+30);
	incy+=100;
	moveto(0,incy);
   }
   if(car[0]=='×')
   {
	pinta_dib5(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
   if(car[0]=='')
   {
	pinta_dib6(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='þ'&& car[0]!='Å'&& car[0]!='×' && car[0]!='')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


void pinta_dib2(int x, int y)
{
  line(x+25,y,x+90,y);
  line(x+25,y,x+25,y+15);
  line(x+25,y+55,x+25,y+70);
  line(x+25,y+70,x+90,y+70);
  line(x+90,y,x+90,y+15);
  line(x+90,y+50,x+90,y+70);
  fuente_voltaje(x+90,y+60,0);
  outtextxy(x+105,y+35,"E");
  outtextxy(x-15,y+35,"R=Infinito");
  setcolor(WHITE);
}


void pinta_dib4(int x, int y)
{
  line(x+25,y,x+90,y);
  line(x+25,y,x+25,y+15);
  line(x+25,y+55,x+25,y+70);
  line(x+25,y+70,x+90,y+70);
  line(x+90,y,x+90,y+15);
  line(x+90,y+50,x+90,y+70);
  fuente_corriente(x+90,y+60,0);
  outtextxy(x+105,y+35,"I");
  outtextxy(x-15,y+35,"R=Infinita");
  setcolor(WHITE);
}


void pinta_dib5(int x, int y)
{
  line(x+70,y,x+90,y);
  line(x+25,y,x+35,y);
  line(x+25,y,x+25,y+70);
  line(x+25,y+70,x+90,y+70);
  line(x+90,y,x+90,y+15);
  line(x+90,y+50,x+90,y+70);
  fuente_voltaje(x+90,y+60,0);
  resistencia_horizontal(x+75,y);
  outtextxy(x+17,y,"a");
  outtextxy(x+17,y+60,"b");
  outtextxy(x+105,y+35,"E");
  outtextxy(x+55,y-20,"R");
  setcolor(WHITE);
}


void pinta_dib6(int x, int y)
{
 line(x+5,y,x+90,y);
 line(x+25,y,x+25,y+15);
 line(x+5,y+70,x+90,y+70);
 line(x+90,y,x+90,y+15);
 line(x+90,y+50,x+90,y+70);
 line(x+25,y+50,x+25,y+70);
 fuente_corriente(x+90,y+60,0);
 resistencia_vertical(x+25,y+55);
 outtextxy(x+105,y+35,"I");
 outtextxy(x+35,y+35,"R");
 setcolor(WHITE);
}


void pinta_dib55(int x, int y)
{
  line(x+10,y,x+90,y);
  line(x+10,y+70,x+90,y+70);
  line(x+45,y,x+45,y+15);
  line(x+90,y+55,x+90,y+70);
  line(x+45,y+55,x+45,y+70);
  line(x+90,y,x+90,y+15);
  resistencia_vertical(x+45,y+55);
  resistencia_vertical(x+90,y+55);
  outtextxy(x+95,y+25,"R");
  outtextxy(x+50,y+25,"R");
  setcolor(WHITE);
}


void cargar_teo14()
{
 incy=0;
 fp=fopen("TRES.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=410)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='¹')
   {
	r_en_serie(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='¹')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}
void r_en_serie(int x,int y)
{
	setcolor(YELLOW);
	resistencia_horizontal(x+30,y+10);
	outtextxy(x+7,y-20,"R1");
	resistencia_horizontal(x+90,y+10);
	outtextxy(x+67,y-20,"R2");
	resistencia_horizontal(x+150,y+10);
	outtextxy(x+127,y-20,"R3");
	line(x-40,y+10,x-10,y+10);
	line(x+30,y+10,x+50,y+10);
	line(x+90,y+10,x+110,y+10);
	line(x+150,y+10,x+180,y+10);
	incy+=40;
	setcolor(WHITE);

   }
void cargar_teo15()
{
 incy=0;
 fp=fopen("cuatro.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='©')
   {
	voltaje_kirchhoff(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }

  if(car[0]!='\n' && car[0]!='©')  outtext(car);
  }

 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 getch();
 fclose(fp);
}
void voltaje_kirchhoff(int x,int y)
{
	setcolor(YELLOW);
	fuente_voltaje(x-11,y+60,1);
	line(x+150,y+10,x+150,y+60);
	resistencia_horizontal(x+30,y+10);
	outtextxy(x+7,y-20,"R1");
	resistencia_horizontal(x+90,y+10);
	outtextxy(x+67,y-20,"R2");
	resistencia_horizontal(x+150,y+10);
	outtextxy(x+127,y-20,"R3");
	line(x+30,y+10,x+50,y+10);
	line(x+90,y+10,x+110,y+10);
	resistencia_horizontal(x+30,y+60);
	outtextxy(x+7,y+70,"R6");
	resistencia_horizontal(x+90,y+60);
	outtextxy(x+67,y+70,"R5");
	resistencia_horizontal(x+150,y+60);
	outtextxy(x+127,y+70,"R4");
	line(x+30,y+60,x+50,y+60);
	line(x+90,y+60,x+110,y+60);
	setcolor(WHITE);

   }
void cargar_teo16()
{
 incy=0;
 fp=fopen("SEIS.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=410)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='“')
   {
	pinta_dibujo(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='“')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 fclose(fp);
}

void cargar_teo55()
{
 incy=0;
 fp=fopen("CINCO.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=410)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='‰')
   {
	pinta_dib55(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='“')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 fclose(fp);
}

void cargar_teo77()
{
 incy=0;
 fp=fopen("SIETE.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=410)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='´')
   {
	pinta_dib77(200,incy+30);
	incy+=150;
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='´')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();

   cleardevice();
  }
 }
 fclose(fp);
}
void pintar_dibujo22(int x, int y)
{
 line(x-10,y,x+10,y);
 line(x+45,y,x+90,y);
 line(x-10,y+70,x+220,y+70);
 line(x+125,y,x+170,y);
 line(x+65,y,x+65,y+15);
 line(x+65,y+55,x+65,y+70);
 line(x+200,y,x+220,y);
 line(x+145,y,x+145,y+15);
 line(x+145,y+55,x+145,y+70);
 line(x+220,y,x+220,y+15);
 line(x+220,y+55,x+220,y+70);
 resistencia_horizontal(x+50,y);
 resistencia_vertical(x+65,y+55);
 resistencia_horizontal(x+130,y);
 resistencia_vertical(x+145,y+55);
 resistencia_horizontal(x+205,y);
 resistencia_vertical(x+220,y+55);
 outtextxy(x+15,y+7,"R1=4");
 outtextxy(x+95,y+7,"R2=1");
 outtextxy(x+175,y+7,"R3=4");
 outtextxy(x+150,y+35,"R5=6");
 outtextxy(x+225,y+35,"R6=2");
 outtextxy(x+73,y+35,"R4=12");
 setcolor(WHITE);
}

void pinta_dib77(int x, int y)
{
  line(x+10,y,x+90,y);
  line(x+10,y+70,x+90,y+70);
  line(x+45,y,x+45,y+15);
  line(x+90,y,x+90,y+70);
  line(x+45,y+55,x+45,y+70);
  resistencia_vertical(x+45,y+55);
  outtextxy(x-5,y+5,"I -->");
  outtextxy(x+50,y+25,"R");
  outtextxy(x+95,y+25,"R=Infinito");
  setcolor(WHITE);
}
 void cargar_cap_uno1()
 {
 incy=0;
 fp=fopen("NUMERAL1.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

  if(car[0]!='\n' )  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
 }
 void cargar_cap_uno2()
 {
 incy=0;
 fp=fopen("NUMERAL2.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

  if(car[0]!='\n' )  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
 }
  void cargar_cap_uno3()
 {
 incy=0;
 fp=fopen("NUMERAL3.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

  if(car[0]!='\n' )  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
 }

 void cargar_cap_uno4()
 {
 incy=0;
 fp=fopen("NUMERAL4.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

  if(car[0]!='\n' )  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
 }

 void cargar_cap_uno5()
 {
 incy=0;
 fp=fopen("NUMERAL5.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }

  if(car[0]!='\n' )  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
 }
 void cargar_cap_uno6()
 {
 incy=0;
 fp=fopen("NUMERAL6.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='@')
   {
	setcolor(YELLOW);
	fuente_voltaje(200,incy+100,1);
	outtextxy(180,incy+70,"E");
	fuente_voltaje(320,incy+100,2);
	outtextxy(317,incy+55,"E");
	incy+=150;
	setcolor(WHITE);
	moveto(0,incy);
   }
   if(car[0]=='‘')
   {
	setcolor(YELLOW);
	fuente_corriente(200,incy+100,0);
	outtextxy(180,incy+70,"I");
	fuente_corriente(320,incy+100,2);
	outtextxy(317,incy+55,"I");
	incy+=150;
	setcolor(WHITE);
	moveto(0,incy);
   }
  if(car[0]!='\n' && car[0]!='@' && car[0]!='‘')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
 }
 void cargar_cap_uno7()
 {
 incy=0;
 fp=fopen("NUMERAL7.txt","r");
 char car[2]={'0','\0'};
 if(fp==NULL)
 {
  printf("ERROR");
  exit(1);
 }
 while(!feof(fp))
 {
  if(incy!=380)
  {
   car[0]=fgetc(fp);
   if(car[0]=='\n')
   {
	incy+=10;
	moveto(0,incy);
   }
     if(car[0]=='@')
   {
	setcolor(YELLOW);
	resistencia_horizontal(280,incy+40);
	outtextxy(257,incy+15,"R");
	setcolor(WHITE);

	incy+=100;
	moveto(0,incy);
   }
   if(car[0]=='‘')
   {
	r_en_serie(200,incy+30);

   }
    if(car[0]=='')
   {
       r_en_paralelo(200,incy+30);


   }
  if(car[0]!='\n' && car[0]!='@' && car[0]!='‘'&& car[0]!='')  outtext(car);
  }
 else
  {
   incy=0;
   moveto(0,incy);
   getch();
   cleardevice();
  }
 }
 getch();
 fclose(fp);
}


void r_en_paralelo(int x,int y)
{          //200,incy+30
   setcolor(YELLOW);
   resistencia_vertical(x+30,y+50);
   resistencia_vertical(x+90,y+50);
   resistencia_vertical(x+150,y+50);
   line(x-30,y+52,x+150,y+52);
   line(x-30,y+8,x+150,y+8);
   outtextxy(x,y+25,"R1");
   outtextxy(x+60,y+25,"R2");
   outtextxy(x+120,y+25,"R3");
   setcolor(WHITE);
   incy+=80;
}


void inicio(void)          //Muestra el texto del menu principal
  {
  clrscr();
  gotoxy(34,6);
  printf("MENU PRINCIPAL");

  gotoxy(5,10);
  printf("CAPITULO I:NOCIONES GENERALES");

  gotoxy(5,12);
  printf("CAPITULO II: LEYES Y ANALISIS DE CIRCUITOS");

  gotoxy(5,14);
  printf("CAPITULO III: METODOS DE SOLUCION DE CIRCUITOS");

  gotoxy(5,16);
  printf("CAPITULO IV: TEOREMAS DE REDES");

  gotoxy(5,18);
  printf("PRESIONE '5' SI DESEA SALIR");




  }



  void elegir_a ()            //Sirve para seleccionar el cap¡tulo
  {
  int num1;

  gotoxy(5,20);
  printf("Seleccione opci¢n:  ");
  scanf("%d",&num1);
  fflush(stdin);

  switch(num1)
	    {
	    case 1:
			cap_1 ();       //Muestra submen£ del cap¡tulo1
			elegir_cap1();  //Se elige el archivo a visualizar
			break;

	    case 2:
			cap_2 ();
			elegir_cap2();
			break;


	    case 3:

			cap_3 ();
			elegir_cap3();
			break;

	    case 4:

			cap_4 ();
			elegir_cap4();
			break;

	    case 5:
			exit(1);
			break;


	    default:
			gotoxy(5,22);
			printf("Digite un n£mero entre 1 y 5");
			elegir_a();  //funcion recursiva por que se
			break;       //llama a la funci¢n dentro de s¡ misma


	    }
  }



  void cap_1 (void)  //Muestra submen£ del cap¡tulo 1
  {
  clrscr();

  gotoxy(5,2);
  printf("CAPITULO I");

  gotoxy(5,4);
  printf("1.INTRODUCCION E HISTORIA DE LA ELECTRICIDAD");

  gotoxy(5,6);
  printf("2.SISTEMA INTERNACIONAL DE UNIDADES (SI) Y NOTACION CIENTIFICA");

  gotoxy(5,8);
  printf("3.ESTRUCTURA ATOMICA");

  gotoxy(5,10);
  printf("4.ELECTRICIDAD Y LEY DE COULOMB");

  gotoxy(5,12);
  printf("5.CORRIENTE Y VOLTAJE");

  gotoxy(5,14);
  printf("6.FUENTES FIJAS");

  gotoxy(5,16);
  printf("7.RESISTENCIA ELECTRICA");

  gotoxy(5,18);
  printf("8.REGRESAR AL MENU PRINCIPAL");


  }

  void cap_2 (void)
  {
  clrscr();
  gotoxy(5,2);
  printf("CAPITULO II");

  gotoxy(5,4);
  printf("1.LEYES Y ANALISIS DE CIRCUITOS");

  gotoxy(5,6);
  printf("2.FUENTES FIJAS");

  gotoxy(5,8);
  printf("3.NIVEL DE REFERENCIA");

  gotoxy(5,10);
  printf("4.CIRCUITOS EN SERIE");

  gotoxy(5,12);
  printf("5.CIRCUITOS EN PARALELO");

  gotoxy(5,14);
  printf("6.CIRCUITOS SERIE-PARALELO");

  gotoxy(5,16);
  printf("7.CIRCUITOS EN CORTO");

  gotoxy(5,18);
  printf("8.REGRESAR AL MENU PRINCIPAL");


  }

  void cap_3 ()
  {
  clrscr();
  gotoxy(5,5);
  printf("CAPITULO III");

  gotoxy(5,8);
  printf("1. DETERMINANTES");

  gotoxy(5,10);
  printf("2. METODO DE CORRIENTE DE MALLA");

  gotoxy(5,12);
  printf("3. METODO DE VOLTAJE DE NODO");

  gotoxy(5,14);
  printf("4. TOPOLOGIA DE CIRCUITOS");

  gotoxy(5,16);
  printf("5. PRINCIPIO DE DUALIDAD");

  gotoxy(5,18);
  printf("6.REGRESAR AL MENU PRINCIPAL");


  }

  void cap_4 (void)
  {
  clrscr();
  gotoxy(5,3);
  printf("CAPITULO IV");

  gotoxy(5,6);
  printf("1. TEOREMA DE LINEALIDAD Y SUPERPOSICION ");

  gotoxy(5,8);
  printf("2. TEOREMA DE THEVENIN");

  gotoxy(5,10);
  printf("3. TEOREMA DE NORTON");

  gotoxy(5,12);
  printf("4. TEOREMA DE SUSTITUCION");

  gotoxy(5,14);
  printf("5. TEOREMA DE MILLMAN");

  gotoxy(5,16);
  printf("6. TEOREMA DE LA MAXIMA TRANSFERENCIA DE POTENCIA");

  gotoxy(5,18);
  printf("7.REGRESAR AL MENU PRINCIPAL");


  }



  void elegir_cap1 ()    //Permite seleccionar el tema del submenu
  {
  int num_c1;

  gotoxy(5,20);
  printf("Ingrese el n£mero del tema deseado: ");
  scanf("%d",&num_c1);

  switch(num_c1)
	    {
	    case 1:
			modo_grafico();     //inicializa modo gr fico
			cargar_cap_uno1();  //llama un archivo con texto y gr ficos
			break;

	    case 2:     modo_grafico();
			cargar_cap_uno2();
			break;


	    case 3:
			modo_grafico();
			cargar_cap_uno3();
			break;

	    case 4:
			modo_grafico();
			cargar_cap_uno4();
			break;

	    case 5:
			modo_grafico();
			cargar_cap_uno5();
			break;

	    case 6:
			modo_grafico();
			cargar_cap_uno6();
			break;

	    case 7:
			modo_grafico();
			cargar_cap_uno7();
			break;

	    case 8:
		       menu_principal();  //opcion para volver al menu ppal.
		       break;



	    default:    fflush(stdin);
			gotoxy(5,22);
			printf("Digite un n£mero entre 1 y 8");
			elegir_cap1();   //funci¢n recursiva

			break;



}

  }

  void elegir_cap2 ()
  {
  int num_c2;

  gotoxy(5,22);
  printf("Ingrese el n£mero del tema deseado: ");
  scanf("%d",&num_c2);

  switch(num_c2)
	    {
	    case 1:
			modo_grafico();
			cargar_teo12();
			break;

	    case 2:
			modo_grafico();
			cargar_teo13();
			break;


	    case 3:
			modo_grafico();
			cargar_teo14();
			break;

	    case 4:
			modo_grafico();
			cargar_teo15();
			break;

	    case 5:     modo_grafico();
			cargar_teo55();
			break;


	    case 6:     modo_grafico();
			cargar_teo16();
			break;
	    case 7:
			modo_grafico();
			cargar_teo77();
			break;


	    case 8:
			clrscr();
			menu_principal();
			break;



	    default:
			gotoxy(5,24);
			printf("Digite un n£mero entre 1 y 8");
			elegir_cap2();
			break;



}

  }



  void elegir_cap3 ()
  {
  int num_c3;

  gotoxy(5,22);
  printf("Ingrese el n£mero del tema deseado: ");
  scanf("%d",&num_c3);

  switch(num_c3)
	    {
	    case 1:
			modo_grafico();
			cargar_teo7();
			break;

	    case 2:
			modo_grafico();
			cargar_teo8();
			break;


	    case 3:
			modo_grafico();
			cargar_teo9();
			break;

	    case 4:
			modo_grafico();
			cargar_teo10();
			break;

	    case 5:
			modo_grafico();
			cargar_teo11();
			break;

	    case 6:
			clrscr();
			menu_principal();
			break;

	    default:
			gotoxy(5,24);
			printf("Digite un n£mero entre 1 y 6");
			elegir_cap3();
			break;



}

  }


  void elegir_cap4 ()
  {
  int num_c4;

  gotoxy(5,20);
  printf("Ingrese el n£mero del tema deseado: ");
  scanf("%d",&num_c4);

  switch(num_c4)
	    {
	    case 1:
			modo_grafico();
			cargar_teo1();
			break;

	    case 2:
			modo_grafico();
			cargar_teo2();
			break;


	    case 3:
			modo_grafico();
			cargar_teo3();
			break;

	    case 4:
			modo_grafico();
			cargar_teo4();
			break;

	    case 5:
			modo_grafico();
			cargar_teo5();
			break;

	    case 6:
			modo_grafico();
			cargar_teo6();
			break;

	    case 7:
			clrscr();
			menu_principal();
			break;



	    default:
			gotoxy(5,22);
			printf("Digite un n£mero entre 1 y 7");
			elegir_cap4();
			break;



 }

  }

  void portada(void){

  int i=0;
  clrscr();
  gotoxy (2,2);
  printf("É");
  gotoxy(79,2);
  printf("»");
  gotoxy(2,24);
  printf("È");
  gotoxy(79,24);
  printf("¼");

	for(i=3;i<79;i++)
	{
	  gotoxy(i,2);      //se incrementa en x en la parte de arriba del
	  printf("Í");      //marco
	}
	for(i=3;i<79;i++)
	{
	  gotoxy(i,24);
	  printf("Í");
	}
	for(i=3;i<24;i++)
	{
	  gotoxy(2,i);
	  printf("º");
	}
	for(i=3;i<24;i++)
	{
	  gotoxy(79,i);
	  printf("º");
	}


  gotoxy(29,6);
  printf("TUTOR BASICO DE CIRCUITOS");
  gotoxy(28,8);
  printf("PROYECTO FINAL INFORMATICA I");

  gotoxy(15,13);
  printf("RESPONSABLES: ");
  gotoxy(30,14);
  printf("Adriana Mar¡a Gustin Rebolledo");
  gotoxy(30,15);
  printf("Andr‚s Felipe Escall¢n Portilla");
  gotoxy(30,16);
  printf("Dar¡o Fernando Gust¡n Insuasty");
  gotoxy(30,17);
  printf("Javier Fernando Imb£s Guzm n");

  gotoxy(30,20);
  printf(" UNIVERSIDAD DEL CAUCA");
  gotoxy(12,22);
  printf(" Facultad de Ingenier¡a Electr¢nica y Telecomunicaciones");
  getch();
  }

void menu_principal()   //Uni¢n de las funciones para mostrar texto y
{                       //elegir opci¢n
inicio();
elegir_a();
}

void continuar(){         //pregunta si desea seguir en el tutor

int i,d;


clrscr();
	gotoxy(15,8);
	printf("É");
	gotoxy(60,8);
	printf("»");
	gotoxy(15,15);
	printf("È");
	gotoxy(60,15);
	printf("¼");
	gotoxy(25,10);
	for(i=16;i<60;i++)
	{
	  gotoxy(i,8);
	  printf("Í");
	}
	for(i=16;i<60;i++)
	{
	  gotoxy(i,15);
	  printf("Í");
	}
	for(i=9;i<15;i++)
	{
	  gotoxy(15,i);
	  printf("º");
	}
	for(i=9;i<15;i++)
	{
	  gotoxy(60,i);
	  printf("º");
	}


	gotoxy(23,10);
	printf("¨Desea continuar en el tutor?");
	gotoxy(34,11);
	printf("1. Si");
	gotoxy(18,12);
	printf("CUALQUIER TECLA DIFENTE DE '1' PARA SALIR");
	gotoxy(25,13);
	printf("->");
	scanf("%d",&d);

 switch(d){

	case 1 :
		 menu_principal();
		 closegraph();
		 continuar();//funcion recursiva

 default : exit(1);

 }
 }


				 //FIN
