#ifndef_MODOGRAFICO_H
#define_MODOGRAFICO_H
#if !defined(__STRING_H)
#include <string.h>
#endif
#if !defined(__BIOS_H)
#include <bios.h>
#endif
#if !defined(__STDIO_H)
#include<stdio.h>
#endif
#if !defined(__CONIO_H)
#include<conio.h>
#endif
#if !defined(__STDLIB_H)
#include <stdlib.h>
#endif
#if !defined(__GRAPHICS_H)
#include <graphics.h>
#endif
#if !defined(__FSTREAM_H)
# include <fstream.h>
#endif
#if !defined(__STRSTREAM_H)
#include<STRSTREAM.H>
#endif
#define A_Mayuscula(x) (x>='a'&&x<='z')?(x-=32):(x)
const ENTER=13,BORRAR='\b',ESC=27,ARR=72,ABJ=80,IZQ=75,DER=77,
TAB='\t',MaxEs=10,DEL=83,HOME=71,FIN=79,NULO='\0',ESP=32;
int cantop,COLORBARRA,COLORLETRA,ELEMSELEC,PALSELEC;
void boton(int);

void Ascci_A_Car(char &T){
 switch(T){
 case 30:T='A';break;
 case 48:T='B';break;
 case 46:T='C';break;
 case 32:T='D';break;
 case 18:T='E';break;
 case 33:T='F';break;
 case 34:T='G';break;
 case 35:T='H';break;
 case 23:T='I';break;
 case 36:T='J';break;
 case 37:T='K';break;
 case 38:T='L';break;
 case 50:T='M';break;
 case 49:T='N';break;
 case 24:T='O';break;
 case 25:T='P';break;
 case 16:T='Q';break;
 case 19:T='R';break;
 case 31:T='S';break;
 case 20:T='T';break;
 case 22:T='U';break;
 case 47:T='V';break;
 case 17:T='W';break;
 case 45:T='X';break;
 case 21:T='Y';break;
 case 44:T='Z';break;
 }
}

void cursor(int x,int y,int color=1){
 int colorante=color;
 do{
	setcolor(color);
	outtextxy(x,y,"_");
	delay(80);
	setcolor(15);
	outtextxy(x,y,"_");
	delay(80);
 }while(!kbhit());
 setcolor(colorante);
}

void centrar(int x,int y,char a[]){
 int l,l1;
 int mx=getmaxx();
 int my=getmaxy();
 l=textwidth(a);
 l=(mx-l)/2;
 l1=textheight(a);
 l1=(my-l1)/2;
 if(x==0&&y!=0)
 outtextxy(l,y,a);
 if(x!=0&&y==0)
 outtextxy(x,l1,a);
 if(x==0&&y==0)
 outtextxy(l,l1,a);
}

void efecto1(int x1,int y1,int x2,int y2,int c,int cs,int ci){
 if ( c!=0) {
 setfillstyle(1,c);
 bar(x1,y1,x2,y2);
 }
 setlinestyle(0,0,0);
 setcolor(cs);
 line(x1,y1,x1,y2);
 line(x1,y1,x2,y1);
 setcolor(ci);
 line(x2,y2,x2,y1);
 line(x2,y2,x1,y2);
}

void efecto2(int x1,int y1,int x2,int y2,int c,int cs,int ci){
 efecto1(x1-1,y1-1,x2+1,y2+1,c,cs,ci);
 efecto1(x1,y1,x2,y2,c,cs,ci);
}

void efecto3(int x1,int y1,int x2,int y2,char a[]){
 efecto2(x1,y1,x2,y2,COLORBARRA,15,8);
 setcolor(0);
 rectangle(x1-2,y1-2,x2+2,y2+2);
 setcolor(7);
 line(x1-2,y1-2,x1-2,y2+2);
 line(x1-2,y1-2,x2+2,y1-2);
 setfillstyle(1,ELEMSELEC);
 bar(x1,y1,x2,y1+20);
 setcolor(PALSELEC);
 outtextxy(x1+10,y1+5,a);
 efecto1(x1,y1,x2,y2,0,15,8);
}

