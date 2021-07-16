t=0:0.00392:1;

for i=1:256

if t(i)<=0.5

dcua(i)=255;

end

if t(i)>0.5

dcua(i)=0;

end

end

[dcua']