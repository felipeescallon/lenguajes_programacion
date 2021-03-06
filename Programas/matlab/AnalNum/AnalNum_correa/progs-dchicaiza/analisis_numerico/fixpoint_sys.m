function x=fixpoint_sys(G,x0,tol,max)
% Resuleve el sistema no lineal x=G(x) usando la iteraci?on del punto fijo
% Los vectores x y x0 son vectores fila
% la funci?on G da un vector columna [g1(x)...gn(x)]'
% detener cuando la norma del cambio en el vector soluci?on sea menor a la
%tolerancia
% la siguiente aproximaci?on de soluci?on es x new=x old+y';
disp([0 x0]);
x_old=x0;
iter=1;
while (iter<=max)
y=feval(G,x_old)
x_new=y';
dif=norm(x_new-x_old);
disp([iter x_new dif]);
if dif<=tol
x=x_new;
disp('La iteraci?on del punto fijo ha convergido')
return;
else
x_old=x_new;
end
iter=iter+1;
end
disp('La iteraci?on del punto fijo no convirgi?o')
x=x_new;
