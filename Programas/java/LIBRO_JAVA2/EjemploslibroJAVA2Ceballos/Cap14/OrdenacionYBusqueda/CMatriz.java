/////////////////////////////////////////////////////////////////
// Métodos de ordenación:
//   ordenar:   método de la burbuja.
//   insercion: método de inserción.
//   qs:        método quicksort recursivo.
//   qsNR:      método quicksort no recursico.
// Todos los métodos se sobrecargan dos veces: una para ordenar
// una matriz de tipo double y otra para ordenar una matriz de
// tipo String.
//
// Métodos de ordenación:
//   búsquedaBin:  buscar un elemento en una matriz ordenada
//   buscarCadena: buscar una cadena en un texto
//
public class CMatriz
{
  public static void ordenar(double[] m)
  {
    double aux;
    int i, número_de_elementos = m.length;
    boolean s = true;
  
    while (s && (--número_de_elementos > 0))
    {
      s = false; // no permutación
      for (i = 1; i <= número_de_elementos; i++)
        // ¿ el elemento (i-1) es mayor que el (i) ?
        if (m[i-1] > m[i])
        {
          // permutar los elementos (i-1) e (i)
          aux = m[i-1];
          m[i-1] = m[i];
          m[i] = aux;
          s = true; // permutación
        }
    }
  }
  
  public static void ordenar(String[] m)
  {
    String aux;
    int i, número_de_elementos = m.length;
    boolean s = true;
  
    while (s && (--número_de_elementos > 0))
    {
      s = false; // no permutación
      for (i = 1; i <= número_de_elementos; i++)
        // ¿ el elemento (i-1) es mayor que el (i) ?
        if (m[i-1].compareTo(m[i]) > 0)
        {
          // permutar los elementos (i-1) e (i)
          aux = m[i-1];
          m[i-1] = m[i];
          m[i] = aux;
          s = true; // permutación
        }
    }
  }

  public static void insercion(double[] m)
  {
    int i, k, n_elementos = m.length;
    double x;
    // Desde el segundo elemento
    for (i = 1; i < n_elementos; i++)
    {
      x = m[i];
      k = i-1;
      // Para k=-1, se ha alcanzado el extremo izquierdo.
      while (k >=0 && x < m[k])
      {
        m[k+1] = m[k]; // hacer hueco para insertar
        k--;
      }
      m[k+1] = x; // insertar x en su lugar
    }
  }

  public static void insercion(String[] m)
  {
    int i, k, n_elementos = m.length;
    String x;
    // Desde el segundo elemento
    for (i = 1; i < n_elementos; i++)
    {
      x = m[i];
      k = i-1;
      // Para k=-1, se ha alcanzado el extremo izquierdo.
      while (k >=0 && x.compareTo(m[k]) < 0)
      {
        m[k+1] = m[k]; // hacer hueco para insertar
        k--;
      }
      m[k+1] = x; // insertar x en su lugar
    }
  }

  public static void quicksort(double[] m)
  {
    qs(m, 0, m.length - 1);
  }
  
  // Método recursivo qs
  private static void qs(double[] m, int inf, int sup)
  {
    int izq, der;
    double mitad, x;
    izq = inf; der = sup;
    mitad = m[(izq + der) / 2];
    do
    {
      while (m[izq] < mitad && izq < sup) izq++;
      while (mitad < m[der] && der > inf) der--;
      if (izq <= der)
      {
        x = m[izq]; m[izq] = m[der]; m[der] = x;
        izq++; der--;
      }
    }
    while (izq <= der);
    if (inf < der) qs(m, inf, der);
    if (izq < sup) qs(m, izq, sup);
  }

  public static void quicksort(String[] m)
  {
    qs(m, 0, m.length - 1);
  }
  
  // Método recursivo qs
  private static void qs(String[] m, int inf, int sup)
  {
    int izq, der;
    String mitad, x;
    izq = inf; der = sup;
    mitad = m[(izq + der) / 2];
    do
    {
      while (m[izq].compareTo(mitad) < 0 && izq < sup) izq++;
      while (mitad.compareTo(m[der]) < 0 && der > inf) der--;
      if (izq <= der)
      {
        x = m[izq]; m[izq] = m[der]; m[der] = x;
        izq++; der--;
      }
    }
    while (izq <= der);
    if (inf < der) qs(m, inf, der);
    if (izq < sup) qs(m, izq, sup);
  }
  
