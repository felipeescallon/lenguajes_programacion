class Punto
{
  private double x, y;
  Punto(double cx, double cy)
  {
    x = cx; y = cy;
  }
}

class C�rculo
{
  // miembros privados
  private Punto centro; // coordenadas del centro
  private double radio; // radio del c�rculo
  
  // miembros protegidos
  protected void msgEsNegativo()
  {
    System.out.println("El radio es negativo. Se convierte a positivo");
  }
  
  // miembros p�blicos
  public C�rculo() {} // constructor sin par�metros
  public C�rculo(double cx, double cy, double r) // constructor
  {
    centro = new Punto(cx, cy);
    if (r < 0)
    {
      msgEsNegativo();
      r = -r;
    }
    radio = r;
  }
  public double longCircunferencia()
  {
    return 2 * Math.PI * radio;
  }
  public double �reaC�rculo()
  {
    return Math.PI * radio * radio;
  }
}

class Test
{
  public static void main(String[] args)
  {
    C�rculo obj1 = new C�rculo();
    System.out.println(obj1.longCircunferencia());
    System.out.println(obj1.�reaC�rculo());

    C�rculo obj2 = new C�rculo(100, 100, 10);
    System.out.println(obj2.longCircunferencia());
    System.out.println(obj2.�reaC�rculo());
  }
}

