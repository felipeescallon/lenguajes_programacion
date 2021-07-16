// La clase Leer debe estar en alguna carpeta de las especificadas
// por la variable de entorno CLASSPATH.
public class CDescuento
{
  public static void main(String[] args)
  {
    int ar, cc;
    float pu, desc;
    
    System.out.print("Código artículo....... ");
    ar = Leer.datoInt();
    System.out.print("Cantidad comprada..... ");
    cc = Leer.datoInt();
    System.out.print("Precio unitario....... ");
    pu = Leer.datoFloat();
    System.out.println();

    if (cc > 100)
      desc = 40F;      // descuento 40%
    else if (cc >= 25)
      desc = 20F;      // descuento 20%
    else if (cc >= 10)
      desc = 10F;      // descuento 10%
    else
      desc = 0.0F;     // descuento 0%
    System.out.println("Descuento............. " + desc + "%");
    System.out.println("Total................. " +
                       cc * pu * (1 - desc / 100));
  }
}
