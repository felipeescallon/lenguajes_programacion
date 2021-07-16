import java.awt.*;
import java.awt.event.*;

public class InterfazdeChat 						//INTERFAZ GRAFICA DE CHAT
{
private Frame MarcoChat;
private Panel PanelChat;
private Panel PanelEtiquetaChat;
private Panel PanelLogin;
private Panel PanelPassword;
private Panel PanelRegistrarse;
private TextField CampoLogin;
private TextField CampoPassword;
private Button BotonRegistrarse;
private Button BotonIniciarSesion;
private Label EtiquetaChat;
private Label EtiquetaLogin;
private Label EtiquetaPassword;
private InterfazPrincipalLider Sesion;

public InterfazdeChat()
{
MarcoChat = new Frame("Interfaz de chat");				//Instanciamos los panels
PanelChat = new Panel(new GridLayout(4,1));
PanelEtiquetaChat = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelLogin = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelPassword = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelRegistrarse = new Panel(new FlowLayout(FlowLayout.CENTER));

CampoLogin = new TextField(15);						//Instanciamos los campos
CampoPassword = new TextField(15);
EtiquetaChat = new Label("INTERFAZ DE CHAT");				//Instanciamos las etiquetas
EtiquetaLogin = new Label("Login");
EtiquetaPassword = new Label("Password");
BotonRegistrarse = new Button("Registrarse");				//Instanciamos los botones
BotonIniciarSesion = new Button("Iniciar Sesion");

PanelChat.add(PanelEtiquetaChat);					//Agregamos los paneles al panel principal
PanelChat.add(PanelLogin);
PanelChat.add(PanelPassword);
PanelChat.add(PanelRegistrarse);

PanelEtiquetaChat.add(EtiquetaChat);					//Agregamos los elementos a los paneles secundarios
PanelLogin.add(EtiquetaLogin);
PanelLogin.add(CampoLogin);
PanelPassword.add(EtiquetaPassword);
PanelPassword.add(CampoPassword);
PanelRegistrarse.add(BotonIniciarSesion);
PanelRegistrarse.add(BotonRegistrarse);

CampoPassword.setEchoChar('o');						//Enmascarar el password
PanelChat.setBackground(Color.lightGray);				//Fondo de la ventana

CampoLogin.addActionListener(new IniciarSesion());			//Registro de los objetos que gestionan los eventos
CampoPassword.addActionListener(new IniciarSesion());
BotonIniciarSesion.addActionListener(new IniciarSesion());
BotonRegistrarse.addActionListener(new Registrarse());

MarcoChat.setSize(400,300);						//tamaño de la ventana
MarcoChat.add(PanelChat);						//agrgamos el panel principal al frame

MarcoChat.addWindowListener(						//Cerrar la ventana Interfaz de Chat
	new WindowAdapter()
	{
	public void windowClosing(WindowEvent evento)
	{
	System.exit(0);
	}
	});

MarcoChat.show();							//Mostrar la ventana
}

public static void main(String[] args)					//Main de la aplicacion
{
InterfazdeChat mostrar = new InterfazdeChat();
}


private class IniciarSesion implements ActionListener			//Gestion a los eventos de INICIAR SESION
{
private RestriccionChat Vacio;
private Cliente DatosCliente;
private GuardarCliente	DatosGuardarCliente;
private ComunicacionCliente Enviar;
private Registro Definitiva;

public void actionPerformed(ActionEvent Evento)				//Metodo que responde al evento sobre el boton INICIAR SESION y los campos de LOGIN Y PASSWORD	
{
int LongitudCampoLogin = CampoLogin.getText().length();
int LongitudCampoPassword = CampoPassword.getText().length();
	
	if(LongitudCampoLogin==0 || LongitudCampoPassword==0)		//Restriccion si alguno de los campos texto no ha sido 
	{								//llenado; no se permiten datos de usuarios en blanco 
	Vacio = new RestriccionChat(MarcoChat,"Registro incompleto");	//Mostrmos un cuadro de dialogo indicancole al usuario que su registro esta incompleto			
	}
		
	else								//Si no hay campos en blanco
	{	
		try
		{
		DatosCliente = new Cliente("","",CampoLogin.getText(), CampoPassword.getText(),0,0);	//Objeto que contiene la informacion introducida por el usurio
		DatosGuardarCliente = new GuardarCliente();	 					//Instanciamos la referencia a la clase Guardar Cliente
		DatosGuardarCliente.GuardarDatos(DatosCliente);						//Guardamos la informacion del usuario en un fichero 
		}	
		
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}

		Enviar = new ComunicacionCliente("localhost",8000);					//Se realiza la Tx de los datos de usuario para que sean verificados 
		Definitiva = Enviar.comunicacion();							//Recibimos la referencia al objeto que contiene los resultados de la verificacion		

			if("Sus datos son inexsistentes. Por favor registrese".compareTo(Definitiva.RetornarVeredicto())==0)	//Los datos introducidos por el usuario son inexistentes en la BASE DE DATOS
			{
			Vacio = new RestriccionChat(MarcoChat,"Usuario no encontrado");			//Mostramos un cuadro de dialogo que le informa el resultado al usuario 
			CampoPassword.setText("");
			}		
	
			if("InterfazPrincipal".compareTo(Definitiva.RetornarVeredicto())==0)		//Los datos introducidos por el usuario son validos 
			{
			MarcoChat.hide();
			Sesion = new InterfazPrincipalLider(Definitiva, CampoLogin.getText());		//Mostramos la interfaz principal que invita al usuario a chatear	
      			}

			if("Su sesion ya se encuentra abierta".compareTo(Definitiva.RetornarVeredicto())==0)			//Los datos introducidos por el usuario son validos pero su sesion ya se encuentra abierta
			{
			Vacio = new RestriccionChat(MarcoChat,"Sesion abierta");			//Mostramos un cuadro de dialogo que le informa el resultado al usuario 
			CampoLogin.setText("");
			CampoPassword.setText("");
			}
	}				
			

}

}


private class Registrarse implements ActionListener			//Respuesta a los eventos de REGISTRARSE
{
private InterfazdeDatos InstanciaInterfazdeDatos;

public void actionPerformed(ActionEvent Evento)				//Metodo que responde al evento sobre el boton Registrarse
	{
	MarcoChat.hide();
	InstanciaInterfazdeDatos = new InterfazdeDatos(0);		//Mostramos la interfaz de datos que invita al usuario a registrarse	
	}

}

}
