import java.lang.*;
import java.io.*;		
	
class ClaseLista							 
{
private ClaseNodo primero,ref,ultimo;

public ClaseLista()							//Cabezera de la lista de nodos que contiene a los usuarios activos en el Chat
	{
	primero=null;
	ref=null;
	ultimo=null;
 	}

public void InsertarNodo(TelecomunicacionRx general, TelecomunicacionTx popular, String CampoLogin)
{
ClaseNodo nuevo = new ClaseNodo();					//Metodo que cada vez que un usuario se activa insertamos un nodo de informacion que 
nuevo.AsignarInfo(general,popular, CampoLogin);				//Contiene la informacion de su login y sus referencias a sus clases Telecomunicacion
									//en Rx y Tx encargadas de procesar la informacion proveniente y hacia el usuario
	if(ref==null)
	{
	nuevo.AsignarSiguiente(primero);
	primero=nuevo;
	}
	
	else
	{
	nuevo.AsignarSiguiente(ref.RetornarSiguiente());
	ref.AsignarSiguiente(nuevo);
	}

	if(nuevo.RetornarSiguiente()==null)
	{
	ultimo=nuevo;
	ref=null;
	}
}

public synchronized void AgregarQuitarLoginLista(String Login, int Tipo)	//Metodo que recibe el login que ha de borrarse o agregarse de la lista de usuarios activos del Chat
{										
TelecomunicacionTx refTx;
	
	if(primero!=null)
	{
		ref=primero;							
		while(ref!=null)						//Vamos a analizar la lista de principio a fin
		{
		String Destino = ref.RetornarCampoLoginL();			//Obtenemos el Login de cada usuario almacenado en el nodo 
			
			if(!(Login.compareTo(Destino)==0))			//Si login retornado y el login buscado son diferentes
			{
			refTx = ref.RetornarPunteroTx();			//Obtenemos la referencia a la clase TelecomunicacionTx con el objetivo 
			refTx.AgregarQuitarLoginTx(Destino,Login,Tipo);		//de utilizar uno de sus metodos para transmitirle al usuario que otro usuario acaba en ingresar
			}							//o que cerro su sesion

		ref=ref.RetornarSiguiente();					//Recorremos todos los nodos
		}
	}

}

public synchronized void EliminarNodo(String CampoLogin)				//Funcion que Recibime el login a eliminar, lo busca en todos los nodos
{											//hasta encontrarlo y una vez hecho esto elimina el nodo
ClaseNodo elim=null;									//lo que significa que el usuario ha decidido cerrer la sesion,
ref = primero;										//ha sido excluido por el servidor o esta actualizando sus datos
		
	while(!(CampoLogin.compareTo(ref.RetornarCampoLoginL())==0) && ref!=null)
	{
	ref = ref.RetornarSiguiente();
	}	
			
	if(ref==primero || ref==null)
	{
	elim=primero;
	primero=primero.RetornarSiguiente();
	ref=null;
	}
		
	else
	{
	elim=ref;
	ref=primero;
	
		while(ref.RetornarSiguiente()!=elim)
		{
		ref=ref.RetornarSiguiente();
		}

		ref.AsignarSiguiente(elim.RetornarSiguiente());
	}


	if(elim.RetornarSiguiente()==null)
	{
	ultimo=ref;
	}

ref = null;
elim = null;
}

}






