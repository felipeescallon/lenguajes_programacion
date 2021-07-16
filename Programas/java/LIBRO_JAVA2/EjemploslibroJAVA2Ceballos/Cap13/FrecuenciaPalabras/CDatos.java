public class CDatos
{
  // Atributos
  private String palabra;
  private int contador;

  // Métodos
  public CDatos() {}        // constructor sin parámetros
  
  public CDatos(String pal) // constructor con un parámetro
  {
    palabra = pal;
    contador = 0;
  }
  
  public CDatos(String pal, int cont) // constructor con dos parámetros
  {
    palabra = pal;
    contador = cont;
  }

  public void asignarPalabra(String pal)
  {
    palabra = pal;
  }
  
  public String obtenerPalabra()
  {
    return palabra;
  }
  
  public void asignarContador(int cont)
  {
    contador = cont;
  }
  
  public int obtenerContador()
  {
    return contador;
  }
}
