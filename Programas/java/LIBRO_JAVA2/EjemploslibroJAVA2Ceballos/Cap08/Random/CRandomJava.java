import java.util.*;

public class CRandomJava
{
  // Obtener números dentro de un rango
  public static void main(String[] args)
  {
    int límiteSup = 49, límiteInf = 1;
    int n[] = new int[6], i, k;

    for (i = 0; i < n.length; i++)
    {
      // Obtener un número aleatorio
      n[i] = (int)((límiteSup - límiteInf + 1) * Math.random() +
                   límiteInf);
      // Verificar si ya existe el último número obtenido
      for (k = 0; k < i; k++)
        if (n[k] == n[i]) // ya existe
        {
          i--;   // i será incrementada por el for externo
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
