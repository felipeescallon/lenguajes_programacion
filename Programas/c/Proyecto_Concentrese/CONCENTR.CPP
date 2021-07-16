#include<stdio.h>
#include<conio.h>
#include<graphics.h>
#include<time.h> //randomize()
#include<stdlib.h>

void dibujartodo(int,int,int,int,int,int); //dibuja toda la pantalla
void dibujarcaja(int,int,int,int); //dibuja una caja
void iniciarmatriz(); //asigna los valores de la matriz
void terminar(); //dice quien gano

//Matrices que contienen informaci¢n sobre el contenido de las cajas
//y sobre las parejas creadas
int matriz[10][10],parejas[10][10];
//posiciones en la matriz de la caja seleccionada y de las
//cajas abiertas
int posx=0,posy=0,aposx1=20,aposx2=20,aposy1=20,aposy2=20,abierto=0;
//Jugador activo
int jugador=0;
//Contador de puntajes de cada jugador
int puntajes[2];

void main()
{
 int salir=0;
 char tecla;
 int gdevice=DETECT,gmode;
 randomize();
 initgraph(&gdevice,&gmode,"");
 dibujartodo(0,0,aposx1,aposy1,aposx2,aposy2);
 iniciarmatriz();
 do
 {
  if(kbhit())
  {
   tecla=getch();
   switch(tecla)
   {
    case 0:{
	    tecla=getch();
	    switch(tecla)
	    {
	     case 77:{
		      if(posx<9)
		      {
		       posx++;
		       dibujartodo(posx,posy,aposx1,aposy1,aposx2,aposy2);
		      }
		      break;
		     }
	     case 75:{
		      if(posx>0)
		      {
		       posx--;
		       dibujartodo(posx,posy,aposx1,aposy1,aposx2,aposy2);
		      }
		      break;
		     }
	     case 80:{
		      if(posy<9)
		      {
		       posy++;
		       dibujartodo(posx,posy,aposx1,aposy1,aposx2,aposy2);
		      }
		      break;
		     }
	     case 72:{
		      if(posy>0)
		      {
		       posy--;
		       dibujartodo(posx,posy,aposx1,aposy1,aposx2,aposy2);
		      }
		      break;
		     }
	    }
	    break;
	   }
    case 13:{
	     if(abierto==0)
	     {
	      aposx1=posx;
	      aposy1=posy;
	     }
	     if(abierto==1)
	     {
	      if((aposx1!=posx)||(aposy1!=posy))
	      {
	       aposx2=posx;
	       aposy2=posy;
	      }
	      else
	      {
	       abierto--;
	      }
	     }
	     if((matriz[aposy2][aposx2]==matriz[aposy1][aposx1])&&(abierto==2))
	     {
	      parejas[aposy2][aposx2]=1;
	      parejas[aposy1][aposx1]=1;
	      puntajes[jugador]++;
	     }
	     if(abierto!=2)
	     {
	      abierto++;
	     }
	     else
	     {
	      if(jugador==0)
	      {
	       jugador=1;
	      }
	      else
	      {
	       jugador=0;
	      }
	      abierto=0;
	      aposx1=20;
	      aposy1=20;
	      aposx2=20;
	      aposy2=20;
	     }
	     if(puntajes[0]+puntajes[1]==50)
	     {
	      terminar();
	      salir=1;
	     }
	     dibujartodo(posx,posy,aposx1,aposy1,aposx2,aposy2);
	     break;
	    }
    case 27:{
	     salir=1;
	     break;
	    }
   }
  }
 }while(!salir);
}









