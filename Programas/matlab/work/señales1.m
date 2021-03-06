clc
A1=2;                  
A2=4;
fc=10;
fm=1;
Ec=8;
Em=0.715;
t=0:0.01:4;
pi2=6.283184;
x=A1*sin(pi2*fc*t)+A1*cos(pi2*fc*t);
P=Ec*cos(pi2*fc*t);
E1=-(Em/2)*cos(pi2*(fc+fm)*t);
E2=(Em/2)*cos(pi2*(fc-fm)*t);
xam=Ec*cos(pi2*fc*t)-(Em/2)*cos(pi2*(fc+fm)*t)+(Em/2)*cos(pi2*(fc-fm)*t);
figure
subplot(5,1,1);plot(t,x);grid;
subplot(5,1,2);plot(t,E1,'b',t,E2,'b',t,P,'r');grid;
subplot(5,1,3);plot(t,xam,'r');grid;
H=fft(x,128);
subplot(5,1,4);plot(2*pi*(0:127)/128,abs(H));
subplot(5,1,5);plot(2*pi*(0:127)/128,angle(H));
