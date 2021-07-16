public class Test
{
  public static void main(String[] args)
  {
    System.out.println(Círculo.seno[90]);
    System.out.println(Círculo.coseno[90]);

    Círculo obj1 = new Círculo();
    System.out.println(obj1.longCircunferencia());
    System.out.println(obj1.áreaCírculo());

    Círculo.cambiarPrecisiónPiA(3.14);

    Círculo obj2 = new Círculo(100, 100, 10);
    System.out.println(obj2.longCircunferencia());
    System.out.println(obj2.áreaCírculo());

    System.out.println(Círculo.numCírculos);
  }
}

