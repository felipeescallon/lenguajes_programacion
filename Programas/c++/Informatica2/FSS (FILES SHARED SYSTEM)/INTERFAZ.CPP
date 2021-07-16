//////////////////////////////////////////////////////////////////////////////////////////////////
//												//
//	Creado por:										//
//	Gustavo Andrés Uribe Gomez								//
//	Luis Miguel Collazos V.									//							//
//	Cesar G. Chavez A.									//
//	Flavio Andres Martinez Erazo								//
//	Andrés Felipe Escallon Portilla								//
 //	Jose Wilmer Castillo Obando								//
 //	Fabian Leonidas Mosquera Palta								//
//	Jorege David Moncayo Bravo				
//												 //					
//----------------------------------------------------------------------------------------------// //												// //	interfaz.cpp:										//
//	Implementacion del manejador de interfaz		
// 												//					
//////////////////////////////////////////////////////////////////////////////////////////////////

#include "interfaz.h"

//----------------------------------------------------------------------------------------------//
//	Constructor de la interfaz								//
//----------------------------------------------------------------------------------------------//

Interfaz::Interfaz()
{
	ubicacion=BUSQUEDA;
	//ubicacion=OPCIONES;
	lineaDeResultados=0;
	lineaDeNodos=0;
	busqueda[0]=' ';
	busqueda[1]='\0';
	estado[0]=' ';
	estado[1]='\0';
}

//----------------------------------------------------------------------------------------------//
//	Destructor de la interfaz								//
//----------------------------------------------------------------------------------------------//

Interfaz::~Interfaz()
{

}

//----------------------------------------------------------------------------------------------//
//	Funcion cambiarEstado									//
//	Parametros:										//
//	nuevoEstado -> Nuevo estado del programa
//----------------------------------------------------------------------------------------------//

void Interfaz::cambiarEstado(char* nuevoEstado)
{
	strcpy(estado,nuevoEstado);
}

//----------------------------------------------------------------------------------------------//
//	Funcion cambiarEstado									//
//	Parametros:										//
//	nuevaBusqueda -> Nuevo estado del programa
//----------------------------------------------------------------------------------------------//

void Interfaz::cambiarBusqueda(char* nuevaBusqueda)
{
	strcpy(busqueda,nuevaBusqueda);
}

//----------------------------------------------------------------------------------------------//
//	Funcion dibujarInterfaz									//
//	Dibuja toda la interfaz									//
//----------------------------------------------------------------------------------------------//

void Interfaz::dibujarInterfaz()
{
  system("clear");
  char caracter;

  //Primera Linea
  cout<<'/';
  for(int cont=0;cont<70;cont++)
  {
   cout<<'-';
  }
  cout<<'\\'<<endl;
  cout<<(char)124<<' ';

  //Linea de Busqueda

  if(ubicacion==BUSQUEDA)
  {
   cout<<(char)187<<' ';
  }
  else
  {
  	cout<<"  ";
  }
  cout<<"Buscar: "<<busqueda;
  for(int cont=0;cont<(45-strlen(busqueda));cont++)
  {
   cout<<' ';
  }
  cout<<' '<<(char)124<<' ';

  //Parte de Opciones

  if(ubicacion==OPCIONES)
  {
  	cout<<(char)187<<' ';
  }
  else
  {
  	cout<<"  ";
  }

  cout<<"Opciones "<<(char)124<<endl;


  //Linea de separacion
  cout<<'/';
  for(int cont=0;cont<70;cont++)
  {
   cout<<'-';
  }
  cout<<'\\'<<endl;

  //Lineas de Resultados

  ifstream resultados("resultados.fss");
  for(int cont2=0;cont2<10;cont2++)
  {	
  	cout<<(char)124<<' ';
  	if(lineaDeResultados != cont2)	cout<<' ';
  	if(lineaDeResultados == cont2 && ubicacion==RESULTADOS)	cout<<(char)187;
  	for(int cont=0;cont<68;cont++)
  	{
  	 resultados.get(caracter);
  	 if(!resultados.eof())
  	 {
  	 	if(caracter!='\n')
  	 	{	
  	 		cout<<caracter;
  	 	}
  	 	else
  	 	{	
  	 		for(int cont3=0;cont3<68-cont;cont3++) cout<<' ';
  	 		int temp=cont;
   	 		for(int cont3=0;cont3<38-temp;cont3++) cont++;
  	 		
  	 	}	
  	 }
  	 else
  	 {
  	  	cout<<' '; 	
  	 }
  	}
	if((ubicacion!=RESULTADOS) &&  cont2==0) cout<<' ';
	cout<<(char)124<<endl;
  }
  resultados.close();
  //Linea de separacion

  cout<<'\\';
  for(int cont=0;cont<70;cont++)
  {
   cout<<'-';
  }
  cout<<'/'<<endl;
  cout<<'/';

  //Listas de Nodos
  ifstream lista("nodosred.fss");
  ifstream mensajes("mensajes.fss");
  for(int cont2=0;cont2<6;cont2++)
  {	
  	if(cont2!=0) cout<<(char)124;
  	//Lee 38 caracteres del archivo de listad de nodos
  	if(!(lineaDeNodos == cont2 && ubicacion==NODOS))	cout<<' ';
  	if(lineaDeNodos == cont2 && ubicacion==NODOS)	cout<<(char)187;
  	for(int cont=0;cont<38;cont++)
  	{
  	    lista.get(caracter);
  	    if(!lista.eof())
  	    {
  	      	 if(caracter!='\n')
  	 	{	
  	 		cout<<caracter;
  	 	}
  	 	else
  	 	{	
  	 		for(int cont3=0;cont3<38-cont;cont3++) cout<<' ';
  	 		int temp=cont;
   	 		for(int cont3=0;cont3<38-temp;cont3++) cont++;
  	 	}	
  	    }
  	    else
  	    {
  	  	cout<<' '; 	
  	    }
  	}
  	cout<<' '<<(char)124<<' ';
  	//Lee los 28 caracteres del archivo de mensajes
  	for(int cont=0;cont<27;cont++)
  	{
  	 mensajes.get(caracter);
  	 if(!mensajes.eof())
  	 {
  	 	if(caracter!='\n' && caracter!=9)
  	 	{	
  	 		cout<<caracter;
  	 	}
  	 	else
  	 	{	
  	 		for(int cont3=0;cont3<27-cont;cont3++) cout<<' ';
   	 		int temp=cont;
   	 		for(int cont3=0;cont3<38-temp;cont3++) cont++;
  	 	}	
  	 }
  	 else
  	 {
  	  	cout<<' '; 	
  	 }
  	}
  	if(cont2==0) cout<<"  \\"<<endl;
  	if(cont2!=0) cout<<' '<<(char)124<<endl;
  }
  mensajes.close();
  lista.close();
  //Linea de separacion

  cout<<'\\';
  for(int cont=0;cont<70;cont++)
  {
   cout<<'-';
  }
  cout<<'/'<<endl;

  //Linea de estado
  cout<<(char)124<<' ';
  cout<<estado;
  for(int cont=0;cont<(68-strlen(estado));cont++)
  {
   cout<<' ';
  }
  cout<<' '<<(char)124<<endl;

  //Ultima linea
  cout<<'\\';
  for(int cont=0;cont<70;cont++)
  {
   cout<<'-';
  }
  cout<<'/'<<endl;
}

