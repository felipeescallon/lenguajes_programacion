import java.net.*;
import java.io.*;
import java.lang.*;

class ComunicacionCliente
{
private ServerSocket SocketServicio;							
private Socket SocketCliente;								
private OutputStream FlujodeSalida;							
private InputStream FlujodeEntrada;
private DataOutputStream Flujohaciaservidor;						
private DataInputStream Flujoentrante;
private FileInputStream Ficherosacar;							
private FileOutputStream Ficherometer;
private RecuperarVeredicto Recuperar;
private Registro Definitiva;

public ComunicacionCliente (String Host, int Puerto)					//Instaciamos los socket y los flujos de entrada y salida
{
	try
	{
	SocketCliente = new Socket(Host, Puerto);					//Creamos la instancia SocketCliente de la clase Socket indicando el nodo destino y el puerto
	FlujodeSalida = SocketCliente.getOutputStream();				//Obtenemos el objeto FlujodeSalida de tipo OutputStream 
	Flujohaciaservidor = new DataOutputStream(FlujodeSalida);			//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
	}
	
	catch(UnknownHostException e)
	{
	System.out.println("Referencia a Host no resuleta: "+e.getMessage());
	System.exit(0);
	}
	
	catch(IOException e)
	{
	System.out.println("Error en las comunicaciones: "+e.getMessage());
	System.exit(0);
	}
	
	catch(SecurityException e)
	{
	System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
	System.exit(0);
	}

}



public Registro comunicacion()							//Metodo que Tx los datos introducidos por el usuario para que sean verificados
{										//y recibe el resultado de esta verificacion 
final int TamanoBuffer = 100;
byte buffer [] = new byte[TamanoBuffer];
int NumBytesLeidos = 0;
int NumBytes = 0;


	try									//PROCESO DE ENVIO DE LOS DATOS DEL USUARIO		
	{
	Ficherosacar = new FileInputStream("DatosPersonales.txt");		//Abrimos un flujo de lectura a un archivo donde estan almacenados  
	}									//los datos introdudcidos por el usuario

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
			Flujohaciaservidor.write(buffer,0,NumBytesLeidos);	//Transmitimos los datos leidos
			}while(true);						//hasta que se termine el archivo
		}
	
		catch(ArrayIndexOutOfBoundsException e)
		{
		}
		
	Ficherosacar.close();							//Cerramos el flujo de lectura del archivo 
	SocketCliente.close();							//Cerramos el socket
	}
	
	catch(IOException e)
	{
	System.out.println("Error: "+e.getMessage());
	}
	
	try									//PROCESO DE RECIBIR EL RESULTADO DE LA VERIFICACION
	{
	SocketServicio = new ServerSocket(8001);				//Instaciamos el socket
	SocketCliente = SocketServicio.accept();				//Bloqueamos el programa hasta que se produsca la conexion por parte del servidor con el resultado
	FlujodeEntrada = SocketCliente.getInputStream();			//Obtenemos el objeto FlujodeEntrada de tipo InputStream 
	Flujoentrante = new DataInputStream(FlujodeEntrada);			//Utilizando el objeto anterior creamos un instanica Flujoentrante de tipo DataInputStream
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
	
		
	try									
	{
	Ficherometer = new FileOutputStream("Veredicto.txt"); 			//Creamos un flujo de escritura en un archivo donde se almacenara
										//el resultado de la verificacion 
	try
	{
			try
			{
			do
			{
			NumBytes = Flujoentrante.read(buffer);			//Recibimos la informacion proveniente del servidor	
			Ficherometer.write(buffer,0,NumBytes);			//Escribimos la informacion en un archivo
			}while(true);						//Hasta que no haya mas informacion por recibir 
			}
			
			catch(IndexOutOfBoundsException e)
			{
			}
	
	Ficherometer.close();							//Cerramos los flujos y los sockets
	SocketCliente.close();
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

	try									
	{
	Recuperar = new RecuperarVeredicto();					//Instanciamos la referencia a la clase RecuperarVeredicto 
	Definitiva = Recuperar.RecuperarFveredicto();				//Apuntamos la referencia Definitiva al Objeto que contiene 
	}									//el resultado de la verificacion 

	catch(Exception e)
	{
	System.out.println(e.getMessage());
	}

	return Definitiva;							//Retornamos la referencia a ese reultado

}

}
