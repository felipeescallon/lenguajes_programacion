import java.lang.*;
import java.io.*;

class Animovimiento implements Runnable
{
private Thread hiloacceso=null;
private ComunicacionServidor RecibirDatos;
private ClaseLista Lista; 

public Animovimiento(ClaseLista Lista1)			
{
	Lista = Lista1;							//Recibimos la referencia a la Lista
	hiloacceso=new Thread(this, "hiloacceso");			//objeto de la clase Thread
	hiloacceso.start();						//el hilo ejecuta el metodo run de hilo1
}

public void run()
{
	
	Thread hiloaccesoActual=Thread.currentThread();
	while(hiloacceso==hiloaccesoActual)				//ejecutamos el hilo hata que valga null				
	{
	RecibirDatos = new ComunicacionServidor(8000);			//Instaciamos los sockets de recepcion de peticiones de acceso		
	RecibirDatos.TratarAcceso(Lista);				//Recibimos la informacion del usuariom la verificamos y  
	}								//devolvemos el resultado
}

public void stop()
{
hiloacceso=null;							//deja al hilo en estado inactivo
}

}
