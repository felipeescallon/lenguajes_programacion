// Utiliza la clase Leer que debe de estar almacenada
// en la misma carpeta

public class LeerDatos
{
  public static void main(String[] args)
  {
    boolean eof = true;
    float[] a = new float[100];
    int i = 0;
    System.out.println("Introducir datos. Finalizar con Ctrl+Z");
    System.out.print("Dato float: ");
    while (i < 100 && Float.isNaN(a[i] = Leer.datoFloat()) != eof)
    {
      i++;
      System.out.print("Dato float: ");
    }
    System.out.println();
    for (int k = 0; k < i; k++)
      System.out.print(a[k] + " ");
    System.out.println();
  }
}
