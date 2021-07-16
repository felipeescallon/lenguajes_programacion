function x=Newton_sys(F,x0,tol,maxit)
% Resuelve el sistema no lineal F(x)=0 usando el m¶etodo de Newton
% Los vectores x y x0 son vectores fila
% la funci¶on F da un vector columna [f1(x)...fn(x)]'
% detener cuando la norma del cambio en el vector soluci¶on sea menor a la
tolerancia
% la siguiente aproximaci¶on de soluci¶on es x new=x old+y';
x_old=x0;
disp([0 x_old]);
iter=1;
while (iter<=maxit)
y=-feval(JF,x_old)nfeval(F,x_old);
x new=x_old+y';
dif=norm(x_new-x_old);
disp([iter x_new_dif]);
if dif<=tol
x=x_new;
disp('El m¶etodo de Newton ha convergido')
return;
else
x_old=x_new;
end
iter=iter+1;
end
disp('El m¶etodo de Newton no convergi¶o')
x=x_new;