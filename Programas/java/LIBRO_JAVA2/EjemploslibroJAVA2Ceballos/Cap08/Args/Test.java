public class Test
{
  public static void main(String[] args)
  {
    // Código común a todos los casos
    System.out.println("Argumentos: ");
    if (args.length == 0)
    {
      // Escriba aquí el código que sólo se debe ejecutar cuando
      // no se pasan argumentos
      System.out.println("    ninguno");
    }
    else
    {
      boolean argumento_k = false, argumento_l = false,
              argumento_n = false;
 
      // ¿Qué argumentos se han pasado?
      for (int i = 0; i < args.length; i++)
      {
        if (args[i].compareTo("-k") == 0) argumento_k = true;
        if (args[i].compareTo("-l") == 0) argumento_l = true;
        if (args[i].compareTo("-n") == 0) argumento_n = true;
      }

      if (argumento_k) // si se pasó el argumento -k:
      {
        // Escriba aquí el código que sólo se debe ejecutar cuando
        // se pasa el argumento -k
        System.out.println("    -k");
      }
      if (argumento_l) // si se pasó el argumento -l:
      {
        // Escriba aquí el código que sólo se debe ejecutar cuando
        // se pasa el argumento -l
        System.out.println("    -l");
      }
      if (argumento_n) // si se pasó el argumento -n:
      {
        // Escriba aquí el código que sólo se debe ejecutar cuando
        // se pasa el argumento -n
        System.out.println("    -n");
      }
    }
    // Código común a todos los casos
  }
}
