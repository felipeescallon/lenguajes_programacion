%Sistemas de ecuaciones no lineales
%metodo de newton rapson 2x2
function [N,x,ea] = nr2x2
syms x1 x2
x0=input('Digite un vector fila con los valores iniciales de x0= ')    %ecuación x0
tol=input('Tolerancia del sistema= ') 
N=input('Numero maximo de iteraciones= ')
F(1)= input('Digite la ecuacion 1 =')    %ecuación 1
F(2)= input('Digite la ecuacion 2 =')    %ecuación 2
ecu=[F(1);F(2)];
vars = '[';
for i = 1:length(F)
    iS = num2str(i);
    vars = [vars 'x' iS ' '];
    eval(['x' iS ' = sym(''x' iS ''');']);
end
vars = [vars ']' ];
eval(['vars= ' vars ';']);
J = jacobian(ecu, vars);
x = x0;
N=N+1;
while (i<N || ea<tol),
    JJ = double(subs(J, vars, x.'));
    FF = double(subs(ecu, vars, x.'));
    x = x - inv(JJ) * FF
    %x(1)=eval(ecu(1));
    %x(2)=eval(ecu(2));
    %x1ante=x1;
    %x1=x(1);
    %x2ante=x2;
    %x2=x(2);
    %ea(1)=abs((x1-x1ante)*100/x1);
    %ea(2)=abs((x2-x2ante)*100/x2);
    i=i+1;
end