public class Test
{
  public static void main(String[] args)
  {
    System.out.println(C�rculo.seno[90]);
    System.out.println(C�rculo.coseno[90]);

    C�rculo obj1 = new C�rculo();
    System.out.println(obj1.longCircunferencia());
    System.out.println(obj1.�reaC�rculo());

    C�rculo.cambiarPrecisi�nPiA(3.14);

    C�rculo obj2 = new C�rculo(100, 100, 10);
    System.out.println(obj2.longCircunferencia());
    System.out.println(obj2.�reaC�rculo());

    System.out.println(C�rculo.numC�rculos);
  }
}

