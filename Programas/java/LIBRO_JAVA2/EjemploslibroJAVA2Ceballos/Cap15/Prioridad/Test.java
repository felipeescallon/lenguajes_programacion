//////////////////////////////////////////////////////////////////
// Hilos.
//
public class Test
{
  public static void main(String[] args)
  {
    int nCuentas = 2; // n�mero de contadores
    // Crear y lanzar el hilo Cuentas
    Cuentas hiloCuantas = new Cuentas(nCuentas);
    hiloCuantas.start();
  }
}
//////////////////////////////////////////////////////////////////
