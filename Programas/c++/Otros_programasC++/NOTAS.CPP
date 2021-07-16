/*Creado por: Andres F Escallon P y Jorge Moncayo___jul/2002*/

#include<iostream.h>//para trabajo orientado a objetos
#define TAM 3//se define un tamaño global
#include<conio.h>
//definicion de la clase
class Nota{
 //declaracion variables ( por defecto son privadas )
float por1,por2;
float notas[TAM];
char materia[20];

public://es como decir a nivel global, se conoce en todo el programa
Nota();//funcion creadora
~Nota();//funcion destructora

// encabezado funciones
void leer_materia();
void recibir();
float computar();
void imprimir(float califica);
void estado_actual();
};

//especificacion para la clase de la implementacion de la funcion
Nota::Nota()
{
 //asignaciones
por1=0.35;
por2=0.30;
 //inicializacion
for(int i=0;i>=TAM;i++){
notas[i]=0.0;
}
}
 //lo mismo que en a anterior
Nota::~Nota()
{
cout<<endl<<"objeto destruido totalmente"<<endl;
}

 //implementacion funciones
void Nota::leer_materia()
{
 cout<<"entre materia cursada:"<<endl;
 cin.get(materia,20);
 cin.ignore(20,'\n');
}



void  Nota::recibir()
{
leer_materia();
cout<<"entre notas parciales y final"<<endl;
 for(int i=1;i<= TAM;i++){
 cout<<"nota"<<i<<endl;
 cin>>notas[i];
			}
}





float Nota::computar()
{
float sum=0;
for(int i=1;i<=TAM;i++){
if(i<3){
sum=sum+(notas[i]*por1);}
else {
sum=sum+(notas[i]*por2);}
			}

return sum;
}

void Nota::imprimir(float califica)
{

cout<<"la nota final del area cursada es:"<<endl;
cout<<"********"<<califica<<"*******"<<endl;

}
void Nota::estado_actual()
{
  if((computar()>=2.0)&&(computar()<3.0))
  {   cout<<"usted tiene derecho a habilitar!"<<endl;  }
  if (computar()<2.0)
  { cout<<"usted perdio la materia"<<endl;}

  if (computar()>=3.0) cout<<"­HAS GANADO LA MATERIA!!!"<<endl;
}

 //funcion principal
int  main()
{
float y;
clrscr();
 //declaracion del objeto: dinamicamente
Nota *p=new  Nota();
 //llamado funciones: modo dinamico
p->recibir();
y=p->computar();
p->imprimir(y);
p->estado_actual();
delete p;//liberar memoria
getch();
return 0;
}

