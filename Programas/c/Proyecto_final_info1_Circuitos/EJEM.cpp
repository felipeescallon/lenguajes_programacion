/*************************************************************************************
Este es un ejemplo de como utlizar las librerias que manejan el modografico
y el mouse para preguntas y/o comentarios escriba a gedarufi@hotmail.com
**************************************************************************************/

#include"mouse.h"
#include"modograf.h"
const esp=10,Y1=25,Y2=42;
void nombres(){
 void *pt=SalvarVentana();
 ventana("GMCsoft CCAL");
 setcolor(COLORLETRA);
 centrar(0,195,"Este es un ejemplo de");
 centrar(0,215,"como utilizar la libreria");
 centrar(0,235,"de modo grafico y mouse");
 centrar(0,255,"gedarufi@hotmail.com");
 boton(282);
 RestaurarVentana(pt);
}

void inicio(int &x,int &y,int ini,char contenido[][13],int posiciones[],int p)
{
 int X1=ini-esp/2,X2,Click;
 char conteni[][15]={"Acerca de...","Color Barra","Color Letra","Color Selec","Color PalSelec","Salir"},
 rayas[][9]={" _","      _","      _","       _","       _","_"},Teclado;
 int TeclaMouse,j,sw=-2,cop=6;
 int sep[]={1,0,0,0,1,0};
 X2=Espacio(conteni,cop)+X1+15;
 void *pt=Salvar(cop,X1,X2,45);
 MuestraMouse();
 do{
 Click=0;
 do{
 j=posicion(x,y,X1,X2,cop,45);
 if(MouseQuieto(x,y)){
 if(sw!=j){
	EscondeCursorMouse();
	menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	sw=j;
	MuestraMouse();
	}
 }
 hora();
 TeclaMouse = LeerTeclaXYMouse(x,y);
 } while (TeclaMouse!=1&&!kbhit());
 if(!TeclaMouse){
 Teclado=getche();
 if(!Teclado){
  Teclado=getch();
  switch(Teclado){
  case ARR:j=sw;
	   j--;
	   if(j<0)j=cop-1;
	   EscondeCursorMouse();
	   menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	   sw=j;
	   MuestraMouse();
	   break;
  case ABJ:j=sw;
	   j++;
	   if(j>=cop)j=0;
	   EscondeCursorMouse();
	   menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	   sw=j;
	   MuestraMouse();
	   break;
  }
 }
 else{
 A_Mayuscula(Teclado);
 switch(Teclado){
 case'C':sw=0;Click=1;break;
 case'B':sw=1;Click=1;break;
 case'L':sw=2;Click=1;break;
 case'E':sw=3;Click=1;break;
 case'A':sw=4;Click=1;break;
 case'S':sw=5;Click=1;break;
 case ENTER:Click=1;break;
 case ESC:sw=-10;Click=1;break;
 default:sw=cop+1;
 }
 }
 }
 if(TeclaMouse){
 if(sw!=-1){
 do{
  j=posicion(x,y,X1,X2,cop,45);
  if(sw!=j){
	EscondeCursorMouse();
	menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	sw=j;
	MuestraMouse();
	}
  hora();
  TeclaMouse = LeerTeclaXYMouse(x,y);
 }while(TeclaMouse);
 }
  Click=1;
 }
 }while(!Click);
 EscondeCursorMouse();
 Recuperar(X1,45,pt);
 setcolor(COLORBARRA);
 rectangle(posiciones[p]-(esp/2),Y1,posiciones[p]+textwidth(contenido[p])+(esp/2),Y2);
 int colorante;
 switch(sw){
	case 0:nombres();
	       break;
	case 1:colorante=COLORBARRA;
	       COLORBARRA=paleta(x,y,"Color de las barras");
	       if(COLORBARRA<0)COLORBARRA=colorante;
	       else
	       if(colorante!=COLORBARRA){
		EscondeCursorMouse();
		menuP(contenido,posiciones);
		hora(-1);
		MuestraMouse();}
	       break;
	case 2:colorante=COLORLETRA;
	       COLORLETRA=paleta(x,y,"Color de la letra");
	       if(COLORLETRA<0)COLORLETRA=colorante;
	       else
	       if(colorante!=COLORLETRA){
		EscondeCursorMouse();
		menuP(contenido,posiciones);
		hora(-1);
		MuestraMouse();}
	       break;
	case 3:colorante=ELEMSELEC;
	       ELEMSELEC=paleta(x,y,"Elemento seleccionado");
	       if(ELEMSELEC<0)ELEMSELEC=colorante;
	       else
	       if(colorante!=ELEMSELEC){
		EscondeCursorMouse();
		menuP(contenido,posiciones);
		hora(-1);
		MuestraMouse();}
	       break;
	case 4:colorante=PALSELEC;
	       PALSELEC=paleta(x,y,"Letra seleccionada");
	       if(PALSELEC<0)PALSELEC=colorante;
	       else
	       if(colorante!=PALSELEC){
		EscondeCursorMouse();
		menuP(contenido,posiciones);
		hora(-1);
		MuestraMouse();}
	       break;
	case 5:MuestraMouse();
	       Grabar();
	       EscondeCursorMouse();
	       closegraph();
	       exit(1);
	       break;
 }
}

