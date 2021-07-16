//////////////////////////////////////////////////////////////////////////////////////////
//																						//
//	Creado por:																			//
//	Gustavo Andrés Uribe Gomez															//
//	Luis Miguel Collazos V.																//
//	Cesar G. Chavez A.																	//
//	Flavio Andres Martinez Erazo														//
//	Andrés Felipe Escallon P.															//
//	Jose Wilmer Castillo Obando															//
//	Fabian Leonidas Mosquera Palta														//
//	Jorge David Moncayo Bravo															//
//																						//
//--------------------------------------------------------------------------------------//
//																						//
//	conexion.cpp:																		//
//	Implementacion de las clases Conexion y Servidor //
//																						//
//////////////////////////////////////////////////////////////////////////////////////////

#include "conexion.h" // Cabecera que contiene la especificacion de las clases  que aqui 
			// se implementaran


//--------------------------------------------------------------------------------------//
//	Constructor de la clase conexion													//
//	
//--------------------------------------------------------------------------------------//

Conexion::Conexion()
{
	estado=1;//en coneccion
}

//--------------------------------------------------------------------------------------//
//	Funcion asignaDirecion								//
//	Parametros:																			//
//	char * IP -> Recibe la dieccion IP del socket.										//
//--------------------------------------------------------------------------------------//

void Conexion::asignarDireccion(char* IP)
{
	info.sin_family= AF_INET;
	info.sin_port=htons(PUERTO);// convierte el numero de puerto a una expresion en formato
								// TCP/IP (short)
	info.sin_addr.s_addr=inet_addr(IP); // convierte un char de ip a un long int equivalente
	long_addr=sizeof(info);
}

//--------------------------------------------------------------------------------------//
// Destructor de la clase conexion														//
//--------------------------------------------------------------------------------------//

Conexion::~Conexion()
{
	ERRORES err;
	if(ROL==CLIENTESERVIDOR)
	{
		err=NOCLOSEESCUCHA;
		if(!shutdown(socketEscucha,2)) tratarExcepcion(err);
	}
	if((estado!=-1)&&(ROL==CLIENTE)) while(recibir()!=0)//si el socket no esta vacio, sigue recibiendo
	                                                    //mensajes
	if(socketIO!=-1)
	{
		err=NOCLOSEIO;
		if(!shutdown(socketIO,2)) tratarExcepcion(err);
	}
}

//--------------------------------------------------------------------------------------//
// Funicion cerrarSocket();														//
//--------------------------------------------------------------------------------------//

void Conexion::cerrarSocket()
{
	ERRORES err;
	if(ROL==CLIENTESERVIDOR)
	{
		err=NOCLOSEESCUCHA;
		if(!shutdown(socketEscucha,2)) tratarExcepcion(err);
	}
	if((estado!=-1)&&(ROL==CLIENTE)) while(recibir()!=0)//si el socket no esta vacio, sigue recibiendo
	                                                    //mensajes
	if(socketIO!=-1)
	{
		err=NOCLOSEIO;
		if(!shutdown(socketIO,2)) tratarExcepcion(err);
	}
}


//--------------------------------------------------------------------------------------//
//	Funcion conectar()																	//
//	Retornos:																			//
//	-1 -> Si no se pudo estabelcer la conexion											//
//	1 -> Si se produjo una conexion														//
//	socketIO -> Identificador del socket para la entrada y salida						//
//--------------------------------------------------------------------------------------//

int Conexion::conectar()
{
	ERRORES err;
	err=NOCONECT;
	if(ROL==CLIENTE)
	{
		estado=connect(socketIO,(struct sockaddr *)&info,sizeof(info));
		if(estado==-1) tratarExcepcion(err);
		return(estado);
	}
	else//tiene que esperar
		{
		listen(socketEscucha,5);//5 conexiones maximo
		socketIO=accept(socketEscucha,(struct sockaddr *)&info,(socklen_t *)&long_addr);
		//socklen_t: tamaño estructura del socket
		return(socketIO);
	}
}

//--------------------------------------------------------------------------------------//
//	Funcion enviar()																	//
//	Parametros:																			//
//	char* Datos -> Contiene los datos para enviar										//
//--------------------------------------------------------------------------------------//

void Conexion::enviar(char* Datos)
{
	send(socketIO,Datos,strlen(Datos)+1,0);//hay que pasar el largo +1:'\0'
}

//--------------------------------------------------------------------------------------//
//	Funcion recibir()																	//
//	Retornos:																			//
//	BUF -> Devuelve el mensaje leido del socket											//
//	0 -> EL socket esta vacio															//
//------------------------------------------------------------------------------------------//

