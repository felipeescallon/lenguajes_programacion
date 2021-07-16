import java.lang.*;									
import java.io.*;
import java.net.*;
import java.sql.*;

public class TelecomunicacionTx implements Runnable 
{
private String BasedeDatos, Login, LoginBD, Mensaje, CLogin;
private String[] Logines;
private Connection Conexion;
private Statement SentenciaSQL;
private ResultSet Personas;
private Socket SocketCliente;								
private OutputStream FlujodeSalida;					
private DataOutputStream Flujohaciaservidor;
private FileInputStream Ficherosacar;
private int PuertoAsignar, i;
private String CadenaIP;
private Thread HiloTx = null;
private Cliente DatosCliente;
private GuardarCliente DatosGuardarCliente;

public TelecomunicacionTx()
{
}

public void LanzarHiloTx(String Mensaje1, String[] Logines1, String CLogin1, int i1)
{
Mensaje = Mensaje1;									//Recibimos el mensaje a transmiteir, los remitentes y								 	
Logines = Logines1;									//la lista de destinataris con su respectivo indice
CLogin = CLogin1;
i = i1;
HiloTx = new Thread(this, "HiloTx");							//Hilo que realiza el enrutamiento y envio 
HiloTx.start();										//de la informacion escrita por el cliente
}


public void run()
{
	if(i==-6)									//Si el envio es: VARIOS A UNO			
	{
	int Grupo=0;
	
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		BasedeDatos = "jdbc:odbc:Modificacion";
		Conexion = DriverManager.getConnection(BasedeDatos);
		SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
		Login = CLogin;	
	
			while(Personas.next())
			{	
			LoginBD = Personas.getString("Login");				//Buscamos el login del lider del grupo remitente en la base de datos
			
				if(Login.compareTo(LoginBD)==0)				//Si el login del lider remitente coincide con el login almacenado en 
				{							//base de datos
				Grupo = Personas.getInt("Grupo");			//Obtenemos el grupo al cual pertenece el remitente	
				break;
				}
			}
			boolean posible = Personas.first();				//Volvemos a la primera posicion
		}
	
		catch(ClassNotFoundException e)
		{
		System.out.println("Clase no encontrada");
		}
	
		catch(SQLException e)
		{
		System.out.println(e);	
		}
	
					
		try
		{
			while(Personas.next())
			{
			int Grupodestino = Personas.getInt("Grupo");				//Obtenemos el grupo y el estado al que pertenece
			int EstadoA = Personas.getInt("Estado");				//cada usuario activo
				if(Grupodestino!=Grupo && Grupodestino!=0 && EstadoA ==1)		//Si el usuario es del grupo contrario al del remitente
				{								
					try
					{
					String MensajeE = " << Grupo ".concat(String.valueOf(Grupo).concat( ">>:   ".concat(Mensaje)));  //Formamos el mensaje compelto a transmitir anteponiendole la etiqueta de remitente
					PuertoAsignar = Personas.getInt("Puerto");			//Obtenemos el puerto y la direccion del destinatario	
					CadenaIP = Personas.getString("CadenaIP");
					SocketCliente = new Socket(CadenaIP, PuertoAsignar+1);		//Instaciamos los sockets de transmision
					FlujodeSalida = SocketCliente.getOutputStream();		//Obtenemos el objeto FlujodeSalida de tipo OutputStream 
					Flujohaciaservidor = new DataOutputStream(FlujodeSalida);	//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
					Flujohaciaservidor.writeInt(0);					//Mandamos el indice=0 para identificar que es una transmision Varios a uno   
					Flujohaciaservidor.writeUTF(MensajeE); 				//Escribimos el mensaje
					SocketCliente.close();						//Cerramos los sockets
					}
				
					catch(UnknownHostException e)
					{
					System.out.println("Referencia a Host no resuleta: "+e.getMessage());
					System.exit(0);
					}
		
					catch(IOException e)
					{
					System.out.println("Daño en run de tx varios a uno");
					System.out.println("Error en las comunicaciones: "+e.getMessage());
					System.exit(0);
					}
						
					catch(SecurityException e)
					{
					System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
					System.exit(0);
					}
				}
			}
		
		Personas.close();									//Liberacion explicita de los recursos empleados
		Conexion.close();
		SentenciaSQL.close();	
		
		}
		
		catch(SQLException e)
		{
		System.out.println(e);	
		}

	
	}
	
