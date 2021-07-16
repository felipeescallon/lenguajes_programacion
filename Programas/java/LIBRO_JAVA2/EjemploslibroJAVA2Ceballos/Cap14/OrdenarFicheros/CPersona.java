/////////////////////////////////////////////////////////////////
// Definición de la clase CPersona
//
public class CPersona
{
  // Atributos
  private String nombre;
  private String dirección;
  private long teléfono;
  
  // Métodos
  public CPersona() {}
  public CPersona(String nom, String dir, long tel)
  {
    nombre = nom;
    dirección = dir;
    teléfono = tel;
  }
  
  public void asignarNombre(String nom)
  {
    nombre = nom;
  }
  
  public String obtenerNombre()
  {
    return nombre;
  }
  
  public void asignarDirección(String dir)
  {
    dirección = dir;
  }
  
  public String obtenerDirección()
  {
    return dirección;
  }
  
  public void asignarTeléfono(long tel)
  {
    teléfono = tel;
  }
  
  public long obtenerTeléfono()
  {
    return teléfono;
  }
  
  public int tamaño()
  {
    // Longitud en bytes de los atributos (un Long = 8 bytes)
    return nombre.length()*2 + dirección.length()*2 + 8;
  }
}
