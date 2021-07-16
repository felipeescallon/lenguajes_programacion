import java.util.*;
//////////////////////////////////////////////////////////////////
// Calcular los centros numéricos entre 1 y n.
//
public class Test
{
  // Método de búsqueda binaria
  //
  // cn: centro numérico
  // (1 a cn-1) cn (cn+1 a mitad)
  // suma_grupo1 = suma de los valores desde 1 a cn-1
  // suma_grupo2 = suma de los valores desde cn+1 a mitad
  //
  // El método devuelve como resultado el valor mitad.
  // Si cn no es un centro numérico devuelve un valor 0.
  //
  public static long búsquedaBin(long cn, long n)
  {
    if (cn <= 0 || n <= 0) return 0;
    
    long suma_grupo1 = ((cn-1) * ((cn-1) + 1)) / 2;
    long suma_grupo2 = 0;
    long mitad = 0;
    
    long inf = cn+1; // límite inferior del grupo 2
    long sup = n;    // límite superior del grupo 2
    
    // Búsqueda binaria  
    do
    {
      mitad = (inf + sup) / 2;
      suma_grupo2 = (mitad * (mitad + 1)) / 2 - suma_grupo1 - cn;
      if (suma_grupo1 > suma_grupo2)
        inf = mitad + 1;
      else
        sup = mitad - 1;
    }
    while ( suma_grupo1 != suma_grupo2 && inf <= sup);

    if (suma_grupo2 == suma_grupo1)
      return mitad;
    else
      return 0;
  }

  public static void main(String[] args)
  {
    long n;              // centros numéricos entre 1 y n
    long cn;             // posible centro numérico
    long lim_sup_grupo2; // límite superior del grupo 2
  
    System.out.print("Centros numéricos entre 1 y ");
    n = Leer.datoLong();
    System.out.println();
    for (cn = 3; cn < n; cn++)
    {
      lim_sup_grupo2 = búsquedaBin(cn, n); 
      if (lim_sup_grupo2 != 0)
        System.out.println(cn + " es centro numérico de 1 a " +
                           (cn-1) + " y " + (cn+1) + " a " +
                           lim_sup_grupo2);
    }
  }
}
