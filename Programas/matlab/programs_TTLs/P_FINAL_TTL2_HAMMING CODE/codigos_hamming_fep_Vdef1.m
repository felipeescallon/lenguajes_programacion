%--------------------PENDIENTE ACABARLO:USAR EL PROGRAMA RESIDUOS--------------------------------------------------------------------
%CREATED BY FELIPE ESCALLON PORTILLA
%26/FEB/04
%CODIGOS DE HAMMING:VERSION DEFINITIVA(ESTA FUNCIONANDO PARA CUALQUIER POSICION DE LOS BITS DE HAMMING)
%CONVENCION:VECTORES([]) CON MAYUS Y VARIABLES CON minus    
%limpiar variables
clear
clc
%basado en ejp libro tomasi:
%en realidad pediremos que el usuario meta su codigo a tx:....DINAMICAMENTE

%-------------------ENTRADA DE DATOS POR PARTE DEL USUARIO-----------------------------------------------------
TX=input('\nIntroduzca No.binario de m bits a trasmitir\n(entre corchetes y separados por espacios): TX= ')
disp('MENSAJE A TRANSMITIR =' )
disp(TX)
TEMP=size(TX);%ENTREGA NUMERO DE FILAS Y COLUMNAS EN UN VECTOR:FILAS=temp(1) COLUMNAS=temp(2)
%nos interesa el # de bits=m=# colunas
disp('usted ha introducido un codigo de m bits =')
m=TEMP(2);
disp(m)
%-------------------------------------------------------------------------------------------------------------

%-----HALLO EL VALOR DE n:(2^n >= n+m+1)-------%
for i=0:9999%es como ilogico tx 10000 bits     
    a=2^i;
    b=m+i+1;
    if a>=b        
        n=i;%ya tengo los bits de Hamming para agregar! 
        break;%ya tengo los bits de Hamming para agregar! 
    else
      i=i+1; 
  end
end
disp('NUMERO DE BITS DE HAMMING (n) =')
disp(n)
t=n+m;%numero total de bits para tx
disp('numero total de bits para tx usando el Codigo de Hamming(t=n+m) =')
disp(t)
%-------------------------------------------------------------------------------------

%--------------------MUESTRO AL USUARIO LA INFORMACION CODIFICADA---------------------------------
%POR LO PRONTO SE ASUME ARBITRARIAMENTE(DA LO MISMO CON CUALQUIERA) LA POSICION DE LOS BITS DE HAMMING PARA PODER DECIFRAR BIT A BIT!
%ESO ES TRANSPARENTE AL USUARIO Y SOLO LE CORRESPONDE AL PROGRAMADOR DECIDIR:LOS PONDRE EN LOS BITS MAS SIGNIFICATIVOS DE LA TXHam
w=1;%aux pa crear el vector de caracter
for conta=1:n
Ham(w)='H';
w=w+1;
end
[y1,y2]=size(Ham);
disp(Ham);
cadenaTX=num2str(TX);%hago la conversion de numero o vector(this case) a cadena
cadenaTXHam=strcat(Ham,cadenaTX);%cadena de datoscon los bits de hamming desconocidos
disp('MENSAJE CODIFICADO CON LOS BITS DE HAMMING ES:')
disp(cadenaTXHam)
%-------------------------------------------------------------------------------------

%------------------HALLO EL VALOR DE LOS BITS DE HAMMING------------------%
j=1;%var auxiliar que me permite crear un vector: ver sgte ciclo.
for contador=1:m
    if TX(contador)==1
        POSICION(j)=contador;%no utilizo la var contador pa evitar problemas
        j=j+1;%incremento j para crear las posiciones del vector
    end
end
[fil,col]=size(POSICION);
%-------------------------------------------------------------------------------------

%--------------SOLAMENTE PARA COMPROBAR:find hace el algoritmO hecho por mi en el ciclo anterior---------------
J=find(TX);%encuentro las posiciones(el numero del bit correspondiente) de los unos
T=TX(J);%probando que esta bien!
C=size(J);%me da el numero de filas y columnas
filas=C(1);%siempre c(1)=1!
columnas=C(2);

%-------------------------------------------------------------------------------------
%PONIENDO LOS BITS EN SU NUMERO NORMAL(DE DER A IZQ EMPEZANDO EN CERO) YA QUE MATLAB TOMA LOS INDICES DE IZQ A DER EMPEZANDO EN 1
k=1;
for cont=1:1:col
    A(k)=m-POSICION(cont)+1;
    k=k+1;
end    
%POSICION,UNOS y A tienen el mismo tamaño.
UNOS=A;%VECTOR DE UNOS DEL MSG A TX como debe ser(der a izq)
%-------------------------------------------------------------------------------------

%-------------------HALLO LOS BITS DE HAMMING-------------------------------------

