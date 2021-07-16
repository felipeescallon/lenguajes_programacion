%Sistemas de ecuaciones no lineales 2x2
%metodo de broyden
%Luis Mario Urrea Murillo
%Codigo 4072011 Universidad Cooperativa de Coolombia
function y = Broyden2x2
syms x1 x2 
x0=input('Digite un vector fila con los valores iniciales de x0= ');    %ecuación x0
TOL=input('Tolerancia del sistema= ');  
f(1)= input('Digite la ecuacion 1 =');    %ecuación 1
f(2)= input('Digite la ecuacion 2 =');    %ecuación 2
F=[f(1);f(2)];
Nmax=input('Máximo de iteraciones del sistema= ');
%Ejemplo
%x0=[0;5];
%TOL=0.5;
%F=[x1*x2-5*x1^2+2;x2^2+2*x1^2*x2-8];
%Nmax=15;
vars = '[';
for i = 1:length(F)
    iS = num2str(i);
    vars = [vars 'x' iS ' '];
    eval(['x' iS ' = sym(''x' iS ''');']);
end
vars = [vars ']' ];
eval(['vars= ' vars ';']);
J=jacobian(F,vars);
disp(x0')
Fold = double(subs(F, vars, x0.'));
Jold=double(subs(J,vars, x0.'));
A0 = inv(Jold);
disp(-A0)
dx = -A0 * Fold;
x0  = x0 + dx;
disp(x0')
for i = 2 : Nmax
    Fnew = double(subs(F,vars, x0.'));
        dy = Fnew - Fold;
        u = A0 * dy;
        v = dx' * A0;
        denom = dx' * u;
        A0 = A0 + ( dx - u ) * v / denom;
        dx = -A0 * Fnew;
    x0 = x0 + dx;        
           disp (x0')
        if (max(abs(dx))<TOL )
              y = x0;
           return
        else
           Fold = Fnew;
        end
end
disp('broyden error: Maximum number of iterations exceeded');
    y = x0; 