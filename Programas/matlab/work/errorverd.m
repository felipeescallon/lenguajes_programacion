%programa para calcular el error relativo verdadero
vb=input ('Digite valor verdadero')
va=input ('Digite el valor aproximado')
ea=abs((vb-va)/vb)*100;
disp ('Error relativo verdadero')
disp (ea)
