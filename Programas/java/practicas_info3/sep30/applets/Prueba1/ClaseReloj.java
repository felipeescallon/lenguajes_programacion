import java.applet.*;
import java.awt.*;
import java.util.*;
import java.text.DateFormat;
import java.lang.*;
import java.io.*;

class ClaseReloj extends Applet  
{

private int horas, minutos, segundos, dia, mes, savehoras, saveminutos, savesegundos, savedia, savemes, segundos1;
private long ano, saveano; 			
private Font fuente;
private String variable1, variable2, variable3, variable4, variable5, variable6;

public ClaseReloj()
{
segundos1=1;
horas=0;
minutos=0;
segundos=0;
dia=1;
mes=1;
ano=2000;
savehoras=0;
saveminutos=0;
savesegundos=0;
savedia=1;
savemes=1;
saveano=2000;
setBackground(Color.black);
}

public void logica()

{
	int limite;
	try
	{
		Thread.sleep(1000);
	}
	catch(InterruptedException e)
	{
	}

	if(segundos==59)
	{
	segundos=0;

		if(minutos==59)
		{
			minutos=0;

			if(horas==23)
			{
			horas=0;
			int control1=bisiesto();

			if(mes==4 || mes==6 || mes==9 || mes==11)
				{
				limite=30;
				}
			else
				{
				limite=31;
				}

			if(control1==1 && mes==2)
				{
				limite=29;
				}
			if(control1==0 && mes==2)
				{
				limite=28;
				}

				if(dia==limite)
				 {
				 dia=1;
						if(mes==12)
						{
						mes=1;
							if(ano==9999)
							{
							ano=0;
							}

							else
							{
							ano=ano+1;
							}

						}
						else
						{
						mes=mes+1;
						}


				}
				else
				{
				dia=dia+1;
				}

			}

			else
			{
			horas=horas+1;
			}

		}

		else
		{
		if(segundos1==1)
		minutos=minutos+1;
		}

	}

	else
	{
	segundos1=1;
	segundos=segundos+1;
	}



}


public int bisiesto()
{
float residuo;
residuo=ano%4;

if(residuo==0)
	{
	return 1;
	}

else
	{
	return 0;
	}

}

public void paint(Graphics g)  					// Muestra la hora en formato HH:MM:SS
{
	fuente = new Font("Arial",Font.BOLD,24);
	g.setColor(Color.green);
	g.draw3DRect(1,1,getSize().width-3,getSize().height-3,false);
	g.setFont(fuente);

	variable1=String.valueOf(horas);
	variable2=String.valueOf(minutos);
	variable3=String.valueOf(segundos);
	variable4=String.valueOf(dia);
	variable5=String.valueOf(mes);
	variable6=String.valueOf(ano);

	if(horas<10)
	variable1="0"+variable1;

	if(minutos<10)
	variable2="0"+variable2;

	if(segundos<10)
	variable3="0"+variable3;

	if(dia<10)
	variable4="0"+variable4;
	
	if(mes<10)
	variable5="0"+variable5;

	if(ano<10)
	variable6="000"+variable6;
	
	if(ano>=10 && ano<100)
	variable6="00"+variable6;
	
	if(ano>=100 && ano<1000)
	variable6="0"+variable6;		
	
	g.drawString("    HH      MM     SS      DIA    MES    AÑO ", 14, 50);
	g.drawString("     "+variable1+"   :   "+variable2+ "   :   " +variable3+"       " +variable4+ "   :   " +variable5+ "   :  "+variable6, 14, 90);
}

public void modificarhora(int decide)
{
if(decide==1)
	{
	horas=horas+1;
	if(horas==24)
		{
		horas=0;
		}
	}

else
	{
	horas=horas-1;
	if(horas==-1)
		{
		horas=23;
		}
	}
}

public void modificarminutos(int decide1)
{

if(decide1==1)
	{
	minutos=minutos+1;
	if(minutos==60)
		{
		minutos=0;
		}
	}

else
	{
	minutos=minutos-1;
	if(minutos==-1)
		{
		minutos=59;
		}
	}
}

public void modificarsegundos(int decide2)
{
if(decide2==1)
	{
	segundos1=1;
	segundos=segundos+1;
	if(segundos==60)
		{
		segundos=0;
		}
	}

else
	{
	segundos=segundos-1;
	if(segundos==-1)
		{
		segundos=59;
		segundos1=0;
		}
	}
}

public void modificarmes(int decide3)
{
int control3=bisiesto();
if(decide3==1)
	{
	mes=mes+1;
	if(mes==13)
		{
		mes=1;
		}

	if(mes==4 || mes==6 || mes==9 || mes==11)
		{
                if(dia>30)
			{
			dia=30;
			}
		}
	if(control3==1 && mes==2)
		{
		if(dia>29)
		dia=29;
		}
	if(control3==0 && mes==2)
		{
		if(dia>28)
		dia=28;
		}

	}
else
	{
	mes=mes-1;
	if(mes==0)
		{
		mes=12;
		}
	if(mes==4 || mes==6 || mes==9 || mes==11)
		{
                if(dia>30)
			{
			dia=30;
			}
		}
	if(control3==1 && mes==2)
		{
		if(dia>29)
		dia=29;
		}
	if(control3==0 && mes==2)
		{
		if(dia>28)
		dia=28;
		}
	}
}

public void modificarano(int decide4)
{
int control4;
if(decide4==1)
	{
	ano=ano+1;
	if(ano==10000)
		{
		ano=0;
		}
	control4=bisiesto();
	if(control4==0 && mes==2)
		{
		if(dia>28);
		dia=28;
		}


	}

else
	{
	ano=ano-1;
	if(ano==-1)
		{
		ano=9999;
		}
	control4=bisiesto();
	if(control4==0 && mes==2)
		{
		if(dia>28);
		dia=28;
		}

	}
}

public void modificardia(int decide5)
{
int control2;
control2=bisiesto();
	if(decide5==1)
		{
		dia=dia+1;
		if(mes==4 || mes==6 || mes==9 || mes==11)
				{
				if(dia==31)
				dia=1;
				}
		if(control2==1 && mes==2)
				{
				if(dia==30)
				dia=1;
				}
		if(control2==0 && mes==2)
				{
				if(dia==29)
				dia=1;
				}

		else
				{
				if(dia==32)
				dia=1;
				}
		}

	else
		{
		dia=dia-1;
		if(mes==4 || mes==6 || mes==9 || mes==11)
				{
				if(dia==0)
				dia=30;
				}
		if(control2==1 && mes==2)
				{
				if(dia==0)
				dia=29;
				}
		if(control2==0 && mes==2)
				{
				if(dia==0)
				dia=28;
				}

		else
				{
				if(dia==0)
				dia=31;
				}
		}

}

public void salvarhora()
{
savehoras=horas;
}

public void salvarminutos()
{
saveminutos=minutos;
}

public void salvarsegundos()
{
savesegundos=segundos;
}

public void salvardia()
{
savedia=dia;
}

public void salvarmes()
{
savemes=mes;
}

public void salvarano()
{
saveano=ano;
}

public void resetearhora()
{
horas=savehoras;
}

public void resetearminutos()
{
minutos=saveminutos;
}

public void resetearsegundos()
{
segundos=savesegundos;
}

public void reseteardia()
{
dia=savedia;
}

public void resetearmes()
{
mes=savemes;
}

public void resetearano()
{
ano=saveano;
}

}