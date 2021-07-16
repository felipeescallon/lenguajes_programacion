%------------------------------------------------------------------------------------------------------------------------------------%
%
%
%                                               CREATED BY FELIPE ESCALLON PORTILLA
%                                                         26/FEB/04
%                                                    CODIGOS DE HAMMING
%            VERSION DEFINITIVA(ESTA FUNCIONANDO PARA CUALQUIER POSICION DE LOS BITS DE HAMMING Y DEL BIT SENCILLO ERRONEO)!!!
%
%
%------------------------------------------------------------------------------------------------------------------------------------%

%------------------------------------------------------------------------------------------------------------------------------------%

%BEGINNING:

%CONVENCION:VECTORES([]) CON MAYUS Y VARIABLES CON minus    
%limpiar variables
clear
clc
%NOTA:basado en ejp libro tomasi:ELLOS TOMAN LOS BITS EMPEZANDO DE DERECHA A IZQUIERDA (NORMAL) PERO EMPEZANDO DESDE LA POSICION 1
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

%------------------------------------------------------------------------------------------------------------------------------------%
%-----------------------------------------------------HALLO EL VALOR DE LOS BITS DE HAMMING-------------------------------------------%
%NOTA:
%find(TX) hace el algoritmo que se muestra a continuacion:HALLAR EL VALOR DE LA POSICION DONDE ESTAN LOS UNOS!!!
j=1;%var auxiliar que me permite crear un vector: ver sgte ciclo.
for contador=1:m
    if TX(contador)==1
        POSICION(j)=contador;%no utilizo la var contador pa evitar problemas
        j=j+1;%incremento j para crear las posiciones del vector
    end
end
[fil,col]=size(POSICION);

%--PONIENDO LOS BITS EN SU NUMERO NORMAL(DE DER A IZQ EMPEZANDO EN CERO) YA QUE MATLAB TOMA LOS INDICES DE IZQ A DER EMPEZANDO EN 1---

k=1;
for cont=1:1:col
    A(k)=m-POSICION(cont)+1;
    k=k+1;
end    
%POSICION,UNOS y A tienen el mismo tamaño.
PosUNOStx=A%VECTOR DE UNOS DEL MSG A TX como debe ser(der a izq)
%-------------------------------------------------------------------------------------------------------------------------------------

%----------ALGORITMO PARA ENCONTRAR EL VALOR DE LOS BITS DE HAMMING:-----------------------------------------------------

%NOTESE LA IMPORTANCIA DE ACTUALIZAR UNA VARIABLE ,CUANDO NECESITEMOS SU RESULTRADO FINAL DESPUES DE UN PROCESO(CICLO)!!!
var=col-1;
codigoHamming=PosUNOStx(col);%es el primer bit
for i=1:var
    codigoHamming=bitxor(codigoHamming,PosUNOStx(col-i));
end
codHam=codigoHamming%NECESITO TENERLO EN ENTERO PARA EL PROCESAMIENTO DEL ERROR EN RX!!!
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
%------------------------------------------------------------------------------------------------------------------------------------%

%----------------MUESTRO AL USUARIO LOS DATOS REALES CODIFICADOS----------------------------------------------------------------------

cadenaVectorHamming=num2str(VectorHamming)%num to str
%cadenaDef=strcat(cadenaVectorHamming,cadenaTX)%concateno::OJO_ESTA CONCATENANDO "MAL"

%NOTA: TXdef=[VectorHamming,TX] === TXdef=cat(2,VectorHamming,TX)!!!

TXdef=cat(2,VectorHamming,TX)%DE UNA CONCATENA VECTORES!!!(NO NECESITO PASO POR str);EL ARG 2 ES PARA QUE LOS PONGA HORIZONTALMENTE

%TXdef=str2num(cadenaDef)%convierte la cadena a numero o vector(this case) MALpor lo anterior___PEND FIX IT!
[z1,z2]=size(TXdef);
disp('MENSAJE CODIFICADO CON LOS BITS DE HAMMING HALLADOS ES:')
disp(TXdef)
%------------------------------------------------------------------------------------------------------------------------------------ 

%----------------------------PROCESAMIENTO DEL ERROR--------------------------------------------------------------------------------

