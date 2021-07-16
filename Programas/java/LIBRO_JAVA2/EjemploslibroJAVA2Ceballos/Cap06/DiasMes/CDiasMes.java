// La clase Leer debe estar en alguna carpeta de las especificadas
// por la variable de entorno CLASSPATH.
public class CDiasMes
{
  // Días correspondientes a un mes de un año dado
  
  public static void main(String[] args)
  {
    int días = 0, mes = 0, año = 0;
    
    System.out.print("Mes (##): "); mes = Leer.datoInt();
    System.out.print("Año (####): "); año = Leer.datoInt();
    
    switch (mes)
    {
      case 1:      // enero
      case 3:      // marzo
      case 5:      // mayo
      case 7:      // julio
      case 8:      // agosto
      case 10:     // octubre
      case 12:     // diciembre
        días = 31;
        break;
      case 4:      // abril
      case 6:      // junio
      case 9:      // septiembre
      case 11:     // noviembre
        días = 30;
        break;
      case 2:      // febrero
        // ¿Es el año bisiesto?
        if ((año % 4 == 0) && (año % 100 != 0) || (año % 400 == 0))
          días = 29;
        else
          días = 28;
          break;
      default:
        System.out.println("\nEl mes no es válido");
        break;
    }
    if (mes >= 1 && mes <= 12)
      System.out.println("\nEl mes " + mes + " del año " + año +
                         " tiene " + días + " días");
  }
}
