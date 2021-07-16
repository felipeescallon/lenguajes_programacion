/*****************************************************************************

  VGALIB.H
****************************************************************************/

#if defined(__COMPACT__) || defined(__LARGE__) || defined(__HUGE__)

#ifndef __VGALIB_H
#define __VGALIB_H

#include <stdlib.h>
#include <string.h>
#include <stdio.h>	// FILE, fopen, fclose, fgetc, ftell, fseek
#include <dos.h>	  // int86, MK_FP
#include <mem.h>	  // memset, memmove, memcpy
#include <math.h>   // sin, cos, abs
#include <alloc.h>  // malloc, free

#define TEXTO 0x03
#define GRAFICO 0x13
#define BYTE unsigned char
#define WORD unsigned int
#define MAX_FRAMES 15

// Puntero a la memoria de video
BYTE *DIR_VGA=(BYTE *) MK_FP(0xA000, 0);

// Tipo de dato para guardar los datos de la paleta
typedef char t_paleta[768];

// Tipo pantalla virtual
typedef BYTE *t_pvirtual;

// Tipo de dato para guardar los datos de un sprite
typedef struct
{
	int x, y; 		     				// Posicion del sprite en la pantalla
	BYTE ancho, alto;  				// Ancho y alto del sprite
	BYTE num_frames;	 				// Numero de frames que tiene el sprite
	BYTE *frame[MAX_FRAMES]; 	// Cada uno de los frames del sprite
} t_sprite;

// Tipo de dato para guardar los datos del cabecera de un archivo PCX
typedef struct
{
		char Fabricante;				// Fabricante (0ah = ZSoft)
		char Version;						// Numero de version del pcx
		char Compresion;				// Hay compresion? 1=Si RLE
		char BPP;								// Bits por pixel
		int  xmin;							// Dimensiones del pcx
		int  ymin;
		int  xmax;
		int  ymax;
		int  Hres;							// Resoluciones de la imagen (DPI)
		int  Vres;
		char Palette16[48];			// Paleta de color (no la usamos)
		char Reservado;					// No se utiliza, tiene que valer 0
		char Numero_planos;			// Numero de planos de color en la imagen
		int  Bytes_por_linea;		// Numero de bytes por linea de la imagen
		int  Palette_type;			// Como interpretamos la paleta
		char Reservados[58];		// Reservados para futuras ampliaciones
} PcxHead;


// Prototipos de funciones
void SetMode(char mode);
void PutPixel(int x, int y, BYTE color, BYTE *where);
BYTE GetPixel(int x, int y, BYTE *where);
void Cls(BYTE color, BYTE *where);
void SetColor(BYTE color, char r, char g, char b);
void GetColor(BYTE color, char *r, char *g, char *b);
void SetPal(t_paleta pal);
void GetPal(t_paleta pal);
void DelPal(BYTE color);
void FadeOut(void);
void FadeIn(t_paleta paleta);
void FadeWhite(void);
void RotatePal(t_paleta pal, int first, int last);
BYTE *SetPVirtual(BYTE **pv);
void FreePVirtual(BYTE **pv);
void Flip(BYTE *source);
void FlipTo(BYTE *source, BYTE *where);
void Circle(int cx, int cy, int radio, BYTE color, BYTE *where);
void LineH(int x, int y, int ancho, BYTE color, BYTE *where);
void LineV(int x, int y, int alto, BYTE color, BYTE *where);
void Line(int x1, int y1, int x2, int y2, BYTE color, BYTE *where);
void Box(int x1, int y1, int x2, int y2, BYTE color, BYTE *where);
void FillBox(int x, int y, int ancho, int alto, BYTE color, BYTE *where);
int LoadSpr(char *filename, BYTE **frame);
int LoadSprite(char *filename, t_sprite *sprite, BYTE num_frames);
void FreeSprite(t_sprite *sprite, BYTE num_frames);
void PutSprite(int x, int y, BYTE *frame, BYTE *where);
void PutImage(int x, int y, BYTE *frame, BYTE *where);
void PutImageCenter(BYTE *frame, BYTE *where);
int LoadPal(char *filename, t_paleta pal);
int LoadRaw(char *filename, BYTE *where);
int LoadPicHead(char *filename, t_paleta paleta, BYTE *where);
int LoadPic(char *filename, t_paleta paleta, BYTE *where);
int LoadCel(char *filename, t_paleta paleta, BYTE **where);
int LoadPcx(char *filename, int off_x, int off_y, t_paleta paleta,
						BYTE *where);
