%programa para calcular el error relativo aproximado
va=input ('Digite valor actual')
vn=input ('Digite el valor anterior')
ea=abs((va-vn)/va)*100;
disp ('Error relativo aproximado')
disp (ea)
