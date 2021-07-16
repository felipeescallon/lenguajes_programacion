function [x,k] = broyden
% syntax:  [X] = BROYDEN(FCN1,FCN2,X0,MAXITS)
% A function to compute a 2x2 system of nonlinear equations
% minumum amount of inputs required are fcn1 & fcn2
% when inputing fcn1,fcn2 please note only x1 and x2 can be used
%
%
% inputs    - FCN1: is the first equation input as a string
%           - FCN2: is the second equation input as a string
%           - X0: an initial approximation to the solution default is [1 1]
%           - MAXITS: maximum number of iterations default is 50
%           - TOL: the tolerance level default is 1e-8
% outputs   - X: the solution to the system
%           - K: the number of iterations taken to reach the solution
%
% Note: To make this file more robust, simply adjust the input variables to
% allow for a function file.
syms x1 x2
x0=[0.5;1];
tol=0.5;
fcn1=x1*x2-5*x1^2+2;
fcn2=x2^2+2*x1^2*x2-8;

maxits=15;

B = [diff(fcn1,x1) diff(fcn1,x2);diff(fcn2,x1) diff(fcn2,x2)];
x1 = x0(1);
x2 = x0(2);
h = inline(fcn1,'x1','x2');
g = inline(fcn2,'x1','x2');
f(1:2,1) = [h(x1,x2);g(x1,x2)];
B = eval(B);
x = [x1 x2]';
for k=1:maxits
    s = B\(-f);
    x = x + s;
    fnew = [h(x(1),x(2));g(x(1),x(2))];
    y = fnew-f;
    if abs(fnew-f) < tol
        break
    end
    f = fnew;
    B = B + ((y-B*s)*s')/(s'*s);
end