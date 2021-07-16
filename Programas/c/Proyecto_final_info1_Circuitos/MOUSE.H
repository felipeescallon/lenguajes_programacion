#if !defined(__MOUSE_H)
#define __MOUSE_H
#if !defined(__DOS_H)
#include <dos.h>
#endif
/*
			struct WORDREGS {
			unsigned int    ax, bx, cx, dx, si, di, cflag, flags;
		};

		struct BYTEREGS {
			unsigned char   al, ah, bl, bh, cl, ch, dl, dh;
			};

		union   REGS    {
			struct  WORDREGS x;
			struct  BYTEREGS h;
		};

			 OPERACIONES CON EL MOUSE
 Para que los programas puedan utilizar el mouse es necesario instalar
 previamente un controlador, el cual puede tener la extensi¢n .SYS o .COM.

 - Si es .SYS debe instalarse desde CONFIG.SYS. En este caso se podr¡a
	instalar en la memoria reservada con la instrucci¢n:
			DEVICEHIGH = C:\DOS\MOUSE.SYS

 - Si es .COM se puede cargar desde el punto de petici¢n del sistema:
			C:\DOS>MOUSE
	en este caso se puede cargar en memoria reservada con LOADHIGH del DOS


		SERVICIOS DE LAS FUNCIONES DEL MOUSE DE LA Int 33H

		00H Instalar controlador del mouse e informar estado
				Si el controlador est  instalado esta funci¢n devuelve en el
				registro AX el valor -1 y en el registro BX el n£mero de botones
				Si no est  instalado devuelve 0 en el registro AX.
		01H Muestra cursor, por defecto en modo de texto
		02H Oculta el cursor
		03H Posici¢n X, Y y estado actuales del cursor
		04H Posici¢n del cursor
		05H Informaci¢n de cuando se puls¢ un bot¢n
		06H Informaci¢n de cuando se solt¢ un bot¢n
		07H Limitar movimiento horizontal
		08H Limitar movimiento vertical
		09H Fijar Cursor en modo gr fico
		0AH Fijar Cursor en modo de texto
		0BH Leer magnitud del movimiento desde la £ltima vez que se le llam¢
		0CH Fijar manipulador de sucesos del mouse
		0FH Fijar relaci¢n mickey/pixel (Escala)
		10H Condici¢n de cursor oculto
		13H Fijar umbral de doble velocidad
		14H Intercambiar manipuladores de sucesos
		15H Requisitos de almacenamiento del estado del controlador del mouse
		16H Grabar estado del controlador del mouse
		17H Recuperar estado del controlador del mouse
		18H Fijar manipulador de sucesos especiales del mouse
		19H Obtener manipulador de sucesos especiales del rat¢n
		1AH Fijar sensibilidad del rat¢n(Mickeys/pixel y umbral
		1BH Obtener sensibilidad del rat¢n
		1CH Fijar velocidad de interrupci¢n del mouse
		1DH Fijar la p gina de visualizaci¢n
		1EH Obtener la p gina de visualizaci¢n
		1FH Desactivar el controlador del mouse
		20H Activar el controlador del mouse
		21H Reinicializar el software del mouse
		22H Fijar el lenguaje del mensaje del mouse
		23H Obtener lenguaje de mensaje del mouse
		24H Obtener informaci¢n del controlador del mouse
*/

