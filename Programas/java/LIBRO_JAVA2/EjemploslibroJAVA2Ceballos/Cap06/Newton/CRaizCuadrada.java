// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CRaizCuadrada
{
  // Raíz cuadrada. Método de Newton.
  public static void main(String[] args)
  {
    double n;        // número
    double aprox;    // aproximación a la raíz cuadrada
    double antaprox; // anterior aproximación a la raíz cuadrada
    double epsilon;  // coeficiente de error
        
    do
    {
      System.out.print("Número: ");
      n = Leer.datoDouble();
    }
    while ( n < 0 );
    do
    {
      System.out.print("Raíz cuadrada aproximada: ");
      aprox = Leer.datoDouble();
    }
    while ( aprox <= 0 );
    do
    {
    System.out.print("Coeficiente de error: ");
    epsilon = Leer.datoDouble();
    }
    while ( epsilon <= 0 );
    do
    {
      antaprox = aprox;
      aprox = (n/antaprox + antaprox) / 2;
    }
    while (Math.abs(aprox - antaprox) >= epsilon);
    System.out.println("La raíz cuadrada de " + n + " es " + aprox);
  }
}
