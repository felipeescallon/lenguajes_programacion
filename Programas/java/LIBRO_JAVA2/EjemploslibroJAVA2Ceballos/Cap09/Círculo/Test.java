class Punto
{
  private double x, y;
  Punto(double cx, double cy)
  {
    x = cx; y = cy;
  }
}

class Círculo
{
  // miembros privados
  private Punto centro; // coordenadas del centro
  private double radio; // radio del círculo
  
  // miembros protegidos
  protected void msgEsNegativo()
  {
    System.out.println("El radio es negativo. Se convierte a positivo");
  }
  
  // miembros públicos
  public Círculo() {} // constructor sin parámetros
  public Círculo(double cx, double cy, double r) // constructor
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
  public double áreaCírculo()
  {
    return Math.PI * radio * radio;
  }
}

class Test
{
  public static void main(String[] args)
  {
    Círculo obj1 = new Círculo();
    System.out.println(obj1.longCircunferencia());
    System.out.println(obj1.áreaCírculo());

    Círculo obj2 = new Círculo(100, 100, 10);
    System.out.println(obj2.longCircunferencia());
    System.out.println(obj2.áreaCírculo());
  }
}