int CargaPcx(char *filename, t_paleta paleta, BYTE **where);
void WaitRetrace(void);
void WaitRetraceN(int n);
int sgn(int num);
long filesize(FILE *f);

/****************************************************************************/
/* 												IMPLEMENTACION DE FUNCIONES       								*/
/****************************************************************************/

// Setea un modo de video especifico
void SetMode(char mode)
{
	union REGS r;

	r.h.ah=0;
	r.h.al=mode;
	int86(0x10, &r, &r);
}

// Coloca un pixel en la pantalla en una posicion determinada
void PutPixel(int x, int y, BYTE color, BYTE *where)
{
	where[(y<<8)+(y<<6)+x]=color;
}

// Obtiene el color de un pixel de una posicion determinada de la pantalla
BYTE GetPixel(int x, int y, BYTE *where)
{
	return (where[(y<<8)+(y<<6)+x]);
}

// Borra la pantalla con un color determinado
void Cls(BYTE color, BYTE *where)
{
	memset(where, color, 64000);
}

// Setea un color
void SetColor(BYTE color, char r, char g, char b)
{
  outportb(0x3C8, color);
  outportb(0x3C9, r);
  outportb(0x3C9, g);
  outportb(0x3C9, b);
}

// Obtiene las intensidades RGB de un color
void GetColor(BYTE color, char *r, char *g, char *b)
{
  outportb(0x3C7, color);
  *r=inportb(0x3C9);
  *g=inportb(0x3C9);
	*b=inportb(0x3C9);
}

// Setea una paleta
void SetPal(t_paleta pal)
{
  int i;

  for(i=0; i<256; i++)
    SetColor(i, pal[i*3], pal[(i*3)+1], pal[(i*3)+2]);
}

// Obtiene la paleta actual
void GetPal(t_paleta pal)
{
	int i;

	for(i=0; i<256; i++)
		GetColor(i, &pal[i*3], &pal[(i*3)+1], &pal[(i*3)+2]);
}

// Coloca el DAC a un valor determinado
void DelPal(BYTE color)
{
	int i;

	for(i=0; i<256; i++)
		SetColor(i, color, color, color);
}

// Realiza un fundido desde la paleta actual hasta llegar al negro
void FadeOut(void)
{
	t_paleta pal;
	int x, y;

	GetPal(pal);

	for(y=0; y<64; y++)
	{
		for(x=0; x<768; x++)
			if(pal[x]>0) pal[x]--;
		WaitRetrace();
		SetPal(pal);
	}
}

// Hace un fundido desce cero (negro) hasta llegar a la paleta pasada
// como parametro
void FadeIn(t_paleta paleta)
{
	t_paleta pal;
	int x, y;

	for(y=0; y<768; y++)  pal[y]=0;

	for(y=0; y<64; y++)
	{
		for(x=0; x<768; x++)
			if(pal[x]<paleta[x])  pal[x]++;
		WaitRetrace();
		SetPal(pal);
	}
}

// Hace un fundido hasta dejar la pantalla completamente iluminada (blanca)
void FadeWhite(void)
{
	t_paleta pal;
	int x,y;

	GetPal(pal);

	for(y=0; y<64; y++)
	{
		for(x=0; x<768; x++)
			if(pal[x]<63) pal[x]++;
		WaitRetrace();
		SetPal(pal);
	}
}

