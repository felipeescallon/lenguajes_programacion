// Leer.class debe estar en la carpeta especificada por CLASSPATH
public class CMatrizUnidimensional
{
  // Trabajar con una matriz unidimensional
  public static void main(String[] args)
  {
    int nAlumnos;    // número de alumnos (valor no negativo)
    do
    {
      System.out.print("Número de alumnos: ");
      nAlumnos = Leer.datoInt();
    }
    while (nAlumnos < 1);
    float[] nota = new float[nAlumnos]; // crear la matriz nota
    int i = 0;       // subíndice
    float suma = 0F; // suma total de las notas medias
    
    System.out.println("Introducir las notas medias del curso.");
    for (i = 0; i < nota.length; i++)
    {
      System.out.print("Nota media del alumno " + (i+1) + ": ");
      nota[i] = Leer.datoFloat();
    }
    
    // Sumar las notas medias
    for (i = 0; i < nota.length; i++)
      suma += nota[i];

    // Visualizar la nota media del curso
    System.out.println("\n\nNota media del curso: " + suma / nAlumnos);
  }
}
