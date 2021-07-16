/////////////////////////////////
//BUSQUEDA DE ARCHIVOS         //
/////////////////////////////////
#include<string.h>
#include<fstream.h>//para archivos
#include<stdlib.h>//para system(" ");

/*global*/
int buscar(char* archivo,char* objeto,int);//busca en el archivo el objeto,0:retorna # caracteres
					   //leidos;1:retorna # lineas leidas
class Lista
{

   char nombre;//nombre de la lista
  public:
   
  Lista(char n);
  ~Lista();

  void leerArchivo(char* filename,int);//obtener dirreciones


  void pregenerarLista(int);//organiza todo para que se genera la lista:recibe el no. de lineas
  void generarLista(char*);
  void buscar(char* );//llama internamente a escribirResultado   void escribirResultado( int);
  void escribirResultado(int );
};
    /*********************************************************************/

Lista::Lista(char n)
{
 nombre=n;
}


Lista::~Lista()
{
}


void Lista::leerArchivo(char* filename, int c)
{
  int i=0;
 ifstream archivo(filename);

  char ch;



   while(i<c){ archivo.get(ch); cout<<ch;  i++;}

 archivo.close();

}


/*************************************************************************************/

void Lista::pregenerarLista(int r)
{
  char comando [150];
  char ch;
  int i=0;
  ofstream archivo("lista");//limpio el archivo
  archivo.close();
  ifstream archivo1("opciones.fss")  ;
  do{archivo1.get(ch);}while(ch!='\n');

  do {i++;
  ofstream archivo2("comando.fss")  ;
  archivo2<<"find ";
  archivo1.get(ch);

   while(ch!='\n') {archivo2<<ch; archivo1.get(ch);  }//estoy leyendo la ruta de op.fss y la guardo
   						      //en comando.fss        	
    archivo2<<"> lista2";
  archivo2.close();

   ifstream archivo3("comando.fss");
   archivo3.get(comando,100);
   archivo3.close();

   generarLista(comando); }while(i<r);
   archivo1.close();
}
void Lista::generarLista(char* comando)
{
	
   char ch;
  system(comando);//system("find ruta>lista2");
  ofstream archivo("lista",ios::app);//se usa para ir al final del archivo, para añadir
  ifstream archivo2("lista2");
         do{                              //copia lo de lista2 a lista
  			archivo2.get(ch);
  			archivo<<ch;
            } while(!archivo2.eof());
 archivo.close();
 archivo2.close();
}

void Lista::buscar(char* abuscar){
           //guardo lo que hay que buscar
           int i=0;//contadores
           int q=0;
		   char d;//para leer archivos caracter a caracter
		

           //pa escritura
           ofstream bus("buscar.fss");  //en buscar guardo lo que se va a buscar
           if(!bus) {
	          cout<<"el archivo buscar.fss no puede abrirse./n";
         
                 } 
           bus<<abuscar<<"^";//caracter diferenciador
           bus.close();


           ofstream aco("lista2",ios::app);//par ir al final del archivo
           if(!aco) {                     cout<<"lista 2";
	          cout<<"el archivo de no puede abrirse que tal./n";
        
                 }
		   aco<<'!';//caracter diferenciador
           aco.close();

           //pa lectura
           ifstream rec("buscar.fss");
           if(!rec) {            cout<<"buscar.fss";
	          cout<<"el archivo de no puede abrirse que tal./n";
        
                 }
           while(!rec.eof()){ rec.get(d); i++;}


           rec.close(); 



           //cout<<endl<<"El archivo digitado fue :"<<abuscar<<endl;
           ofstream tado("resultado.fss");
	       tado.close(); 
	       ifstream nem("lista");
           ofstream res("resultados");
           if(!res) {  cout<<"res";
	          cout<<"el archivo de no puede abrirse que tal./n";
        
                 }
	       if(!nem) {          cout<<"nem";
	          cout<<"el archivo de no puede abrirse que tal./n";
        
                 }
		    //cout<<"Buscando"<<"... . ";
           
	//////////////////////////////////////////////////////////////////////////
	
		   char ch;
           /*cadenas comienzan desde 0*/
           int c=i-2;//tamaño de abuscar empezando desde 0:le quito el '/0' y arrancaba en un 1
           i=0;//lo inicializa de nuevo
           int p=0;//no. de car leidos
           q=0;//inicializa de nuevo y me guarda los resultados
           int r=0;//no. car leidos hasta la ultima linea


	    while (!nem.eof())
	       {
               nem.get(ch);
	       p++;
	
        
	        if( ch==abuscar[i] ) i++;        //va comparando con lo que hay que buscar y si es igual aumenta uno al contador
	         else if (c==i){             //p-i:posicion en donde inicia la cadena a buscar
	                        if(i>0) q++; res<<p-i<<" Ç "; escribirResultado(r);
	                        i=0;//para poder comparar otra vez
	                        do{nem.get(ch);p++;}while(ch!='\n');
	                        }//cuando se encuentra la cadena que se necesita se llama la funcion escribirResultado
	             else i=0;//se hace si c no es = a i
              
	         if( ch=='\n' ) r=p;         //r es una referencia de linea
			  
	       }//termina el ciclo
           
           nem.close();
           res.close();
           /*i se lo uiliza pa compara SIEMPRE COMO CONTADOR*/
           i=0;
           


		ofstream fichero("buscar.fss",ios::app);
           if(!fichero) {             cout<<"buscar.fss";
	          cout<<"el archivo de no puede abrirse que tal./n";
        
                 }
           fichero<<'\n'<<q;//guarda en buscar el # de resultados

           fichero.close(); 
	


          /*  ////////////////////muestra el resultado de la busqueda
               //cout<<q<<" "<<"resultado(s)" ;
	      ifstream ado("resultado.fss");
	      if(!ado) {
	      cout<<"el archivo resultado.fss no puede abrirse que tal./n";
        
                 }
		  i=1;
		
         while(!ado.eof()){
         ado.get(ch);
		 if ((ch== '\n' || i==1) && q>i-1){cout<<endl<<" "<<i<<": ";
										i++;
										if (i==2)cout<<ch;
						  }
                             else cout<<ch;
		                      
                      }
         ado.close();
         ////////////////////////////////
         ///////finaliza la muestra del resultado */

}


