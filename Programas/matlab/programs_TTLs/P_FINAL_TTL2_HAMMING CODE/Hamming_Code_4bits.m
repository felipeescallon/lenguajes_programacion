%BUEN PROGRAMA PERO ES ESTATICO(4 BITS)


%Efren Cortez y Bairon Alvira
%DETECCION DE ERRORES POR CODIGO HAMMING
clear;
clc;
disp(' ');
disp(' ');
disp('         TEORIA DE TELECOMUNICACIONES II')
disp(' ');
disp(' ');
disp('               CODIGO HAMMING');
disp(' ');
disp('A continuacion se presenta un sencillo ejemplo de este codigo');
disp('Se le pedira ingresar un dato que representa el mensaje.  Este dato');
disp('se codifica de acuerdo con el codigo hamming y se "envia"');
disp('Luego se le pide ingresar un dato de 7 digitos que supuestamente es el ');
disp('dato recibido y que contiene un error');
disp('El programa se encargara de detectar el error y corregirlo.');
disp(' ');
%Codificacion
t=input('\nIntroduzca No.binario de 4 bits a trasmitir\n(entre corchetes y separados por espacios): t= ');

pc1=[t(1) t(2) t(4)];
pc2=[t(1) t(3) t(4)];
pc4=[t(2) t(3) t(4)];

s1=sum(pc1);
res1=rem(s1,2);
if res1==0
    p1=0;
else
    p1=1;
end

s2=sum(pc2);
res2=rem(s2,2);
if res2==0
    p2=0;
else
    p2=1;
end

s4=sum(pc4);
res4=rem(s4,2);
if res4==0
    p4=0;
else
    p4=1;
end

tx=[p1 p2 t(1) p4 t(2) t(3) t(4)];              %     1  2   3   4   5   6    7
disp(' ');                                       %tx=[p1 p2 t(1) p4 t(2) t(3) t(4]
disp('El dato codificado es el siguiente:');
disp(' ');
disp(tx);
disp(' ');
r=input('\nIntroduzca dato recibido( de 7 bits y que tenga un error)\n(entre corchetes y separados por espacios): r= ');

%Decodificacion del dato recibido

cd1=[r(1) r(3) r(5) r(7)];
cd2=[r(2) r(3) r(6) r(7)];
cd4=[r(4) r(5) r(6) r(7)];

s1=sum(cd1);
res1=rem(s1,2);
if res1==0
    c1=0;
else
    c1=1;
end

s2=sum(cd2);
res2=rem(s2,2);
if res2==0
    c2=0;
else
    c2=1;
end

s4=sum(cd4);
res4=rem(s4,2);
if res4==0
    c4=0;
else
    c4=1;
end

error=[c4 c2 c1];
dec=c4*2^2+c2*2^1+c1*1;

switch dec
case 0
rdcx=[r(1) r(2) r(3) r(4) r(5) r(6) r(7)];   %1  2   3   4   5   6    7
                                             %rx=[c1 c2 r(1) c4 r(2) r(3) t(4)]
disp(' ');
disp(' ');
disp('!eureka¡ No hubo error en la transmision');
otherwise,                                   % opción por defecto
aux=r(dec);
if aux==0
    r(dec)=1;
else
    r(dec)=0;
end
rdcx=[r(1) r(2) r(3) r(4) r(5) r(6) r(7)];    %1  2   3   4   5   6    7
                                              %rx=[c1 c2 r(1) c4 r(2) r(3) t(4)]
rx=[r(3) r(5) r(6) r(7)];
disp(' ');
disp('El dato enviado es el siguiente:');
disp(t);
disp(' ');
disp('El error se encuentra en la posicion(1  2   3   4   5   6   7):');
disp(dec)
disp(' ');
disp('El dato corregido queda asi:');
disp(rx)                                              
end