// Rota una paleta hacia la derecha o izquierda, entre un rango de colores
void RotatePal(t_paleta pal, int first, int last)
{
	int i;
	char r, g, b;

	if (first < last)	// Rota la paleta una posicion a la derecha
	{
		first=first*3;
		last=(last*3)+2;

		r=pal[last-2];
		g=pal[last-1];
		b=pal[last];
		for (i=last; i>first; i-=3)
		{
			pal[i]=pal[i-3];
			pal[i-1]=pal[i-4];
			pal[i-2]=pal[i-5];
		}
		pal[first]=r;
		pal[first+1]=g;
		pal[first+2]=b;
	}
	else	// Rota la paleta una posicion a la izquierda
	{
		first=(first*3)+2;
		last=last*3;

		r=pal[last];
		g=pal[last+1];
		b=pal[last+2];
		for (i=last; i<first; i+=3)
		{
			pal[i]=pal[i+3];
			pal[i+1]=pal[i+4];
			pal[i+2]=pal[i+5];
		}
		pal[first-2]=r;
		pal[first-1]=g;
		pal[first]=b;
	}
}

// Reserva memoria para una pantalla virtual
BYTE *SetPVirtual(BYTE **pv)
{
	*pv=(BYTE *)calloc(256000, sizeof(BYTE));
  return (*pv);
}

// Libera la memoria de una pantalla virtual
void FreePVirtual(BYTE **pv)
{
  free(*pv);
  *pv=NULL;
}

// Mueve un bloque de 64.000 bytes a la memoria de la VGA
void Flip(BYTE *source)
{
  memmove(DIR_VGA, source, 256000);
}

// Mueve el contenido de una pantalla virtual a otra pantalla
void FlipTo(BYTE *source, BYTE *where)
{
  memmove(where, source, 64000);
}

// Dibuja un circulo en la pantalla
void Circle(int cx, int cy, int radio, BYTE color, BYTE *where)
{
  float angulo=0;
  int x, y;

  do
  {
    x = cx + radio * cos(angulo);
    y = cy + radio * sin(angulo) / 1.2;

    if((x>=0) && (y>=0) && (x<320) && (y<200))
      PutPixel(x, y, color, where);

    angulo+=0.005;
	} while(angulo<6.28);
}

// Dibuja una linea horizontal
void LineH(int x, int y, int ancho, BYTE color, BYTE *where)
{
  int i;

  for(i=x; i<=x+ancho; i++)
    PutPixel(i, y, color, where);
}

// Dibuja una linea vertical
void LineV(int x, int y, int alto, BYTE color, BYTE *where)
{
  int i;

  for(i=y; i<=y+alto; i++)
    PutPixel(x, i, color, where);
}

// Dibuja una linea con cualquier pendiente
void Line(int x1, int y1, int x2, int y2, BYTE color, BYTE *where)
{
  int i, s, u, v, dx1, dx2, dy1, dy2, m, n;

  u=x2-x1;
  v=y2-y1;
  dx1=sgn(u);
  dy1=sgn(v);
  dx2=sgn(u);
  dy2=0;
  m=abs(u);
  n=abs(v);
  if(!(m>n))
  {
    dx2=0;
    dy2=sgn(v);
    m=abs(v);
    n=abs(u);
	}
  s=m>>2; // s=m/2;
  for(i=0; i<=m; i++)
  {
    PutPixel(x1, y1, color, where);
    s=s+n;
    if (!(s<m))
    {
      s=s-m;
      x1=x1+dx1;
      y1=y1+dy1;
    }
    else
    {
      x1=x1+dx2;
      y1=y1+dy2;
    }
  }
}

// Dibuja un rectangulo
void Box(int x1, int y1, int x2, int y2, BYTE color, BYTE *where)
{
  LineV(x2, y1, y2-y1, color, where);
  LineV(x1, y1, y2-y1, color, where);
  LineH(x1, y1, x2-x1, color, where);
  LineH(x1, y2, x2-x1, color, where);
}

// Dibuja un rectangulo relleno
void FillBox(int x, int y, int ancho, int alto, BYTE color, BYTE *where)
{
  int px, py;

  for(px=x; px<=x+ancho; px++)
    for(py=y; py<=y+alto; py++)
      PutPixel(px, py, color, where);
}

