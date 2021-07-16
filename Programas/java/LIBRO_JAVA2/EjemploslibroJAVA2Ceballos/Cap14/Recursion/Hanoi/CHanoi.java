/////////////////////////////////////////////////////////////////
// Torres de Hanoi
//
public class CHanoi
{
  private static int movimientos = 0;
  
  public static int mover(int n_discos, char a, char b, char c)
  {
    if (n_discos > 0)
    {
      mover(n_discos-1, a, c, b);
      System.out.println("mover disco de "+ a + " a " + c);
      movimientos++;
      mover(n_discos-1, b, a, c);
    }
    return movimientos;
  }
}
/////////////////////////////////////////////////////////////////
