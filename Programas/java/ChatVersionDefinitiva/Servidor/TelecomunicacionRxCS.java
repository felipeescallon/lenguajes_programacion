import java.lang.*;						
import java.io.*;
import java.net.*;
import java.awt.*;

class TelecomunicacionRxCS implements Runnable
{
private ServerSocket SocketServicio;						
private Socket SocketServidor;							
private InputStream FlujodeEntrada;						
private DataInputStream Flujoentrante;				        	
private String Mensaje;
private List ListaUsuarios;
private TextArea AreaMensajeEntrante;
private Thread HiloRx = null;

public TelecomunicacionRxCS(TextArea AreaMensajeEntrante1, List ListaUsuarios1)
{
	ListaUsuarios = ListaUsuarios1;							//Recibimos el area de texto a imprimir y  	
	AreaMensajeEntrante = AreaMensajeEntrante1;					//la lista de usuarios activos 
	HiloRx = new Thread(this, "HiloRx");						//Hilo encargado permanenetemente de recibir la informacion proveniente del Servidor como sistema				
	HiloRx.start();								
}

public void run()									//Metodo encargado de recibir lo mensajes que le escriben al servidor
{
	Thread HiloRxActual=Thread.currentThread();
	while(HiloRx==HiloRxActual)							//Ciclo infinito y permanente			
	{
		try
		{
		SocketServicio = new ServerSocket(8003);				//Instaciamos los sockets de recepcion
		SocketServidor= SocketServicio.accept();				//Bloqueamos el programa hasta que el sistema servidor se conecte para la transmision de algun mensaje
		FlujodeEntrada = SocketServidor.getInputStream();			//obtenemos el objeto Flujoentrante de tipo InputStream 			
		Flujoentrante = new DataInputStream(FlujodeEntrada);			//utilizamos el objeto FlujodeEntrada para crear una instancia Flujoentrante de tipo DataInputStream
				
		int i = Flujoentrante.readInt();					//Leemos la variable que indica la funcion que debe realizar el servidor com cliente
					
			if(i==-4)							//Si (i==-4) SIGNIFICA QUE UN USUARIO ACTIVO HA PASADO AL ESTADO INACTIVO Y DEBE SER BORRADO DE LA LISTA DE USUARIOS						
			{
			Mensaje = Flujoentrante.readUTF();				//Recibimos el login a borrar
			int Indice=0; 	
				while(Indice!=-1)					//Buscamos el login del usuario en la lista de usuarios
				{
				String LoginRetorno=ListaUsuarios.getItem(Indice);
					if(LoginRetorno.compareTo(Mensaje)==0)
					{
					ListaUsuarios.delItem(Indice);			//y lo borramos	
					break;
					} 
				Indice++;
				}
				
			SocketServidor.close();						//Cerramos los sockets de Rx para que se instancien unos nuevos 					
			SocketServicio.close();						//encargados de recibir la informacion de la siguiente accion a realizarse
			}			
			

			
			if(i==-3)							//Si (i==-3) SIGNIFICA QUE UN USUARIO HA SIDO ACTIVADO Y DEBE SER AGREGADO EN LA LISTA DE USUARIOS												
			{
			Mensaje = Flujoentrante.readUTF();				//Recibimos el login a agregar
			ListaUsuarios.addItem(Mensaje);					//Agregamos el login en la lista de usuarios
			SocketServidor.close();						//Cerramos los sockets con el mismo objetivo que se ha realizado el cierre en el anterior caso
			SocketServicio.close();
			}
			
			
			if(i!=-3 && i!=-4)						//SINO ES NINGUNO DE LOS CASOS ANTERIORES SIGNIFICA QUE OTRO USUARIO LE HA ESCRITO UN MENSAJE Y 
			{								//SE LE IMPRIME EN PANTALLA				
			Mensaje = Flujoentrante.readUTF();				//Leemos el mensaje a mostrar
			AreaMensajeEntrante.append(Mensaje +"\n"); 			//Mostramos el mesaje	
			SocketServidor.close();						//Cerramos los sockets para que unos nuevoa reciban el la informacion de la proxima accion a 	
			SocketServicio.close();						//realizarse
			}
				

		}
		catch(IOException e)
		{
		System.out.println("Daño Recepcion Servidor");
		System.out.println("Error en las comunicaciones: "+e.getMessage());
		System.exit(0);
		}

		catch(SecurityException e)
		{
		System.out.println("Comunicacion no permitida por seguridad: "+e.getMessage());
		System.exit(0);
		}	
	}

}		

public void stop()
{	
HiloRx=null;
}

}