  public static void quicksortNR(double[] m)
  {
    qsNR(m, 0, m.length - 1);
  }
  
  // Método no recursivo qs
  private static void qsNR(double[] m, int inf, int sup)
  {
    CPila pila = new CPila(); // pila de elementos (inf,sup)
    CDatos dato;              // encapsula los atributos inf y sup
  
    int izq, der, p;
    double mitad, x;
    // Iniciar la pila con los valores: inf, sup
    pila.meterEnPila(new CDatos(inf, sup)); 
    do
    {
      // Tomar los datos inf, sup de la parte superior de la pila
      dato = (CDatos)pila.sacarDePila();
      inf = dato.obtenerInf(); sup = dato.obtenerSup();
      do
      {
        // División de la matriz en dos partes
        izq = inf; der = sup;
        mitad = m[(izq + der) / 2];
        do
        {
          while (m[izq] < mitad && izq < sup) izq++;
          while (mitad < m[der] && der > inf) der--;
          if (izq <= der)
          {
            x = m[izq]; m[izq] = m[der]; m[der] = x;
            izq++; der--;
          }
        }
        while (izq <= der);
        if (izq < sup)
        {
          // Meter en la pila los valores: izq, sup
          pila.meterEnPila(new CDatos(izq, sup)); 
        }
        /* inf = inf; */ sup = der;
      }
      while (inf < der);
    }
    while (pila.tamaño() != 0);
  }
  
  public static void quicksortNR(String[] m)
  {
    qsNR(m, 0, m.length - 1);
  }
  
  // Método no recursivo qs
  private static void qsNR(String[] m, int inf, int sup)
  {
    CPila pila = new CPila(); // pila de elementos (inf,sup)
    CDatos dato;              // encapsula los atributos inf y sup
  
    int izq, der, p;
    String mitad, x;
    // Iniciar la pila con los valores: inf, sup
    pila.meterEnPila(new CDatos(inf, sup)); 
    do
    {
      // Tomar los datos inf, sup de la parte superior de la pila
      dato = (CDatos)pila.sacarDePila();
      inf = dato.obtenerInf(); sup = dato.obtenerSup();
      do
      {
        // División de la matriz en dos partes
        izq = inf; der = sup;
        mitad = m[(izq + der) / 2];
        do
        {
          while (m[izq].compareTo(mitad) < 0 && izq < sup) izq++;
          while (mitad.compareTo(m[der]) < 0 && der > inf) der--;
          if (izq <= der)
          {
            x = m[izq]; m[izq] = m[der]; m[der] = x;
            izq++; der--;
          }
        }
        while (izq <= der);
        if (izq < sup)
        {
          // Meter en la pila los valores: izq, sup
          pila.meterEnPila(new CDatos(izq, sup)); 
        }
        /* inf = inf; */ sup = der;
      }
      while (inf < der);
    }
    while (pila.tamaño() != 0);
  }

  public static int búsquedaBin(double[] m, double v)
  {
    // El método búsquedaBin devuelve como resultado la posición
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

  public static int buscarCadena(String stexto, String scadena)
  {
    // Buscar una "cadena" en un "texto"
    char[] texto = stexto.toCharArray();
    char[] cadena = scadena.toCharArray();

    // Construir la tabla "delta"
    int[] delta = new int[256];
    int i, longCad = cadena.length;
    // Iniciar la tabla "delta"
    for (i = 0; i < 256; i++)
      delta[i] = longCad;
    // Asignar valores a la tabla
    for (i = 0; i < longCad; ++i)
      delta[cadena[i]] = longCad - i - 1;

    // Algoritmo Boyer-Moore
    int j, longTex = texto.length;
    i = longCad - 1; // i es el índice dentro del texto
    while (i < longTex)
    {
      j = longCad - 1; // índice dentro de la cadena a buscar
      // Mientras haya coincidencia de caracteres
      while (cadena[j] == texto[i])
      {
        if ( j > 0 )
        {
          // Siguiente posición a la izquierda
          j--; i--;
        }
        else
        {
          // Se llegó al principio de la cadena, luego se encontró.
          return i;
        }
      }
      // Los caracteres no coinciden. Mover i lo que indique el
      // valor "delta" del carácter del texto que no coincide
      i += delta[texto[i]];
    }
    return -1;
  }
}
/////////////////////////////////////////////////////////////////
