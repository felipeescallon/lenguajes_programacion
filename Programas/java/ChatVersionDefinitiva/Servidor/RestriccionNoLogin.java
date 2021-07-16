import java.awt.*;
import java.awt.event.*;

public class RestriccionNoLogin 					 				//Restriccion
{
private Dialog DialogRestriccion;
private Panel PanelPrincipal;
private Panel PanelRelleno0;
private Panel PanelEtiquetaRestriccion;
private Panel PanelBotonAceptar;
private Button BotonAceptar;
private Label EtiquetaRestriccion;

public RestriccionNoLogin(Frame MarcoMaestro)						
{
DialogRestriccion = new Dialog(MarcoMaestro,"Restriccion",true);			//Instanicmaos el cuadro de dialogo
PanelPrincipal = new Panel(new GridLayout(3,1));				        //Instanciamos los panel 
PanelRelleno0 = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelEtiquetaRestriccion = new Panel(new FlowLayout(FlowLayout.CENTER));
PanelBotonAceptar = new Panel(new FlowLayout(FlowLayout.CENTER));
EtiquetaRestriccion = new Label("No hay logines seleccionados");			//Intanciamos la etiqueta
BotonAceptar = new Button("Aceptar");							//Instanciamos el boton

PanelEtiquetaRestriccion.add(EtiquetaRestriccion);					//Agrgamos los elementos a los paneles secundarios						
PanelBotonAceptar.add(BotonAceptar);

PanelPrincipal.add(PanelRelleno0);							//Agregamos los paneles secundarios al
PanelPrincipal.add(PanelEtiquetaRestriccion);						//Panel principal
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