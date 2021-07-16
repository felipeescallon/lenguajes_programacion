import java.io.*;
import java.lang.*;

class BasedeDatos   
{
private ObjectInputStream ObjetoLeer;
private File NombreArchivo; 
private Cliente DatosPersonales;
private ClaseConsultar InstanciaClaseConsultar;
private ClaseInsertar InstanciaClaseInsertar;
private ClaseCampo DatosUsuario;
private Registro Resultado;
private GuardarRegistro ResultadoG;
private ClaseListaUsuarios InstanciaListaUsuarios;
private AsignarPuerto Muelle; 
private int Registrarse, PuertoAsignar, identificador;
private String[] ListaUsuarios;
private ClaseModificacion InstanciaModificacion;

public BasedeDatos()
{
DatosPersonales = new Cliente("Juan","Pedro","Carlos","Miguel", 1, 1);			//Creamos una falsa instacia de la clase Cliente para recuperar los datos introducidos por el usuario			
ResultadoG = new GuardarRegistro();							//Instancia de la clase donde se guardare el resultdo de la verificacion
ListaUsuarios = new String[0];								//Se crea una falsa lista de usuarios del Chat
}

public Cliente RecuperarFile()throws Exception						//Metodo que permite recuperar del archivo los datos introducidos por el usuario
{
	try
	{
	NombreArchivo = new File("DatosPersonales.txt"); 				//Nombre del fichero
	ObjetoLeer = new ObjectInputStream(new FileInputStream(NombreArchivo));		//Instaciamos los flujos para el archivo que contiene los datos del usuario
	DatosPersonales = (Cliente)ObjetoLeer.readObject();				//Leemos el objeto del archivo
	}
	
	finally										//Cerramos los flujos
	{
	if(ObjetoLeer!=null)
	ObjetoLeer.close();
	return DatosPersonales;								//Retornamos la referencia que apunta a los datos del usuario							
	}	
}

public int ConsultarDatos()								//VERIFICA SI LOS DATOS INTRODUCIDOS POR EL USUARIO SON VALIDOS	
{											
identificador = DatosPersonales.RetornarIdentificador(); 
	
	if(identificador==0)								//Datos provenientes de la interfaz de Chat
	{	
	InstanciaClaseConsultar = new ClaseConsultar("Principales", DatosPersonales);
	}

	else										//Datos provenientes de la interfaz de Datos	
	{
	InstanciaClaseConsultar = new ClaseConsultar("Personales", DatosPersonales);
	}

Registrarse = InstanciaClaseConsultar.RetornarRegistro();				//Retornamos el valor de Registrarse y su correspondiente
return Registrarse;									//significado
}

public int AsignaciondePuertos()							//ASIGNA UN PUERTO INDIVIDUAL A CADA USUARIO 
{
	DatosUsuario = InstanciaClaseConsultar.RetornarDatosUsuario(); 			//Obtenemos la referencia que apunta a los datos del usuario a almacenarse en la Base de Datos
	Muelle = new AsignarPuerto();							//Asignamos un puerto individual a cada usuario por el cual se realizara la comunicacion (Cliente-Servidor)							
	PuertoAsignar = Muelle.RetornarPuertoAsignar();					//Retornamos el valor del puerto asignado por el servidor
	return PuertoAsignar;
}

public void ObtenerLogines()								//OBTIENE LA LISTA DE USUARIOS ACTIVOS EN EL CHAT
{
	InstanciaListaUsuarios = new ClaseListaUsuarios();				//Obtenemos la Lista de usuarios activos en el Chat
	ListaUsuarios = InstanciaListaUsuarios.RetornarListaUsuarios();			//la cual se le enviara al usuario	
}


public void InsertarDatos(String CadenaIP)							//INSERTAMOS O MODIFICAMOS LOS DATOS INTRODUCIDOS POR EL USUARIO	
{
	if(Registrarse==1 && identificador<=1)							//Los datos introducidos en la Interfaz de Datos van a registrarse							
	{ 	
	InstanciaClaseInsertar = new ClaseInsertar(DatosUsuario, CadenaIP,PuertoAsignar,1 ); 	//Insertar usuario en la Base de Datos	
	}

	if(Registrarse==2) 									//Los datos introducidos en la InterfazdeChat son validos 
	{
	InstanciaClaseInsertar = new ClaseInsertar(DatosUsuario, CadenaIP, PuertoAsignar, 2);	//Modificar campo puerto, cadenaIP y estado en la Base de Datos
	}
	
	if(Registrarse==1 && identificador>1)							//Los datos introducidos en la interfaz de actualizar se pueden modificar 
	{
	InstanciaModificacion = new ClaseModificacion(DatosUsuario, CadenaIP, PuertoAsignar, identificador);	//modificamos los antiguos datos del usuario por los recien
	}													//digitados
}

	
public void  CrearEnlace()
{
	if(Registrarse == 4)									//SESION ABIERTA
	{
		try
		{
		Resultado = new Registro("Su sesion ya se encuentra abierta",ListaUsuarios,0);	//Guardamos en un archivo el resultado de la verificacion en este caso:	
		ResultadoG.GuardarDatosRegistro(Resultado);					//Lista de usuarios activos vacia, puerto de conversacion igual a cero y	
		}										//un mensaje que indica que su sesion esta abierta ya sea en otro computador o por
												//otra persona 	
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}	
	}

	
	if(Registrarse == 3)									//LOS DATOS INTRODUCIDOS EN LA INTERFAZ DE CHAT SON INEXISTENTES
	{
		try
		{
		Resultado = new Registro("Sus datos son inexsistentes. Por favor registrese",ListaUsuarios,0);		//Guardamos en un archivo el resultado de la verificacion en este caso:		
		ResultadoG.GuardarDatosRegistro(Resultado);								//Lista de usuarios activos vacia, puerto de conversacion igual a cero y	
		}													//un mensaje que indica que los datos introducidos son erroneos o inexistentes

		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}		
	}


	if(Registrarse == 2 || Registrarse ==1)							 //LOS DATOS INTRODUCIDOS EN LA INTERFAZ DE CHAT SON VALIDOS
	{										 	 //LOS DATOS INTRODUCIDOS EN LA INTERFAZ DE DATOS O ACTUALIZAR SE 
		try										 //SE INGRESARON O MODIFICARON SIN NINGUN PROBLEMA
		{
		Resultado = new Registro("InterfazPrincipal",ListaUsuarios, PuertoAsignar);	//Guardamos en un archivo el resultado de la verificacion en este caso:			
		ResultadoG.GuardarDatosRegistro(Resultado);					//Lista de usuarios activos, puerto de conversacion asignado por el servidor y	
		}										//un mensaje que indica que los datos introducidos se ingresaron o modificaron correctamente

		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}				

	}

	
	if(Registrarse == 0)									//EL LOGIN INTRODUCIDO EN LA INTERFAZ DE DATOS YA EXISTE 
	{
		try
		{
		Resultado = new Registro("Su login ya existe. Por favor ingrese uno nuevo", ListaUsuarios,0);	//Guardamos en un archivo el resultado de la verificacion en este caso:			
		ResultadoG.GuardarDatosRegistro(Resultado);							//Lista de usuarios activos vacia, puerto de conversacion igual a cero y	
		}												//un mensaje que indica que el login introducido ya existe

		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}			
	}	

}

}












