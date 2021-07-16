// La clase Leer debe estar en alguna carpeta de las especificadas
// por la variable de entorno CLASSPATH.
public class CDiasMes
{
  // D�as correspondientes a un mes de un a�o dado
  
  public static void main(String[] args)
  {
    int d�as = 0, mes = 0, a�o = 0;
    
    System.out.print("Mes (##): "); mes = Leer.datoInt();
    System.out.print("A�o (####): "); a�o = Leer.datoInt();
    
    switch (mes)
    {
      case 1:      // enero
      case 3:      // marzo
      case 5:      // mayo
      case 7:      // julio
      case 8:      // agosto
      case 10:     // octubre
      case 12:     // diciembre
        d�as = 31;
        break;
      case 4:      // abril
      case 6:      // junio
      case 9:      // septiembre
      case 11:     // noviembre
        d�as = 30;
        break;
      case 2:      // febrero
        // �Es el a�o bisiesto?
        if ((a�o % 4 == 0) && (a�o % 100 != 0) || (a�o % 400 == 0))
          d�as = 29;
        else
          d�as = 28;
          break;
      default:
        System.out.println("\nEl mes no es v�lido");
        break;
    }
    if (mes >= 1 && mes <= 12)
      System.out.println("\nEl mes " + mes + " del a�o " + a�o +
                         " tiene " + d�as + " d�as");
  }
}
