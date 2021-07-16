//////////////////////////////////////////////////////////////////////////////////////////
//																					//
//	Creado por:																			//
//	Gustavo Andrés Uribe Gomez															//
//	Luis Miguel Collazos V.																//
//	Cesar G. Chaves A.																	//
//	Flavio Andres Martinez Erazo														//
//	Andrés Felipe Escallon Portilla.													// 
//	Jose Wilmer Castillo Obando															//
//	Fabian Leonidas Mosquera Palta														//
//	Jorge David Moncayo Bravo															//
//																					//
//------------------------------------------------------------------------------------------//
//																					//
//	FSS.cpp:																			//
//	Funcion principal del programa										 				//
//																					//
//////////////////////////////////////////////////////////////////////////////////////////////

#include <signal.h> //Permite el manejo de las señales del sistema
#include "conexion.h"
#include "errores.h"
#include "interfaz.h"
#include <iostream.h>//Entrada y salida estandar.
#include <stdlib.h> //exit(),system()
#include <netdb.h>  //gethostent()
#include <sys/wait.h> //Funcion wait()

void cerrar(int);//Para las salidas abruptas
int iniciarConexiones(int,char*);//inicia las conexiones
void iniciarArchivos();//inicia los archivos
static Conexion* CC,*CS;// Sirve solo en este archivo
static Servidor* S1; 

int main(int argc, char *argv[])//pareametros del main: maneja linea decomandos 
{ 	
	//Declaracion de variables
	ERRORES err;
	OPERACIONES op;
	struct in_addr IP;
	char* mensaje=new char[1024];
	int estado;
	
	iniciarArchivos();
			
        IP=verMiIP();//Obtiene la IP de la maquina actual
		
	S1=new Servidor();
	S1->generarIP();
	if(!strcmp(inet_ntoa(IP),S1->direccion()))//inet_ntoa(): retorna un puntero a un char con la ip
						  //en notacion de punto	
	{
		 S1->iniciarROL(CLIENTESERVIDOR);
	}
	else
	{
		S1->iniciarROL(CLIENTE);	
	}
	
	CS=new Conexion();
	CC=new Conexion();
		
	Interfaz interfaz;
		
	signal(SIGINT,cerrar);	
	signal(SIGTERM,cerrar);
	signal(SIGCLD,SIG_IGN);//Para no crear zoombies
	
	int PID = getpid();
	//Generar la lista de los archivos a compartir (WILMER y  FELIPE , FABIAN , CESAR)
	{
		switch(fork())
		{
		case -1:{
				 tratarExcepcion(NOHIJO);
				 break;
			}
		case 0:{	//HIJO
				ofstream mensajes1("mensajesHijo.log");  //Todos los archivo .log
									//son codigo de testeo
				if(argc>1)
	 				if(!strcmp(argv[1],"log"))
						mensajes1.write("Entra al HIJO\n",15);
				
				interfaz.dibujarInterfaz();
	
				if(argc>1)
					if(!strcmp(argv[1],"log"))
					mensajes1.write("Interfaz iniciada\n",19);
				
				mensajes1.close();
													
				estado=iniciarConexiones(argc,argv[1]);
				
				ofstream mensajes2("mensajesHijo.log",ios::app);
								
				if(estado == -1)
					 interfaz.cambiarEstado("No se pudo establecer la conexion");
								
				interfaz.dibujarInterfaz();
								
				err=NOCONECT;
				if(S1->modo())
				{
					do
					{
						op=interfaz.manejarInterfaz(); 	
						switch(op)
						{
							case TERMINAR:{
									kill(PID,SIGINT); //Envia una señal de interrupcion
										//al proceso padre, para que se termine la ejecucion
									break;
									}			
							case ENVIARMENSAJE:{
										if(estado != -1)	
										{
										char *men=new char[1024];
										strcpy(men,"MENSAJE:");
										interfaz.cambiarEstado("Escriba un mensaje");
										interfaz.dibujarInterfaz();
										//cin.get(mensaje,1000);
										//cin.ignore(1000,'\n');
										strcpy(mensaje,"hola martin");
										strcat(men,mensaje);
										strcat(men,"\n");
										CC->enviar(men);
										ofstream chat("mensajes.fss");
 										chat.write(men,strlen(men));
 										op=ENVIARMENSAJE;
 										chat.close();
										if(argc>1)
	 										if(!strcmp(argv[1],"log"))
											{	
												mensajes2.write("Mensaje Enviado:",17);
												mensajes2.write(men,1024);
												mensajes2.write("\n",15);
											}	
										}
										interfaz.dibujarInterfaz();
										break;
									}
							case	BUSCAR:{
										char *busqueda1=new char[1024];
										strcpy(busqueda1,"./buscar ");
										strcat(busqueda1,interfaz.verBusqueda());
										system(busqueda1);
										break;
									}		
						}
					}while(op!=TERMINAR);	//Se repite mientras se escoga la opcion
									//TERMINAR
				}
				if(argc>1)
	 				if(!strcmp(argv[1],"log"))
	 					mensajes2.write("Sale del hijo\n",15);
				mensajes2.close();
				exit(0);
				break;
			   }
		default:{	//PADRE
					ofstream mensajes1("mensajesPadre.log");
					if(argc>1)
	 					if(!strcmp(argv[1],"log"))
	 						mensajes1.write("Entra al padre\n",16);	
			 	 	
			 	 	mensajes1.close();
			 	 	
			 	 	CS->asignarDireccion(inet_ntoa(IP));
			 	 	CS->iniciarROL(CLIENTESERVIDOR);
								 	 	
			 	 	ofstream mensajes4("mensajesPadre.log",ios::app);
			 	 	
			 	 	if(argc>1)
	 					if(!strcmp(argv[1],"log"))
	 						mensajes4.write("Conexion iniciada como cliente servidor\n",41);	
			 	 	
			 	 	CS->conectar();
			 	 	while(1)
					{
					 	if(argc>1)
	 						if(!strcmp(argv[1],"log"))
	 							mensajes1.write("Una conexion ha sido aceptada\n",16);	
			 	 	
					 	if(!S1->modo())
					 	{
							interfaz.cambiarEstado("Esperando una conexion");
							//char *temp=new char[1024];
							//while(temp!=0) temp=CS->recibir();
							S1->manejarMensaje(CS->recibir(),CS); 		
							interfaz.dibujarInterfaz();
					 	}
					}
					if(argc>1)
	 					if(!strcmp(argv[1],"log"))
	 						mensajes4.write("Sale del padre\n",16);	
	 				mensajes4.close();	
					wait(0); //Espèra que los procesos hijos mueran
					break;	
			}
		}
	}
	return (0);
}

