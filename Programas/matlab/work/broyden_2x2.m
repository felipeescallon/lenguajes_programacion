%Metodo de Broyden: Sist. Ecuac. No Lineales

function [iter,x,ea] = broyden_2x2
%Función para solucionar un sistema de dos ecuaciones no lineales
%"ecuaciones" es una subfunción que contiene las ecuaciones 
%x0 es un vector con los valores iniciales en fila,
%tol la tolerancia en porcentaje (%)
 
%syms x1 x2
x0=input('digite los valores iniciales de x1 (x1_0), x2 (x2_0) en un vector columna. x0=')%ecuación x0
%disp(x0);
tol=input('digite el porcentaje de error aceptado. Es=Tol=')  
%disp(tol);
%ecu=ecuaciones;%subfunción de las ecuaciones a resolver

x1=x0(1);
x2=x0(2);
disp(x1);
disp(x2);
%y=eval(ecu(1));
%disp(y);

%f1=x1*x2-5*x1*x1+2;
%disp(f1);
%y=eval(f1);
     %Example:
 
     %fevaluada=ecu(1);   
     %f = @fevaluada; % creates a function_handle f to function 'humps'. 
         %x = fminbnd(f,1,2); % passes the function_handle f to FMINBND.
 
      % Which 'humps' function is called by 'fminbnd' is determined by the 
       %context when f is created, as opposed to when f is feval'd.
 
       fun1=@f1;
         y1=feval(fun1,x1,x2); % calls the function_handle f with the argument x.
 disp(y1);


 fun2=@f2;
         y2=feval(fun2,x1,x2); % calls the function_handle f with the argument x.
 disp(y2);

 
%FEVAL(F,x1,...,xn)
%y1 =feval((ecu(1)),x1,x2);
%y2 =feval((ecu(2)),x1,x2);
%disp(y);
%disp(y2);

%y(2)=eval(ecu(2));
%disp(y(2)); 

%iter=0;
%disp(iter);

%ea=[100 100];
%disp(ea);

%x01=x0(1); 
%disp(x01);
%x02=x0(2); 
%disp(x02);

%A0=jacobian(ecu, [x1, x2]);
%disp(A0);
%A0inv=inv(A0);
%disp(A0inv);

%xi1=x0-(A0inv)*ecu;
%disp(xi1);


%while ((ea(1)>tol)|(ea(2)>tol))
%    iter=iter+1;
%    x(1)=eval(ecu(1));
%    x(2)=eval(ecu(2));

%    x1ante=x1;
%    x1=x(1);
%    disp(x1);
%    x2ante=x2;
%    x2=x(2);
%    disp(x2);
    

    
%   ea(1)=abs((x1-x1ante)*100/x1);
%   ea(2)=abs((x2-x2ante)*100/x2);
    
%    disp(ea);
%end


%Subfunción 
%function [ecu] = ecuaciones
%función que entrega el sistema de dos ecuaciones no lineales con dos
%incognitas x=x1, y=x2
%para el método de broyden
 
%syms x1 x2 

%f(1)= input('digite la ecuacion 1 igualada a cero. f1(x1,x2)=')    %ecuación 1
%f(2)= input('digite la ecuacion 2 igualada a cero. f2(x1,x2)=')    %ecuación 2
%ecu=[f(1);f(2)];
%disp(ecu);