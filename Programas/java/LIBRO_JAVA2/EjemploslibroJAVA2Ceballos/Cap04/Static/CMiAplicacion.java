public class CMiAplicacion
{
  public static void main (String[] args)
  {
    COrdenador.EstablecerGarantía((byte)3);
  }
}

class COrdenador
{
  private String Marca;
  private String Procesador;
  private String Pantalla;
  private static byte Garantía;
  private boolean OrdenadorEncendido;
  private boolean Presentación;
  // ...
  
  public static void EstablecerGarantía(byte g)
  {
    Garantía = g; // Garantía es un miembro de la clase
  }
}
