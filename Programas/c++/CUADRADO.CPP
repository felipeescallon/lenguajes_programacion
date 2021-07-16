//Archivo cuadrado.cpp
//Definici¢n de la clase cuadrado

#include "figuras.h"

cuadrado_figura::cuadrado_figura(double xc, double yc, double l)
{
x=xc;
y=yc;
lado=l;
}

double cuadrado_figura::calcular_area(void) const
{
return(lado*lado);
}

void ccuadrado_figura::dibujar(void) const
{
cout<<"Dibujar cuadrado de lado "<<lado<<"desde   "<<x<<" , " <<y<<endl;
}