%--------------------PENDIENTE ACABARLO--------------------------------------------------------------------
%CREATED BY FELIPE ESCALLON PORTILLA
%17/FEB/04
%CODIGOS DE HAMMING
%limpiar variables
clear;
clc;
%basado en ejp libro tomasi:
%en realidad pediremos que el usuario meta su codigo a tx:....DINAMICAMENTE
TX=input('\nIntroduzca No.binario de m bits a trasmitir\n(entre corchetes y separados por espacios): TX= ');
disp('MENSAJE A TRANSMITIR =' );
disp(TX);
temp=size(TX);%ENTREGA NUMERO DE FILAS Y COLUMNAS EN UN VECTOR:FILAS=temp(1) COLUMNAS=temp(2)
%nos interesa el # de bits=m=# colunas
disp('usted ha introducido un codigo de m bits =');
m=temp(2);
disp(m);
%V=[1 0 1 1 0 0 0 1 0 0 1 0];%cadena de datos version normal
%V=[0 1 0 0 1 0 0 0 1 1 0 1];%cadena de datos version al reves
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
disp('NUMERO DE BITS DE HAMMING (n) =');
disp(n);
t=n+m;%numero total de bits para tx
disp('numero total de bits para tx usando el Codigo de Hamming(t=n+m) =');
disp(t);
%VH=[H H H H H 0 1 0 0 1 0 0 0 1 1 0 1]%cadena de datos con los bits de hamming desconocidos
%------------------HALLO EL VALOR DE LOS BITS DE HAMMING------------------%
J=find(TX)%encuentro las posiciones(el numero del bit correspondiente) de los unos
T=TX(J)%probando que esta bien!
c=size(J)%me da el numero de filas y columnas
filas=c(1)%siempre c(1)=1!
columnas=c(2)
%PONIENDO LOS BITS EN SU NUMERO NORMAL(DE DER A IZQ EMPEZANDO EN CERO) YA QUE MATLAB TOMA LOS INDICES DE IZQ A DER EMPEZANDO EN 1
for cont=1:1:columnas
    a=[m-J(cont)+1]
end    

%disp(a);
%YO SE QUE J ES UNA MATRIZ DE 1 FILA Y c(2) COLUMNAS

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
A=VTX-VRX
B=abs(A)
pos=find(B)%encuentra posicion del vector en donde hay un numero distinto de cero.
%posBitError=t-pos+1% la numeracion es al reves comenzando en 1 <=> el profe dice que al reves no
valorBitError=B(pos)
correccionBit=not(B(pos))

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

%-------NO ES!!!-------------
%bitErroneo=t4(???): lo hago con un recoorido del vector r4, sacando la posicion que....
%....tenga 1, ya que los demas son ceros(error es en un solo bit)
%solucion:
%k=find(R)%encuentra posicion del vector en donde hay un numero distinto de cero.
%bitErroneo=n-k+1%find tiene la numeracion al reves.
%valorBitErroneo=R(k)
%correccionBitErroneo=not(R(k))
%-----------------------------------
