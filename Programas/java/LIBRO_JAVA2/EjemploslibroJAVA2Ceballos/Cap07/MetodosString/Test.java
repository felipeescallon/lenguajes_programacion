public class Test
{
  public static void main(String[] args)
  {
    String str1 = "La provincia de Santander es muy bonita";
    String str2 = "La provincia de SANTANDER es muy bonita";
    String strtemp;
    int resultado;
    resultado = str1.compareToIgnoreCase(str2);
    if( resultado > 0 )
      strtemp = "mayor que ";
    else if( resultado < 0 )
      strtemp = "menor que ";
    else
      strtemp = "igual a ";
    System.out.println( str1 + " es " + strtemp + str2 );
  }
}
