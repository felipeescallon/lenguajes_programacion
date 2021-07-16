public class CDatos
{
  // Atributos
  private String nombre;
  private double nota;

  // Métodos
  public CDatos() {}        // constructor sin parámetros
  public CDatos(String nom, double n) // constructor con parámetros
  {
    nombre = nom;
    nota = n;
  }

  public void asignarNombre(String nom)
  {
    nombre = nom;
  }
  
  public String obtenerNombre()
  {
    return nombre;
  }
  
  public void asignarNota(double n)
  {
    nota = n;
  }
  
  public double obtenerNota()
  {
    return nota;
  }
}
