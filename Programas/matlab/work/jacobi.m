function [iter,x,ea] = jacobi(x0,tol)
%Función para solucionar un sistema de tres ecuaciones lineales
%ecugauss es una subfunción que contiene las ecuaciones 
%x0 son los valores iniciales en un vector de filas(x0=[0 0 0])
%tol la tolerancia en porcentaje(tol=5)

disp(x0);
disp(tol);

syms x1 x2 x3


ecu=ecujacobi;         %subfunción de las ecuaciones a resolver
y(1)=solve(ecu(1),x1);
y(2)=solve(ecu(2),x2);
y(3)=solve(ecu(3),x3);

disp(y); 

iter=0;
disp(iter);



ea=[100 100 100];%inicializo vector de errores de x1, x2 y x3
x1=x0(1); x2=x0(2); x3=x0(3);
%x=[x1 x2 x3];
disp(x1);
disp(x2);
disp(x3);
%disp(x0);

while ((ea(1)>tol)|(ea(2)>tol)|(ea(3)>tol))%and=|
    
    iter=iter+1;
    disp(iter);
    
    x(1)=eval(y(1));
    x(2)=eval(y(2));
    x(3)=eval(y(3));
    x1ante=x1;
    x1=x(1);
    x2ante=x2;
    x2=x(2);
    x3ante=x3;
    x3=x(3);
    
    disp(x1);
    disp(x2);
    disp(x3);
    %disp(x0);
    
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);
    ea(3)=abs((x3-x3ante)*100/x3);
    
    disp(ea);
end


%Subfunción 
function [ecu] = ecujacobi
%función que entrega el sistema de tres ecuaciones lineales 
%para el método de Jacobi 
 
syms x1 x2 x3 
f(1)=4*x1-x2-x3+2;    %ecuación 1
f(2)=6*x1+8*x2+0*x3-45;    %ecuación 2
f(3)=-5*x1+0*x2+12*x3-80;     %ecuación 3
ecu=[f(1);f(2);f(3)];
