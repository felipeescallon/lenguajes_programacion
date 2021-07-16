%funcion error relativo verdadero
function[et]=err_ver(vb,va)
et=abs((vb-va)/vb)*100;