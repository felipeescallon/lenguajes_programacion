%Sistemas de Ecuaciones no lineales 2x2
%Método de Punto Fijo Multivariado 
%Luis Mario Urrea Murillo
%Codigo 4072011 Universidad Cooperativa de Coolombia
function [iter,x,ea] = puntofijomultivariado2x2
syms x1 x2 %sistema para dos ecuaciones
x0=input('Digite f(x0)=');%valores iniciales de x1, x2 en un vector fila
tol=input('Digite la Tolerancia del Sistema=');  
f(1)=input('digite f(1)=');    %ecuación 1
f(2)=input('digite f(2)=');    %ecuación 2
ecu=[f(1);f(2)];
iter=0;
ea=[100 100];
x1=x0(1); x2=x0(2);
disp(x0);
disp(tol);
fprintf('iter   x1        x2          ea(1)       ea(2) \n');
fprintf('%d      %f\t %f\t %f\t %f\n',iter,x1,x2,ea(1),ea(2));
while ((ea(1)>tol)||(ea(2)>tol))
    iter=iter+1;
    x(1)=eval(ecu(1));
    x(2)=eval(ecu(2));
    x1ante=x1;
    x1=x(1);
    x2ante=x2;
    x2=x(2);    
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);
    fprintf('%d      %f\t %f\t %f\t %f\n',iter,x1,x2,ea(1),ea(2));
end