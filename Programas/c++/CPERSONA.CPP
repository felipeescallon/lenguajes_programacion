/*creado por Felipe Escallon P_____jul/2002*/
/*clase persona*/

#include<conio.h>
#include"iostream.h"
//private: por DEFECTO
class Persona
{
public:
long int identificacion;
char nombre[15];

public:
Persona();
~Persona();

};

Persona::Persona(){
cout<<"obj creado";}

Persona::~Persona(){
cout<<"obj destruido!!!";}

int main()
{
clrscr();
char nombre[15];
long int identificacion;
Persona p;

cout<<"entre su nombre:"<<endl;
cin>>nombre;

cout<<"entre su identificacion:"<<endl;
cin>>identificacion;

cout<<p.identificacion<<endl;
cout<<p.nombre<<endl;

getch();
return 0;


}

