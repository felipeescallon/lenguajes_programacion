import java.io.*;
//////////////////////////////////////////////////////////////////
// Sincronizaci�n de hilos.
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
      br.readLine(); // ejecuci�n detenida hasta pulsar [Entrar]
      // Iniciar la ejecuci�n de los hilos
      productor1.start();
      consumidor1.start();
      br.readLine(); // ejecuci�n detenida hasta pulsar [Entrar]
    }
    catch (IOException e) {}
    // Permitir a los hilos finalizar
    productor1.terminar();
    consumidor1.terminar();
  }
}
//////////////////////////////////////////////////////////////////
