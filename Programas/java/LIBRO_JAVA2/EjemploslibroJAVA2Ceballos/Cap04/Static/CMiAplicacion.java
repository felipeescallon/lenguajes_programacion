public class CMiAplicacion
{
  public static void main (String[] args)
  {
    COrdenador.EstablecerGarant�a((byte)3);
  }
}

class COrdenador
{
  private String Marca;
  private String Procesador;
  private String Pantalla;
  private static byte Garant�a;
  private boolean OrdenadorEncendido;
  private boolean Presentaci�n;
  // ...
  
  public static void EstablecerGarant�a(byte g)
  {
    Garant�a = g; // Garant�a es un miembro de la clase
  }
}
