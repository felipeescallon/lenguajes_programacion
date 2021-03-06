function [] = dominio 
clc
A1=1; %Amplitud
fc=50; %frecuencia portadora
fm=1; %frecuencia de la se?al mensaje
%Nota: fm<<fc 
%fm mucho menor que fc
%y la frecuencia est? dada en KHz
Ec=8; % Voltaje Pico de la Portadora
Em=0.715; %Voltaje Pico de Excursi?n
%Intervalo de tiempo
t=0:0.001:0.4;
signal = square(2*pi*fm*t); %se?al cuadrada
x=A1*sin(2*pi*fc*t)+A1*cos(2*pi*fc*t);
P=Ec*cos(2*pi*fc*t);
E1=-(Em/2)*cos(2*pi*(fc+fm)*t);
E2=(Em/2)*cos(2*pi*(fc-fm)*t);
xam=Ec*cos(2*pi*fc*t)-(Em/2)*cos(2*pi*(fc+fm)*t)+(Em/2)*cos(2*pi*(fc-fm)*t);
%Aplicaci?n de la transformada de fourier
Y=fft(x,512);
H=Y.*conj(Y) / 512;
f = 1000*(0:255)/512;
%Graficas de: la se?al AM, la trayectoria en el espacio de la
%fase y la modulaci?n AM doble banda lateral
figure
subplot(3,1,1);plot(t,xam,'r');grid;
subplot(3,1,2);plot(Y(1:fc));
subplot(3,1,3);plot(f,H(1:256));

hold off;                                                                       % subsequent plot commands go on their own axes
figure;                                                                         % new graphics window
plot(t(1:100),signal(1:100));       % Compare original square wave to its F.S.
xlabel('Time (s)');
ylabel('Amplitude');