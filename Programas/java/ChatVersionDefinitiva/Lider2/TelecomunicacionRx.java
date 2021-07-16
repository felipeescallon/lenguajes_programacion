import java.lang.*;						
import java.io.*;
import java.net.*;
import java.awt.*;

class TelecomunicacionRx implements Runnable
{
private ServerSocket SocketServicio;						
private Socket SocketServidor;							
private InputStream FlujodeEntrada;						
private DataInputStream Flujoentrante;				        	
private FileOutputStream Ficherometer;
private int Puerto;
private String Mensaje;
private List ListaUsuarios;
private TelecomunicacionTx ExcluirTx;
private TextArea AreaMensajeEntrante;
private Thread HiloRx = null;
private InterfazdeDatos Actualizar;
private InterfazdeChat Abrir;
private Frame MarcoPrincipal;
private RestriccionChat Vacio;

public TelecomunicacionRx(Registro Veredicto, TextArea AreaMensajeEntrante1, List ListaUsuarios1, TelecomunicacionTx InstanciaEnviar, Frame MarcoPrincipal1) 	
{
	MarcoPrincipal = MarcoPrincipal1;					//Recibimos la referencia a los resutados de la verificacion, el area de texto a imprimir	
	ExcluirTx = InstanciaEnviar;						//la lista de usuarios activos, una referencia a la clase TelecomunicacionTX y el marco de la
	ListaUsuarios = ListaUsuarios1;						//interfaz de Chat
	AreaMensajeEntrante = AreaMensajeEntrante1;
	Puerto = Veredicto.RetornarPuertoA() + 1;
	HiloRx = new Thread(this, "HiloRx");					
	HiloRx.start();								//Hilo encargado permanenetemente de recibir la informacion proveniente del Servidor
}

public void run()							        //Metodo encargado de recibir lo mensajes provenientes del servidor
{
int Mostrar=0;

	Thread HiloRxActual=Thread.currentThread();
	while(HiloRx==HiloRxActual)						//Ciclo infinito y permanente
	{
		try
		{
		SocketServicio = new ServerSocket(Puerto);			//Instaciamos los sockets de recepcion
		SocketServidor= SocketServicio.accept();			//Bloqueamos el programa hasta que el servidor se conecte para la transmision de algun mensaje
		FlujodeEntrada = SocketServidor.getInputStream();		//obtenemos el objeto Flujoentrante de tipo InputStream 		
		Flujoentrante = new DataInputStream(FlujodeEntrada);		//utilizamos el objeto FlujodeEntrada para crear una instancia Flujoentrante de tipo DataInputStream
				
		int i = Flujoentrante.readInt();				//Leemos la variable que indica la funcion que debe realizar el sistema del cliente
		
			if(i==-5)						//Si (i==-5) SIGNIFICA QUE EL USUARIO VA A SER EXLCUIDO POR EL SERVIDOR
			{
			Mensaje = Flujoentrante.readUTF();			//Recibimos el mensaje de exlcusion
			AreaMensajeEntrante.append(Mensaje +"\n"); 		//Imprimimos el mensaje de exclusion en el area de texto
			ExcluirTx.ExcluirCorrer();				//llamamos al metodo que transmite la falsa informacion al servidor para que este cierre la sesion
			Mostrar=1;						//Para que muestre el cuadro de dialogo de exclusion por parte del servidor
			SocketServidor.close();					//Cerramos los sockets de Rx para que se instancien unos nuevos 				
			SocketServicio.close();					//encargados de recibir la informacion de la siguiente accion a realizarse 
			}
			
			if(i==-4)						//Si (i==-4) SIGNIFICA QUE UN USUARIO ACTIVO HA PASADO AL ESTADO INACTIVO Y DEBE SER BORRADO DE LA LISTA DE USUARIOS						
			{
			Mensaje = Flujoentrante.readUTF();			//Recibimos el login a borrar
			int Indice=0; 	
				while(Indice!=-1)				//Buscamos el login del usuario en la lista de usuarios
				{
				String LoginRetorno=ListaUsuarios.getItem(Indice);
					if(LoginRetorno.compareTo(Mensaje)==0)
					{
					ListaUsuarios.delItem(Indice);		 //y lo borramos	
					break;
					} 
				Indice++;
				}
				
			SocketServidor.close();					//Cerramos los sockets de Rx para que se instancien unos nuevos 				
			SocketServicio.close();					//encargados de recibir la informacion de la siguiente accion a realizarse
			}			
			

			
			if(i==-3)						//Si (i==-4) SIGNIFICA QUE UN USUARIO HA SIDO ACTIVADO Y DEBE SER AGREGADO EN LA LISTA DE USUARIOS									
			{
			Mensaje = Flujoentrante.readUTF();			//Recibimos el login a agregar
			ListaUsuarios.addItem(Mensaje);				//Agregamos el login en la lista de usuarios
			SocketServidor.close();					//Cerramos los sockets con el mismo objetivo que se ha realizado el cierre en los anteriores casos
			SocketServicio.close();
			}
			
			if(i==-2)						//Si (i==-2) SIGNIFICA QUE EL USUARIO HA DECIDIDO CERRAR LA SESION									
			{							//Cerramos los sockets ya que no va haber mas recepcion 
			SocketServidor.close();
			SocketServicio.close();					
			break;							//Cerramos el ciclo infinito de recepcion por no la va haber mas
			}
			
			if(i==-7)						//Si (i==-7) SIGNIFICA QUE UN USUARIO HA DECIDIDO ACTUALIZAR SUS DATOS									
			{
			final int TamanoBuffer = 100;
			byte buffer [] = new byte[TamanoBuffer];
			int NumBytes = 0;
						
				try
				{
				Ficherometer = new FileOutputStream("DatosPersonales.txt"); 	//Abrimos un flujo de escritura en un archivo para recibir sus datos personales
				

					try
					{
						try
						{
						do					
						{
						NumBytes = Flujoentrante.read(buffer);		//Recibimos la informacion de los datos de usuario que seran modificados
						Ficherometer.write(buffer,0,NumBytes);		//Escribimos en el archivo la informacion revibida	
						}while(true);					//Hasta que no haya mas informacion 
						}
			
						catch(IndexOutOfBoundsException e)
						{
						}
	
					Ficherometer.close();					//Cerramos los flujos y los sockets
					SocketServidor.close();
					SocketServicio.close();
					}	
			
			
					catch(IOException e) 		
					{
					System.out.println("Error de entrada/salida: "+e.getMessage());	
					}	

				}
			
				catch(FileNotFoundException e)
				{
				System.out.println("Fichero no encontrado:" +e.getMessage());
				}
				
				Mostrar=2;							
			break;									//Cerramos el ciclo infinito de recepcion por no la va haber mas mientras el usuario 
			}									//actualiza sus datos
			
			if(i!=-2 && i!=-3 && i!=-4 && i!=-5 && i!=-7)				//SINO ES NINGUNO DE LOS CASOS ANTERIORES SIGNIFICA QUE OTRO USUARIO LE HA ESCRITOUN MENSAJE Y 
			{									//SE LE IMPRIME EN PANTALLA
			Mensaje = Flujoentrante.readUTF();					//Leemos el mensaje a mostrar
			AreaMensajeEntrante.append(Mensaje +"\n"); 				//Mostramos el mesaje	
			SocketServidor.close();							//Cerramos los sockets para que unos nuevoa reciban el la informacion de la proxima accion a 	
			SocketServicio.close();							//realizarse
			}
				

		}
		catch(IOException e)
		{
		System.out.println("Error en las comunicaciones: "+e.getMessage());
		System.exit(0);
		}

		catch(SecurityException e)
		{
		System.out.println("Comunicacion no permitida por seguridad: "+e.getMessage());
		System.exit(0);
		}	
	}

	if(Mostrar==1)										//(Mostrar=1) Significa que fue excluido por el servidor y mostramos el rspectivo cuadro de dialogo
	{
	Vacio = new RestriccionChat(MarcoPrincipal,"Excluido por el servidor");
	MarcoPrincipal.hide();
	Abrir = new InterfazdeChat();
	}
	
	if(Mostrar==2)										//(Mostrar==2) Significa que el usuario queiere actualizar sus datos y le mostramos la Interfaz de Actualizar
	{
	MarcoPrincipal.hide();
	Actualizar = new InterfazdeDatos(1);
	}

	if(Mostrar==0)										//(Mostrar==0) Significa que el usuario cerro la sesion y mostramos la interfaz de chat
	{
	MarcoPrincipal.hide();
	Abrir = new InterfazdeChat();
	}


}		

public void stop()
{	
HiloRx=null;
}

}







