/////////////////////////////////////////////////////////////
// Creado por:                             		   //
// Felipe Escallon P y Gustavo Andres Uribe                //
// jul/2002                 				   //
/////////////////////////////////////////////////////////////

#include<iostream.h>
#include<conio.h>

/*Dinamicamente: usamos punteros*/
class Suma_vectores{

  int *v1,*v2;
  int l1,l2;//para los ciclos for

//encabezado funciones
 public:
  Suma_vectores(int ,int );//para saber el tama¤o
  ~Suma_vectores();

  void recibir_valores();
  int sumar_vectores();

 };

  Suma_vectores::Suma_vectores(int n1,int n2)
 {
   v1=new (int[n1]);
   v2=new (int[n2]);
   cout<<"objeto creado!"<<endl;
    for(int c1=0;c1<l1;c1++) v1[c1]=0;  //inicializacion
    for(int c2=0;c2<l2;c2++) v2[c2]=0;
   l1=n1;
   l2=n2;
 }



 Suma_vectores::~Suma_vectores()
 {
   delete(v1);//liberar memoria
   delete(v2);
   cout<<endl<<"objeto destruido!!!"<<endl;

 }

 void Suma_vectores::recibir_valores()
 {
   cout<<"entre valor del vector1:"<<endl;
   for(int c1=0;c1<l1;c1++)
   {
    cin>>v1[c1];
    cin.ignore();
   }

   cout<<"entre valor del vector2:"<<endl;
   for(int c2=0;c2<l2;c2++)
   {
    cin>>v2[c2];
    cin.ignore();
   }
 }


 int Suma_vectores::sumar_vectores()
 {
    int suma=0;
    for(int c1=0;c1<l1;c1++)
     {
       suma+=v1[c1];//variable acumuladora
     }
    for (int c2=0;c2<l2;c2++)
     {
      suma+=v2[c2];
     }
   return (suma);
 }



int main()
{
 int l1,l2,r;
 clrscr();

 cout<<"entre el largo del vector1:"<<endl;
 cin>>l1;
 cin.ignore();
 cout<<"entre el largo del vector2:"<<endl;
 cin>>l2;
 cin.ignore();

 /*creamos el objeto*/
  Suma_vectores vectors(l1,l2);

 //enviamos mensaje
 vectors.recibir_valores();
 r=vectors.sumar_vectores();
 cout<<"la suma de todos los elementos de los dos vectores es:"<<r;

 getch();
 return 0;

}