int iniciarConexiones(int argc,char* argv)
{
	ofstream mensajes("mensajesHijo.log",ios::app);
	struct in_addr IP;
	IP=verMiIP();
	
	if(!strcmp(inet_ntoa(IP),S1->direccion()))
	{
	   	CS->asignarDireccion(S1->direccion());
	   	CS->iniciarROL(CLIENTESERVIDOR);
	 	if(argc>1)
	 		if(!strcmp(argv,"log"))
	 		{
				mensajes.write("Servidor iniciado como CLIENTESERVIDOR con la IP ",50);
	 			mensajes.write(S1->direccion(),12);
				mensajes.write("\n",2);
			}	
	 	mensajes.close();
	 	return(CS->verEstado());
	 }
	else
	{
		CC->asignarDireccion(S1->direccion());
		CC->iniciarROL(CLIENTE);
		CC->conectar();
		if(argc>1)
	 		if(!strcmp(argv,"log"))
	 		{
				mensajes.write("Se ha intentado conectar con la IP ",36);
				mensajes.write(S1->direccion(),12);
				mensajes.write("\n",2);
			}	
		S1->actualizarRed(" ",CC);
		if(argc>1)
	 		if(!strcmp(argv,"log"))
	 			mensajes.write("Servidor iniciado como CLIENTE\n",32);
		mensajes.close();
		return(CC->verEstado());
	}
}

void cerrar(int sig)
{
	//S1->eliminarNodo(inet_ntoa(verMiIP()),CC);//elimina el nodo de la lista de nodos
	delete(S1);
	delete(CS);
	delete(CC);
	system("clear");
	exit(0);
}

void iniciarArchivos()
{	
	ofstream orrores("error.log");
	orrores.write(" ",20);
	orrores.close();
	ofstream resultados("resultados.fss");
	resultados.write("<< SIN RESULTADOS >>",20);
	resultados.close();
	ofstream Lista("nodosred.fss");
	Lista.write("<< NO HAY NODOS CONECTADOS >>",29);
	Lista.close();
	ofstream mensajes("mensajes.fss");
	mensajes.write("<< Bienvenido >>",17);
	mensajes.close();
}	
