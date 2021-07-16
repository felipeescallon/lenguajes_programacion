// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CAjedrez
{
  // Imprimir un tablero de ajedrez.
  public static void main(String[] args)
  {
    int falfil, calfil; // posición inicial del alfil
    int fila, columna;  // posición actual del alfil
    
    System.out.println("Posición del alfil:");
    System.out.print("  fila    "); falfil = Leer.datoInt();
    System.out.print("  columna "); calfil = Leer.datoInt();
    System.out.println(); // dejar una línea en blanco
    
    // Pintar el tablero de ajedrez
    for (fila = 1; fila <= 8; fila++)
    {
      for (columna = 1; columna <= 8; columna++)
      {
        if ((fila + columna == falfil + calfil) ||
           (fila - columna == falfil - calfil))
          System.out.print("* ");
        else if ((fila + columna) % 2 == 0)
          System.out.print("B ");
        else
          System.out.print("N ");
      }
      System.out.println(); // cambiar de fila
    }
  }
}
