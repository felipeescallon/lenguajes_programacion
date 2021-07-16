%Función para solucionar un sistema de dos ecuaciones lineales, %con las variables desconocidas x1,x2
clc
clear all

syms x1 x2
f=input('digite la primera funcion f1(x1,x2)=')%a*x1+b*x2+k
g=input('digite la segunda funcion f2(x1,x2)=')%a*x1+b*x2+k
ecu1=solve(f,x2)
ecu2=solve(g,x2)
x1=[0:1:12];       %Rango de x1 para realizar la grafica
ecu1=eval(ecu1)
ecu2=eval(ecu2)
plot(x1,ecu1)
hold on
plot(x1,ecu2,'r')
grid
