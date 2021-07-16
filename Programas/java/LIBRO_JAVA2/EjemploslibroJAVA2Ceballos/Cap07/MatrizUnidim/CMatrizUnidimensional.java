// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CMatrizUnidimensional
{
  // Creación de una matriz unidimensional
  public static void main(String[] args)
  {
    int nElementos;
    System.out.print("Número de elementos de la matriz: ");
    nElementos = Leer.datoInt();
    int[] m = new int[nElementos]; // crear la matriz m
    int i = 0; // subíndice
    
    System.out.println("Introducir los valores de la matriz.");
    for (i = 0; i < nElementos; i++)
    {
      System.out.print("m[" + i + "] = ");
      m[i] = Leer.datoInt();
    }
    
    // Visualizar los elementos de la matriz
    System.out.println();
    for (i = 0; i < nElementos; i++)
      System.out.print(m[i] + " ");
    System.out.println("\n\nFin del proceso.");
  }
}