// Carga en memoria un sprite (solo un frame)
int LoadSpr(char *filename, BYTE **frame)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, NO_HAY_MEMORIA=-1, MUY_GRANDE=-2, OK=1};
	int ancho, alto;
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	// Leemos el ancho y el alto del frame
	ancho=fgetc(f);
	alto=fgetc(f);

	if((ancho>255) || (alto>255))
	{
		fclose(f);
		return MUY_GRANDE;
	}

	// Reservamos memoria para la imagen, el ancho y el alto
	if((*frame=(BYTE *)malloc((ancho*alto)+2))==NULL)
	{
		fclose(f);
		return NO_HAY_MEMORIA;
	}

	// Copiamos el ancho y el alto
	memcpy(*frame, &ancho, 1);
	memcpy(*frame+1, &alto, 1);

	// Leemos la imagen
	fread(*frame+2, sizeof(char), (ancho*alto), f);

	fclose(f);

	return OK;
}

// Carga un sprite en memoria (una secuencia de uno o mas frames)
int LoadSprite(char *filename, t_sprite *sprite, BYTE num_frames)
{
	BYTE i, num;
	int r;
	char *nombre, *numero;

	sprite->num_frames=num_frames;	// Guardamos total de frames
	// Inicializamos a cero las coordenadas del sprite
	sprite->x=sprite->y=0;

	// Cargaremos cada uno de los frames que forma el sprite
	for(i=0, num=1; i<num_frames; i++, num++)
	{
		// Creamos el nombre del archivo
		strcpy(nombre, filename);
		itoa(num, numero, 10);
		strcat(nombre, numero);
		strcat(nombre, ".spr");
		// Lo cargamos
		r=LoadSpr(nombre, &(sprite->frame[i]));
		// Si hubo algun error salimos del for
		if(r!=1) break;
	}

	// Si hubo algun error al cargar algun un frame, liberamos la memoria
	// reservada hasta ese momento
	if(r!=1)
	{
		if (i!=0) // Si se cargo en memoria al menos un frame...
		{
			for(num=0; num<i; num++)
				free(sprite->frame[num]);
		}
		return r;	// Retornamos el error producido
	}

	// Guardamos el ancho y el alto del sprite
	memcpy(&(sprite->ancho), &(sprite->frame[0][0]), 1);
	memcpy(&(sprite->alto), &(sprite->frame[0][1]), 1);

	return 1;	// Todo OK!
}

// Libera la memoria ocupada por un sprite
void FreeSprite(t_sprite *sprite, BYTE num_frames)
{
	BYTE i;

	sprite->x=sprite->y=0;
	sprite->num_frames=0;
	sprite->ancho=sprite->alto=0;

	for(i=0; i<num_frames; i++)
		free(sprite->frame[i]);
}

// Coloca un sprite en una pantalla  (tama¤o maximo 256x256)
void PutSprite(int x, int y, BYTE *frame, BYTE *where)
{
	BYTE ancho=frame[0], alto=frame[1], color;
	int col, fil, inc=2;

	for(fil=0; fil<alto; fil++)
		for(col=0; col<ancho; col++)
		{
			color=frame[inc++];
			if(color!=0)
				where[((fil+y)<<8)+ ((fil+y)<<6)+col+x]=color;
		}
}

// Coloca un sprite en una pantalla (tama¤o maximo 320x200)
void PutImage(int x, int y, BYTE *frame, BYTE *where)
{
	BYTE color;
	int ancho, alto, col, fil, inc=4;

	memcpy(&ancho, &frame[0], 2);
	memcpy(&alto, &frame[2], 2);

	for(fil=0; fil<alto; fil++)
		for(col=0; col<ancho; col++)
		{
			color=frame[inc++];
			if(color!=0)
				where[((fil+y)<<8)+ ((fil+y)<<6)+col+x]=color;
		}
}

// Coloca un sprite centrado en una pantalla (tama¤o maximo 320x200)
void PutImageCenter(BYTE *frame, BYTE *where)
{
	BYTE color;
	int x, y, ancho, alto, col, fil, inc=4;

	memcpy(&ancho, &frame[0], 2);
	memcpy(&alto, &frame[2], 2);

	// Centramos la imagen
	x=(320-ancho)/2;
	y=(200-alto)/2;

	for(fil=0; fil<alto; fil++)
		for(col=0; col<ancho; col++)
		{
			color=frame[inc++];
			if(color!=0)
				where[((fil+y)<<8)+ ((fil+y)<<6)+col+x]=color;
		}
}


