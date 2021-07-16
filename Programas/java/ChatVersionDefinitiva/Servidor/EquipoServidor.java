import java.lang.*;
import java.io.*;

class EquipoServidor
{

private BasedeDatos Administrador;
private TelecomunicacionRx Recepcion;
private TelecomunicacionTx Transmision;
private ClaseLista Lista;
private Cliente DatosPersonales;
private String CadenaIP;

public EquipoServidor()			
{
	Administrador = new BasedeDatos(); 							//Instaciamos la referencia a la clase base de datos
			
}

public void Operaciones(ClaseLista Lista1, String CadenaIP1)
{
	try
	{
	CadenaIP = CadenaIP1;									
	Lista = Lista1;
	DatosPersonales = Administrador.RecuperarFile();					//Recuperamos la referencia que apunta a los datos
	}											//introducidos por el usuario

	catch(Exception e)
	{
	System.out.println(e.getMessage());
	}

int Registrarse = Administrador.ConsultarDatos();						//Se realiza la consulta de los datos del usuario
	
	if(Registrarse==1 || Registrarse==2)							//SI LOS DATOS INTRODUCIDOS POR EL USUARIO SON VALIDOS	
	{
	int PuertoAsignar = Administrador.AsignaciondePuertos();				//Le asignamos un puerto individual al usuario por el cual se realizara la comunicaicon 
	Administrador.ObtenerLogines();								//Obtenemos los logines de los demas usuarios activos en el Chat
	Administrador.InsertarDatos(CadenaIP);							//Realizamos los cambios de estado, puerto cadenaIP al usuario que acaba de activarse
	Transmision = new TelecomunicacionTx();							//Instancia de la clase que se encargara de la Tx de los mensajes del usuario					
	Recepcion = new TelecomunicacionRx(PuertoAsignar, Transmision, Lista);			//Instancia de la clase que se encargara de la RX de los mensajes introducidos por el usuario
	Lista.AgregarQuitarLoginLista(DatosPersonales.RetornarLogin(),-3);			//Insertamos el login del nuevo usuario activo en la lista de usuarios de los demas clientes
	Lista.InsertarNodo(Recepcion, Transmision,DatosPersonales.RetornarLogin());		//Insertamos un nuevo nodo en la lista de los usuarios activos	
	}

Administrador.CrearEnlace();									//Guardamos los resultado de la verificacion hecha en la base de datos								
}

}
