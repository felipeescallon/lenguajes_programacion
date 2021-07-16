class COrdenador
{
  String Marca;
  String Procesador;
  String Pantalla;
  boolean OrdenadorEncendido;
  boolean Presentaci�n;
  
  void EncenderOrdenador()
  {
    if (OrdenadorEncendido == true) // si est� encendido...
      System.out.println("El ordenador ya est� encendido.");
    else // si no est� encendido, encenderlo.
    {
      OrdenadorEncendido = true;
      System.out.println("El ordenador se ha encendido.");
    }
  }
  
  void Estado()
  {
    System.out.println("\nEstado del ordenador:" +
                       "\nMarca " + Marca +
                       "\nProcesador " + Procesador +
                       "\nPantalla " + Pantalla + "\n");
    if (OrdenadorEncendido == true) // si el ordenador est� encendido...
      System.out.println("El ordenador est� encendido.");
    else // si no est� encendido...
      System.out.println("El ordenador est� apagado.");
  }

  public static void main (String[] args)
  {
    COrdenador MiOrdenador = new COrdenador();
    MiOrdenador.Marca = "Ast";
    MiOrdenador.Procesador = "Intel Pentium";
    MiOrdenador.Pantalla = "TFT";
    MiOrdenador.EncenderOrdenador();
    MiOrdenador.Estado();
  }
}
