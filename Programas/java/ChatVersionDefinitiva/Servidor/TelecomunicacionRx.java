import java.lang.*;						
import java.io.*;
import java.net.*;

class TelecomunicacionRx implements Runnable					//CADA USUARIO TIENE EN SU NODO ESTA CLASE Y EN ELLA CORRIENDO UN HILO EN PARALELO
{										//QUE SE ENCARGA DE LA RECEPCION Y EL ENRUTAMIENTO DE LOS MENSAJES U OTRAS FUNCIONES
private ServerSocket SocketServicio;						
private Socket SocketServidor;							
private InputStream FlujodeEntrada;						
private DataInputStream Flujoentrante;				        	 
private TelecomunicacionTx Transmision;
private Thread HiloRx = null;
private int PuertoAsignar;
private ModificacionEstadoPuerto EstadoPuertoM;
private String Mensaje;
private ClaseLista Lista;

public TelecomunicacionRx(int PuertoAsignar1, TelecomunicacionTx Transmision1, ClaseLista Lista1)
{
	Lista = Lista1;								//Recibimos un referencia a la lista de nodos de usuarios activos, 									
	Transmision = Transmision1;						//una referencia a la clase TelecomunicacionTzx y el puerto
	PuertoAsignar = PuertoAsignar1;						//que le asigno a cada usuario para que realiza la comunicacion
	EstadoPuertoM = new ModificacionEstadoPuerto();
	HiloRx = new Thread(this, "HiloRx");					
	HiloRx.start();								//Hilo encargado de la recepcion permanente de los mensajes escritos por el usuario
}

public void run()
{
int i = 0;	
String Logines[];
	
	try
	{
	SocketServicio = new ServerSocket(PuertoAsignar);			//Instaciamos los sockets de recepcion
	SocketServidor= SocketServicio.accept();				//bloqueamos el programa hata que un cliente se conecte
	FlujodeEntrada = SocketServidor.getInputStream();			//obtenemos el objeto Flujoentrante de tipo InputStream 		
	Flujoentrante = new DataInputStream(FlujodeEntrada);			//utilizamos el objeto FlujodeEntrada para crear una instancia Flujoentrante de tipo DataInputStream
	}

	catch(IOException e)
	{
	System.out.println("Daño en run de Recepcion");
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}

	catch(SecurityException e)
	{
	System.out.println("Comunicacion no permitida por seguridad: "+e.getMessage());
	System.exit(0);
	}	
	
	Thread HiloRxActual=Thread.currentThread();
		while(HiloRx==HiloRxActual)					//Ciclo infinito
		{
			try
			{
			i = Flujoentrante.readInt();				//Leemos el valor de la variable que indica la funcion a realizar o el indice de 
										//la matriz de usuarios receptores				
						
				if(i!=-2 && i!=-5 && i!=-6 && i!=-7)		//SIGNIFICA QUE EL MESNAJE RECIBIDO SE DEBE TRANSMITIR A LOS USUARIOS QUE SELECCIONO EL REMITENTE
				{						//Y LA I SIGNIFICA EL INDICE DE LA MATRIZ DE USUARIOS DESTINO
				Logines = new String[i+1];			//Instanciamos una matriz para recibir los logines de los usuarios destino	

					for(int contador=0; contador<=i; contador++)
					{								//Recibimos los logines de los destinatarios	
					Logines[contador]=Flujoentrante.readUTF();			//Guardamos en una matriz de String
					}
				
				Mensaje = Flujoentrante.readUTF(); 					//Recibimos el mensaje que el usuario desea transmitir 	
									
				Transmision.LanzarHiloTx(Mensaje, Logines,"Ignorado" ,i);		//Hilo que se encarga de la Tx del mensaje a los destinatarios finales
				}
			
				if(i==-5)								//(i==-5) SIGNIFICA QUE UN O VARIOS USUARIOS VAN A SER EXCLUIDOS DEL CHAT 
				{
				int j = Flujoentrante.readInt();					//Recibimos el indice de la matriz de usuarios que van a ser excluidos 
				Logines = new String[j+1];						//Instaciamos un amatriz con ese indice
					
					for(int contador=0; contador<=j; contador++)
					{								//Recibimos los logines de los usuarios a ser excluidos		
					Logines[contador]=Flujoentrante.readUTF();			//LLenamos la matriz con los logines de los usuarios a excluir		
					}
				
				Mensaje = Flujoentrante.readUTF();					//Recibimos el mensaje que se les imprimira en pantalla
								
				Transmision.ExcluirHiloTx(Mensaje, Logines, j, -5); 			//Hilo que se encarga de la transmision de las ordenes para realizar la exclusion  
				}
				
													//TRANSMISION (VARIOS A UNO)
				if(i==-6)								//(i=-6) SIGNIFICA QUE EL MENSAJE QUE LLEGA SE DEBE ENVIAR A TODOS LOS INTEGRANTES ACTIVOS DEL OTRO GRUPO
				{						
				Logines = new String[0]; 
				String CampoLogin = Flujoentrante.readUTF();				//Recibimos el login del usuario lider del grupo remitente
				Mensaje = Flujoentrante.readUTF();					//Recibimos el mensaje que se le enviara a todos los demas usuarios del grupo opuesto
				Transmision.LanzarHiloTx(Mensaje, Logines, CampoLogin, -6);		//Metodo que permite realizar la Tx del mensaje
				}	
			
				if(i==-7)								//(i==-7) SIGNIFICA QUE UN USUARIO QUIERE ACTUALIZAR SUS DATOS
				{
				String CampoLoginActualizar = Flujoentrante.readUTF();			//Recibimos el login del usuariO que desea actualizarse	
				Transmision.ActualizarHiloTx(CampoLoginActualizar);			//Hilo que se encarga de realizar la Tx de los datos de usuario que estan almacenados en la base de datos
				EstadoPuertoM.ModificarEstadoPuerto(CampoLoginActualizar);		//Desactivamos al usuario que se va arealizar la actualizacion y liberamo los puertos que estaba usando 				
				SocketServidor.close();							//Cerramos el socket de recepcion pues la sesion del usuario se cierra y no habran mas recepciones de mensajes
				SocketServicio.close();
				Lista.AgregarQuitarLoginLista(CampoLoginActualizar, -4);		//Quitamos a el login del usuario a actualizarse de la pantalla de todos los demas usuarios activos
				Lista.EliminarNodo(CampoLoginActualizar);				//Eliminamos el nodo del usuario de la lista de nodos de usuarios activos
				break;
				}

				
				if(i==-2)								//(i==-7) SIGNIFICA QUE UN USUARIO DESEA CERRAR SU SESION
				{
				String CampoLogin = Flujoentrante.readUTF();				//Recibimos el login del usuariO que desea cerrar la sesion			
				Transmision.CerrarHiloTx(CampoLogin, i);				//Hilo que se encarga de Tx la orden para que se le cieere la sesion al usuario
				EstadoPuertoM.ModificarEstadoPuerto(CampoLogin); 			//Desactivamos al usuario que se va arealizar la actualizacion y liberamo los puertos que estaba usando 
				SocketServidor.close();							//Cerramos el socket de recepcion pues la sesion del usuario se cierra y no habran mas recepciones de mensajes
				SocketServicio.close();
				Lista.AgregarQuitarLoginLista(CampoLogin, -4);				//Quitamos a el login del usuario a actualizarse de la pantalla de todos los demas usuarios activos
				Lista.EliminarNodo(CampoLogin);						//Eliminamos el nodo del usuario de la lista de nodos de usuarios activos
				break;
				}								

		
			}
			catch(IOException e)
			{
				try
				{
				System.out.println("Daño en run de Recepcion1");
				System.out.println("Error en las comunicaciones: "+e.getMessage());
				SocketServidor.close();							//Cerramos el socket de recepcion pues la sesion del usuario se cierra y no habran mas recepciones de mensajes
				SocketServicio.close();
				break;
				//System.exit(0);
				}
				
				catch(IOException y)
				{
				System.out.println("Dano interno de Recepcion1.1");
				}
			
				catch(SecurityException y)
				{
				System.out.println("Comunicacion no permitida por seguridad: "+e.getMessage());
				}
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







