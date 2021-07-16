import java.awt.*;
import java.awt.event.*;

// *********************************************************************
// *                    Servicio Talk                                  *
// *   nota: los puertos origen y destino se deben cruzar en las       *
// *         dos instancias que se creen del talk                      *
// *    usar: java Talk PuertoOrigen PuertoDestino                     *
// *********************************************************************  
public class Talk {
   static final  int TamanioMaximoMensaje = 90; 

   TextArea AreaRecibir; 
   TextArea AreaEnviar; 
   TextField HostDestino;
   Button BotonEnviar;
   String MensajeRecibido; 
   int PuertoOrigen, PuertoDestino;
  
   TRecibeUDP InstanciaRecibeUDP; 
   TEnviaUDP InstanciaEnviaUDP;  

   public Talk(int PuertoOrigen, int PuertoDestino) {
     this.PuertoOrigen = PuertoOrigen;
     this.PuertoDestino = PuertoDestino;
     InstanciaRecibeUDP = new TRecibeUDP();
     InstanciaEnviaUDP = new TEnviaUDP();
     Frame Marco = new Frame("Talk");
     Panel panel = new Panel();
     Label EtiquetaMensajeSaliente = new Label ("Mensaje Saliente:"); 
     Label EtiquetaMensajeEntrante = new Label ("Mensaje Entrante:");
     Label EtiquetaHostDestino = new Label ("Host destino");
     AreaRecibir = new TextArea(3,24);
     AreaEnviar = new TextArea(3,24);
     HostDestino = new TextField("127.0.0.1");
     BotonEnviar = new Button("Enviar");

     Marco.setSize(250,280);
     Marco.setLayout(new BorderLayout());
     Marco.add("Center",panel);
     panel.add(EtiquetaHostDestino);
     panel.add(HostDestino);
     panel.add(BotonEnviar);
     panel.add(EtiquetaMensajeSaliente);
     panel.add(AreaEnviar);
     panel.add(EtiquetaMensajeEntrante);
     panel.add(AreaRecibir);
     Marco.show();

     TalkRecibir HiloRecibir = new TalkRecibir();
     TalkEnviar HiloEnviar = new TalkEnviar();

     HiloRecibir.start();
     HiloEnviar.start();

 } // Constructor


public static void main(String[] args) {
  if (args.length!=2) {
   System.out.println("usar: java Talk PuertoOrigen PuertoDestino, ejemplo: java Talk 5000 5002");
   System.exit(1);
  }
  Talk Instancia = new Talk(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
}
 


// *********************************************************************
// *                    Thread para recibir datos                      *
// *********************************************************************  
private class TalkRecibir extends Thread {

  public void run() {
     do {
       MensajeRecibido = InstanciaRecibeUDP.Recibe(PuertoOrigen,TamanioMaximoMensaje);
       AreaRecibir.setText(MensajeRecibido);   
     } while (MensajeRecibido.length()!=0);
     System.exit(1); 
  }
} //class TalkRecibir


// *********************************************************************
// *                        Thread de envio                            *
// *********************************************************************                     
private class TalkEnviar extends Thread {

  public void run() {
     BotonEnviar.addActionListener(new RespuestaAEnviar());
  }

  private class RespuestaAEnviar extends Object implements ActionListener {
    public void actionPerformed(ActionEvent e) {
       String Mensaje = AreaEnviar.getText();
       InstanciaEnviaUDP.Envia(Mensaje,Mensaje.length(),HostDestino.getText(),PuertoDestino);
       AreaEnviar.setText("");
       if (Mensaje.length() ==0)
         System.exit(1);   // Al mandar un mensaje vacio se abandona el programa
    } // actionPerformed
  } // RespuestaAEnviar

} //class TalkEnviar

} // clase