void buscar(int &x,int &y,int ini,char contenido[][13],int posiciones[],int p)
{
 int X1=ini-esp/2,X2,Click;
 char conteni[][15]={"Por Numero","Por Dia","Por Mes","Por Remitente"},
 rayas[][9]={"    _","    _","    _","    _"},Teclado;
 int TeclaMouse,j,sw=-2,cop=4;
 int sep[]={1,0,1,0};
 X2=Espacio(conteni,cop)+X1+15;
 void *pt=Salvar(cop,X1,X2,45);
 MuestraMouse();
 do{
 Click=0;
 do{
 j=posicion(x,y,X1,X2,cop,45);
 if(MouseQuieto(x,y)){
 if(sw!=j){
	EscondeCursorMouse();
	menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	sw=j;
	MuestraMouse();
	}
 }
 hora();
 TeclaMouse = LeerTeclaXYMouse(x,y);
 } while (TeclaMouse!=1&&!kbhit());
 if(!TeclaMouse){
 Teclado=getche();
 if(!Teclado){
  Teclado=getch();
  switch(Teclado){
  case ARR:j=sw;
	   j--;
	   if(j<0)j=cop-1;
	   EscondeCursorMouse();
	   menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	   sw=j;
	   MuestraMouse();
	   break;
  case ABJ:j=sw;
	   j++;
	   if(j>=cop)j=0;
	   EscondeCursorMouse();
	   menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	   sw=j;
	   MuestraMouse();
	   break;
  }
 }
 else{
 A_Mayuscula(Teclado);
 switch(Teclado){
 case'N':sw=0;Click=1;break;
 case'D':sw=1;Click=1;break;
 case'M':sw=2;Click=1;break;
 case'R':sw=3;Click=1;break;
 case ENTER:Click=1;break;
 case ESC:sw=-10;Click=1;break;
 default:sw=cop+1;
 }
 }
 }
 if(TeclaMouse){
 if(sw!=-1){
 do{
 j=posicion(x,y,X1,X2,cop,45);
 if(sw!=j){
	EscondeCursorMouse();
	menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	sw=j;
	MuestraMouse();
	}
 hora();
 TeclaMouse = LeerTeclaXYMouse(x,y);
 }while(TeclaMouse);
 }
 Click=1;
 }
 }while(!Click);
 EscondeCursorMouse();
 Recuperar(X1,45,pt);
 setcolor(COLORBARRA);
 rectangle(posiciones[p]-(esp/2),Y1,posiciones[p]+textwidth(contenido[p])+(esp/2),Y2);
 int colorante;
 switch(sw){
	case 0:break;
	case 1:break;
	case 2:break;
	case 3:break;
 }
}

