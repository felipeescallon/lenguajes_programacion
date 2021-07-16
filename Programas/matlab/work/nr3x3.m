%Sistemas de ecuaciones no lineales 3x3
%metodo de newton rapson 
%Luis Mario Urrea Murillo
%Codigo 4072011 Universidad Cooperativa de Coolombia
function [iter,x,ea] = nr3x3
syms x1 x2 x3
x0=input('Digite un vector fila con los valores iniciales de x0= ');    %ecuación x0
tol=input('Tolerancia del sistema= ');  
f(1)= input('Digite la ecuacion 1 =');    %ecuación 1
f(2)= input('Digite la ecuacion 2 =');    %ecuación 2
f(3)= input('Digite la ecuacion 3 =');    %ecuación 3
ecu=[f(1);f(2);f(3)];
iter=0;
ea=[100;100;100];
xx1=x0(1); xx2=x0(2); xx3=x0(3);
fprintf('iter   x1        x2        x3          ea(1)       ea(2)       ea(3) \n');
fprintf('%d      %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\n',iter,xx1,xx2,xx3,ea(1),ea(2),ea(3));
vars = '[';
for i = 1:length(f)
    iS = num2str(i);
    vars = [vars 'x' iS ' '];
    eval(['x' iS ' = sym(''x' iS ''');']);
end
vars = [vars ']' ];
eval(['vars= ' vars ';']);
J = jacobian(ecu, vars);
x = x0;
while ((ea(1)>tol)||(ea(2)>tol)||(ea(3)>tol))
    iter=iter+1;
    JJ = double(subs(J, vars, x.'));
    FF = double(subs(ecu, vars, x.'));
    x = x - inv(JJ) * FF;
    x1ante=xx1;
    xx1=x(1);
    x2ante=xx2;
    xx2=x(2);
    x3ante=xx3;
    xx3=x(3);
    ea(1)=abs((xx1-x1ante)*100/xx1);
    ea(2)=abs((xx2-x2ante)*100/xx2);
    ea(3)=abs((xx3-x3ante)*100/xx3);
    fprintf('%d      %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\t %3.4f\n',iter,xx1,xx2,xx3,ea(1),ea(2),ea(3));
end