// La clase Leer debe estar en alguna carpeta de las especificadas
// por la variable de entorno CLASSPATH.
public class CMenor
{
  // Menor de tres números a, b y c
   
  public static void main(String[] args)
  {
    float a, b, c, menor;
    
    // Leer los valores de a, b y c
    System.out.print("a : "); a = Leer.datoFloat();
    System.out.print("b : "); b = Leer.datoFloat();
    System.out.print("c : "); c = Leer.datoFloat();
    // Obtener el menor
    if (a < b)
      if (a < c)
        menor = a;
      else
        menor =c;
    else
      if (b < c)
        menor = b;
      else
        menor = c;
    System.out.println("Menor = " + menor);
  }
}