// Carga desde un archivo una paleta de colores
int LoadPal(char *filename, t_paleta pal)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, ARCHIVO_NO_VALIDO=-1, OK=1};
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	if(filesize(f)!=768)
	{
		fclose(f);
		return ARCHIVO_NO_VALIDO;
	}

	fread(pal, sizeof(char), 768, f);
	fclose(f);

	return OK;
}

// Carga desde un archivo una imagen en formato crudo
int LoadRaw(char *filename, BYTE *where)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, ARCHIVO_NO_VALIDO=-1, OK=1};
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	if(filesize(f)>200000)
	{
		fclose(f);
		return ARCHIVO_NO_VALIDO;
	}

	fread(where, sizeof(char), 200000, f);
	fclose(f);

	return OK;
}

// Carga una imagen en formato PIC de Animator Studio
int LoadPicHead(char *filename, t_paleta paleta, BYTE *where)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, ARCHIVO_NO_VALIDO=-1, OK=1};
	char head[32];
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	if(filesize(f)!=128000)
	{
		fclose(f);
		return ARCHIVO_NO_VALIDO;
	}

	fread(head, sizeof(char), 32, f);
	fread(paleta, sizeof(char), 768, f);
	fread(where, sizeof(char), 128000, f);
	fclose(f);

	return OK;
}

// Carga una imagen en formato PIC (Paleta+Imagen)
int LoadPic(char *filename, t_paleta paleta, BYTE *where)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, ARCHIVO_NO_VALIDO=-1, OK=1};
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	if(filesize(f)!=128768)
	{
		fclose(f);
		return ARCHIVO_NO_VALIDO;
	}

	fread(paleta, sizeof(char), 768, f);
	fread(where, sizeof(char), 128000, f);
	fclose(f);

	return OK;
}

// Carga una imagen en formato CEL de Animator Studio
int LoadCel(char *filename, t_paleta paleta, BYTE **where)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, NO_HAY_MEMORIA=-1, NO_ES_CEL=-2, OK=1};

	struct
	{
		WORD Magic_Number;// Numero magico debe ser igual 0x9119
		WORD Width;	   // Ancho de la imagen
		WORD Height;	   // Alto de la imagen
		WORD Horiz;		// Posicion horizontal
		WORD Vert;	   // Posicion vertical
		BYTE BPP;	   // Bits Por Pixel (8)
		BYTE Compresion;   // Tipo de compresion (0=no comprimido)
		long Size;	   // Tama¤o en bytes de la imagen
		BYTE Reservados[16];// Reservados para futuras ampliaciones
	} CelHead;

	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	// Leemos la cabecera
	fread(&CelHead, sizeof(char), 32, f);

	if(CelHead.Magic_Number!=0x9119)
	{
		fclose(f);
		return NO_ES_CEL;
	}

	// Se reserva memoria para guardar la imagen y 4 bytes mas para guardar
	// el ancho y el alto (2 bytes cada uno)
	if((*where=(BYTE*)malloc((CelHead.Width*CelHead.Height)+4))==NULL)
	{
		fclose(f);
		return NO_HAY_MEMORIA;
	}

	// Guardamos los valores del ancho y el alto
	memcpy(*where, &CelHead.Width, 2);
	memcpy(*where+2, &CelHead.Height, 2);

	// Leemos la paleta
	fread(paleta, sizeof(char), 768, f);

	// Leemos la imagen y la copiamos a *where+4 despues del ancho y alto
	fread(*where+4, sizeof(char), (CelHead.Width*CelHead.Height), f);

	fclose(f);

	return OK;
}

