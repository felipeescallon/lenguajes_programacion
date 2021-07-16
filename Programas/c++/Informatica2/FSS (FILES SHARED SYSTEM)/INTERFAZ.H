//////////////////////////////////////////////////////////////////////////////////////////////
//																					//
//	Creado por:																			//
//	Gustavo Andrés Uribe Gomez															//
//	Luis Miguel Collazos V.																//
//	Cesar G. Chavez A.																	//
//	Flavio Andres Martinez Erazo														//
//	Andrés Felipe Escallon Portilla				
								
	// //	Jose Wilmer Castillo Obando			
								
		// //	Fabian Leonidas Mosquera Palta		
								
		// //	Jorge David Moncayo Bravo		
								
			// //					
								
						//
//------------------------------------------------------------------------------------------// //																					// //	interfaz.h:																			// //	Cabecera de manejador de interfaz													// //																					// //////////////////////////////////////////////////////////////////////////////////////////////


#ifndef CABECERA_INTERFAZ
#define CABECERA_INTERFAZ

#include <iostream.h>
#include <stdlib.h>
#include <string.h>
#include <fstream.h>
#include "conexion.h"

#define OPERACIONES enum OPERACIONES
#define UBICACIONES enum UBICACIONES

OPERACIONES{TERMINAR,ENVIARMENSAJE,TRANSFERIR,OPTIONS,BUSCAR};
UBICACIONES{BUSQUEDA,OPCIONES,RESULTADOS,CHAT,NODOS};

class Interfaz
{
	char busqueda[1024],estado[512];
	int lineaDeResultados, lineaDeNodos;
	UBICACIONES ubicacion;
public:
	Interfaz();
	~Interfaz();
	void cambiarEstado(char*);
	void cambiarBusqueda(char*);
	void dibujarInterfaz();
	char *verBusqueda();
	char *verEstado();
	OPERACIONES manejarInterfaz();
};


#endif

