import java.awt.*;
import java.awt.event.*;

class InterfazdeDatos 							//INTERFAZ DE DATOS
{

private Frame MarcoDatos;
private Panel PanelDatos;
private Panel PanelEtiquetaDatos;
private Panel PanelNombre;
private Panel PanelApellido;
private Panel PanelLogin;
private Panel PanelPassword;
private Panel PanelPassword1;
private Panel PanelGrupo; 
private Panel PanelIniciarSesion;
private TextField CampoNombre;
private TextField CampoApellido;
private TextField CampoLogin;
private TextField CampoPassword;
private TextField CampoPassword1;
private Choice ChoiceGrupo;
private Button BotonIniciarSesion;
private Button BotonCancelar;
private Label EtiquetaDatos;
private Label EtiquetaNombre;
private Label EtiquetaApellido;
private Label EtiquetaLogin;
private Label EtiquetaPassword;
private Label EtiquetaPassword1;
private Label EtiquetaGrupo;
private InterfazdeChat InstanciaInterfazdeChat;
private Cliente DatosCliente;
private RecuperarCliente Actualizacion;
private String Titulo;
private	int Diseno;

public InterfazdeDatos(int Diseno1)
{
Diseno=Diseno1;								//Identifica cual es la ventana a mostrar
	
	if(Diseno==0)							//Colocamos el correspondiente titulo
 	{								//a la ventana
	Titulo = "Interfaz de datos";
	BotonIniciarSesion = new Button("Iniciar Sesion");		//Instanciamos los botones con los respectivos  
	BotonCancelar = new Button("Cancelar");				//nombres dependiendo del titulo de la ventana
	}

	else
	{
	Titulo = "Actualizar datos";
	BotonIniciarSesion = new Button("Actualizar");			
	BotonCancelar = new Button("Cerrar Sesion");
	}

MarcoDatos = new Frame(Titulo);					         //Instanciamos los panels
PanelDatos = new Panel(new GridLayout(8,1));
PanelEtiquetaDatos = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelNombre = new Panel(new FlowLayout(FlowLayout.LEFT));
PanelApellido = new Panel(new FlowLayout(FlowLayout.LEFT));
PanelLogin = new Panel(new FlowLayout(FlowLayout.LEFT));	
PanelPassword = new Panel(new FlowLayout(FlowLayout.LEFT));	
PanelPassword1 = new Panel(new FlowLayout(FlowLayout.LEFT));
PanelGrupo = new Panel(new FlowLayout(FlowLayout.LEFT));
PanelIniciarSesion = new Panel(new FlowLayout(FlowLayout.CENTER));

CampoLogin = new TextField(15);						//Instanciamos los campos
CampoPassword = new TextField(15);
CampoPassword1 = new TextField(15);
CampoNombre = new TextField(25);
CampoApellido = new TextField(35);

EtiquetaDatos = new Label("INTERFAZ DE DATOS");				//Instanciamos las etiquetas
EtiquetaLogin = new Label("Login");
EtiquetaPassword = new Label("Password");
EtiquetaNombre = new Label("Nombre");
EtiquetaApellido = new Label("Apellido");
EtiquetaPassword1 = new Label("Vuelva a escribir el password");
EtiquetaGrupo = new Label("Grupo");

ChoiceGrupo = new Choice(); 						//Instanciamos el choice

PanelDatos.add(PanelEtiquetaDatos);					//Agregamos los paneles al panel principal
PanelDatos.add(PanelNombre);
PanelDatos.add(PanelApellido);
PanelDatos.add(PanelLogin);
PanelDatos.add(PanelPassword);
PanelDatos.add(PanelPassword1);
PanelDatos.add(PanelGrupo);
PanelDatos.add(PanelIniciarSesion);

PanelEtiquetaDatos.add(EtiquetaDatos);					//Agregamos los elementos a los paneles secundarios
PanelNombre.add(EtiquetaNombre);
PanelNombre.add(CampoNombre);
PanelApellido.add(EtiquetaApellido);
PanelApellido.add(CampoApellido);
PanelLogin.add(EtiquetaLogin);
PanelLogin.add(CampoLogin);
PanelPassword.add(EtiquetaPassword);
PanelPassword.add(CampoPassword);
PanelPassword1.add(EtiquetaPassword1);
PanelPassword1.add(CampoPassword1);
PanelGrupo.add(EtiquetaGrupo);
PanelGrupo.add(ChoiceGrupo);
PanelIniciarSesion.add(BotonIniciarSesion);
PanelIniciarSesion.add(BotonCancelar);

CampoNombre.addActionListener(new IniciarSesion());			//Registro de los objetos que generan eventos
CampoApellido.addActionListener(new IniciarSesion());			
CampoLogin.addActionListener(new IniciarSesion());			
CampoPassword.addActionListener(new IniciarSesion());			
CampoPassword1.addActionListener(new IniciarSesion());	
BotonIniciarSesion.addActionListener(new IniciarSesion());		
BotonCancelar.addActionListener(new Cancelar());


CampoPassword.setEchoChar('o');						//Enmascarar el password y su comprobacion
CampoPassword1.setEchoChar('o');

	for(int i=1; i<=2; i++)						//LLenamos el chice de los grupos
	{
	ChoiceGrupo.addItem(""+i);
	}


	if(Diseno==1)
	{
		try
		{
		Actualizacion = new RecuperarCliente();					//Instanciamos la referencia a la clase Recuperar cliente
		DatosCliente = Actualizacion.RecuperarFDatosPersonales();		//Apuntamos la referencia DatosCliente al objeto que contiene los datos 
		}									//del usuario que estaban almacenados en la base de datos del servidor

		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}

	CampoNombre.setText(DatosCliente.RetornarNombre());				//LLenamos los diferentes campos con los los datos almacenados 
	CampoApellido.setText(DatosCliente.RetornarApellido());				//en la base de datos 
	CampoLogin.setText(DatosCliente.RetornarLogin());
	CampoPassword.setText(DatosCliente.RetornarPassword());
	CampoPassword1.setText(DatosCliente.RetornarPassword());
	ChoiceGrupo.select(DatosCliente.RetornarGrupo()-1);				//seleccionamos el grupo al que pertenece el usuario
	}

PanelDatos.setBackground(Color.lightGray);				//Fondo y dimensiones de la ventana
MarcoDatos.setSize(400,400);
MarcoDatos.add(PanelDatos);

MarcoDatos.addWindowListener(						//Cerrar la ventana Interfaz de Datos
	new WindowAdapter()
	{
	public void windowClosing(WindowEvent evento)
	{
	MarcoDatos.hide();						//Si se cierra la ventana de interfaz de Datos	
	InstanciaInterfazdeChat = new InterfazdeChat();			//Mostramos la ventana de interfaz de Chat		
	}
	});

MarcoDatos.show();							//Mostrar la ventana de interfaz de Datos
} 	

