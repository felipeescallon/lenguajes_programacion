public class CPitagoras
{
  // Teorema de Pitágoras
  public static void main(String[] args)
  {
    int x = 1, y = 1, z = 0;
    System.out.println("Z\t" + "X\t" + "Y");
    System.out.println("____________________");
    while (x <= 50)
    {
      // Calcular z. Como z es un entero, almacena
      // la parte entera de la raíz cuadrada
      z = (int)Math.sqrt(x * x + y * y);
      while (y <= 50 && z <= 50)
      {
        // Si la raíz cuadrada anterior fue exacta,
        // escribir z, x e y
        if (z * z == x * x + y * y)
          System.out.println(z + "\t" + x + "\t" + y);
        y = y + 1;
        z = (int)Math.sqrt(x * x + y * y);
      }
      x = x + 1; y = x;
    }
  }
}