//------------------------------------------------------------------------------------------//
// Funcion manejarInertaz                                                                   //
// Se encarga de manejar el teclado en la interfz					    //
//------------------------------------------------------------------------------------------//

OPERACIONES Interfaz::manejarInterfaz()
{
 OPERACIONES op;
 char caracter;

 cin.get(caracter);
 //cin.ignore(100,'\n');
 switch(caracter)
 {
 	case 10:{                  //ENTER
 			switch(ubicacion)
 			{
 				case BUSQUEDA:{
 							ubicacion=OPCIONES;
 							break;
 						}
 				case OPCIONES:{
 							ubicacion=RESULTADOS;
 							break;
 						}
 				case RESULTADOS:{
 							ubicacion=NODOS;
 							break;
 						}
 				case NODOS:{
 							ubicacion=CHAT;
 							op=ENVIARMENSAJE;
 							break;
 						}
 				case CHAT:{
 							ubicacion=BUSQUEDA;
 							//char *busqueda1=new char[1024];
 							//cin.get(busqueda1,1024);
 							//cin.ignore(1024,'\n');
 							//cambiarBusqueda(busqueda1);
 							break;
 						}								
 			}
 			break;
 		}
 	case 27:{             //ESC
 			op=TERMINAR;
 			break;
 		}
 	case 9:{         //TAB
 			switch(ubicacion)
 			{
 				case BUSQUEDA:{
 							op=BUSCAR;
 							break;
 						}
 				case OPCIONES:{
 							op=OPTIONS;
 							break;
 						}
 				case RESULTADOS:{
 							op=TRANSFERIR;
 							break;
 						}
 				case NODOS:{
 							ubicacion=CHAT;
 							ofstream mensajes("mensajes.fss");
 							mensajes.write(inet_ntoa(verMiIP()),13);
 							mensajes.put(':');
 							op=ENVIARMENSAJE;
 							mensajes.close();
 							break;
 						}
 			}
 			break;
 		}
 	default:{
 			ofstream mensajes("mensajes.fss");
 			mensajes.write(inet_ntoa(verMiIP()),13);
 			mensajes.put(':');
 			mensajes.put(caracter);
 			op=ENVIARMENSAJE;
                        mensajes.close();
               }

 }
 dibujarInterfaz();
 return(op);
}


//----------------------------------------------------------------------------------------------//
// Funcion verBusqueda										//
// Retornos:											//
// busqueda -> Devuelve el atributo busqueda de la clase					//
//----------------------------------------------------------------------------------------------//

char* Interfaz::verBusqueda()
{
	return(busqueda);
}

//----------------------------------------------------------------------------------------------//
// Funcion verEstado										//
// Retornos:											//
// Estado -> Devuelve el atributo estado de la clase						//
//----------------------------------------------------------------------------------------------//

char* Interfaz::verEstado()
{
	return(estado);
}