int  IniciaMouse ( int &);
void MuestraMouse ( void);
void EscondeCursorMouse ( void);
void LeerXYMouse ( int &, int &);
void Ir_a_XYMouse ( int, int);
int  LeerTeclaMouse ( void );
int  TiempoPulsadoBoton ( int, int &, int &, int &);
int  TiempoLiberadoBoton ( int, int &, int &, int &);
void MovimientoMouse ( int &, int &);
void AreasDesapareceMouse ( int, int, int, int);
void AreaMouse ( int, int, int, int);
void MickeysPorPixel ( int, int);
void UmbralVelocidadMouse ( int);
void MickeysPorPixelYUmbral ( int, int, int);
void LeerSensibilidadMouse ( int &, int &, int &);
void FijarPaginaMouse ( int);
int  LeerPaginaMouse ( void);
void DesactivarControladorMouse(void);
void ActivarControladorMouse(void);
void GraficaRaton(void);
int MouseQuieto(int,int);
void ApuntaMouse(void);
//****************************************************************************
int IniciaMouse( int &BotonMouse){
	union REGS entra,sale;
	entra.x.ax = 0x0000;
	int86( 0x33, &entra, &sale);
	BotonMouse = sale.x.bx;
	return ( sale.x.ax );
}
//**************************************************************************
void MuestraMouse( void ){
 union REGS entra, sale;
 entra.x.ax = 0x0001;
 int86 ( 0x33, &entra, &sale );
}
//**************************************************************************
void EscondeCursorMouse( void ){
 union REGS entra, sale;
 entra.x.ax = 0x0002;
 int86( 0x33, &entra, &sale);
}
//**************************************************************************
int LeerTeclaMouse( void ){
 union REGS entra, sale;
 entra.x.ax = 0x0003;
 int86( 0x33, &entra, &sale);
 return(sale.x.bx);
}
//**************************************************************************
void LeerXYMouse( int &x, int &y){
 union REGS entra,sale;
 entra.x.ax = 0x0003;
 int86( 0x33, &entra, &sale );
 x = sale.x.cx;
 y = sale.x.dx;
}
//**************************************************************************
int LeerTeclaXYMouse( int &x, int &y){
 union REGS entra,sale;
 entra.x.ax = 0x0003;
 int86( 0x33, &entra, &sale );
 x = sale.x.cx;
 y = sale.x.dx;
 return(sale.x.bx);
}
//**************************************************************************
void Ir_a_XYMouse( int x, int y ){
 union REGS entra, sale;
 entra.x.ax = 0x0004;
 entra.x.cx = x;
 entra.x.dx = y;
 int86( 0x33, &entra, &sale);
}
//**************************************************************************
int TiempoPulsadoBoton( int BotonMouse, int &x, int &y, int &Tiempo ){
 union REGS entra,sale;
 entra.x.ax = 0x0005;
 entra.x.bx = BotonMouse;
 int86( 0x33, &entra, &sale);
 x = sale.x.cx;
 y = sale.x.dx;
 Tiempo = sale.x.ax;
 return( sale.x.bx);
}
//**************************************************************************
int TiempoLiberadoBoton( int BotonMouse, int &x, int &y, int &Tiempo ){
  union REGS entra,sale;
  entra.x.ax = 0x0006;
	entra.x.bx = BotonMouse;
  int86( 0x33, &entra, &sale );
  x = sale.x.cx;
	y = sale.x.dx;
  Tiempo = sale.x.ax;
	return(sale.x.bx);
}
//**************************************************************************
void MovimientoMouse( int &x, int &y){
  union REGS entra,sale;
  entra.x.ax = 0x000b;
  int86( 0x33, &entra, &sale );
  x = sale.x.cx;
  y = sale.x.dx;
}
//**************************************************************************
void AreaMouse( int x1, int y1, int x2, int y2 ){
  union REGS entra,sale;
  entra.x.ax = 0x0007;
	entra.x.cx = x1;
  entra.x.dx = x2;
  int86( 0x33, &entra, &sale );
  entra.x.ax = 0x0008;
  entra.x.cx = y1;
	entra.x.dx = y2;
  int86( 0x33, &entra, &sale );
}
//**************************************************************************
void AreaDesapareceMouse( int x1, int y1, int x2, int y2 ){
  struct REGPACK regs;
  regs.r_ax = 0x0010;
  regs.r_cx = x1;
  regs.r_dx = y1;
  regs.r_si = x2;
  regs.r_di = y2;
  intr( 0x33, &regs );
}
//**************************************************************************
void MickeysPorPixel(  int x, int y ){
	union REGS entra,sale;
	entra.x.ax = 0x000f;
	entra.x.cx = x;
	entra.x.dx = y;
	int86( 0x33, &entra, &sale );
}
//**************************************************************************
void UmbralVelocidadMouse( int limite ){
	union REGS entra,sale;
	entra.x.ax = 0x0013;
	entra.x.dx = limite;
	int86( 0x33, &entra, &sale );
}
//**************************************************************************
void MickeysPorPixelYUmbral( int x, int y, int UmbralDobleVelocidad ){
	union REGS entra,sale;
	entra.x.ax = 0x001a;
	entra.x.bx = x;
	entra.x.cx = y;
	entra.x.dx = UmbralDobleVelocidad;
	int86( 0x33, &entra, &sale );
}
//**************************************************************************
void LeerSensibilidadMouse( int &x, int &y, int &limit ){
	union REGS entra,sale;
	entra.x.ax = 0x001b;
	int86( 0x33, &entra, &sale );
	x = sale.x.bx;
	y = sale.x.cx;
	limit = sale.x.dx;
}
//**************************************************************************
int LeerPaginaMouse( void ){
	union REGS entra,sale;
	entra.x.ax = 0x001e;
	int86( 0x33, &entra, &sale );
	return( sale.x.bx );
}
//**************************************************************************
void FijarPaginaMouse( int Pagina ){
	union REGS entra,sale;
	entra.x.ax = 0x001d;
	entra.x.bx = Pagina;
	int86( 0x33, &entra, &sale );
}
//**************************************************************************
void DesactivarControladorMouse(void){
	union REGS entra,sale;
	entra.x.ax = 0x001f;
	int86( 0x33, &entra, &sale );
}
//**************************************************************************
void ActivarControladorMouse(void){
	union REGS entra,sale;
	entra.x.ax = 0x0020;
	int86( 0x33, &entra, &sale );
}
//**************************************************************************
void GraficaRaton(void){
	union REGS entra, sale;
	entra.h.ah = 0;
	entra.h.al = 6;
	int86 (0x10, &entra, &sale);
	entra.x.ax = 1;
	int86 (0x33, &entra, &sale);
}
//**************************************************************************
void ApuntaMouse(void){
	union REGS entra, sale;
	struct SREGS segmento;
	unsigned CUAOICO[] = {
	0x1C58,0x1B98,0x1BD8,0x1BD8,
	0x1BD8,0x1C58,0x1FF8,0x1FF8,
	0x1C38,0x1BD8,0x1BD8,0x1BD8,
	0x1BD8,0x1C38,0x8001,0xC3C3,
	0xE3A7,0xE467,0xE427,0xE427,
	0xE467,0xE3A7,0xE007,0xE007,
	0xE3C7,0xE427,0xE427,0xE427,
	0xE427,0xE3C7,0x700E,0x3C3C };
	entra.x.ax = 9;
	entra.x.bx = 0;
	entra.x.cx = 0;
	entra.x.dx = (unsigned) CUAOICO;
	segread (&segmento);
	int86 (0x33, &entra, &sale);
}
//**************************************************************************
void RatonMouse(void){
	union REGS entra, sale;
	struct SREGS segmento;

	unsigned raton[] = {
	1,1,1,1,1,1,1,1,
	1,1,1,1,1,1,1,1,
	0x0000,  //0000 0000 0000 0000 //M scara del cursor
	0x0000,  //0000 0000 0000 0000
	0x0000,  //0000 0000 0000 0000
	0x0000,  //0000 0000 0000 0000
	0x1000,  //0001 0000 0000 0000
	0x13C0,  //0001 0011 1100 0000
	0x3FF8,  //0011 1111 1111 1000
	0x7FF8,  //0111 1111 1111 1000
	0xFFF8,  //1111 1111 1111 1000
	0x0824,  //0000 1000 0010 0100
	0x0822,  //0000 1000 0010 0010
	0x1CE2,  //0001 1100 1110 0010
	0x0000,  //0000 0000 0000 0000
	0x0000,  //0000 0000 0000 0000
	0x0000,  //0000 0000 0000 0000
	0x0000}; //0000 0000 0000 0000

	entra.x.ax = 9;
	entra.x.bx = 0;
	entra.x.cx = 8;
	entra.x.dx = (unsigned) raton;
	segread (&segmento);
	int86 (0x33, &entra, &sale);
}
//**************************************************************************
void RestaurarMouse(int &x,int &y){
 while(LeerTeclaXYMouse(x,y));
 MouseQuieto(x+1,y+1);
}

int MouseQuieto(int x,int y){
 static int X=0,Y=0;
 int sw=x!=X||y!=Y;
 X=x;
 Y=y;
 return sw;
}
#endif