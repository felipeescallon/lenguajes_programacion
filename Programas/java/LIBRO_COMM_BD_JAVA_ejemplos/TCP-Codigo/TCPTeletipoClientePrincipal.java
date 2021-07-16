public class TCPTeletipoClientePrincipal {

  public static void main(String[] args) {
      int Puerto = Integer.parseInt(args[1]);
      String Host = args[0];
      TCPTeletipoCliente  InstanciaCliente = new TCPTeletipoCliente(Host,Puerto);
  }

}