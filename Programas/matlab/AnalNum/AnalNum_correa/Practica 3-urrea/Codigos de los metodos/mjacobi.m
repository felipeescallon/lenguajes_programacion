function [iter,x,ea] = mjacobi
%Función para solucionar un sistema de tres ecuaciones lineales
syms x1 x2 x3
x0=input('Digite f(x0)=');
tol=input('Digite el porcentaje de tolerancia=');
ecu=leerecu;         %subfunción de las ecuaciones a resolver
y(1)=solve(ecu(1),x1);
y(2)=solve(ecu(2),x2);
y(3)=solve(ecu(3),x3);
iter=0;
ea=[100 100 100];
x1=x0(1); x2=x0(2); x3=x0(3);
while ((ea(1)>tol)||(ea(2)>tol)||(ea(3)>tol))
    iter=iter+1;
    x(1)=eval(y(1));
    x(2)=eval(y(2));
    x(3)=eval(y(3));
    x1ante=x1;
    x1=x(1);
    x2ante=x2;
    x2=x(2);
    x3ante=x3;
    x3=x(3);
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);
    ea(3)=abs((x3-x3ante)*100/x3);
    disp(iter)
    disp(x1)
    disp(x2)
    disp(x3)
    disp(ea)
end

