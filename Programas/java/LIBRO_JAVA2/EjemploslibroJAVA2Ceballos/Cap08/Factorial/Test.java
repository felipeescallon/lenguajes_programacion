public class Test
{
  // C�lculo del factorial de un n�mero
  public static long factorial(int n)
  {
    if (n == 0)
      return 1;
    else
      return n * factorial(n-1);
  }

  public static void main(String[] args)
  {
    int numero;
    long fac;
    do
    {
      System.out.print("�N�mero? ");
      numero = Leer.datoInt();
    }
    while (numero < 0 || numero > 25);
    fac = factorial(numero);
    System.out.println("\nEl factorial de " + numero + " es: " + fac);
  }
}
