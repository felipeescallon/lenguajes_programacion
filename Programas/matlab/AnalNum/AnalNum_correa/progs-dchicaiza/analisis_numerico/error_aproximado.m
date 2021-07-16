function Error=error_aproximado(Vact,Vante)
Error = abs((Vact-Vante)/Vact)*100;