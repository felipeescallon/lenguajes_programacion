import java.io.*;
public class Test
{
  static double exponencial(double x)
  {
    int n = 1;
    double exp, término = 1;
    exp = término;    // primer término
    while (término > 1e-7)
    {
      término *= x/n; // siguiente término
      exp += término; // sumar otro término
      n++;
    }
    return exp;
  }
  public static void main(String[] args)
  {
    double exp, x;
    System.out.print("Valor de x: "); x = Leer.datoFloat();
    exp = exponencial(x);
    System.out.println("exp(" + x + ") = " + exp);
  }
}
