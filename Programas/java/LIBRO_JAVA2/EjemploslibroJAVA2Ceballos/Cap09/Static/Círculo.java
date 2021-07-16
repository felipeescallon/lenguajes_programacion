class Punto
{
  private double x, y;
  Punto(double cx, double cy)
  {
    x = cx; y = cy;
  }
}

public class C�rculo
{
  // Atributos
  private static double pi = 3.141592;
  public static int numC�rculos;
  public static double seno[] = new double[360];
  public static double coseno[] = new double[360];
  // Iniciador est�tico
  static
  {
    // Tablas del seno y coseno de grado en grado
    for (int i = 0; i < 360; i++)
    {
      double s, c;
      // Calcular el seno y el coseno de i
      s = Math.sin(Math.toRadians(i));
      c = Math.cos(Math.toRadians(i));
      // Almacenar los valores redondeados a 6 decimales
      seno[i] = Math.rint(s*1000000)/1000000;
      coseno[i] = Math.rint(c*1000000)/1000000;
    }
  }

  private Punto centro; // coordenadas del centro
  private double radio; // radio del c�rculo
  
  // M�todos
  protected void msgEsNegativo()
  {
    System.out.println("El radio es negativo. Se convierte a positivo");
  }
  
  public C�rculo() // constructor sin par�metros
  {
    this(100.0, 100.0, 100.0);
  }

  public C�rculo(double cx, double cy, double r) // constructor
  {
    centro = new Punto(cx, cy);
    if (r < 0)
    {
      msgEsNegativo();
      r = -r;
    }
    radio = r;
    numC�rculos++;
  }

  public double longCircunferencia()
  {
    return 2 * pi * radio;
  }

  public double �reaC�rculo()
  {
    return pi * radio * radio;
  }

  public static void cambiarPrecisi�nPiA(double nuevoValor)
  {
    if (nuevoValor < 3.14 || nuevoValor > 3.1416) return;
    pi = nuevoValor;
  }
}
