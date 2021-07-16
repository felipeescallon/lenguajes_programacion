import java.io.*;
// Utiliza Leer.class que está en CLASSPATH=c:\jdk1.2.2\misClases
public class CMatriz1Cadenas
{
  public static void main(String[] args)
  {
    try
    {
      // Definir un flujo de caracteres de entrada: flujoE
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader flujoE = new BufferedReader(isr);

      // Definir una referencia al flujo estándar de salida: flujoS
      PrintStream flujoS = System.out;

      int nFilas = 0, nCarsPorFila = 0;
      int fila = 0, nCarsLeidos = 0, eof = -1;
      do
      {
        System.out.print("Número de filas de la matriz:  ");
        nFilas = Leer.datoInt();
      }
      while (nFilas < 1);       // no permitir un valor negativo
      do
      {
        System.out.print("Número de caracteres por fila: ");
        nCarsPorFila = Leer.datoInt();
      }
      while (nCarsPorFila < 1); // no permitir un valor negativo
      // Matriz de cadenas de caracteres
      char[][] nombre = new char[nFilas][nCarsPorFila];
    
      System.out.println("Escriba los nombres que desea introducir.");
      System.out.println("Para finalizar pulse las teclas [Ctrl][Z].");
      for (fila = 0; fila < nFilas; fila++)
      {
        flujoS.print("Nombre[" + fila + "]: ");
        nCarsLeidos = flujoE.read(nombre[fila], 0, nCarsPorFila);
        // Si se pulsó [Ctrl][Z], salir del bucle
        if (nCarsLeidos == eof) break;
        // Eliminar los caracteres CR LF
        nombre[fila][nCarsLeidos-1] = '\0';
        nombre[fila][nCarsLeidos-2] = '\0';
      }
      flujoS.print("\n\n");
      nFilas = fila; // número de filas leídas
      char respuesta;
      do
      {
        flujoS.print("¿Desea visualizar el contenido de la matriz? (s/n): ");
        respuesta = ((flujoE.readLine()).toLowerCase()).charAt(0);
      }
      while (respuesta != 's' && respuesta != 'n');
      if ( respuesta == 's' )
      {
        // Visualizar la lista de nombres
        flujoS.println();
        for (fila = 0; fila < nFilas; fila++)
          flujoS.println(nombre[fila]);
      }
    }
    catch (IOException ignorada) { }
  }
}
