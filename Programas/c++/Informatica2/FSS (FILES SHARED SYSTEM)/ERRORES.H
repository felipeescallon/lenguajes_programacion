//////////////////////////////////////////////////////////////////////////////////////////////
//																					//
//	Creado por:																			//
//	Gustavo Andr�s Uribe Gomez															//
//	Luis Miguel Collazos V.																//
//	Cesar G. Chavez A.																	//
//	Flavio Andres Martinez Erazo														//
//	Andr�s Felipe Escallon P.															//
//	Jose Wilmer Castillo Obando															//
//	Fabian Leonidas Mosquera Palta														//
//	Jorege David Moncayo Bravo															//
//																					//
//------------------------------------------------------------------------------------------//
//																					//
//	errores.h:																			//
//	Contiene un tipo enumerado que contiene todos los errores posibles de la			//
//	aplicacion,y define la respuesta para cada uno de ellos								//
//																					//
//////////////////////////////////////////////////////////////////////////////////////////////


#ifndef CABECERA_ERORES
#define CABECERA_ERORES

#define ERRORES enum ERRORES //var enumerada

ERRORES{NOCONECT,NOCLOSEESCUCHA,NOCLOSEIO,SOCKETVACIO,NOHIJO};

#include <iostream.h>
#include <fstream.h>

void tratarExcepcion(ERRORES);

#endif

