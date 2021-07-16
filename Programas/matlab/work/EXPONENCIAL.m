X=[1,2,3,4]
Y=[3,5,8,10]
YA=log(Y)
sol=polyfit(X,YA,1)
plot(X,Y,'*')
b2=sol(1)
a2=exp(sol(2))
