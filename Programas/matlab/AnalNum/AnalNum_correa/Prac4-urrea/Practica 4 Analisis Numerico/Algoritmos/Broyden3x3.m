%Sistemas de ecuaciones no lineales 3x3
%metodo de broyden
%Luis Mario Urrea Murillo
%Codigo 4072011 Universidad Cooperativa de Coolombia
function y = Broyden3x3
syms x1 x2 x3
x0=input('Digite un vector fila con los valores iniciales de x0= ');    %ecuación x0
tol=input('Tolerancia del sistema= ');  
f(1)= input('Digite la ecuacion 1 =');      %ecuación 1
f(2)= input('Digite la ecuacion 2 =');      %ecuación 2
f(3)= input('Digite la ecuacion 3 =');      %ecuación 3
F=[f(1);f(2);f(3)];
%Ejemplo
%x0=[0;5];
%TOL=0.5;
%F=[x1*x2-5*x1^2+2;x2^2+2*x1^2*x2-8];
iter=0;
ea=[100;100;100];
xx1=x0(1); xx2=x0(2); xx3=x0(3);
fprintf('iter   x1        x2        x3          ea(1)       ea(2)       ea(3) \n');
fprintf('%d      %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\n',iter,xx1,xx2,xx3,ea(1),ea(2),ea(3));
vars = '[';
for i = 1:length(F)
    iS = num2str(i);
    vars = [vars 'x' iS ' '];
    eval(['x' iS ' = sym(''x' iS ''');']);
end
vars = [vars ']' ];
eval(['vars= ' vars ';']);
J=jacobian(F,vars);
Fold = double(subs(F, vars, x0.'));
Jold=double(subs(J,vars, x0.'));
A0 = inv(Jold);
dx = -A0 * Fold;
x0  = x0 + dx;
    iter=iter+1;
    x1ante=xx1;
    xx1=x0(1);
    x2ante=xx2;
    xx2=x0(2);
    x3ante=xx3;
    xx3=x0(3);
    ea(1)=abs((xx1-x1ante)*100/xx1);
    ea(2)=abs((xx2-x2ante)*100/xx2);
    ea(3)=abs((xx3-x3ante)*100/xx3);
    fprintf('%d      %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\n',iter,xx1,xx2,xx3,ea(1),ea(2),ea(3));
while ((ea(1)>tol)||(ea(2)>tol)||(ea(3)>tol))
    iter=iter+1;
    Fnew = double(subs(F,vars, x0.'));
        dy = Fnew - Fold;
        u = A0 * dy;
        v = dx' * A0;
        denom = dx' * u;
        A0 = A0 + (dx-u) * v / denom;
        dx = -A0 * Fnew;
        x0 = x0 + dx;        
    x1ante=xx1;
    xx1=x0(1);
    x2ante=xx2;
    xx2=x0(2);
    x3ante=xx3;
    xx3=x0(3);
    ea(1)=abs((xx1-x1ante)*100/xx1);
    ea(2)=abs((xx2-x2ante)*100/xx2);
    ea(3)=abs((xx3-x3ante)*100/xx3);
    fprintf('%d      %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\n',iter,xx1,xx2,xx3,ea(1),ea(2),ea(3));
end