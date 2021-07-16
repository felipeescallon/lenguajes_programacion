import java.awt.*;
import java.awt.event.*;

public class InterfazPrincipalLider 							//INTERFAZ PRINCIPAL
{
private Frame MarcoPrincipal;
private Panel PanelPrincipal;
private GridBagLayout Estructura;
private GridBagConstraints coordenadas;  
private Panel PanelBotonActualizarDatos;
private Panel PanelBotonCerrarSesion;
private Panel PanelRelleno0;
private Panel PanelRelleno1;
private Panel PanelRelleno2;
private Panel PanelEtiquetaEntrante;
private Panel PanelEtiquetaUsuarios;
private Panel PanelRelleno3;
private Panel PanelRelleno4;
private Panel PanelEtiquetaSaliente;
private Panel PanelListaUsuarios;
private Panel PanelCheckboxTodos;
private Panel PanelBotonEnviar;
private TextArea AreaMensajeEntrante;
private TextField MensajeSaliente;
private Label EtiquetaUsuarios;
private Label EtiquetaSaliente;
private Label EtiquetaEntrante;
private Button BotonActualizarDatos;
private Button BotonCerrarSesion;
private Button BotonVariosaUno;
private Button BotonEnviar;
private List ListaUsuarios;	 	
private Checkbox CheckboxTodos;
private Registro Veredicto;
private TelecomunicacionRx InstanciaRecibir;
private TelecomunicacionTx InstanciaEnviar;
private String CampoLogin;
private InterfazdeChat Abrir;
private String[] Ceros = new String[0];
private int Interruptor=0;

public InterfazPrincipalLider(Registro Definitiva, String CampoLogin1)
{
Veredicto = Definitiva;								//Recibimos la referencia que apunta al resultado de la verificacion
CampoLogin = CampoLogin1;							//Recibimos el Login del Usuario
InstanciaEnviar = new TelecomunicacionTx(Veredicto, CampoLogin);		//Instanciamos los sockets de transmision
MarcoPrincipal = new Frame("Interfaz principal equipo lider - grupo1 ");	//Instanciamos el marco
EtiquetaSaliente = new Label("Mensaje Saliente:");				//Instanciamos las etiquetas
EtiquetaEntrante = new Label("Mensaje Entrante:");
EtiquetaUsuarios = new Label("    Usuarios:");
BotonActualizarDatos = new Button("Actualizar Datos");				//Instanciamos los botones
BotonCerrarSesion = new Button("Cerrar Sesion");
BotonEnviar = new Button("     Enviar     ");
BotonVariosaUno = new Button("Varios a Uno");
ListaUsuarios = new List(19,true);						//Instanciamos la Lista
CheckboxTodos = new Checkbox("Todos");                  			//Instanciamos el chekbox

Estructura = new GridBagLayout();						//Distribucion del contenedor
coordenadas = new GridBagConstraints();
PanelPrincipal = new Panel();
PanelPrincipal.setLayout(Estructura);						
coordenadas.fill = GridBagConstraints.BOTH;

coordenadas.ipadx = 75;								//Agregamos el boton ActualizarDatos 
coordenadas.gridx = 0;
coordenadas.gridy = 0;
PanelBotonActualizarDatos = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelBotonActualizarDatos,coordenadas);
PanelPrincipal.add(PanelBotonActualizarDatos);	
PanelBotonActualizarDatos.add(BotonActualizarDatos);	

coordenadas.ipadx = 175;							//Agregamos paneles de relleno
coordenadas.gridx = 1;
coordenadas.gridy = 0;
PanelRelleno1 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno1,coordenadas);
PanelPrincipal.add(PanelRelleno1);

coordenadas.ipadx = 175;
coordenadas.gridx = 2;
coordenadas.gridy = 0;
PanelRelleno2 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno2,coordenadas);
PanelPrincipal.add(PanelRelleno2);
	
coordenadas.ipadx = 75;								//Agregamos el boton CerrarSesion
coordenadas.gridx = 3;
coordenadas.gridy = 0;
PanelBotonCerrarSesion = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelBotonCerrarSesion,coordenadas);
PanelPrincipal.add(PanelBotonCerrarSesion);
PanelBotonCerrarSesion.add(BotonCerrarSesion);	

coordenadas.gridx = 0;								//Agregamos panel de relleno
coordenadas.gridy = 1;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelRelleno0 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno0,coordenadas);
PanelPrincipal.add(PanelRelleno0);