void Lista ::escribirResultado(int r){
    //r  es la linea de texto a transferir de lista2 a resultado.fss
    int u=0;//PARA RECORRER EL ARCHIVO HASTA LA LINEA DE BUSQUEDA
	char t;

	
	
	ifstream ar("lista2");//se abre lista2
    if(!ar) {
	      cout<<"el archivo lista2 no puede abrirse que tal./n";
        
                 }
	
	ofstream tado("resultado.fss",ios::app);//se abre reultado.fss(OFICIAL) y se va hasta el final
    if(r!=0) do {ar.get(t); u++;}while(u<r );//recorre el archivo hasta la linea que se quiere guardar

    /*!:end of file*/
    do {ar.get(t); tado<<t;}while(t!='\n' && t!='!');//escribe caracter por caracter en resultado.fss
    tado.close();
	ar.close(); 
  
					
}

/**************************************************************************************/


int main(int argc,char *argv[])
{
	 int c=0,r=0;
	
	Lista *objeto=new Lista('l');
	
	 /*en c se guarda el numero de car leidos*/
	 c= buscar("opciones.fss","SERVIDOR:",0)-10;//obtienre en que posicion empieza servidor
	 /*-2:no me interesa las lineas de los extremos que se encuentran en el archivo  */
	
	 r= buscar("opciones.fss","SERVIDOR:",1)-2; //obtiene el numero de directorios o lineas
	
	
    
	//objeto->leerArchivo("opciones.fss",c);//muestra los directorios compartidos	
	
	objeto->pregenerarLista(r);
	
	
	system("cat lista > lista2");//REALIZA UNA COPIA DE LISTA

		/*argc empieza en 0->se le paso el primer parametro llamando al ejecutable*/
		if(argc==1)
		{   //verifica los argumentos de entrada
		char parabuscar[20];
		
		cout<<" Digite el nombre del archivo que desea buscar: "<<endl;
	 	cin.get(parabuscar,19);//-1 por el '/0'
		objeto->buscar(parabuscar);
		}
		
			else{
		         //es el buscar de la clase
       		         objeto->buscar(argv[1]);//comienza en cero
		            }
	delete(objeto);
	
	return 0;
}






//global
int buscar(char* archivo,char* objeto,int r)
{
	char bufer[100];
	int caracteresLeidos=0,i=0;
	char c;
		
	
	ifstream fichero(archivo);
	
	do
	{
		fichero.get(bufer,100);
		caracteresLeidos+=strlen(bufer);//guarda el no. de car leidos
		if(!strcmp(bufer,objeto))
	 	{
			fichero.close();

			if(r=0)return(caracteresLeidos);
		        if(r=1)return(i);//no.lineas
		}
		
		fichero.get(c);
		caracteresLeidos++;
		if (c='\n') i++;
	}while(!fichero.eof());
	
	fichero.close();
	return(0);
}
