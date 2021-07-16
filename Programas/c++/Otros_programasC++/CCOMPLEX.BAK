/////////////////////////////////////////////////////////////
// Creado por:                             		   //
// Felipe Escallon P y Gustavo Andres Uribe                //
// jul/2002                 				   //
/////////////////////////////////////////////////////////////


#include<iostream.h>
#include<conio.h>

class Complejo
{
  double real,imaginaria;
  public:
    Complejo() { real=0.0; imaginaria=0.0; };
    //f creadora con parametros
    Complejo(int ,int );
    ~Complejo() {/* cout<<endl<<"object destroyed!"<<endl;*/ };//todo de una vez
    void modificar_complejos(int ,int );
    Complejo sumar_complejos(Complejo c);
    Complejo multiplicar_complejos(int ,int );

    //defino e implemento de una sola
    int get_preal() { return real; };
    int get_pimaginaria() { return imaginaria; };
};

Complejo::Complejo(int r,int i)
{
 real=r;
 imaginaria=i;
}

void Complejo::modificar_complejos(int r,int i)
{
 real=r;
 imaginaria=i;
}

Complejo Complejo::sumar_complejos(Complejo c)
{
 c.modificar_complejos(c.get_preal()+real,c.get_pimaginaria()+imaginaria);
 return c;
}

Complejo Complejo::multiplicar_complejos(int r,int i)
{
 Complejo c((r*real)-(i*imaginaria),(r*imaginaria)+(i*real));
 return c;
}

 int main()
 {
  double r1,r2,i1,i2;
  Complejo s,m;
  clrscr();
  cout<<"Complejo #1:"<<endl;
  cout<<"entre parte real:"<<endl;
  cin>>r1;
  cin.ignore();
  cout<<"entre parte imaginaria:"<<endl;
  cin>>i1;
  cin.ignore();
  cout<<"Complejo #2:"<<endl;
  cout<<"entre parte real:"<<endl;
  cin>>r2;
  cin.ignore();
  cout<<"entre parte imaginaria:"<<endl;
  cin>>i2;
  cin.ignore();

  Complejo x(r1,i1);
  Complejo y(r2,i2);
  Complejo resultado;

  resultado=x.sumar_complejos(y);
  cout<<"suma de los 2 complejos:";
  cout<<resultado.get_preal()<<"+"<<resultado.get_pimaginaria()<<"i"<<endl;

  resultado=x.multiplicar_complejos(y.get_preal(),y.get_pimaginaria());
  cout<<endl<<"multiplicacion de los 2 complejos:";
  cout<<resultado.get_preal()<<"+"<<resultado.get_pimaginaria()<<"i"<<endl;


 getch();

 return 0;
 }