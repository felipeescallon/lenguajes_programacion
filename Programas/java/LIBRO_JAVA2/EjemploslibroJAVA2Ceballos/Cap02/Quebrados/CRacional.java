class CRacional
{
  int Numerador;
  int Denominador;
  
  void AsignarDatos(int num, int den)
  {
    Numerador = num;
    if (den == 0) den = 1; // el denominador no puede ser cero
    Denominador = den;
  }
  
  void VisualizarRacional()
  {
    System.out.println(Numerador + "/" + Denominador);
  }
  
  public static void main (String[] args)
  {
    // Punto de entrada a la aplicación
    CRacional r1 = new CRacional(); // crear un objeto CRacional
    
    r1.AsignarDatos(2, 5);
    r1.VisualizarRacional();
  }
}