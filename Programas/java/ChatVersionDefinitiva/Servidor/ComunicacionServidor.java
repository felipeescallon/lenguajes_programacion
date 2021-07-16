import java.lang.*;
import java.io.*;
import java.net.*;

class ComunicacionServidor 
{
private ServerSocket SocketServicio;						
private Socket SocketServidor;							
private InputStream FlujodeEntrada;						
private OutputStream FlujodeSalida;
private DataInputStream Flujoentrante;				        	
private DataOutputStream Flujohaciacliente;
private FileInputStream Ficherosacar;
private FileOutputStream Ficherometer;
private EquipoServidor Recuperar;

public ComunicacionServidor(int Puerto)			
{
	try
	{
	SocketServicio = new ServerSocket(Puerto);				//Instanciamos los sockets  
	SocketServidor= SocketServicio.accept();				//Bloqueamos el programa hasta que se produsca la conexion por parte del cliente con los datos de usuario 
	FlujodeEntrada= SocketServidor.getInputStream();			//obtenemos el Stream de entrada asociado al socket que acabamos de conseguir  
	Flujoentrante = new DataInputStream(FlujodeEntrada);			//utilizamos el objeto FlujodeEntrada para crear una instancia Flujoentrante de tipo DataInputStream
	}

	catch(IOException e)
	{
	System.out.println("Daño en comunicacionservidor 1");
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}	
	
	catch(SecurityException e)
	{
	System.out.println("Comunicacion no permitida por seguridad: "+e.getMessage());
	System.exit(0);
	}

}

public void TratarAcceso(ClaseLista Lista)
{
final int TamanoBuffer = 100;
byte buffer[] = new byte[TamanoBuffer]; 
int NumBytes = 0;
int NumBytesLeidos = 0;
String CadenaIP = null;

	try									 //PROCESO DE RECIBIR EL LOS DATOS DEL USUARIO						
	{
	Ficherometer = new FileOutputStream("DatosPersonales.txt"); 		 //Creamos un flujo de escritura en un archivo donde se almacenaran lo  
										 //datos introducidos por el usuario
	try
	{
			try
			{
			do
			{
			NumBytes = Flujoentrante.read(buffer);			//Recibimos la informacion proveniente del cliente	
			Ficherometer.write(buffer,0,NumBytes);			//Escribimos la informacion en un archivo
			}while(true);						//Hasta que no haya mas informacion por recibir 
			}
			
			catch(IndexOutOfBoundsException e)
			{
			}
	
	Ficherometer.close();							//Cerramos los flujos y los sockets
	CadenaIP = SocketServidor.getInetAddress().toString();			//Obetenemos la direccion IP del cliente	
	int Indice = CadenaIP.indexOf('/');					
										
		if(Indice!=-1)
		{
		CadenaIP = CadenaIP.substring(Indice+1,CadenaIP.length());	//Limpiamos la basura con que el sistmea optiene la 			
		}								//la direccion IP 
	
	SocketServidor.close();							//Cerramos los sockets de recepcion 	
	SocketServicio.close();
	}	
	
	catch(IOException e) 		
	{
	System.out.println("Error de entrada/salida: "+e.getMessage());	
	}	

	}
	catch(FileNotFoundException e)
	{
	System.out.println("Fichero no encontrado: "+e.getMessage());
	}
	
	Recuperar = new EquipoServidor();					//ANALISIS DE LOS DATOS INTRODUCIDOS POR EL USUARIO
	Recuperar.Operaciones(Lista, CadenaIP);
	
	try									//PROCESO DE TRANSMITIR EL RESULTADO DE LA VERIFICACION						
	{
	SocketServidor = new Socket(CadenaIP,8001);				//Creamos la instancia SocketCliente de la clase Socket indicando el nodo destino y el puerto
	FlujodeSalida = SocketServidor.getOutputStream(); 			//Obtenemos el objeto FlujodeSalida de tipo OutputStream 		
	Flujohaciacliente = new DataOutputStream(FlujodeSalida);		//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
	}

	
	catch(UnknownHostException e)
	{
	System.out.println("Referencia a Host no resuleta: "+e.getMessage());
	System.exit(0);
	}
	
	catch(IOException e)
	{
	System.out.println("Daño comunicacion 2");
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}
	

	try										
	{
	Ficherosacar = new FileInputStream("Veredicto.txt");			//Abrimos un flujo de lectura a un archivo donde esta  
	}									//almacendo el resultado de la verificacion

   	catch(FileNotFoundException e)
	{
	System.out.println("Fichero no encontrado: "+e.getMessage());
	}

	try
	{
		try
		{
			do
			{
			NumBytesLeidos=Ficherosacar.read(buffer);		//Leemos los datos provenientes del archivo	
			Flujohaciacliente.write(buffer,0,NumBytesLeidos);	//Transmitimos los datos leidos
			}while(true);						//hasta que se termine el archivo
		}
	
		catch(ArrayIndexOutOfBoundsException e)
		{
		}
		
	Ficherosacar.close();							//Cerramos el flujo de lectura del archivo 
	SocketServidor.close();							//Cerramos el socket
	}
	
	catch(IOException e)
	{
	System.out.println("Error: "+e.getMessage());
	}

}

}
