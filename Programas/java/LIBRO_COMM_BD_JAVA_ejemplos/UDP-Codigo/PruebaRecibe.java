import java.awt.*;

public class PruebaRecibe extends Object {
 
   public PruebaRecibe() {
     final int  Puerto = 5000;
     final  int TamanioMaximoMensaje = 20;

     String MensajeRecibido;
     TRecibeUDP InstanciaRecibeUDP = new TRecibeUDP();
     Frame Marco = new Frame("EnvioUDP");
     Panel panel = new Panel();
     Label EtiquetaMensaje =  new Label ("Mensaje:");
     Label PosicionXY = new Label("");
 
     Marco.setSize(200,80);
     panel.setLayout(new GridLayout(2,1));
     Marco.add(panel);  
     panel.add(EtiquetaMensaje);
     panel.add(PosicionXY);
     Marco.show();

     do {
       MensajeRecibido = InstanciaRecibeUDP.Recibe(Puerto,TamanioMaximoMensaje);
       try {
       int i = MensajeRecibido.indexOf(" ");
       if (i!=-1&&MensajeRecibido.indexOf("*")==0) {
         String X = MensajeRecibido.substring(1,i);
         String Y = MensajeRecibido.substring(i+1,MensajeRecibido.length());  
         PosicionXY.setText("("+X+", "+Y+")");
       }
       else
         EtiquetaMensaje.setText("Mensaje: "+MensajeRecibido);
       } catch (Exception e){ }
        
     } while (MensajeRecibido.length()!=0);

     System.exit(1);
 } // Constructor

 public static void main(String[] args) {
     PruebaRecibe MiClaseRecibe = new PruebaRecibe();
 } //main
 
} // clase