	else										//Si el envio es: UNO A UNO, UNO A VARIOS y UNO A TODOS			
	{
	int contador = 0;
	
		while(contador<=i)								
		{	
			try
			{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			BasedeDatos = "jdbc:odbc:Modificacion";
			Conexion = DriverManager.getConnection(BasedeDatos);
			SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
			Login = Logines[contador];								//Obtenemos los logines destino señalados por el usuario	
				
				while(Personas.next())									
				{	
				LoginBD = Personas.getString("Login");						//Obtenemos los logines de la base de datos

					if(Login.compareTo(LoginBD)==0)						//Si los logines coinciden
					{
					PuertoAsignar = Personas.getInt("Puerto");				//Obtenemos el puerto que tiene asocido el usuario destino y su 
					CadenaIP = Personas.getString("CadenaIP");				//direccion de destino
					
						try
						{
						SocketCliente = new Socket(CadenaIP, PuertoAsignar+1);		//Instanciamos los sockets
						FlujodeSalida = SocketCliente.getOutputStream();		//obtenemos el objeto FlujodeSalida de tipo OutputStream 
						Flujohaciaservidor = new DataOutputStream(FlujodeSalida);	//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
						Flujohaciaservidor.writeInt(i);					//Mandamos el indice de la matriz de usuarios selecionados
						Flujohaciaservidor.writeUTF(Mensaje); 				//Mandamos el mensaje escrito por el remitente
						SocketCliente.close();						//Cerramos los sockets
						}
	
						catch(UnknownHostException e)
						{
						System.out.println("Referencia a Host no resuleta: "+e.getMessage());
						System.exit(0);
						}
		
						catch(IOException e)
						{
						System.out.println("Daño uno a uno");
						System.out.println("Error en las comunicaciones: "+e.getMessage());
						System.exit(0);
						}	
			
						catch(SecurityException e)
						{
						System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
						System.exit(0);
						}

					break;
					}
				
				}

			boolean posible = Personas.first();		//Volvemos de nuevo al primer registro de la base de datos 					
			contador=contador+1;
				
			Personas.close();				//Liberacion explicita de los recursos empleados
			Conexion.close();
			SentenciaSQL.close();
			}
			
			catch(ClassNotFoundException e)
			{
			System.out.println("Clase no encontrada");
			}
	
			catch(SQLException e)
			{
			System.out.println(e);	
			}

		}
	}
}
	

public void ExcluirHiloTx(String Mensajex, String[] MatrizLoginex, int i, int j)	//Recibimos el mensaje que se le imprimira en pantalla al usuario cuando ha sido excluido por el servidor,	
{											//Recibimos la matriz de logines a excluir, su indice y el numero que significa que el usuario sera excluido	
int contador = 0;
	
	while(contador<=i)								//ejecutamos el ciclo hasta el ultimo de los destinatarios
	{	
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		BasedeDatos = "jdbc:odbc:Modificacion";
		Conexion = DriverManager.getConnection(BasedeDatos);
		SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
		Login = MatrizLoginex[contador];					//Obtenemos el login del usuario a desactivar
		
			while(Personas.next())						
			{	
			LoginBD = Personas.getString("Login");				//Obtenemos los logines almacenados en la base de datos

				if(Login.compareTo(LoginBD)==0)				//Si los logines coinciden obtenemos el puerto y el destino del
				{							//usuario a desactivar
				PuertoAsignar = Personas.getInt("Puerto");
				CadenaIP = Personas.getString("CadenaIP");	
					
					try
					{
					SocketCliente = new Socket(CadenaIP, PuertoAsignar+1);		//Instanciamos los sockets de transmision
					FlujodeSalida = SocketCliente.getOutputStream();		//obtenemos el objeto FlujodeSalida de tipo OutputStream 
					Flujohaciaservidor = new DataOutputStream(FlujodeSalida);	//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
					Flujohaciaservidor.writeInt(j);					//Mandamos la variable que identifca el equipo del cliente que se va ha cerrar la sesion	
					Flujohaciaservidor.writeUTF(Mensajex); 				//Mandamos el mensaje en el que se le inica que sera excluido
					SocketCliente.close();						//Cerramos los sockets
					}
	
					catch(UnknownHostException e)
					{
					System.out.println("Referencia a Host no resuleta: "+e.getMessage());
					System.exit(0);
					}
		
					catch(IOException e)
					{
					System.out.println("Tamos paila en excluir");
					System.out.println("Error en las comunicaciones: "+e.getMessage());
					System.exit(0);
					}
			
					catch(SecurityException e)
					{
					System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
					System.exit(0);
					}

				break;
				}
				
			}

		boolean posible = Personas.first();									//Retornamos a al primer registro de la base de datos
		contador=contador+1;
		
		Personas.close();								//Liberacion explicita de los recursos empleados
		Conexion.close();
		SentenciaSQL.close();		
		}
	
		catch(ClassNotFoundException e)
		{
		System.out.println("Clase no encontrada");
		}
	
		catch(SQLException e)
		{
		System.out.println(e);	
		}

	}

}

