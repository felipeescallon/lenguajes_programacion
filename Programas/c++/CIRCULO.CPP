//Archivo circulo.cpp
//Definici¢n de la clase circulo

#include "figuras.h"

circulo_figura::circulo_figura(double xc, double yc, double r)
{
x=xc;
y=yc;
radio=r;
}

double circulo_figura::calcular_area(void) const
{
return(3.141592*radio*radio);
}

void circulo_figura::dibujar(void) const
{
cout<<"Dibujar circulo de radio    " <<radio<<"con centro en    "<<x<<" , " <<y<<endl;
}