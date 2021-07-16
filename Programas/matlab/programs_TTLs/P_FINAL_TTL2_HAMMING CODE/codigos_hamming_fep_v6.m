%--------------------PENDIENTE ACABARLO--------------------------------------------------------------------
%CREATED BY FELIPE ESCALLON PORTILLA

%22/FEB/04
%CODIGOS DE HAMMING

%CONVENCION:VECTORES([]) CON MAYUS Y VARIABLES CON minus    

%limpiar variables
clear;
clc;
%basado en ejp libro tomasi:
%en realidad pediremos que el usuario meta su codigo a tx:....DINAMICAMENTE
TX=input('\nIntroduzca No.binario de m bits a trasmitir\n(entre corchetes y separados por espacios): TX= ')
disp('MENSAJE A TRANSMITIR =' )
disp(TX)
TEMP=size(TX);%ENTREGA NUMERO DE FILAS Y COLUMNAS EN UN VECTOR:FILAS=temp(1) COLUMNAS=temp(2)
%nos interesa el # de bits=m=# colunas
disp('usted ha introducido un codigo de m bits =')
m=TEMP(2);
disp(m)
%V=[1 0 1 1 0 0 0 1 0 0 1 0];%cadena de datos version normal
%V=[0 1 0 0 1 0 0 0 1 1 0 1];%cadena de datos version al reves
%m=input('how many bits does the data stream have:')%numero de bits de la cadena de datos

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


disp('MENSAJE CODIFICADO CON LOS BITS DE HAMMING ES:')
%LOS VALORES NEGARTIVOS CORRESPONDEN A LOS DISTINTOS BITS DE HAMMING
TXH=[-5 -4 -3 -2 -1 1 0 1 1 0 0 0 1 0 0 1 0 ];%el codigo a tx es fijo:PENDIENTE:HACERLO CON strcat OR SOMETHING
disp(TXH)
%VH=[H H H H H 0 1 0 0 1 0 0 0 1 1 0 1]%cadena de datos con los bits de hamming desconocidos

%------------------HALLO EL VALOR DE LOS BITS DE HAMMING------------------%

%----------------------PRIMERO ENCUENTRO LAS POSICIONES DONDE ESTAN LOS UNOS DEL MSG----------------------



j=1%var auxiliar que me permite crear un vector: ver sgte ciclo.
for contador=1:m
    if TX(contador)==1
        POSICION(j)=contador;%no utilizo la var contador pa evitar problemas
        j=j+1;%incremento j para crear las posiciones del vector
    end
end

[fil,col]=size(POSICION);
%--------------------------------------------------------------------------------------------------------
%SOLAMENTE PARA COMPROBAR:find hace el algoritma hecho por mi en el ciclo anterior
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
UNOS=A%VECTOR DE UNOS DEL MSG A TX como debe ser(der a izq)

%disp(A);
%YO SE QUE J ES UNA MATRIZ DE 1 FILA Y c(2) COLUMNAS

%--------------------PENDIENTE CORREGIR LO SGTE--------------------------------------------------------------------
%not--------------------------------
%k2=1
%for z=1:1:col    
    
 %   P(k2)=dec2bin((UNOS(z)),n)
 %   k2=k2+1
 %end

%disp(P)

%ASI NO FUNCIONA--------------------------------
%OJOOOOOOOOOOOOOOO
%c=bitxor(2,5)=>me da en c el 2 xor 3 sin necesidad de convertir a binario el 2 y 3
y0=UNOS(col);%0 es distinto de o
y1=UNOS(col-1);
z1=bitxor(y0,y1);

%NO SE PUEDE POR QUE EL XOR ES BIT A BIT
%u0=dec2bin(UNOS(col),n)
%u1=dec2bin(UNOS(col-1),n)
%temp1=xor(u0,u1)%NO SE PUEDE POR QUE EL XOR ES BIT A BIT
%PEND:CORREGIR(VER OTROS CODIGOS)
k3=n;
for c=2:col%default: step=1
    %AUX(K3)=dec2bin(UNOS(col-2))
    conv=col-c+1;
    %a=UNOS(conv)
    b=UNOS(conv-1);
    XOR(k3)=bitxor(z1,b);
    %hamming=bitxor()
    k3=k3-1;
    if conv==2
        break
    end
end
%ASI NO FUNCIONA--------------------------------
    

%-------------------------------------------------------------------------
%------------------------------------------------------------------------------------------

%CORRECCION DE LO ANTERIOR:

%Ahora se realiza la operacion con XOR para determinar el
%codigo de Hamming


hamming=dec2bin(UNOS(col),n)%CONVIERTO EL NUMERO ENTERO A BINARIO;ME LO ENTREGA EN FORMA DE CADENA(OJO:STRING)
hamming=str2num(hamming)%CONVIERTO BINARIO OBTENIDO EN STR A NUMERO!
for i=2:col,
   aux(i)=str2num(dec2bin(UNOS(i),n))
   hamming=bitxor(hamming,aux(i))
end

hamming=num2str(hamming)%CONVIERTO NUM A STR PA PODERLO MOSTRAR
fprintf('Codigo Hamming con las xor: ')
fprintf(hamming)
fprintf('\n')
fprintf('\n')

%------------------------------------------------------------------------------------------------------
%VAUX=size(P)

%col=VAUX(2);%col=columnas
%----------------------------------------------------------------------------------------------------
b2=[0 0 0 1 0]
b5=[0 0 1 0 1]
r1=xor(b2,b5)
b9=[0 1 0 0 1]
r2=xor(r1,b9)
b10=[0 1 0 1 0]
r3=xor(r2,b10)
b12=[0 1 1 0 0]
r4=xor(r3,b12)
bH=r4
%MATLAB RECORRE DE IZQ A DERECHA LOS VECTORES!
bh5=bH(5)
bh4=bH(4)
bh3=bH(3)
bh2=bH(2)
bh1=bH(1)
%------------------INFORMACION A TX------------------%
VTX=[bh1 bh2 bh3 bh4 bh5  0 1 0 0 1 0 0 0 1 1 0 1]



%------------------INFORMACION RECIBIDA-----------------%
%SUPONEMOS ERROR EN EL BIT 7(en la realidad le pediremos al usr que meta el codigo con un error)
VRX=[bh1 bh2 bh3 bh4 bh5 0 0 0 0 1 0 0 0 1 1 0 1]

%----------------LO MAS OVBIO:PERO ASI NOOOOOOO------------------%
F=VTX-VRX
G=abs(F)
pos=find(G)%encuentra posicion del vector en donde hay un numero distinto de cero.
%posBitError=t-pos+1% la numeracion es al reves comenzando en 1 <=> el profe dice que al reves no
valorBitError=G(pos)
correccionBit=not(G(pos))

%----------------%
%----------HALLO POSICION DEL BIT ERRONEO---------------%
t1=xor(bH,b5)
t2=xor(t1,b9)
t3=xor(t2,b10)
t4=xor(t3,b12)
M=t4%vector de n(=5) posiciones en donde esta codificado en binario la posicion del bit de error
%BASTA CON SOLO CONVERTIR ESE VALOR A ENTERO Y LISTO!!!
%reccorrido es al reves
m5=M(5)
m4=M(4)
m3=M(3)
m2=M(2)
m1=M(1)
