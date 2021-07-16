#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>    //maneja el isalpha
#include <conio.h>    //
#include <string.h>   //maneja funciones de caracteres
#include <dos.h>      // maneja el delay
#include <graphics.h>

char palabras[12][10];
char palabra[12],respuesta[12],repet[12];
char letra,opc,categor;
int z,d,comparar,longitud,i,oport,a,j,bandera;

int categoria(void);    //declaracion de cabeceras de la funciones
int elegir(int);
int imprime(void);

int presentacion()     //declaracion y cuerpo de funcion para presentacion
{
  int z=0;
  while(z<40)
  {
    cleardevice();         //limpia pantalla
    moveto(6.5*z,110);  //coordenadas en (x,y) se mueve en x
    setcolor(2);         //color verde
    settextstyle(TRIPLEX_FONT, HORIZ_DIR,6);
			 //estilo de letra parametros son(tipo_letra,direccion
			 //  vertical(1) o haorizontal(0), size o tama¤o)

    outtext("JUEGO EL ");  //salida de texto
    moveto(600-8.5*z,150); //(x,y) se mueve en x y en este
			   //lo hace de atras hacia adelante
    setcolor(7);          //color lightgray
    settextstyle(TRIPLEX_FONT, HORIZ_DIR,6);
			//estilo de letra parametros son(tipo_letra,direccion
			 //  vertical(1) o horizontal(0), size o tama¤o)
    outtext("AHORCADO");    //salida de texto
    z++;           //aumenta el contador
    delay(100);  //funcion que se aplica en intervalos
		//parametro dado en milisegundos
  }
  moveto(200,340);    //(x,y) no se mueve
  setcolor(2);        //clor green
  settextstyle(DEFAULT_FONT, HORIZ_DIR,1);
	     //estilo de letra parametros(tipo_letra,direccion en este caso
	     // vertical,size o tama¤o)
  outtext("ANDRES FELIPE CAMPO"); //salida de texto
  moveto(300,360);      //(x,y)
  setcolor(7);          //color lightgray
  settextstyle(DEFAULT_FONT, HORIZ_DIR,1);
	  //estilo de letra parametros(tipo_letra,direccion en este caso
	  // vertical,size o tama¤o)
  outtext("WILFER FERNEY GOMEZ");  //salida de texto
  delay(5000);                   // intervalo de tiempo
  cleardevice();                 //limpia de pantalla
  return 0;          //indica que returna cero si no hay errores
}

	   /****************	FUNCION PRINCIPAL***********************/

