import java.io.*;
//////////////////////////////////////////////////////////////////
// Aplicación para trabajar con una matriz hash
//
public class Test
{
  public static void main(String[] args)
  {
    // Definición de variables
    PrintStream flujoS = System.out;
    int n_elementos; // número de elementos de la matriz hash
    int i;
    String nombre;
    int matrícula;
    CAlumno x;
  
    // Crear un objeto HashAbierto (encapsula la matriz hash)
    System.out.println("número de elementos:  ");
    n_elementos = Leer.datoInt();
    HashAbierto m = new HashAbierto(n_elementos);
    flujoS.println("Se construye una matriz de " +
                    m.númeroDeElementos() + " elementos");
    
    // Introducir datos
    flujoS.println("Introducir datos. " +
                   "Para finalizar, matrícula = 0\n");
    flujoS.print("matrícula:  "); matrícula = Leer.datoInt();
    while (matrícula != 0)
    {
      flujoS.print("nombre:     "); nombre = Leer.dato();
      m.hashIn(new CAlumno(nombre, matrícula));
      flujoS.print("matrícula:  "); matrícula = Leer.datoInt();
    }
    
    // Buscar datos
    flujoS.println("Buscar datos. " +
                   "Para finalizar, matrícula = 0\n");
    flujoS.print("matrícula:  "); matrícula = Leer.datoInt();
    while (matrícula != 0)
    {
      x = (CAlumno)m.hashOut(new CAlumno("", matrícula));
      if (x != null)
        flujoS.println("nombre: " + x.obtenerNombre());
      else
        flujoS.println("No existe");
      flujoS.print("matrícula:  "); matrícula = Leer.datoInt();
    }

  }
}