void modificar(int &x,int &y,int ini,char contenido[][13],int posiciones[],int p)
{
 int X1=ini-esp/2,X2,Click;
 char conteni[][15]={"Anexar","Cambiar","Eliminar   "},
 rayas[][9]={"_","_","_"},Teclado;
 int TeclaMouse,j,sw=-2,cop=3;
 int sep[]={1,1,0};
 X2=Espacio(conteni,cop)+X1+15;
 void *pt=Salvar(cop,X1,X2,45);
 MuestraMouse();
 do{
 Click=0;
 do{
 j=posicion(x,y,X1,X2,cop,45);
 if(MouseQuieto(x,y)){
 if(sw!=j){
	EscondeCursorMouse();
	menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	sw=j;
	MuestraMouse();
	}
 }
 hora();
 TeclaMouse = LeerTeclaXYMouse(x,y);
 } while (TeclaMouse!=1&&!kbhit());
 if(!TeclaMouse){
 Teclado=getche();
 if(!Teclado){
  Teclado=getch();
  switch(Teclado){
  case ARR:j=sw;
	   j--;
	   if(j<0)j=cop-1;
	   EscondeCursorMouse();
	   menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	   sw=j;
	   MuestraMouse();
	   break;
  case ABJ:j=sw;
	   j++;
	   if(j>=cop)j=0;
	   EscondeCursorMouse();
	   menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	   sw=j;
	   MuestraMouse();
	   break;
  }
 }
 else{
 A_Mayuscula(Teclado);
 switch(Teclado){
 case'A':sw=0;Click=1;break;
 case'C':sw=1;Click=1;break;
 case'E':sw=2;Click=1;break;
 case ENTER:Click=1;break;
 case ESC:sw=-10;Click=1;break;
 default:sw=cop+1;
 }
 }
 }
 if(TeclaMouse){
 if(sw!=-1){
 do{
 j=posicion(x,y,X1,X2,cop,45);
 if(sw!=j){
	EscondeCursorMouse();
	menuG(conteni,rayas,sep,cop,X1,X2,45,j,sw);
	sw=j;
	MuestraMouse();
	}
 hora();
 TeclaMouse = LeerTeclaXYMouse(x,y);
 }while(TeclaMouse);
 }
 Click=1;
 }
 }while(!Click);
 EscondeCursorMouse();
 Recuperar(X1,45,pt);
 setcolor(COLORBARRA);
 rectangle(posiciones[p]-(esp/2),Y1,posiciones[p]+textwidth(contenido[p])+(esp/2),Y2);
 int colorante;
 switch(sw){
	case 0:break;
	case 1:break;
	case 2:break;
 }
}

