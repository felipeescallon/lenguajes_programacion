/////////////////////////////////////////////////////////////////
// Definición de la clase CAlumno
//
public class CAlumno
{
  // Atributos
  private int matrícula;
  private String nombre;
  
  // Métodos
  public CAlumno() {}
  public CAlumno(String nom, int mat)
  {
    nombre = nom;
    matrícula = mat;
  }
  
  public void asignarNombre(String nom)
  {
    nombre = nom;
  }
  
  public String obtenerNombre()
  {
    return nombre;
  }
  
  public void asignarMatrícula(int mat)
  {
    matrícula = mat;
  }
  
  public long obtenerMatrícula()
  {
    return matrícula;
  }
}
