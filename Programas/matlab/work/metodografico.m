%metodo grafico
syms x
y1= input('Digite la funcion 1')
y2= input('Digite la funcion 2')
x=[-20:0.5:30];
y1= eval (y1);
y2= eval (y2);
plot(x,y1)
hold on
plot(x,y2,'r')
grid on