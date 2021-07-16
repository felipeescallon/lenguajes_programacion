%Regla de crammer
a=input('Ingrese el termino 1.1 de la matriz=');
b=input('Ingrese el termino 1.2 de la matriz=');
c=input('Ingrese el termino 2.1 de la matriz=');
d=input('Ingrese el termino 2.2 de la matriz=');
A= [a,b;c,d];
e=input('Ingrese el primer termino del vector de constantes');
f=input('Ingrese el segundo termino del vector de constantes');
B=[e;f];
X=det([e,b;f,d])/det(A)
Y=det([a,e;c,f])/det(A)