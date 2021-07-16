import java.io.*;
//////////////////////////////////////////////////////////////////
// Sincronización de hilos.
//
public class Test
{
  public static void main(String[] args)
  {
    CMatriz matriz = new CMatriz(10);
    Productor productor1 = new Productor(matriz);
    Consumidor consumidor1 = new Consumidor(matriz);

    System.out.println("Pulse [Entrar] para continuar y");
    System.out.println("vuelva a pulsar [Entrar] para finalizar.");
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);
    
    try
    {
      br.readLine(); // ejecución detenida hasta pulsar [Entrar]
      // Iniciar la ejecución de los hilos
      productor1.start();
      consumidor1.start();
      br.readLine(); // ejecución detenida hasta pulsar [Entrar]
    }
    catch (IOException e) {}
    // Permitir a los hilos finalizar
    productor1.terminar();
    consumidor1.terminar();
  }
}
//////////////////////////////////////////////////////////////////
