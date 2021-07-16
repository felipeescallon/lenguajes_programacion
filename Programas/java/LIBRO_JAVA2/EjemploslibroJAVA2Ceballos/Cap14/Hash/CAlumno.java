/////////////////////////////////////////////////////////////////
// Definici�n de la clase CAlumno
//
public class CAlumno
{
  // Atributos
  private int matr�cula;
  private String nombre;
  
  // M�todos
  public CAlumno() {}
  public CAlumno(String nom, int mat)
  {
    nombre = nom;
    matr�cula = mat;
  }
  
  public void asignarNombre(String nom)
  {
    nombre = nom;
  }
  
  public String obtenerNombre()
  {
    return nombre;
  }
  
  public void asignarMatr�cula(int mat)
  {
    matr�cula = mat;
  }
  
  public long obtenerMatr�cula()
  {
    return matr�cula;
  }
}
