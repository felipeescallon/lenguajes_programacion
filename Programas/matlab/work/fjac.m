%calculando la matriz jacobiana y su inversa.

function [fjinv] = fjac(xent1,xent2);

           
            
           syms x1 x2 
           f1=x1*x2-5*x1*x1+2;
           f2=x2*x2+2*x1*x1*x2-8;
      
          j=jacobian([f1;f2],[x1 x2]);
          disp(j);
          jinv=inv(j);
          disp(jinv);
          
          
          fjinv=jinv;

  