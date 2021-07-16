// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CValoresMaxMin
{
  // Obtener el máximo y el mínimo de un conjunto de valores
  public static void main(String[] args)
  {
    int nElementos;    // número de elementos (valor no negativo)
    do
    {
      System.out.print("Número de valores que desea introducir: ");
      nElementos = Leer.datoInt();
    }
    while (nElementos < 1);
    float[] dato = new float[nElementos]; // crear la matriz dato
    int i = 0;       // subíndice
    float max, min;  // valor máximo y valor mínimo
    
    System.out.println("Introducir los valores.\n" +
                       "Para finalizar pulse [Entrar]");
    for (i = 0; i < dato.length; i++)
    {
      System.out.print("dato[" + i + "]= ");
      dato[i] = Leer.datoFloat();
      if (Float.isNaN(dato[i])) break; // salir del bucle
    }
    nElementos = i; // número de valores leídos
    // Obtener los valores máximo y mínimo
    if (nElementos > 0)
    {
      max = min = dato[0];
      for (i = 0; i < nElementos; i++)
      {
        if (dato[i] > max)
          max = dato[i];
        if (dato[i] < min)
          min = dato[i];
      }
      // Escribir los resultados 
      System.out.println("\nValor máximo: " + max);
      System.out.println("Valor mínimo: " + min);
    }
    else
      System.out.println("\nNo hay datos.");
  }
}
