%Sistemas de Ecuaciones no lineales 3x3
%Método de Punto Fijo Multivariado 
%Luis Mario Urrea Murillo
%Codigo 4072011 Universidad Cooperativa de Coolombia
function [iter,x,ea] = puntofijomultivariado3x3
syms x1 x2 x3 %sistema para tres ecuaciones
x0=input('Digite f(x0)=');%valores iniciales de x1, x2, x3 en un vector fila
tol=input('Digite la Tolerancia del Sistema=');
f(1)= input('digite f(1)=');    %ecuación 1
f(2)= input('digite f(2)=');    %ecuación 2
f(3)= input('digite f(3)=');    %ecuación 3
ecu=[f(1);f(2);f(3)];
iter=0;
ea=[100 100 100];
x1=x0(1); x2=x0(2); x3=x0(3);
disp(x0);
disp(tol);
fprintf('iter   x1        x2        x3          ea(1)       ea(2)       ea(3) \n');
fprintf('%d      %f\t %f\t %f\t %f\t %f\t %f\n',iter,x1,x2,x3,ea(1),ea(2),ea(3));
while ((ea(1)>tol)||(ea(2)>tol)||(ea(3)>tol))
    iter=iter+1;
    x(1)=eval(ecu(1));
    x(2)=eval(ecu(2));
    x(3)=eval(ecu(3));
    x1ante=x1;
    x1=x(1);
    x2ante=x2;
    x2=x(2);
    x3ante=x3;
    x3=x(3);
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);
    ea(3)=abs((x3-x3ante)*100/x3);
    fprintf('%d      %f\t %f\t %f\t %f\t %f\t %f\n',iter,x1,x2,x3,ea(1),ea(2),ea(3));
end