%NO TAN BUENO: ES SOLO UN EJP(EL DE TOMASI)


%CODIGO DE HAMMING
% Codigo Caracter:     100101111001
%Andres Felipe Rendon Rios   cod.06991095
%Javier Mesa Durango			  cod.06991062	



clear

%bit1=[0 0 0 0 1];
bit2=[0 0 0 1 0];
%bit3=[0 0 0 1 1];
%bit4=[0 0 1 0 0];
%bit5=[0 0 1 0 1];
bit6=[0 0 1 1 0];
%bit7=[0 0 1 1 1];
%bit8=[0 1 0 0 0];
%bit9=[0 1 0 0 1];
%bit10=[0 1 0 1 0];
%bit11=[0 1 0 1 1];
bit12=[0 1 1 0 0];
%bit13=[0 1 1 0 1];
bit14=[0 1 1 1 0];
%bit15=[0 1 1 1 1];
bit16=[1 0 0 0 0];
%bit17=[1 0 0 0 1];



fprintf('\n')
fprintf('\n')
fprintf('        DETECCION DE ERRORES POR CODIGO DE HAMMING ')
fprintf('\n')
fprintf('\n')

caracter=[1 0 1 1 0 0 0 1 0 0 1 0];
caract=num2str(caracter)
fprintf('Informacion a transmitir: ')
fprintf(caract)
fprintf('\n')
fprintf('\n')
fprintf('Criterio     2exp(n)> m+n+1')
fprintf('\n')
fprintf('\n')

%criterio     2exp(n)> m+n+1
%n: No. de bits del còdigo e hamming = 12 bits
%m: No. de bits de caracter
%Para este caso n=5      =>    32 > 18 

n=0;                         %No. de bits codigo de hamming
m=12;

while (2^n)<(m+n+1),
   n=n+1;
end

N=num2str(n);
fprintf('No. de bits Codigo de Hamming: ')
fprintf(N)
fprintf('\n')
fprintf('\n')
   
   
   %Ahora se ubican arbitrariamente los bits de Hamming en el caracter
   %con lo cual tendremos una palabra M=17 bits      (5+12)
   
   
M=17;   
codificada= [8 1 0 1 8 1 0 0 8 8 0 1 0 8 0 1 0];  
cod=num2str(codificada);
fprintf('Información mas Hamming: ')
fprintf(cod)
fprintf('\n')
fprintf('\n')
   

%Ahora se halla el código de Hamming

j=n;
for i=1:M
   if codificada(i)== 1;
      c=(M+1-i);
      bit(j)=c;
      j=j-1;
   end
   
end
Bit=num2str(bit);
fprintf('Bits que son iguales a uno:  ')
fprintf(Bit)
fprintf('\n')
fprintf('\n')



%Ahora se realiza la operacion con XOR para determinar el
%codigo de Hamming


ham=dec2bin(bit(1))
ham=str2num(ham)
for i=2:n
   temp(i)=str2num(dec2bin(bit(i)))
   ham=bitxor(ham,temp(i))
end
ham=num2str(ham)
fprintf('Codigo Hamming con las xor: ')
fprintf(ham)
fprintf('\n')
fprintf('\n')



%Aqui con las variables BIT 1,2,3....
%Los bits que tienen unos son 2 6 12 14 16

cod_ham=bit2
cod_ham=xor(cod_ham,bit6)
cod_ham=xor(cod_ham,bit12)
cod_ham=xor(cod_ham,bit14)
cod_ham=xor(cod_ham,bit16) 
cod_ham

Cod_ham=num2str(cod_ham);
fprintf('El codigo de hamming es: ')
fprintf(Cod_ham)
fprintf('\n')
fprintf('\n')


%El nuevo código sera:   [1 1 0 1 0 1 0 0 1 1 0 1 0 0 0 1 0]

fprintf('El flujo de datos codificado se convierte en:   [1 1 0 1 0 1 0 0 1 1 0 1 0 0 0 1 0]')
fprintf('\n')
fprintf('\n')

fprintf('\n')
fprintf('\n')
fprintf('Asumimos un error en el bit 14:   	  [1 1 0 0 0 1 0 0 1 1 0 1 0 0 0 1 0]')
fprintf('\n')
fprintf('\n')

codificada(M+1-14)=0;                              %Ubicacion del error


j=0;
for i=1:M,
   if codificada(i)== 1
      j=j+1;
      c=(M+1-i);
      new_bit(j)=c;
      
   end
end

Nuevos=num2str(new_bit);
fprintf('Bits que son iguales a uno:  ')
fprintf(Nuevos)
fprintf('\n')
fprintf('\n')
fprintf('Usamos xor con el codigo binario de cada uno de estos bits: ')
fprintf('\n')
fprintf('\n')


%Hallamos donde esta el error
%Los bits que tienen unos son new_bit= 2 6 12 16

for i=1:n,
   cx(i)=dec2bin(cod_ham(i));
end
cx=str2num(cx);

for i=1:j,
   aux(i)=str2num(dec2bin(new_bit(i)));
   cx=bitxor(cx,aux(i));
end
cx=num2str(cx);
fprintf('Error con las xor: ')
fprintf(cx)
fprintf('\n')
fprintf('\n')



%Error con las variables BIT 1,2,3....

error=cod_ham;
error=xor(error,bit2);
error=xor(error,bit6);
error=xor(error,bit12);
error=xor(error,bit16); 
error
for i=1:n,
   Error(i)=num2str(error(i));
end

Error=bin2dec(Error);
Error=num2str(Error);

fprintf('El error ocurrio en el bit: ')
fprintf(Error)
fprintf('\n')
fprintf('\n')








