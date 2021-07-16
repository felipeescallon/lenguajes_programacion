X=[1,2,3,4]
Y=[2.3,4.5,7.8,9.6]
XA=log10(X)
YA=log10(Y)
sol=polyfit(XA,YA,1)
plot(X,Y,'*')
b3=sol(1)
a3=10^(sol(2))
