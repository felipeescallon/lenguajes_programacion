public class TCPTeletipoServidorPrincipal {

  public static void main(String[] args) {
      int Puerto = Integer.parseInt(args[0]);
      TCPTeletipoServidor InstanciaServidor = new TCPTeletipoServidor(Puerto);
  }

}