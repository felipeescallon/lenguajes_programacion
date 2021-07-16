import java.awt.*;
import java.awt.event.*;

public class InterfazServidor 							//INTERFAZ SERVIDOR
{
private Frame MarcoServidor;
private Panel PanelServidor;
private GridBagLayout Estructura;
private GridBagConstraints coordenadas;  
private Panel PanelRelleno6;
private Panel PanelRelleno5;
private Panel PanelRelleno0;
private Panel PanelRelleno1;
private Panel PanelRelleno2;
private Panel PanelEtiquetaEntrante;
private Panel PanelEtiquetaUsuarios;
private Panel PanelRelleno3;
private Panel PanelRelleno4;
private Panel PanelEtiquetaSaliente;
private Panel PanelListaUsuarios;
private Panel PanelBotonEnviar;
private TextArea AreaMensajeEntrante;
private TextField MensajeSaliente;
private Label EtiquetaUsuarios;
private Label EtiquetaSaliente;
private Label EtiquetaEntrante;
private Button BotonExcluir;
private Button BotonEnviar;
private List ListaUsuarios;	
private Animovimiento ManejarAcceso; 
private ClaseLista Lista;
private TelecomunicacionTx TransmisionS;
private TelecomunicacionRx RecepcionS;
private TelecomunicacionTxCS InstanciaEnviarCS;
private TelecomunicacionRxCS InstanciaRecibirCS;

public InterfazServidor()
{
MarcoServidor = new Frame("Interfaz servidor");					//Instanciamos el marco
EtiquetaSaliente = new Label("Mensaje Saliente:");				//Instanciamos las etiquetas
EtiquetaEntrante = new Label("Mensaje Entrante:");
EtiquetaUsuarios = new Label("    Usuarios:");
BotonEnviar = new Button("Enviar");						//Instanciamos los botones
BotonExcluir = new Button("Excluir");
ListaUsuarios = new List(21,true);						//Instanciamos la Lista

Estructura = new GridBagLayout();						//Distribucion del contenedor
coordenadas = new GridBagConstraints();
PanelServidor = new Panel();
PanelServidor.setLayout(Estructura);						
coordenadas.fill = GridBagConstraints.BOTH;

coordenadas.ipadx = 65;								//Agregamos paneles de relleno 
coordenadas.gridx = 0;
coordenadas.gridy = 0;
PanelRelleno0 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno0,coordenadas);
PanelServidor.add(PanelRelleno0);	

coordenadas.ipadx = 205;							
coordenadas.gridx = 1;
coordenadas.gridy = 0;
PanelRelleno1 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno1,coordenadas);
PanelServidor.add(PanelRelleno1);

coordenadas.ipadx = 205;
coordenadas.gridx = 2;
coordenadas.gridy = 0;
PanelRelleno2 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno2,coordenadas);
PanelServidor.add(PanelRelleno2);
	
coordenadas.ipadx = 75;								
coordenadas.gridx = 3;
coordenadas.gridy = 0;
PanelRelleno3 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno3,coordenadas);
PanelServidor.add(PanelRelleno3);

coordenadas.gridx = 0;								
coordenadas.gridy = 1;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelRelleno4 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno4,coordenadas);
PanelServidor.add(PanelRelleno4);

coordenadas.gridx = 0;								//Agregamos la etiqueta: Mensaje entrante
coordenadas.gridy = 2;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelEtiquetaEntrante = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelEtiquetaEntrante,coordenadas);
PanelServidor.add(PanelEtiquetaEntrante);
PanelEtiquetaEntrante.add(EtiquetaEntrante);

coordenadas.gridx = 3;								//Agregamos la etiqueta: Usuarios
coordenadas.gridy = 2;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelEtiquetaUsuarios = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelEtiquetaUsuarios,coordenadas);
PanelServidor.add(PanelEtiquetaUsuarios);
PanelEtiquetaUsuarios.add(EtiquetaUsuarios);

coordenadas.gridx = 0;								//Agregamos el area de los mensajes entrantes
coordenadas.gridy = 3;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
AreaMensajeEntrante = new TextArea(16,65);
Estructura.setConstraints(AreaMensajeEntrante,coordenadas);
PanelServidor.add(AreaMensajeEntrante);

coordenadas.gridx = 0;								//Agregamos paneles de relleno
coordenadas.gridy = 4;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelRelleno5 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno5,coordenadas);
PanelServidor.add(PanelRelleno5);

coordenadas.gridx = 0;								//Agregamos la etiqueta: Mensaje saliente
coordenadas.gridy = 5;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelEtiquetaSaliente = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelEtiquetaSaliente,coordenadas);
PanelServidor.add(PanelEtiquetaSaliente);
PanelEtiquetaSaliente.add(EtiquetaSaliente);

