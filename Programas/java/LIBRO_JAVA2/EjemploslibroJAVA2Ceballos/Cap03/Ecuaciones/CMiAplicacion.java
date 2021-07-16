public class CMiAplicacion
{
  public static void main(String[] args)
  {
    CEcuacion ec1 = new CEcuacion();
    ec1.Ecuación(1, -3.2, 0, 7);
    
    double r = ec1.ValorPara(1);
    System.out.println(r);
    
    r = ec1.ValorPara(1.5);
    System.out.println(r);
  }
}

class CEcuacion
{
  // El término de mayor grado tiene exponente 3 fijo
  double c3, c2, c1, c0; // coeficientes
  public void Ecuación(double a, double b, double c, double d)
  {
    c3 = a; c2 = b; c1 = c; c0 = d;
  }
  
  public double ValorPara(double x)
  {
    double resultado;
    resultado = c3*x*x*x + c2*x*x + c1*x + c0;
    return resultado; // devolver el valor calculado
  }
}
