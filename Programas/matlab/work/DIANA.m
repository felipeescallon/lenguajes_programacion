% ERROR_APROXIMADO
Vact = input('digite el valor actual')
Vante = input('digite el valor anterior')
Error = abs((Vact-Vante)/Vact)*100;
disp('Error=')
disp(Error)