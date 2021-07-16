import java.util.Arrays;
/////////////////////////////////////////////////////////////////
// Definición de la clase CVector
//
public class CVector
{
  private double[] vector; // matriz vector
  private int nElementos;  // número de elementos de la matriz
  
  public CVector() // número de elementos por omisión: 10
  {
    nElementos = 10;
    vector = new double[nElementos];
  }
  
  public CVector(int ne) // ne elementos
  {
    if ( ne < 1 )
    {
      System.out.println("Nº de elementos no válido: " + ne);
      System.out.println("Se asignan 10 elementos");
      ne = 10;
    }
    nElementos = ne;
    vector = new double[nElementos];
  }
  
  public CVector(double[] m) // crea un CVector desde una matriz
  {
    nElementos = m.length;
    vector = new double[nElementos];
    // Copiar los elementos de la matriz m
    for ( int i = 0; i < nElementos; i++ )
      vector[i] = m[i];
  }
  
  public CVector(CVector v) // constructor copia
  {
    nElementos = v.nElementos;
    vector = new double[nElementos];
    // Copiar el objeto v
    for ( int i = 0; i < nElementos; i++ )
      vector[i] = v.vector[i];
  }
  
  public CVector copiar(CVector v) // copia un CVector en otro
  {
    nElementos = v.nElementos;
    vector = new double[nElementos];
    // Copiar el objeto v
    for ( int i = 0; i < nElementos; i++ )
      vector[i] = v.vector[i];
    
    return this;
  }
  
  public void ponerValorEn( int i, double valor )
  {
    if (i >= 0 && i < nElementos)
      vector[i] = valor;
    else
      System.out.println("Índice fuera de límites");
  }
  
  public double valorEn( int i )
  {
    if (i >= 0 && i < nElementos)
      return vector[i];
    else
    {
      System.out.println("Índice fuera de límites");
      return Double.NaN;
    }
  }
  
  public int longitud() { return nElementos; }
  
  public boolean equals(CVector v)
  {
    return Arrays.equals(vector, v.vector);      
  }
}
