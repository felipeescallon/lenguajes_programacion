%FELIPE ESCALLON PORTILLA
%11/AGO/2004
%MATLAB:TTL'S
%EJERCICIO CON ARREGLOS:ver cuaderno

t=1:10; %default: paso=1--->t=1:1:10

x=t.*sin(t);
y=(t-1)./(t+1);
z=sin(t.^2)./t.^2;
disp('    x       y        z')
disp([x;y;z]')


%el equivalente sin vectores
pause(2)
%t=0;

for t=1:10
      %t=t+1;
      x=t*sin(t); 
      y=(t-1)/(t+1);
      z=sin(t^2)/t^2;
      disp([x;y;z]')
end