%ESTA FUNCIONANDO PARA CUALQUIER POSICION DE LOS BITS DE HAMMING
%OJOOOOOOOOOOOOOOO:c=bitxor(2,5)=>me da en c el 2 xor 3 sin necesidad de convertir a binario el 2 y 3
%AL TANTEO...ME AYUDO PARA HACER EL CICLO!!!
%cod_ham=UNOS(col)
%cod_ham=bitxor(cod_ham,UNOS(col-1))
%cod_ham=bitxor(cod_ham,UNOS(col-2))
%cod_ham=bitxor(cod_ham,UNOS(col-3))
%cod_ham=bitxor(cod_ham,UNOS(col-4)) 
%cod_ham
%cod_ham=dec2bin(cod_ham,n)%YA TENGO EL CODIGO DE HAMMING

%ALGORITMO DE HAMMING
%NOTESE LA IMPORTANCIA DE ACTUALIZAR UNA VARIABLE ,CUANDO NECESITEMOS SU RESULTRADO FINAL DESPUES DE UN PROCESO(CICLO)!!!
var=col-1;
codigoHamming=UNOS(col);
for i=1:var
    codigoHamming=bitxor(codigoHamming,UNOS(col-i));
end
codigoHamming=dec2bin( codigoHamming,n);%YA TENGO EL CODIGO DE HAMMING EN BINARIO(OJO:pero esta como un STRING=>conv a formato numerico
disp('EL CODIGO DE HAMMING ES:')
disp(codigoHamming) 
cod_ham=str2num(codigoHamming)%CONVIERTO EL STRING A NUMERO 
%------------------------------------------------------------------------------------------------------------------------------------

%---------------CONVERTIR ESA CADENA A UN VECTOR CON POSICIONES=DIGITOS::BASADO EN EL PROGRAMA "CONVERSOR NUMERO_VECTOR"-------
j=n;
cociente=cod_ham;%CERO A LA IZQUIERDA NO IMPORTA
Residuo(n)=mod(cociente,10);
    while cociente~=1%diferente es ~=     
        if(j~=1)
            j=j-1;
            cociente=fix(cociente./10);%da la parte entera(fix)
            Residuo(j)=mod(cociente,10);%da el residuo(mod)       
        else 
            break
        end
    end
VectorHamming=Residuo;    
%------------------------------------------------------------------------------------------------------------------------------------ 

%----------------MUESTRO AL USUARIO LOS DATOS REALES CODIFICADOS------------------------------------------------------------------------------------ 
cadenaVectorHamming=num2str(VectorHamming)
cadenaDef=strcat(cadenaVectorHamming,cadenaTX)
disp('MENSAJE CODIFICADO CON LOS BITS DE HAMMING HALLADOS ES:')
disp(cadenaDef)
%------------------------------------------------------------------------------------------------------------------------------------ 





%TAMBIEN SE ASUMIRA UN ERROR CONOCIDO(POSICION ESPECIFICA):SO FAR; THE IDEASIS TO MAKE IT WORK WITH ANY ERROR POSITION!!! 
%ENTONCES TENEMOS:

b2=[0 0 0 1 0];
b5=[0 0 1 0 1];
r1=xor(b2,b5);
b9=[0 1 0 0 1];
r2=xor(r1,b9);
b10=[0 1 0 1 0];
r3=xor(r2,b10);
b12=[0 1 1 0 0];
r4=xor(r3,b12);
bH=r4;
%MATLAB RECORRE DE IZQ A DERECHA LOS VECTORES!
bh5=bH(5);
bh4=bH(4);
bh3=bH(3);
bh2=bH(2);
bh1=bH(1);
%------------------INFORMACION A TX------------------%
VTX=[bh1 bh2 bh3 bh4 bh5  0 1 0 0 1 0 0 0 1 1 0 1];
%------------------INFORMACION RECIBIDA-----------------%
%SUPONEMOS ERROR EN EL BIT 7(en la realidad le pediremos al usr que meta el codigo con un error)
VRX=[bh1 bh2 bh3 bh4 bh5 0 0 0 0 1 0 0 0 1 1 0 1];
%----------------LO MAS OVBIO:PERO ASI NOOOOOOO===>>>>PERO SIRVE PARA MANEJO DE EXCEPCIONES POR MENSAJE!------------------%
F=VTX-VRX;
G=abs(F);
pos=find(G);%encuentra posicion del vector en donde hay un numero distinto de cero.
%posBitError=t-pos+1% la numeracion es al reves comenzando en 1 <=> el profe dice que al reves no
valorBitError=G(pos);
correccionBit=not(G(pos));
%----------------%
%----------HALLO POSICION DEL BIT ERRONEO---------------%
t1=xor(bH,b5);
t2=xor(t1,b9);
t3=xor(t2,b10);
t4=xor(t3,b12);
M=t4;%vector de n(=5) posiciones en donde esta codificado en binario la posicion del bit de error
%BASTA CON SOLO CONVERTIR ESE VALOR A ENTERO Y LISTO!!!
%reccorrido es al reves
m5=M(5);
m4=M(4);
m3=M(3);
m2=M(2);
m1=M(1);