void efecto4(int x1,int y1,int x2,int y2,int c){
 efecto1(x1,y1,x2,y2,c,8,15);
 efecto1(x1+1,y1+1,x2-1,y2-1,0,0,7);
}

void ventana(char a[]){
 int x,y;
 x=639;
 y=479;
// efecto3(180,150,x-180,y-150,a);
 efecto3(230,165,x-230,y-165,a);
}

void borrar(int swx,int swy){
 int escala=10,espaciox=48,espacioy=13;
 for(int i=0;i<12;i+=3)
	 for(int j=0;j<12;j+=3){
		setcolor(COLORBARRA);
		if(j/3==swx&&i/3==swy)setcolor(COLORLETRA);
		rectangle((j*escala+215+espaciox)-1,(i*escala+180+espacioy)-1,(j*escala+215+20+espaciox)+1,(i*escala+180+20+espacioy)+1);
	 }
}

int posicion(int x,int y,int x1,int x2,int cant,int ini=24,int incre=17,int ancho=18){
 int i,j,sw;
 if(x>=x1&&x<=x2){
 for (i=ini,j=0;j<cant;i+=incre,j++)
 {
	if(y>=i&&y<=i+ancho)
	return(j);
	else sw=-1;
 }
 }
 else return(-1);
 return(sw);
}

void *SalvarVentana(){
 int x1=230,x2=639-230,y1=165,y2=479-165;
 void *pt;
 unsigned size=imagesize(x1-2,y1-2,x2+2,y2+2);
 pt=malloc(size);
 getimage(x1-2,y1-2,x2+2,y2+2,pt);
 return pt;
}

void RestaurarVentana(void *pt){
 int x1=230,y1=165;
 putimage(x1-2,y1-2,pt,COPY_PUT);
 free(pt);
}

int paleta(int &x,int &y,char *a){
 EscondeCursorMouse();
 void *pt=SalvarVentana();
 int j,TeclaMouse,escala=10,espaciox=48,espacioy=13;
 ventana(a);
 int colo[4][4];
 for(int col=1,i=0;i<12;i+=3)
	 for(j=0;j<12;j+=3){
		efecto4(j*escala+215+espaciox,i*escala+180+espacioy,j*escala+215+20+espaciox,i*escala+180+20+espacioy,col);
		colo[i/3][j/3]=col;
		col++;
	 }
 int swx=0,swy=10,jx=0,jy,po,COLORANTE;
 char Teclado;
 RestaurarMouse(x,y);
 do{
 do{
	MuestraMouse();
//(int x,int y,int x1,int x2,int cant,int ini=24,int incre=17,int ancho=18)
	jx=posicion(y,x,180+espacioy,290+espacioy,4,215+espaciox,31,21);
	jy=posicion(x,y,215+espaciox,325+espaciox,4,180+espacioy,31,21);
	if(jx!=swx||jy!=swy){
	 swx=jx;
	 swy=jy;
	 EscondeCursorMouse();
	 borrar(swx,swy);
	 MuestraMouse();
	}
	TeclaMouse=LeerTeclaXYMouse(x,y);
 }while((TeclaMouse!=1||swx==-1||swy==-1)&&!kbhit());
 COLORANTE=colo[swy][swx];
 if(!TeclaMouse){
 Teclado=getch();
 if(Teclado==ESC)swx=-1;
 if(!Teclado)Teclado=getch();}
 else{
 do{
	MuestraMouse();
	jx=posicion(y,x,180+espacioy,290+espacioy,4,215+espaciox,31,21);
	jy=posicion(x,y,215+espaciox,325+espaciox,4,180+espacioy,31);
	if(jx!=swx||jy!=swy){
	 swx=jx;
	 swy=jy;
	 EscondeCursorMouse();
	 borrar(swx,swy);
	 MuestraMouse();
	}
	TeclaMouse=LeerTeclaXYMouse(x,y);
 }while(TeclaMouse==1);
 COLORANTE=colo[swy][swx];
 if(swx==-1||swy==-1) COLORANTE=-1;
 }
 }while(Teclado!=ESC&&COLORANTE==-1);
 if(Teclado==ESC)COLORANTE=-1;
 EscondeCursorMouse();
 RestaurarVentana(pt);
 MuestraMouse();
 return COLORANTE;
}

