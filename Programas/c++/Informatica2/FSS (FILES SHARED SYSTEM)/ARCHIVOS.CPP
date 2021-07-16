//////////////////////////////////////////////////////////////////////////////////////////
//											//
//	Creado por:									//
//	Gustavo Andrés Uribe Gomez							//
//	Luis Miguel Collazos V.								//
//	Cesar G. Chaves A.								//
//	Flavio Andres Martinez Erazo							//
//	Andrés Felipe Escallón Portilla							//
//	Jose Wilmer Castillo Obando							//
//	Fabian Leonidas Mosquera Palta							//
//	Jorege David Moncayo Bravo							//
//											//
//--------------------------------------------------------------------------------------//
//											//
//	archivos.cpp:									//
//	Implementacion de las funciones propias para el manejo de archivos.		//
//											//
//////////////////////////////////////////////////////////////////////////////////////////

#include "archivos.h"
#include <string.h>

//--------------------------------------------------------------------------------------//
// Funcion buscar:									//
// Parametros:										//
// archivo -> Nombre del archivo donde se  realizara la busqueda			//
// objeto  -> Objeto a buscar 								//
// Retornos:										//
// 0 -> Busqueda fallida								//
// caracteresLeidos -> Numeros de caracteres leidos					//
//--------------------------------------------------------------------------------------//				
									
int buscar(char* archivo,char* objeto)
{
	char bufer[100];
	int caracteresLeidos=0;
	char c;
	ifstream fichero(archivo);
	
	do
	{
		fichero.get(bufer,100);
		caracteresLeidos+=strlen(bufer);
		if(!strcmp(bufer,objeto))
	 	{
			fichero.close();
			return(caracteresLeidos);
		}
		fichero.get(c);
		caracteresLeidos++;
	}while(!fichero.eof());
	
	fichero.close();
	return(0);
}