char* Conexion::recibir()
{
	ERRORES err;
	err=SOCKETVACIO;
	if(recv(socketIO,BUF,1024,0)>0)	return BUF;
	//else
	tratarExcepcion(err);
	return 0;
}

//----------------------------------------------------------------------------------------------//
//	Funcion verEstado()																			//
//	Retornos:																				//
//	-1 -> Conexion dañada																			//
//	Diferente a -1 -> Conexion activa																		//
//----------------------------------------------------------------------------------------------//

int Conexion::verEstado()
{
	return(estado);
}


//----------------------------------------------------------------------------------------------//
//	Funcion modo()																			//
//	Retornos:																				//
//	1 -> CLIENTE																			//
//	0 -> CLIETESERVIDOR																		//
//----------------------------------------------------------------------------------------------//

int Conexion::modo()
{
	if(ROL==CLIENTESERVIDOR)
		return(0);
	return(1);//else
}

//----------------------------------------------------------------------------------------------//
//	Funcion iniciarROL()									//
//	Parametros:										//
//	R -> Rol que va a tomar  la clase servidor en esta maquina				//
//----------------------------------------------------------------------------------------------//

void Conexion::iniciarROL(rol R)
{
	ROL=R;
	if(ROL==CLIENTESERVIDOR)
	{
		socketEscucha=socket(AF_INET,SOCK_STREAM,0); //Para el CLIENTESERVIDOR
		bind(socketEscucha,(struct sockaddr*)&info,sizeof(info));//asocia una ip al socket
	}
	else
	{
		socketIO=socket(AF_INET,SOCK_STREAM,0);//abre un canal para la conexion
	}
}



//----------------------------------------------------------------------------------------------//
//	Constructor de la clase servidor							//
//----------------------------------------------------------------------------------------------//

Servidor::Servidor()
{
	strcpy(listaDeNodos,"nodosred.fss");
}

//------------------------------------------------------------------------------------------//
// Destructor de la clase servidor															// 
//------------------------------------------------------------------------------------------//

Servidor::~Servidor()
{
	ofstream archivo(listaDeNodos);
	archivo.write(" \n",3);
	archivo.close();
}

//------------------------------------------------------------------------------------------//
// Funcion insertarNodo()																	//
// Parametros:																				//
// IP -> Direccion IP del nuevo nodo														//
// C -> Puntero a la conexion que se mantiene con el servidor								//
//------------------------------------------------------------------------------------------//

void Servidor::insertarNodo(char* men,char* IP,Conexion *C)
{
  char *mensaje= new char[100];//declara mensaje con 100
  char caracter;
  if(C->verEstado()!= -1)
  	if(ROL==CLIENTE)
  	{ 	
		strcpy(mensaje,"SOYNUEVO:");
		strcat(mensaje,IP);
		strcat(mensaje,"\n");
		C->enviar(mensaje);
  	}
  	else
  	{       ifstream nodosredp("nodosred.fss");
  		nodosredp.get(caracter);
  		nodosredp.close();
 		if(caracter!='<')
 		{
 			ofstream nodosred("nodosred.fss",ios::app);//para añadir informacion al archivo
 			nodosred.write(men,strlen(men));
			nodosred.write(" \n",3);
			nodosred.close();
 		}
 		else
 		{
 			ofstream nodosred("nodosred.fss");
 			nodosred.write(men,strlen(men));
			nodosred.write(" \n",3);
			nodosred.close();
 		}
 	 }
}


//----------------------------------------------------------------------------------------------//
// Funcion generarIP()																			//		 	
// 																						//
// Guarda en el atributo IP de la clase Servidor, la IP del servidor que se encuentre en el		//
// archivo opciones.fss																			//
//----------------------------------------------------------------------------------------------//

void Servidor::generarIP()
{
	char c;
	int cont;
	cont=buscar("opciones.fss","SERVIDOR:");
	ifstream fichero("opciones.fss");
	while(cont!=0)
	{
	 fichero.get(c);
	 cont--;
	}
	fichero.get(c);
	fichero.get(IP,13);//lee la ip y la guarda
	fichero.close();
}

//----------------------------------------------------------------------------------------------//
//	Funcion actualizarRed()																	//
//	Actualiza la lista de nodos																//
//----------------------------------------------------------------------------------------------//

void Servidor::actualizarRed(char* mensaje,Conexion* C)
{
        insertarNodo(mensaje,inet_ntoa(verMiIP()),C);
}