public void CerrarHiloTx(String CampoLogin, int i)						//Recibimos el login del usuario que desea cerrar la sesion	
{												//y la variable que indica que se el usuario desea cerrar la sesion
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		BasedeDatos = "jdbc:odbc:Modificacion";
		Conexion = DriverManager.getConnection(BasedeDatos);
		SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
		
		Login = CampoLogin;	
	
		while(Personas.next())
		{	
		LoginBD = Personas.getString("Login");							//Buscamos el login del usuario que desea cerrar la sesion
		
			if(Login.compareTo(LoginBD)==0)							//Si los logines coinciden
			{
			PuertoAsignar = Personas.getInt("Puerto");					//Obtenemos el puerto y la direccion de destino del cliente
			CadenaIP = Personas.getString("CadenaIP");	
			
				try
				{
				SocketCliente = new Socket(CadenaIP, PuertoAsignar+1);			//Instaciamso los Sokets
				FlujodeSalida = SocketCliente.getOutputStream();			//obtenemos el objeto FlujodeSalida de tipo OutputStream 
				Flujohaciaservidor = new DataOutputStream(FlujodeSalida);		//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
				Flujohaciaservidor.writeInt(i);						//Transmitimos la variable que indica que el usuario desea cerrar sesion
				SocketCliente.close();							//Cerramos los sockets
				}
	
				catch(UnknownHostException e)
				{
				System.out.println("Referencia a Host no resuleta: "+e.getMessage());
				System.exit(0);
				}
		
				catch(IOException e)
				{
				System.out.println("Estamos paila en cerrar");
				System.out.println("Error en las comunicaciones: "+e.getMessage());
				System.exit(0);
				}
			
				catch(SecurityException e)
				{
				System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
				System.exit(0);
				}

			break;
			}
		}
	
	Personas.close();								//Liberacion explicita de los recursos empleados
	Conexion.close();
	SentenciaSQL.close();
	}

	catch(ClassNotFoundException e)
	{
	System.out.println("Clase no encontrada");
	}
	
	catch(SQLException e)
	{
	System.out.println(e);	
	}
	
}
	

public void AgregarQuitarLoginTx(String Destino, String LoginTx, int Tipo)		//Recibimos el login de destino, el login a eliminar o agregar y		
{											//la varialbe que me indica si el login se debe agregar o eliminar
Connection Conexionx;					//Definimos nuevas variables para optimizar los recursos y 											
Statement SentenciaSQLx;				//así evitar que por casualida los hilos que utilizan metodos diferentes a este
ResultSet Personasx;					//tenga ocupadas dichas varialbes cuando el hilo que utiliza este metodo se ejecute
Socket SocketClientex;								
OutputStream FlujodeSalidax;					
DataOutputStream Flujohaciaservidorx;
FileInputStream Ficherosacarx;
int PuertoAsignarx, i;
String CadenaIPx;
	
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	BasedeDatos = "jdbc:odbc:Modificacion";
	Conexionx = DriverManager.getConnection(BasedeDatos);
	SentenciaSQLx = Conexionx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	Personasx = SentenciaSQLx.executeQuery("SELECT * FROM DatosPersonales");
	String Loginx = Destino;							//Obtenemos el login del usuario al cual debemos modificarse su lista de usuarios activos
											//en su interfaz principal
		while(Personasx.next())
		{	
		String LoginBDx = Personasx.getString("Login");				//Obtenemos los logines almacenado en la base de datos
		
			if(Loginx.compareTo(LoginBDx)==0)				//Si lo logines coinciden	
			{
			
			PuertoAsignarx = Personasx.getInt("Puerto");			//Obtenemos el puerto asignado y la direcion del usuario que sufrira la  	
			CadenaIPx = Personasx.getString("CadenaIP");			//modificacion	
			
				try
				{
				SocketClientex = new Socket(CadenaIPx, PuertoAsignarx+1);		//Instaciamos los sockets de transmision
				FlujodeSalidax = SocketClientex.getOutputStream();		//obtenemos el objeto FlujodeSalida de tipo OutputStream 
				Flujohaciaservidorx = new DataOutputStream(FlujodeSalidax);	//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
				Flujohaciaservidorx.writeInt(Tipo);				//Mandamos la variable que indica si el login debe ser borrado o agregado de la matriz de usuarios
				Flujohaciaservidorx.writeUTF(LoginTx);				//Enviamos el login a eliminar
				SocketClientex.close();
				}
	
				catch(UnknownHostException e)
				{
				System.out.println("Referencia a Host no resuleta: "+e.getMessage());
				System.exit(0);
				}
		
				catch(IOException e)
				{
				//System.out.println("Pailas en agregar quitar login");
				//System.out.println("Error en las comunicaciones: "+e.getMessage());
				//System.exit(0);
				}
			
				catch(SecurityException e)
				{
				System.out.println("Comunicación no permitida por razones de seguridad: "+e.getMessage());
				System.exit(0);
				}

			break;
			}
				
		}

	Personasx.close();								//Liberacion explicita de los recursos empleados
	Conexionx.close();
	SentenciaSQLx.close();	
	}

	catch(ClassNotFoundException e)
	{
	System.out.println("Clase no encontrada");
	}
	
	catch(SQLException e)
	{
	System.out.println(e);	
	}

}

