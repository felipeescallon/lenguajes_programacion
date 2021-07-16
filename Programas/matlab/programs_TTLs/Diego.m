% ciclos o anillos
for x=1:0.5:5;         %x varia de 1 a 5 en pasos de 1
    y=sin(x);
    disp([x,y])    % despliega valores de X y Y
end
pause(10)
disp('*******************************************')   % renglon vacio

x=0;
while x<5
    x=x+1;
    y=sin(x);
    disp([x,y])
end