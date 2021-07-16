public class Test
{
  static double[][] CopiarMatriz2D(double[][] x)
  {
    double[][] z = new double[x.length][x[0].length];

    for (int f = 0; f < x.length; f++)
      for (int c = 0; c < x[0].length; c++)
        z[f][c] = x[f][c];
    return z;
  }

  public static void main(String[] args)
  {
    double[][] m1 = {{10, 20, 30}, {40, 50, 60}};
    // Copiar una matriz utilizando un método
    double[][] m2 = CopiarMatriz2D(m1);
    m1[0][0] = 77; // modificar un elemento de la matriz original
    // Visualizar la matriz m2
    for (int f = 0; f < m2.length; f++)
      for (int c = 0; c < m2[0].length; c++)
        System.out.print(m2[f][c] + " ");
    System.out.println();
    
    // Copiar una matriz utilizando el método clone
    double[][] m3 = (double[][])m1.clone();
    for (int f = 0; f < m1.length; f++)
      m3[f] = (double[])m1[f].clone();
    m1[0][0] = 10;
    // Visualizar la matriz m3
    for (int f = 0; f < m3.length; f++)
      for (int c = 0; c < m3[0].length; c++)
        System.out.print(m3[f][c] + " ");
    System.out.println();
  }
}
