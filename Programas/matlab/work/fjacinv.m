%Para evaluar la matriz jacobiana inversa.

function [fjinv] = fjacinv(x1,x2);
    jinv=[-(x2+x1^2)/(-x2^2+x1^2*x2+10*x1*x2+10*x1^3) 1/2*x1/(-x2^2+x1^2*x2+10*x1*x2+10*x1^3);2*x1*x2/(-x2^2+x1^2*x2+10*x1*x2+10*x1^3) 1/2*(-x2+10*x1)/(-x2^2+x1^2*x2+10*x1*x2+10*x1^3)];
    fjinv=jinv;