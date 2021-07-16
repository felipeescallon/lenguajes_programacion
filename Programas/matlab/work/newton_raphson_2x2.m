%ANALISIS NUMERICO_UCC_ING.ESP. ANDRES FELIPE ESCALLON PORTILLA_MAYO 2010
%Metodo de Newton Rapshon: Sist. Ecuac. No Lineales

function [iter,x,ea] = newton_raphson_2x2
%Función para solucionar un sistema de dos ecuaciones no lineales
%ecuaciones es una subfunción que contiene las ecuaciones 
%x0 es un vector con los valores iniciales en columna,
%tol la tolerancia en porcentaje (%)
 
syms x1 x2 
x0=input('digite los valores iniciales de x1 (x1_0), x2 (x2_0) en un vector columna. x0=')
tol=input('digite el porcentaje de error aceptado. Es=Tol=')  
%ecu=ecuaciones;%subfunción de las ecuaciones a resolver
    
iter=0;
disp(iter);

ea=[100 100];
disp(ea);

x1=x0(1); 
x2=x0(2); 

disp(x1);
disp(x2);

    %f1(x1,x2);
      fun1=@f1;
      x(1)=feval(fun1,x1,x2); % calls the function_handle f with the argument x.
      disp(x(1));
%f2(x1,x2);
      fun2=@f2;
      x(2)=feval(fun2,x1,x2); % calls the function_handle f with the argument x.
      disp(x(2));


while ((ea(1)>tol)|(ea(2)>tol))
   
      iter=iter+1;
      
      x1ante=x1;
      x1=x(1);
      x2ante=x2;
      x2=x(2);
           
      disp(x1);
      disp(x2);
    
%    f1(x1(iter),x2(iter));
      fun1=@f1;
      y1=feval(fun1,x1,x2); % calls the function_handle f with the argument x.
      disp(y1);
%f2(x1(iter),x2(iter));
      fun2=@f2;
      y2=feval(fun2,x1,x2); % calls the function_handle f with the argument x.
      disp(y2);

      y=[y1;y2];
      disp(y);
      
 %jinversa1(x1(iter),x2(iter))
      fjinv=@fjacinv;
      yinv=feval(fjinv,x1,x2); % calls the function_handle f with the argument x.
      disp(yinv);

    x=x-yinv*y;
    
    
    ea(1)=abs((x1-x1ante)*100/x1);
    ea(2)=abs((x2-x2ante)*100/x2);

    
    disp(ea);
end


%Subfunción 
%function [ecu] = ecuaciones
%función que entrega el sistema de dos ecuaciones no lineales con dos
%incognitas x=x1, y=x2
%para el método de newton_raphson 
 
%syms x1 x2 

%f(1)= input('digite la ecuacion 1 igualada a cero, f1(x1,x2): ')    %ecuación 1
%f(2)= input('digite la ecuacion 2 igualada a cero, f2(x1,x2):')    %ecuación 2
%f1=f(1);
%f2=f(2);

%ecu=[f1;f2];
%disp(ecu);

%fun1=@f1;
%y1=feval(fun1,x1,x2); % calls the function_handle f with the argument x.
%disp(y1);


%un2=@f2;

%y2=feval(fun2,x1,x2); % calls the function_handle f with the argument x.
%disp(y2);

%jinversa(1)=feval(jinv(x1,x2));
%disp(jinversa);

