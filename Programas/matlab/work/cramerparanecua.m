%Regla de crammer para cualquier tipo de matriz
clear all
clc
N=input('Digita el tama�o de la matriz: ');
for I=1:N
 for J=1:N
  
  A(I,J)=input('Digite el valor de la matriz');
 end
 B(I)=input('Ingrese el tama�o vector de constantes');
end
B=B';
a=det(A);
for I=1:N
    c=A;
    c(:,I)=B
    X(I)=det(c)/a
end
    
