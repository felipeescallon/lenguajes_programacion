// La clase Leer debe estar en alguna carpeta de las especificadas
// por la variable de entorno CLASSPATH.
public class CIntereses
{
  public static void main(String[] args)
  {
    double c, intereses, capital;
    float r;
    int t;
    
    System.out.print("Capital invertido: ");
    c = Leer.datoDouble();
    System.out.print("\nA un % anual del: ");
    r = Leer.datoFloat();
    System.out.print("\nDurante cuántos días: ");
    t = Leer.datoInt();
    
    intereses = c * r * t / (360 * 100);
    capital = c + intereses;

    System.out.println("Intereses producidos... " + intereses);
    System.out.println("Capital acumulado...... " + capital);
  }
}
