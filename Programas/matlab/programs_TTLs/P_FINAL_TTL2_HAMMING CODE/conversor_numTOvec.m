
%CREATED BY FELIPE ESCALLON PORTILLA
%26/FEB/04

%IT`S WORKING
%TAMBIEN SIRVE PARA LA INVERSION DE LOS DIGITOS DE UN NUMERO DADO!(BASADO EN: II PARCIAL PRACTICO DE INFORMATICA 1)

clear
clc

%HAY QUE APLICARLO EN HAMMING!

%just to begin proving...
a=1824;
c=fix(a./10);
r=mod(a,10);
c=fix(c./10);
r=mod(c,10);
c=fix(c./10);
r=mod(c,10);

%ALGORITMO QUE CONVIERTE UN NUMERO A UN VECTOR DONDE CADA DIGITO ES UNA POSICION DEL VECTOR

n=5%es conocido y es el numero de digitos que tiene el numero
%j=1
j=n
cociente=01000%CONOCIDO
%cociente=12345
    
    %Residuo(1)=mod(cociente,10)
    Residuo(n)=mod(cociente,10)

    while cociente~=1%diferente es ~=
        %j=j+1
        if(j~=1)
            j=j-1
            cociente=fix(cociente./10)%da la parte entera(fix)
            Residuo(j)=mod(cociente,10)%da el residuo(mod)       
        else 
            break
        end
    end   

[p,q]=size(Residuo)
V=find(Residuo)
disp(Residuo)