function [ecu] = leerecu
%funci�n que entrega el sistema de tres ecuaciones lineales 
%para el m�todo de Gauss Seidel y Jacobi 
 
syms x1 x2 x3 
f(1)=input('digite f(1)=');    %ecuaci�n 1
f(2)=input('digite f(2)=');    %ecuaci�n 2
f(3)=input('digite f(3)=');    %ecuaci�n 3
ecu=[f(1);f(2);f(3)];