void iniciar(char *a="c:\\tc\\bgi"){
 int manejador=DETECT,modo,errorcode;
 initgraph(&manejador,&modo,a);
 errorcode=graphresult();
 if(errorcode!=grOk){
  if(errorcode==-3){
   if(*a<='e'){a[0]=a[0]+1;iniciar(a);}
  }
  else{
  printf("Graphics error: %s\n", grapherrormsg(errorcode));
  printf("Press any key to halt:");
  getch();
  exit(1);}}
 delay(500);
 settextstyle(2,0,4);
}

void equis(int x, int y){
 setcolor(8);
 circle(x+2,y+2,16);
 setfillstyle(1,8);
 floodfill(x+2,y+2,8);
 setcolor(0);
 circle(x,y,16);
 setfillstyle(1,4);
 floodfill(x,y,0);
 setcolor(15);
 line(x-7,y-9,x+9,y+7);
 line(x-9,y-7,x+7,y+9);
 line(x-7,y-9,x-9,y-7);
 line(x+9,y+7,x+7,y+9);
 line(x-9,y+7,x+7,y-9);
 line(x-7,y+9,x+9,y-7);
 line(x-9,y+7,x-7,y+9);
 line(x+7,y-9,x+9,y-7);
 setfillstyle(1,15);
 floodfill(x,y,15);
 floodfill(x-4,y-4,15);
 floodfill(x-4,y+4,15);
 floodfill(x+4,y-4,15);
 floodfill(x+4,y+4,15);
}

void bmenu(char cont[][13],int pos[]){
 setcolor(COLORLETRA);
 for(int i=0;i<cantop;i++){
	outtextxy(pos[i],26,cont[i]);
	outtextxy(pos[i],27,"_");
 }
}

void babajo(char a[]){
 int mx=639,my=479;
 efecto4(5,my-25,mx-5,my-5,1);
 setcolor(15);
 centrar(0,my-18,a);
}

void baux(){
 int mx=639,my=479;
 efecto1(0,my-39+10,mx,my,COLORBARRA,15,8);
 efecto1(0,my-54,mx,my-39+9,COLORBARRA,15,8);
 efecto1(3,my-52,250,my-39+7,0,8,15);
 efecto1(252,my-52,400,my-39+7,0,8,15);
 efecto1(402,my-52,637,my-39+7,0,8,15);
}

void pantalla(){
 int mx=639,my=479;
 efecto4(2,46,mx-2,my-56,15);
 efecto1(622,5,635,18,0,7,0);
 efecto1(623,6,634,17,COLORBARRA,15,8);
 setcolor(0);
 line(625,8,632,15);
 line(626,8,632,14);
 line(625,9,631,15);
 line(632,8,625,15);
 line(631,8,625,14);
 line(632,9,626,15);
}

void pantalla1(){
 int mx=639,my=479;
 setfillstyle(1,0);
 bar(96,47,mx-4,my-58);
}

void menuP(char cont[][13],int pos[]){
 efecto3(1,1,638,478,"Control de Correspondencia");
 bmenu(cont,pos);
 pantalla();
 baux();
}

