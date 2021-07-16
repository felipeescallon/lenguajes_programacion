// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CEcuacion2Grado
{
  // Calcular las ra�ces de una ecuaci�n de 2� grado
  public static void main(String[] args)
  {
    double a, b, c; // coeficientes de la ecuaci�n
    double d;       // discriminante
    double re, im;  // parte real e imaginaria de la ra�z
    
    System.out.println("Coeficientes a, b y c de la ecuaci�n:");
    System.out.print("a = "); a = Leer.datoDouble();
    System.out.print("b = "); b = Leer.datoDouble();
    System.out.print("c = "); c = Leer.datoDouble();
    System.out.println();
    if (a == 0 && b == 0)
      System.out.println("La ecuaci�n es degenerada");
    else if (a == 0)
      System.out.println("La �nica ra�z es: " + -c/b);
    else
    {
      re = -b / (2 * a);
      d = b * b - 4 * a * c;
      im = Math.sqrt(Math.abs(d)) / (2 * a);
      if (d >= 0)
      {
        System.out.println("Ra�ces reales:");
        System.out.println((re+im) + ", " + (re-im));
      }
      else
      {
        System.out.println("Ra�ces complejas:");
        System.out.println(re + " + " + Math.abs(im) + " j");
        System.out.println(re + " - " + Math.abs(im) + " j");
      }
    }
  }
}
