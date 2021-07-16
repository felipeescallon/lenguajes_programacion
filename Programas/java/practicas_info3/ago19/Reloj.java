/*  PROGRAMA CREADO POR: FELIPE ESCALLON Y DIEGO MANQUILLO
 *  INFORMATICA 3
 *  JAVA 2!
 *  AGO/19/04
 */

class Reloj
{
private
int hor, min, seg;			//declaramos las variables;

public Reloj() 				// Constructor que inicializa atributos del objeto
{
	hor=0;
	min=0;
	seg=0;
	
}

public void logica()  			// Incremento del valor de horas minutos y segundos 

 {

  	if(seg>=59)
	{
	seg=0;
		if(min>=59)
		{
			min=0;
			if(hor>=23)
			{
			hor=0;
			}

			else
			{ 
			hor=hor+1;
			}

		}

		else
		{ 
		min=min+1;
		}
	
	}

	else
	{ 
	seg=seg+1;
	}	
 
}




public void fijar_valor(int h, int m, int s)  // Asigna una hora especifica
{

	hor=h;
  min=m;
	seg=s;
}




 public void mostrar()  					// Muestra la hora en formato HH:MM:SS
 {
	if(hor<10)                      
	System.out.print("0"+hor);      //TO AVOID OVERWRITING WHEN \r(CAR RETURN)
	else
	System.out.print(hor);

	if(min<10)
	System.out.print(" : 0"+min);//TO AVOID OVERWRITING WHEN \r(CAR RETURN)
	else
	System.out.print(" : "+min);//TO AVOID OVERWRITING WHEN \r(CAR RETURN)

	if(seg<10)
	System.out.print(" : 0"+seg+ "\r");//TO AVOID OVERWRITING WHEN \r(CAR RETURN)
	else
	System.out.print(" : "+seg+ "\r");
	

 }



}