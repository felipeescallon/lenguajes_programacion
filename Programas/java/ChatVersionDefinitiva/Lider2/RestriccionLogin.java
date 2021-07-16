import java.awt.*;
import java.awt.event.*;

public class RestriccionLogin					 			//Restriccion
{
private Dialog DialogRestriccion;
private Panel PanelPrincipal;
private Panel PanelRelleno0;
private Panel PanelEtiquetaRestriccion;
private Panel PanelEtiquetaRestriccion1;
private Panel PanelBotonAceptar;
private Button BotonAceptar;
private Label EtiquetaRestriccion;
private Label EtiquetaRestriccion1;

public RestriccionLogin(Frame MarcoMaestro)						
{
DialogRestriccion = new Dialog(MarcoMaestro,"Restriccion",true);			//Instanicmaos el cuadro de dialogo
PanelPrincipal = new Panel(new GridLayout(4,1));				        //Instanciamos los panel 
PanelRelleno0 = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelEtiquetaRestriccion = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelEtiquetaRestriccion1 = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelBotonAceptar = new Panel(new FlowLayout(FlowLayout.CENTER));
EtiquetaRestriccion = new Label("Otro usuario selecciono el login escrito");		//Intanciamos la etiqueta
EtiquetaRestriccion1 = new Label("Por favor ingrese otro");
BotonAceptar = new Button("Aceptar");							//Instanciamos el boton

PanelEtiquetaRestriccion.add(EtiquetaRestriccion);					//Agrgamos los elementos a los paneles secundarios						
PanelEtiquetaRestriccion1.add(EtiquetaRestriccion1);
PanelBotonAceptar.add(BotonAceptar);

PanelPrincipal.add(PanelRelleno0);							//Agregamos los paneles secundarios al
PanelPrincipal.add(PanelEtiquetaRestriccion);						//Panel principal
PanelPrincipal.add(PanelEtiquetaRestriccion1);	
PanelPrincipal.add(PanelBotonAceptar);	

BotonAceptar.addActionListener(new CerrarDialogo());					//Registro de los eventos		

PanelPrincipal.setBackground(Color.lightGray);	
DialogRestriccion.setSize(350,250);							//Tamaño de la ventana
DialogRestriccion.add(PanelPrincipal);							//Agregamos el panel al cuadro de dialogo

DialogRestriccion.addWindowListener(						       //Cerrar la ventana Interfaz de Chat
		new WindowAdapter()
		{
		public void windowClosing(WindowEvent evento)
		{
		DialogRestriccion.hide();
		}
		});

DialogRestriccion.show();								//Mostramos el cuadro de dialogo
}

private class CerrarDialogo implements ActionListener					//Gestion a los eventos de Aceptar
{
public void actionPerformed(ActionEvent Evento)
	{
	DialogRestriccion.hide();							//Cerrar el cuadro de dialogo
	}


}

}