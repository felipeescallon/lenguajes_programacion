/**
 * Esta clase implementa una cuenta bancaria que
 * simula el comportamiento b�sico de una cuenta 
 * abierta en una entidad bancaria cualquiera.
 */
public class CCuentaBancaria
{
  private double tipoDeInter�s;
  private double saldo;
  
  public void EstablecerTipoDeInter�s(double ti)
  {
    if ( ti < 0)
    {
      System.out.println("El tipo de inter�s no puede ser negativo");
      return; // retornar
    }
    tipoDeInter�s = ti;
  }

  public void IngresarDinero(double ingreso)
  {
    saldo += ingreso;
  }

  public void RetirarDinero(double cantidad)
  {
    if ( saldo - cantidad < 0)
    {
      System.out.println("No tiene saldo suficiente");
      return;
    }
    // Hay saldo suficiente. Retirar la cantidad.
    saldo -= cantidad;
  }

  public double SaldoActual()
  {
    return saldo;
  }

  public void AbonarIntereses()
  {
    saldo += saldo * tipoDeInter�s / 100;
  }

  public static void main (String[] args)
  {
    // Abrir una cuenta con 1.000.000 a un 2%
    CCuentaBancaria Cuenta01 = new CCuentaBancaria();
    Cuenta01.IngresarDinero(1000000);
    Cuenta01.EstablecerTipoDeInter�s(2);
    
    // Operaciones
    System.out.println(Cuenta01.SaldoActual());
    Cuenta01.IngresarDinero(500000);
    Cuenta01.RetirarDinero(200000);
    System.out.println(Cuenta01.SaldoActual());
    Cuenta01.AbonarIntereses();
    System.out.println(Cuenta01.SaldoActual());
  }
}