public void ActualizarHiloTx(String CampoLogin)									//Recibimos el campo login a actualizar
{
int Identificador, GrupoM;
String Nombre, Apellido, Password;
final int TamanoBuffer = 100;
byte buffer [] = new byte[TamanoBuffer];
int NumBytesLeidos = 0;

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
BasedeDatos = "jdbc:odbc:Modificacion";
Conexion = DriverManager.getConnection(BasedeDatos);
SentenciaSQL = Conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
Personas = SentenciaSQL.executeQuery("SELECT * FROM DatosPersonales");
Login = CampoLogin;	
	
	while(Personas.next())
	{	
	LoginBD = Personas.getString("Login");						//Analizamos la base de datos hasta encontrar el registro que contiene 
											//el login del usuario que hadecidido actualizar sus datos
		if(Login.compareTo(LoginBD)==0)
		{
		Identificador = Personas.getRow();					//Obtenemos todos sus datos personales y la posicion de su registro en 
		Nombre = Personas.getString("Nombre");					//la base de datos
		Apellido = Personas.getString("Apellido");
		Password = Personas.getString("Password");	
		GrupoM = Personas.getInt("Grupo");	
			
			try												
			{
			DatosCliente = new Cliente(Nombre, Apellido, Login, Password, GrupoM, Identificador);	//Creamos un objeto que contiene todos los datos personales del usuario			
			DatosGuardarCliente = new GuardarCliente();						//Instancia de la clase GuardarCliente
			DatosGuardarCliente.GuardarDatos(DatosCliente);						//Guardamos los datos personales del usuario en un archivo
			}
	
			catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	
		PuertoAsignar = Personas.getInt("Puerto");				//Obtenemos el puerto y la direccion de destino del usuario a quien se le 
		CadenaIP = Personas.getString("CadenaIP");				//enviara la informacion 
					
			try
			{
			SocketCliente = new Socket(CadenaIP, PuertoAsignar+1);			//Instaciamos los sockets de transmision
			FlujodeSalida = SocketCliente.getOutputStream();			//obtenemos el objeto FlujodeSalida de tipo OutputStream 
			Flujohaciaservidor = new DataOutputStream(FlujodeSalida);		//Utilizando el objeto anterior creamos un instanica Flujohaciaservidor de tipo ObjectOutputStream 
			Flujohaciaservidor.writeInt(-7);					//Enviamos el numerto -7: SIGNIFICA QUE EL USUARIO VA A ACTUALIZAR SUS DATOS
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

			try									
			{
			Ficherosacar = new FileInputStream("DatosPersonales.txt");		//Habrimos un flujo de lectura del archivo
			} 
					
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
					NumBytesLeidos=Ficherosacar.read(buffer);		//Leemos la informacion contenida en el archivo
					Flujohaciaservidor.write(buffer,0,NumBytesLeidos);	//Le transmitimos esa informacion al cliente  
					}while(true);						//Hasta que no haya mas informacion 
				}
	
				catch(ArrayIndexOutOfBoundsException e)
				{
				}
		
			Ficherosacar.close();							//Cerramos los flujos y los sockets
			SocketCliente.close();
			}
	
			catch(IOException e)
			{
			System.out.println("Error: "+e.getMessage());
			}
		
		break;
		}
				
	}											

Personas.close();										//Liberacion explicita de los recursos empleados
Conexion.close();
SentenciaSQL.close();				
	
}

catch(ClassNotFoundException e)
{
System.out.println("Clase no encontrada");
}
	
catch(SQLException e)
{
System.out.println(e);	
}

	
}

}








