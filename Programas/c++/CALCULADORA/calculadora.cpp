#include<iostream>
#include "calculadora.h"
#include "funciones.h"

void calculadora::datos()
{
    cout<<"Digite primer numero"<<endl;
    cin>>a;
    cout<<"Digite segundo numero"<<endl;
    cin>>b;
}

int calculadora::menu()
{
    int r;
    cout<<"\t\t C A L C U L A D O R A \n\n";
	cout<<"\t 1.- Suma\n";
	cout<<"\t 2.- Resta\n";
	cout<<"\t 3.- Multplicaci¢n\n";
	cout<<"\t 4.- Division\n";
	cout<<"\t 5.- Raiz cuadrada\n";
	cout<<"\t\t Elija su Opci¢n:";
	cin>>r;
	return r;
}
