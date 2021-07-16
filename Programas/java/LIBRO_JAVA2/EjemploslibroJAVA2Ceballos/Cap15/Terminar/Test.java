import java.io.*;
//////////////////////////////////////////////////////////////////
// Terminar un hilo.
//
public class Test
{
  public static void main(String[] args)
  {
    // Lanzar el demonio dbip
    CDemonio dbip = new CDemonio();
    
    // Lanzar el hilo cuentaAdelante
    ContadorAdelante cuentaAdelante = new ContadorAdelante("Contador+");
    
    System.out.println("Pulse [Entrar] para finalizar");
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);
    try
    {
      br.readLine(); // ejecución detenida hasta pulsar [Entrar]
    }
    catch (IOException e) {}
    // Permitir al hilo cuentaAdelante finalizar
    cuentaAdelante.terminar();
  }
}
//////////////////////////////////////////////////////////////////
