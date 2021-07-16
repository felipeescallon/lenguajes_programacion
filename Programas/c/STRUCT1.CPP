/*catalogo de tarjetas electronico*/
#include<stdio.h>
#include<conio.h>
#include<string.h>
#include<stdlib.h>

#define max 100
/*encabezado fs*/
int menu(void);
void display(int i);
void autor_search((void),title_search(void);
void intro(void),save(void),load(void);

struct catalogo
{
char name[80];/*autor name*/
char title[80];
char publisher[80];/*editorial*/
unsigned date;/*fecha de publicacion*/
unsigned char ed;/*edicion*/
}cat[max];

int top=0;/*last used position*/

void main(void)
{
int opcion;
load();//leer catalogo

do{
  opcion=menu();
  switch(opcion){
   case 1: intro();//introduccion de libros
   break;
   case 2: autor_search(();//autor search
   break;
   case 3: title_(_();//title search
   break;
   case 4: save();
  }
 }while (opcion!=5);
getch();
}

//implementacion fs

menu(void)
{
 int i;
 printf("tarjeta:\n");
 printf("1.entrada\n");
 printf("2.busqueda por autor\n");
 printf("3.busqueda por titulo\n");
 printf("4.guardar catalogo\n");
 printf("5.SALIR\n");

 do{
  printf("elija opcion:");
  i= getche()-'0';
  printf("\n");
   }while(i||i5);

 return i;
 }

 /*introducir libros en database*/
 void intro(void);
 {
  int i;
  char temp[80];
  for(i=top;i<max;i++){
   printf("digite nombre del autor(enter para salir):");
   gets(cat[i].name);
   if(!*cat[i].name) break;
   printf("digite titulo:");
   gets(cat[i].title);
   printf("digite editorial:");
   gets(cat[i].publisher);
   printf("digite fecha de publicacion:");
   gets(temp);
   cat[i].date=(unsigned) atoi(temp);
   printf("digite edicion:");
   gets(temp);
   cat[i].ed=(unsigned char) atoi(temp);
   }

  top=i;
}

/*busqueda por autor*/
void autor_search(void)
{
 char name[80];
 int i,found;

 printf("nombre:");
 gets(name);

 found=0;//logic!
 for(i=0;i<top;i++)
  if(!strcmp(name,cat[i].name)){
      display(i);
      found=1;
      printf("\n");}

  if(!found) printf("no encontrado\n");
 }

/*busqueda por titulo*/
void title_search(void)
{
 char title[80];
 int i,found;

 printf("titulo:");
 gets(title);

 found=0;
 for(i=0;i<top;i++)
   if(!strcmp(title,cat[i].title)){
      display(i);
      found=1;
      printf("\n");}

  if(!found) printf("no encontrado\n");
 }

 /*display catalog entry*/
void display(int i)
{
 printf("%s\n",cat[i].title);
 printf("por %s\n",cat[i].name);
 printf("editorial %s\n",cat[i].publisher);
 printf("editado en %u, %u edicion\n",cat[i].date, cat[i].ed);
}

//recover file*/
viod load(void)
{
 FILE *fp;
 if((fp=fopen("catalogo","r"))==NULL){
   printf("archivo de catalogo no esta en disco\n");
   return;}

 fread(&top,sizeof top,1,fp);/*contador de lectura*/
 fread(cat,sizeof cat,1,fp);

 fclose(fp);
}

/*save catalog file*/
void save(void)
{

  FILE *fp;

  if((fp=fopen("catalogo","w"))==NULL){
   printf("no se puede abrir archivo de catalogo\n");
   exit(1);}

 fwrite(&top,sizeof top,1,fp);
 fwrite(cat,sizeof cat,1,fp);

 fclose(fp);
}
