class prueba
{
  public static int b�squedaBin1(double[] m, double v)
  {
    // El m�todo b�squedaBin devuelve como resultado la posici�n
    // del valor. Si el valor no se localiza devuelve -1.

    if (m.length == 0) return -1;
    int mitad, inf = 0, sup = m.length - 1;
  
    do
    {
      mitad = (inf + sup) / 2;
      if (v > m[mitad])
        inf = mitad + 1;
      else
        sup = mitad - 1;
    }
    while( m[mitad] != v && inf <= sup);

    if (m[mitad] == v)
      return mitad;
    else
      return -1;
  }

  public static int b�squedaBin2(double[] m, double v)
  {
    // El m�todo b�squedaBin devuelve como resultado la posici�n
    // del valor. Si el valor no se localiza devuelve -1.

    if (m.length == 0) return -1;
    int mitad, inf = 0, sup = m.length - 1;
  
    do
    {
      mitad = (inf + sup) / 2;
      if (v > m[mitad])
        inf = mitad + 1;
      else
        sup = mitad;

    }
    while ( inf < sup );

    if (m[inf] == v)
      return inf;
    else
      return -1;
  }

  public static void main(String[] args)
  {
    double[] a = new double[100000];
    long ti, n = a.length;
    int i;
    for (i = 0; i < n; i++)
      a[i] = i+1;
    
    // Versi�n 1
    ti = System.currentTimeMillis();
    i = b�squedaBin1(a,0);
    for (i = 0; i < n; i++)
      i = b�squedaBin1(a,i+1);
    i = b�squedaBin1(a,n+1);
    System.out.println((System.currentTimeMillis()-ti) + " milisegundos");

    // Versi�n 2
    ti = System.currentTimeMillis();
    i = b�squedaBin2(a,0);
    for (i = 0; i < n; i++)
      i = b�squedaBin2(a,i+1);
    i = b�squedaBin2(a,n+1);
    System.out.println((System.currentTimeMillis()-ti) + " milisegundos");
  }
}

// Es m�s r�pido el m�todo 2 debido a que en la condici�n del while
// el m�todo 1 tiene que realizar dos comparaciones frente a una
// que tiene que realizar el m�todo 2.