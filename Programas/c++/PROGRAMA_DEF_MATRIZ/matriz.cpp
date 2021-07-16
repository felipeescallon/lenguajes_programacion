#include  <iostream.h>
#include <string>
#include <string.h>
#include <ctype.h>

void main () 
{

	//leer matriz
char m[5][5]={{'e','m','e','b','a',},{'s','o','m','o','a'},{'t','r','e','l','l'},{'e','q','r','a','m'},{'a','u','o','c','a'}};


//muestra la matriz original:
cout<<m[0][0];
cout<<'\t';
cout<<m[0][1];
cout<<'\t';
cout<<m[0][2];
cout<<'\t';
cout<<m[0][3];
cout<<'\t';
cout<<m[0][4];
cout<<'\n';

cout<<m[1][0];
cout<<'\t';
cout<<m[1][1];
cout<<'\t';
cout<<m[1][2];
cout<<'\t';
cout<<m[1][3];
cout<<'\t';
cout<<m[1][4];
cout<<'\n';

cout<<m[2][0];
cout<<'\t';
cout<<m[2][1];
cout<<'\t';
cout<<m[2][2];
cout<<'\t';
cout<<m[2][3];
cout<<'\t';
cout<<m[2][4];
cout<<'\n';


cout<<m[3][0];
cout<<'\t';
cout<<m[3][1];
cout<<'\t';
cout<<m[3][2];
cout<<'\t';
cout<<m[3][3];
cout<<'\t';
cout<<m[3][4];
cout<<'\n';

cout<<m[4][0];
cout<<'\t';
cout<<m[4][1];
cout<<'\t';
cout<<m[4][2];
cout<<'\t';
cout<<m[4][3];
cout<<'\t';
cout<<m[4][4];
cout<<'\n';

 
//PRIMERA ITERACION: SE MOVIO TODA LA MATRIZ UNA POSICION(A)
 
 char Atmp4_0='a';
 char Atmp4_1='u';
 char Atmp4_2='o';
 char Atmp4_3='c';
 char Atmp4_4='a';

 

	//columna 0
		m[4][0]=m[3][0];
		m[3][0]=m[2][0];
		m[2][0]=m[1][0];
		m[1][0]=m[0][0];
		m[0][0]=m[4][4];
	//columna 1 
		m[4][1]=m[3][1];
		m[3][1]=m[2][1];
		m[2][1]=m[1][1];
		m[1][1]=m[0][1];
		m[0][1]=Atmp4_0;
	//columna 2	 
		m[4][2]=m[3][2];
		m[3][2]=m[2][2];
		m[2][2]=m[1][2];
		m[1][2]=m[0][2];
		m[0][2]=Atmp4_1;
	//columna 3
		m[4][3]=m[3][3];
		m[3][3]=m[2][3];
		m[2][3]=m[1][3];
		m[1][3]=m[0][3];
		m[0][3]=Atmp4_2;
	//columna 4
		m[4][4]=m[3][4];
		m[3][4]=m[2][4];
		m[2][4]=m[1][4];
		m[1][4]=m[0][4];
		m[0][4]=Atmp4_3;


//SEGUNDA ITERACION: SE MOVIO TODA LA MATRIZ UNA POSICION(B)
 
 char Btmp4_0='e';
 char Btmp4_1='q';
 char Btmp4_2='r';
 char Btmp4_3='a';
 char Btmp4_4='m';

 

	//columna 0
		m[4][0]=m[3][0];
		m[3][0]=m[2][0];
		m[2][0]=m[1][0];
		m[1][0]=m[0][0];
		m[0][0]=m[4][4];
	//columna 1 
		m[4][1]=m[3][1];
		m[3][1]=m[2][1];
		m[2][1]=m[1][1];
		m[1][1]=m[0][1];
		m[0][1]=Btmp4_0;
	//columna 2	 
		m[4][2]=m[3][2];
		m[3][2]=m[2][2];
		m[2][2]=m[1][2];
		m[1][2]=m[0][2];
		m[0][2]=Btmp4_1;
	//columna 3
		m[4][3]=m[3][3];
		m[3][3]=m[2][3];
		m[2][3]=m[1][3];
		m[1][3]=m[0][3];
		m[0][3]=Btmp4_2;
	//columna 4
		m[4][4]=m[3][4];
		m[3][4]=m[2][4];
		m[2][4]=m[1][4];
		m[1][4]=m[0][4];
		m[0][4]=Btmp4_3;


//TERCERA ITERACION:SE MOVIO TODA LA MATRIZ UNA POSICION(C)
 char Ctmp4_0='t';
 char Ctmp4_1='r';
 char Ctmp4_2='e';
 char Ctmp4_3='l';
 char Ctmp4_4='l';

 

	//columna 0
		m[4][0]=m[3][0];
		m[3][0]=m[2][0];
		m[2][0]=m[1][0];
		m[1][0]=m[0][0];
		m[0][0]=m[4][4];
	//columna 1 
		m[4][1]=m[3][1];
		m[3][1]=m[2][1];
		m[2][1]=m[1][1];
		m[1][1]=m[0][1];
		m[0][1]=Ctmp4_0;
	//columna 2	 
		m[4][2]=m[3][2];
		m[3][2]=m[2][2];
		m[2][2]=m[1][2];
		m[1][2]=m[0][2];
		m[0][2]=Ctmp4_1;
	//columna 3
		m[4][3]=m[3][3];
		m[3][3]=m[2][3];
		m[2][3]=m[1][3];
		m[1][3]=m[0][3];
		m[0][3]=Ctmp4_2;
	//columna 4
		m[4][4]=m[3][4];
		m[3][4]=m[2][4];
		m[2][4]=m[1][4];
		m[1][4]=m[0][4];
		m[0][4]=Ctmp4_3;



//muestra la matriz resultante:
cout<<'\n';
cout<<'\n';
cout<<m[0][0];
cout<<'\t';
cout<<m[0][1];
cout<<'\t';
cout<<m[0][2];
cout<<'\t';
cout<<m[0][3];
cout<<'\t';
cout<<m[0][4];
cout<<'\n';

cout<<m[1][0];
cout<<'\t';
cout<<m[1][1];
cout<<'\t';
cout<<m[1][2];
cout<<'\t';
cout<<m[1][3];
cout<<'\t';
cout<<m[1][4];
cout<<'\n';

cout<<m[2][0];
cout<<'\t';
cout<<m[2][1];
cout<<'\t';
cout<<m[2][2];
cout<<'\t';
cout<<m[2][3];
cout<<'\t';
cout<<m[2][4];
cout<<'\n';


cout<<m[3][0];
cout<<'\t';
cout<<m[3][1];
cout<<'\t';
cout<<m[3][2];
cout<<'\t';
cout<<m[3][3];
cout<<'\t';
cout<<m[3][4];
cout<<'\n';

cout<<m[4][0];
cout<<'\t';
cout<<m[4][1];
cout<<'\t';
cout<<m[4][2];
cout<<'\t';
cout<<m[4][3];
cout<<'\t';
cout<<m[4][4];
cout<<'\n';

}

