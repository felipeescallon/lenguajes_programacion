import java.io.*;
public class Test
{
  static double exponencial(double x)
  {
    int n = 1;
    double exp, t�rmino = 1;
    exp = t�rmino;    // primer t�rmino
    while (t�rmino > 1e-7)
    {
      t�rmino *= x/n; // siguiente t�rmino
      exp += t�rmino; // sumar otro t�rmino
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