void menuG(char contenido[][15],char rayas[][9],int sep[],int cant,int x1,int x3,int ini,int sw=-1,int posante=-1){
 int separacion=17;
 int k,r,i,j,cb=COLORBARRA,cl=COLORLETRA,es=ELEMSELEC,ps=PALSELEC;
 for(i=ini,j=0;j<cant;i+=separacion,j++){
  if(sw==-1&&posante==-2){
  delay(15);
  efecto1(x1,ini,x3,i+separacion,cb,15,8);
  efecto1(x1-1,ini-1,x3+1,i+separacion+1,0,7,0);
  setcolor(cl);
  for(k=0,r=ini;k<=j;k++,r+=separacion){
   outtextxy(x1+8,r+1,contenido[k]);
   outtextxy(x1+8,r+2,rayas[k]);
   if(sep[k]){
    efecto1(x1+3,r+separacion-1,x3-3,r+separacion,0,8,15);
    setcolor(cl);
   }
  }
  }
  if(j==sw&&posante!=-2){
   setfillstyle(1,es);
   bar(x1+2,i+2,x3-2,i+separacion-3);
   setcolor(ps);
   outtextxy(x1+8,i+1,contenido[j]);
   outtextxy(x1+8,i+2,rayas[j]);
  }
  if(j==posante&&posante!=-2){
   setfillstyle(1,cb);
   bar(x1+2,i+2,x3-2,i+separacion-3);
   setcolor(cl);
   outtextxy(x1+8,i+1,contenido[j]);
   outtextxy(x1+8,i+2,rayas[j]);
  }
 }
}

void *Salvar(int cant,int x1,int x3,int ini){
 int i,j;
 void *pt;
 for(i=ini,j=0;j<cant;i+=21,j++);
 unsigned size=imagesize(x1-1,ini-1,x3+1,i+1);
 pt=malloc(size);
 if(pt)getimage(x1-1,ini-1,x3+1,i+1,pt);
 return pt;
}

void Recuperar(int x1,int ini,void *pt){
 if(pt){
 putimage(x1-1,ini-1,pt,COPY_PUT);
 free(pt);}
}

void borrar(int a[],int sw=-1,int posante=-1,int cs=15,int ci=8){
 for(int i=0;i<3;i++){
 if(posante==i||posante==-1){
 setcolor(7);
 rectangle(a[i],3,a[i+1]-1,22);}
 if(i==sw)efecto1(a[i],3,a[i+1]-1,22,0,cs,ci);}
}

void botones(){
 efecto1(214-1,269-1,286+1,296+1,0,7,0);
 efecto1(214,269,286,296,0,15,8);
 efecto1(353-1,269-1,425+1,296+1,0,7,0);
 efecto1(353,269,425,296,0,15,8);
 setcolor(COLORLETRA);
 outtextxy(240,280,"Si");
 outtextxy(379,280,"No");
 outtextxy(240,281,"_");
 outtextxy(379,281,"_");
}