//----------------------------------------------------------------------------------------------//
//	Funcion modo()																			//
//	Retornos:																				//
//	1 -> CLIENTE																			//
//	0 -> CLIETESERVIDOR																		//
//----------------------------------------------------------------------------------------------//

int Servidor::modo()
{
	if(ROL==CLIENTESERVIDOR) return(0);
	return(1);
}

//----------------------------------------------------------------------------------------------//
//	Funcion direccion()																		//
//	Retornos:																				//
//	IP -> La Ip del computador escogido como servidor										//
//----------------------------------------------------------------------------------------------//

char*  Servidor::direccion()
{
	return(IP);
}

//----------------------------------------------------------------------------------------------//
//	Funcion iniciarROL()									//
//	Parametros:										//
//	R -> Rol que va a tomar  la clase servidor en esta maquina				//
//----------------------------------------------------------------------------------------------//

void Servidor::iniciarROL(rol R)
{
	ROL=R;
}

//----------------------------------------------------------------------------------------------//
//	Funcion manejarMensaje()								//
//	Parametros:										//
//	char* mensaje -> mensaje a tratar							//
//	Conexion *C -> Conexion por donde se obtienen los mensajes				//
//----------------------------------------------------------------------------------------------//

void Servidor::manejarMensaje(char *mensaje, Conexion* C)
{
 	char *cabecera=new char[100];
 	int cont=0, cont2=0;
 	while(mensaje[cont] != ':')
	{
	 cabecera[cont]=mensaje[cont];
	 cont++;
	}
	cont++;
	while(mensaje[cont] != '\n')
	{
	 mensaje[cont2]=mensaje[cont];
	 cont++;
	 cont2++;
	}
 	mensaje[cont2]= '\0';//Conrrexion
 	if(!strcmp(cabecera,"SOYNUEVO"))//srtrcmp(): es al reves
 	{
 		actualizarRed(mensaje,C); 		
 	}
 	if(!strcmp(cabecera,"MEMUERO"))//srtrcmp(): devuelve 0 si son iguales
 	{
 		eliminarNodo(mensaje,C); 		
 	}
 	if(!strcmp(cabecera,"MENSAJE"))
 	{
 		//ofstream mensajes("mensajes.fss");
 		//mensajes.write(mensajes,strlen(mensaje));
 		//mensajes.close();
 	}
}

//----------------------------------------------------------------------------------------------//
//	Funcion eliminarNodo									//
//----------------------------------------------------------------------------------------------//	

void Servidor::eliminarNodo(char* IP,Conexion *C)
{
	char *mensaje= new char[100];//declara mensaje con 100
	char caracter;
	int cont;
  	if(C->verEstado()!= -1)
 		if(ROL==CLIENTE)
  		{ 	
			strcpy(mensaje,"MEMUERO:");
			strcat(mensaje,IP);
			strcat(mensaje,"\n");
			C->enviar(mensaje);
  		}
  		else
  		{
  			
  			cont=buscar("nodosred.fss",IP);
  			ifstream nodosred("nodosred.fss");
  			ofstream temp("temp.fss");
  			cont-=strlen(IP);
  			nodosred.get(caracter);
  			cont--;
  			if(caracter!='<')
  			{
  				while(cont!=0)
  				{
  					nodosred.get(caracter);
  					temp.put(caracter);
  					cont--;
  				}
  				for(int cont2;cont2<=strlen(IP);cont2++) nodosred.get(caracter);
  				while(!nodosred.eof())
  				{
  					nodosred.get(caracter);
  					temp.put(caracter);
  				}
  				system("rm nodosred.fss");
  				system("mv temp.fss nodosred.fss");
  			}
  			temp.close();
  			nodosred.close();	
 		}
}

//----------------------------------------------------------------------------------------------//
// Funcion verMiIP										//		
// Retornos:											//
// struct in_addr IP -> Devuelve en una estructura la IP de la maquina actual			//
//----------------------------------------------------------------------------------------------//

struct in_addr verMiIP()
{
 	struct hostent* host;
	struct in_addr IP;
	host=gethostent();
	IP.s_addr= *(long *)host->h_addr;//h_addr contiene la dir. del host en entero,
					// entonces debemos convertir el host a dicho formato
	if(strcmp(inet_ntoa(IP),"127.0.0.1"))//dir poe defecto(interna)	del pc
	{	
		endhostent();
		return(IP);
	}	
	host =gethostent();
	IP.s_addr= *(long *)host->h_addr;
        endhostent();
        return(IP);
}	