%NOTA:LOS BITS DE HAMMING NO SE LOS TENDRA EN CUENTA EN RX <=> TOMASI`S WAY!
%COMO YO PUSE LOS BITS DE HAMMING CONVENIENTEMENTE0 =>PosUNOSrx="(PosUNOStx + error)"

%------------MANEJO DE EXECPCIONES:-----------------------------

RXerroneo=input('DE ACUERDO AL MENSAJE CODIFICADO ANTERIOR(t bits), DIGITE(entre corchetes y separados por espacios)\n EL MENSAJE DE RX CON UN ERROR SENCILLO(1 SOLO BIT DE ERROR): RX= ')
[fi,co]=size(RXerroneo)
if co~=t
    disp('EL MENSAJE DEBE SER DE t BITS, CON t= ')
    disp(t)
    disp('INTENTE DE NUEVO!')
    return
end

disp('MENSAJE DE RX CON 1 ERROR EN UN BIT = ' )
disp(RXerroneo)
Errores=abs(TXdef-RXerroneo)
Auxil=find(Errores)
[fila,columna]=size(Auxil)
%-------------------VALIDACION DE QUE LOS ERRORES NO PUEDEN ESTAR EN LOS BITS DE HAMMING-----------------------------
%or=="|"
auxilia=0
%for int=1:columna
for int=1:n
    if Errores(int)==1
    %if Auxil(int)==int
       auxilia=auxilia+1
    end
end

if auxilia>=1
    disp('NO PUEDEN HABER ERRORES EN LOS BITS DE HAMMING, SOLO SE PERMITE ERROR SENCILLO EN EL MENSAJE ORIGINAL A TRANSMITIR!')
    disp('INTENTE DE NUEVO!')
    return
end
%------------------------------------------------------------------------------------------------------------------------------------%

if columna>1
    disp('NO SE PERMITEN ERRORES DE BIT MULTIPLES!!!')
    disp('INTENTE DE NUEVO!')
    return%SE SALE DEL PROGRAMA
    %break
    
    else
        if columna==0
            disp('NO EXISTEN ERRORES!!!')
             disp('INTENTE DE NUEVO!')
            return
        end
end
%¡¡¡¡HA QUEDADO ASEGURADO QUE EL ERROR existente SERA DE BIT SENCILLO!!!!
%--------------------------------------------------------------------------------
%-------------------ALGORITMO DE DETECCION DEL BIT DE ERROR----------------------

bitError=find(Errores)%me da la posicion(matlab`s) del bit de error
%bitErrorReal=t-bitError+1
%tempo=PosUNOStx(bitErrorReal)
valorBitError=RXerroneo(bitError)%ME DA EL VALOR EN EL BIT DE ERROR:
                    %si es cero(en TXdef era un 1)=>hay que excluir ese UNO de PosUNOSrx(column=col-1)
                    %si es uno(en TXdef era un 0)=>hay que incluir ese UNO  a PosUNOSrx(column=col+1)  
if (valorBitError==0)
    column=col-1
    bitErrorReal=bitError-n%ref es de matlab(OJO)
    valor=TX(bitErrorReal)%es la ref que nos sirve(EN TX TODAVIA NO SE AÑADEN LOS N BITS DE HAMMING!)
    aux=not(valor)
    TX(bitErrorReal)=aux%ha sido complementada(0) para que se excluya ese uno
    Auxi=TX
    PosRx=find(Auxi)
    g=1;
    for contad=1:column
        B(g)=m-PosRx(contad)+1;%EN TX TODAVIA NO SE AÑADEN LOS N BITS DE HAMMING!
        g=g+1;
    end    
    PosUNOSrx=B%VECTOR DE UNOS DEL MSG EN RX como debe ser(der a izq)
    
    else%valorBitError==1
        column=col+1
        %disp('PENDIENTE')%no tener en cuenta primeras n posiciones(matlab)=>PosUnosrx=vector de RX restante
        %PosUNOSrx=PosUNOStx%JUST PROVING
        bitErrorReal=bitError-n%ref es de matlab(OJO)
        valor=TX(bitErrorReal)
        aux=not(valor)
        TX(bitErrorReal)=aux%ha sido complementada(1) para que se añada ese uno
        Auxi=TX
        PosRx=find(Auxi)
        g=1;
        for contad=1:column
        B(g)=m-PosRx(contad)+1;%EN TX TODAVIA NO SE AÑADEN LOS N BITS DE HAMMING!
        g=g+1;
        end    
        PosUNOSrx=B%VECTOR DE UNOS DEL MSG EN RX como debe ser(der a izq)    
end

error=codHam;%es el primer operador
error=bitxor(codHam,PosUNOSrx(column));

for j=1:(column-1)
    error=bitxor(error,PosUNOSrx(column-j));
end

disp('EL ERROR ESTA EN LA POSICION:')
posicionError=error-1;%CONVENCION REAL ES:POSICIONES COMIENZAN EN CERO Y AUMENTAN DE UNO EN UNO DE DERECHA A IZQUIERDA!!!
disp(posicionError) 
%-------------------------------------------------------------------------------------------

%---------------------PARA CONTRASTAR Y VER EFECTIVA LA CORRECCION DEL ERROR:----------------------------------------

disp('MENSAJE ERRONEO= ')
disp(RXerroneo)
%------------------------------------------------------------------------------------------------------------------


%--------------------------------CORRECCION DEL ERROR:---------------------------------------------------------------
RXreal=RXerroneo%para verificar que este bien al final

correccionBitError=not(valorBitError)
tempo=RXerroneo(bitError)
RXerroneo(bitError)=not(tempo)
msgCorregido=RXerroneo

disp('EL MENSAJE CORREGIDO ES:')
disp(msgCorregido)

VERIFICACION=abs(RXreal-msgCorregido)
%------------------------------------------------------------------------------------------------------------------

%ENDING.

%------------------------------------------------------------------------------------------------------------------------------------%