void main(void)
{
  int gdriver = DETECT, gmode, errorcode, midx,midy,x,y;
	  //EL MOLDE PARA GRAFICOS, EL GDRIVER FUNCIONA COMO CONTROLADOR
	  //DEL GRAFICO,EL DETECT IDENTIFICA EL TIPO DE VIDEO SI ES EGA,
	  //U OTRO,GMODE LLAMA EL ARCHIVO PARA EJECUTAR GRAFICOS

  initgraph(&gdriver, &gmode, "e:\\tc\\bgi");
		//INICIALIZA EL MODO GRAFICO BUSCANDO LA DIRECCION DEL ARCHIVO
		//PARA MANEJO DE GRAFICOS
  errorcode = graphresult();
		//LA FUNCION GRAPHRESULT RETORNA UN VALOR QUE  INDICA QUE
		//ERROR SE ESTA PRESENTANDO EN UN RANGO
		//DE - 15 A 0 Y SE LO ASIGA A ERRORCODE
  if (errorcode != grOk)
		//SI EL RESULTADO DE GRAPHRESULT ES DIFERENTE DE GROK(0),
		//OSEA SI HAY UN ERROR EN LA GRAFICA IMPRIMIRA EL SIGUIENTE
		//TEXTO,GARANTIZA LA VERIFICACION DE ERRORES
  {
    printf("error en el grafico: %s\n", grapherrormsg(errorcode));
	       // LA FUNCION IMPRIME QUE CLASE DE ERRROR SE HA
	       //COMETIDO TENIENDO COMO PARAMETRO LA VARIABLE ERRORCODE
    printf("Presione una tecla cualquiera para salir:");
    getch(); //IDENTIFICA CUALQUIER LETRA PARA SALIR SIN ECO
    exit(1); //SALE DEL PROGRAMA EL PARAMETRO 1 INDICA QUE HAY ERROR
  }
  midx = getmaxx()/ 2; //VARIABLE QUE SE LE ASIGNA EL MAXIMA VALOR
	//DE X Y LUEGO SE UBICA EN LA MITAD AL DIVIDIRLO POR DOS
  midy = getmaxy()/ 3;   //VARIABLE QUE SE LE ASIGNA EL MAXIMA VALOR
	//DE Y Y LUEGO SE UBICA EN ELTERCIO SUOERIOR AL DIVIDIRLO POR TRES
  setcolor(WHITE);    //COLOR LETRA
  settextjustify(CENTER_TEXT, CENTER_TEXT);
	 // justificacion del texto parametros (x,y)

  do{
     cleardevice();//limpia pantalla
     setcolor(15);//color white
     settextstyle(4, 0, random(15));
			//parametros(tipo_letra gotica,direccion horizontal,
			//tama¤o de letra aleatorio escogido entre 0 y 15
     outtextxy(midx, midy,"Info1");
			//salida de texto en (x,y) parametros (coordenadax,
			//coordenada y, texto)
     setcolor(12);//color lightred
     settextstyle(2,0,random(10));
			//parametros(tipo_letra SMALLFONTS,direccion horizontal,
			//tama¤o aleatorio)
     outtextxy(150,400,"una tecla para continuar");
			//salida de texto(x,y)parametros (coordenadas x,
			//coordenadas y,texto)
     delay(50); //intervalos de tiempo en milisegundos

  }while(!kbhit());
	 //repite mientras se oprime cualquier tecla
  getch();
  presentacion();
  closegraph();
  delay(1000);
  clrscr();
  do{
    categoria();
    textbackground(BLUE);  /*color fondo azul*/
    textcolor(WHITE);      /*color letra blanca*/
    clrscr();
    switch(categor)
    {
	case '1':
		gotoxy(25,6);printf("CATEGORIA:ANIMALES");
		break;
	case '2':
		gotoxy(25,6);printf("CATEGORIA:DEPORTES");
		break;
	case '3':
		gotoxy(25,6);printf("CATEGORIA:PAISES");
		break;
	case '4':
		gotoxy(25,6);printf("CATEGORIA: CAPITALES");
		break;
	case '5':
		gotoxy(25,6);printf("CATEGORIA: CARROS");
		break;
	case '6':
		gotoxy(25,6);printf("CATEGORIA:  FRUTAS");
		break;
	case '7':
		gotoxy(25,6);printf("CATEGORIA:  NOMBRES");
		break;
	case '8':
		gotoxy(25,6);printf("CATEGORIA:  CANTANTES");
		break;
    }
    z=comparar=d=i=longitud=j=bandera=oport=a=0;//inicia cada variable a cero
    for(z=0;z<10;z++)   //limpia la cadena tambien se puede hacer con un NULL
    {
      respuesta[z]='\0';
    }
    for(z=1;z<=13;z++) //imprime las primeas 13 letras del abecedario
		       //verticalmente con intervalos de tiempo
    {
      gotoxy(65,(1.5*z)+0.3);//(x,y)
      delay(50);
      printf("%c",(65+z-1));     //imprime caracter y al aumentael contador;
				  //imprime cada caracter CODIGO ASCII
    }

    for(z=1;z<=13;z++)           //IMPRIME LAS OTRAS TRECE LETRAS
    {
      gotoxy(70,(1.5*z)+0.3);
      delay(50);
      printf("%c",65+(z-1)+13);
    }
    randomize();  //esta funcion elige un numero aleatorio
    d=random(9);  // para escoger una palabra entre las 9 opciones posibles
    elegir(d);   //ESTA FUNCION ELIGE ALEATORIAMENTE UNA PALABRA
						  //PARAMETRO VARIABLE d

    longitud=strlen(palabra); //asigna a esta variable la cantidad
									   // de caracteres de la palabra
    imprime(); //llama la funcion
    do{
       gotoxy(25,10);
       printf("ADIVINA LA PALABRA");
       gotoxy(25,18);
       printf("ELIGE LAS LETRAS");

	 do{       // toupper convierte a mayusculas LAS QUE ENTRAN
	   letra=toupper(getch()); //con este do no se admiten numeros
	 }while(!isalpha(letra));    //ya que el isalpha solo recibe caracteres

       gotoxy(10+oport*3,22);//(x,y) utiliza el oport como contador
       printf("%c",letra);   //IMPRIME LAS LETRAS QUE UNO HA ESCOGIDO
       gotoxy(25,16);
       printf("INTENTO #(%i),MAXIMO 15 OPORTUNIDADES",oport+1);
		//IMPRIME EL NUMERO DE OPORTUNIDADES EJECUTADAS
       for(z=0;z<longitud;z++)//CONTADOR HASTA LONGITUD DE LA PALABRA
       {     //VA COMPARANDO LA LETRA CON CADA CASILLA DEL VECTOR
	 if(letra==palabra[z])
	 {
	   gotoxy(8+z*4+18,20);  //SI SON IGUALES LA UBICA DEPENDIENDO DEL
	   printf("%c",letra);   //CONTADOR EN DONDE LA ENCONTRO
	   respuesta[z]=letra;   //CARGA ESTE VECTOR CON LAS LETRAS
		//QUE CUMPLEN LA CONDICION UBICANDOLAS EN LA POSICION EN
		 //QUE LAS ENCONTRO
	 }
       }
       comparar=strcmp(palabra,respuesta);  //ASIGNA A LA VARIABLE EL VALOR
		//QUE RETORNA LA FUNCION strcmp COMPARA LAS DOS CADENAS
       if(comparar==0) //SI EL VALOR DE LA VARIABLE ES IGUAL A CERO
				//QUIERE DECIR QUE LAS CADENAS SON IGUALES
       {
	  gotoxy(25,23);
	  textcolor(GREEN);
	  cprintf("CORRECTO");
       }
       oport++;      //AUMENTA EL CONTADOR DE OPORTUNIDADES
    }while(oport<15 && comparar!=0);
	 //REPITE TODO EL PROCESO MIENTRAS EL CONTADOR SEA MENOR QUE 15
	 //QUE ES EL NUMERO MAXIMO DE OPORTUNIDADES Y MIENTRAS LA VARIABLE
	 //COMPARAR SEA DIFERENTE DE CERO OSEA QUE LAS CADENAS SEAN DIFERENTES
    if(oport==15)
	 //SI EL CONTADOR LLEGA A 15 QUIERE DECIR QUE SE ACABO LAS OPORTUNIDADES
    {
      gotoxy(25,23);
      textcolor(RED);
      cprintf("INCORRECTO LA PALABRA ES %s",palabra);
    }
    gotoxy(25,25);
    printf("PARA CONTINUAR PRESIONE \'C\' PARA SALIR \'S\'");
    do{
       opc=toupper(getch()); //LEE LO QUE EL USUARIO DIGITA Y LO
		 //TRANSFORMA A MAYUSCULAS
    }while(opc!='C' && opc!='S');
	 //REPITE HASTA QUE EL USUARIO DIGITE C(CONTINUAR) O S(SALIR)
  }while(opc!='S');
	 //REPITE EL JUEGO HASTA QUE EL USUARIO DIGITE S
  clrscr();
  exit(1);
  return;
}
   /**************FIN FUNCION PRINCIPAL******************************/

