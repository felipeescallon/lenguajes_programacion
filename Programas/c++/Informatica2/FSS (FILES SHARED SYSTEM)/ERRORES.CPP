//////////////////////////////////////////////////////////////////////////////////////////////
//																					//
//	Creado por:																			//
//	Gustavo Andrés Uribe Gomez															//
//	Luis Miguel Collazos V.																//
//	Cesar G. Chaves A.																	//
//	Flavio Andres Martinez Erazo														//
//	Andrés Felipe Escallon P.															//
//	Jose Wilmer Castillo Obando															//
//	Fabian Leonidas Mosquera Palta														//
//	Jorege David Moncayo Bravo															//
//																					//
//------------------------------------------------------------------------------------------//
//																					//
//	errores.cpp:																		//
//	Manejo de errores que se pueden dar en la aplicacion(exepciones)								//
//																					//
//////////////////////////////////////////////////////////////////////////////////////////////

#include "errores.h"

void tratarExcepcion(ERRORES errores) 
{
	ofstream error("error.log");//.log: para pruebas
	switch(errores)
		{
			case NOCONECT:
						{
							error.write("No se pudo establecer la conexion\n",35);
							break;
						}
			case NOCLOSEESCUCHA:
						{
							error.write("ERROR al cerrar el socket de escucha\n",35);
							break;
						}
			case NOCLOSEIO:
						{
							error.write("ERROR al cerrar el socket de entrada y salida\n",35);
							break;
						}
			case SOCKETVACIO:
						{
							error.write("El socket esta vacio\n",35);
							break;
						}
			case NOHIJO:
						{
							error.write("ERROR: no sepudo crear el proceso hijo\n",35); 				
	                                                break;
	                                        } 		
		}
	error.close();	
}

