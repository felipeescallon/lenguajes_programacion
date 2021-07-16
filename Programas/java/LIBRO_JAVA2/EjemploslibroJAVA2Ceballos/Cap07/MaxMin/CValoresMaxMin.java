// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CValoresMaxMin
{
  // Obtener el m�ximo y el m�nimo de un conjunto de valores
  public static void main(String[] args)
  {
    int nElementos;    // n�mero de elementos (valor no negativo)
    do
    {
      System.out.print("N�mero de valores que desea introducir: ");
      nElementos = Leer.datoInt();
    }
    while (nElementos < 1);
    float[] dato = new float[nElementos]; // crear la matriz dato
    int i = 0;       // sub�ndice
    float max, min;  // valor m�ximo y valor m�nimo
    
    System.out.println("Introducir los valores.\n" +
                       "Para finalizar pulse [Entrar]");
    for (i = 0; i < dato.length; i++)
    {
      System.out.print("dato[" + i + "]= ");
      dato[i] = Leer.datoFloat();
      if (Float.isNaN(dato[i])) break; // salir del bucle
    }
    nElementos = i; // n�mero de valores le�dos
    // Obtener los valores m�ximo y m�nimo
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
      System.out.println("\nValor m�ximo: " + max);
      System.out.println("Valor m�nimo: " + min);
    }
    else
      System.out.println("\nNo hay datos.");
  }
}
