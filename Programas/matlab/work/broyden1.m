% Inicio
v = F(x0)
M = inv(DF(x0))
  % Inversa Jacobiano
s = - M*v;
x = x0+s;
  % Paso de Newton
incr = norm(s);
while incr > tol
  w = v;	   % F(x(k-1))
  v = F(x);	
  y = v-w;	   % F(x(k)) - F(x(k-1))
  z = - M*y;	   % -inv(A(k-1))*y(k)
  p = - s' *z;	
  q = s' *M;	   % s(k)'*inv(A(k-1)
  R = (s+z)*q/p; 
  M = M+R;	   % inversa de A(k)
  s = - M*v;
  x = x+s;	  % Paso de Broyden
  incr = norm(s);
end
