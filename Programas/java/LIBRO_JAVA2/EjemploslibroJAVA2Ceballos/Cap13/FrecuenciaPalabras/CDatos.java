public class CDatos
{
  // Atributos
  private String palabra;
  private int contador;

  // M�todos
  public CDatos() {}        // constructor sin par�metros
  
  public CDatos(String pal) // constructor con un par�metro
  {
    palabra = pal;
    contador = 0;
  }
  
  public CDatos(String pal, int cont) // constructor con dos par�metros
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
