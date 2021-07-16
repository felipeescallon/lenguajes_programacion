//////////////////////////////////////////////////////////////////////////////////////////
//																						//
//	Creado por:																			//
//	Gustavo Andrés Uribe Gomez															//
//	Luis Miguel Collazos V.																//
//	Cesar G. Chaves A.																	//
//	Flavio Andres Martinez Erazo														//
//	Andrés Felipe Escallón Portilla															//
//	Jose Wilmer Castillo Obando															//
//	Fabian Leonidas Mosquera Palta														//
//	Jorege David Moncayo Bravo															//
//																						//
//--------------------------------------------------------------------------------------//
//																						//
//	conexion.h:																			//
//	Contiene las clases Conexion, ConexionServidor, Cliente y Servidor					//
//																						//
//////////////////////////////////////////////////////////////////////////////////////////

#ifndef CABECERA_CONEXION
#define	CABECERA_CONEXION

#define PUERTO 7000	//Puerto por defecto
#define rol	enum rol //define rol como la variable tipo enum rol

#include <sys/types.h> //Contiene los tipos de archivos necesarios para el manejo de sockets
#include <sys/socket.h>//para el uso de sockets
#include <arpa/inet.h>// inet_ntoa(): rtorna un *p a la ip en notacion de punto
#include <string.h> //Contiene las funciones para el manejo de cadenas
#include <netinet/in.h>//Permite el manejo de las direcciones de la familia AF_INET
#include <unistd.h>//fork():manejo de procesos
#include "errores.h"//manejo de errores(exepciones)
#include <fstream.h> //Para el manejo de archivos
#include "archivos.h" //Para buscar cadenas en archivos
#include <netdb.h>  //gethostent():obtiene la entrada del host(archivo que esta en la
		    //carpeta etc-> contiene la ip del propio pc)
#include <stdlib.h> //system()		

rol{CLIENTESERVIDOR,CLIENTE};//CLIENTESERVIDOR es el cliente que esta a la espera de una conexion

//--------------------------------------------------------------------------------------//
// Clase conexion																		//
//--------------------------------------------------------------------------------------//

class Conexion
{
protected:	
	int socketIO,socketEscucha;//El socketIO nos permite establecer el intercambio de inforacion
				//El socket escucha permite establecer un socket solo de escucha
	struct sockaddr_in info;//info contiene la direccion IP, el puerto y la familia de conexion (AF_INET)
	char BUF[1024];//enviar y recibir mensajes
	int long_addr;//direccion ip
	rol ROL;
	int estado;//-1 la conexion esta dañada
public:
	Conexion();
	~Conexion();
	int conectar();
	void enviar(char*); //Esta funcion envia un mensaje al servidor para que lo
	                    //incluya en la lista de nodos
	char* recibir();
	int modo();//0:cliente; 1:servidor
	int verEstado();
	void iniciarROL(rol);
	void asignarDireccion(char*);
	void cerrarSocket();
};


//--------------------------------------------------------------------------------------//
//	Clase Servidor																		//		
//--------------------------------------------------------------------------------------//

class Servidor
{
	char listaDeNodos[40];
	char IP[13];
	rol ROL;
public:
	Servidor();
	~Servidor();
	void insertarNodo(char*,char*,Conexion*);
	void generarIP();
	void actualizarRed(char*,Conexion*);
	int modo();
	char* direccion();
	void iniciarROL(rol);
	void manejarMensaje(char*,Conexion*);
	void eliminarNodo(char*,Conexion*);
};

//----------------------------------------------------------------------------------------//

struct in_addr verMiIP();

#endif

