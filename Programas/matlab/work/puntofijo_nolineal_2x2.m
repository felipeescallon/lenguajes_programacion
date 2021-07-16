%ANALISIS NUMERICO_UCC_ING.ESP. ANDRES FELIPE ESCALLON PORTILLA_MAYO 2010
%Metodo de Punto Fijo Multivariado: Sist. Ecuac. No Lineales

function [iter,x,ea] = puntofijo_nolineal_2x2
%Funci�n para solucionar un sistema de tres ecuaciones no lineales
%ecuaciones es una subfunci�n que contiene las ecuaciones 
%x0 es un vectorson los valores iniciales en fila,
%tol la tolerancia en porcentaje (%)
 
syms x1 x2 %x3
x0=input('digite los valores iniciales de x1 (x1_0), x2 (x2_0) en un vector fila. x0=')    %ecuaci�n x0
tol=input('digite el porcentaje de error aceptado. Es=Tol=')  
ecu=ecuaciones;           %subfunci�n de las ecuaciones a resolver

%y(1)=solve(ecu(1),x1);
%y(2)=solve(ecu(2),x2);
%y(3)=solve(ecu(3),x3);
 
iter=0;

ea=[100 100];
x1=x0(1); x2=x0(2); %x3=x0(3);
 
disp(x0);
disp(tol);
%disp(y); 
disp(iter);
disp(ea);
disp(x1);
disp(x2);
%disp(x3);

while ((ea(1)>tol)|(ea(2)>tol))%|(ea(3)>tol))
    iter=iter+1;
    x(1)=eval(ecu(1));
    x(2)=eval(ecu(2));
    %x(3)=eval(y(3));
    x1ante=x1;
    x1=x(1);
    x2ante=x2;
    x2=x(2);
    %x3ante=x3;
    %x3=x(3);
    
    disp(x1);
    disp(x2);
    %disp(x3);

    
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);
    %ea(3)=abs((x3-x3ante)*100/x3);
    
    disp(ea(1));
    disp(ea(2));
    %disp(ea(3));
    disp(ea);
end


%Subfunci�n 
function [ecu] = ecuaciones
%funci�n que entrega el sistema de dos ecuaciones no lineales con dos
%incognitas x=x1, y=x2
%para el m�todo de punto fijo multivariado 
 
syms x1 x2 %x3

f(1)= input('digite la ecuacion 1 con la variable x1 despejada: x1=f1(x1,x2). x1=')    %ecuaci�n 1
f(2)= input('digite la ecuacion 2 con la variable x2 despejada: x2=f2(x1,x2). x2=')    %ecuaci�n 2
%f(3)= input('digite la ecuacion 3 con la variable x2 despejada: x3=f3(x1,x2,x3). x3=')    %ecuaci�n 3

ecu=[f(1);f(2)];
disp(ecu);