// Carga un archivo en formato PCX en una pantalla virtual
int LoadPcx(char *filename, int off_x, int off_y, t_paleta paleta,
						BYTE *where)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, NO_ES_ZSOFT=-1, MUY_GRANDE=-2, OK=1};

	WORD cont, ancho, alto, x=0, y=0;
	BYTE byte, listo=0;
	PcxHead head;
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	fread(&head, sizeof(char), 128, f);

	if(head.Fabricante!=0x0A)
	{
		fclose(f);
		return NO_ES_ZSOFT;
	}

	ancho=(head.xmax-head.xmin)+1;
	alto=(head.ymax-head.ymin)+1;

	if((ancho>320) || (alto>200))
	{
		fclose(f);
		return MUY_GRANDE;
	}

	while(!listo)
	{
		byte=fgetc(f);
		// Si los 2 bits mas significativos estan activados, entonces el byte
		//   entonces el byte es un contador
		if((byte&192)==192) // 192=11000000b
		{
			cont=byte & 63;		// 63=00111111b
			byte=fgetc(f);
			for(; cont>0; cont--)
			{
			    where[((off_y+y)<<8)+((off_y+y)<<6)+(off_x+x)]=byte;
			    x++;
			    if(x==ancho)   // Si ya terminamos la linea
			    {
				x=0;   // Volvemos a empezar en la linea siguiente
				y++;
			      }
				// Si ya terminamos todas las lineas de la imagen
			     if(y==alto) listo=1;
			}
		}
		// Si los 2 bits mas significativos no estan activados, entonces es
		// un byte de datos
		else
		{
			where[((off_y+y)<<8)+((off_y+y)<<6)+(off_x+x)]=byte;
			x++;
			if(x==ancho)      // Si ya terminamos la linea
			{
				x=0;               // Volvemos a empezar en la linea siguiente
				y++;
			}
			// Si ya terminamos todas las lineas de la imagen
			if(y==alto) listo=1;
		}
	}

	fgetc(f);	// Se lee el byte de separacion de valor 12d

	for(cont=0; cont<768; cont++)
		paleta[cont]=fgetc(f)>>2;	// dividimos por 4

	fclose(f);

	return OK;
}

// Carga un archivo PCX de cualquier tama¤o (maximo 320x200)
// Almacena tambien los datos del ancho y alto
int CargaPcx(char *filename, t_paleta paleta, BYTE **where)
{
	enum{ARCHIVO_NO_ENCONTRADO=0, NO_ES_ZSOFT=-1, MUY_GRANDE=-2, NO_MEMORIA=-3,
			 OK=1};

	WORD i, cont, total, ancho, alto;
	BYTE byte;
	PcxHead head;
	FILE *f;

	if((f=fopen(filename, "rb"))==NULL)  return ARCHIVO_NO_ENCONTRADO;

	fread(&head, sizeof(char), 128, f);

	if(head.Fabricante!=0x0A)
	{
		fclose(f);
		return NO_ES_ZSOFT;
	}

	ancho=(head.xmax-head.xmin)+1;
	alto=(head.ymax-head.ymin)+1;

	if((ancho>320) || (alto>200))
	{
		fclose(f);
		return MUY_GRANDE;
	}

	if((*where=(BYTE*)(malloc((ancho*alto)+4)))==NULL) return NO_MEMORIA;

	memcpy(*where, &ancho, 2);
	memcpy(*where+2, &alto, 2);

	total=ancho*alto;

	for(i=0; i<total;)
	{
		byte=fgetc(f);
		if((byte&192)==192)
		{
			cont=byte & 63;
			byte=fgetc(f);
			for(; cont>0; cont--)
			{
				*(*where+i+4)=byte;
				i++;
			}
		}
		else
		{
			*(*where+i+4)=byte;
			i++;
		}
	}

	fgetc(f);

	for(cont=0; cont<768; cont++)  paleta[cont]=fgetc(f)>>2;

	fclose(f);

	return OK;
}

// Espera hasta que no haya retrazado vertical
void WaitRetrace(void)
{
	while(!((inportb(0x3DA) & 8)==8));  // Retrazado activo
  while(!((inportb(0x3DA) & 8)==0));  // Retrazado no activo
}

// Espera n retrazos verticales
void WaitRetraceN(int n)
{
	int i;

	for(i=0; i<n; i++)
		WaitRetrace();
}

// Devuelve el signo de un numero
int sgn(int num)
{
	if(num>0) return(1);
	if(num<0) return(-1);
	return(0);
}

// Devuelve el tama¤o en bytes de un archivo
long filesize(FILE *f)
{
	long pos_actual, longitud;

	pos_actual=ftell(f);
	fseek(f, 0L, SEEK_END);
	longitud=ftell(f);
	fseek(f, pos_actual, SEEK_SET);

	return longitud;
}

#endif

#else
#error COMPILAR CON MODELO COMPACT, LARGE O HUGE
#endif




