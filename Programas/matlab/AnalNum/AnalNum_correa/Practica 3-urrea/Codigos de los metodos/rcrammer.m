function [x] = rcrammer
syms x
%Función para solucionar un sistema de n-ecuaciones lineales  
A=input('Digite la matriz A=');
B=input('Digite la matriz B=');
n = length(B);
for i = 1:n,
    C = A;
    C(:,i) = B;
    x(i)=det(C)/det(A);
    disp(x)
end