void confirmacion(int &x,int &y,int &z){
 void *pt=SalvarVentana();
 int TeclaMouse,sw=10,j;
 char Teclado;
 ventana("Confirmaci¢n");
 setcolor(COLORLETRA);
 switch(z){
 case 0:centrar(0,210,"¨Deseas trabajar");
	centrar(0,230,"con switch?");
	break;
 case 1:centrar(0,200,"La palabra no se encuentra");
	centrar(0,220,"en la base de datos");
	centrar(0,240,"¨desea agregarla?");
	break;
 case 2:centrar(0,210,"¨Deseas agregar");
	centrar(0,230,"otro significado?");
	break;
 case 3:centrar(0,200,"La palabra ya existe");
	centrar(0,220,"en la base de datos");
	centrar(0,240,"¨desea agregar otro significado?");
	break;
 }
 botones();
 MuestraMouse();
 do{
 do{
	j=posicion(y,x,270,295,2,215,139,70);
	if(sw!=j){
	sw=j;
	EscondeCursorMouse();
	if(sw==0){
	 botones();
	 setcolor(COLORBARRA);
	 rectangle(351,267,427,298);
	 setcolor(0);
	 rectangle(212,267,288,298);
	}
	if(sw==1){
	 botones();
	 setcolor(COLORBARRA);
	 rectangle(212,267,288,298);
	 setcolor(0);
	 rectangle(351,267,427,298);
	}
	if(sw==-1){
	setcolor(COLORBARRA);
	rectangle(351,267,427,298);
	rectangle(212,267,288,298);
	}
	}
	MuestraMouse();
	TeclaMouse = LeerTeclaXYMouse(x,y);
	if(((TeclaMouse)&&sw==-1)&&(!(x>=180&&x<=459&&y>=150&&y<=329))){sound(50);delay(50);nosound();}
 }while((sw==-1||TeclaMouse!=1)&&!kbhit());
	sw=-1;
 if(!TeclaMouse)
 {
	Teclado=getche();
	if(Teclado>='a'&&Teclado<='z')Teclado-=32;
	switch(Teclado){
	case 'S'   :sw=0;break;
	case 'N'   :sw=1;break;
	case ESC   :sw=1;break;
	case ENTER :sw=0;break;
	default :sw=10;
 }
 }
 if(TeclaMouse)
 do{
	j=posicion(y,x,270,295,2,215,139,70);
	if(sw!=j){
	sw=j;
	EscondeCursorMouse();
	if(sw==0){
	 setcolor(COLORBARRA);
	 rectangle(212,267,288,298);
	 efecto1(214-1,269-1,286+1,296+1,0,8,15);
	 efecto1(214,269,286,296,0,0,7);
	 }
	if(sw==1){
	 setcolor(COLORBARRA);
	 rectangle(351,267,427,298);
	 efecto1(353-1,269-1,425+1,296+1,0,8,15);
	 efecto1(353,269,425,296,0,0,7);
	}
	if(sw==-1){
	botones();
	setcolor(COLORBARRA);
	rectangle(351,267,427,298);
	rectangle(212,267,288,298);
	}
	}
	MuestraMouse();
	TeclaMouse = LeerTeclaXYMouse(x,y);
 }while(TeclaMouse);
 }while(sw==10||sw==-1);
 if(!TeclaMouse)EscondeCursorMouse();
 EscondeCursorMouse();
 botones();
 z=sw;
 RestaurarVentana(pt);
}

void sinmouse(){
 setbkcolor(0);
 ventana("Error de instalacion");
 setcolor(0);
 centrar(0,225,"!El raton no esta instalado");
 centrar(0,240,"y su programa sera");
 centrar(0,255,"interrumpido indefinidamente­");
 setcolor(1);
 centrar(0,285,"PRESIONE CUALQUIER");
 centrar(0,305,"TECLA PARA CONTINUAR");
 equis(210,200);
 boton(282);
 closegraph();
 exit(1);
}

void sinmen(char a[]){
 ventana("No hay memoria disponible");
 setcolor(COLORLETRA);
 centrar(0,225,"El sintema no cuenta");
 centrar(0,240,"con la memoria suficiente");
 centrar(0,255,a);
 equis(210,200);
 boton(282);
}

void LeerModoGraf(char a[],int cant,int x1,int y1){
 int i,alt=textheight("I"),anc=textwidth("H"),j,color=15;
 char Teclado;
 for(i=0;i<cant;i++)
	a[i]=NULO;
 efecto4(x1,y1,x1+anc*cant+anc,y1+alt+8,color);
 i=0;
 setcolor(1);
 do{
	cursor(i*anc+x1+anc,y1+5,1);
	Teclado=getch();
	if(!Teclado){
	 Teclado=getch();
	 switch(Teclado){
	 case DER:if(i<cant-2)i++;break;
	 case IZQ:if(i>0)i--;break;
	 case DEL:for(j=i;j<cant-2;j++)
		  a[j]=a[j+1];
		  a[cant-1]=NULO;
		  break;
	 case HOME:i=0;break;
	 case FIN :i=strlen(a);break;
	 }
	}
	else{
	 switch(Teclado){
	 case BORRAR:if(i>0){
		     for(j=i-1;j<cant-1;j++)
		      a[j]=a[j+1];
		     a[cant-1]=NULO;
		     i--;}
		      else{
		       sound(450);
		       delay(500);
		       nosound();}
		     break;
	 case ESC:break;
	 case ENTER:break;
	 case ESP:if(i<cant-1){
		  for(j=cant-2;j>=i;j--)
		   a[j]=a[j-1];
		  a[i]=ESP;
		  i++;}
		  else{
		   sound(450);
		   delay(500);
		   nosound();}
		  break;
	 default:if(i<cant-1){
		 for(j=cant-2;j>=i;j--)
		  a[j]=a[j-1];
		 a[i]=Teclado;
		 i++;}
		 else{
		  sound(450);
		  delay(500);
		  nosound();}
	 }
	}
	efecto4(x1,y1,x1+anc*cant+anc,y1+alt+8,color);
	setcolor(1);
	outtextxy(x1+anc,y1+4,a);
 }while(Teclado!=ENTER||i<=0);
 while(a[0]==ESP)
 for(j=0;j<strlen(a);j++)
 a[j]=a[j+1];
 i=strlen(a)-1;
 while(a[i]==ESP){
	a[i]=NULO;
	i--;
 }
}