coordenadas.gridx = 0;								//Agregamos la etiqueta: Mensaje entrante
coordenadas.gridy = 2;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelEtiquetaEntrante = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelEtiquetaEntrante,coordenadas);
PanelPrincipal.add(PanelEtiquetaEntrante);
PanelEtiquetaEntrante.add(EtiquetaEntrante);

coordenadas.gridx = 3;								//Agregamos la etiqueta: Usuarios
coordenadas.gridy = 2;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelEtiquetaUsuarios = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelEtiquetaUsuarios,coordenadas);
PanelPrincipal.add(PanelEtiquetaUsuarios);
PanelEtiquetaUsuarios.add(EtiquetaUsuarios);

coordenadas.gridx = 0;								//Agregamos el area de los mensajes entrantes
coordenadas.gridy = 3;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
AreaMensajeEntrante = new TextArea(14,60);
Estructura.setConstraints(AreaMensajeEntrante,coordenadas);
PanelPrincipal.add(AreaMensajeEntrante);

coordenadas.gridx = 0;								//Agregamos paneles de relleno
coordenadas.gridy = 4;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelRelleno3 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno3,coordenadas);
PanelPrincipal.add(PanelRelleno3);

coordenadas.gridx = 0;								//Agregamos la etiqueta: Mensaje saliente
coordenadas.gridy = 5;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelEtiquetaSaliente = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelEtiquetaSaliente,coordenadas);
PanelPrincipal.add(PanelEtiquetaSaliente);
PanelEtiquetaSaliente.add(EtiquetaSaliente);

coordenadas.gridx = 0;								//Agregamos el campo de los mensajes salientes
coordenadas.gridy = 6;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
MensajeSaliente = new TextField(60);					     	
Estructura.setConstraints(MensajeSaliente,coordenadas);
PanelPrincipal.add(MensajeSaliente);

coordenadas.gridx = 0;								//Agregamos paneles de relleno
coordenadas.gridy = 7;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelRelleno4 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno4,coordenadas);
PanelPrincipal.add(PanelRelleno4);

coordenadas.gridx = 0;								//Agregamos boton Enviar y varios a uno		
coordenadas.gridy = 8;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelBotonEnviar = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelBotonEnviar,coordenadas);
PanelPrincipal.add(PanelBotonEnviar);
PanelBotonEnviar.add(BotonEnviar);
PanelBotonEnviar.add(BotonVariosaUno);

coordenadas.gridx = 3;								//Agregamos el CheckBox
coordenadas.gridy = 8;
PanelCheckboxTodos = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelCheckboxTodos,coordenadas);
PanelPrincipal.add(PanelCheckboxTodos);
PanelCheckboxTodos.add(CheckboxTodos);

coordenadas.gridx = 3;								//Agregamos la lista de usuarios
coordenadas.gridy = 3;
coordenadas.gridheight = 5;
PanelListaUsuarios = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelListaUsuarios,coordenadas);
PanelPrincipal.add(PanelListaUsuarios);
PanelListaUsuarios.add(ListaUsuarios);

String ListaLogines[] = Veredicto.RetornarLista();				//Retornamos la matriz de usuarios activos en el chat
int i=0;									//y la añadimos a la lista de la interfaz para que 
										//sea visible al usuario
	try
	{
		for(;;)
		{
		ListaUsuarios.add(ListaLogines[i]);
		i=i+1;
		}
	}

	catch(ArrayIndexOutOfBoundsException e)					//Hasta añadir todos los terminos de la matriz
	{
	}
	
InstanciaRecibir = new TelecomunicacionRx(Veredicto, AreaMensajeEntrante, ListaUsuarios, InstanciaEnviar, MarcoPrincipal);	//Lanzamos el hilo continuo de recepcion de mensajes

BotonEnviar.addActionListener(new Enviar());					//Registro de los objetos que generan eventos						
MensajeSaliente.addActionListener(new Enviar());
BotonCerrarSesion.addActionListener(new CerrarSesion());
BotonVariosaUno.addActionListener(new EnviarVariosaUno());
CheckboxTodos.addItemListener(new SeleccionarTodos());
BotonActualizarDatos.addActionListener(new ActualizarDatos());

PanelPrincipal.setBackground(Color.lightGray);					//Fondo de la ventana
MarcoPrincipal.setSize(800,450);
MarcoPrincipal.add(PanelPrincipal);

