%x=[-20:1:20];
%y=2.*x.^3-5*x+3
 
% plot(x,y)
% grid on
% hold on
disp ('NEWTON-RAPHSON')
xo=input('Valor inicial =');
n=input ('numero de iteraciones=');
syms x
f=input ('digite la funcion = ');
df=diff(f);
salida=ones(n,3);   % matiz de salida de datos 

for i=1:n
    x=xo;
    x1=xo-eval(f)/eval (df);
    vsal=[xo;x1];
    %er=[[abs((xo-x1)/xo)]]*100;  % error relativo porcentual 
    ea=[[abs((x1-xo)/x1)]]*100;  % error 
    xo=x1;
 
    salida(i,1)=i;
    salida(i,2)=x1;
    salida(i,3)=ea;
    %salida(i,4)=ea;
end
disp('ite         raiz                                ea');
disp(num2str(salida));
