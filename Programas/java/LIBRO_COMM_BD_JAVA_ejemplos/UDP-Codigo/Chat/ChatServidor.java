public class ChatServidor {

  ChatServidor() {
    final int PUERTO_ORIGEN = 5002;
    final int PUERTO_DESTINO = 5000;
    final int TAMANIO_MENSAJE=40;
    final int NUM_MENSAJES=10;
    final char INSERTAR_FRASE='0';
    final char DEVOLVER_FRASES='1';
    final int TIMEOUT=0;

    String MensajeRecibido, MensajeAEnviar,IPRemota;
    String[] MensajesRecibidos;
    char TipoMensaje;
    int PuertoRemoto;

    TRecibeUDP InstanciaRecibeUDP = new TRecibeUDP();
    TEnviaUDP InstanciaEnviaUDP = new TEnviaUDP();
    Conversacion ConversacionChat = new Conversacion(NUM_MENSAJES);
    do {
     MensajeRecibido = InstanciaRecibeUDP.Recibe(PUERTO_ORIGEN,TAMANIO_MENSAJE,TIMEOUT);
     TipoMensaje = MensajeRecibido.charAt(0);
     if (TipoMensaje==INSERTAR_FRASE) {
       MensajeRecibido=MensajeRecibido.substring(1,MensajeRecibido.length());
       ConversacionChat.InsertarMensaje(MensajeRecibido);
       System.out.println("Se ha recibido el mensaje: "+MensajeRecibido);
     } else { // (TipoMensaje==DEVOLVER_FRASE)
        MensajeAEnviar="";
        MensajesRecibidos=ConversacionChat.DameMensajes();
        for (int i=0;i<NUM_MENSAJES;i++)
          MensajeAEnviar = MensajeAEnviar+MensajesRecibidos[i]+"\n";
        IPRemota=InstanciaRecibeUDP.DameIPRemota();
        InstanciaEnviaUDP.Envia(MensajeAEnviar,TAMANIO_MENSAJE*NUM_MENSAJES,IPRemota,PUERTO_DESTINO);
     }
    } while (true);
    
  }

  public static void main(String[] args){
    ChatServidor InstanciaChat = new ChatServidor();
  }



private class Conversacion {
  private String[] Mensajes;
  private int NumMensajes;

  Conversacion(int NumMensajes) {
    Mensajes = new String[NumMensajes];
    this.NumMensajes = NumMensajes;
    for (int i=0;i<NumMensajes;i++)
      Mensajes[i]="";
  }

  public void InsertarMensaje(String Mensaje) {
    for (int i=NumMensajes-2;i!=-1;i--)
      Mensajes[i+1]=Mensajes[i];
    Mensajes[0] = Mensaje;
  }

  public String[] DameMensajes() {
    return Mensajes;
  }

  public void ImprimeMensajes() {
    for (int i=0;i<NumMensajes;i++)
      System.out.println(Mensajes[i]);
    System.out.println();
  }

}


} // ChatServidor

