%FELIPE ESCALLON PORTILLA
%11/AGO/2004
%MATLAB:TTL'S
%CICLOS O ANILLOS

for x=1:5
%x esta en radianes ojo---->>>>para grados hago x*180/pi
    y=sin(x);
    z=cos(x);
    %disp==desplegar
    disp([x;y;z]')%':transpuesta    
end    

%ojo:el ; es para que no muestre los datos u operaciones


%GRAFICA del sin y cos

t=0:pi/20:2*pi;

y=sin(t);
z=cos(t);
    
plot(t,y,'pk',t,z,'-g') %respetar el orden:var_independ,var_depend,'simboloCOLOR'
grid                    %cuadricula para la grafica
legend('seno','coseno') %labels for the graphics


text(2.5,.7,'sen(t)')   %texto ubicado con coordenadas (x,y)
text(2.5,-.7,'cos(t)')

%gtext('cos(t)')         %ubicar con el raton:una sola vez!



%CICLO WHILE

disp('******')
disp('******')

pause                   %espera por una tecla para continuar
%pause(2)               %espera 2 segundos

x=0
while x<5
    x=x+2.5;
    y=sin(x);
    z=cos(x);
    disp([x;y;z]')      %no ee necesario usar ; al final
end    