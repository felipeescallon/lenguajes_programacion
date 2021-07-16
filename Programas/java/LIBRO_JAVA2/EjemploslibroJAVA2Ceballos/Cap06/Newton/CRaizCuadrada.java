// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CRaizCuadrada
{
  // Ra�z cuadrada. M�todo de Newton.
  public static void main(String[] args)
  {
    double n;        // n�mero
    double aprox;    // aproximaci�n a la ra�z cuadrada
    double antaprox; // anterior aproximaci�n a la ra�z cuadrada
    double epsilon;  // coeficiente de error
        
    do
    {
      System.out.print("N�mero: ");
      n = Leer.datoDouble();
    }
    while ( n < 0 );
    do
    {
      System.out.print("Ra�z cuadrada aproximada: ");
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
    System.out.println("La ra�z cuadrada de " + n + " es " + aprox);
  }
}