cargaranimales()   //CARGA LAS CADENAS
{
  strcpy(palabras[0],"PERRO");  //COPIA LA CADENA PERRO
  strcpy(palabras[1],"CABALLO");//AL VECTOR
  strcpy(palabras[2],"GALLINA");
  strcpy(palabras[3],"GATO");
  strcpy(palabras[4],"CULEBRA");
  strcpy(palabras[5],"CONEJO");
  strcpy(palabras[6],"COCODRILO");
  strcpy(palabras[7],"PATO");
  strcpy(palabras[8],"ZANCUDO");
  strcpy(palabras[9],"VENADO");
  return 0;

}
			 //FUNCION PARA ELEGIR PALABRA PARAMETRO
			 //cuyo parametro el numero dado alatoriamente

elegir(int num)  // declaracion y cuerpo de funcion
{
   strcpy(palabra,palabras[num]);
			 //copia el vector a la cadena palabra
   return 0;
}


			imprime()    //declaracion y cuerpo de funcion
	 {
			for(z=1;z<=longitud;z++) //imprime el numero de acuerdo a la
			{                       //longitud de la palabra
			gotoxy(8+z*4+14,20);printf("");
	 }      //se deja la mismas coordenadas que la de la ubicacion de la
			//letra para que vaya destapando las caritas
			return 0;
	 }




	 cargardeportes()
	 {
		 strcpy(palabras[0],"BILLAR");
		 strcpy(palabras[1],"BASEBALL");
		 strcpy(palabras[2],"GOLF");
		 strcpy(palabras[3],"TENNIS");
		 strcpy(palabras[4],"BEISBOL");
		 strcpy(palabras[5],"PINPON");
		 strcpy(palabras[6],"SOCCER");
		 strcpy(palabras[7],"NATACION");
		 strcpy(palabras[8],"FUTBOL");
		 strcpy(palabras[9],"CICLISMO");
		 return 0;

	}

	cargarpaises()
	{
		 strcpy(palabras[0],"PARAGUAY");
		 strcpy(palabras[1],"URUGUAY");
		 strcpy(palabras[2],"FRANCIA");
		 strcpy(palabras[3],"ITALIA");
		 strcpy(palabras[4],"MEXICO");
		 strcpy(palabras[5],"CUBA");
		 strcpy(palabras[6],"BELGICA");
		 strcpy(palabras[7],"COLOMBIA");
		 strcpy(palabras[8],"BRASIL");
		 strcpy(palabras[9],"PORTUGAL");
		 return 0;
	}


	 cargarcapitales()
		 {
		 strcpy(palabras[0],"MADRID");
		 strcpy(palabras[1],"BOGOTµ");
		 strcpy(palabras[2],"WASHINTON");
		 strcpy(palabras[3],"MEXICO");
		 strcpy(palabras[4],"CARCAS");
		 strcpy(palabras[5],"BUENOSAIRES");
		 strcpy(palabras[6],"BELICE");
		 strcpy(palabras[7],"SANTIAGO");
		 strcpy(palabras[8],"LAPAZ");
		 strcpy(palabras[9],"LAHABANA");
		 return 0;
		 }

		 cargarcarros()
	{
		 strcpy(palabras[0],"MAZDA");
		 strcpy(palabras[1],"SUZUKI");
		 strcpy(palabras[2],"RENAULT");
		 strcpy(palabras[3],"HONDA");
		 strcpy(palabras[4],"NISSAN");
		 strcpy(palabras[5],"FORD");
		 strcpy(palabras[6],"FERRARY");
		 strcpy(palabras[7],"DODGE");
		 strcpy(palabras[8],"CHEVROLET");
		 strcpy(palabras[9],"TOYOTA");
		 return 0;
	}
		cargarfrutas()
	{
		 strcpy(palabras[0],"MANSANA");
		 strcpy(palabras[1],"NARANJA");
		 strcpy(palabras[2],"PAPAYA");
		 strcpy(palabras[3],"GUANABANA");
		 strcpy(palabras[4],"GUAYABA");
		 strcpy(palabras[5],"MARACUYA");
		 strcpy(palabras[6],"AGUACATE");
		 strcpy(palabras[7],"PERA");
		 strcpy(palabras[8],"BANANO");
		 strcpy(palabras[9],"MANGO");
		 return 0;
		 }
		 cargarnombres()
		{
		 strcpy(palabras[0],"ANDRES");
		 strcpy(palabras[1],"FELIPE");
		 strcpy(palabras[2],"CARLOS");
		 strcpy(palabras[3],"JAIRO");
		 strcpy(palabras[4],"FERNANDO");
		 strcpy(palabras[5],"CLAUDIA");
		 strcpy(palabras[6],"TATIANA");
		 strcpy(palabras[7],"LICET");
		 strcpy(palabras[8],"ANGELA");
		 strcpy(palabras[9],"WILLIAM");
		 return 0;
		 }

		 cargarcantantes()
		{
		 strcpy(palabras[0],"AEROSMITH");
		 strcpy(palabras[1],"LIMPBIZKIT");
		 strcpy(palabras[2],"OFFSPRING");
		 strcpy(palabras[3],"METALLICA");
		 strcpy(palabras[4],"SHAKIRA");
		 strcpy(palabras[5],"DIOMEDES");
		 strcpy(palabras[6],"HECTORLAVOE");
		 strcpy(palabras[7],"GUAYACAN");
		 strcpy(palabras[8],"PAPAROACH");
		 strcpy(palabras[9],"MARYLIN");
		 return 0;
		 }
	categoria()          //FUNCION QUE PRESENTA LAS OPCIONES
	{
	textbackground(BLUE);
	textcolor(WHITE);
	clrscr();
	gotoxy(25,2);
	cprintf("CATEGORIAS");
	gotoxy(25,4);
	cprintf("1  ANIMALES");
	gotoxy(25,6);
	cprintf("2  DEPORTES");
	gotoxy(25,8);
	cprintf("3  PAISES");
	gotoxy(25,10);
	cprintf("4  CAPITALES");
	gotoxy(25,12);
	cprintf("5  CARROS");
	gotoxy(25,14);
	cprintf("6  FRUTAS");
	gotoxy(25,16);
	cprintf("7   NOMBRES");
	gotoxy(25,18);
	cprintf("8  CANTANTES");
	gotoxy(25,20);

	cprintf("ELIGE UNA OPCION");
	//REPITE EL PROCESO MIENTRAS DIGITE OTRO NUMERO DIFERENTE A LAS OPCIONES
	do{
		 categor=getch();
		 }while(categor!='1' && categor!='2' && categor!='3' && categor!='4' && categor!='5'&& categor!='6' && categor!='7' && categor!='8');

		 switch(categor)//DEPENDIENDO DE LA OPCION DEL USUARIO
		 //LLAMA CADA FUNCION EN LA QUE SE HAN CARGADO LAS PALABRAS
		 {
	case '1':cargaranimales();
	break;
	case '2':cargardeportes();
	break;
	case '3':cargarpaises();
	break;
	case '4':cargarcapitales();
	break;
	case '5':cargarcarros();
	break;
	case '6':cargarfrutas();
	break;
	case '7':cargarnombres();
	break;
	case '8':cargarcantantes();
	break;

		}

		 return 0;
		}
