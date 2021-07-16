import java.util.*;

public class CRandomJava
{
  // Obtener n�meros dentro de un rango
  public static void main(String[] args)
  {
    int l�miteSup = 49, l�miteInf = 1;
    int n[] = new int[6], i, k;

    for (i = 0; i < n.length; i++)
    {
      // Obtener un n�mero aleatorio
      n[i] = (int)((l�miteSup - l�miteInf + 1) * Math.random() +
                   l�miteInf);
      // Verificar si ya existe el �ltimo n�mero obtenido
      for (k = 0; k < i; k++)
        if (n[k] == n[i]) // ya existe
        {
          i--;   // i ser� incrementada por el for externo
          break; // salir de este for
        }
    }
    // Clasificar la matriz
    Arrays.sort(n);
    // Mostrar la matriz
    for (i = 0; i < n.length; i++)
      System.out.print(n[i] + " ");
    System.out.println();
  }
}
