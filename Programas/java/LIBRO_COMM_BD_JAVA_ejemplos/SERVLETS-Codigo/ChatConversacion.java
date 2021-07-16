public class ChatConversacion {

  private static final int NUM_LINEAS=10;
  private static String[]Conversacion = new String[NUM_LINEAS];

  public synchronized static void InsertaMensaje(String Mensaje) {
    for(int i=NUM_LINEAS-2;i>=0;i--)
      Conversacion[i+1] = Conversacion[i];
    Conversacion[0]=Mensaje;
  }

  public synchronized static String[] DameConversacion() {
    return Conversacion;
  }

}