coordenadas.gridx = 0;								//Agregamos el area de los mensajes salientes
coordenadas.gridy = 6;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
MensajeSaliente = new TextField(65);					     	
Estructura.setConstraints(MensajeSaliente,coordenadas);
PanelServidor.add(MensajeSaliente);

coordenadas.gridx = 0;								//Agregamos paneles de relleno
coordenadas.gridy = 7;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelRelleno6 = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelRelleno6,coordenadas);
PanelServidor.add(PanelRelleno6);

coordenadas.gridx = 0;								//Agregamos boton Enviar y varios a uno		
coordenadas.gridy = 8;
coordenadas.gridwidth = GridBagConstraints.RELATIVE;
PanelBotonEnviar = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelBotonEnviar,coordenadas);
PanelServidor.add(PanelBotonEnviar);
PanelBotonEnviar.add(BotonEnviar);
PanelBotonEnviar.add(BotonExcluir);

coordenadas.gridx = 3;								//Agregamos la lista de usuarios
coordenadas.gridy = 3;
coordenadas.gridheight = 5;
PanelListaUsuarios = new Panel(new FlowLayout(FlowLayout.CENTER));
Estructura.setConstraints(PanelListaUsuarios,coordenadas);
PanelServidor.add(PanelListaUsuarios);
PanelListaUsuarios.add(ListaUsuarios);

InstanciaRecibirCS = new TelecomunicacionRxCS(AreaMensajeEntrante, ListaUsuarios);	//Lanzamos el hilo continuo de recepcion de mensajes del servidor como cliente
Lista = new ClaseLista();							//Creamos la cabecera de la lista que contiene los nodo de los usuarios activos	
TransmisionS = new TelecomunicacionTx();					//Instancia de la clase que se encargara del enrutamineto y la Tx de los mensajes del servidor como usuario					
RecepcionS = new TelecomunicacionRx(8002,TransmisionS,Lista);			//Instancia de la clase que se encargara de la RX de los mensajes introducidos por el servidor como usuario
Lista.InsertarNodo(RecepcionS, TransmisionS, "Servidor");			//Insertamos el ultimo nodo de la lista que contiene al servidor como cliente activo de Chat	
InstanciaEnviarCS = new TelecomunicacionTxCS();					//Instanciamos los sockets de transmision del servidor como cliente					

BotonEnviar.addActionListener(new Enviar());					//Registro de los objetos que generan eventos						
MensajeSaliente.addActionListener(new Enviar());
BotonExcluir.addActionListener(new Excluir());

PanelServidor.setBackground(Color.lightGray);					//Fondo de la ventana
MarcoServidor.setSize(800,450);
MarcoServidor.add(PanelServidor);

MarcoServidor.addWindowListener(						//Cerrar la ventana Interfaz de Chat
	new WindowAdapter()
	{
	public void windowClosing(WindowEvent evento)
	{
	System.exit(0);
	}
	});

MarcoServidor.show();								//Mostramos la ventana

ManejarAcceso = new Animovimiento(Lista);					//Se Lanzara el Hilo para que el servidor trabaje siempre
} 	

public static void main(String[] args)						//Main de la aplicacion
{
InterfazServidor mostrar = new InterfazServidor();
}


private class Excluir implements ActionListener					//Gestion a los eventos de EXCLUIR
{
private RestriccionNoLogin Nologin;

public void actionPerformed(ActionEvent Evento)					//Metodo que responde a los eventos sobre el Boton excluir  	
	{
	int i=0;	
	String Logines[]=ListaUsuarios.getSelectedItems();			//Obtenemos la matriz con todos los logines seleccionados		
	
		try
		{	
			for(;;)
			{
			String controlador = Logines[i];			//Obtenemos el indice de los logines que hay selecionados		
			i++;
			}
		}

		catch(ArrayIndexOutOfBoundsException e)
		{
		i--;
		}

		if(i!=-1)							//Si por lo menos hay un login seleccionado 
		{
		InstanciaEnviarCS.ExcluirUsuario(Logines, i);			//Se realiza la excluison de los logines seleccionados
		}
		
		else								//Si no hay logines seleecionados se lemuestra al usuario un cruadro de dialogo 	
		{								//indicandole que debe por lo menos selecionar un login
		Nologin = new RestriccionNoLogin(MarcoServidor); 
		}	
	}

}

private class Enviar implements ActionListener					//Gestion a los eventos de ENVIAR
{
private RestriccionNoLogin Nologin;  

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
		
		if(i!=-1)							//Si por lo menos hay un login seleccionado 
		{
		InstanciaEnviarCS.correr(MensajeSaliente, Logines,i,"Servidor");//Se realiza el envio del mesaje;
		MensajeSaliente.setText("");
		}
		
		else								//Si no hay logines seleecionados se lemuestra al usuario un cruadro de dialogo 	
		{								//indicandole que debe por lo menos selecionar un login	
		Nologin = new RestriccionNoLogin(MarcoServidor); 
		}
	}

}




}