%funcion Secante modificado
function[res,Ea]=funcion_secante(g,xo,Es,xan)
Ea=100;
u=g/dg
while Ea>Es
 x=xo
 xa=eval(u);
 x=xan;
 xb=eval(u);
 res=xo-((xa*(xan-xo))/(xb-xa))
 Ea=funcion_error_relativo_aproximado(res,xo)
 xan=xo;
 xo=res;