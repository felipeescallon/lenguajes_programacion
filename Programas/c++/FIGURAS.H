//Archivo figuras.h

#if !defined(FIGURAS_H)
#define FIGURAS_H

#include<stdio.h>
#include<math.h>

//Clase Abstracta

class figura
{
public:
  virtual double calcular_area(void) const {}
  virtual void dibujar(void) const {}
};

//Definici¢n Clase circulo


class circulo_figura : public figura
{
private:
  double x,y;
  double radio;

public:
  circulo_figura(double x, double y, double radio);
  double calcular_area(void) const;
  void dibujar(void) const;

};

//Definici¢n Clase cuadrado

class cuadrado_figura : public figura
{
private:
  double x,y;
  double lado;

public:
  cuadrado_figura(double x, double y, double lado);
  double calcular_area(void) const;
  void dibujar(void) const;

};

//Definici¢n Clase triangulo

class triangulo_figura : public figura
{
private:
  double x,y;

public:
  triangulo_figura(double x, double y);
  double calcular_area(void) const;
  void dibujar(void) const;

};

#endif            //#if !defined(FIGURAS_H)