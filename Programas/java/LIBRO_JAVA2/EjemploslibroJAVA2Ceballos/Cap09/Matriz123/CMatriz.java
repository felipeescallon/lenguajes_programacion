//////////////////////////////////////////////////////////////////
// Matriz multidimensional basada en una unidimensional
//
public class CMatriz
{
  private double[] matriz;   // matriz unidimensional
  private int nDims;         // número de dimensiones
  private int[] dimsMatriz;  // valor de cada dimensión
  
  private void construir( int[] dim )
  {
    int i;

    for ( i = 0; i < dim.length; i++ )
      if ( dim[i] < 1 )
      {
        System.out.println("Dimensión nula o negativa");
        System.exit(-1);
      }
    // Establecer los atributos
    dimsMatriz = new int[dim.length];
    for ( i = 0; i < dim.length; i++ ) dimsMatriz[i] = dim[i];
    nDims = dim.length;
    matriz = new double[totalElementos()];
  }

  public CMatriz() // constructor
  {
    int dim[] = { 10 }; // dimensión por omisión
    construir( dim );
  }

  public CMatriz( int d1 ) // constructor
  {
    int dim[] = { d1 }; // una dimensión
    construir( dim );
  }

  public CMatriz( int d1, int d2 ) // constructor
  {
    int dim[] = { d1, d2 }; // dos dimensiones
    construir( dim );
  }

  public CMatriz( int d1, int d2, int d3 ) // constructor
  {
    int dim[] = { d1, d2, d3 }; // tres dimensiones
    construir( dim );
  }

  public int totalElementos()
  {
    int i;
    int nTElementos = 1;
    // Calcular el número total de elementos de la matriz
    for ( i = 0; i < nDims; i++ )
      nTElementos *= dimsMatriz[i];

    return nTElementos;
  }

  public int desplazamiento( int[] subind )
  {
    int i;
    int desplazamiento = 0;

    for ( i = 0; i < nDims; i++ )
    {
      // Verificar si los subíndices están dentro del rango
      if ( subind[i] < 0 || subind[i] > dimsMatriz[i] )
      {
        System.out.println("Subíndice fuera de rango");
        return -1;
      }
      // Desplazamiento equivalente en la matriz unidimensional
      desplazamiento += subind[i];
      if ( i+1 < nDims )
        desplazamiento *= dimsMatriz[i+1];
    }
    return desplazamiento;
  }

  public void asignarDato( int dato, int i1 )
  {
    asignarDato(dato, i1, 0, 0);
  }
  
  public void asignarDato( int dato, int i1, int i2 )
  {
    asignarDato(dato, i1, i2, 0);
  }
  
  public void asignarDato( int dato, int i1, int i2, int i3 )
  {
    // Asignar un valor al elemento especificado de la matriz
    int subind[] = { i1, i2, i3 };
    int i = desplazamiento( subind );
    if ( i == -1 ) System.exit(-1); // subíndice fuera de rango
    matriz[i] = dato;
  }

  public double obtenerDato( int i1 )
  {
    return obtenerDato( i1, 0, 0);
  }
  
  public double obtenerDato( int i1, int i2 )
  {
    return obtenerDato( i1, i2, 0);
  }
  
  public double obtenerDato( int i1, int i2, int i3 )
  {
    // Obtener el valor al elemento especificado de la matriz
    int subind[] = { i1, i2, i3 };
    int i = desplazamiento( subind );
    if ( i == -1 ) System.exit(-1); // subíndice fuera de rango
    return matriz[i];
  }
}
//////////////////////////////////////////////////////////////////
