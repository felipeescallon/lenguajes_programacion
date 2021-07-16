t=0:0.001:30;
x=exp(-0.1*t).*sin((2\3)*t);
axis([0 30 -1 1])
plot(t,x)
grid
ylabel('x(t)')
xlabel('t(seg)')