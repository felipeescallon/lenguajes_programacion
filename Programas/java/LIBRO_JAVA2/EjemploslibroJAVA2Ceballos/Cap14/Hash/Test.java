import java.io.*;
//////////////////////////////////////////////////////////////////
// Aplicaci�n para trabajar con una matriz hash
//
public class Test
{
  public static void main(String[] args)
  {
    // Definici�n de variables
    PrintStream flujoS = System.out;
    int n_elementos; // n�mero de elementos de la matriz hash
    int i;
    String nombre;
    int matr�cula;
    CAlumno x;
  
    // Crear un objeto HashAbierto (encapsula la matriz hash)
    System.out.println("n�mero de elementos:  ");
    n_elementos = Leer.datoInt();
    HashAbierto m = new HashAbierto(n_elementos);
    flujoS.println("Se construye una matriz de " +
                    m.n�meroDeElementos() + " elementos");
    
    // Introducir datos
    flujoS.println("Introducir datos. " +
                   "Para finalizar, matr�cula = 0\n");
    flujoS.print("matr�cula:  "); matr�cula = Leer.datoInt();
    while (matr�cula != 0)
    {
      flujoS.print("nombre:     "); nombre = Leer.dato();
      m.hashIn(new CAlumno(nombre, matr�cula));
      flujoS.print("matr�cula:  "); matr�cula = Leer.datoInt();
    }
    
    // Buscar datos
    flujoS.println("Buscar datos. " +
                   "Para finalizar, matr�cula = 0\n");
    flujoS.print("matr�cula:  "); matr�cula = Leer.datoInt();
    while (matr�cula != 0)
    {
      x = (CAlumno)m.hashOut(new CAlumno("", matr�cula));
      if (x != null)
        flujoS.println("nombre: " + x.obtenerNombre());
      else
        flujoS.println("No existe");
      flujoS.print("matr�cula:  "); matr�cula = Leer.datoInt();
    }

  }
}