private class IniciarSesion implements ActionListener			//Gestion a los eventos de INICIAR SESION
{
private RestriccionChat Vacio;
private GuardarCliente DatosGuardarCliente;
private ComunicacionCliente Enviar;
private Registro Definitiva;
private RestriccionLogin Existente;
private InterfazPrincipal Sesion;
private int Identificador;

public void actionPerformed(ActionEvent Evento)				//Metodo que responde al evento sobre el boton de Iniciar Sesion o sobre
	{								//alguno de los campos si se presiona enter
	int LongitudCampoNombre = CampoNombre.getText().length(); 
	int LongitudCampoApellido = CampoApellido.getText().length();
	int LongitudCampoLogin = CampoLogin.getText().length();
	int LongitudCampoPassword = CampoPassword.getText().length();
	int LongitudCampoPassword1 = CampoPassword1.getText().length();	
	
		if(LongitudCampoNombre==0 || LongitudCampoApellido==0 || LongitudCampoLogin==0 || LongitudCampoPassword==0 || LongitudCampoPassword1==0)		//Restriccion si alguno de los campos de texto no ha 
		{																			//sido llenado; no se permiten datos de usuario en blanco 
		Vacio = new RestriccionChat(MarcoDatos, "Registro incompleto");			//Mostramos un cuadro de dialogo indicandole al usuario que su registro esta incompleto			
		}
	
		else										//Si no hay campos en blanco
		{
			if(!(CampoPassword.getText().compareTo(CampoPassword1.getText())==0))	//Restriccion si el password y su comprobante no son iguales
			{
			Vacio = new RestriccionChat(MarcoDatos,"Vuelva a escribir correctamente su password" );		//Mostramos un cuadro de dialogo indicandole al usuario que 
			CampoPassword.setText("");									//los password introducidos son diferentes
			CampoPassword1.setText("");
			}	

			else									//Si los passwords considen	
			{
					
			try
			{
			Enviar = new ComunicacionCliente("localhost",8000);				//Instanciamos los sockets que realizaran la TX de los datos introducidos
				
				if(Diseno == 0)							//Si la interfaz es la Interfaz de Datos	
				{
				Identificador = 1;
				}

				else
				{
				Identificador = DatosCliente.RetornarIdentificador();		//Identificador sera igual a la posicion del Usuario en la base de datos		
				}
	
			DatosCliente = new Cliente(CampoNombre.getText(), CampoApellido.getText(), CampoLogin.getText(), CampoPassword.getText(), ChoiceGrupo.getSelectedIndex()+1,Identificador);	//Objeto que contiene la informacion introducida por el usuario
			DatosGuardarCliente = new GuardarCliente();																	//Instanciamos la referencia a la clase Guardar Cliente
			DatosGuardarCliente.GuardarDatos(DatosCliente);																	//Guardamos la informacion introducida por el usuario en un fichero	
			}	
		
			catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
									
			Definitiva = Enviar.comunicacion();					//Se realiza la Tx del fichero que contiene la informacion y recibimos la referenica 	
												//al objeto que contiene los resultados de la verificacion 			
			if("Su login ya existe. Por favor ingrese uno nuevo".compareTo(Definitiva.RetornarVeredicto())==0)						  //Restriccion si el Login ingresado por el usuario ya esta  
			{																		  //siendo utilizado por otro usario; no se permite logines iguales	
			Existente = new RestriccionLogin(MarcoDatos);				//Mostramos de nuevo la interfaz de datos e invitamos al usuario a 			
			CampoLogin.setText("");							//introducir un login diferente
			}		
	
			if("InterfazPrincipal".compareTo(Definitiva.RetornarVeredicto())==0)	//Si no hubo problemas con el registro  
			{
			MarcoDatos.hide();
			Sesion = new InterfazPrincipal(Definitiva, CampoLogin.getText());	//Mostramos la interfaz principal e invitamos al usuario a chatear	
     			}
			
			}
	
	
		}
	}
}


private class Cancelar implements ActionListener			//Gestion a los eventos de CANCELAR
{

public void actionPerformed(ActionEvent Evento)				//Metodo que responde a los eventos sobre el boton Cancelar
	{
	MarcoDatos.hide();
	InstanciaInterfazdeChat = new InterfazdeChat();			//Mostramos la Interfazde Chat
	}

}







}