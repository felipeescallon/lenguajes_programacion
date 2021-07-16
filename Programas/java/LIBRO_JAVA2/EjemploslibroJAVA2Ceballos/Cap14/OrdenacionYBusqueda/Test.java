//////////////////////////////////////////////////////////////////
// Ordenar una matriz utilizando el m�todo de la burbuja
//
public class Test
{
  public static void main(String[] args)
  {
    // Burbuja:
    // Matriz num�rica
    double[] m = {3,2,1,5,4};
    CMatriz.ordenar(m);
    for (int i = 0; i < m.length; i++)
      System.out.print(m[i] + " ");
    System.out.println();
    
    // Matriz de cadenas de caracteres
    String[] s = {"ccc","bbb","aaa","eee","ddd"};
    CMatriz.ordenar(s);
    for (int i = 0; i < s.length; i++)
      System.out.print(s[i] + " ");
    System.out.println();

    // Inserci�n:
    // Matriz num�rica
    double[] m1 = {3,2,1,5,4};
    CMatriz.insercion(m1);
    for (int i = 0; i < m1.length; i++)
      System.out.print(m1[i] + " ");
    System.out.println();
    
    // Matriz de cadenas de caracteres
    String[] s1 = {"ccc","bbb","aaa","eee","ddd"};
    CMatriz.insercion(s1);
    for (int i = 0; i < s1.length; i++)
      System.out.print(s1[i] + " ");
    System.out.println();

    // Quicksort recursivo:
    // Matriz num�rica
    double[] m2 = {3,2,1,5,4};
    CMatriz.quicksort(m2);
    for (int i = 0; i < m2.length; i++)
      System.out.print(m2[i] + " ");
    System.out.println();
    
    // Matriz de cadenas de caracteres
    String[] s2 = {"ccc","bbb","aaa","eee","ddd"};
    CMatriz.quicksort(s2);
    for (int i = 0; i < s2.length; i++)
      System.out.print(s2[i] + " ");
    System.out.println();

    // Quicksort no recursivo:
    // Matriz num�rica
    double[] m3 = {3,2,1,5,4};
    CMatriz.quicksortNR(m3);
    for (int i = 0; i < m3.length; i++)
      System.out.print(m3[i] + " ");
    System.out.println();
    
    // Matriz de cadenas de caracteres
    String[] s3 = {"ccc","bbb","aaa","eee","ddd"};
    CMatriz.quicksortNR(s3);
    for (int i = 0; i < s3.length; i++)
      System.out.print(s3[i] + " ");
    System.out.println();

    // Buscar un elemento en una matriz ordenada
    double[] m4 = {3,5,7,9,11};
    int i = CMatriz.b�squedaBin(m4,7);
    System.out.println(m4[i]);

    // Buscar una cadena en un texto
    String s4 = "M�s vale un ya que cien despu�s se har�";
    i = CMatriz.buscarCadena(s4, "cien");
    System.out.println(i);
  }
}
//////////////////////////////////////////////////////////////////
