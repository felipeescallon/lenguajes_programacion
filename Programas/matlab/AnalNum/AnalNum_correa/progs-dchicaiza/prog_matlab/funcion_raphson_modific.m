%funcion newton raphson modificado
function[xa,Ea]=funcion_raphson_modific(g,xo,Es)
dg= diff(g)
u=g/dg
du= diff(u)
Ea=100;
while Ea>Es
 x=xo;
 xa= x-(eval(u)/eval(du))
 Ea=funcion_error_relativo_aproximado(xa,xo)
 xo=xa;
end
