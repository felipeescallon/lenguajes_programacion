%Metodo de Gauss Seidel
function [iter,x,ea] = gaussseidel(x0,tol)
%Función para solucionar un sistemas de tres ecuaciones lineales 
%ecugauss es una subfunción que contiene las ecuaciones 
%x0 son los valores iniciales en fila
%tol es la tolerancia en porcentaje

disp(x0);
disp(tol);

syms x1 x2 x3 
ecu=ecugauss;           %subfunción de las ecuaciones a resolver
y(1)=solve(ecu(1),x1);
y(2)=solve(ecu(2),x2);
y(3)=solve(ecu(3),x3);

disp(y); 

iter=0;
disp(iter);

ea=[100 100 100];
x1=x0(1); x2=x0(2); x3=x0(3);

disp(ea);
disp(x1);
disp(x2);
disp(x3);

while ((ea(1)>tol)|(ea(2)>tol)|(ea(3)>tol))
    
    x(1)=eval(y(1));
    x1ante=x1;
    x1=x(1);
    x(2)=eval(y(2));
    x2ante=x2;
    x2=x(2);
    x(3)=eval(y(3));
    x3ante=x3;
    x3=x(3);
    
    disp(x1);
    disp(x2);
    disp(x3);

   
    iter=iter+1;
    disp(iter);
    
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);
    ea(3)=abs((x3-x3ante)*100/x3);

    disp(ea(1));
    disp(ea(2));
    disp(ea(3));
    disp(ea);
end

%Subfunción 
function [ecu] = ecugauss
%función que entrega el sistema de tres ecuaciones lineales 
%para el método de Gauss Seidel y Jacobi 
 
syms x1 x2 x3 

f(1)=17*x1-2*x2-3*x3-500;    %ecuación 1
f(2)=-5*x1+21*x2-2*x3-200;     %ecuación 2
f(3)=-5*x1-5*x2+22*x3-30;    %ecuación 3

ecu=[f(1);f(2);f(3)];
