public class Test
{
  public static void main(String[] args)
  {
    int x = 0;
    CMiClase obj = new CMiClase();
    try
    {
      obj.m(x);
    }
    catch (EValorNoValido e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println("Continúa la ejecución");
  }
}
