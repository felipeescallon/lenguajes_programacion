import java.awt.*;
import java.awt.event.*;

public class GUIAccesoBD {

  private TextField CampoDNI;
  private TextField CampoNombre;
  private TextField CampoApellido;
  private TextField CampoEdad; 
  private TextArea AreaDeListado;
  private Panel MiPanel;   
  
  private int PosicionBuscada = 1;

  GUIAccesoBD() {
    MiPanel = new Panel(new GridLayout(1,2));
    Panel PanelIzq = new Panel(new GridLayout(6,1));
    Panel PanelDNI = new Panel(new FlowLayout(FlowLayout.LEFT));
    Panel PanelNombre = new Panel(new FlowLayout(FlowLayout.LEFT));
    Panel PanelApellido = new Panel(new FlowLayout(FlowLayout.LEFT));
    Panel PanelEdad = new Panel(new FlowLayout(FlowLayout.LEFT));
    Panel PanelAccion = new Panel(new FlowLayout(FlowLayout.CENTER));
    Panel PanelControles = new Panel(new FlowLayout(FlowLayout.CENTER));

    Label EtiquetaDNI = new Label("DNI");
    Label  EtiquetaNombre = new Label("Nombre");
    Label EtiquetaApellido = new Label("Apellido");
    Label EtiquetaEdad = new Label("Edad");

    CampoDNI = new TextField(9);
    CampoNombre = new TextField(15); 
    CampoApellido = new TextField(30); 
    CampoEdad = new TextField(3);   

    AreaDeListado = new TextArea(); 
    
    Button BotonConsultar = new Button("Consultar");
    Button BotonInsertar = new Button("Insertar");
    Button BotonModificar = new Button("Modificar");
    Button BotonBorrar = new Button("Borrar");
    Button BotonListar = new Button("Listar");

    Button BotonPrimero = new Button("|<"); BotonPrimero.setName("1");
    Button BotonMenosCinco = new Button("--"); BotonMenosCinco.setName("2");
    Button BotonAnterior = new Button("-"); BotonAnterior.setName("3");
    Button BotonSiguiente = new Button("+"); BotonSiguiente.setName("4");
    Button BotonMasCinco = new Button("++"); BotonMasCinco.setName("5");
    Button BotonUltimo = new Button(">|"); BotonUltimo.setName("0");

    PanelIzq .add(PanelDNI);
    PanelIzq .add(PanelNombre);
    PanelIzq .add(PanelApellido);
    PanelIzq .add(PanelEdad);
    PanelIzq .add(PanelAccion);
    PanelIzq .add(PanelControles);
    MiPanel.add(PanelIzq);
    MiPanel.add(AreaDeListado);

    PanelDNI.add (EtiquetaDNI); PanelDNI.add(CampoDNI); 
    PanelNombre.add (EtiquetaNombre); PanelNombre.add(CampoNombre);  
    PanelApellido.add (EtiquetaApellido); PanelApellido.add(CampoApellido);  
    PanelEdad.add (EtiquetaEdad); PanelEdad.add(CampoEdad);  
    PanelAccion.add(BotonConsultar);
    PanelAccion.add(BotonInsertar); 
    PanelAccion.add(BotonModificar); 
    PanelAccion.add(BotonBorrar);  
    PanelAccion.add(BotonListar); 
    PanelControles.add(BotonPrimero);  
    PanelControles.add(BotonMenosCinco);  
    PanelControles.add(BotonAnterior);   
    PanelControles.add(BotonSiguiente);  
    PanelControles.add(BotonMasCinco);  
    PanelControles.add(BotonUltimo);  

    BotonConsultar.addActionListener(new ConsultarDatos());
    BotonInsertar.addActionListener(new InsertarDatos());
    BotonModificar.addActionListener(new ModificarDatos());
    BotonBorrar.addActionListener(new BorrarDatos());
    BotonListar.addActionListener(new ListarDatos());

    BotonPrimero.addActionListener(new Controles());
    BotonMenosCinco.addActionListener(new Controles());
    BotonAnterior.addActionListener(new Controles());
    BotonSiguiente.addActionListener(new Controles());
    BotonMasCinco.addActionListener(new Controles());
    BotonUltimo.addActionListener(new Controles());
  }