void LeerNum(int x,int y,int &num,int cant=7){
 char a[10];
 int i,sw;
 if(cant>9)cant=9;
 do{
 LeerModoGraf(a,cant,x,y);
 for(i=0,sw=0;i<strlen(a);i++)
  if(!(a[i]>='0'&&a[i]<='9'||a[i]=='-'))sw=1;
 }while(sw);
 num=atoi(a);
}

void boton(int y1){
 char Teclado;
 int dis=3,sw=-1,TeclaMouse,x,y;
 EscondeCursorMouse();
 efecto1(279,y1-1,639-279,y1+22,0,8,0);
 efecto1(280,y1,639-280,y1+21,0,15,8);
 setcolor(COLORLETRA);
 centrar(0,y1+4,"Aceptar");
 do{
 do{
 MuestraMouse();
	if(x>=280&&x<=639-280&&y>=y1&&y<=y1+21){
	if(sw!=0){
	 setcolor(COLORLETRA);
	 EscondeCursorMouse();
	 rectangle(280+dis,y1+dis,639-280-dis,y1+21-dis);
	 MuestraMouse();
	 sw=0;
	}
	}else{
	 if(sw!=-1){
	 setcolor(COLORBARRA);
	 EscondeCursorMouse();
	 rectangle(280+dis,y1+dis,639-280-dis,y1+21-dis);
	 MuestraMouse();
	 sw=-1;}
	}
	TeclaMouse = LeerTeclaXYMouse(x,y);
 }while(TeclaMouse!=1&&!kbhit());
 if(!TeclaMouse){
  Teclado=getch();
  if(!Teclado){
   Teclado=getch();
   sw=-1;
  }
  else
   if(Teclado==ENTER)sw=0;
 }
 else{
 sw=-1;
 setcolor(COLORBARRA);
 EscondeCursorMouse();
 MuestraMouse();
 do{
	if(x>=280&&x<=639-280&&y>=y1&&y<=y1+21){
	if(sw!=0){
	 setcolor(COLORLETRA);
	 EscondeCursorMouse();
	 rectangle(280+dis,y1+dis,639-280-dis,y1+21-dis);
	 efecto1(279,y1-1,639-279,y1+22,0,8,15);
	 efecto1(280,y1,639-280,y1+21,0,0,7);
	 MuestraMouse();
	 sw=0;
	}
	}else{
	 if(sw!=-1){
	 setcolor(COLORBARRA);
	 EscondeCursorMouse();
	 rectangle(280+dis,y1+dis,639-280-dis,y1+21-dis);
	 efecto1(279,y1-1,639-279,y1+22,0,8,0);
	 efecto1(280,y1,639-280,y1+21,0,15,8);
	 MuestraMouse();
	 sw=-1;}
	}
	TeclaMouse = LeerTeclaXYMouse(x,y);
 }while(TeclaMouse);
 }
 }while(sw!=0);
 EscondeCursorMouse();
 efecto1(279,y1-1,639-279,y1+22,0,8,0);
 efecto1(280,y1,639-280,y1+21,0,15,8);
}

void sindisk(){
 ventana("Drive A: Not Ready");
 setcolor(0);
 centrar(0,215,"No se pudo tener acceso");
 centrar(0,235,"al dispositivo a:/ inserte");
 centrar(0,255,"el disco en la unidad.");
 equis(210,200);
 boton(282);
}

