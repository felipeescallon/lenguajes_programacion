%Regla de Crammer
function [x] = crammer(A,B)
%Función para solucionar un sistema de n-ecuaciones lineales  
% A es la matriz de coeficientes, B es el vector de constantes
    	
clc
clear all

A=input('digite la matriz de coeficientes A entre corchetes y filas separadas por ";"A=')
B=input('digite el vector de constantes B entre corchetes y filas separadas por ";"B=')

n = length(B);

for i = 1:n,
          
          C = A;
          C(:,i) = B;
          x(i)=det(C)/det(A);
end
