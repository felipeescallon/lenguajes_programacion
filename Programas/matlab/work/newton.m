x0=input('Ingrese el valor inicial: ');
tol=input('Ingrese el porcentaje de error: ');
f=input('Ingrese la función: ');
i=1;
fx(i)=x0;

syms x;
f1=subs(f,x,fx(i));
z=diff(f);
d=subs(z,x,fx(i));

ea(1)=100;

while abs(ea(i))>=tol;
    fx(i+1)=fx(i)-f1/d; f1=subs(f,x,fx(i+1)); d=subs(z,x,fx(i+1));
    ea(i+1)=abs((fx(i+1)-fx(i))/fx(i+1)*100);
    i=i+1;
end
fprintf('i     fx(i)         Error aprox (i) \n');
for j=1:i;
    fprintf('%2d \t %11.7f \t %7.3f \n',j-1,fx(j),ea(j));
end

