import java.awt.*;

public class TCPTeletipoConHilos {

  public static void main(String[] args) {
    Frame MiMarco = new Frame();
    Panel Visor = new Panel(new GridLayout(2,1));
    Panel AreaEnviar =  new Panel(new FlowLayout(FlowLayout.LEFT));
    Panel AreaRecibir = new Panel(new FlowLayout(FlowLayout.LEFT));
    TextField Entrada = new TextField(30);
    TextField Salida  = new TextField(30);

    AreaEnviar.add(new  Label("Enviado :"));
    AreaEnviar.add(Entrada);
    AreaRecibir.add(new Label("Recibido:"));
    AreaRecibir.add(Salida);
    Visor.add(AreaEnviar);
    Visor.add(AreaRecibir);
    MiMarco.add(Visor);
    MiMarco.setSize(400,90);
    MiMarco.setTitle("Talk con TCP");  
    MiMarco.setVisible(true);

    if (args.length==3) {
      String HostRemoto = args[0];
      int PuertoLocal = Integer.parseInt(args[2]);
      int PuertoRemoto = Integer.parseInt(args[1]);
      TCPTeletipoServidorConHilos InstanciaServidor = new TCPTeletipoServidorConHilos(PuertoLocal,Salida);
      TCPTeletipoClienteConHilos  InstanciaCliente  = new TCPTeletipoClienteConHilos(HostRemoto,PuertoRemoto,Entrada);
      InstanciaServidor.start();
      InstanciaCliente.start();
    }
    else {
      System.out.println("Es necesario pasar tres argumentos (host remoto, puerto remoto, puerto local)");
      System.exit(0);
    }

  }

}