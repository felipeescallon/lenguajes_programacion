//////////////////////////////////////////////////////////////////
// Torres de Hanoi
//
public class Test
{
  public static void main(String[] args)
  {
    int n_discos, movimientos;
    System.out.print("N�mero de discos: ");
    n_discos = Leer.datoInt();
    movimientos = CHanoi.mover(n_discos, 'A', 'B', 'C');
    System.out.println("\nmovimientos efectuados: " + movimientos);
  }
}
//////////////////////////////////////////////////////////////////