  public Panel DamePanel() {
    return MiPanel;
  }


  private class ConsultarDatos implements ActionListener {
     public void actionPerformed(ActionEvent Evento) {
        ObConsulta InstanciaConsulta = new ObConsulta(CampoDNI.getText()); 
        PosicionBuscada = InstanciaConsulta.PosicionEncontrada(); 

        if (PosicionBuscada!=0) {
             ObRegistro InstanciaFila = InstanciaConsulta.DameDatos();
             CampoDNI.setText(InstanciaFila.DameDNI());
             CampoNombre.setText(InstanciaFila.DameNombre()); 
             CampoApellido.setText(InstanciaFila.DameApellido()); 
             CampoEdad.setText(String.valueOf(InstanciaFila.DameEdad()));  
        } else {
             CampoNombre.setText("------");
             CampoApellido.setText("------"); 
             CampoEdad.setText("---");   
       }
    }
  }


  private class InsertarDatos implements ActionListener {
     public void actionPerformed(ActionEvent Evento) {
        ObRegistro InstanciaFila = new ObRegistro(CampoDNI.getText(), CampoNombre.getText(),  CampoApellido.getText(), 
                                      			       Integer.parseInt(CampoEdad.getText()));
        ObInserccion InstanciaInserccion = new ObInserccion(InstanciaFila); 
        CampoDNI.setText(""); CampoNombre.setText("");
        CampoApellido.setText(""); CampoEdad.setText("");  
        PosicionBuscada=1; 
     }
  }


  private class ModificarDatos implements ActionListener {
     public void actionPerformed(ActionEvent Evento) {
        ObConsulta InstanciaConsulta = new ObConsulta(CampoDNI.getText()); 
        PosicionBuscada = InstanciaConsulta.PosicionEncontrada(); 

        if (PosicionBuscada!=0) {
          ObRegistro InstanciaFila = new ObRegistro(CampoDNI.getText(), CampoNombre.getText(),  CampoApellido.getText(), 
                                      			       Integer.parseInt(CampoEdad.getText()));
          ObModificacion InstanciaModificacion = new ObModificacion(PosicionBuscada, InstanciaFila);
          CampoDNI.setText(""); CampoNombre.setText("");
          CampoApellido.setText(""); CampoEdad.setText(""); 
        } else {
           CampoNombre.setText("------");
           CampoApellido.setText("------"); 
           CampoEdad.setText("---");   
       }
    }
  }


  private class BorrarDatos implements ActionListener {
     public void actionPerformed(ActionEvent Evento) {
        ObConsulta InstanciaConsulta = new ObConsulta(CampoDNI.getText()); 
        PosicionBuscada = InstanciaConsulta.PosicionEncontrada(); 

        if (PosicionBuscada!=0) {
          ObBorrado InstanciaBorrado = new ObBorrado(PosicionBuscada);
          CampoDNI.setText(""); CampoNombre.setText("");
          CampoApellido.setText(""); CampoEdad.setText(""); 
        } else {
          CampoNombre.setText("------");
          CampoApellido.setText("------"); 
          CampoEdad.setText("---");   
       }
    }
  }


  private class ListarDatos implements ActionListener {
     public void actionPerformed(ActionEvent Evento) {
        ObListado InstanciaListado = new ObListado(AreaDeListado); 
        PosicionBuscada = 1;
     }
  }


    private class Controles implements ActionListener {
     public void actionPerformed(ActionEvent Evento) {
        Button Pulsado = (Button) Evento.getSource();
        String Identificador = Pulsado.getName();
        ObPosicionamiento InstanciaPosicionamiento = new ObPosicionamiento(Identificador,PosicionBuscada);

        ObRegistro InstanciaFila = InstanciaPosicionamiento.DameDatos();
        CampoDNI.setText(InstanciaFila.DameDNI());
        CampoNombre.setText(InstanciaFila.DameNombre()); 
        CampoApellido.setText(InstanciaFila.DameApellido()); 
        CampoEdad.setText(String.valueOf(InstanciaFila.DameEdad()));

        PosicionBuscada = InstanciaPosicionamiento.Posicion();    
     }
  }


}