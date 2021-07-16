function [ecu] = leerecu
%función que entrega el sistema de tres ecuaciones lineales 
%para el método de Gauss Seidel y Jacobi 
 
syms x1 x2 x3 
f(1)=input('digite f(1)=');    %ecuación 1
f(2)=input('digite f(2)=');    %ecuación 2
f(3)=input('digite f(3)=');    %ecuación 3
ecu=[f(1);f(2);f(3)];