MarcoPrincipal.addWindowListener(						//Cerramos la ventan de interfaz de Chat 
	new WindowAdapter()							//que equivale a cerrar la sesion
	{
	public void windowClosing(WindowEvent evento)
	{
	InstanciaEnviar.correr(MensajeSaliente, Ceros,-2);			//Cerramos la sesion;
	}
	});

MarcoPrincipal.show();								//Mostramos la ventana
} 


private class SeleccionarTodos implements ItemListener				//Gestion a los eventos de SELECIONAR TODOS
{
public void itemStateChanged(ItemEvent Evento)					//Metodo que responde alos eventso en el CheckBox
{
int Cuenta = ListaUsuarios.countItems();					//Obtenemos el numero de usuarios en la lista

	if(Interruptor==0)							//Si se presiona el CheckBox un vez impar todos los usuarios se 
	{									//seleccionan exepto el servidor que simpre aparecera en la primera posicion
		for(int i=1; i<=Cuenta-1; i++)					
		{	
		ListaUsuarios.select(i);
		}
	Interruptor=1;	
	}
	
	else									//Si se presiona el CheckBox un vez par todos los usuarios seleccionados 
	{									//se desselecionan
		for(int i=1; i<=Cuenta-1; i++)
		{	
		ListaUsuarios.deselect(i);
		}
	Interruptor=0;	
	}
}
}


private class Enviar implements ActionListener					//Gestion a los eventos de ENVIAR
{

private RestriccionChat Vacio;						  
				
public void actionPerformed(ActionEvent Evento)					//Metodo que responde a los eventos sobre el Boton enviar  	
	{
	int i=0;
	String Logines[]=ListaUsuarios.getSelectedItems();			//Obtenemos la matriz con todos los logines seleccionados
		try
		{	
			for(;;)							//Obtenemos el indice de los logines que hay selecionados
			{
			String controlador = Logines[i];		
			i++;
			}
		}

		catch(ArrayIndexOutOfBoundsException e)
		{
		i--;
		}
		
		if(i!=-1)							 //Si por lo menos hay un login seleccionado 
		{
		InstanciaEnviar.correr(MensajeSaliente, Logines,i);		 //Se realiza el envio del mesaje;
		MensajeSaliente.setText("");
		}
		
		else								 //Si no hay logines seleecionados se lemuestra al usuario un cruadro de dialogo 	
		{								 //indicandole que debe por lo menos selecionar un login	
		Vacio = new RestriccionChat(MarcoPrincipal, "No hay logines seleccionados"); 
		}
	}

}

private class CerrarSesion implements ActionListener				 //Gestion a los eventos de CERRAR SESION
{

public void actionPerformed(ActionEvent Evento)
	{									 //ENVIAMOS UN (-2) QUE SIGNIFICA QUE EL USUARIO DESEA CERRAR LA SESION	
	InstanciaEnviar.correr(MensajeSaliente, Ceros, -2);			 //Enviamos una falsa lista de logines seleccionados
	}									 //Enviamos un mensaje indicandole al servidor que desconecte al usuario;	
}

private class ActualizarDatos implements ActionListener				 //Gestion a los eventos de ACTUALIZAR DATOS
{
public void actionPerformed(ActionEvent Evento)					 //ENVIAMOS UN (-7) QUE SIGNIFICA QUE EL USUARIO DESEA ACTUALIZAR SUS DATOS	
	{									 //Enviamos una falsa lista de logines seleccionados
	InstanciaEnviar.correr(MensajeSaliente, Ceros, -7);			 //Cerramos la seison del usuario  y le mostramos los datos a modificar
	}									 //Enviamos un mensaje indicandole al servidor que desconecte al usuario y le		
}										 //permita actualizar sus datos	

private class EnviarVariosaUno implements ActionListener	                 //Gestion a los eventos de ENVIAR VARIOS A UNO
{
public void actionPerformed(ActionEvent Evento)					
	{
	InstanciaEnviar.VariosaUno(MensajeSaliente);				 //Llamamos a la funcion de la clase TelecomunicacionTx encargada de realizar
	MensajeSaliente.setText("");						 //este tipo de envios; aqui unicamente le enviamos el mensaje escrito por 
	}									 //el lider de grupo 1 que se mostrara a integrantes del grupo 2.
}


}
	