void dibujartodo(int cajax,int cajay,int abx1,int aby1,int abx2,int aby2)
{
 char* tempo=new(char[3]);
 setcolor(15);
 settextstyle(2,0,6);
 setfillstyle(1,0);
 bar(0,getmaxy()-40,getmaxx(),getmaxy());
 if(jugador==0)
 {
  outtextxy(100,getmaxy()-20,"JUGADOR 1");
 }
 else
 {
  outtextxy(100,getmaxy()-20,"JUGADOR 2");
 }
 outtextxy(getmaxx()-150,getmaxy()-40,"PUNTAJE 1: ");
 outtextxy(getmaxx()-40,getmaxy()-40,itoa(puntajes[0],tempo,10));
 outtextxy(getmaxx()-150,getmaxy()-20,"PUNTAJE 2: ");
 outtextxy(getmaxx()-40,getmaxy()-20,itoa(puntajes[1],tempo,10));
 for(int nx=0;nx<10;nx++)
 {
  for(int ny=0;ny<10;ny++)
  {
   if(((abx1==nx)&&(aby1==ny))||((abx2==nx)&&(aby2==ny)))
   {
    if((cajax==nx)&&(cajay==ny))
    {
     dibujarcaja(1,1,nx,ny);
    }
    else
    {
     dibujarcaja(0,1,nx,ny);
    }
   }
   else
   {
    if((cajax==nx)&&(cajay==ny))
    {
     dibujarcaja(1,0,nx,ny);
    }
    else
    {
     dibujarcaja(0,0,nx,ny);
    }
   }
  }
 }
}




void dibujarcaja(int activo,int abierta,int nx,int ny)
{
 char* tempo=new(char[3]);
 setfillstyle(1,1);
 if(parejas[ny][nx]!=1)
 {
  bar(nx*getmaxx()/11,ny*getmaxy()/11,getmaxx()*nx/11+50,getmaxy()*ny/11+40);
 }
 setcolor(4);
 setlinestyle(0,0,1);
 rectangle(nx*getmaxx()/11,ny*getmaxy()/11,getmaxx()*nx/11+50,getmaxy()*ny/11+40);
 setcolor(15);
 if((activo)&&(!abierta))
 {
  rectangle(nx*getmaxx()/11,ny*getmaxy()/11,getmaxx()*nx/11+50,getmaxy()*ny/11+40);
 }
 if((activo)&&(abierta))
 {
  rectangle(nx*getmaxx()/11,ny*getmaxy()/11,getmaxx()*nx/11+50,getmaxy()*ny/11+40);
  settextstyle(2,0,6);
  setcolor(15);
  outtextxy(nx*getmaxx()/11+15,ny*getmaxy()/11+10,itoa(matriz[ny][nx],tempo,10));
 }
 if((!activo)&&(abierta))
 {
  settextstyle(2,0,6);
  setcolor(15);
  outtextxy(nx*getmaxx()/11+15,ny*getmaxy()/11+10,itoa(matriz[ny][nx],tempo,10));
 }
}








void iniciarmatriz()
{
 int numero=1,fila1,col1,fila2,col2,temp1,temp2;
 for(int f=0;f<10;f++)
 {
  for(int c=0;c<10;c+=2)
  {
   matriz[f][c]=numero;
   matriz[f][c+1]=numero;
   numero++;
  }
 }
 for(int cont=0;cont<500;cont++)
 {
  fila1=random(10);
  col1=random(10);
  fila2=random(10);
  col2=random(10);
  temp1=matriz[fila1][col1];
  temp2=matriz[fila2][col2];
  matriz[fila1][col1]=temp2;
  matriz[fila2][col2]=temp1;
 }
}

void terminar()
{
 setcolor(2);
 if(puntajes[0]>puntajes[1])
 {
  settextstyle(0,0,3);
  outtextxy(getmaxx()/2-200,getmaxy()/2-100,"JUGADOR 1 GANO");
 }
 if(puntajes[0]<puntajes[1])
 {
  settextstyle(0,0,3);
  outtextxy(getmaxx()/2-200,getmaxy()/2-100,"JUGADOR 2 GANO");
 }
 if(puntajes[0]==puntajes[1])
 {
  settextstyle(0,0,3);
  outtextxy(getmaxx()/2-200,getmaxy()/2-100,"EMPATE TECNICO");
 }
 getch();
}