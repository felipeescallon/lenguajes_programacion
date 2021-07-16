import java.util.*;

public class CRandom
{
  // Números aleatorios entre 0 y 1
  public static double rnd(int[] random)
  {
    random[0] = (25173 * random[0] + 13849) % 65536;
    return ((double)random[0] / 65535);
  }

  public static void main(String[] args)
  {
    int inicio = (int)((new Date()).getTime()%65536); // semilla
    int[] random = {inicio}; // random = número entre 0 y 65535

    // Generar números seudoaleatorios
    double n;
    for (int i = 10; i != 0; i--)
    {
      n = rnd(random);
      System.out.println(n);
    }
  }
}
