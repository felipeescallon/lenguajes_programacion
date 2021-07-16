public class Test
{
  public static void main(String[] args)
  {
    char car = 0, cero = (char)'0', nueve = (char)'9',menos = (char)'-';
    String s = null;
    double d = 0.0;

    System.out.print("dato: ");
    if ((car = Leer.mirar()) >= cero && car <= nueve || car == menos)
      d = Leer.datoDouble();
    else
      s = Leer.dato();

    if (s != null)
      System.out.println(s);
    else
      System.out.println(d);
  }
}
