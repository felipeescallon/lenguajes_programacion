/////////////////////////////////////////////////////////////////
// Definición de la clase CRegistro
//
public class CRegistro
{
  // Atributos
  private String referencia;
  private double precio;
  
  // Métodos
  public CRegistro() {}
  public CRegistro(String ref, double pre)
  {
    referencia = ref;
    precio = pre;
  }
  
  public void asignarReferencia(String ref)
  {
    referencia = ref;
  }
  
  public String obtenerReferencia()
  {
    return referencia;
  }
  
  public void asignarPrecio(double pre)
  {
    precio = pre;
  }
  
  public double obtenerPrecio()
  {
    return precio;
  }
  
  public int tamaño()
  {
    // Longitud en bytes de los atributos (un Double = 8 bytes)
    return referencia.length()*2 + 8;
  }
}
