// La clase Leer debe estar en alguna carpeta de las especificadas
// por la variable de entorno CLASSPATH.
public class CEcuacion
{
  public static void main(String[] args)
  {
    double a, b, c, d, x1, x2;
    
    System.out.print("Coeficiente a: "); a = Leer.datoDouble();
    System.out.print("Coeficiente b: "); b = Leer.datoDouble();
    System.out.print("Coeficiente c: "); c = Leer.datoDouble();
    
    d = b * b - 4 * a * c;
    if (d < 0)
    {
      // Si d es menor que 0
      System.out.println("Las raíces son complejas.");
      return; // salir
    }
    // Si d es mayor o igual que 0
    System.out.println("Las raíces reales son:");
    d = Math.sqrt(d);
    x1 = (-b + d) / (2 * a);
    x2 = (-b - d) / (2 * a);
    System.out.println("x1 = " + x1 + ", x2 = " + x2);
  }
}
