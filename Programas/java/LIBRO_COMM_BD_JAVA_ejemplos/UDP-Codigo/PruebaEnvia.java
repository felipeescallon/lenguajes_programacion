import java.awt.*;
import java.awt.event.*;

public class PruebaEnvia extends Object {

   static final int TamanioMaximoMensaje = 20; 
   Frame Marco;
   Panel panel;
   Label EtiquetaMensaje;
   TextField CampoMensaje;
   TextField HostDestino;
   TextField Puerto;
   TEnviaUDP EnviaFrase;

   public PruebaEnvia() {
     Marco = new Frame("EnvioUDP");
     panel = new Panel();
     EtiquetaMensaje =  new Label ("Mensaje: "); 
     CampoMensaje = new TextField(TamanioMaximoMensaje);
     HostDestino = new TextField("127.0.0.1",15);
     Puerto = new TextField("5000",4);
     Marco.setSize(500,200);
     Marco.add (panel);  
     panel.add (HostDestino);
     panel.add (Puerto);
     panel.add (EtiquetaMensaje);
     panel.add (CampoMensaje);
     Marco.show();
     EnviaFrase = new TEnviaUDP();

     CampoMensaje.addActionListener(new RespuestaAEnviar());
     panel.addMouseMotionListener(new MovimientoDelRaton());
   } // constructor


  private class RespuestaAEnviar extends Object implements ActionListener {
    public void actionPerformed(ActionEvent e) {
       String Mensaje = CampoMensaje.getText();
       EnviaFrase.Envia(Mensaje,Mensaje.length(),HostDestino.getText(),Integer.parseInt(Puerto.getText()));
       System.out.println("Datagrama enviado: " + Mensaje + " , "+Mensaje.length()+" Caracteres");
       if (Mensaje.length() ==0)
         System.exit(1);   // Al mandar un mensaje vacio se abandona el programa
    } // actionPerformed
  } // RespuestaAEnviar

  private class MovimientoDelRaton extends Object implements MouseMotionListener {
    public void mouseMoved(MouseEvent e) {
       String Mensaje="*"+String.valueOf(e.getX())+" "+String.valueOf(e.getY());
       EnviaFrase.Envia(Mensaje,Mensaje.length(),HostDestino.getText(),Integer.parseInt(Puerto.getText()));
       System.out.println("Datagrama enviado: " + Mensaje + " , "+Mensaje.length()+" Caracteres");
       if (Mensaje.length() ==0)
         System.exit(1);   // Al mandar un mensaje vacio se abandona el programa
    } // mouseMoved

    public void mouseDragged(MouseEvent e) {
    }

  } // MovimientoDelRaton

 public static void main(String[] args) {
     PruebaEnvia MiClaseEnvia = new PruebaEnvia();
 } //main
 
} // clase