void main()
{
 char contenido[][13]={"Inicio","Buscar","Modificar"},Teclado;
 cantop=3;
 int posiciones[3],i,TeclaMouse,x,y,j,sw,sw1=0,Click=0;
 COLORBARRA=7;
 COLORLETRA=0;
 ELEMSELEC=1;
 PALSELEC=15;
 iniciar();
 CargarArchivo();
 posiciones[0]=10;
 for(i=1;i<cantop;i++)
	posiciones[i]=posiciones[i-1]+esp+textwidth(contenido[i-1]);
 menuP(contenido,posiciones);
 if(!IniciaMouse(TeclaMouse))
	sinmouse();
 Ir_a_XYMouse(320,240);
 MuestraMouse();
 x=getmaxx();
 y=getmaxy();
 for(;;){
 do{
 Click=0;
 do{
	MuestraMouse();
	if(MouseQuieto(x,y)){
	if(y>=Y1&&y<=Y2){
	j=0;
	while(j<cantop&&!(x>=posiciones[j]-(esp/2)&&x<=posiciones[j]+textwidth(contenido[j])+(esp/2)))j++;
	if(sw!=j&&j<cantop){
	 EscondeCursorMouse();
	 setcolor(COLORBARRA);
	 for(sw=0;sw<cantop;sw++)
	 rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	 efecto1(posiciones[j]-(esp/2),Y1,posiciones[j]+textwidth(contenido[j])+(esp/2),Y2,0,15,8);
	 sw=j;
	 MuestraMouse();
	}
	else{
	if(sw!=-1&&j==cantop){
	EscondeCursorMouse();
	setcolor(COLORBARRA);
	for(sw=0;sw<cantop;sw++)
	rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	sw=-1;
	MuestraMouse();}}
	}
	else{
	 if(sw!=-1){
	 EscondeCursorMouse();
	 setcolor(COLORBARRA);
	 for(sw=0;sw<cantop;sw++)
	 rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	 sw=-1;
	 MuestraMouse();}}}
	 hora();
	TeclaMouse=LeerTeclaXYMouse(x,y);
 }while(TeclaMouse!=1&&!kbhit());
 if(!TeclaMouse){
	Teclado=getch();
	if(!Teclado){
	 Teclado=getch();
	if(Teclado==107)sw1=1;
	else{
	  switch(Teclado){
	  case IZQ:EscondeCursorMouse();
		   setcolor(COLORBARRA);
		   if(sw>=0)rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
		   sw--;
		   if(sw<0)sw=cantop-1;
		   efecto1(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2,0,15,8);
		   MuestraMouse();
		   break;
	  case DER:EscondeCursorMouse();
		   setcolor(COLORBARRA);
		   if(sw>=0)rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
		   sw++;
		   if(sw>=cantop)sw=0;
		   efecto1(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2,0,15,8);
		   MuestraMouse();
		   break;
	  case ABJ:if(sw>=0){
		   EscondeCursorMouse();
		   Click=1;
		   efecto1(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2,0,8,15);
		   MuestraMouse();}
		   break;
	  default:Ascci_A_Car(Teclado);
		  j=0;
		  while(j<cantop&&Teclado!=contenido[j][0])j++;
		  if(j<cantop){
		  sw=j;
		  efecto1(posiciones[j]-(esp/2),Y1,posiciones[j]+textwidth(contenido[j])+(esp/2),Y2,0,8,15);
		  Click=1;
		  }
	 }
	}
	}
   else
   if(sw>=0)
   switch(Teclado){
   case ENTER:EscondeCursorMouse();
	      efecto1(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2,0,8,15);
	      MuestraMouse();
	      Click=1;
	      break;
   default:EscondeCursorMouse();
	   setcolor(COLORBARRA);
	   for(j=0;j<cantop;j++)
	    rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	   MuestraMouse();
	   sw=-1;
	   break;
   }
  }
 if(TeclaMouse){
 sw=-1;
 do{
	if(y>=Y1&&y<=Y2){
	j=0;
	while(j<cantop&&!(x>=posiciones[j]-(esp/2)&&x<=posiciones[j]+textwidth(contenido[j])+(esp/2)))j++;
	if(sw!=j&&j<cantop){
	 EscondeCursorMouse();
	 setcolor(COLORBARRA);
	 for(sw=0;sw<cantop;sw++)
	 rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	 efecto1(posiciones[j]-(esp/2),Y1,posiciones[j]+textwidth(contenido[j])+(esp/2),Y2,0,8,15);
	 sw=j;
	 MuestraMouse();
	}
	else{
	 if(sw!=-1&&j==cantop){
	 EscondeCursorMouse();
	 setcolor(COLORBARRA);
	 for(sw=0;sw<cantop;sw++)
	 rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	 sw=-1;
	 MuestraMouse();}}
	}
	else{
	 if(sw!=-1){
	 EscondeCursorMouse();
	 setcolor(COLORBARRA);
	 for(sw=0;sw<cantop;sw++)
	 rectangle(posiciones[sw]-(esp/2),Y1,posiciones[sw]+textwidth(contenido[sw])+(esp/2),Y2);
	 sw=-1;
	 MuestraMouse();}}
	 if(sw1==0)
	 if(x>=622&&y>=5&&x<=635&&y<=18){
	   EscondeCursorMouse();
	   efecto1(622,5,635,18,0,8,15);
	   efecto1(623,6,634,17,0,0,7);
	   sw1=1;
	   MuestraMouse();
	 }
	 if(sw1==1)
	 if(!(x>=622&&y>=5&&x<=635&&y<=18)){
	   EscondeCursorMouse();
	   efecto1(622,5,635,18,0,7,0);
	   efecto1(623,6,634,17,0,15,8);
	   sw1=0;
	   MuestraMouse();
	 }
	TeclaMouse=LeerTeclaXYMouse(x,y);
 }while(TeclaMouse);
 Click=sw!=-1;
 }
 if(sw1)Click=1;
 }while(!Click);
 if(!sw1){
 RestaurarMouse(x,y);
 switch(sw){
	case 0:EscondeCursorMouse();
	       inicio(x,y,posiciones[sw],contenido,posiciones,sw);
	       break;
	case 1:EscondeCursorMouse();
	       buscar(x,y,posiciones[sw],contenido,posiciones,sw);
	       break;
	case 2:EscondeCursorMouse();
	       modificar(x,y,posiciones[sw],contenido,posiciones,sw);
	       break;
 }
 }
  else{
   EscondeCursorMouse();
   efecto1(622,5,635,18,0,7,0);
   efecto1(623,6,634,17,0,15,8);
   MuestraMouse();
   Grabar();
   closegraph();
   exit(1);
   }
 }
}





