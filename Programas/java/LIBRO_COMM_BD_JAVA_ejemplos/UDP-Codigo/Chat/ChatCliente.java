import java.awt.*;
import java.awt.event.*;
import java.util.*;

// *********************************************************************
// *                    Cliente Chat                                   *
// *********************************************************************  
public class ChatCliente {
   final int TAMANIO_MAX_MENSAJE = 40;
   final int TIMEOUT=200;
   final int PUERTO_ORIGEN=5000, PUERTO_DESTINO=5002;

   TextArea AreaRecibir; 
   TextField TextoEnviar; 
   TextField HostDestino;
   Button BotonEnviar;
   String MensajeRecibido; 

   TRecibeUDP InstanciaRecibeUDP; 
   TEnviaUDP InstanciaEnviaUDP; 

   public ChatCliente(String Host) {
     InstanciaRecibeUDP = new TRecibeUDP();
     InstanciaEnviaUDP = new TEnviaUDP();
     Frame Marco = new Frame("Chat");
     Panel PanelSur = new Panel();
     Label EtiquetaMensajeSaliente = new Label ("Mensaje:"); 
     Label EtiquetaHostDestino = new Label ("Host destino");
     TextoEnviar = new TextField(25);
     AreaRecibir = new TextArea();
     HostDestino = new TextField(Host);
     BotonEnviar = new Button("Enviar");

     Marco.setSize(600,250);
     Marco.setLayout(new BorderLayout());
     Marco.add("South",PanelSur);
     PanelSur.add(EtiquetaHostDestino);
     PanelSur.add(HostDestino);
     PanelSur.add(EtiquetaMensajeSaliente);
     PanelSur.add(BotonEnviar);
     PanelSur.add(TextoEnviar);
     Marco.add("Center",AreaRecibir);
     Marco.show();

     ChatRecibir HiloRecibir = new ChatRecibir();
     ChatEnviar HiloEnviar = new ChatEnviar();

     HiloRecibir.start();
     HiloEnviar.start();

 } // Constructor


public static void main(String[] args) {
  ChatCliente Instancia = new ChatCliente(args[0]);
}
 


// *********************************************************************
// *                    Thread para recibir datos                      *
// *********************************************************************  

private class ChatRecibir extends Thread {

  public void run() {
     do {
       InstanciaEnviaUDP.Envia(" ",1,HostDestino.getText(),PUERTO_DESTINO); 
       MensajeRecibido = InstanciaRecibeUDP.Recibe(PUERTO_ORIGEN,400,TIMEOUT);  
       AreaRecibir.setText(MensajeRecibido); 
       try {
         Thread.sleep(5000l);
       } catch (InterruptedException e) {}  
     } while (true);
  }
} //class ChatRecibir


// *********************************************************************
// *                        Thread de envio                            *
// *********************************************************************                     
private class ChatEnviar extends Thread {

  public void run() {
     BotonEnviar.addActionListener(new MensajeAEnviar());
  }

  private class MensajeAEnviar extends Object implements ActionListener {
    public void actionPerformed(ActionEvent e) {
       String Mensaje = "0"+TextoEnviar.getText();
       InstanciaEnviaUDP.Envia(Mensaje,Mensaje.length(),HostDestino.getText(),PUERTO_DESTINO);
       TextoEnviar.setText("");
    } // actionPerformed
  } // MensajeAEnviar

} //class ChatCliente

} // clase