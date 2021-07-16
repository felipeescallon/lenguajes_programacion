//////////////////////////////////////////////////////////////////
// Recursividad
//
public class Test
{
  public static void main(String[] args)
  {
    int m, n, a;
    System.out.println("Cálculo de A(m,n)=A(m-1,A(m,n-1))\n");
    System.out.print("Valor de m: "); m = Leer.datoInt();
    System.out.print("Valor de n: "); n = Leer.datoInt();
    a = CRecursion.Ackerman(m,n);
    System.out.println("\nA(" + m + "," + n + ") = " + a);
    a = CRecursion.AckermanNR(m,n);
    System.out.println("\nA(" + m + "," + n + ") = " + a);
  }
}
//////////////////////////////////////////////////////////////////