void comprovar(){
 int result;
 char buffer[512];
 do{
  result = biosdisk(4,0,0,0,0,1,buffer);
  result &= 0x02;
  (result) ?(efecto4(0,0,0,0,15)):(sindisk());
 }while(result!=2);
}

void diskprotec(){
 int result;
 char buffer[512];
 do{
  result = biosdisk(3,0,0,0,0,1,buffer);
  result &= 0x03;
  if(result==3){
   EscondeCursorMouse();
   ventana("Error drive A");
   setcolor(0);
   centrar(0,195,"Compruebe que el disco");
   centrar(0,215,"no esta lleno, ni protegido");
   centrar(0,235,"contra escritura y presione");
   centrar(0,255,"cualquier tecla para continuar");
   equis(210,200);
   boton(282);
  }
  else
   efecto4(0,0,0,0,15);
 }while(result==3);
}

const char *ArchivoT="c:\confisist.gmc";

void Grabar(){
	int comp[4]={COLORBARRA,COLORLETRA,ELEMSELEC,PALSELEC};
//	diskprotec();
	fstream Archivo;
	Archivo.open(ArchivoT,ios::out|ios::binary|ios::trunc);
	if (!Archivo) {
	COLORBARRA=7;
	COLORLETRA=0;
	ELEMSELEC=1;
	PALSELEC=15;
		}
	else{
		for(int i=0;i<4;i++)
			Archivo.write((char *)&comp[i],sizeof(int));
		Archivo.close();
		}
}

void CargarArchivo(){
	int comp[4];
	cout<<"Cargando configuracion del sistema."<<endl
	<<"Todos los derechos reservados GMC CCAL"<<endl
	<<"Creada el 30 de febrero del 2000";
//	comprovar();
	int i=0;
	ifstream in(ArchivoT);
	if (!in){
		Grabar();
	}
	else {
		while (in.read((unsigned char *)&comp[i],sizeof(comp[i])))
		i++;
		in.close();
		COLORBARRA=comp[0];
		COLORLETRA=comp[1];
		ELEMSELEC=comp[2];
		PALSELEC=comp[3];
	}
}

void hora(int x=0){
 struct time ta;
 static struct time tant={0,0,0,0};
 char cad[20];
 if(x)tant.ti_min=x;
 gettime(&ta);
 if(ta.ti_min!=tant.ti_min){
  if((int)ta.ti_hour<12)
  if((int)ta.ti_min<10)
  ostrstream(cad,20)<<(int)ta.ti_hour<<":0"<<(int)ta.ti_min<<" AM"<<ends;
  else
  ostrstream(cad,20)<<(int)ta.ti_hour<<":"<<(int)ta.ti_min<<" AM"<<ends;
  if((int)ta.ti_hour==12)
  if((int)ta.ti_min<10)
  ostrstream(cad,20)<<(int)ta.ti_hour<<":0"<<(int)ta.ti_min<<" Me"<<ends;
  else
  ostrstream(cad,20)<<(int)ta.ti_hour<<":"<<(int)ta.ti_min<<" Me"<<ends;
  if((int)ta.ti_hour>12)
  if((int)ta.ti_min<10)
  ostrstream(cad,20)<<((int)ta.ti_hour-12)<<":0"<<(int)ta.ti_min<<" PM"<<ends;
  else
  ostrstream(cad,20)<<((int)ta.ti_hour-12)<<":"<<(int)ta.ti_min<<" PM"<<ends;
  efecto1(572,479-24,635,479-6,COLORBARRA,8,15);
  settextstyle(2,0,4);
  setcolor(COLORLETRA);
  outtextxy(580,479-22,cad);
  tant.ti_min=ta.ti_min;
 }
}

int Espacio(char conteni[][15],int cop){
 int mayor=0;
 for(int i=0;i<cop;i++)
  if(mayor<textwidth(conteni[i]))mayor=textwidth(conteni[i]);
 return mayor;
}
# endif






